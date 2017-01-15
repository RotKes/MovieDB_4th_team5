package a501.itis.kpfu.ru.themoviedbapplication.async;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.searching.RequestSearchTvShow;
import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.searching.SearchedTvShow;
import a501.itis.kpfu.ru.themoviedbapplication.interfaces.API.SearchTvShowInterface;
import a501.itis.kpfu.ru.themoviedbapplication.interfaces.TaskListenerInterface;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Амир on 15.01.2017.
 */

public class AsyncSearchTvShow extends AsyncTask<Void, Void, Void> {
    TaskListenerInterface mTaskListener;
    List<SearchedTvShow> listOfTvSeries;
    String api_key = "9fa7612ec9bee429aa3e09ca8408ef48";
    String language= "en-US";
    String query = " ";
    String page = "1";
    Map<String, String> parameters;

    public AsyncSearchTvShow (TaskListenerInterface taskListener) {
        mTaskListener = taskListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected void onPostExecute(Void integer) {
        super.onPostExecute(integer);
        mTaskListener.onTaskFinish(listOfTvSeries, 4);
    }

    public void newListener( TaskListenerInterface taskListenerInterface) {
        mTaskListener = taskListenerInterface;
    }

    public void setTvShowTitleRequest(String title) {
        this.query = title;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        parameters = new HashMap<>();
        parameters.put("api_key", api_key);
        parameters.put("language", language);
        parameters.put("query", query);
        parameters.put("page", page);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/").addConverterFactory(GsonConverterFactory.create())
                .build();

        SearchTvShowInterface server = retrofit.create(SearchTvShowInterface.class);
        Call<RequestSearchTvShow> list = server.searchTvShowRequest(parameters);
        try {
            listOfTvSeries = list.execute().body().getResults();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
