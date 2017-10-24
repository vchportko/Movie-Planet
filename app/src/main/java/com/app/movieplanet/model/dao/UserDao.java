package com.app.movieplanet.model.dao;

import com.app.movieplanet.model.entity.User;

public interface UserDao {
    void save(User user);

    void update(User user);

    void insert(User user);

    void login(User user);

    User getLoggedUser();

    void logout();

    User findById(Integer id);

    User findByGoogleId(String googleId);

    boolean hasReadTutorial();

    void informHasReadTutorial();
}
