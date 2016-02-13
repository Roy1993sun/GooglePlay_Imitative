package com.roy_sun.googleplay_imitative.fragment;

import com.roy_sun.googleplay_imitative.base.BaseHolder;
import com.roy_sun.googleplay_imitative.base.SuperBaseAdapter;
import com.roy_sun.googleplay_imitative.bean.HomeBean;
import com.roy_sun.googleplay_imitative.holder.AppItemHolder;
import com.roy_sun.googleplay_imitative.protocol.AppProtocol;
import com.roy_sun.googleplay_imitative.utils.UIUtils;
import com.roy_sun.googleplay_imitative.view.LoadDataUI;

import android.view.View;
import android.widget.ListView;

import java.util.List;

/**
 * 应用页面
 * Created by Roy_Sun on 2016/2/13 0013.
 */
public class AppFragment extends LoadDataFragment {

    private AppProtocol mProtocol;
    private List<HomeBean.AppBean> mDatas;


    @Override
    protected LoadDataUI.Result doInBackground() {
        // 加载网络数据
        mProtocol = new AppProtocol();

        try {
            mDatas = mProtocol.loadPage(0);

            if (mDatas == null || mDatas.size() == 0) {
                return LoadDataUI.Result.EMPTY;
            }
            return LoadDataUI.Result.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return LoadDataUI.Result.FAILED;
        }
    }

    @Override
    protected View initSuccessView() {
        ListView listView = new ListView(UIUtils.getContext());

        // listview的adapter
        listView.setAdapter(new AppAdapter(mDatas));
        return listView;
    }

    private class AppAdapter extends SuperBaseAdapter<HomeBean.AppBean> {


        public AppAdapter(List<HomeBean.AppBean> datas) {
            super(datas);
        }

        @Override
        protected BaseHolder getItemHolder() {
            return new AppItemHolder();
        }

        @Override
        protected boolean hasLoadMore() {
            return true;
        }

        @Override
        protected List<HomeBean.AppBean> onLoadMore() throws Exception {
            return mProtocol.loadPage(mDatas.size());
        }
    }
}
