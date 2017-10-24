package com.app.movieplanet.model.dao;

import com.app.movieplanet.model.entity.Movie;

import java.util.List;

public interface MovieDao {
    void save(Movie movie);

    void update(Movie movie);

    void insert(Movie movie);

    Movie findById(Long id);

    List<Movie> listAll();

    List<Movie> listAllUpcoming();
}
