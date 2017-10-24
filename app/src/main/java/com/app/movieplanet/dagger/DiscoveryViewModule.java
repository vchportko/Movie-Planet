package com.app.movieplanet.dagger;

import com.app.movieplanet.model.api.MovieApi;
import com.app.movieplanet.model.dao.MovieInterestDao;
import com.app.movieplanet.model.dao.MovieNotInterestDao;
import com.app.movieplanet.model.dao.MovieWatchedDao;
import com.app.movieplanet.model.dao.UserDao;
import com.app.movieplanet.presenter.DiscoveryPresenter;
import com.app.movieplanet.view.DiscoveryView;
import com.app.movieplanet.view.activity.DiscoveryActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = {ApiModule.class, DaoModule.class}, injects = DiscoveryActivity.class)
public class DiscoveryViewModule {

    private DiscoveryView view;

    public DiscoveryViewModule(DiscoveryView view) {
        this.view = view;
    }

    @Provides
    public DiscoveryPresenter provideDiscoveryPresenter(MovieApi movieApi, MovieWatchedDao movieWatchedDao,
                                                        MovieInterestDao movieInterestDao, UserDao userDao,
                                                        MovieNotInterestDao movieNotInterestDao) {
        return new DiscoveryPresenter(view, movieApi, movieWatchedDao, movieInterestDao, movieNotInterestDao, userDao);
    }
}
