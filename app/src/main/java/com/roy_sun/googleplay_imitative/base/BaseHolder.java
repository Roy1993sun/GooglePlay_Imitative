package com.roy_sun.googleplay_imitative.base;

import android.view.View;

/**
 * Created by Roy_Sun on 2016/2/11 0011.
 */
public abstract class BaseHolder<T> {

    private View mRootView;

    protected BaseHolder() {
        //        初始化View
        mRootView = initView();
    }

    protected abstract View initView();

    public View gerRootView() {
        return mRootView;
    }

    public void setData(T data) {
        /*-------- 更新UI --------*/
        refreshUI(data);

    }

    protected abstract void refreshUI(T data);


}
