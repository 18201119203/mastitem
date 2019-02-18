package com.example.lijiapeng20190218_zhoukao1.contract;

import com.example.lijiapeng20190218_zhoukao1.bean.BannerBean;
import com.example.lijiapeng20190218_zhoukao1.bean.ShopBean;
import com.example.lijiapeng20190218_zhoukao1.model.ShopModel;

import java.util.List;

public interface ShopContract {

    abstract class conPresenter{
        public abstract void getBannerData();
        public abstract void getShopData();
    }

    abstract class conModel{

        public abstract void getBannerData(ShopModel.getShopData callback);
        public abstract void getShopData(ShopModel.getShopData callback);
    }

    interface conView{
        void shopSuccess(List<ShopBean.ResultBean.listBean> shopBean);
        void bannerSuccess(BannerBean bannerBean);
        void Failure(String msg);
    }


}
