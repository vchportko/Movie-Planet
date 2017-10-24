package com.app.movieplanet.dagger;

import com.app.movieplanet.model.api.ImageApi;
import com.app.movieplanet.presenter.ListPersonMediaPresenter;
import com.app.movieplanet.view.ListPersonMediaView;
import com.app.movieplanet.view.fragment.ListPersonMediaFragment;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = {AppModule.class, ApiModule.class}, injects = ListPersonMediaFragment.class)
public class ListPersonMediaViewModule {

    private ListPersonMediaView view;

    public ListPersonMediaViewModule(ListPersonMediaView view) {
        this.view = view;
    }

    @Provides
    public ListPersonMediaPresenter provideListPersonMediaPresenter(ImageApi imageApi) {
        return new ListPersonMediaPresenter(view, imageApi);
    }
}
