package com.lll.zk03_moni.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lll.zk03_moni.DetilsActivity;
import com.lll.zk03_moni.R;
import com.lll.zk03_moni.bean.GoodsList;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyHolder> {
    List<GoodsList.RxxpBean.CommodityListBean> list = new ArrayList();
    public void AddAll(List<GoodsList.RxxpBean.CommodityListBean> rxxpcommodityList) {
        if (rxxpcommodityList != null){
            list.addAll(rxxpcommodityList);
        }
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_layout, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, int i) {
        final GoodsList.RxxpBean.CommodityListBean commodityListBean = list.get(i);
        myHolder.news_image.setImageURI(commodityListBean.getMasterPic());
        myHolder.news_name.setText(commodityListBean.getCommodityName());
        myHolder.news_price.setText("￥"+commodityListBean.getPrice());
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myHolder.itemView.getContext(), DetilsActivity.class);
                intent.putExtra("commodityId", commodityListBean.getCommodityId());
                myHolder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView news_image;
        private final TextView news_name;
        private final TextView news_price;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            news_image = itemView.findViewById(R.id.news_image);
            news_name = itemView.findViewById(R.id.news_name);
            news_price = itemView.findViewById(R.id.news_price);
        }
    }
}
