package com.roy_sun.googleplay_imitative.protocol;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.roy_sun.googleplay_imitative.bean.HomeBean;

import java.util.List;

/**
 * Created by Roy_Sun on 2016/2/13 0013.
 */
public class AppProtocol extends PageProtocol<List<HomeBean.AppBean>> {
    @Override
    protected String getInterfacePath() {
        return "/app";
    }

    @Override
    protected List<HomeBean.AppBean> parseJson(String json) {
        Gson gson = new Gson();

        return gson.fromJson(json, new TypeToken<List<HomeBean.AppBean>>(){}.getType());
    }


}
