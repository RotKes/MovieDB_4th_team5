package a501.itis.kpfu.ru.themoviedbapplication.async;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.searching.RequestSearchMovie;
import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.searching.SearchedMovie;
import a501.itis.kpfu.ru.themoviedbapplication.interfaces.API.SearchFilmInterface;
import a501.itis.kpfu.ru.themoviedbapplication.interfaces.TaskListenerInterface;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class AsyncSearchFilm extends AsyncTask<Void, Void, Void> {

    TaskListenerInterface mTaskListener;
    List<SearchedMovie> listOfFilms;
    String title = " ";

    public AsyncSearchFilm (TaskListenerInterface taskListener) {
        mTaskListener = taskListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected void onPostExecute(Void integer) {
        super.onPostExecute(integer);
        mTaskListener.onTaskFinish(listOfFilms, 0);
    }

    public void newListener( TaskListenerInterface taskListenerInterface) {
        mTaskListener = taskListenerInterface;
    }

    public void setMovieTitleRequest(String title) {
        this.title = title;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/").addConverterFactory(GsonConverterFactory.create())
                .build();

        SearchFilmInterface server = retrofit.create(SearchFilmInterface.class);
        Call<RequestSearchMovie> list = server.searchMovieRequest(title);
        try {
            listOfFilms = list.execute().body().getResults();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
