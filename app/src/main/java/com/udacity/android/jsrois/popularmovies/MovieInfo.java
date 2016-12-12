package com.udacity.android.jsrois.popularmovies;

import android.content.Context;
import android.util.Log;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by jsrois on 12/12/16.
 */
public class MovieInfo {
    private String title;
    private String overview;
    private String posterPath;

    public MovieInfo(JSONObject jsonObject) {
        try {
            title = jsonObject.getString("title");
            overview = jsonObject.getString("overview");
            posterPath = jsonObject.getString("poster_path");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // for the tiled view
    public void printTo(MovieAdapter.MovieAdapterViewHolder viewHolder) {
        Context context = viewHolder.posterView.getContext();
        // poster
        Log.i("", "printTo: " +posterPath);

        String posterUrl = MovieDatabase.getPosterUrl(posterPath).toString();
        Log.i("", "printTo: "+ posterUrl);
        Picasso.with(context)
                .load(posterUrl)
                .into(viewHolder.posterView);
    }
}
