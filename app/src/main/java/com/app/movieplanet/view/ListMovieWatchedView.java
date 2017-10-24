package com.app.movieplanet.view;

import com.app.movieplanet.model.entity.Movie;
import com.app.movieplanet.model.entity.MovieWatched;

import java.util.List;

public interface ListMovieWatchedView {
    void showWatchedMovies(List<MovieWatched> movieWatchedList);

    void warnAnyWatchedMovieFounded();

    void warnMovieRemoved(Movie movie);
}
