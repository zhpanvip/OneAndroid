package com.zhpan.oneandroid;

import android.app.Application;

import com.blankj.utilcode.util.LogUtils;
import com.zhpan.oneandroid.utils.UserInfoHelper;

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



    }
}
