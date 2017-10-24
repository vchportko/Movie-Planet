package com.app.movieplanet.model.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.app.movieplanet.model.dao.Dao;
import com.app.movieplanet.model.dao.MovieDao;
import com.app.movieplanet.model.dao.MovieInterestDao;
import com.app.movieplanet.model.dao.UserDao;
import com.app.movieplanet.model.entity.Movie;
import com.app.movieplanet.model.entity.MovieInterest;
import com.app.movieplanet.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class MovieInterestDaoImpl extends Dao implements MovieInterestDao, LoaderManager.LoaderCallbacks<Cursor> {

    private static final int MOVIEINTEREST_LISTALL_CODE = 4820;
    private static final String BUNDLE_ARG_USER = "bundlearguser";


    public static final String TABLE_NAME = "movie_interest";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_USER_ID = "user_id";
    public static final String COLUMN_NAME_MOVIE_ID = "movie_id";
    public static final String[] COLUMNS = new String[]{COLUMN_NAME_ID, COLUMN_NAME_USER_ID, COLUMN_NAME_MOVIE_ID};

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (\n" +
            COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
            COLUMN_NAME_USER_ID + " INTEGER, \n" +
            COLUMN_NAME_MOVIE_ID + " INTEGER, \n" +
            "FOREIGN KEY(" + COLUMN_NAME_USER_ID + ") REFERENCES "+UserDaoImpl.TABLE_NAME+"("+UserDaoImpl.COLUMN_NAME_ID+"), \n" +
            "FOREIGN KEY(" + COLUMN_NAME_MOVIE_ID + ") REFERENCES "+MovieDaoImpl.TABLE_NAME+"("+MovieDaoImpl.COLUMN_NAME_ID+") \n" +
            ")";

    private MovieDao movieDao;
    private UserDao userDao;

    public MovieInterestDaoImpl(Context context, SQLiteDatabase database, MovieDao movieDao, UserDao userDao) {
        super(context, database);
        this.movieDao = movieDao;
        this.userDao = userDao;
    }

    @Override
    public void listAll(User user) {
        checkDaoListener();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_ARG_USER, user);
        getActivity().getSupportLoaderManager().initLoader(MOVIEINTEREST_LISTALL_CODE, bundle, this);
    }

    @Override
    public List<MovieInterest> listAllUpcoming(User user) {
        List<MovieInterest> movieInterestList = new ArrayList<>();

        for(Movie movie : movieDao.listAllUpcoming()) {
            MovieInterest movieInterest = findByMovie(movie, user);
            if(movieInterest != null) {
                movieInterestList.add(movieInterest);
            }
        }

        return movieInterestList;
    }

    @Override
    public MovieInterest findByMovie(Movie movie, User user) {
        Cursor cursor = getDatabase().query(TABLE_NAME, COLUMNS, COLUMN_NAME_MOVIE_ID + " = ? AND " + COLUMN_NAME_USER_ID + " = ?",
                new String[]{String.valueOf(movie.getId()), String.valueOf(user.getId())}, null, null, null);

        List<MovieInterest> movieInterestList = fromCursor(cursor);
        if (movieInterestList.size() == 0) {
            return null;
        }

        return movieInterestList.get(0);
    }

    @Override
    public void remove(MovieInterest movieInterest) {
        getDatabase().delete(TABLE_NAME, COLUMN_NAME_ID + " = ?", new String[]{String.valueOf(movieInterest.getId())});
    }

    @Override
    public void remove(Movie movie, User user) {
        getDatabase().delete(TABLE_NAME,  COLUMN_NAME_MOVIE_ID + " = ? AND " + COLUMN_NAME_USER_ID + " = ?",
                new String[]{String.valueOf(movie.getId()), String.valueOf(user.getId())});
    }

    @Override
    public void insert(MovieInterest movieInterest) {
        long id = getDatabase().insert(TABLE_NAME, null, toContentValues(movieInterest));
        movieInterest.setId(id);
    }

    public ContentValues toContentValues(MovieInterest movieInterest) {
        ContentValues contentValues = new ContentValues();
        if (movieInterest.getId() != null) {
            contentValues.put(COLUMN_NAME_ID, movieInterest.getId());
        }
        movieDao.save(movieInterest.getMovie());
        contentValues.put(COLUMN_NAME_MOVIE_ID, movieInterest.getMovie().getId());
        contentValues.put(COLUMN_NAME_USER_ID, movieInterest.getUser().getId());
        return contentValues;
    }

    public List<MovieInterest> fromCursor(Cursor cursor) {
        List<MovieInterest> movieInterestList = new ArrayList<>();

        while (cursor.moveToNext()) {
            MovieInterest movieInterest = new MovieInterest();
            movieInterest.setId(cursor.getLong(0));
            movieInterest.setUser(userDao.findById(cursor.getInt(1)));
            movieInterest.setMovie(movieDao.findById(cursor.getLong(2)));

            movieInterestList.add(movieInterest);
        }

        return movieInterestList;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case MOVIEINTEREST_LISTALL_CODE:
                User user = args.getParcelable(BUNDLE_ARG_USER);
                return new CursorLoader(getActivity(), MovieCheckContentProvider.MOVIEINTEREST_URI,
                        COLUMNS, COLUMN_NAME_USER_ID + " = ?", new String[]{String.valueOf(String.valueOf(user.getId()))}, null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        getDaoListener().onLoad(fromCursor(cursor));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

}
