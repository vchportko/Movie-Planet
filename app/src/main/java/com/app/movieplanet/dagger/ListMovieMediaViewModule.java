package com.app.movieplanet.dagger;

import com.app.movieplanet.model.api.ImageApi;
import com.app.movieplanet.model.api.VideoApi;
import com.app.movieplanet.presenter.ListMovieMediaPresenter;
import com.app.movieplanet.view.ListMovieMediaView;
import com.app.movieplanet.view.fragment.ListMovieMediaFragment;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = {AppModule.class, ApiModule.class}, injects = ListMovieMediaFragment.class)
public class ListMovieMediaViewModule {

    private ListMovieMediaView view;

    public ListMovieMediaViewModule(ListMovieMediaView view) {
        this.view = view;
    }

    @Provides
    public ListMovieMediaPresenter provideListVideoPresenter(VideoApi videoApi, ImageApi imageApi) {
        return new ListMovieMediaPresenter(view, videoApi, imageApi);
    }
}
