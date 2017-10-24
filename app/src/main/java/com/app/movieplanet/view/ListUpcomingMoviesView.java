package com.app.movieplanet.view;

import com.app.movieplanet.model.entity.Movie;

import java.util.List;

public interface ListUpcomingMoviesView {
    void showLoadingMovies();

    void warnAnyMovieFounded();

    void showMovies(List<Movie> movieList);

    void hideLoadingMovies();

    void warnFailedToLoadMovies();
}
