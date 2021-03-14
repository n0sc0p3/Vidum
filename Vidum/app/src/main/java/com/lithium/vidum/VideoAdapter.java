package com.lithium.vidum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class VideoAdapter extends ArrayAdapter<Video> {
    Context context;

    public VideoAdapter(Context context, int simple_list_item_1, ArrayList<Video> videos) {
        super(context, simple_list_item_1, videos);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.video_item, parent, false);
        }

        Video currentVideo = getItem(position);
        assert currentVideo != null;

        ImageView thumbnailImageView = (ImageView) listItemView.findViewById(R.id.thumbnail);
        ImageLoadTask imageLoadTask = null;

              imageLoadTask =  new ImageLoadTask(currentVideo.getThumbnailURL(), thumbnailImageView);
              imageLoadTask.execute();

        ((ListActivity)context).setImageLoadTask(imageLoadTask);




        TextView nameTextView = (TextView) listItemView.findViewById(R.id.video_name);
        nameTextView.setText(currentVideo.getName());

        TextView authorTextView = (TextView) listItemView.findViewById(R.id.video_author);
        authorTextView.setText(currentVideo.getAuthor());

        return listItemView;
    }

    public Video getItemAtPosition(int position){
        return getItem(position);
    }

}
