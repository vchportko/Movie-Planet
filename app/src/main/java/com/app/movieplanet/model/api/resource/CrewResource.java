package com.app.movieplanet.model.api.resource;


import com.app.movieplanet.model.entity.Crew;
import com.app.movieplanet.model.entity.Movie;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface CrewResource {

    @GET("movie/{id}/credits")
    Call<List<Crew>> listAllByMovie(@Path("id") Long id, @Query("api_key") String apiKey);

    @GET("person/{id}/movie_credits")
    Call<List<Movie>> listMovieByCrew(@Path("id") Long personId, @Query("api_key") String apiKey);
}
