package com.roy_sun.googleplay_imitative.protocol;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import com.roy_sun.googleplay_imitative.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roy Sun on 2016/2/26.
 */
public class GameProtocol extends PageProtocol<List<HomeBean.AppBean>> {
    @Override
    protected String getInterfacePath() {
        return "/game";
    }

    @Override
    protected List<HomeBean.AppBean> parseJson(String json) {
//        Gson gson = new Gson();
//        /*****  泛型解析  *****/
//        return gson.fromJson(json, new TypeToken<List<HomeBean.AppBean>>() {
//        }.getType());

        /*****  节点解析  *****/
        List<HomeBean.AppBean> data = new ArrayList<>();
        // 1 创建解析器对象
        JsonParser parser = new JsonParser();

        // 2 解析json
        JsonElement rootElement = parser.parse(json);

        JsonArray root = rootElement.getAsJsonArray();

        for (int i = 0; i < root.size(); i++) {
            JsonElement element = root.get(i);
            // 3 获得具体的类型
            JsonObject jsonObj = element.getAsJsonObject();

            //des
            JsonPrimitive desJson = jsonObj.getAsJsonPrimitive("des");
            String des = desJson.getAsString();

            //downloadUrl
            JsonPrimitive downloadUrlJson = jsonObj.getAsJsonPrimitive("downloadUrl");
            String downloadUrl = downloadUrlJson.getAsString();

            //iconUrl
            JsonPrimitive iconUrlJson = jsonObj.getAsJsonPrimitive("iconUrl");
            String iconUrl = iconUrlJson.getAsString();

            // id	1642739
            JsonPrimitive idJson = jsonObj.getAsJsonPrimitive("id");
            long id = idJson.getAsLong();

            // name
            JsonPrimitive nameJson = jsonObj.getAsJsonPrimitive("name");
            String name = nameJson.getAsString();

            //  packageName
            JsonPrimitive packageNameJson = jsonObj.getAsJsonPrimitive("packageName");
            String packageName = packageNameJson.getAsString();

            //  size	9815944
            JsonPrimitive sizeJson = jsonObj.getAsJsonPrimitive("size");
            long size = sizeJson.getAsLong();

            // stars	2.5
            JsonPrimitive starsJson = jsonObj.getAsJsonPrimitive("stars");
            float stars = starsJson.getAsFloat();


            HomeBean.AppBean bean = new HomeBean.AppBean();
            bean.des = des;
            bean.downloadUrl = downloadUrl;
            bean.iconUrl = iconUrl;
            bean.id = id;
            bean.name = name;
            bean.packageName = packageName;
            bean.size = size;
            bean.stars = stars;


            data.add(bean);

        }
        return data;
    }
}
