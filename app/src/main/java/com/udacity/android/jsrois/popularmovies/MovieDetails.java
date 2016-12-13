package com.udacity.android.jsrois.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MovieDetails extends AppCompatActivity {


    private ImageView posterView;
    private TextView titleView;
    private TextView overviewView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // get the data!
        MovieInfo movieInfo = getIntent().getParcelableExtra("MovieInfo");
        // set poster
        // set overview and title
        // set rating
        posterView = (ImageView) findViewById(R.id.iv_movie_poster);
        titleView = (TextView) findViewById(R.id.tv_title);
        overviewView = (TextView) findViewById(R.id.tv_overview);
        movieInfo.printOverviewTo(overviewView);
        movieInfo.printPosterTo(posterView);
        movieInfo.printTitleTo(titleView);

    }
}
