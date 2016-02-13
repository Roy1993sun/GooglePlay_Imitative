package com.roy_sun.googleplay_imitative.protocol;

import com.google.gson.Gson;

import com.roy_sun.googleplay_imitative.bean.HomeBean;

/**
 * 封装网络获取流程
 * Created by Roy_Sun on 2016/2/13 0013.
 */
public class HomeProtocol extends PageProtocol<HomeBean> {


    @Override
    protected String getInterfacePath() {
        return "/home";
    }

    @Override
    protected HomeBean parseJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json,HomeBean.class);
    }


}
