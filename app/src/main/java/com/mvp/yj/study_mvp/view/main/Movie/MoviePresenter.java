package com.mvp.yj.study_mvp.view.main.Movie;

import android.content.Context;
import android.widget.Toast;

import com.mvp.yj.study_mvp.exception.NetworkInfo;
import com.mvp.yj.study_mvp.model.MovieList;

import java.util.ArrayList;

public class MoviePresenter implements MovieContract.Presenter, MovieContract.Model.OnFinishedListener {

    private MovieContract.Model movieModel;
    private MovieContract.View movieView;
    static ArrayList<MovieList> arr = new ArrayList<>();


    public MoviePresenter( MovieContract.View movieView) {
        this.movieView = movieView;

        movieModel = new MovieModel();
    }

    //무한스크롤 total 전체 받아오기 기존 :100 까지만 받는 것을 total만큼 받게 하기.
    @Override
    public void OnFinished(ArrayList<MovieList> movieListArrayList,String words, int total) {
        arr.addAll(movieListArrayList);
        if(total>arr.size()){
            int num = arr.size()+1;
            movieModel.getMovieList(this,words, num);
        }else{
            movieView.setDataRecyclerView(arr);
        }

        if(movieView!=null){
            movieView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        movieView.onResponseFailure(t);

        if(movieView!=null)
            movieView.hideProgress();

    }

    @Override
    public void onDestroy() {
        this.movieView = null;
    }

    //api문서상 최대 100씩밖에 못가져오므로 100개후에도 계속 가져오게 만들기
    @Override
    public void getMoreDate(String words, int page) {
        if(movieView!=null){
            //초기화
            arr.clear();
            movieView.showProgress();
        }
        movieModel.getMovieList(this,words,page);

}

    //초기 데이터 가져오기
    @Override
    public void requestFromServer(String words) {
        if(movieView!=null){
            movieView.showProgress();
        }

        movieModel.getMovieList(this,words,1);
    }

    @Override
    public void getNetwork(Context context) {
        NetworkInfo.getNetwork(context);
    }
}
