package a501.itis.kpfu.ru.themoviedbapplication.Interface.API;

import a501.itis.kpfu.ru.themoviedbapplication.ApiObjects.RequestPopularFilms;
import a501.itis.kpfu.ru.themoviedbapplication.ApiObjects.RequestPopularTvSeries;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Марат on 13.01.2017.
 */

public interface PopularTvSeriesRequestInterface {
    @GET("3/tv/popular?api_key=9fa7612ec9bee429aa3e09ca8408ef48&language=en-US&page=1")
    Call<RequestPopularTvSeries> getPopularRequest();
}
