package a501.itis.kpfu.ru.themoviedbapplication.fragments.async;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import a501.itis.kpfu.ru.themoviedbapplication.async.AsyncRequestFilm;
import a501.itis.kpfu.ru.themoviedbapplication.async.AsyncRequestTvSeries;
import a501.itis.kpfu.ru.themoviedbapplication.interfaces.TaskListenerInterface;

/**
 * Created by Марат on 15.01.2017.
 */

public class TvSeriesRequestFragment extends Fragment{
    TaskListenerInterface mTaskListener;
    AsyncRequestTvSeries async;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mTaskListener = (TaskListenerInterface) context;
        if(async != null) {
            async.newListener(mTaskListener);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mTaskListener = (TaskListenerInterface) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mTaskListener = null;
    }

    public void startAsync() {
        async = new AsyncRequestTvSeries(mTaskListener);
        async.execute();
    }

    public void sendRequest(int id) {
        async = new AsyncRequestTvSeries(mTaskListener);
        async.setSeriesId(id);
        async.execute();
    }
}
