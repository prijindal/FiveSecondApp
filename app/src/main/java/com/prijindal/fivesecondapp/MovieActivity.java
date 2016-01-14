package com.prijindal.fivesecondapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.prijindal.fivesecondapp.Movies.TmdbAdapter;
import com.prijindal.fivesecondapp.Movies.TmdbApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity {
    public static String TAG = "Movie Activity";
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.movies);
        TmdbApi.refreshMovieDatabase(this); // This Function in turn is calling populate movies function

        recyclerView = (RecyclerView) findViewById(
                R.id.list_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        EditText search_text = (EditText) findViewById(R.id.input_search);
        search_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.v(TAG, "BEFORE" + ":" + charSequence);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.v(TAG, "ON" + ":" + charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.v(TAG, "After" + ":" + editable.toString());
                TmdbApi.searchMovies(MovieActivity.this, editable.toString());
            }
        });
    }

    public void populateMovies(JSONObject jsonObject) {
        ArrayList<JSONObject> jsonObjects = new ArrayList<JSONObject>();

        try {
            JSONArray results = jsonObject.getJSONArray("results");
//            Log.v(TAG, results.toString());

            for (int i = 0; i<results.length(); ++i) {
                jsonObjects.add(results.getJSONObject(i));
            }
            TmdbAdapter adapter = new TmdbAdapter(this, jsonObjects);
            recyclerView.setAdapter(adapter);
        }
        catch (NullPointerException e) {
            Log.e(TAG, "Can't connect to internet");
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        }
    }
}
