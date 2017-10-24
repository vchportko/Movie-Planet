package com.app.movieplanet.view;

import com.app.movieplanet.model.entity.Movie;
import com.app.movieplanet.model.entity.Person;

import java.util.List;

public interface SearchView {
    void showLoadingMovies();

    void warnAnyMovieFounded();

    void showMovies(List<Movie> movieList);

    void hideLoadingMovies();

    void warnFailedToLoadMovies();

    void showLoadingPerson();

    void warnAnyPersonFounded();

    void showPerson(List<Person> personList);

    void hideLoadingPerson();

    void warnFailedToLoadPerson();
}
