package com.app.movieplanet.presenter;

import com.app.movieplanet.model.entity.Person;
import com.app.movieplanet.view.PersonProfileView;

public class PersonProfilePresenter {

    private PersonProfileView view;

    public PersonProfilePresenter(PersonProfileView view) {
        this.view = view;
    }

    public void init(Person person) {
        view.showPersonName(person.getName());
    }
}
