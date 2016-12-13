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
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieAdapterViewHolder> {
    private MovieInfo[] moviesInfo;


    MoviesAdapterOnClickHandler onClickHandler;

    public interface MoviesAdapterOnClickHandler {
        void onClick(MovieInfo movieInfo);
    }


    public MoviesAdapter(MoviesAdapterOnClickHandler onClickHandler) {
        this.onClickHandler = onClickHandler;
    }

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
        movieInfo.printPosterTo(holder);
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

    public class MovieAdapterViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public ImageView posterView;
        public MovieAdapterViewHolder(View view) {
            super(view);
            posterView = (ImageView) view.findViewById(R.id.iv_movie_poster);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            MovieInfo movieInfo = moviesInfo[adapterPosition];
            onClickHandler.onClick(movieInfo);
        }
    }


}
