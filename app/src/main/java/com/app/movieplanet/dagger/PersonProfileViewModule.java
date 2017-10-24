package com.app.movieplanet.dagger;

import com.app.movieplanet.presenter.PersonProfilePresenter;
import com.app.movieplanet.view.PersonProfileView;
import com.app.movieplanet.view.activity.PersonProfileActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, injects = {PersonProfileActivity.class}, includes = {AppModule.class, ApiModule.class})
public class PersonProfileViewModule {

    private PersonProfileView view;

    public PersonProfileViewModule(PersonProfileView view) {
        this.view = view;
    }

    @Provides
    public PersonProfilePresenter providePersonProfilePresenter() {
        return new PersonProfilePresenter(view);
    }
}
