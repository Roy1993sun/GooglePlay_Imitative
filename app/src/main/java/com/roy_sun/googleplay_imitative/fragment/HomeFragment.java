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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        /*-------- 此处IP为我本机的内网IP --------*/
        //        String url = Constants.SERVER_URL + "/home?index=0";
        //
        //        OkHttpClient client = new OkHttpClient();
        //
        //        Request request = new Request.Builder().get()
        //                                               .url(url)
        //                                               .build();
        //        try {
        //            Response response = client.newCall(request)
        //                                      .execute();
        //            if (response.isSuccessful()) {
        //                String json = response.body()
        //                                      .string();
        //
        //                Log.d(TAG, "json : " + json);
        //                /*-------- 解析Json --------*/
        //                Gson gson = new Gson();
        //                HomeBean bean = gson.fromJson(json, HomeBean.class);
        //
        //                if (bean == null) {
        //                    Log.d(TAG, "服务器为空");
        //                    return LoadDataUI.Result.EMPTY;
        //                }
        //
        //                mDatas = bean.list;
        //                mPictures = bean.picture;
        //
        //                return LoadDataUI.Result.SUCCESS;
        //
        //            } else {
        //                /*-------- 访问失败 --------*/
        //                return LoadDataUI.Result.FAILED;
        //
        //            }
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //            return LoadDataUI.Result.FAILED;
        //        }

        /*-------- 使用封装后的网络获取加载数据 --------*/
        mProtocol = new HomeProtocol();
        Map<String, String> map = new HashMap<>();
        map.put("index", "0");
        mProtocol.setParameters(map);
        try {
            HomeBean bean = mProtocol.loadData();

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

        Map<String, String> map = new HashMap<>();
        map.put("index", "" + mDatas.size());
        mProtocol.setParameters(map);
        HomeBean bean = mProtocol.loadData();
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
