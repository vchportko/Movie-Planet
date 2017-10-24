package com.app.movieplanet.model.api.asynctask;

import android.content.Context;

import com.app.movieplanet.model.api.asynctask.exception.BadRequestException;
import com.app.movieplanet.model.api.resource.ImageResource;
import com.app.movieplanet.model.entity.Image;
import com.app.movieplanet.model.entity.Movie;

import java.util.List;

import retrofit.Response;

import static java.net.HttpURLConnection.HTTP_OK;

public class ListImageByMovieAsyncTask extends GenericAsyncTask<Void, Void, List<Image>> {

    private ImageResource imageResource;
    private Movie movie;

    public ListImageByMovieAsyncTask(Context context, ImageResource imageResource, Movie movie) {
        super(context);
        this.imageResource = imageResource;
        this.movie = movie;
    }

    @Override
    protected AsyncTaskResult<List<Image>> doInBackground(Void... params) {

        try {
            Response<List<Image>> response = imageResource.listByMovie(movie.getId(), getApiKey()).execute();
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
