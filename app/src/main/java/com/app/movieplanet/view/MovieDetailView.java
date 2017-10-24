package com.app.movieplanet.view;

import com.app.movieplanet.model.entity.Genre;

import java.util.Date;
import java.util.List;

public interface MovieDetailView {
    void showVoteCount(long voteCount);

    void showVoteAverage(float voteAverage);

    void showOverview(String overview);

    void showReleaseDate(Date releaseDate);

    void showPoster(String posterUrl);

    void showBackdrop(String backdropUrl);

    void showLoadingGenres();

    void warnFailedOnLoadGenres();

    void showGenres(List<Genre> genreList);

    void warnAnyGenreFounded();

    void hideLoadingGenres();

    void disableToCheckInterest();

    void enableToCheckInterest();

    void checkInterest();

    void uncheckInterest();

    void showUserClassification(Float classification);

    void enableToClassify();

    void warnRemovedFromWatched();

    void warnAddedAsWatched();

    void warmAddedAsInteresting();

    void removedFromInteresting();
}
