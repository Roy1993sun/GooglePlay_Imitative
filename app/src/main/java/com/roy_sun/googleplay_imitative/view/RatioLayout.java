package com.roy_sun.googleplay_imitative.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.roy_sun.googleplay_imitative.R;

/**
 * Created by Roy Sun on 2016/2/29.
 * 测量服务器传来的图片,按照定义的规则去展示
 */
public class RatioLayout extends FrameLayout {

    public static final int RELATIVE_WIDTH  = 0;
    public static final int RELATIVE_HEIGHT = 1;

    private float mRatio    = 0f; // 图片比例值
    private int   mRelative = RELATIVE_WIDTH;


    public RatioLayout(Context context) {
        this(context, null);
    }

    public RatioLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 自定义属性
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RatioLayout);
        mRatio = ta.getFloat(R.styleable.RatioLayout_rlRatio, 0);
        mRelative = ta.getInt(R.styleable.RatioLayout_rlRelative, RELATIVE_HEIGHT);

        ta.recycle();


    }

    public void setRatio(float ratio) {
        mRatio = ratio;
    }

    public void setRelative(int relative) {
        if (relative != RELATIVE_HEIGHT || relative != RELATIVE_WIDTH) {
            throw new RuntimeException("relative 必须为 0 或 1");
        }
        mRelative = relative;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY && mRatio != 0 && mRelative == RELATIVE_WIDTH) {
            //1.已知 宽度确定的值，宽高的一个比例,计算出高度，对孩子的宽高产生一个期望
            float height = widthSize / mRatio;

            int childWidth  = widthSize - getPaddingLeft() - getPaddingRight();
            int childHeight = (int) (height - getPaddingTop() - getPaddingBottom() + 0.5f);

            //测量孩子
            int childWidthSpec  = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
            int childHeightSpec = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);
            measureChildren(childWidthSpec, childHeightSpec);

            //设置自己
            setMeasuredDimension(widthSize, (int) (height + 0.5f));
        } else if (heightMode == MeasureSpec.EXACTLY && mRatio != 0 && mRelative == RELATIVE_HEIGHT) {
            //2.已知 高度确定的值，宽高的一个比例,计算出宽度，对孩子的宽高产生一个期望
            float width = heightSize * mRatio;

            //测量孩子
            int childWidth      = (int) (width - getPaddingLeft() - getPaddingRight() + 0.5f);
            int childHeight     = heightSize - getPaddingTop() - getPaddingBottom();
            int childWidthSpec  = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
            int childHeightSpec = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);
            measureChildren(childWidthSpec, childHeightSpec);

            //设置自己
            setMeasuredDimension((int) (width + 0.5f), heightSize);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
