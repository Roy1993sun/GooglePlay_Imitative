package com.roy_sun.googleplay_imitative.holder;

import com.roy_sun.googleplay_imitative.R;
import com.roy_sun.googleplay_imitative.base.BaseHolder;
import com.roy_sun.googleplay_imitative.utils.UIUtils;

import android.view.View;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Roy_Sun on 2016/2/12 0012.
 */
public class LoadMoreHolder extends BaseHolder<Integer> {
    @Bind(R.id.item_loadmore_container_loading)
    LinearLayout mLoading;
    @Bind(R.id.item_loadmore_container_retry)
    LinearLayout mRetry;

    /*-------- 设置状态 --------*/
    public static final int STATE_LOADING = 0;
    public static final int STATE_ERROR   = 1;
    public static final int STATE_EMPTY   = 2;

    @Override
    protected View initView() {
        //        TextView tv = new TextView(UIUtils.getContext());
        //        tv.setText("jiazaigengduo ");
        //        tv.setTextColor(Color.RED);
        //        tv.setPadding(10,10,10,10);
        //        tv.setGravity(Gravity.CENTER);
        //        tv.setTextSize(36);
        //        return tv;

        View view = View.inflate(UIUtils.getContext(), R.layout.item_load_more, null);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    protected void refreshUI(Integer data) {
        switch (data) {
            case STATE_LOADING:
                mLoading.setVisibility(View.VISIBLE);
                mRetry.setVisibility(View.GONE);
                break;
            case STATE_EMPTY:
                mLoading.setVisibility(View.GONE);
                mRetry.setVisibility(View.VISIBLE);
                break;
            case STATE_ERROR:
                mLoading.setVisibility(View.GONE);
                mRetry.setVisibility(View.GONE);
                break;

        }

    }
}
