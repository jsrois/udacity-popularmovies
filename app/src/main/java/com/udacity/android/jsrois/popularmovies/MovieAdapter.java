package com.udacity.android.jsrois.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by jsrois on 12/12/16.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {
    private MovieInfo[] moviesInfo;

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_grid_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
        Log.i("", "onBindViewHolder: ");
        MovieInfo movieInfo = moviesInfo[position];
        movieInfo.printTo(holder);
    }

    @Override
    public int getItemCount() {
        if (moviesInfo == null) {
            return 0;
        }
        return moviesInfo.length;
    }

    public void setMoviesInfo(MovieInfo[] moviesInfo) {
        this.moviesInfo = moviesInfo;
        notifyDataSetChanged();
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder{
        public ImageView posterView;
        public MovieAdapterViewHolder(View view) {
            super(view);
            posterView = (ImageView) view.findViewById(R.id.iv_movie_poster);
            // TODO setOnClickListener
        }
    }


}
