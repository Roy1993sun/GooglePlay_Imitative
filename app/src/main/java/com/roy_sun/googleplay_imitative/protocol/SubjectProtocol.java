package com.roy_sun.googleplay_imitative.protocol;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.roy_sun.googleplay_imitative.base.BaseProtocol;
import com.roy_sun.googleplay_imitative.bean.SubjectBean;

import java.util.List;

/**
 * Created by Roy Sun on 2016/2/27.
 */
public class SubjectProtocol extends PageProtocol<List<SubjectBean>> {
    @Override
    protected String getInterfacePath() {
        return "/subject";
    }

    @Override
    protected List<SubjectBean> parseJson(String json) {
        return new Gson().fromJson(json, new TypeToken<List<SubjectBean>>(){}.getType());
    }
}
