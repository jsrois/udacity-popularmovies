package com.udacity.android.jsrois.popularmovies;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by jsrois on 12/12/16.
 */
class MovieDatabase {
    private static final String MOVIEDB_BASE_URL = "http://api.themoviedb.org/3/movie";
    private static final String API_KEY_PARAM = "api_key";
    private static final String MOVIEDB_IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";

    MovieInfo[] getMovies(String filter) {
        URL popularMoviesUrl = buildQueryUrl(filter);
        try {
            String jsonResponse = getResponseFromHttpUrl(popularMoviesUrl);
            Log.i("", "getMovies: " + jsonResponse);
            return getMovieInfoArrayFromJson(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private MovieInfo[] getMovieInfoArrayFromJson(String jsonResponse) {
        MovieInfo[] infoArray;
        try {
            JSONObject movies = new JSONObject(jsonResponse);
            JSONArray results = movies.getJSONArray("results");
            infoArray = new MovieInfo[results.length()];
            for (int i=0; i<results.length(); i++) {
                // It is the MovieInfo object who manages construction
                infoArray[i] = new MovieInfo(results.getJSONObject(i));
            }
            return infoArray;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new MovieInfo[0];
    }

    private String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    private URL buildQueryUrl(String path) {
        Uri builtUri = Uri.parse(MOVIEDB_BASE_URL).buildUpon()
                .appendPath(path)
                .appendQueryParameter(API_KEY_PARAM, BuildConfig.API_KEY)
                .build();

        URL url;
        try {
            url = new URL(builtUri.toString());
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static URL getPosterUrl(String posterPath) {

        Uri builtUri = Uri.parse(MOVIEDB_IMAGE_BASE_URL).buildUpon()
                .appendPath("w185")
                // sanitize (removing the starting "/" )
                .appendPath(posterPath.replace("/",""))
                .appendQueryParameter(API_KEY_PARAM, BuildConfig.API_KEY)
                .build();

        URL url;
        try {
            url = new URL(builtUri.toString());
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
