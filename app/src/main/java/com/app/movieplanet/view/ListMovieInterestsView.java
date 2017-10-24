package com.app.movieplanet.view;

import com.app.movieplanet.model.entity.Movie;
import com.app.movieplanet.model.entity.MovieInterest;

import java.util.List;

public interface ListMovieInterestsView {
    void showMovieInterests(List<MovieInterest> movieInterestList);

    void warnAnyInterestFounded();

    void warnMovieRemoved(Movie movie);
}
