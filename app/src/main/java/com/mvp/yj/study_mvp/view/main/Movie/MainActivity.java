package com.mvp.yj.study_mvp.view.main.Movie;

import android.content.Context;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mvp.yj.study_mvp.R;
import com.mvp.yj.study_mvp.api.Api;
import com.mvp.yj.study_mvp.exception.NetworkInfo;
import com.mvp.yj.study_mvp.listener.OnItemClickListener;
import com.mvp.yj.study_mvp.model.Movie;
import com.mvp.yj.study_mvp.model.MovieList;
import com.mvp.yj.study_mvp.view.main.base.baseActivity;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends baseActivity implements MovieContract.View{
    public static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView.LayoutManager layoutManager;
    MovieAdapter movieAdapter;
    Context context;
    ArrayList<MovieList> arr;

    @BindView(R.id.btn_search)
    Button btn_search;
    @BindView(R.id.edit_search)
    EditText edit_search;
    @BindView(R.id.r_view)
    RecyclerView r_view;

    @BindView(R.id.rotateloading)
    RotateLoading rotateLoading;

    private MovieContract.Presenter moviePresenter;
    private int pageNo = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        ButterKnife.bind(this);
        initRecyclerView();

        moviePresenter = new MoviePresenter(this);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setListeners(edit_search.getText().toString(), 1);
            }
        });

    }

    private void initRecyclerView(){

        r_view.setHasFixedSize(true);
        r_view.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        layoutManager = new LinearLayoutManager(this);
        r_view.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        moviePresenter.getNetwork(this);
        super.onResume();
    }

    private void setListeners(String words, int pageNo){
        moviePresenter.getMoreDate(words, pageNo);
    }

    @Override
    public void showProgress() {
        progressON("잠시만 기달려주세요...");
        arr = new ArrayList<>();
    }

    @Override
    public void hideProgress() {
        progessOFF();
    }

    @Override
    public void setDataRecyclerView(ArrayList<MovieList> movieListArrayList) {
        arr.addAll(movieListArrayList);
        movieAdapter = new MovieAdapter(context,arr);
        r_view.setAdapter(movieAdapter);
      //  movieAdapter.notifyDataSetChanged();

        pageNo++;
    }

    @Override
    public void onResponseFailure(Throwable t) {
        ShowToast(t.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        moviePresenter.onDestroy();
    }
}
