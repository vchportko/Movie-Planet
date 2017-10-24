package com.app.movieplanet.dagger;

import com.app.movieplanet.model.api.MovieApi;
import com.app.movieplanet.presenter.SearchMoviePresenter;
import com.app.movieplanet.view.SearchMovieView;
import com.app.movieplanet.view.activity.SearchMovieActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = ApiModule.class, injects = SearchMovieActivity.class)
public class SearchMovieViewModule {

    SearchMovieView view;

    public SearchMovieViewModule(SearchMovieView view) {
        this.view = view;
    }

    @Provides
    public SearchMoviePresenter provideSearchMoviePresenter(MovieApi movieApi) {
        return new SearchMoviePresenter(view, movieApi);
    }
}
