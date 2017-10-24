package com.app.movieplanet.dagger;

import com.app.movieplanet.model.api.GenreApi;
import com.app.movieplanet.model.dao.MovieInterestDao;
import com.app.movieplanet.model.dao.MovieWatchedDao;
import com.app.movieplanet.model.dao.UserDao;
import com.app.movieplanet.presenter.MovieDetailPresenter;
import com.app.movieplanet.view.MovieDetailView;
import com.app.movieplanet.view.fragment.MovieDetailFragment;

import dagger.Module;
import dagger.Provides;

@Module(library = true, injects = MovieDetailFragment.class, includes = {AppModule.class, ApiModule.class, DaoModule.class})
public class MovieDetailViewModule {

    private MovieDetailView view;

    public MovieDetailViewModule(MovieDetailView view) {
        this.view = view;
    }

    @Provides
    public MovieDetailPresenter provideMovieDetailPresenter(GenreApi genreApi, MovieInterestDao movieInterestDao, MovieWatchedDao movieWatchedDao, UserDao userDao) {
        return new MovieDetailPresenter(view, genreApi, movieInterestDao, movieWatchedDao, userDao);
    }
}
