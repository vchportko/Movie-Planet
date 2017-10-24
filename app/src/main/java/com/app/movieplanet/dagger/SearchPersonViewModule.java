package com.app.movieplanet.dagger;

import com.app.movieplanet.model.api.PersonApi;
import com.app.movieplanet.presenter.SearchPersonPresenter;
import com.app.movieplanet.view.SearchPersonView;
import com.app.movieplanet.view.activity.SearchPersonActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = ApiModule.class, injects = SearchPersonActivity.class)
public class SearchPersonViewModule {

    SearchPersonView view;

    public SearchPersonViewModule(SearchPersonView view) {
        this.view = view;
    }

    @Provides
    public SearchPersonPresenter provideSearchPersonPresenter(PersonApi movieApi) {
        return new SearchPersonPresenter(view, movieApi);
    }
}
