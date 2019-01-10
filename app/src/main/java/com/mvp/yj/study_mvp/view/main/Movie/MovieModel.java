package com.mvp.yj.study_mvp.view.main.Movie;

import android.util.Log;

import com.mvp.yj.study_mvp.api.Api;
import com.mvp.yj.study_mvp.model.Movie;
import com.mvp.yj.study_mvp.model.MovieList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieModel implements MovieContract.Model {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void getMovieList(final OnFinishedListener onFinishedListener, String words, int page) {
        final int start = page;
        final String str = words;
        if(words!=null) {
            Call<Movie> call = Api.getInstance().getMovieList(words, start);

            call.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    if (response.body() != null) {
                        int total = response.body().total;
                        ArrayList<MovieList> item = response.body().items;
                        onFinishedListener.OnFinished(item, total);
                    }
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {
                    Log.e(TAG, "" + t.getMessage());
                    onFinishedListener.onFailure(t);
                }
            });
        }
    }
}
