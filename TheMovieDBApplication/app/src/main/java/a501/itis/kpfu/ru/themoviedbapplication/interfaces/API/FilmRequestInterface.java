package a501.itis.kpfu.ru.themoviedbapplication.interfaces.API;

import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.RequestPopularFilms;
import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.fillmsObjects.FilmObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Марат on 14.01.2017.
 */

public interface FilmRequestInterface {
    @GET("3/movie/{movieId}?api_key=9fa7612ec9bee429aa3e09ca8408ef48&language=en-US")
    Call<FilmObject> getFilm(@Path("movieId") int id);
}
