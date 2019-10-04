package com.riodev.favoritemovie;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.riodev.favoritemovie.adapter.ListMovieAdapter;
import com.riodev.favoritemovie.model.Movie;
import com.riodev.favoritemovie.model.MovieHelper;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.riodev.favoritemovie.MappingHelper.mapCursorToArrayList;
import static com.riodev.favoritemovie.database.DatabaseContract.MovieColumn.CONTENT_URI;
import static com.riodev.favoritemovie.database.DatabaseContract.TABLE_NAME;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickListener {

    private RecyclerView rvListMovie;
    private ProgressBar progressBarMovie;
    private ArrayList<Movie> listMovie;
    private MovieHelper movieHelper;
    private ListMovieAdapter listMovieAdapter;
    private final int INTENT_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvListMovie = findViewById(R.id.rv_ListMovie);
        progressBarMovie = findViewById(R.id.pb_movie);

        movieHelper = new MovieHelper(this);
        listMovie = new ArrayList<>();

        rvListMovie.setLayoutManager(new LinearLayoutManager(this));
        listMovieAdapter = new ListMovieAdapter(this);
        rvListMovie.setAdapter(listMovieAdapter);

        listMovieAdapter.setListMovie(listMovie);
        listMovieAdapter.setRecyclerViewClickListener(this);

        if (savedInstanceState == null) {
            loadData();
        } else {
            listMovie = savedInstanceState.getParcelableArrayList("list");
            if (listMovie!=null){
                listMovieAdapter.setListMovie(listMovie);
            }
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("list", listMovieAdapter.getListMovie());
    }

    @Override
    public void onItemClicked(int position) {

    }

    private void loadData(){
        new MovieAsyncTask().execute();
    }

    private class MovieAsyncTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            rvListMovie.setVisibility(View.GONE);
            progressBarMovie.setVisibility(View.VISIBLE);
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return getContentResolver().query(CONTENT_URI, null, null, null, null);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            rvListMovie.setVisibility(View.VISIBLE);
            progressBarMovie.setVisibility(View.GONE);

            listMovie = mapCursorToArrayList(cursor);
            listMovieAdapter.setListMovie(listMovie);
        }
    }
}
