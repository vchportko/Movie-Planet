package com.app.movieplanet.model.api.asynctask;

import android.content.Context;

import com.app.movieplanet.model.api.asynctask.exception.BadRequestException;
import com.app.movieplanet.model.api.resource.PersonResource;
import com.app.movieplanet.model.entity.Person;

import retrofit.Response;

import static java.net.HttpURLConnection.HTTP_OK;

public class FindPersonAsyncTask extends GenericAsyncTask<Void, Void, Person> {

    private PersonResource personResource;
    private Long personId;

    public FindPersonAsyncTask(Context context, PersonResource personResource, Long personId) {
        super(context);
        this.personResource = personResource;
        this.personId = personId;
    }

    @Override
    protected AsyncTaskResult<Person> doInBackground(Void... params) {

        try {
            Response<Person> response = personResource.findById(personId, getApiKey()).execute();
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
