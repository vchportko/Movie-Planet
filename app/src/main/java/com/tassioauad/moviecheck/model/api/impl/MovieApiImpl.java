package com.tassioauad.moviecheck.model.api.impl;

import android.content.Context;
import android.os.AsyncTask;

import com.tassioauad.moviecheck.model.api.GenericApi;
import com.tassioauad.moviecheck.model.api.MovieApi;
import com.tassioauad.moviecheck.model.api.asynctask.DiscoverMoviesAsyncTask;
import com.tassioauad.moviecheck.model.api.asynctask.ListMovieByGenreAsyncTask;
import com.tassioauad.moviecheck.model.api.asynctask.ListMoviesByNameAsyncTask;
import com.tassioauad.moviecheck.model.api.asynctask.ListNowPlayingMoviesAsyncTask;
import com.tassioauad.moviecheck.model.api.asynctask.ListPopularMoviesAsyncTask;
import com.tassioauad.moviecheck.model.api.asynctask.ListTopRatedMoviesAsyncTask;
import com.tassioauad.moviecheck.model.api.asynctask.ListUpComingMovieAsyncTask;
import com.tassioauad.moviecheck.model.api.resource.MovieResource;
import com.tassioauad.moviecheck.model.entity.Genre;

public class MovieApiImpl extends GenericApi implements MovieApi {

    private ListUpComingMovieAsyncTask listUpComingMovieAsyncTask;
    private ListPopularMoviesAsyncTask listPopularMoviesAsyncTask;
    private ListTopRatedMoviesAsyncTask listTopRatedMoviesAsyncTask;
    private ListNowPlayingMoviesAsyncTask listNowPlayingMoviesAsyncTask;
    private ListMovieByGenreAsyncTask listMovieByGenreAsyncTask;
    private ListMoviesByNameAsyncTask listMoviesByNameAsyncTask;
    private DiscoverMoviesAsyncTask discoverMoviesAsyncTask;
    private MovieResource movieResource;

    public MovieApiImpl(Context context, MovieResource movieResource) {
        super(context);
        this.movieResource = movieResource;
    }

    @Override
    public void listUpcomingMovies() {
        verifyServiceResultListener();
        listUpComingMovieAsyncTask = new ListUpComingMovieAsyncTask(getContext(), movieResource, 1);
        listUpComingMovieAsyncTask.setApiResultListener(getApiResultListener());
        listUpComingMovieAsyncTask.execute();
    }

    @Override
    public void discoverMovies(int page) {
        verifyServiceResultListener();
        discoverMoviesAsyncTask = new DiscoverMoviesAsyncTask(getContext(), movieResource, page);
        discoverMoviesAsyncTask.setApiResultListener(getApiResultListener());
        discoverMoviesAsyncTask.execute();
    }

    @Override
    public void listUpcomingMovies(int page) {
        verifyServiceResultListener();
        listUpComingMovieAsyncTask = new ListUpComingMovieAsyncTask(getContext(), movieResource, page);
        listUpComingMovieAsyncTask.setApiResultListener(getApiResultListener());
        listUpComingMovieAsyncTask.execute();
    }

    @Override
    public void listPopularMovies() {
        verifyServiceResultListener();
        listPopularMoviesAsyncTask = new ListPopularMoviesAsyncTask(getContext(), movieResource, 1);
        listPopularMoviesAsyncTask.setApiResultListener(getApiResultListener());
        listPopularMoviesAsyncTask.execute();
    }

    @Override
    public void listPopularMovies(int page) {
        verifyServiceResultListener();
        listPopularMoviesAsyncTask = new ListPopularMoviesAsyncTask(getContext(), movieResource, page);
        listPopularMoviesAsyncTask.setApiResultListener(getApiResultListener());
        listPopularMoviesAsyncTask.execute();
    }

    @Override
    public void listTopRatedMovies() {
        verifyServiceResultListener();
        listTopRatedMoviesAsyncTask = new ListTopRatedMoviesAsyncTask(getContext(), movieResource, 1);
        listTopRatedMoviesAsyncTask.setApiResultListener(getApiResultListener());
        listTopRatedMoviesAsyncTask.execute();
    }

    @Override
    public void listTopRatedMovies(int page) {
        verifyServiceResultListener();
        listTopRatedMoviesAsyncTask = new ListTopRatedMoviesAsyncTask(getContext(), movieResource, page);
        listTopRatedMoviesAsyncTask.setApiResultListener(getApiResultListener());
        listTopRatedMoviesAsyncTask.execute();
    }

    @Override
    public void listNowPlayingMovies() {
        verifyServiceResultListener();
        listNowPlayingMoviesAsyncTask = new ListNowPlayingMoviesAsyncTask(getContext(), movieResource, 1);
        listNowPlayingMoviesAsyncTask.setApiResultListener(getApiResultListener());
        listNowPlayingMoviesAsyncTask.execute();
    }

    @Override
    public void listNowPlayingMovies(int page) {
        verifyServiceResultListener();
        listNowPlayingMoviesAsyncTask = new ListNowPlayingMoviesAsyncTask(getContext(), movieResource, page);
        listNowPlayingMoviesAsyncTask.setApiResultListener(getApiResultListener());
        listNowPlayingMoviesAsyncTask.execute();
    }

    @Override
    public void listByGenre(Genre genre, int page) {
        verifyServiceResultListener();
        listMovieByGenreAsyncTask = new ListMovieByGenreAsyncTask(getContext(), movieResource, genre, page);
        listMovieByGenreAsyncTask.setApiResultListener(getApiResultListener());
        listMovieByGenreAsyncTask.execute();
    }

    @Override
    public void listByName(String name, int page) {
        verifyServiceResultListener();
        listMoviesByNameAsyncTask = new ListMoviesByNameAsyncTask(getContext(), movieResource, name, page);
        listMoviesByNameAsyncTask.setApiResultListener(getApiResultListener());
        listMoviesByNameAsyncTask.execute();
    }

    @Override
    public void listByName(String name) {
        verifyServiceResultListener();
        listMoviesByNameAsyncTask = new ListMoviesByNameAsyncTask(getContext(), movieResource, name, 1);
        listMoviesByNameAsyncTask.setApiResultListener(getApiResultListener());
        listMoviesByNameAsyncTask.execute();
    }
    
    @Override
    public void cancelAllServices() {
        if(listUpComingMovieAsyncTask != null && listUpComingMovieAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            listUpComingMovieAsyncTask.cancel(true);
        }
        if(listPopularMoviesAsyncTask != null && listPopularMoviesAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            listPopularMoviesAsyncTask.cancel(true);
        }
        if(listTopRatedMoviesAsyncTask != null && listTopRatedMoviesAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            listTopRatedMoviesAsyncTask.cancel(true);
        }
        if(listNowPlayingMoviesAsyncTask != null && listNowPlayingMoviesAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            listNowPlayingMoviesAsyncTask.cancel(true);
        }
        if(listMovieByGenreAsyncTask != null && listMovieByGenreAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            listMovieByGenreAsyncTask.cancel(true);
        }
        if(listMoviesByNameAsyncTask != null && listMoviesByNameAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            listMoviesByNameAsyncTask.cancel(true);
        }
        if(discoverMoviesAsyncTask != null && discoverMoviesAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            discoverMoviesAsyncTask.cancel(true);
        }
    }
}
