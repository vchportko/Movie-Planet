package com.app.movieplanet.dagger;

import com.app.movieplanet.model.api.MovieApi;
import com.app.movieplanet.presenter.ListTopRatedMoviesPresenter;
import com.app.movieplanet.view.ListTopRatedMoviesView;
import com.app.movieplanet.view.activity.ListTopRatedMoviesActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = ApiModule.class, injects = ListTopRatedMoviesActivity.class)
public class ListTopRatedMoviesViewModule {

    ListTopRatedMoviesView view;

    public ListTopRatedMoviesViewModule(ListTopRatedMoviesView view) {
        this.view = view;
    }

    @Provides
    public ListTopRatedMoviesPresenter provideListTopRatedMoviesPresenter(MovieApi movieApi) {
        return new ListTopRatedMoviesPresenter(view, movieApi);
    }
}
