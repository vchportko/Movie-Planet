package com.tassioauad.moviecheck.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.tassioauad.moviecheck.MovieCheckApplication;
import com.tassioauad.moviecheck.R;
import com.tassioauad.moviecheck.dagger.PersonWorkViewModule;
import com.tassioauad.moviecheck.model.entity.Movie;
import com.tassioauad.moviecheck.model.entity.Person;
import com.tassioauad.moviecheck.presenter.PersonWorkPresenter;
import com.tassioauad.moviecheck.view.PersonWorkView;
import com.tassioauad.moviecheck.view.activity.MovieProfileActivity;
import com.tassioauad.moviecheck.view.adapter.MovieListAdapter;
import com.tassioauad.moviecheck.view.adapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PersonWorkFragment extends Fragment implements PersonWorkView {

    @Inject
    PersonWorkPresenter presenter;

    private List<Movie> workAsCastList;
    private List<Movie> workAsCrewList;
    private Person person;
    private static final String KEY_WORKASCREWLIST = "WORKASCREWLIST";
    private static final String KEY_WORKASCASTLIST = "WORKASCASTLIST";
    private static final String KEY_PERSON = "PERSON";

    @Bind(R.id.recyclerview_cast)
    RecyclerView recyclerViewCast;
    @Bind(R.id.recyclerview_crew)
    RecyclerView recyclerViewCrew;
    @Bind(R.id.linearlayout_cast_anyfounded)
    LinearLayout linearLayoutAnyCastFounded;
    @Bind(R.id.linearlayout_cast_loadfailed)
    LinearLayout linearLayoutCastLoadFailed;
    @Bind(R.id.progressbar_cast)
    ProgressBar progressBarCast;
    @Bind(R.id.linearlayout_crew_anyfounded)
    LinearLayout linearLayoutAnyCrewFounded;
    @Bind(R.id.linearlayout_crew_loadfailed)
    LinearLayout linearLayoutCrewLoadFailed;
    @Bind(R.id.progressbar_crew)
    ProgressBar progressBarCrew;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MovieCheckApplication) getActivity().getApplication())
                .getObjectGraph().plus(new PersonWorkViewModule(this)).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personwork, container, false);
        ButterKnife.bind(this, view);

        person = getArguments().getParcelable(KEY_PERSON);

        if (workAsCastList == null && savedInstanceState != null) {
            workAsCastList = savedInstanceState.getParcelableArrayList(KEY_WORKASCASTLIST);
        }

        if (workAsCrewList == null && savedInstanceState != null){
            workAsCrewList = savedInstanceState.getParcelableArrayList(KEY_WORKASCREWLIST);
        }

        if (workAsCastList == null) {
            presenter.loadCastWorks(person);
        } else if (workAsCastList.size() == 0) {
            warnAnyWorkAsCastFounded();
        } else {
            showWorksAsCast(workAsCastList);
        }

        if (workAsCrewList == null) {
            presenter.loadCrewWorks(person);
        } else if (workAsCrewList.size() == 0) {
            warnAnyWorkAsCrewFounded();
        } else {
            showWorksAsCrew(workAsCrewList);
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Tracker defaultTracker = ((MovieCheckApplication) getActivity().getApplication()).getDefaultTracker();
        defaultTracker.setScreenName("Person Work Screen");
        defaultTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public void onStop() {
        presenter.stop();
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (workAsCrewList != null) {
            outState.putParcelableArrayList(KEY_WORKASCREWLIST, new ArrayList<>(workAsCrewList));
        }
        if (workAsCastList != null) {
            outState.putParcelableArrayList(KEY_WORKASCASTLIST, new ArrayList<>(workAsCastList));
        }
    }

    public static PersonWorkFragment newInstance(Person person) {
        Bundle args = new Bundle();
        args.putParcelable(KEY_PERSON, person);
        PersonWorkFragment fragment = new PersonWorkFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void hideLoadingWorkAsCrew() {
        progressBarCrew.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingWorkAsCrew() {
        progressBarCrew.setVisibility(View.VISIBLE);
        linearLayoutCrewLoadFailed.setVisibility(View.GONE);
        linearLayoutAnyCrewFounded.setVisibility(View.GONE);
    }

    @Override
    public void showWorksAsCrew(List<Movie> movieList) {
        this.workAsCrewList = movieList;
        linearLayoutCrewLoadFailed.setVisibility(View.GONE);
        linearLayoutAnyCrewFounded.setVisibility(View.GONE);
        recyclerViewCrew.setVisibility(View.VISIBLE);
        recyclerViewCrew.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        recyclerViewCrew.setAdapter(new MovieListAdapter(movieList, new OnItemClickListener<Movie>() {
            @Override
            public void onClick(Movie movie, View view) {
                startActivity(MovieProfileActivity.newIntent(getActivity(), movie), ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), view.findViewById(R.id.imageview_poster), "moviePoster").toBundle());
            }

            @Override
            public void onLongClick(Movie movie, View view) {

            }
        }));
    }

    @Override
    public void warnAnyWorkAsCrewFounded() {
        workAsCrewList = new ArrayList<>();
        linearLayoutCrewLoadFailed.setVisibility(View.GONE);
        linearLayoutAnyCrewFounded.setVisibility(View.VISIBLE);
        recyclerViewCrew.setVisibility(View.GONE);
    }

    @Override
    public void warnFailedToLoadWorkAsCrew() {
        linearLayoutCrewLoadFailed.setVisibility(View.VISIBLE);
        linearLayoutCrewLoadFailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loadCrewWorks(person);
            }
        });
        linearLayoutAnyCrewFounded.setVisibility(View.GONE);
        recyclerViewCrew.setVisibility(View.GONE);
    }

    @Override
    public void hideLoadingWorkAsCast() {
        progressBarCast.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingWorkAsCast() {
        progressBarCast.setVisibility(View.VISIBLE);
        linearLayoutCastLoadFailed.setVisibility(View.GONE);
        linearLayoutAnyCastFounded.setVisibility(View.GONE);
    }

    @Override
    public void showWorksAsCast(List<Movie> movieList) {
        this.workAsCastList = movieList;
        linearLayoutCastLoadFailed.setVisibility(View.GONE);
        linearLayoutAnyCastFounded.setVisibility(View.GONE);
        recyclerViewCast.setVisibility(View.VISIBLE);
        recyclerViewCast.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        recyclerViewCast.setAdapter(new MovieListAdapter(movieList, new OnItemClickListener<Movie>() {
            @Override
            public void onClick(Movie movie, View view) {
                startActivity(MovieProfileActivity.newIntent(getActivity(), movie), ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), view.findViewById(R.id.imageview_poster), "moviePoster").toBundle());
            }

            @Override
            public void onLongClick(Movie movie, View view) {

            }
        }));
    }

    @Override
    public void warnAnyWorkAsCastFounded() {
        workAsCastList = new ArrayList<>();
        linearLayoutCastLoadFailed.setVisibility(View.GONE);
        linearLayoutAnyCastFounded.setVisibility(View.VISIBLE);
        recyclerViewCast.setVisibility(View.GONE);
    }

    @Override
    public void warnFailedToLoadWorkAsCast() {
        linearLayoutCastLoadFailed.setVisibility(View.VISIBLE);
        linearLayoutCastLoadFailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loadCastWorks(person);
            }
        });
        linearLayoutAnyCastFounded.setVisibility(View.GONE);
        recyclerViewCast.setVisibility(View.GONE);
    }
}
