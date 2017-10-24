package com.app.movieplanet.dagger;

import com.app.movieplanet.model.api.MovieApi;
import com.app.movieplanet.model.api.PersonApi;
import com.app.movieplanet.presenter.SearchPresenter;
import com.app.movieplanet.view.SearchView;
import com.app.movieplanet.view.activity.SearchActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = {AppModule.class, ApiModule.class}, injects = SearchActivity.class)
public class SearchViewModule {

    private SearchView view;

    public SearchViewModule(SearchView view) {
        this.view = view;
    }

    @Provides
    public SearchPresenter provideSearchPresenter(MovieApi movieApi, PersonApi personApi) {
        return new SearchPresenter(view, movieApi, personApi);
    }

}
