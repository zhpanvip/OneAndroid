package com.zhpan.oneandroid;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Debug;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.blankj.utilcode.util.LogUtils;
import com.zhpan.oneandroid.utils.UserInfoHelper;

import java.io.File;

/**
 * @author zhangpan
 * @date 2020/12/11
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.e("App", "App onCreate");
        UserInfoHelper.INSTANCE.init(this);

//        Debug.startMethodTracing(new File(getExternalFilesDir(""),"trace").getAbsolutePath(),8*1024*1024,1_000);
//        Debug.startMethodTracingSampling(new File(getExternalFilesDir(""), "trace").getAbsolutePath(), 8 * 1024 * 1024, 1_000);
        // 注册Activity生命周期监听回调
        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks);
    }


    private final ActivityLifecycleCallbacks mActivityLifecycleCallbacks=new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(@NonNull Activity activity,
            @Nullable Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {

        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {

        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity,
            @NonNull Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {

        }
    };
}
