package com.app.movieplanet.model.api.asynctask;

import android.content.Context;

import com.app.movieplanet.model.api.asynctask.exception.BadRequestException;
import com.app.movieplanet.model.api.resource.CastResource;
import com.app.movieplanet.model.entity.Movie;
import com.app.movieplanet.model.entity.Person;

import java.util.List;

import retrofit.Response;

import static java.net.HttpURLConnection.HTTP_OK;

public class ListMovieByCastAsyncTask extends GenericAsyncTask<Void, Void, List<Movie>> {

    private CastResource castResource;
    private Person person;

    public ListMovieByCastAsyncTask(Context context, CastResource castResource, Person person) {
        super(context);
        this.castResource = castResource;
        this.person = person;
    }

    @Override
    protected AsyncTaskResult<List<Movie>> doInBackground(Void... params) {

        try {
            Response<List<Movie>> response = castResource.listMoviesByCast(person.getId(), getApiKey()).execute();
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
