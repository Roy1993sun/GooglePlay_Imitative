package com.roy_sun.googleplay_imitative.fragment;

import com.roy_sun.googleplay_imitative.view.LoadDataUI;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Roy_Sun on 2016/2/9 0009.
 */
public class HomeFragment extends LoadDataFragment {

    /*-------- 自己实现加载的数据 --------*/
    @Override
    protected LoadDataUI.Result doInBackground() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return LoadDataUI.Result.SUCCESS;
    }

    @Override
    protected View initSuccessView() {
        TextView textView = new TextView(getActivity());
        textView.setText("Test init page");
        textView.setTextSize(36);

        return textView;
    }
}
