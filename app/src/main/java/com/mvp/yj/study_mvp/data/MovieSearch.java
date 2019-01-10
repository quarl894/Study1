package com.mvp.yj.study_mvp.data;

import android.content.Context;
import android.util.Log;

import com.mvp.yj.study_mvp.api.Api;
import com.mvp.yj.study_mvp.model.Movie;
import com.mvp.yj.study_mvp.model.MovieList;
import com.mvp.yj.study_mvp.view.main.Movie.MovieAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieSearch {

    private static MovieSearch movieSearch;

    public static MovieSearch getInstance(){
        if(movieSearch==null){
            movieSearch = new MovieSearch();
        }
        return movieSearch;
    }

    public MovieSearch() {
    }

//    public void getSearch(Context context, String words, int page) {
//        final int start = page;
//        final String str = words;
//        if(words!=null) {
//            Call<Movie> call = Api.getInstance().getMovieList(words, start);
//
//            call.enqueue(new Callback<Movie>() {
//                @Override
//                public void onResponse(Call<Movie> call, Response<Movie> response) {
//                    if (response.body() != null) {
//                        int total = response.body().total;
//                        ArrayList<MovieList> item = response.body().items;
//                        if(item.isEmpty()){
//                      //      ShowToast("검색 결과가 없습니다");
//                            movieAdapter = new MovieAdapter(context,arr);
//                            r_view.setAdapter(movieAdapter);
//                            progessOFF();
//                        }
//                        if(start>total){
//                            progessOFF();
//                            movieAdapter = new MovieAdapter(context,arr);
//                            r_view.setAdapter(movieAdapter);
//                        }
//                        else{
//                            for (MovieList obj : item) {
//                                arr.add(obj);
//                            }
//                            Search(str,start+100);
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<Movie> call, Throwable t) {
//                    Log.e("error", "" + t.getMessage());
//                }
//            });
//        }
//    }
}
