package com.app.movieplanet.view;

import com.app.movieplanet.model.entity.Genre;
import com.app.movieplanet.model.entity.MovieInterest;
import com.app.movieplanet.model.entity.User;

import java.util.List;

public interface UserDetailView {

    void showFullname(String fulname);

    void showEmail(String email);

    void showPhoto(String posterUrl);

    void showUser(User user);

    void showUpcomingInterests(List<MovieInterest> movieInterests);

    void showLoadingGenres();

    void warnAnyGenreFounded();

    void showGenres(List<Genre> genreList);

    void hideLoadingGenres();

    void warnFailedOnLoadGenres();

    void warnAnyInterestFound();
}
