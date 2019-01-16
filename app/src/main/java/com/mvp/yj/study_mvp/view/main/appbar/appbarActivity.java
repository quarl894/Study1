package com.mvp.yj.study_mvp.view.main.appbar;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomappbar.BottomAppBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mvp.yj.study_mvp.R;
import com.mvp.yj.study_mvp.view.main.base.baseActivity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class appbarActivity extends baseActivity {
    @BindView(R.id.fab)
    FloatingActionButton fbtn;
    @BindView(R.id.bottom_app_bar)
    BottomAppBar bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_appbar);

        ButterKnife.bind(this);

        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowToast("click the fab");
            }
        });

    }
}
