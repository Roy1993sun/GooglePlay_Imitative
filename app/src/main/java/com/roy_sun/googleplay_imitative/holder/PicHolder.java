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
public class PicHolder extends BaseHolder<List<String>> implements ViewPager.OnPageChangeListener {
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

        mPicDotContainer.removeAllViews();

        for (int i = 0; i < data.size(); i++) {

            View dot = new View(UIUtils.getContext());
            dot.setBackgroundResource(R.mipmap.indicator_normal);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(UIUtils.dp2px(20),
                                                                             UIUtils.dp2px(20));

            if (i != 0) {
                params.leftMargin = UIUtils.dp2px(10);
                params.bottomMargin = UIUtils.dp2px(30);
            } else {
                dot.setBackgroundResource(R.mipmap.indicator_selected);
            }
            mPicDotContainer.addView(dot, params);
        }

        mViewPager.addOnPageChangeListener(this);

        int item = Integer.MAX_VALUE / 2;
        item = item - item % mData.size();
        mViewPager.setCurrentItem(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        position = position % mData.size();
        int count = mPicDotContainer.getChildCount();

        for (int i = 0; i < count; i++) {
            View view = mPicDotContainer.getChildAt(i);
            view.setBackgroundResource(
                    position == i ? R.mipmap.indicator_selected : R.mipmap.indicator_normal);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // 添加viewpager的动画
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
    }

    private class PicAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            if (mData != null) {
                return Integer.MAX_VALUE;
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            position = position % mData.size();
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


    /*-------- develop training里的custom animation --------*/
    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
}
