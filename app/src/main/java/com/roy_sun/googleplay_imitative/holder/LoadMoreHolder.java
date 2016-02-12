package com.roy_sun.googleplay_imitative.holder;

import com.roy_sun.googleplay_imitative.base.BaseHolder;
import com.roy_sun.googleplay_imitative.utils.UIUtils;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Roy_Sun on 2016/2/12 0012.
 */
public class LoadMoreHolder extends BaseHolder<Integer> {
    @Override
    protected View initView() {
        TextView tv = new TextView(UIUtils.getContext());
        tv.setText("jiazaigengduo ");
        tv.setTextColor(Color.RED);
        tv.setPadding(10,10,10,10);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(36);
        return tv;
    }

    @Override
    protected void refreshUI(Integer data) {

    }
}
