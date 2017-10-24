package com.app.movieplanet.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.squareup.picasso.Picasso;
import com.app.movieplanet.MovieCheckApplication;
import com.app.movieplanet.R;
import com.app.movieplanet.dagger.PersonDetailViewModule;
import com.app.movieplanet.model.entity.Person;
import com.app.movieplanet.presenter.PersonDetailPresenter;
import com.app.movieplanet.view.PersonDetailView;
import com.app.movieplanet.view.activity.FullImageSliderActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PersonDetailFragment extends Fragment implements PersonDetailView {

    @Inject
    PersonDetailPresenter presenter;
    private static final String KEY_PERSON = "PERSON";
    private Person person;

    @Bind(R.id.textview_birthday)
    TextView textViewBirthday;
    @Bind(R.id.textview_deathday)
    TextView textViewDeathday;
    @Bind(R.id.imageview_poster)
    ImageView imageViewPhoto;
    @Bind(R.id.textview_placebirthday)
    TextView textViewPlaceOfBirth;
    @Bind(R.id.textview_biography)
    TextView textViewBiography;
    @Bind(R.id.progressbar)
    ProgressBar progressBar;
    @Bind(R.id.linearlayout_deathday)
    LinearLayout linearLayoutDeathday;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MovieCheckApplication) getActivity().getApplication())
                .getObjectGraph().plus(new PersonDetailViewModule(this)).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_persondetail, container, false);
        ButterKnife.bind(this, view);

        if(savedInstanceState != null) {
            person = savedInstanceState.getParcelable(KEY_PERSON);
        }

        if(person == null) {
            person = getArguments().getParcelable(KEY_PERSON);
            presenter.loadPerson(person.getId());
        }

        showPerson(person);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Tracker defaultTracker = ((MovieCheckApplication) getActivity().getApplication()).getDefaultTracker();
        defaultTracker.setScreenName("Person Detail Screen");
        defaultTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_PERSON, person);
    }

    public static PersonDetailFragment newInstance(Person person) {
        Bundle args = new Bundle();
        args.putParcelable(KEY_PERSON, person);
        PersonDetailFragment fragment = new PersonDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showBiography(String biography) {
        textViewBiography.setText(biography);
    }

    @Override
    public void showPlaceOfBirth(String placeOfBirth) {
        textViewPlaceOfBirth.setText(placeOfBirth);
    }

    @Override
    public void showBirthday(Date birthdayDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getString(R.string.general_date), Locale.getDefault());
        textViewBirthday.setText(simpleDateFormat.format(birthdayDate));
    }

    @Override
    public void showDeathday(Date deathday) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getString(R.string.general_date), Locale.getDefault());
        textViewDeathday.setText(simpleDateFormat.format(deathday));
    }

    @Override
    public void showPhoto(String photoUrl) {
        final String pathUrl = getString(R.string.imagetmdb_baseurl) + photoUrl;
        Picasso.with(getContext()).load(pathUrl).into(imageViewPhoto);
        imageViewPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(FullImageSliderActivity.newIntent(getContext(), pathUrl));
            }
        });
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void warnPersonNotFound() {
        Toast.makeText(getContext(), R.string.persondetailfragment_notfound, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void warnFailedToLoadPerson() {
        Toast.makeText(getContext(), R.string.persondetailfragment_failedtoload, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPerson(Person person) {
        this.person = person;
        showPhoto(person.getProfilePath());
        if (person.getDeathday() != null) {
            linearLayoutDeathday.setVisibility(View.VISIBLE);
            showDeathday(person.getDeathday());
        } else {
            linearLayoutDeathday.setVisibility(View.GONE);
        }
        if (person.getBirthday() != null) {
            showBirthday(person.getBirthday());
        }
        showBiography(person.getBiography());
        showPlaceOfBirth(person.getPlaceOfBirth());
    }

}
