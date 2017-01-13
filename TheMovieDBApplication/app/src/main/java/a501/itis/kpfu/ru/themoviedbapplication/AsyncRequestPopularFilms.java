package a501.itis.kpfu.ru.themoviedbapplication;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.RequestPopularFilmObject;
import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.RequestPopularFilms;
import a501.itis.kpfu.ru.themoviedbapplication.interfaces.API.PopularFilmsRequestInterface;
import a501.itis.kpfu.ru.themoviedbapplication.interfaces.TaskListenerInterface;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AsyncRequestPopularFilms extends AsyncTask<Void, Void, Void> {
    TaskListenerInterface mTaskListener;
    List<RequestPopularFilmObject> listOfFilms;

    public AsyncRequestPopularFilms (TaskListenerInterface taskListener) {
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

        PopularFilmsRequestInterface server = retrofit.create(PopularFilmsRequestInterface.class);
        Call<RequestPopularFilms> list = server.getPopularRequest();
        try {
            listOfFilms = list.execute().body().getResults();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(Void integer) {
        super.onPostExecute(integer);
        mTaskListener.onTaskFinish(listOfFilms, 2);

    }

    public void newListener( TaskListenerInterface taskListenerInterface) {
        mTaskListener = taskListenerInterface;
    }
}
