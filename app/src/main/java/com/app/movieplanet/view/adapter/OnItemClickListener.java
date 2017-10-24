package com.app.movieplanet.view.adapter;

import android.view.View;

public interface OnItemClickListener<MODEL> {
    void onClick(MODEL model, View view);

    void onLongClick(MODEL model, View view);
}