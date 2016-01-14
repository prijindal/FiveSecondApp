package com.prijindal.fivesecondapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MovieInfo extends AppCompatActivity {
    public String TAG = "Movie Info";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        Bundle extras = getIntent().getExtras();
        int id = extras.getInt("MOVIE_ID");
        Log.v(TAG, "ID: " + id);
    }
}
