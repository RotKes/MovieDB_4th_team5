package a501.itis.kpfu.ru.themoviedbapplication.async;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    String api_key = "9fa7612ec9bee429aa3e09ca8408ef48";
    String language= "en-US";
    String query = " ";
    String page = "1";
    String include_adult = "false";
    Map<String, String> parameters;

    public AsyncSearchFilm (TaskListenerInterface taskListener) {
        mTaskListener = taskListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected void onPostExecute(Void integer) {
        super.onPostExecute(integer);
        mTaskListener.onTaskFinish(listOfFilms, 3);
    }

    public void newListener( TaskListenerInterface taskListenerInterface) {
        mTaskListener = taskListenerInterface;
    }

    public void setMovieTitleRequest(String title) {
        this.query = title;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        parameters = new HashMap<>();
        parameters.put("api_key", api_key);
        parameters.put("language", language);
        parameters.put("query", query);
        parameters.put("page", page);
        parameters.put("include_adult", include_adult);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/").addConverterFactory(GsonConverterFactory.create())
                .build();

        SearchFilmInterface server = retrofit.create(SearchFilmInterface.class);
        Call<RequestSearchMovie> list = server.searchMovieRequest(parameters);
        try {
            listOfFilms = list.execute().body().getResults();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
