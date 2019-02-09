package com.example.lizx;

import android.content.Context;
import android.support.annotation.Keep;
import android.util.Log;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixApplication;
import com.taobao.sophix.SophixEntry;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

public class SophixStubApplication extends SophixApplication {
    private final String TAG = "SophixStubApplication";

            // 此处SophixEntry应指定真正的Application，并且保证RealApplicationStub类名不被混淆。
            @Keep
            @SophixEntry(MyApplication.class)
            static class RealApplicationStub {}


//    @Override
//    public void onCreate() {
//                super.onCreate();
//        Log.i(TAG, "onCreate: ============================");
//                /* queryAndLoadNewPatch不可放在attachBaseContext 中， 否则无网络权限，建议放在后面任意时刻，如onCreate中 */
//        SophixManager.getInstance().queryAndLoadNewPatch();
//            }



            @Override
    protected void attachBaseContext(Context base) {
                super.attachBaseContext(base);
        //         如果需要使用MultiDex，需要在此处调用。
        //         MultiDex.install(this);
                initSophix();
            }

            private void initSophix() {
                String appVersion = "0.0.0";
                try {
                        appVersion = this.getPackageManager()
                                         .getPackageInfo(this.getPackageName(), 0)
                                         .versionName;
                    } catch (Exception e) {
                    e.printStackTrace();
                    }
                    //setSecretMetaData(idSecret, appSecret, rsaSecret): <可选，推荐使用> 三个Secret分别对应AndroidManifest里面的三个，可以不在AndroidManifest设置而是用此函数来设置Secret。放到代码里面进行设置可以自定义混淆代码，更加安全，此函数的设置会覆盖AndroidManifest里面的设置，如果对应的值设为null，默认会在使用AndroidManifest里面的
                    //   .setSecretMetaData(null, null, null)

                final SophixManager instance = SophixManager.getInstance();
                instance.setContext(this)
                        .setAppVersion(appVersion)
//                        .setSecretMetaData("25640062", "40effa3311c5e622849a2e3099341245", "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCgrZDptIDfc10LAJU29hHntqMOLwKHHSUKRQv5je9U6e2xu2WGHbLlP9VU7r4u9AxSJlrNk9Od8fkR1v5mLDzzDkSH0Fmu8y62KXUttwwE7u20E4c3Pppd8urDfNvdJc5UCsRjCSIXiISNsTXVSHLboWcg7toQV8bh2fhvHg7B/acRviAPFoa+mxiyOI6BWEBgube/Sxf63dCr8eQQThjaYa4iwrFOi0YMJqzehSZggsGJ3y/f+b6dS8YTfYJiEAc9o5HitcBOhhqRn2Ye/i0Kf48gwj0zbp82SrZFRMCDwdqIvMhzLDCnaEVseOdBeiZloTeAmBT8P6iNARTbIREZAgMBAAECggEAQqAV0cKGGaG1eAVy2gpo3EMQE6aMjsgX/s0orP8cuYISjLse2uDfuE2iwQgKFmAd58Z9qnbcrcaTN8GFVYqMb2CALCzJs1ClEuXX7+/R7Q3BxQS/8blkuwYqMItKg8XUa2bXjhVQbJ+wDyznHJxg/A9OOwqvo00ya549dJ3rEqbZpDmWnSuMiDJgbi5phyUz1hwODl2CWkj2ZZRFlwGjRusT7c+wdiYahvb6rRl5Mqs0DCiOUnaROEMT6vHCDqg+rrxXHdDU4m9WKi/xWib9HQoJ9XBTX+T5d+ApGVb6a4aP9HC/fV6YWd1ByysMABqEX41P2EqLyuByUxSXvgSa7QKBgQDX6UxgOMPhrh7GJwnGKSNGE8nhT/4BzsANIrfhj/01DcdxO7RQFBajZ459ACAE6la3jh8rGhOiti48g6eRWUOXPYldUkpE1iW0WmezWiasjDLjXBGre8XAT+m2fYzWGMeqeQ/2slGQA2DhrDOulE3yxXpwXIu+bpBVj/9GDs6sRwKBgQC+gup5PimhjY+7ZawO4SKlUOKSdmOt7/Hu/yxlSmQuIWuvAPdHQfyTOJgO+MyQbGY2eP+S3trHeoOReYzPj0gdcwzW0e4SL2Aoeo3CGr5RkelQVEXsH4aklFlvTcecxR8aEu2MOFeERYIOY3sRIzSoHi5NEyAcXAl3X9s5MjDnnwKBgQDSoi+3Y0l9Xj95clYvsqxNzjpRi5cyrj9f0THwZGdSHaonRmanBKH9MaapPbj+8po8ofK4Os1THEXJFWsg+iNdZVQf7Yq00vGfm9608srt5Cw07/nxAGOwNIW9WYhOyYfoQAiZ0+aMBy+5mSQmOVo97/d9rFtMIVK/liYkc4eeOwKBgQCTJ5BxwmBp12zUdX5np+MZCtdYBnaMXjDkSNpECyhCsbH55acbtY6hMS/TML7iN7jkDqZrDhSzzKBf+CGaGbS8eqfKLzHTfiCqY6+n5PxpCWnMkKvJ7bgU3DoQzWWIZILXoxM2IjcHhqEk+BaRcD42NcnR4xoEjdsKCFLPZUN9RQKBgQCKmqDRRAv1WlJSpNpb5SAQegRa3/CH1mcO0EU1VomBsQEg2nIngpJPJh2tLI/3e6vQr8sBNrGGmIZjFeVkZ0vAudoz9W44NEm8a2p6g9p4xHCj0X/+yMRtKV+I/U97GWSsxHwVql1hO25wmcumtOJzRO+rmKVOilkhxXyHwkMICg==")
                           .setSecretMetaData(null, null, null)
                        .setEnableFullLog()
                        .setEnableDebug(true)
                        .setAesKey(null)
                        .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                                                Log.i(TAG, "=================================sophix load patch success!");
                                            } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                                                // 如果需要在后台重启，建议此处用SharePreference保存状态。
                                                Log.i(TAG, "=================================sophix preload patch success. restart app to make effect.");
                                            }else{
                                            Log.i(TAG, "====================================code="+code+",info="+info+",handlePatchVersion="+handlePatchVersion
                                            +",mode="+mode);
                                        }
                                    }
                }).initialize();
            }
}


