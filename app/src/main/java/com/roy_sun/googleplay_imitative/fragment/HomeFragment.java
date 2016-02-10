package com.roy_sun.googleplay_imitative.fragment;

import com.roy_sun.googleplay_imitative.R;
import com.roy_sun.googleplay_imitative.base.SuperBaseAdapter;
import com.roy_sun.googleplay_imitative.utils.UIUtils;
import com.roy_sun.googleplay_imitative.view.LoadDataUI;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Roy_Sun on 2016/2/9 0009.
 */
public class HomeFragment extends LoadDataFragment {

    private List<String> mDatas; // 假数据模拟adapter

    /*-------- 自己实现加载数据 --------*/
    @Override
    protected LoadDataUI.Result doInBackground() {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mDatas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mDatas.add("Data - " + i);
        }
        return LoadDataUI.Result.SUCCESS;
    }

    @Override
    protected View initSuccessView() {

        ListView mListView = new ListView(UIUtils.getContext());

        mListView.setAdapter(new HomeAdapter(mDatas));

        return mListView;
    }


    private class HomeAdapter extends SuperBaseAdapter<String> {


        public HomeAdapter(List<String> datas) {
            super(datas);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                /*-------- 加载布局 --------*/
                convertView = View.inflate(UIUtils.getContext(), R.layout.item_tmp,null);
                /*-------- 创建holder --------*/
                holder = new ViewHolder();
                /*-------- tag --------*/
                convertView.setTag(holder);
                /*-------- 绑定view --------*/
                holder.tvTitle = (TextView) convertView.findViewById(R.id.item_tmp_tv);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }

            String data = mDatas.get(position);
            /*-------- 设置数据 --------*/
            holder.tvTitle.setText(data);

            return convertView;
        }
    }
    public class ViewHolder {
        TextView tvTitle;
    }
}
