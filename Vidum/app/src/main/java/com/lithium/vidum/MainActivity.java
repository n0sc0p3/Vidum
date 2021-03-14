package com.lithium.vidum;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    JSONObject videos;
    String accountURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ProgressDialog dialog = ProgressDialog.show(this, "Loading", "Please wait...", true);
        StringRequest request = new StringRequest("https://raw.githubusercontent.com/n0sc0p3/Vidum/master/paths.json", new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                parseJsonData(string, dialog);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Some error occurred!!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
        rQueue.add(request);



        Button english = (Button)findViewById(R.id.english);
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra("accountURL", accountURL);
                intent.putExtra("categoryName", "English");
                try {
                    intent.putExtra("JSONArray", videos.getJSONArray("english").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        });
        Button hindi = (Button)findViewById(R.id.hindi);
        hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra("accountURL", accountURL);
                intent.putExtra("categoryName", "Hindi");
                try {
                    intent.putExtra("JSONArray", videos.getJSONArray("hindi").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        });
    }

    void parseJsonData(String jsonString, ProgressDialog dialog) {
        try {
            JSONObject object = new JSONObject(jsonString);
            accountURL = object.getString("account");
            videos = object.getJSONObject("videos");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        dialog.dismiss();
    }
}