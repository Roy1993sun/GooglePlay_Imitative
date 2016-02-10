package com.roy_sun.googleplay_imitative.view;

import com.roy_sun.googleplay_imitative.R;
import com.roy_sun.googleplay_imitative.utils.UIUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 负责加载数据的view
 * 需要展示一个UI:
 * 共同点:
 * 1, 加载数据的UI
 * 2, 加载失败的UI
 * 3, 服务器未返回数据的UI
 * 不同点:
 * 加载数据成功后的UI.
 * 一次只可以显示一种view
 * Created by Roy_Sun on 2016/2/10 0010.
 */
public abstract class LoadDataUI extends FrameLayout {

    public static final int STATE_NONE    = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_ERROR   = 2;
    public static final int STATE_EMPTY   = 3;
    public static final int STATE_SUCCESS = 4;
    private int mCurrState;

    private View mLoadingView;// 加载中的UI
    private View mErrorView; // 错误的UI
    private View mEmptyView;
    private View mSuccessView;

    public LoadDataUI(Context context) {
        this(context, null);
    }

    public LoadDataUI(Context context, AttributeSet attrs) {
        super(context, attrs);

        initUI();
    }

    private void initUI() {
        // 加载中的UI
        mLoadingView = View.inflate(getContext(), R.layout.pager_londing, null);
        addView(mLoadingView);


        mErrorView = View.inflate(getContext(), R.layout.pager_error, null);
        addView(mErrorView);


        mEmptyView = View.inflate(getContext(), R.layout.pager_empty, null);
        addView(mEmptyView);

        safeUpdateUI();
    }

    private void safeUpdateUI() {
        UIUtils.post(new Runnable() {
            @Override
            public void run() {
                updateUI();
            }
        });
    }

    private void updateUI() {
        mLoadingView.setVisibility(mCurrState == STATE_LOADING || mCurrState == STATE_NONE ? View.VISIBLE : View.GONE);

        mErrorView.setVisibility(mCurrState == STATE_ERROR ? View.VISIBLE : View.GONE);
        mEmptyView.setVisibility(mCurrState == STATE_EMPTY ? View.VISIBLE : View.GONE);

        if (mCurrState == STATE_SUCCESS && mSuccessView == null) {

            mSuccessView = onLoadSuccessView();
            addView(mSuccessView);
        }
        // 加载成功的显示
        if (mSuccessView != null) {
            mSuccessView.setVisibility(mCurrState == STATE_SUCCESS ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 触发加载数据的行为, 耗时操作
     */
    public void loadData() {

        new Thread(new LoadDataTask()).start();
    }

    protected abstract Result onLoadData();

    protected abstract View onLoadSuccessView();

    private class LoadDataTask implements Runnable {
        @Override
        public void run() {
            Result result = onLoadData();
            mCurrState = result.getState();

            safeUpdateUI();
        }
    }

    public enum Result {
        SUCCESS(STATE_SUCCESS), FAILED(STATE_ERROR), EMPTY(STATE_EMPTY);

        private final int mState;

        Result(int state) {
            this.mState = state;
        }

        public int getState() {
            return mState;
        }
    }
}
