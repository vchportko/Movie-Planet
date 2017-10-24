package com.app.movieplanet.dagger;

import android.support.v4.app.FragmentActivity;

import com.app.movieplanet.model.dao.MovieInterestDao;
import com.app.movieplanet.model.dao.UserDao;
import com.app.movieplanet.presenter.ListMovieInterestsPresenter;
import com.app.movieplanet.view.ListMovieInterestsView;
import com.app.movieplanet.view.fragment.ListMovieInterestsFragment;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = {AppModule.class, ApiModule.class, DaoModule.class}, injects = ListMovieInterestsFragment.class)
public class ListMovieInterestViewModule {

    private ListMovieInterestsView view;
    private FragmentActivity activity;

    public ListMovieInterestViewModule(ListMovieInterestsView view, FragmentActivity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Provides
    public ListMovieInterestsPresenter provideListMovieInterestsPresenter(MovieInterestDao movieInterestDao, UserDao userDao) {
        movieInterestDao.setActivity(activity);
        return new ListMovieInterestsPresenter(view, movieInterestDao, userDao);
    }
}
