package a501.itis.kpfu.ru.themoviedbapplication.async;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.RequestPopularFilmObject;
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

    @Override
    protected Void doInBackground(Void... voids) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/").addConverterFactory(GsonConverterFactory.create())
                .build();

        SearchFilmInterface server = retrofit.create(SearchFilmInterface.class);
        Call<RequestSearchMovie> list = server.searchMovieRequest();
        try {
            listOfFilms = list.execute().body().getResults();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
