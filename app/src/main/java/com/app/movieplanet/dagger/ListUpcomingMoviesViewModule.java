package com.app.movieplanet.dagger;

import com.app.movieplanet.model.api.MovieApi;
import com.app.movieplanet.presenter.ListUpcomingMoviesPresenter;
import com.app.movieplanet.view.ListUpcomingMoviesView;
import com.app.movieplanet.view.activity.ListUpcomingMoviesActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = ApiModule.class, injects = ListUpcomingMoviesActivity.class)
public class ListUpcomingMoviesViewModule {

    ListUpcomingMoviesView view;

    public ListUpcomingMoviesViewModule(ListUpcomingMoviesView view) {
        this.view = view;
    }

    @Provides
    public ListUpcomingMoviesPresenter provideListUpcomingMoviesPresenter(MovieApi movieApi) {
        return new ListUpcomingMoviesPresenter(view, movieApi);
    }
}
