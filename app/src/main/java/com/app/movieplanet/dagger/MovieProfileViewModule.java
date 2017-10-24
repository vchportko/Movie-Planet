package com.app.movieplanet.dagger;

import com.app.movieplanet.presenter.MovieProfilePresenter;
import com.app.movieplanet.view.MovieProfileView;
import com.app.movieplanet.view.activity.MovieProfileActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, injects = {MovieProfileActivity.class}, includes = {AppModule.class, ApiModule.class})
public class MovieProfileViewModule {

    private MovieProfileView view;

    public MovieProfileViewModule(MovieProfileView view) {
        this.view = view;
    }

    @Provides
    public MovieProfilePresenter provideProfilePresenter() {
        return new MovieProfilePresenter(view);
    }
}
