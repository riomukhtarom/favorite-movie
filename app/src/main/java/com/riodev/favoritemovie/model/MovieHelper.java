package com.riodev.favoritemovie.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


import com.riodev.favoritemovie.database.DatabaseHelper;

import java.util.ArrayList;

import static com.riodev.favoritemovie.database.DatabaseContract.MovieColumn.GENRES;
import static com.riodev.favoritemovie.database.DatabaseContract.MovieColumn.LANGUAGE;
import static com.riodev.favoritemovie.database.DatabaseContract.MovieColumn.OVERVIEW;
import static com.riodev.favoritemovie.database.DatabaseContract.MovieColumn.POPULARITY;
import static com.riodev.favoritemovie.database.DatabaseContract.MovieColumn.POSTER_PATH;
import static com.riodev.favoritemovie.database.DatabaseContract.MovieColumn.RATING;
import static com.riodev.favoritemovie.database.DatabaseContract.MovieColumn.RELEASE_DATE;
import static com.riodev.favoritemovie.database.DatabaseContract.MovieColumn.TITLE;
import static com.riodev.favoritemovie.database.DatabaseContract.MovieColumn._ID;


public class MovieHelper {
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public MovieHelper(Context context) {
        this.context = context;
    }

    public MovieHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        databaseHelper.close();
    }

    public ArrayList<Movie> getMovie(String table, String title){
        Cursor cursor = database.query(table, null, TITLE + " = ?", new String[]{title}, null,null, null);
        cursor.moveToFirst();
        ArrayList<Movie> listMovie = new ArrayList<>();
        Movie movieModel;
        if (cursor.getCount()>0){
            do{
                movieModel = new Movie();
                movieModel.setId(cursor.getString(cursor.getColumnIndexOrThrow(_ID)));
                movieModel.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                movieModel.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                movieModel.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(POSTER_PATH)));
                movieModel.setRating(cursor.getString(cursor.getColumnIndexOrThrow(RATING)));
                movieModel.setPopularity(cursor.getString(cursor.getColumnIndexOrThrow(POPULARITY)));
                movieModel.setLanguage(cursor.getString(cursor.getColumnIndexOrThrow(LANGUAGE)));
                movieModel.setGenres(cursor.getString(cursor.getColumnIndexOrThrow(GENRES)));
                movieModel.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));
                listMovie.add(movieModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return listMovie;
    }

    public ArrayList<Movie> getAllData(String table) {
        Cursor cursor = database.query(table, null, null, null, null, null, _ID +" ASC", null);
        cursor.moveToFirst();
        ArrayList<Movie> listMovie = new ArrayList<>();
        Movie movieModel;
        if (cursor.getCount()>0){
            do{
                movieModel = new Movie();
                movieModel.setId(cursor.getString(cursor.getColumnIndexOrThrow(_ID)));
                movieModel.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                movieModel.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                movieModel.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(POSTER_PATH)));
                movieModel.setRating(cursor.getString(cursor.getColumnIndexOrThrow(RATING)));
                movieModel.setPopularity(cursor.getString(cursor.getColumnIndexOrThrow(POPULARITY)));
                movieModel.setLanguage(cursor.getString(cursor.getColumnIndexOrThrow(LANGUAGE)));
                movieModel.setGenres(cursor.getString(cursor.getColumnIndexOrThrow(GENRES)));
                movieModel.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));
                listMovie.add(movieModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return listMovie;
    }

    public long insert(String table, Movie movie){
        ContentValues contentValues = new ContentValues();
        contentValues.put(_ID, movie.getId());
        contentValues.put(TITLE, movie.getTitle());
        contentValues.put(OVERVIEW, movie.getOverview());
        contentValues.put(POSTER_PATH, movie.getPosterPath());
        contentValues.put(RATING, movie.getRating());
        contentValues.put(POPULARITY, movie.getPopularity());
        contentValues.put(LANGUAGE, movie.getLanguage());
        contentValues.put(GENRES, movie.getGenres());
        contentValues.put(RELEASE_DATE, movie.getReleaseDate());
        return database.insert(table, null, contentValues);
    }

    public int update(String table, Movie movie){
        ContentValues contentValues = new ContentValues();
        contentValues.put(_ID, movie.getId());
        contentValues.put(TITLE, movie.getTitle());
        contentValues.put(OVERVIEW, movie.getOverview());
        contentValues.put(POSTER_PATH, movie.getPosterPath());
        contentValues.put(RATING, movie.getRating());
        contentValues.put(POPULARITY, movie.getPopularity());
        contentValues.put(LANGUAGE, movie.getLanguage());
        contentValues.put(GENRES, movie.getGenres());
        contentValues.put(RELEASE_DATE, movie.getReleaseDate());
        return database.update(table, contentValues, _ID +"= '"+movie.getId()+"'", null);
    }

    public int delete(String table, String id){
        return database.delete(table, _ID+"= '"+id+"'", null);
    }

}
