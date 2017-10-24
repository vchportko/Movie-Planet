package com.app.movieplanet.dagger;

import com.app.movieplanet.model.api.CastApi;
import com.app.movieplanet.model.api.CrewApi;
import com.app.movieplanet.presenter.CastCrewPresenter;
import com.app.movieplanet.view.CastCrewView;
import com.app.movieplanet.view.fragment.CastCrewFragment;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = {AppModule.class, ApiModule.class}, injects = CastCrewFragment.class)
public class CastCrewViewModule {

    private CastCrewView view;

    public CastCrewViewModule(CastCrewView view) {
        this.view = view;
    }

    @Provides
    public CastCrewPresenter provideCastCrewPresenter(CastApi castApi, CrewApi crewApi) {
        return new CastCrewPresenter(view, castApi, crewApi);
    }

}
