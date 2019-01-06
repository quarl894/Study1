package com.mvp.yj.study_mvp.exception;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class NetworkInfo {
    public static void getNetwork(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        android.net.NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                //WIFI에 연결됨
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                //LTE(이동통신망)에 연결됨
            }
        } else {
            // 연결되지않음
            Toast.makeText(context, "네트워크가 불안정합니다. 확인해주세요...",Toast.LENGTH_SHORT).show();
        }
    }
}
