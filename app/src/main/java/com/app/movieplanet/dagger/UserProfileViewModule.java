package com.app.movieplanet.dagger;

import com.app.movieplanet.model.dao.UserDao;
import com.app.movieplanet.presenter.UserPerfilPresenter;
import com.app.movieplanet.view.UserProfileView;
import com.app.movieplanet.view.activity.UserProfileActivity;

import dagger.Module;
import dagger.Provides;

@Module(library = true, injects = {UserProfileActivity.class}, includes = {AppModule.class, ApiModule.class, DaoModule.class})
public class UserProfileViewModule {

    private UserProfileView view;

    public UserProfileViewModule(UserProfileView view) {
        this.view = view;
    }

    @Provides
    public UserPerfilPresenter provideUserPresenter(UserDao userDao) {
        return new UserPerfilPresenter(view, userDao);
    }
}
