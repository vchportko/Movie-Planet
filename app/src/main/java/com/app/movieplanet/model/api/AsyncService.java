package com.app.movieplanet.model.api;

import com.app.movieplanet.model.api.asynctask.ApiResultListener;

public interface AsyncService {

    ApiResultListener getApiResultListener();

    void setApiResultListener(ApiResultListener listener);

    void cancelAllServices();
}
