package a501.itis.kpfu.ru.themoviedbapplication.activity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import a501.itis.kpfu.ru.themoviedbapplication.R;
import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.fillmsObjects.FilmObject;
import a501.itis.kpfu.ru.themoviedbapplication.fragments.async.FilmRequestFragment;
import a501.itis.kpfu.ru.themoviedbapplication.interfaces.TaskListenerInterface;

public class FilmInfoActivity extends AppCompatActivity implements TaskListenerInterface{
    private final String MOVIE_REQUEST_FRAGMENT = "movie_request";
    List<FilmObject> filmInfoList;
    TextView nameFilm;
    TextView filmYear;
    TextView filmYearValue;
    TextView filmCountry;
    TextView filmCountryName;
    TextView filmOverview;
    ImageView filmPoster;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_info);
        context = this;

        nameFilm = (TextView) findViewById(R.id.filmName);
        filmYear = (TextView) findViewById(R.id.filmYear);
        filmYearValue = (TextView) findViewById(R.id.filmYearValue);
        filmCountry = (TextView) findViewById(R.id.filmCountry);
        filmCountryName = (TextView) findViewById(R.id.filmCountryName);
        filmOverview = (TextView) findViewById(R.id.filmOverview);

        filmPoster = (ImageView) findViewById(R.id.filmPoster);

        FilmRequestFragment filmRequestFragment = (FilmRequestFragment) getAsyncFragmentByTag(MOVIE_REQUEST_FRAGMENT);
        Intent intent = getIntent();
        filmRequestFragment.sendRequest(intent.getIntExtra("filmId", 1));

    }

    private Fragment getAsyncFragmentByTag(String tag){
        Fragment fragment =  getFragmentManager().findFragmentByTag(tag);
        if (fragment == null) {
            fragment = new FilmRequestFragment();
            getFragmentManager()
                    .beginTransaction()
                    .add(fragment, tag)
                    .commit();
        }
        return fragment;
    }

    @Override
    public void onTaskFinish(List list, int id) {
        filmInfoList =  list;
        FilmObject film = filmInfoList.get(0);
        nameFilm.setText(filmInfoList.get(0).getTitle());
        filmYearValue.setText(filmInfoList.get(0).getReleaseDate());
        if (filmInfoList.get(0).getProductionCountries().size() == 0) {
            filmCountryName.setText("-");
        }
        else {
            filmCountryName.setText(filmInfoList.get(0)
                    .getProductionCountries()
                    .get(0)
                    .getIso31661());
        }
        filmOverview.setText(film.getOverview());

        Picasso.with(context).load("https://image.tmdb.org/t/p/w500" + filmInfoList.get(0).getPosterPath()).into(filmPoster);


    }

    @Override
    public void onTaskStarted() {

    }
}
