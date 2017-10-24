package com.app.movieplanet.model.api.asynctask;

import android.content.Context;

import com.app.movieplanet.model.api.asynctask.exception.BadRequestException;
import com.app.movieplanet.model.api.resource.ImageResource;
import com.app.movieplanet.model.entity.Image;
import com.app.movieplanet.model.entity.Person;

import java.util.List;

import retrofit.Response;

import static java.net.HttpURLConnection.HTTP_OK;

public class ListImageByPersonAsyncTask extends GenericAsyncTask<Void, Void, List<Image>> {

    private ImageResource imageResource;
    private Person person;

    public ListImageByPersonAsyncTask(Context context, ImageResource imageResource, Person person) {
        super(context);
        this.imageResource = imageResource;
        this.person = person;
    }

    @Override
    protected AsyncTaskResult<List<Image>> doInBackground(Void... params) {

        try {
            Response<List<Image>> response = imageResource.listByPerson(person.getId(), getApiKey()).execute();
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
