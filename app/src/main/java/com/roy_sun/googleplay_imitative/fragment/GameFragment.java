package com.roy_sun.googleplay_imitative.fragment;

import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.roy_sun.googleplay_imitative.base.BaseHolder;
import com.roy_sun.googleplay_imitative.base.SuperBaseAdapter;
import com.roy_sun.googleplay_imitative.bean.HomeBean;
import com.roy_sun.googleplay_imitative.holder.AppItemHolder;
import com.roy_sun.googleplay_imitative.protocol.GameProtocol;
import com.roy_sun.googleplay_imitative.utils.UIUtils;
import com.roy_sun.googleplay_imitative.view.LoadDataUI;

import java.util.List;

/**
 * Created by on 2016/2/26.
 * 游戏页面的加载
 */
public class GameFragment extends LoadDataFragment {

    GameProtocol mProtocol;
    private List<HomeBean.AppBean> mData;

    @Override
    protected LoadDataUI.Result doInBackground() {

        mProtocol = new GameProtocol();

        try {
            mData = mProtocol.loadPage(0);


            if (mData == null || mData.size() == 0) {
                return LoadDataUI.Result.EMPTY;
            } else {
                return LoadDataUI.Result.SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return LoadDataUI.Result.FAILED;
        }

    }

    @Override
    protected View initSuccessView() {

        ListView listView = new ListView(UIUtils.getContext());

        // listview的adapter
        listView.setAdapter(new GameAdapter(mData));
        return listView;

    }

    private class GameAdapter extends SuperBaseAdapter<HomeBean.AppBean> {
        public GameAdapter(List<HomeBean.AppBean> data) {
            super(data);
        }

        @Override
        protected BaseHolder getItemHolder() {
            return new AppItemHolder();
        }

        /*****  加载更多  *****/
        @Override
        protected boolean hasLoadMore() {
            return true;
        }

        @Override
        protected List<HomeBean.AppBean> onLoadMore() throws Exception {

            return mProtocol.loadPage(mData.size());
        }
    }
}
