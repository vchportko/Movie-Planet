package com.app.movieplanet.view;

import com.app.movieplanet.model.entity.Image;
import com.app.movieplanet.model.entity.Media;

import java.util.List;

public interface ListPersonMediaView {
    void warnAnyMediaFounded();

    void showMedias(List<Media> mediaList);

    void showLoadingMedias();

    void hideLoadingMedias();

    void warnFailedToLoadMedias();

    void showImages(List<Image> imageList);
}
