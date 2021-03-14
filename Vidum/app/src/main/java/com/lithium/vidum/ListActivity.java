package com.lithium.vidum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ArrayList<Video> videos = new ArrayList<Video>();
    ImageLoadTask imageLoadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String accountURL = intent.getStringExtra("accountURL");
        String categoryName = intent.getStringExtra("categoryName");
        String videosJSONArray = intent.getStringExtra("JSONArray");
        try {
            JSONArray videosArray = new JSONArray(videosJSONArray);

        for (int i = 0; i < videosArray.length(); ++i) {
            videos.add(new Video(accountURL + videosArray.getJSONObject(i).getString("fileName") + ".jpg",
                    accountURL + videosArray.getJSONObject(i).getString("fileName") + ".mp4", videosArray.getJSONObject(i).getString("fileName"),
                    videosArray.getJSONObject(i).getString("name"), videosArray.getJSONObject(i).getString("author")));

        }} catch (JSONException e) {
            e.printStackTrace();
        }


        super.onCreate(savedInstanceState);


        setContentView(R.layout.videos_list);
        setTitle(categoryName);


                ListView videosListView = findViewById(R.id.list);


                VideoAdapter adapter = new VideoAdapter(
                        ListActivity.this, android.R.layout.simple_list_item_1, videos);

                videosListView.setAdapter(adapter);


                videosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Video video = adapter.getItemAtPosition(position);

                        Intent intent = new Intent(ListActivity.this, VideoDetails.class);
                        intent.putExtra("videoURL", video.getVideoURL());
                        intent.putExtra("videoName", video.getName());
                        intent.putExtra("fileName", video.getFileName());
                        startActivity(intent);
                    }
                });
    }

    public void setImageLoadTask(ImageLoadTask imageLoadTask){
        this.imageLoadTask = imageLoadTask;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        imageLoadTask.cancel(true);
        this.finish();
    }
}