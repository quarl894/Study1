package com.mvp.yj.study_mvp;

import android.app.Activity;
import android.app.Application;
import android.text.TextUtils;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialog;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class BaseApplication extends Application {

    public static BaseApplication baseApplication;
    AppCompatDialog p_dialog;

    public static BaseApplication getInstance(){
        return baseApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        baseApplication = this;

//        //font 초기 설정
//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//        .setDefaultFontPath("fonts/helvetica.ttc")
//        .setFontAttrId(R.attr.fontPath)
//        .build());
//
    }

    public void progressOn(Activity activity, String message){
        if(activity ==null || activity.isFinishing()) return;

        progressSET(message);
        if(p_dialog !=null && p_dialog.isShowing()){
            progressSET(message);
        }else{
            p_dialog = new AppCompatDialog(activity);
            p_dialog.setCancelable(false);
            p_dialog.setContentView(R.layout.progress_loading);
            p_dialog.show();
        }


        final ProgressBar p_bar = p_dialog.findViewById(R.id.p_loading);
        TextView p_message = p_dialog.findViewById(R.id.p_message);

        final android.os.Handler handler = new android.os.Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                p_bar.setIndeterminate(true);
            }
        });
        if(!TextUtils.isEmpty(message)){
            p_message.setText(message);
        }
    }

    public void progressSET(String message){
        if(p_dialog == null || !p_dialog.isShowing()) return;

        TextView p_message = p_dialog.findViewById(R.id.p_message);
        if(!TextUtils.isEmpty(message)){
            p_message.setText(message);
        }
    }

    public void progressOFF(){
        if(p_dialog !=null && p_dialog.isShowing()) p_dialog.dismiss();
    }


}
