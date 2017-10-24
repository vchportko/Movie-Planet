package com.app.movieplanet.dagger;

import com.app.movieplanet.model.api.CastApi;
import com.app.movieplanet.model.api.CrewApi;
import com.app.movieplanet.presenter.PersonWorkPresenter;
import com.app.movieplanet.view.PersonWorkView;
import com.app.movieplanet.view.fragment.PersonWorkFragment;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = {AppModule.class, ApiModule.class}, injects = PersonWorkFragment.class)
public class PersonWorkViewModule {

    private PersonWorkView view;

    public PersonWorkViewModule(PersonWorkView view) {
        this.view = view;
    }

    @Provides
    public PersonWorkPresenter providePersonWorkPresenter(CastApi castApi, CrewApi crewApi) {
        return new PersonWorkPresenter(view, castApi, crewApi);
    }

}
