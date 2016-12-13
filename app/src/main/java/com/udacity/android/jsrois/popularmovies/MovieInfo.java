package com.udacity.android.jsrois.popularmovies;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;


/**
 * Created by jsrois on 12/12/16.
 */
public class MovieInfo implements Parcelable{
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

    protected MovieInfo(Parcel in) {
        title = in.readString();
        overview = in.readString();
        posterPath = in.readString();
    }

    public static final Creator<MovieInfo> CREATOR = new Creator<MovieInfo>() {
        @Override
        public MovieInfo createFromParcel(Parcel in) {
            return new MovieInfo(in);
        }

        @Override
        public MovieInfo[] newArray(int size) {
            return new MovieInfo[size];
        }
    };

    // for the tiled view
    public void printPosterTo(MoviesAdapter.MovieAdapterViewHolder viewHolder) {
        printPosterTo(viewHolder.posterView);
    }

    public void printPosterTo(ImageView view) {
        Context context = view.getContext();
        String posterUrl = MovieDatabase.getPosterUrl(posterPath).toString();
        Picasso.with(context)
                .load(posterUrl)
                .into(view);
    }

    void printTitleTo(TextView textView) {
        textView.setText(title);
    }

    void printOverviewTo(TextView textView) {
        textView.setText(overview);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.overview);
        parcel.writeString(this.posterPath);
    }
}
