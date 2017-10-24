package com.app.movieplanet;

import android.support.multidex.MultiDexApplication;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.app.movieplanet.dagger.ApiModule;
import com.app.movieplanet.dagger.AppModule;
import com.app.movieplanet.dagger.DaoModule;

import dagger.ObjectGraph;

public class MovieCheckApplication extends MultiDexApplication {

    private ObjectGraph objectGraph;
    private Tracker mTracker;

    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(
                new Object[]{
                        new AppModule(MovieCheckApplication.this),
                        new ApiModule(),
                        new DaoModule()
                }
        );

    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }
}
