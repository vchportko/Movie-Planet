package com.app.movieplanet.dagger;

import com.app.movieplanet.model.api.MovieApi;
import com.app.movieplanet.presenter.ListNowPlayingMoviesPresenter;
import com.app.movieplanet.view.ListNowPlayingMoviesView;
import com.app.movieplanet.view.activity.ListNowPlayingMoviesActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = ApiModule.class, injects = ListNowPlayingMoviesActivity.class)
public class ListNowPlayingMoviesViewModule {

    ListNowPlayingMoviesView view;

    public ListNowPlayingMoviesViewModule(ListNowPlayingMoviesView view) {
        this.view = view;
    }

    @Provides
    public ListNowPlayingMoviesPresenter provideListNowPlayingMoviesPresenter(MovieApi movieApi) {
        return new ListNowPlayingMoviesPresenter(view, movieApi);
    }
}
