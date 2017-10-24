package com.app.movieplanet.model.api.asynctask;

import android.content.Context;

import com.app.movieplanet.model.api.asynctask.exception.BadRequestException;
import com.app.movieplanet.model.api.resource.MovieResource;
import com.app.movieplanet.model.entity.Movie;

import java.util.List;

import retrofit.Response;

import static java.net.HttpURLConnection.HTTP_OK;

public class ListMoviesByNameAsyncTask extends GenericAsyncTask<Void, Void, List<Movie>> {

    private MovieResource movieResource;
    private String name;
    private Integer page;

    public ListMoviesByNameAsyncTask(Context context, MovieResource movieResource, String name, Integer page) {
        super(context);
        this.movieResource = movieResource;
        this.name = name;
        this.page = page;
    }

    @Override
    protected AsyncTaskResult<List<Movie>> doInBackground(Void... params) {

        try {
            Response<List<Movie>> response = movieResource.listByName(getApiKey(), name, page).execute();
            switch (response.code()) {
                case HTTP_OK:
                    return new AsyncTaskResult<>(response.body());
                default:
                    return new AsyncTaskResult<>(new BadRequestException());
            }
        } catch (Exception ex) {
            return new AsyncTaskResult<>(new BadRequestException());
        }

    }
}
