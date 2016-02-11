package com.roy_sun.googleplay_imitative.fragment;

import com.roy_sun.googleplay_imitative.base.BaseHolder;
import com.roy_sun.googleplay_imitative.base.SuperBaseAdapter;
import com.roy_sun.googleplay_imitative.holder.AppItemHolder;
import com.roy_sun.googleplay_imitative.utils.UIUtils;
import com.roy_sun.googleplay_imitative.view.LoadDataUI;

import android.view.View;
import android.widget.ListView;

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
        protected BaseHolder getItemHolder() {
            return new AppItemHolder();
        }


    }

}
