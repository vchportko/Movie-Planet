package com.app.movieplanet.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.app.movieplanet.model.dao.impl.MovieDaoImpl;
import com.app.movieplanet.model.dao.impl.MovieInterestDaoImpl;
import com.app.movieplanet.model.dao.impl.MovieNotInterestDaoImpl;
import com.app.movieplanet.model.dao.impl.MovieWatchedDaoImpl;
import com.app.movieplanet.model.dao.impl.UserDaoImpl;

public class SqliteConnection extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "moviecheck";
    private static final int DATABASE_VERSION = 1;

    public SqliteConnection(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserDaoImpl.CREATE_TABLE);
        db.execSQL(MovieInterestDaoImpl.CREATE_TABLE);
        db.execSQL(MovieDaoImpl.CREATE_TABLE);
        db.execSQL(MovieWatchedDaoImpl.CREATE_TABLE);
        db.execSQL(MovieNotInterestDaoImpl.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
