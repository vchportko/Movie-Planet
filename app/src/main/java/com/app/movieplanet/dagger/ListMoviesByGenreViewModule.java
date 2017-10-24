package com.app.movieplanet.dagger;

import com.app.movieplanet.model.api.MovieApi;
import com.app.movieplanet.presenter.ListMoviesByGenrePresenter;
import com.app.movieplanet.view.ListMoviesByGenreView;
import com.app.movieplanet.view.activity.ListMoviesByGenreActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = ApiModule.class, injects = ListMoviesByGenreActivity.class)
public class ListMoviesByGenreViewModule {

    ListMoviesByGenreView view;

    public ListMoviesByGenreViewModule(ListMoviesByGenreView view) {
        this.view = view;
    }

    @Provides
    public ListMoviesByGenrePresenter provideListMoviesByGenrePresenter(MovieApi movieApi) {
        return new ListMoviesByGenrePresenter(view, movieApi);
    }
}
