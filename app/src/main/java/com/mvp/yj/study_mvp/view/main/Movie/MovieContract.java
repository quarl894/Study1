package com.mvp.yj.study_mvp.view.main.Movie;

import android.content.Context;

import com.mvp.yj.study_mvp.listener.OnItemClickListener;
import com.mvp.yj.study_mvp.model.MovieList;

import java.util.ArrayList;

public interface MovieContract {

    interface View{
        void showProgress();

        void hideProgress();

        void setDataRecyclerView(ArrayList<MovieList> movieListArrayList);

        void onResponseFailure(Throwable t);

    }

    interface Presenter{
        void onDestroy();

        void getMoreDate(String words, int page);

        void getNetwork(Context context);

        void requestFromServer(String words);


    }

    interface Model{

        interface OnFinishedListener{
            void OnFinished(ArrayList<MovieList>movieListArrayList, String words, int total);

            void onFailure(Throwable t);
        }

        void getMovieList(OnFinishedListener onFinishedListener, String words, int page);
    }
}
