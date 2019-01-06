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

public class MainActivity extends baseActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        ButterKnife.bind(this);

        r_view.setHasFixedSize(true);
        r_view.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        layoutManager = new LinearLayoutManager(this);
        r_view.setLayoutManager(layoutManager);

        // 검색 시작
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressON("잠시만 기달려주세요...");
                arr = new ArrayList<>();
                Search(edit_search.getText().toString(),1);
            }
        });

    }

    @Override
    protected void onResume() {
        NetworkInfo.getNetwork(context);
        super.onResume();
    }

    // @OnClick(R.id.btn_search)
    public void Search(String words, int page){
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
                        if(item.isEmpty()){
                            ShowToast("검색 결과가 없습니다");
                            movieAdapter = new MovieAdapter(context,arr);
                            r_view.setAdapter(movieAdapter);
                            progessOFF();
                        }
                        if(start>total){
                            progessOFF();
                            movieAdapter = new MovieAdapter(context,arr);
                            r_view.setAdapter(movieAdapter);
                        }
                        else{
                            for (MovieList obj : item) {
                                arr.add(obj);
                            }
                            Search(str,start+100);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {
                    Log.e("error", "" + t.getMessage());
                }
            });
        }
    }

}
