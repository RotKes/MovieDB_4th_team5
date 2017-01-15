package a501.itis.kpfu.ru.themoviedbapplication.async;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.fillmsObjects.FilmObject;
import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.seriesObjects.TvSeriesObject;
import a501.itis.kpfu.ru.themoviedbapplication.interfaces.API.FilmRequestInterface;
import a501.itis.kpfu.ru.themoviedbapplication.interfaces.API.TvSeriesRequestInterface;
import a501.itis.kpfu.ru.themoviedbapplication.interfaces.TaskListenerInterface;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Марат on 15.01.2017.
 */

public class AsyncRequestTvSeries extends AsyncTask<Void, Void, Void> {
    TaskListenerInterface mTaskListener;
    int id;
    List<TvSeriesObject> listOfSeries;

    public AsyncRequestTvSeries (TaskListenerInterface taskListener) {
        mTaskListener = taskListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/").addConverterFactory(GsonConverterFactory.create())
                .build();

        TvSeriesRequestInterface server = retrofit.create(TvSeriesRequestInterface.class);
        Call<TvSeriesObject> series = server.getTvSeries(id);
        listOfSeries = new LinkedList();
        try {
            listOfSeries.add(0, series.execute().body());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void onPostExecute(Void integer) {
        super.onPostExecute(integer);
        mTaskListener.onTaskFinish(listOfSeries, 0);

    }

    public void newListener( TaskListenerInterface taskListenerInterface) {
        mTaskListener = taskListenerInterface;
    }

    public void setSeriesId(int id) {
        this.id = id;
    }
}
