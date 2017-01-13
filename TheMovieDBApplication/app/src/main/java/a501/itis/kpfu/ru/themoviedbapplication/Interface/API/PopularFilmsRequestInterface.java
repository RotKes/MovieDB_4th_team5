package a501.itis.kpfu.ru.themoviedbapplication.Interface.API;

import a501.itis.kpfu.ru.themoviedbapplication.ApiObjects.RequestPopularFilms;
import retrofit2.Call;
import retrofit2.http.GET;



public interface PopularFilmsRequestInterface {
    @GET("3/movie/popular?api_key=9fa7612ec9bee429aa3e09ca8408ef48&language=en-US&page=1")
    Call<RequestPopularFilms> getPopularRequest();
}
