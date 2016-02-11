package com.roy_sun.googleplay_imitative.holder;

import com.roy_sun.googleplay_imitative.R;
import com.roy_sun.googleplay_imitative.base.BaseHolder;
import com.roy_sun.googleplay_imitative.bean.HomeBean;
import com.roy_sun.googleplay_imitative.utils.UIUtils;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Roy_Sun on 2016/2/11 0011.
 */
public class AppItemHolder extends BaseHolder<HomeBean.AppBean> {


    @Bind(R.id.item_appinfo_iv_icon)
    protected ImageView mIvIcon;
    @Bind(R.id.item_appinfo_tv_title)
    protected TextView  mTvTitle;
    @Bind(R.id.item_appinfo_rb_stars)
    protected RatingBar mRbStars;
    @Bind(R.id.item_appinfo_tv_size)
    protected TextView  mTvSize;
    @Bind(R.id.item_appinfo_tv_des)
    protected TextView  mTvDes;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_app_info, null);


        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void refreshUI(HomeBean.AppBean data) {

        mTvDes.setText(data.des);
        mTvSize.setText(Formatter.formatFileSize(UIUtils.getContext(),data.size));
        mTvTitle.setText(data.name);
        
        //// TODO: 2016/2/12 0012
    }


}
