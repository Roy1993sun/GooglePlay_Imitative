package com.roy_sun.googleplay_imitative.fragment;

import com.roy_sun.googleplay_imitative.base.BaseHolder;
import com.roy_sun.googleplay_imitative.base.SuperBaseAdapter;
import com.roy_sun.googleplay_imitative.bean.HomeBean;
import com.roy_sun.googleplay_imitative.holder.AppItemHolder;
import com.roy_sun.googleplay_imitative.protocol.HomeProtocol;
import com.roy_sun.googleplay_imitative.utils.UIUtils;
import com.roy_sun.googleplay_imitative.view.LoadDataUI;

import android.view.View;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Roy_Sun on 2016/2/9 0009.
 */
public class HomeFragment extends LoadDataFragment {

    private static final String TAG = "HomeFragment";

    private List<HomeBean.AppBean> mDatas;
    private List<String>           mPictures; // 轮播图
    private HomeProtocol           mProtocol;

    /*-------- 自己实现加载数据 --------*/
    @Override
    protected LoadDataUI.Result doInBackground() {

        /*-------- 使用封装后的网络获取加载数据 --------*/
        mProtocol = new HomeProtocol();
        try {
            HomeBean bean = mProtocol.loadPage(0);

            if (bean == null) {
                return LoadDataUI.Result.EMPTY;
            }
            mDatas = bean.list;
            mPictures = bean.picture;
            return LoadDataUI.Result.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return LoadDataUI.Result.FAILED;
        }
    }


    @Override
    protected View initSuccessView() {

        ListView mListView = new ListView(UIUtils.getContext());

        //// TODO: 2016/2/11 0011 轮播图
        mListView.setAdapter(new HomeAdapter(mDatas));
        return mListView;
    }


    private class HomeAdapter extends SuperBaseAdapter<HomeBean.AppBean> {
        public HomeAdapter(List<HomeBean.AppBean> datas) {
            super(datas);
        }

        @Override
        protected boolean hasLoadMore() {
            return true;
        }

        @Override
        protected BaseHolder getItemHolder() {
            return new AppItemHolder();
        }

        @Override
        protected List<HomeBean.AppBean> onLoadMore() throws Exception {
            return loadMore();
        }
    }

    /*-------- 通过网络加载数据 --------*/
    private List<HomeBean.AppBean> loadMore() throws Exception {

        HomeBean bean = mProtocol.loadPage(mDatas.size());
        if (bean != null) {
            return bean.list;
        }
        return null;


        //        OkHttpClient client = new OkHttpClient();
        //        String url = Constants.SERVER_URL + "/home?index=" + mDatas.size();
        //
        //        Request request = new Request.Builder().get()
        //                                               .url(url)
        //                                               .build();
        //
        //        Response response = client.newCall(request)
        //                                  .execute();
        //
        //        if (response.isSuccessful()) {
        //            String json = response.body()
        //                                  .string();
        //            Gson gson = new Gson();
        //            HomeBean bean = gson.fromJson(json, HomeBean.class);
        //
        //            if (bean != null) {
        //                return bean.list;
        //            }
        //        } else {
        //            return null;
        //        }
        //
        //        return null;
    }
}
