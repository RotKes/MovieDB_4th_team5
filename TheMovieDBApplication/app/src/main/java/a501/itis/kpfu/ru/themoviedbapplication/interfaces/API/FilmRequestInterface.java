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
    @GET("3/movie/{movieId}")
    Call<FilmObject> getFilm(@Path("movieId") int id);
}
