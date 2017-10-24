package com.app.movieplanet.dagger;

import com.app.movieplanet.model.api.MovieApi;
import com.app.movieplanet.model.dao.UserDao;
import com.app.movieplanet.presenter.HomePresenter;
import com.app.movieplanet.view.HomeView;
import com.app.movieplanet.view.activity.HomeActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = {ApiModule.class, DaoModule.class}, injects = HomeActivity.class)
public class HomeViewModule {

    HomeView view;

    public HomeViewModule(HomeView view) {
        this.view = view;
    }

    @Provides
    public HomePresenter provideHomePresenter(MovieApi movieApi, UserDao userDao) {
        return new HomePresenter(view, movieApi, userDao);
    }

}
