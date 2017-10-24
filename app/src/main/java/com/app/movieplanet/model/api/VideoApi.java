package com.app.movieplanet.model.api;

import com.app.movieplanet.model.entity.Movie;

public interface VideoApi extends AsyncService {
    void listAllByMovie(Movie movie);
}
