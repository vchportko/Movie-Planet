package com.app.movieplanet.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.app.movieplanet.MovieCheckApplication;
import com.app.movieplanet.R;
import com.app.movieplanet.dagger.SearchMovieViewModule;
import com.app.movieplanet.model.entity.Movie;
import com.app.movieplanet.presenter.SearchMoviePresenter;
import com.app.movieplanet.view.SearchMovieView;
import com.app.movieplanet.view.adapter.ListViewAdapterWithPagination;
import com.app.movieplanet.view.adapter.MovieListAdapter;
import com.app.movieplanet.view.adapter.OnItemClickListener;
import com.app.movieplanet.view.adapter.OnShowMoreListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchMovieActivity extends AppCompatActivity implements SearchMovieView {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerview_movies)
    RecyclerView recyclerViewMovies;
    @Bind(R.id.progressbar)
    ProgressBar progressBar;
    @Bind(R.id.linearlayout_anyfounded)
    LinearLayout linearLayoutAnyFounded;
    @Bind(R.id.linearlayout_loadfailed)
    LinearLayout linearLayoutLoadFailed;

    @Inject
    SearchMoviePresenter presenter;
    private List<Movie> movieList;
    private Integer page = 1;
    private String query;
    private Integer columns = 3;
    private int scrollToItem;
    private ListViewAdapterWithPagination listViewAdapter;
    private static final String BUNDLE_KEY_MOVIELIST = "bundle_key_movielist";
    private static final String BUNDLE_KEY_PAGE = "bundle_key_page";
    private static final String KEY_QUERY = "QUERY";
    private final int itensPerPage = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchmovie);
        ButterKnife.bind(this);
        ((MovieCheckApplication) getApplication()).getObjectGraph().plus(new SearchMovieViewModule(this)).inject(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        query = getIntent().getStringExtra(KEY_QUERY);
        getSupportActionBar().setTitle(query);
        getSupportActionBar().setSubtitle(getString(R.string.searchmovieactivity_movies));

        if (savedInstanceState == null) {
            presenter.search(query, page);
        } else {
            List<Movie> movieList = savedInstanceState.getParcelableArrayList(BUNDLE_KEY_MOVIELIST);
            if (movieList == null) {
                presenter.search(query, page);
            } else if (movieList.size() == 0) {
                warnAnyMovieFounded();
            } else {
                page = savedInstanceState.getInt(BUNDLE_KEY_PAGE);
                showMovies(movieList);
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Tracker defaultTracker = ((MovieCheckApplication) getApplication()).getDefaultTracker();
        defaultTracker.setScreenName("Search Movie Screen");
        defaultTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    protected void onStop() {
        presenter.stop();
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (movieList != null) {
            outState.putParcelableArrayList(BUNDLE_KEY_MOVIELIST, new ArrayList<>(movieList));
        }
        outState.putInt(BUNDLE_KEY_PAGE, page);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoadingMovies() {
        progressBar.setVisibility(View.VISIBLE);
        linearLayoutAnyFounded.setVisibility(View.GONE);
        linearLayoutLoadFailed.setVisibility(View.GONE);
    }

    @Override
    public void warnAnyMovieFounded() {
        if (movieList == null) {
            linearLayoutAnyFounded.setVisibility(View.VISIBLE);
            linearLayoutLoadFailed.setVisibility(View.GONE);
            recyclerViewMovies.setVisibility(View.GONE);
        } else {
            Toast.makeText(this, getString(R.string.general_anyfounded), Toast.LENGTH_SHORT).show();
            listViewAdapter.withShowMoreButton(false);
            recyclerViewMovies.setAdapter(listViewAdapter);
        }
    }

    @Override
    public void showMovies(final List<Movie> newMovieList) {
        if (movieList == null) {
            movieList = newMovieList;
        } else {
            movieList.addAll(newMovieList);
        }
        linearLayoutAnyFounded.setVisibility(View.GONE);
        linearLayoutLoadFailed.setVisibility(View.GONE);
        recyclerViewMovies.setVisibility(View.VISIBLE);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, columns, GridLayoutManager.VERTICAL, false);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position >= movieList.size() ? columns : 1;
            }
        });
        recyclerViewMovies.setLayoutManager(layoutManager);
        recyclerViewMovies.setItemAnimator(new DefaultItemAnimator());
        listViewAdapter = new ListViewAdapterWithPagination(
                new MovieListAdapter(movieList, new OnItemClickListener<Movie>() {
                    @Override
                    public void onClick(Movie movie, View view) {
                        startActivity(MovieProfileActivity.newIntent(SearchMovieActivity.this, movie), ActivityOptionsCompat.makeSceneTransitionAnimation(SearchMovieActivity.this, view.findViewById(R.id.imageview_poster), "moviePoster").toBundle());
                    }

                    @Override
                    public void onLongClick(Movie movie, View view) {

                    }
                }
                ),
                new OnShowMoreListener() {
                    @Override
                    public void showMore() {
                        scrollToItem = layoutManager.findFirstVisibleItemPosition();
                        presenter.search(query, ++page);
                    }
                },
                itensPerPage);
        recyclerViewMovies.setAdapter(listViewAdapter);
        recyclerViewMovies.scrollToPosition(scrollToItem);
    }

    @Override
    public void hideLoadingMovies() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void warnFailedToLoadMovies() {
        if (movieList == null) {
            linearLayoutAnyFounded.setVisibility(View.GONE);
            linearLayoutLoadFailed.setVisibility(View.VISIBLE);
            linearLayoutLoadFailed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.search(query, page);
                }
            });
            recyclerViewMovies.setVisibility(View.GONE);
        } else {
            Toast.makeText(this, getString(R.string.general_failedtoload), Toast.LENGTH_SHORT).show();
        }
    }

    public static Intent newIntent(Context context, String query) {
        Intent intent = new Intent(context, SearchMovieActivity.class);
        intent.putExtra(KEY_QUERY, query);
        return intent;
    }
}
