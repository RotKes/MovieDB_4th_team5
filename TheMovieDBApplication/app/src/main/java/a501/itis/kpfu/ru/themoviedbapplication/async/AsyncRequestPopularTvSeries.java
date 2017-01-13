package a501.itis.kpfu.ru.themoviedbapplication.async;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.RequestPopularTvSeries;
import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.RequestPopularTvSeriesObject;
import a501.itis.kpfu.ru.themoviedbapplication.interfaces.API.PopularFilmsRequestInterface;
import a501.itis.kpfu.ru.themoviedbapplication.interfaces.API.PopularTvSeriesRequestInterface;
import a501.itis.kpfu.ru.themoviedbapplication.interfaces.TaskListenerInterface;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class AsyncRequestPopularTvSeries extends AsyncTask<Void, Void, Void> {
    TaskListenerInterface mTaskListener;
    List<RequestPopularTvSeriesObject> listOfSeries;

    public AsyncRequestPopularTvSeries (TaskListenerInterface taskListener) {
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

        PopularTvSeriesRequestInterface server = retrofit.create(PopularTvSeriesRequestInterface.class);
        Call<RequestPopularTvSeries> list = server.getPopularRequest();
        try {
            listOfSeries = list.execute().body().getResults();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(Void integer) {
        super.onPostExecute(integer);
        mTaskListener.onTaskFinish(listOfSeries, 3);

    }

    public void newListener( TaskListenerInterface taskListenerInterface) {
        mTaskListener = taskListenerInterface;
    }
}
