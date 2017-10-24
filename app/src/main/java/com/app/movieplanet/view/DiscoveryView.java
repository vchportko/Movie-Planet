package com.app.movieplanet.view;

import com.app.movieplanet.model.entity.Movie;

import java.util.List;

public interface DiscoveryView {
    void showMovie(Movie movie, int index);

    void movieLoaded(List<Movie> movieList, int page);

    void warnWasNotPossibleToLoadMoreMovies();

    void warnFailedToLoadMoreMovies();

    void showLoading();

    void hideLoading();

    void checkInterest();

    void uncheckInterest();

    void removedFromInteresting();

    void warmAddedAsInteresting();
}
