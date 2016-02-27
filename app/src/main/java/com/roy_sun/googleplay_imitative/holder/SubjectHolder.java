package com.roy_sun.googleplay_imitative.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.roy_sun.googleplay_imitative.R;
import com.roy_sun.googleplay_imitative.base.BaseHolder;
import com.roy_sun.googleplay_imitative.bean.SubjectBean;
import com.roy_sun.googleplay_imitative.utils.Constants;
import com.roy_sun.googleplay_imitative.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Roy Sun on 2016/2/27.
 */
public class SubjectHolder extends BaseHolder<SubjectBean> {

    @Bind(R.id.item_sub_iv_icon)
    protected ImageView mIvPic;
    @Bind(R.id.item_sub_tv_desc)
    protected TextView  mTvDesc;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_subject, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void refreshUI(SubjectBean data) {

        // 文字赋值
        mTvDesc.setText(data.des);

        // 图片
        String url = Constants.IMAGE_BASE + data.url;

        Picasso.with(UIUtils.getContext())
               .load(url)
               .placeholder(R.mipmap.ic_default)
               .error(R.mipmap.ic_launcher)
               .into(mIvPic);
    }
}
