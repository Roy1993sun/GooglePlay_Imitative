package com.roy_sun.googleplay_imitative.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.util.TypedValue;

/**
 * Created by Roy_Sun on 2016/2/9 0009.
 */
public class UIUtils {
    private static Context mBaseContext;
    private static Handler mHandler;


    /*-------- 初始化工具 --------*/
    public static void init(Application application) {
        mBaseContext = application;

        /*-------- main thread中new --------*/
        mHandler = new Handler();

    }

    public static Context getContext() {
        return mBaseContext;
    }


    /*↓↓↓↓↓↓↓↓↓↓ 属于handler的工具方法 ↓↓↓↓↓↓↓↓↓↓*/
    public static void post(Runnable task) {
        mHandler.post(task);
    }

    public static void postDelayed(Runnable task, long delayed) {
        mHandler.postDelayed(task, delayed);
    }

    public static void removeCallbacks(Runnable task) {
        mHandler.removeCallbacks(task);
    }
    /*↑↑↑↑↑↑↑↑↑↑ 方法结束 ↑↑↑↑↑↑↑↑↑↑*/


    /*↓↓↓↓↓  得到资源的方法  ↓↓↓↓↓*/
    public static Resources getResources() {
        return mBaseContext.getResources();
    }

    public static String[] getStringArray(int resID) {
        return getResources().getStringArray(resID);
    }
    /*↑↑↑↑↑  方法结束  ↑↑↑↑↑*/


    /**
     * dp 转换 px
     *
     * @param dp 输入的dp值
     * @return 转换后的px值
     */
    public static int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, dp,
                                               getResources().getDisplayMetrics());
    }

    public static int px2dp(float px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px,
                                               getResources().getDisplayMetrics());

    }

}
