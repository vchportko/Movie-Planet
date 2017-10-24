package com.app.movieplanet.model.api;

import com.app.movieplanet.model.entity.Movie;

public interface ReviewApi extends AsyncService {
    void listByMovies(Movie movie, int page);
}
