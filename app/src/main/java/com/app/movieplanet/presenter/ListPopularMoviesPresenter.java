package com.app.movieplanet.presenter;

import com.app.movieplanet.model.api.MovieApi;
import com.app.movieplanet.model.api.asynctask.ApiResultListener;
import com.app.movieplanet.model.entity.Movie;
import com.app.movieplanet.view.ListPopularMoviesView;

import java.util.List;

public class ListPopularMoviesPresenter {

    private ListPopularMoviesView view;
    private MovieApi movieApi;

    public ListPopularMoviesPresenter(ListPopularMoviesView view, MovieApi movieApi) {
        this.view = view;
        this.movieApi = movieApi;
    }

    public void loadMovies(Integer page) {
        view.showLoadingMovies();
        movieApi.setApiResultListener(new ApiResultListener() {
            @Override
            public void onResult(Object object) {
                List<Movie> movieList = (List<Movie>) object;
                if (movieList == null || movieList.size() == 0) {
                    view.warnAnyMovieFounded();
                } else {
                    view.showMovies(movieList);
                }
                view.hideLoadingMovies();
            }

            @Override
            public void onException(Exception exception) {
                view.warnFailedToLoadMovies();
                view.hideLoadingMovies();
            }
        });

        movieApi.listPopularMovies(page);
    }


    public void stop() {
        movieApi.cancelAllServices();
    }
}
