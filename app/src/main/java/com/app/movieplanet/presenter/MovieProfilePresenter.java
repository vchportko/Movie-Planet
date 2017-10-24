package com.app.movieplanet.presenter;

import com.app.movieplanet.model.entity.Movie;
import com.app.movieplanet.view.MovieProfileView;

public class MovieProfilePresenter {

    private MovieProfileView view;

    public MovieProfilePresenter(MovieProfileView view) {
        this.view = view;
    }

    public void init(Movie movie) {
        view.showMovieName(movie.getTitle());
    }
}
