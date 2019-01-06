package com.mvp.yj.study_mvp.view.main.base;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import com.mvp.yj.study_mvp.BaseApplication;

import io.reactivex.annotations.Nullable;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class baseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void ShowToast(String text){
        Toast.makeText(getBaseContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void progressON(){
        BaseApplication.getInstance().progressOn(this,null);
    }

    public void progressON(String message){
        BaseApplication.getInstance().progressOn(this,message);
    }

    public void progessOFF(){
        BaseApplication.getInstance().progressOFF();
    }
}
