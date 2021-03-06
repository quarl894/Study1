package com.mvp.yj.study_mvp.view.main.appbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mvp.yj.study_mvp.R;
import com.mvp.yj.study_mvp.view.main.base.baseActivity;

import java.util.zip.Inflater;

import androidx.annotation.IdRes;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class appbarActivity extends baseActivity {
    @BindView(R.id.fab)
    FloatingActionButton fbtn;
    @BindView(R.id.bottom_app_bar)
    BottomAppBar bar;
    @BindView(R.id.btn_test)
    Button btn_test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar);
        ButterKnife.bind(this);

        init();

    }


    // Butterknife 9.0.0-rc2와 androidxd에서 error
    @OnClick({R.id.btn_test, R.id.fab}) void getTest(View v){
        switch(v.getId()){
            case R.id.btn_test :
                ShowToast("test");
                break;
            case R.id.fab :
                ShowToast("fab");
                break;
        }
    }




    public void init(){
        bar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
        bar.replaceMenu(R.menu.appbar_menu);
        bar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.app_bar_fav :
                        ShowToast("item1");
                        break;
                    case R.id.app_bar_search :
                        ShowToast("search");
                        break;
                }
                return true;

            }
        });
    }
}
