package com.mvp.yj.study_mvp.view.main.appbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomappbar.BottomAppBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mvp.yj.study_mvp.R;
import com.mvp.yj.study_mvp.view.main.base.baseActivity;

import java.util.zip.Inflater;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class appbarActivity extends baseActivity {
    @BindView(R.id.fab)
    FloatingActionButton fbtn;
    @BindView(R.id.bottom_app_bar)
    BottomAppBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar);
        ButterKnife.bind(this);

        init();

        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowToast("click the fab");
            }
        });
    }


    public void init(){
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
