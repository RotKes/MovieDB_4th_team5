package a501.itis.kpfu.ru.themoviedbapplication.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.List;

import a501.itis.kpfu.ru.themoviedbapplication.R;
import a501.itis.kpfu.ru.themoviedbapplication.fragments.MainPageFragment;
import a501.itis.kpfu.ru.themoviedbapplication.fragments.MovieSearchFragment;
import a501.itis.kpfu.ru.themoviedbapplication.fragments.MoviesListFragment;
import a501.itis.kpfu.ru.themoviedbapplication.fragments.TvSerialsListFragment;
import a501.itis.kpfu.ru.themoviedbapplication.fragments.TvShowSearchFragment;
import a501.itis.kpfu.ru.themoviedbapplication.fragments.async.PopularRequestFilmsFragment;
import a501.itis.kpfu.ru.themoviedbapplication.fragments.async.PopularRequestTvSeriesFragment;
import a501.itis.kpfu.ru.themoviedbapplication.fragments.async.SearchMovieFragment;
import a501.itis.kpfu.ru.themoviedbapplication.fragments.async.SearchTvShowFragment;
import a501.itis.kpfu.ru.themoviedbapplication.interfaces.TaskListenerInterface;

public class MainActivity extends AppCompatActivity implements TaskListenerInterface {

