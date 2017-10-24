package com.app.movieplanet.model.dao;

public interface DaoListener<ENTITY> {

    void onLoad(ENTITY entity);

}
