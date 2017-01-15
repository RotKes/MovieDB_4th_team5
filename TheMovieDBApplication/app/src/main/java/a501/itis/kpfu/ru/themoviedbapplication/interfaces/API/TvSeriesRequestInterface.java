package a501.itis.kpfu.ru.themoviedbapplication.interfaces.API;

import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.fillmsObjects.FilmObject;
import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.seriesObjects.TvSeriesObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Марат on 15.01.2017.
 */

public interface TvSeriesRequestInterface {
    @GET("3/tv/{seriesId}?api_key=9fa7612ec9bee429aa3e09ca8408ef48&language=en-US")
    Call<TvSeriesObject> getTvSeries(@Path("seriesId") int id);
}