    private final String MOVIES_REQUEST_FRAGMENT = "movies_request_fragment";
    private final String TV_SERIES_REQUEST_FRAGMENT = "tv_series_request_fragment";
    public final String SEARCH_MOVIE_REQUEST_FRAGMENT = "search_movie_request_fragment";
    public final String SEARCH_TV_SHOW_REQUEST_FRAGMENT = "search_tv_show_request_fragment";
    private final String MOVIES_LIST_FRAGMENT = "movies_list_fragment";
    private final String TV_SERIES_FRAGMENT = "tv_series_fragment";
    int workingFragment;
    PopularRequestFilmsFragment filmsFragment;
    PopularRequestTvSeriesFragment seriesFragment;
    MainPageFragment mainFragment;
    SearchMovieFragment searchMovieFragment;
    SearchTvShowFragment searchTvShowFragment;
    TvShowSearchFragment tvShowSearchFragment;
    MovieSearchFragment movieSearchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        if (savedInstanceState != null) {
            setFragmentById(savedInstanceState.getInt("workingFragment"));
        }
        else {
            bottomBar.setDefaultTab(R.id.tab_main);
        }
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_movies) {
                    movieSearchFragment = new MovieSearchFragment();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.contentContainer, movieSearchFragment, MovieSearchFragment.class.getName())
                            .commit();
                    searchMovieFragment = (SearchMovieFragment) getAsyncFragmentByTag(SEARCH_MOVIE_REQUEST_FRAGMENT);
                    workingFragment = 3;
                    searchMovieFragment.sendRequest(" ");
                }
                if (tabId == R.id.tab_main) {
                    mainFragment = new MainPageFragment();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.contentContainer, mainFragment, MainPageFragment.class.getName())
                            .commit();
                    filmsFragment = (PopularRequestFilmsFragment) getAsyncFragmentByTag(MOVIES_REQUEST_FRAGMENT);
                    workingFragment = 1;
                    filmsFragment.startAsync();
                    seriesFragment = (PopularRequestTvSeriesFragment) getAsyncFragmentByTag(TV_SERIES_REQUEST_FRAGMENT);
                    seriesFragment.startAsync();

                }
                if (tabId == R.id.tab_series) {
                    tvShowSearchFragment = new TvShowSearchFragment();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.contentContainer, tvShowSearchFragment, TvShowSearchFragment.class.getName())
                            .commit();
                    searchTvShowFragment = (SearchTvShowFragment) getAsyncFragmentByTag(SEARCH_TV_SHOW_REQUEST_FRAGMENT);
                    workingFragment = 4;
                    searchTvShowFragment.sendRequest(" ");
                }
            }
        });
        bottomBar.setDefaultTab(R.id.tab_main);
    }

    public Fragment getAsyncFragmentByTag(String tag) {
        if (tag.equals(MOVIES_REQUEST_FRAGMENT)) {
            PopularRequestFilmsFragment popularRequestFilmsFragment = (PopularRequestFilmsFragment) getFragmentManager().findFragmentByTag(tag);
            if (popularRequestFilmsFragment == null) {
                popularRequestFilmsFragment = new PopularRequestFilmsFragment();
                getFragmentManager()
                        .beginTransaction()
                        .add(popularRequestFilmsFragment, tag)
                        .commit();
            }
            return popularRequestFilmsFragment;
        }
        else if (tag.equals(TV_SERIES_REQUEST_FRAGMENT)) {
            PopularRequestTvSeriesFragment popularRequestTvSeriesFragment = (PopularRequestTvSeriesFragment) getFragmentManager().findFragmentByTag(tag);
            if (popularRequestTvSeriesFragment == null) {
                popularRequestTvSeriesFragment = new PopularRequestTvSeriesFragment();
                getFragmentManager()
                        .beginTransaction()
                        .add(popularRequestTvSeriesFragment, tag)
                        .commit();
            }
            return popularRequestTvSeriesFragment;
        }
        else if (tag.equals(SEARCH_MOVIE_REQUEST_FRAGMENT)) {
            SearchMovieFragment searchMovieFragment = (SearchMovieFragment) getFragmentManager().findFragmentByTag(tag);
            if (searchMovieFragment == null) {
                searchMovieFragment = new SearchMovieFragment();
                getFragmentManager().beginTransaction()
                        .add(searchMovieFragment, tag)
                        .commit();
            }
            return searchMovieFragment;
        }
        else if (tag.equals(SEARCH_TV_SHOW_REQUEST_FRAGMENT)) {
            SearchTvShowFragment searchTvShowFragment = (SearchTvShowFragment) getFragmentManager().findFragmentByTag(tag);
            if (searchTvShowFragment == null) {
                searchTvShowFragment = new SearchTvShowFragment();
                getFragmentManager().beginTransaction()
                        .add(searchTvShowFragment, tag)
                        .commit();
            }
            return searchTvShowFragment;
        }
        return null;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("workingFragment", workingFragment);
    }

    public void setFragmentById(int id) {
        if (id == 1) {
            mainFragment = (MainPageFragment) getFragmentManager().findFragmentByTag(MainPageFragment.class.getName());
            getFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, mainFragment, MainPageFragment.class.getName())
                    .commit();
        }
        else if (id == 3) {
            movieSearchFragment = (MovieSearchFragment) getFragmentManager().findFragmentByTag(MovieSearchFragment.class.getName());
            getFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, movieSearchFragment, MovieSearchFragment.class.getName())
                    .commit();
        }
        else if (id == 4) {
            tvShowSearchFragment = (TvShowSearchFragment) getFragmentManager().findFragmentByTag(TvShowSearchFragment.class.getName());
            getFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, tvShowSearchFragment, TvShowSearchFragment.class.getName())
                    .commit();
        }
    }

    @Override
    public void onTaskFinish(List list, int id) {
        switch (id) {
            case 2:
                if (workingFragment == 1) {
                    MoviesListFragment moviesFragment = new MoviesListFragment();
                    moviesFragment.setList(list);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.moviesContainer, moviesFragment, MOVIES_LIST_FRAGMENT)
                            .commit();
                }
                break;
            case 1:
                if (workingFragment == 1) {
                    TvSerialsListFragment serialFragment = new TvSerialsListFragment();
                    serialFragment.setList(list);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.tvSeriesContainer, serialFragment, TV_SERIES_FRAGMENT)
                            .commit();
                }
                break;
            case 3:
                if (workingFragment == 3) {
                    MovieSearchFragment movieSearchFragment = new MovieSearchFragment();
                    movieSearchFragment.setList(list);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.contentContainer, movieSearchFragment, MovieSearchFragment.class.getName())
                            .commit();
                }
                break;
            case 4:
                if (workingFragment == 4) {
                    TvShowSearchFragment tvShowSearchFragment = new TvShowSearchFragment();
                    tvShowSearchFragment.setList(list);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.contentContainer, tvShowSearchFragment, TvShowSearchFragment.class.getName())
                            .commit();
                }
                break;
        }
    }

    @Override
    public void onTaskStarted() {

    }
}
