package com.example.lizx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lizx.R;
import com.taobao.sophix.SophixManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        SophixManager.getInstance().queryAndLoadNewPatch();

        final TextView tv=findViewById(R.id.tv_hello);
        tv.setText("oh success 9909!!!!");
//         new Thread("Thread1") {
//            public void run() {
//                tv.setText(System.currentTimeMillis() + "33333333");
//                tv.append(Thread.currentThread().getName());
//            };
//        }.start();




    }
}
