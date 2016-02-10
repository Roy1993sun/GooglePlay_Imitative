package com.roy_sun.googleplay_imitative.fragment;

import com.roy_sun.googleplay_imitative.utils.UIUtils;
import com.roy_sun.googleplay_imitative.view.LoadDataUI;

import android.view.View;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Roy_Sun on 2016/2/9 0009.
 */
public class HomeFragment extends LoadDataFragment {

    /*-------- 自己实现加载数据 --------*/
    @Override protected LoadDataUI.Result doInBackground() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LoadDataUI.Result[] results = new LoadDataUI.Result[]{
                LoadDataUI.Result.SUCCESS, LoadDataUI.Result.EMPTY, LoadDataUI.Result.FAILED};

        Random r = new Random();

        return results[r.nextInt(results.length)];
    }

    @Override protected View initSuccessView() {
        /*-------- 此处可能会造成null point, 使用工具类中的方法可以避免 --------*/
        TextView textView = new TextView(UIUtils.getContext());
        textView.setText("Test init page");
        textView.setTextSize(36);

        return textView;
    }
}
