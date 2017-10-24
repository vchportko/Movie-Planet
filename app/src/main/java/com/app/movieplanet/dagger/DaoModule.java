package com.app.movieplanet.dagger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.app.movieplanet.model.SqliteConnection;
import com.app.movieplanet.model.dao.MovieDao;
import com.app.movieplanet.model.dao.MovieInterestDao;
import com.app.movieplanet.model.dao.MovieNotInterestDao;
import com.app.movieplanet.model.dao.MovieWatchedDao;
import com.app.movieplanet.model.dao.UserDao;
import com.app.movieplanet.model.dao.impl.MovieDaoImpl;
import com.app.movieplanet.model.dao.impl.MovieInterestDaoImpl;
import com.app.movieplanet.model.dao.impl.MovieNotInterestDaoImpl;
import com.app.movieplanet.model.dao.impl.MovieWatchedDaoImpl;
import com.app.movieplanet.model.dao.impl.UserDaoImpl;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = AppModule.class)
public class DaoModule {

    @Provides
    public SQLiteDatabase provideSqLiteDatabase(Context context) {
        return new SqliteConnection(context).getWritableDatabase();
    }

    @Provides
    public UserDao provideUserDao(Context context) {
        return new UserDaoImpl(context, provideSqLiteDatabase(context));
    }

    @Provides
    public MovieDao provideMovieDao(Context context) {
        return new MovieDaoImpl(context, provideSqLiteDatabase(context));
    }

    @Provides
    public MovieInterestDao provideMovieInterestDao(Context context) {
        return new MovieInterestDaoImpl(context, provideSqLiteDatabase(context),
                provideMovieDao(context), provideUserDao(context));
    }

    @Provides
    public MovieNotInterestDao provideMovieNotInterestDao(Context context) {
        return new MovieNotInterestDaoImpl(context, provideSqLiteDatabase(context),
                provideMovieDao(context), provideUserDao(context));
    }

    @Provides
    public MovieWatchedDao provideMovieWatchedDao(Context context) {
        return new MovieWatchedDaoImpl(context, provideSqLiteDatabase(context),
                provideMovieDao(context), provideUserDao(context));
    }
}
