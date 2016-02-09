package com.roy_sun.googleplay_imitative.base;

import com.roy_sun.googleplay_imitative.utils.UIUtils;

import android.app.Application;

/**
 * Created by Roy_Sun on 2016/2/9 0009.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化工具
        UIUtils.init(this);
    }
}
