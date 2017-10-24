package com.app.movieplanet.model.api.asynctask;

import android.content.Context;

import com.app.movieplanet.model.api.asynctask.exception.BadRequestException;
import com.app.movieplanet.model.api.resource.CrewResource;
import com.app.movieplanet.model.entity.Crew;
import com.app.movieplanet.model.entity.Movie;

import java.util.List;

import retrofit.Response;

import static java.net.HttpURLConnection.HTTP_OK;

public class ListCrewByMovieAsyncTask extends GenericAsyncTask<Void, Void, List<Crew>> {

    private CrewResource crewResource;
    private Movie movie;

    public ListCrewByMovieAsyncTask(Context context, CrewResource crewResource, Movie movie) {
        super(context);
        this.crewResource = crewResource;
        this.movie = movie;
    }

    @Override
    protected AsyncTaskResult<List<Crew>> doInBackground(Void... params) {

        try {
            Response<List<Crew>> response = crewResource.listAllByMovie(movie.getId(), getApiKey()).execute();
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
