package a501.itis.kpfu.ru.themoviedbapplication;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import a501.itis.kpfu.ru.themoviedbapplication.Interface.TaskListenerInterface;

/**
 * Created by Марат on 13.01.2017.
 */

public class PopularRequestFilmsFragment extends Fragment {
    TaskListenerInterface mTaskListener;
    AsyncRequestPopularFilms async;

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
        async = new AsyncRequestPopularFilms(mTaskListener);
        async.execute();
    }


}
