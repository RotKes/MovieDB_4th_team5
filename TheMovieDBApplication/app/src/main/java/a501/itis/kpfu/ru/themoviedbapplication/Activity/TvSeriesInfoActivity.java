package a501.itis.kpfu.ru.themoviedbapplication.activity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import a501.itis.kpfu.ru.themoviedbapplication.R;
import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.fillmsObjects.FilmObject;
import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.seriesObjects.TvSeriesObject;
import a501.itis.kpfu.ru.themoviedbapplication.fragments.async.FilmRequestFragment;
import a501.itis.kpfu.ru.themoviedbapplication.fragments.async.TvSeriesRequestFragment;
import a501.itis.kpfu.ru.themoviedbapplication.interfaces.TaskListenerInterface;

public class TvSeriesInfoActivity extends AppCompatActivity implements TaskListenerInterface {
    private final String TV_REQUEST_FRAGMENT = "tv_request";
    List<TvSeriesObject> tvInfoList;
    TextView nameTv;
    TextView tvYear;
    TextView tvYearValue;
    TextView tvCountry;
    TextView tvCountryName;
    TextView tvOverview;
    ImageView tvPoster;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_series_info);
        context = this;

        nameTv = (TextView) findViewById(R.id.tvName);
        tvYear = (TextView) findViewById(R.id.tvYear);
        tvYearValue = (TextView) findViewById(R.id.tvYearValue);
        tvCountry = (TextView) findViewById(R.id.tvCountry);
        tvCountryName = (TextView) findViewById(R.id.tvCountryName);
        tvOverview = (TextView) findViewById(R.id.tvOverview);

        tvPoster = (ImageView) findViewById(R.id.tvPoster);

        TvSeriesRequestFragment tvSeriesRequestFragment = (TvSeriesRequestFragment) getAsyncFragmentByTag(TV_REQUEST_FRAGMENT);
        Intent intent = getIntent();
        tvSeriesRequestFragment.sendRequest(intent.getIntExtra("seriesId", 1));

    }

    private Fragment getAsyncFragmentByTag(String tag){
        Fragment fragment =  getFragmentManager().findFragmentByTag(tag);
        if (fragment == null) {
            fragment = new TvSeriesRequestFragment();
            getFragmentManager()
                    .beginTransaction()
                    .add(fragment, tag)
                    .commit();
        }
        return fragment;
    }

    @Override
    public void onTaskFinish(List list, int id) {
        tvInfoList =  list;
        TvSeriesObject film = tvInfoList.get(0);
        nameTv.setText(tvInfoList.get(0).getName());
        tvYearValue.setText(tvInfoList.get(0).getFirstAirDate());
        if (tvInfoList.get(0).getOriginCountry().size() == 0) {
            tvCountryName.setText("-");
        }
        else {
            tvCountryName.setText(tvInfoList.get(0)
                    .getOriginCountry().get(0));
        }
        tvOverview.setText(film.getOverview());

        Picasso.with(context).load("https://image.tmdb.org/t/p/w500" + tvInfoList.get(0).getPosterPath()).into(tvPoster);
    }


    @Override
    public void onTaskStarted() {

    }
}
