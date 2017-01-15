package a501.itis.kpfu.ru.themoviedbapplication.interfaces.API;

import java.util.Map;

import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.searching.RequestSearchTvShow;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Амир on 15.01.2017.
 */

public interface SearchTvShowInterface {
    @GET("3/search/tv")
    Call<RequestSearchTvShow> searchTvShowRequest(@QueryMap Map<String, String> parameters);
}
