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
import a501.itis.kpfu.ru.themoviedbapplication.fragments.MoviesListFragment;
import a501.itis.kpfu.ru.themoviedbapplication.fragments.TvSerialsListFragment;
import a501.itis.kpfu.ru.themoviedbapplication.fragments.async.PopularRequestFilmsFragment;
import a501.itis.kpfu.ru.themoviedbapplication.fragments.async.PopularRequestTvSeriesFragment;
import a501.itis.kpfu.ru.themoviedbapplication.interfaces.TaskListenerInterface;

public class MainActivity extends AppCompatActivity implements TaskListenerInterface {

    private final String MOVIES_REQUEST_FRAGMENT = "movies_request_fragment";
    private final String TV_SERIES_REQUEST_FRAGMENT = "tv_series_request_fragment";
    private final String MOVIES_LIST_FRAGMENT = "movies_list_fragment";
    private final String TV_SERIES_FRAGMENT = "tv_series_fragment";
    PopularRequestFilmsFragment filmsFragment;
    PopularRequestTvSeriesFragment seriesFragment;
    MainPageFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainFragment = new MainPageFragment();

        getFragmentManager().beginTransaction().replace(R.id.contentContainer, mainFragment, MainPageFragment.class.getName()).commit();

        getFragmentManager().beginTransaction()
                .replace(R.id.contentContainer, mainFragment, MainPageFragment.class.getName())
                .commit();

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_movies) {

                }
                if (tabId == R.id.tab_main) {
                    filmsFragment = (PopularRequestFilmsFragment) getAsyncFragmentByTag(MOVIES_REQUEST_FRAGMENT);
                    filmsFragment.startAsync();
                    seriesFragment = (PopularRequestTvSeriesFragment) getAsyncFragmentByTag(TV_SERIES_REQUEST_FRAGMENT);
                    seriesFragment.startAsync();

                }
                if (tabId == R.id.tab_series) {

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
        return null;
    }

    @Override
    public void onTaskFinish(List list, int id) {
        switch (id) {
            case 2:

                MoviesListFragment moviesFragment = new MoviesListFragment();
                moviesFragment.setList(list);
                getFragmentManager().beginTransaction()
                        .replace(R.id.moviesContainer, moviesFragment, MOVIES_LIST_FRAGMENT)
                        .commit();
                break;
            case 1:
                TvSerialsListFragment serialFragment = new TvSerialsListFragment();
                serialFragment.setList(list);
                getFragmentManager().beginTransaction()
                        .replace(R.id.tvSeriesContainer, serialFragment, TV_SERIES_FRAGMENT)
                        .commit();
                break;
            case 3:
                break;
        }
    }

    @Override
    public void onTaskStarted() {

    }
}
