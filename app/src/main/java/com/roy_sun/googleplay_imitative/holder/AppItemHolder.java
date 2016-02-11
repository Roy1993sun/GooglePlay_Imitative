package com.roy_sun.googleplay_imitative.holder;

import com.roy_sun.googleplay_imitative.R;
import com.roy_sun.googleplay_imitative.base.BaseHolder;
import com.roy_sun.googleplay_imitative.utils.UIUtils;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Roy_Sun on 2016/2/11 0011.
 */
public class AppItemHolder extends BaseHolder<String>{

    private TextView tv;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_tmp,null);

        tv = (TextView) view.findViewById(R.id.item_tmp_tv);
        return view;
    }

    @Override
    protected void refreshUI(String data) {
        tv.setText(data);
    }
}
