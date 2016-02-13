package com.roy_sun.googleplay_imitative.holder;

import com.roy_sun.googleplay_imitative.R;
import com.roy_sun.googleplay_imitative.base.BaseHolder;
import com.roy_sun.googleplay_imitative.utils.Constants;
import com.roy_sun.googleplay_imitative.utils.UIUtils;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import it.sephiroth.android.library.picasso.Picasso;

/**
 * 轮播图的holder
 * Created by Roy_Sun on 2016/2/13 0013.
 */
public class PicHolder extends BaseHolder<List<String>> {
    @Bind(R.id.item_home_pic_pager)
    ViewPager    mViewPager;
    @Bind(R.id.item_home_pic_dot_container)
    LinearLayout mPicDotContainer;

    private List<String> mData;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_home_pic, null);

        ButterKnife.bind(this, view);

        return view;

    }

    @Override
    protected void refreshUI(List<String> data) {
        // set data
        this.mData = data;

        mViewPager.setAdapter(new PicAdapter());

    }

    private class PicAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            if (mData != null) {
                return mData.size();
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView iv = new ImageView(UIUtils.getContext());
            // 加载图片数据
            String url = Constants.IMAGE_BASE + mData.get(position);

            Picasso.with(UIUtils.getContext())
                   .load(url)
                   .error(R.mipmap.ic_default)
                   .placeholder(R.mipmap.ic_default)
                   .into(iv);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(iv);

            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
