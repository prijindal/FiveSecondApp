package com.prijindal.fivesecondapp.Movies;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.prijindal.fivesecondapp.MovieActivity;
import com.prijindal.fivesecondapp.MovieInfo;

import org.json.JSONObject;

/**
 * Created by Priyanshu on 01/12/16.
 */
public class TmdbApi {
    public static String TAG = "TMDB Api";
    public static String API_KEY = "3106d2441353062ff3c817d38d7240ae";
    public static String discoverMovies = "http://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY;
    public static String searchMovies = "http://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&query=";

    public static void refreshMovieDatabase(final MovieActivity context) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, discoverMovies, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        context.populateMovies(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.toString());
                    }
                }
        );
        Volley.newRequestQueue(context.getApplicationContext()).add(jsonObjectRequest);
    }

    public static void searchMovies(final MovieActivity context, String keyword) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, searchMovies + keyword, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        context.populateMovies(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.toString());
                    }
                }
        );
        Volley.newRequestQueue(context.getApplicationContext()).add(jsonObjectRequest);
    }
}
