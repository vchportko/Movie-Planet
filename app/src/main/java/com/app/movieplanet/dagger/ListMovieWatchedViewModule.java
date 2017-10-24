package com.app.movieplanet.dagger;

import android.support.v4.app.FragmentActivity;

import com.app.movieplanet.model.dao.MovieWatchedDao;
import com.app.movieplanet.model.dao.UserDao;
import com.app.movieplanet.presenter.ListMovieWatchedPresenter;
import com.app.movieplanet.view.ListMovieWatchedView;
import com.app.movieplanet.view.fragment.ListMovieWatchedFragment;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = {AppModule.class, ApiModule.class, DaoModule.class}, injects = ListMovieWatchedFragment.class)
public class ListMovieWatchedViewModule {

    private ListMovieWatchedView view;
    private FragmentActivity activity;

    public ListMovieWatchedViewModule(ListMovieWatchedView view, FragmentActivity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Provides
    public ListMovieWatchedPresenter provideListMovieWatchedPresenter(MovieWatchedDao movieWatchedDao, UserDao userDao) {
        movieWatchedDao.setActivity(activity);
        return new ListMovieWatchedPresenter(view, movieWatchedDao, userDao);
    }
}
