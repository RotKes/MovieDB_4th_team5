package a501.itis.kpfu.ru.themoviedbapplication.async;

import android.os.AsyncTask;

import java.util.LinkedList;
import java.util.List;

import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.fillmsObjects.FilmObject;
import a501.itis.kpfu.ru.themoviedbapplication.interfaces.API.FilmRequestInterface;
import a501.itis.kpfu.ru.themoviedbapplication.interfaces.TaskListenerInterface;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AsyncRequestFilm extends AsyncTask<Void, Void, Void> {
    TaskListenerInterface mTaskListener;
    int id;
    List listOfFilms;

    public AsyncRequestFilm (TaskListenerInterface taskListener) {
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

        FilmRequestInterface server = retrofit.create(FilmRequestInterface.class);
        Call<FilmObject> film = server.getFilm(id);
        listOfFilms = new LinkedList();
        listOfFilms.add(film);

        return null;
    }

    protected void onPostExecute(Void integer) {
        super.onPostExecute(integer);
        mTaskListener.onTaskFinish(listOfFilms, 0);

    }

    public void newListener( TaskListenerInterface taskListenerInterface) {
        mTaskListener = taskListenerInterface;
    }

    public void setFilmId(int id) {
        this.id = id;
    }
}
