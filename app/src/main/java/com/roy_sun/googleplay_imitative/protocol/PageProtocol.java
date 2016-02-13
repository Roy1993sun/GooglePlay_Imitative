package com.roy_sun.googleplay_imitative.protocol;

import com.roy_sun.googleplay_imitative.base.BaseProtocol;

import java.util.HashMap;
import java.util.Map;

/**
 * 对具备下载下一页功能进行网络封装
 * Created by Roy_Sun on 2016/2/13 0013.
 */
public abstract class PageProtocol<T> extends BaseProtocol<T> {

    private Map<String, String> mParameters;

    @Override
    protected Map<String, String> getParams() {
        return mParameters;
    }

    public T loadPage(int index) throws Exception {
        if (mParameters == null) {
            mParameters = new HashMap<>();
        }
        mParameters.put("index",String.valueOf(index));

       return loadData();
    }

}
