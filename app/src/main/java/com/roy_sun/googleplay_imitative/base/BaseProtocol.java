package com.roy_sun.googleplay_imitative.base;

import com.roy_sun.googleplay_imitative.utils.Constants;

import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 抽取可变参数做成abstract:
 * url地址
 * url参数
 * 返回的bean
 * Created by Roy_Sun on 2016/2/13 0013.
 */
public abstract class BaseProtocol<T> {

    protected abstract String getInterfacePath();

    protected abstract T parseJson(String json);

    protected abstract Map<String, String> getParams();

    // 如果有自定义返回头的话 就override此方法
    protected Map<String, String> getHeaders() {
        return null;
    }

    private String initUrl() {
        String url = Constants.SERVER_URL + getInterfacePath();

        StringBuilder sb = new StringBuilder(url);
        Map<String, String> params = getParams();
        if (params != null) {
            sb.append("?");
            int i = 0;
            for (Map.Entry<String, String> en : params.entrySet()) {
                String key = en.getKey();
                String value = en.getValue();

                sb.append(key);
                sb.append("=");
                sb.append(value);

                if (i != params.size() - 1) {
                    sb.append("&");
                }
                i++;
            }
        }
        return sb.toString();
    }

    public T loadData() throws Exception {
        OkHttpClient client = new OkHttpClient();

        String url = initUrl();

        Request.Builder builder = new Request.Builder().get()
                                                       .url(url);

//        添加自定义头
//        Map<String, String> headers = getHeaders();
//        if (headers != null) {
//            for (Map.Entry<String, String> entry : headers.entrySet()) {
//                String key = entry.getKey();
//                String value = entry.getValue();
//                builder.addHeader(key,value);
//            }
//        }
        Request request = builder.build();
        Response response = client.newCall(request)
                                  .execute();

        if (response.isSuccessful()) {
            String json = response.body()
                                  .string();

            return parseJson(json);
        }
        return null;
    }

}
