package com.app.movieplanet.view;

import com.app.movieplanet.model.entity.Image;
import com.app.movieplanet.model.entity.Media;
import com.app.movieplanet.model.entity.Video;

import java.util.List;

public interface ListMovieMediaView {
    void warnAnyMediaFounded();

    void showLoadingMedias();

    void showVideos(List<Video> videoList);

    void showMedias(List<Media> mediaList);

    void hideLoadingMedias();

    void warnFailedToLoadMedias();

    void showImages(List<Image> imageList);
}
