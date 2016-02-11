package com.roy_sun.googleplay_imitative.view;

import com.roy_sun.googleplay_imitative.R;
import com.roy_sun.googleplay_imitative.manager.ThreadManager;
import com.roy_sun.googleplay_imitative.utils.UIUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

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

    private int mCurrentState = STATE_NONE;

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
        mErrorView.findViewById(R.id.error_btn_retry)
                  .setOnClickListener(new OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          Toast.makeText(getContext(), "load data", Toast.LENGTH_SHORT)
                               .show();
                          loadData();
                      }
                  });
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
//        mLoadingView.setVisibility(mCurrentState == STATE_LOADING ||
//                                           mCurrentState == STATE_NONE ? View.VISIBLE : View.GONE);
//
//        mErrorView.setVisibility(mCurrentState == STATE_ERROR ? View.VISIBLE : View.GONE);
//        mEmptyView.setVisibility(mCurrentState == STATE_EMPTY ? View.VISIBLE : View.GONE);
//
//        if (mCurrentState == STATE_SUCCESS && mSuccessView == null) {
//
//            mSuccessView = onLoadSuccessView();
//            addView(mSuccessView);
//        }
//        // 加载成功的显示
//        if (mSuccessView != null) {
//            mSuccessView.setVisibility(mCurrentState == STATE_SUCCESS ? View.VISIBLE : View.GONE);
//        }

        mLoadingView.setVisibility((mCurrentState == STATE_LOADING || mCurrentState == STATE_NONE)
                                           ? View.VISIBLE
                                           : View.GONE);

        //加载错误的显示
        mErrorView.setVisibility(mCurrentState == STATE_ERROR
                                         ? View.VISIBLE
                                         : View.GONE);
        //加载空的显示
        mEmptyView.setVisibility(mCurrentState == STATE_EMPTY
                                         ? View.VISIBLE
                                         : View.GONE);


        if (mCurrentState == STATE_SUCCESS && mSuccessView == null) {
            //没有添加过成功的view,不确定的
            mSuccessView = onLoadSuccessView();
            addView(mSuccessView);
        }

        if (mSuccessView != null) {
            //加载成功的显示
            mSuccessView.setVisibility(mCurrentState == STATE_SUCCESS
                                               ? View.VISIBLE
                                               : View.GONE);
        }
    }

    /**
     * 触发加载数据的行为, 耗时操作
     */
    public void loadData() {

        /*-------- 如果当前已经加载出success或loading页面,则不需要再次加载 --------*/
        if (mCurrentState == STATE_SUCCESS || mCurrentState == STATE_LOADING) {
            return;
        }
        /*-------- 如果是error或empty时改变状态 再次加载 --------*/
        mCurrentState = STATE_LOADING;
        safeUpdateUI();

        //        new Thread(new LoadDataTask()).start();
        /*-------- 使用threadpool --------*/
        ThreadManager.getNormalPool()
                     .execute(new LoadDataTask());
    }

    protected abstract Result onLoadData();

    protected abstract View onLoadSuccessView();

    private class LoadDataTask implements Runnable {
        @Override
        public void run() {
            Result result = onLoadData();
            mCurrentState = result.getState();

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
