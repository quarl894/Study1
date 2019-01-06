package com.mvp.yj.study_mvp.api;

import com.mvp.yj.study_mvp.model.Movie;
import com.mvp.yj.study_mvp.model.MovieList;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {

    @Headers({
            "X-Naver-Client-Id: 9DU13FbLcFc30vfXjQA9",
            "X-Naver-Client-Secret: ITm6rgEGw1"
    })
    @GET("movie.json?display=100")
    Call<Movie> getMovieList(@Query("query") String query, @Query("start") int start);

    Single<Movie> getMovieList2(@Query("query") String query, @Query("start") int start);
}
