package com.example.lijiapeng20190218_zhoukao1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.example.lijiapeng20190218_zhoukao1.R;
import com.example.lijiapeng20190218_zhoukao1.adapter.HomeAdapter;
import com.example.lijiapeng20190218_zhoukao1.bean.BannerBean;
import com.example.lijiapeng20190218_zhoukao1.bean.ShopBean;
import com.example.lijiapeng20190218_zhoukao1.contract.ShopContract;
import com.example.lijiapeng20190218_zhoukao1.presenter.ShopPresenter;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements ShopContract.conView,HomeAdapter.onClickitem{


    private Unbinder bind;
    private ShopPresenter shopPresenter;
    @BindView(R.id.rv)
    RecyclerView rv;
    private HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind = ButterKnife.bind(this);
        initData();

    }
    private void initData() {
        shopPresenter = new ShopPresenter(this);
        shopPresenter.getBannerData();
        shopPresenter.getShopData();
        homeAdapter = new HomeAdapter(this);
        homeAdapter.setClickitem(this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(homeAdapter);
    }

    @Override
    public void shopSuccess(List<ShopBean.ResultBean.listBean> list) {

        if (list!=null){
            homeAdapter.setSlist(list);
        }

    }

    @Override
    public void bannerSuccess(BannerBean bannerBean) {

        if (bannerBean!=null){
            homeAdapter.setBlist(bannerBean.result);
        }

    }

    @Override
    public void Failure(String msg) {
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();
        }
        shopPresenter.wrUbind();
    }

    @Override
    public void getName(String name) {
        Toast.makeText(MainActivity.this,name,Toast.LENGTH_LONG).show();
    }

}
