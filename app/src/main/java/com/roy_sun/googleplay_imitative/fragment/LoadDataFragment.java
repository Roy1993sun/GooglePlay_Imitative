package com.roy_sun.googleplay_imitative.fragment;

import com.roy_sun.googleplay_imitative.base.BaseFragment;
import com.roy_sun.googleplay_imitative.view.LoadDataUI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * 加载数据的fragment的基类
 * Created by Roy_Sun on 2016/2/10 0010.
 */
public abstract class LoadDataFragment extends BaseFragment {
    private static final String TAG = "LoadDataFragment";
    private LoadDataUI mUi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
            @Nullable
            ViewGroup container,
            @Nullable
            Bundle savedInstanceState) {
        if (mUi == null) {
            Log.d(TAG, "onCreateView mui为空");
            mUi = new LoadDataUI(getActivity()) {
                @Override
                protected Result onLoadData() {
                /*-------- 实现加载数据的方式 --------*/
                    return doInBackground();
                }

                @Override
                protected View onLoadSuccessView() {
                    return initSuccessView();
                }
            };
        } else {
            //不等于null,说明mui已经有父容器了
            ViewParent parent = mUi.getParent();

            if (parent != null && parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(mUi);
            }

        }
        return mUi;
    }


    @Override
    public void onActivityCreated(
            @Nullable
            Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void loadData(){
        if (mUi != null) {
            // 加载数据
            mUi.loadData();
        }
    }

    protected abstract LoadDataUI.Result doInBackground();

    protected abstract View initSuccessView();
}
