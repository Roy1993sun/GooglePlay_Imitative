package com.roy_sun.googleplay_imitative.bean;

import java.util.List;

/**
 * 首页的item数据
 * Created by Roy_Sun on 2016/2/11 0011.
 */
public class HomeBean {
    public List<AppBean> list;
    public List<String>  picture;

    public class AppBean {
        public String des;
        public String downloadUrl;
        public String iconUrl;
        public long   id;
        public String name;
        public String packageName;
        public long size;
        public float  stars;
    }
}
