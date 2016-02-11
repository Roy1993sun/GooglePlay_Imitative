package com.roy_sun.googleplay_imitative.base;

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

    public SuperBaseAdapter(List<T> datas) {
        mDatas = datas;
    }

    private List<T> mDatas;

    @Override
    public int getCount() {
        if (mDatas != null) {
            return mDatas.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mDatas != null) {
            mDatas.get(position);
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
            holder = getItemHolder();

            convertView = holder.gerRootView();
            convertView.setTag(holder);
        } else {
            holder = (BaseHolder) convertView.getTag();
        }

        T data = mDatas.get(position);
        holder.setData(data);

        return convertView;
    }

    /**
     * 暴露出去的方法,由具体的fragment去实现
     * @return 返回一个具体的holder加载到页面中
     */
    protected abstract BaseHolder getItemHolder();
}
