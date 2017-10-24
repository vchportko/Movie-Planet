package com.app.movieplanet.dagger;

import com.app.movieplanet.model.api.GenreApi;
import com.app.movieplanet.model.dao.MovieInterestDao;
import com.app.movieplanet.model.dao.MovieWatchedDao;
import com.app.movieplanet.model.dao.UserDao;
import com.app.movieplanet.presenter.UserDetailPresenter;
import com.app.movieplanet.view.UserDetailView;
import com.app.movieplanet.view.fragment.UserDetailFragment;

import dagger.Module;
import dagger.Provides;

@Module(library = true, injects = UserDetailFragment.class, includes = {AppModule.class, ApiModule.class, DaoModule.class})
public class UserDetailViewModule {

    private UserDetailView view;

    public UserDetailViewModule(UserDetailView view) {
        this.view = view;
    }

    @Provides
    public UserDetailPresenter provideUserDetailPresenter(UserDao userDao, MovieInterestDao movieInterestDao, MovieWatchedDao movieWatchedDao, GenreApi genreApi) {
        return new UserDetailPresenter(view, userDao, movieInterestDao, movieWatchedDao, genreApi);
    }
}
