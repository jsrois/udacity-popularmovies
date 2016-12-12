package com.udacity.android.jsrois.popularmovies;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    ProgressBar progressBar;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.pb_loading_movies);

        recyclerView = (RecyclerView) findViewById(R.id.rv_activity_main);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        movieAdapter = new MovieAdapter();
        recyclerView.setAdapter(movieAdapter);

        if (!online()) {
            if (toast != null) {
                toast.cancel();
            }
            toast = Toast.makeText(this,"Unable to connect to server",Toast.LENGTH_SHORT);
            return;
        }
        getMovieData();
    }

    private boolean online() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void getMovieData() {
        new QueryMovieDataTask().execute();
    }


    class QueryMovieDataTask extends AsyncTask<Void,Void,MovieInfo[]> {

        MovieDatabase movieDatabase;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            movieDatabase = new MovieDatabase();
        }

        @Override
        protected MovieInfo[] doInBackground(Void... voids) {
            return movieDatabase.getPopularMovies();
        }

        @Override
        protected void onPostExecute(MovieInfo[] movieInfos) {
            progressBar.setVisibility(View.INVISIBLE);
            Log.i("MOVIE_INFO","onPostExecute: "+movieInfos.length);
            movieAdapter.setMoviesInfo(movieInfos);
            super.onPostExecute(movieInfos);
        }


    }

}
