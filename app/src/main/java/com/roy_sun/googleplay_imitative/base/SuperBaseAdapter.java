package com.roy_sun.googleplay_imitative.base;

import com.roy_sun.googleplay_imitative.holder.LoadMoreHolder;

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

            convertView = holder.gerRootView();
            convertView.setTag(holder);


        } else {
            holder = (BaseHolder) convertView.getTag();
        }


        if (getItemViewType(position) == TYPE_LOAD_MORE) {
            // TODO: 2016/2/12 0012 加载更多的数据

        } else {
            T data = mData.get(position);
            holder.setData(data);
        }
        return convertView;
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
}
