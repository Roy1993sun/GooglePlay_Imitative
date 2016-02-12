package com.roy_sun.googleplay_imitative.fragment;

import com.google.gson.Gson;

import com.roy_sun.googleplay_imitative.base.BaseHolder;
import com.roy_sun.googleplay_imitative.base.SuperBaseAdapter;
import com.roy_sun.googleplay_imitative.bean.HomeBean;
import com.roy_sun.googleplay_imitative.holder.AppItemHolder;
import com.roy_sun.googleplay_imitative.utils.UIUtils;
import com.roy_sun.googleplay_imitative.view.LoadDataUI;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Roy_Sun on 2016/2/9 0009.
 */
public class HomeFragment extends LoadDataFragment {

    private static final String TAG = "HomeFragment";

    private List<HomeBean.AppBean> mDatas;
    private List<String>           mPictures; // 轮播图

    /*-------- 自己实现加载数据 --------*/
    @Override
    protected LoadDataUI.Result doInBackground() {

        /*-------- 此处IP为我本机的内网IP --------*/
        String url = "http://192.168.1.194:8080/GooglePlayServer/home?index=0";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().get()
                                               .url(url)
                                               .build();
        try {
            Response response = client.newCall(request)
                                      .execute();
            if (response.isSuccessful()) {
                String json = response.body()
                                      .string();

                Log.d(TAG, "json : "+ json);
                /*-------- 解析Json --------*/
                Gson gson = new Gson();
                HomeBean bean = gson.fromJson(json, HomeBean.class);

                if (bean == null) {
                    Log.d(TAG, "服务器为空");
                    return LoadDataUI.Result.EMPTY;
                }

                mDatas = bean.list;
                mPictures = bean.picture;

                return LoadDataUI.Result.SUCCESS;

            } else {
                /*-------- 访问失败 --------*/
                return LoadDataUI.Result.FAILED;

            }
        } catch (IOException e) {
            e.printStackTrace();
            return LoadDataUI.Result.FAILED;

        }
    }

    @Override
    protected View initSuccessView() {

        ListView mListView = new ListView(UIUtils.getContext());

        //// TODO: 2016/2/11 0011
        mListView.setAdapter(new HomeAdapter(mDatas));
        return mListView;
    }


    private class HomeAdapter extends SuperBaseAdapter<HomeBean.AppBean> {
        public HomeAdapter(List<HomeBean.AppBean> datas) {
            super(datas);
        }

        @Override
        protected BaseHolder getItemHolder() {
            return new AppItemHolder();
        }


    }

}
