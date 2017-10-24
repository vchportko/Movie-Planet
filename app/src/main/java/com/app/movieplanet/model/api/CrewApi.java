package com.app.movieplanet.model.api;

import com.app.movieplanet.model.entity.Movie;
import com.app.movieplanet.model.entity.Person;

public interface CrewApi extends AsyncService{
    void listAllByMovie(Movie movie);

    void listMoviesByCrew(Person person);
}
