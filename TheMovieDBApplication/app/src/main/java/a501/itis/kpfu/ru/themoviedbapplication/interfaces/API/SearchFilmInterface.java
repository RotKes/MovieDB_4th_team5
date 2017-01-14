package a501.itis.kpfu.ru.themoviedbapplication.interfaces.API;

import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.RequestPopularTvSeries;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Амир on 14.01.2017.
 */

public interface SearchFilmInterface {
    @GET("3/search/movie?api_key=9fa7612ec9bee429aa3e09ca8408ef48&language=en-US&query={movie_title}&page=1&include_adult=false")
    Call<RequestPopularTvSeries> searchMovieRequest(@Path("movie_title") String movie_title);
}
