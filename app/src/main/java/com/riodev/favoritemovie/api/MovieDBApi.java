package com.riodev.favoritemovie.api;

import android.net.Uri;


import com.riodev.favoritemovie.BuildConfig;

import java.net.MalformedURLException;
import java.net.URL;

public class MovieDBApi {
    static final private String BASE_URL = BuildConfig.BASE_URL;
    static final private String BASE_URL_POSTER = BuildConfig.BASE_URL_POSTER;
    static final private String API_KEY = BuildConfig.TMDB_API_KEY;
    static final private String POSTER_SIZE = "w185";
    static final private String PARAM_API_KEY = "api_key";
    static final private String MOVIE = "movie";
    static final private String POPULAR = "popular";
    static final private String SEARCH = "search";
    static final private String LANGUAGE = "language";
    static final private String QUERY = "query";
    static final private String EN = "en-US";
    static final private String NOW_PLAYING = "now_playing";
    static final private String UPCOMING = "upcoming";

    public static URL getPoster(String path){
        // https://image.tmdb.org/t/p/{POSTER SIZE}/{POSTER FILENAME}
        path = path.startsWith("/")? path.substring(1) : path;
        Uri uri = Uri.parse(BASE_URL_POSTER).buildUpon()
                .appendPath(POSTER_SIZE)
                .appendPath(path)
                .build();

        return getUrl(uri);
    }


    private static URL getUrl(Uri uri){
        URL url = null;
        try{
            url = new URL(uri.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }


}
