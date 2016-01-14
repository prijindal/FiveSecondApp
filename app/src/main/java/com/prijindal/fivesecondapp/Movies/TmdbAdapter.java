package com.prijindal.fivesecondapp.Movies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.prijindal.fivesecondapp.MovieInfo;
import com.prijindal.fivesecondapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Priyanshu on 01/12/16.
 */
public class TmdbAdapter extends RecyclerView.Adapter<TmdbAdapter.ViewHolder> {
    public static String TAG = "Tmdb Adapter";
    public String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/w320";
    public String IMAGE_KEY = "poster_path";
    private Context context;
    private ArrayList<JSONObject> mJsonObjects;
    ImageLoader imageLoader;


    public TmdbAdapter(Context context, ArrayList<JSONObject> list) {
        this.mJsonObjects = list;
        this.context = context;
        imageLoader = ImageLoader.getInstance(); // Get singleton instance
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(context).inflate(R.layout.movie_card, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.main_container.setImageDrawable(null);
        JSONObject jsonObject = mJsonObjects.get(position);
        try {
            String url = jsonObject.getString(IMAGE_KEY);
            Log.v(TAG, jsonObject.getString("original_title"));
            holder.setId(jsonObject.getInt("id"));
            imageLoader.displayImage(BASE_IMAGE_URL + url, holder.main_container);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mJsonObjects.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView main_container;
        private int id;

        public ViewHolder(View view) {
            super(view);
            main_container = (ImageView) view.findViewById(R.id.main_container);

            view.setOnClickListener(this);
        }
        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, MovieInfo.class);
            intent.putExtra("MOVIE_ID", this.getId());
            context.startActivity(intent);
        }
    }
}
