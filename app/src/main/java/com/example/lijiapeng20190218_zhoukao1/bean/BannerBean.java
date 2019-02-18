package com.example.lijiapeng20190218_zhoukao1.bean;

import java.util.List;

public class BannerBean {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        public String imageUrl;
        public String jumpUrl;
        public int rank;
        public String title;
    }
}
