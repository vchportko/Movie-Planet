package com.app.movieplanet.model.api.asynctask;

import android.content.Context;

import com.app.movieplanet.model.api.asynctask.exception.BadRequestException;
import com.app.movieplanet.model.api.resource.ReviewResource;
import com.app.movieplanet.model.entity.Movie;
import com.app.movieplanet.model.entity.Review;

import java.util.List;

import retrofit.Response;

import static java.net.HttpURLConnection.HTTP_OK;

public class ListReviewByMovieAsyncTask extends GenericAsyncTask<Void, Void, List<Review>> {

    private ReviewResource reviewResource;
    private Movie movie;
    private int page;

    public ListReviewByMovieAsyncTask(Context context, ReviewResource reviewResource, Movie movie, int page) {
        super(context);
        this.reviewResource = reviewResource;
        this.movie = movie;
        this.page = page;
    }

    @Override
    protected AsyncTaskResult<List<Review>> doInBackground(Void... params) {
        try {
            Response<List<Review>> response = reviewResource.listByMovie(movie.getId(), getApiKey(), page).execute();
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
