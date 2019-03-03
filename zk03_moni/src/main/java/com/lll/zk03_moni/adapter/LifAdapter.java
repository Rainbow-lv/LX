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

public class LifAdapter extends RecyclerView.Adapter<LifAdapter.MyHolder> {
    List<GoodsList.PzshBean.CommodityListBeanX> list = new ArrayList();
    public void AddAll(List<GoodsList.PzshBean.CommodityListBeanX> pzshcommodityList) {
        if (pzshcommodityList != null){
            list.addAll(pzshcommodityList);
        }
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lif_layout, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, final int i) {
        final GoodsList.PzshBean.CommodityListBeanX commodityListBeanX = list.get(i);
        myHolder.sim_image.setImageURI(commodityListBeanX.getMasterPic());
        myHolder.tv_name.setText(commodityListBeanX.getCommodityName());
        myHolder.tv_price.setText("￥"+commodityListBeanX.getPrice());
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myHolder.itemView.getContext(),DetilsActivity.class);
                int id = list.get(i).getCommodityId();
                intent.putExtra("commodityId",id);
                myHolder.itemView.getContext().startActivity(intent);
               /* Intent intent = new Intent(myHolder.itemView.getContext(), DetilsActivity.class);
                intent.putExtra("commodityId", commodityListBeanX.getCommodityId());
                myHolder.itemView.getContext().startActivity(intent);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }




    public class MyHolder extends RecyclerView.ViewHolder {

        private final TextView tv_name;
        private final SimpleDraweeView sim_image;
        private final TextView tv_price;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            sim_image = itemView.findViewById(R.id.sim_image);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
        }
    }
}
