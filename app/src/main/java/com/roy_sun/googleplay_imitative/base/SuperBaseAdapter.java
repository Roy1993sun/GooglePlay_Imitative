package com.roy_sun.googleplay_imitative.base;

import com.roy_sun.googleplay_imitative.holder.LoadMoreHolder;
import com.roy_sun.googleplay_imitative.manager.ThreadManager;
import com.roy_sun.googleplay_imitative.utils.Constants;
import com.roy_sun.googleplay_imitative.utils.UIUtils;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 属于controller层,控制数据的获取与传递,
 * 由于未知所得的数据类型, 则用泛型替代.
 * Created by Roy_Sun on 2016/2/11 0011.
 */
public abstract class SuperBaseAdapter<T> extends BaseAdapter {
    private static final String TAG = "SuperBaseAdapter";
    private List<T> mData;
    private final static int TYPE_LOAD_MORE   = 0;
    private static final int TYPE_NORMAL_ITEM = 1;

    private boolean isLoadingMore = false; // 记录当前是否正在加载更多数据

    private LoadMoreHolder mLoadMoreHolder;

    public SuperBaseAdapter(List<T> datas) {
        mData = datas;
    }

    /**
     * 获得条目的类型
     */
    @Override
    public int getItemViewType(int position) {

        if (position == getCount() - 1) {
            /*-------- 加载更多的数据 --------*/
            return TYPE_LOAD_MORE;

        }

        return TYPE_NORMAL_ITEM;

    }

    /**
     * 获得adapter 中 item的*类型*个数
     */
    @Override
    public int getViewTypeCount() {

        return super.getViewTypeCount() + 1;
    }

    @Override
    public int getCount() {
        if (mData != null) {
            return mData.size() + 1; // 提供多一个条目 触发加载更多
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mData != null) {
            mData.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder;
        if (convertView == null) {

            if (getItemViewType(position) == TYPE_LOAD_MORE) {
                // TODO: 2016/2/12 0012 显示自定义的加载页面
                Log.d(TAG, "getView 加载更多");
                holder = getLoadMoreHolder();
            } else {

                holder = getItemHolder();
            }

            convertView = holder.getRootView();
            convertView.setTag(holder);


        } else {
            holder = (BaseHolder) convertView.getTag();
        }


        if (getItemViewType(position) == TYPE_LOAD_MORE) {
            // TODO: 2016/2/12 0012 加载更多的数据
            if (!hasLoadMore()) {
                // 不具备加载的条件
                holder.setData(LoadMoreHolder.STATE_EMPTY);
            } else {
                // 具备加载的条件  显示时去触发加载数据
                loadMoreData();
            }
        } else {
            T data = mData.get(position);
            holder.setData(data);
        }
        return convertView;
    }

    private void loadMoreData() {

        if (isLoadingMore) {
            return;
        }
        isLoadingMore  = true;
        // 加载loading
        mLoadMoreHolder.setData(LoadMoreHolder.STATE_LOADING);

        // 加载数据

        ThreadManager.getNormalPool()
                     .execute(new LoadMoreTask());
    }

    protected boolean hasLoadMore() {
        return false;
    }


    protected LoadMoreHolder getLoadMoreHolder() {
        if (mLoadMoreHolder == null) {
            mLoadMoreHolder = new LoadMoreHolder();
        }
        return mLoadMoreHolder;
    }

    /**
     * 暴露出去的方法,由具体的fragment去实现
     *
     * @return 返回一个具体的holder加载到页面中
     */
    protected abstract BaseHolder getItemHolder();


    protected List<T> onLoadMore() throws Exception {return null;}

    private class LoadMoreTask implements Runnable {

        @Override
        public void run() {
            // 加载数据
            List<T> more = null;
            int s = LoadMoreHolder.STATE_LOADING;
            try {
                Thread.sleep(500);
                more = onLoadMore();

            } catch (Exception e) {
                e.printStackTrace();
                // 失败则加载提示UI
                s = LoadMoreHolder.STATE_ERROR;
            }

            final int state = s;
            final List<T> datas = more;
            UIUtils.post(new Runnable() {
                @Override
                public void run() {
                    int current = state;
                    if (datas == null || datas.size() == 0) {
                        current = LoadMoreHolder.STATE_EMPTY;
                    } else {

                        if (datas.size() < Constants.PAGE_SIZE) {
                            /*-------- 服务器没有更多数据 --------*/
                            current = LoadMoreHolder.STATE_EMPTY;
                        } else {
                            current = LoadMoreHolder.STATE_LOADING;
                        }
                        mData.addAll(datas);
                        notifyDataSetChanged();

                    }

                    mLoadMoreHolder.setData(current);

                    // 加载更多的任务执行完成 修改状态
                    isLoadingMore = false;
                }
            });
        }
    }
}
