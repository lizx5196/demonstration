package com.example.lizx;

import android.app.Application;
import android.support.annotation.Keep;
import android.util.Log;

import com.taobao.sophix.SophixApplication;
import com.taobao.sophix.SophixEntry;

public class MyApplication extends Application {
    private final String TAG = "MyApplication";


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ============================");
    }
}
