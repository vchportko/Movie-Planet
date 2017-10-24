package com.app.movieplanet.dagger;

import com.app.movieplanet.model.api.MovieApi;
import com.app.movieplanet.presenter.ListPopularMoviesPresenter;
import com.app.movieplanet.view.ListPopularMoviesView;
import com.app.movieplanet.view.activity.ListPopularMoviesActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = ApiModule.class, injects = ListPopularMoviesActivity.class)
public class ListPopularMoviesViewModule {

    ListPopularMoviesView view;

    public ListPopularMoviesViewModule(ListPopularMoviesView view) {
        this.view = view;
    }

    @Provides
    public ListPopularMoviesPresenter provideListPopularMoviesPresenter(MovieApi movieApi) {
        return new ListPopularMoviesPresenter(view, movieApi);
    }
}
