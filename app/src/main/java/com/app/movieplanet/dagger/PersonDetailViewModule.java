package com.app.movieplanet.dagger;

import com.app.movieplanet.model.api.PersonApi;
import com.app.movieplanet.presenter.PersonDetailPresenter;
import com.app.movieplanet.view.PersonDetailView;
import com.app.movieplanet.view.fragment.PersonDetailFragment;

import dagger.Module;
import dagger.Provides;

@Module(library = true, injects = PersonDetailFragment.class, includes = {AppModule.class, ApiModule.class})
public class PersonDetailViewModule {

    private PersonDetailView view;

    public PersonDetailViewModule(PersonDetailView view) {
        this.view = view;
    }

    @Provides
    public PersonDetailPresenter providePersonDetailPresenter(PersonApi personApi) {
        return new PersonDetailPresenter(view, personApi);
    }
}
