package com.example.lijiapeng20190218_zhoukao1.presenter;

import com.example.lijiapeng20190218_zhoukao1.bean.BannerBean;
import com.example.lijiapeng20190218_zhoukao1.bean.ShopBean;
import com.example.lijiapeng20190218_zhoukao1.contract.ShopContract;
import com.example.lijiapeng20190218_zhoukao1.model.ShopModel;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ShopPresenter extends ShopContract.conPresenter {


    private ShopContract.conView view;
    private ShopModel model;
    private WeakReference<ShopContract.conView> wr;

    public ShopPresenter(ShopContract.conView view) {
        wr = new WeakReference<>(view);
        this.view = wr.get();
        model = new ShopModel();
    }

    @Override
    public void getBannerData() {

        if (model!=null){
            model.getBannerData(new ShopModel.getShopData() {
                @Override
                public void Success(String respomse) {
                    if (view!=null){
                        BannerBean bannerBean = new Gson().fromJson(respomse, BannerBean.class);
                        view.bannerSuccess(bannerBean);
                    }
                }

                @Override
                public void Failure(String msg) {
                    if (view!=null){
                        view.Failure(msg);
                    }
                }
            });
        }

    }

    @Override
    public void getShopData() {
        model.getShopData(new ShopModel.getShopData() {
            @Override
            public void Success(String respomse) {
                if (view!=null){
                    ShopBean shopBean = new Gson().fromJson(respomse, ShopBean.class);

                    List<ShopBean.ResultBean.listBean> list = new ArrayList<>();

                    list.addAll(shopBean.result.mlss);
                    list.addAll(shopBean.result.pzsh);
                    list.addAll(shopBean.result.rxxp);

                    view.shopSuccess(list);
                }
            }

            @Override
            public void Failure(String msg) {
                if (view!=null){
                    view.Failure(msg);
                }
            }
        });
    }

    public void wrUbind(){
        if (wr!=null){
            wr.clear();
            wr=null;
            view=null;
        }
    }


}
