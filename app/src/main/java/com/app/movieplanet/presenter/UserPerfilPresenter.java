package com.app.movieplanet.presenter;

import com.app.movieplanet.model.dao.UserDao;
import com.app.movieplanet.view.UserProfileView;

public class UserPerfilPresenter {

    private UserProfileView view;
    private UserDao userDao;

    public UserPerfilPresenter(UserProfileView view, UserDao userDao) {
        this.view = view;
        this.userDao = userDao;
    }

    public void init() {
        view.showUserName(userDao.getLoggedUser().getName());
    }
}
