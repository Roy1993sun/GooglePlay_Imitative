package com.roy_sun.googleplay_imitative.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
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
        return null;
    }

}
