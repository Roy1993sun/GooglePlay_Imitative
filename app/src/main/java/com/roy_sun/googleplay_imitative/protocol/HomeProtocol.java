package com.roy_sun.googleplay_imitative.protocol;

import com.google.gson.Gson;

import com.roy_sun.googleplay_imitative.bean.HomeBean;
import com.roy_sun.googleplay_imitative.utils.Constants;

import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 封装网络获取流程
 * Created by Roy_Sun on 2016/2/13 0013.
 */
public class HomeProtocol {

    private Map<String, String> mParameters;

    private String initUrl() {
        String url = Constants.SERVER_URL + "/home";

        StringBuilder sb = new StringBuilder(url);
        int i = 0;

        if (mParameters != null) {
            sb.append("?");
            for (Map.Entry<String, String> en : mParameters.entrySet()) {
                String key = en.getKey();
                String value = en.getValue();

                sb.append(key);
                sb.append("=");
                sb.append(value);

                if (i != mParameters.size() - 1) {
                    sb.append("&");
                }

                i++;
            }
        }
        return sb.toString();
    }

    public void setParameters(Map<String, String> maps) {
        this.mParameters = maps;
    }

    public HomeBean loadData() throws Exception {

        OkHttpClient client = new OkHttpClient();

        String url = initUrl();

        Request request = new Request.Builder().get()
                                               .url(url)
                                               .build();

        Response response = client.newCall(request)
                                  .execute();

        if (response.isSuccessful()) {
            String json = response.body()
                                  .string();

            Gson gson = new Gson();
            return gson.fromJson(json, HomeBean.class);
        }
        return null;
    }
}
