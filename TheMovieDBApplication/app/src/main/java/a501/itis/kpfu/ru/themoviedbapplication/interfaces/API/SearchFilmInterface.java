package a501.itis.kpfu.ru.themoviedbapplication.interfaces.API;

import java.util.Map;

import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.searching.RequestSearchMovie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Амир on 14.01.2017.
 */

public interface SearchFilmInterface {
    @GET("3/search/movie")
    Call<RequestSearchMovie> searchMovieRequest(@QueryMap Map<String, String> parameters);
}
