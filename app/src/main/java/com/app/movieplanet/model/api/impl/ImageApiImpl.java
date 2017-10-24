package com.app.movieplanet.model.api.impl;

import android.content.Context;
import android.os.AsyncTask;

import com.app.movieplanet.model.api.GenericApi;
import com.app.movieplanet.model.api.ImageApi;
import com.app.movieplanet.model.api.asynctask.ListImageByMovieAsyncTask;
import com.app.movieplanet.model.api.asynctask.ListImageByPersonAsyncTask;
import com.app.movieplanet.model.api.resource.ImageResource;
import com.app.movieplanet.model.entity.Movie;
import com.app.movieplanet.model.entity.Person;

public class ImageApiImpl extends GenericApi implements ImageApi {

    private ImageResource imageResource;
    private ListImageByMovieAsyncTask listImageByMovieAsyncTask;
    private ListImageByPersonAsyncTask listImageByPersonAsyncTask;

    public ImageApiImpl(Context context, ImageResource imageResource) {
        super(context);
        this.imageResource = imageResource;
    }

    @Override
    public void listAllByMovie(Movie movie) {
        verifyServiceResultListener();
        listImageByMovieAsyncTask = new ListImageByMovieAsyncTask(getContext(), imageResource, movie);
        listImageByMovieAsyncTask.setApiResultListener(getApiResultListener());
        listImageByMovieAsyncTask.execute();
    }

    @Override
    public void listAllByPerson(Person person) {
        verifyServiceResultListener();
        listImageByPersonAsyncTask = new ListImageByPersonAsyncTask(getContext(), imageResource, person);
        listImageByPersonAsyncTask.setApiResultListener(getApiResultListener());
        listImageByPersonAsyncTask.execute();
    }

    @Override
    public void cancelAllServices() {
        if(listImageByMovieAsyncTask != null && listImageByMovieAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            listImageByMovieAsyncTask.cancel(true);
        }
        if(listImageByPersonAsyncTask != null && listImageByPersonAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            listImageByPersonAsyncTask.cancel(true);
        }
    }
}
