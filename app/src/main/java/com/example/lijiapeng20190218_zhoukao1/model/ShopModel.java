package com.example.lijiapeng20190218_zhoukao1.model;

import com.example.lijiapeng20190218_zhoukao1.api.Api;
import com.example.lijiapeng20190218_zhoukao1.contract.ShopContract;
import com.example.lijiapeng20190218_zhoukao1.retrofitutils.RetrofitCallback;
import com.example.lijiapeng20190218_zhoukao1.retrofitutils.RetrofitUtils;

public class ShopModel extends ShopContract.conModel {


    @Override
    public void getBannerData(final getShopData callback) {
        RetrofitUtils.initRetrofitUtils().doGet(Api.BannerUrl, new RetrofitCallback() {
            @Override
            public void Success(String response) {
                if (callback!=null){
                    callback.Success(response);
                }
            }

            @Override
            public void Failure(String msg) {
                if (callback!=null){
                    callback.Failure(msg);
                }
            }
        });
    }

    @Override
    public void getShopData(final getShopData callback) {
        RetrofitUtils.initRetrofitUtils().doGet(Api.ShopListUrl, new RetrofitCallback() {
            @Override
            public void Success(String response) {
                if (callback!=null){
                    callback.Success(response);
                }
            }

            @Override
            public void Failure(String msg) {
                if (callback!=null){
                    callback.Failure(msg);
                }
            }
        });
    }

    public interface getShopData{
        void Success(String respomse);
        void Failure(String msg);
    }
}
