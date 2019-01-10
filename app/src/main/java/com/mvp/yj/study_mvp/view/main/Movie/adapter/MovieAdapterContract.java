package com.mvp.yj.study_mvp.view.main.Movie.adapter;

import com.mvp.yj.study_mvp.model.MovieList;

import java.util.ArrayList;

public interface MovieAdapterContract {

    interface View{
        void notifyAdapter();
    }

    interface Model{
        void addItems(ArrayList<MovieList> imageItems);

        void clearItem();

      //  MovieList getItem(int position);
    }
}
