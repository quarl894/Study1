package com.mvp.yj.study_mvp.view.main.Movie;

import android.content.Context;
import android.content.Intent;
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
import com.mvp.yj.study_mvp.view.main.appbar.appbarActivity;
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
    @BindView(R.id.btn_jump)
    Button btn_jump;

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

    }

    @OnClick({R.id.btn_jump, R.id.btn_search})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_jump :
                Intent i = new Intent(context, appbarActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.btn_search :
                setListeners(edit_search.getText().toString(), 1);
                break;

        }
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

    //검색어 입력안했을 때 예외처리
    private void setListeners(String words, int pageNo){
        if(words.length()>0){
            moviePresenter.getMoreDate(words, pageNo);
        }else{
            ShowToast("검색어를 정확하게 입력해주세요.");
        }
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
