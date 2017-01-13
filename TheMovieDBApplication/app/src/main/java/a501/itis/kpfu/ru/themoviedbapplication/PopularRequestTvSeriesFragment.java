package a501.itis.kpfu.ru.themoviedbapplication;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import a501.itis.kpfu.ru.themoviedbapplication.Interface.TaskListenerInterface;


public class PopularRequestTvSeriesFragment extends Fragment {
    TaskListenerInterface mTaskListener;
    AsyncRequestPopularTvSeries async;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mTaskListener = (TaskListenerInterface) context;
        if(async!=null) {
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
        async = new AsyncRequestPopularTvSeries(mTaskListener);
        async.execute();
    }
}
