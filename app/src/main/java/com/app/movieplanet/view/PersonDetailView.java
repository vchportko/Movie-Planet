package com.app.movieplanet.view;

import com.app.movieplanet.model.entity.Person;

import java.util.Date;

public interface PersonDetailView {

    void showBiography(String overview);

    void showPlaceOfBirth(String placeOfBirth);

    void showBirthday(Date birthday);

    void showDeathday(Date deathday);

    void showPhoto(String posterUrl);

    void showLoading();

    void hideLoading();

    void warnPersonNotFound();

    void warnFailedToLoadPerson();

    void showPerson(Person person);
}
