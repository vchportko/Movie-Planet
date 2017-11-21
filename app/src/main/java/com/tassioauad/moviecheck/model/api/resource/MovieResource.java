package com.tassioauad.moviecheck.model.api.resource;

import com.tassioauad.moviecheck.model.entity.Movie;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface MovieResource {

    @GET("movie/upcoming")
    Call<List<Movie>> listUpComing(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("discover/movie")
    Call<List<Movie>> discover(@Query("api_key") String apiKey, @Query("page") Integer page);


    @GET("movie/popular")
    Call<List<Movie>> listPopular(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("movie/top_rated")
    Call<List<Movie>> listTopRated(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("movie/now_playing")
    Call<List<Movie>> listNowPlaying(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("genre/{id}/movies")
    Call<List<Movie>> listByGenre(@Path("id") Long genreId, @Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("search/movie")
    Call<List<Movie>> listByName(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") int page);

}
