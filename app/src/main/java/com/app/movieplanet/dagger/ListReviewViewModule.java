package com.app.movieplanet.dagger;

import com.app.movieplanet.model.api.ReviewApi;
import com.app.movieplanet.presenter.ListReviewPresenter;
import com.app.movieplanet.view.ListReviewView;
import com.app.movieplanet.view.fragment.ListReviewFragment;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = {AppModule.class, ApiModule.class}, injects = ListReviewFragment.class)
public class ListReviewViewModule {

    private ListReviewView view;

    public ListReviewViewModule(ListReviewView view) {
        this.view = view;
    }

    @Provides
    public ListReviewPresenter provideListReviewPresenter(ReviewApi reviewApi) {
        return new ListReviewPresenter(view, reviewApi);
    }
}
