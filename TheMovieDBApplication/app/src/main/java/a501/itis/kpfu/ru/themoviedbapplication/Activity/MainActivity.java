package a501.itis.kpfu.ru.themoviedbapplication.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.List;

import a501.itis.kpfu.ru.themoviedbapplication.R;
import a501.itis.kpfu.ru.themoviedbapplication.fragments.MoviesListFragment;
import a501.itis.kpfu.ru.themoviedbapplication.fragments.async.PopularRequestFilmsFragment;
import a501.itis.kpfu.ru.themoviedbapplication.fragments.async.PopularRequestTvSeriesFragment;
import a501.itis.kpfu.ru.themoviedbapplication.interfaces.TaskListenerInterface;

public class MainActivity extends AppCompatActivity implements TaskListenerInterface {

    private final String MOVIES_REQUEST_FRAGMENT = "movies_request_fragment";
    private final String TV_SERIES_REQUEST_FRAGMENT = "tv_series_request_fragment";
    PopularRequestFilmsFragment filmsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_movies) {

                }
                if (tabId == R.id.tab_main) {
                    filmsFragment = (PopularRequestFilmsFragment) getAsyncFragmentByTag(MOVIES_REQUEST_FRAGMENT);
                    filmsFragment.startAsync();
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
                MoviesListFragment fragment = new MoviesListFragment();
                fragment.setList(list);
                getFragmentManager().beginTransaction()
                        .replace(R.id.moviesContainer, fragment, MoviesListFragment.class.getName())
                        .commit();
                break;
            case 1:
                break;
            case 3:
                break;
        }
    }

    @Override
    public void onTaskStarted() {

    }
}
