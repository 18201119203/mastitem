package com.example.lijiapeng20190218_zhoukao1.adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.lijiapeng20190218_zhoukao1.R;
import com.example.lijiapeng20190218_zhoukao1.bean.BannerBean;
import com.example.lijiapeng20190218_zhoukao1.bean.ShopBean;
import com.recker.flybanner.FlyBanner;
import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BannerBean.ResultBean> blist;
    private List<ShopBean.ResultBean.listBean> slist;
    private Context context;

    public HomeAdapter(Context context) {
        this.context = context;
        slist = new ArrayList<>();
        blist = new ArrayList<>();
    }

    public void setBlist(List<BannerBean.ResultBean> blist) {
        this.blist = blist;
        notifyDataSetChanged();
    }

    public void setSlist(List<ShopBean.ResultBean.listBean> slist) {
        this.slist = slist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (getItemViewType(viewType)==0){
            view = LayoutInflater.from(context).inflate(R.layout.banneritem, parent, false);
            BannerHolder bannerHolder = new BannerHolder(view);
            return bannerHolder;
        }else{
            view = LayoutInflater.from(context).inflate(R.layout.homeitem, parent, false);
            ShopHolder shopHolder = new ShopHolder(view);
            return shopHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position)==1){
            List<String> list = new ArrayList<>();
            for (BannerBean.ResultBean b : blist) {
                list.add(b.imageUrl);
            }
            ((BannerHolder)holder).banner.setImagesUrl(list);
        }else{
            ((ShopHolder) holder).rv.setLayoutManager(new LinearLayoutManager(context));
            ((ShopHolder) holder).rv.setAdapter(new DataAdapter(context,slist.get(position).commodityList));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickitem!=null){
                    clickitem.getName(slist.get(position).name);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return slist==null?0:slist.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return 0;
        }
        return 1;
    }

    

    class BannerHolder extends RecyclerView.ViewHolder{
        private FlyBanner banner;
        public BannerHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

    public class ShopHolder extends RecyclerView.ViewHolder {
        private RecyclerView rv;
        public ShopHolder(View itemView) {
            super(itemView);
            rv = itemView.findViewById(R.id.rv);
        }
    }


    private onClickitem clickitem;
    public void setClickitem(onClickitem clickitem) {
        this.clickitem = clickitem;
    }
    public interface onClickitem{
        void getName(String name);
    }

}
