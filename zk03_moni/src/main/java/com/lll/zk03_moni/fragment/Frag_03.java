package com.lll.zk03_moni.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lll.zk03_moni.R;
import com.lll.zk03_moni.adapter.FinAdapter;
import com.lll.zk03_moni.adapter.LifAdapter;
import com.lll.zk03_moni.bean.GoodsList;
import com.lll.zk03_moni.bean.Result;
import com.lll.zk03_moni.core.DataCall;
import com.lll.zk03_moni.presenter.GoodsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Frag_03 extends Fragment {
    @BindView(R.id.recy_lif)
    RecyclerView recyLif;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_03, container, false);
        unbinder = ButterKnife.bind(this, view);
        //创建对象
        GoodsPresenter goodsPresenter = new GoodsPresenter(new GoodsCall());
        goodsPresenter.reqeust();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class GoodsCall implements DataCall<Result<GoodsList>> {
        @Override
        public void success(Result<GoodsList> result) {
            GoodsList result1 = result.getResult();
            GoodsList.PzshBean pzsh = result1.getPzsh();
            List<GoodsList.PzshBean.CommodityListBeanX> pzshcommodityList = pzsh.getCommodityList();

            //自定义适配器
            LifAdapter lifAdapter = new LifAdapter();
            lifAdapter.AddAll(pzshcommodityList);
            //设置布局
            StaggeredGridLayoutManager lif = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            recyLif.setLayoutManager(lif);
            recyLif.setAdapter(lifAdapter);
        }

        @Override
        public void fail(Result result) {
            Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
        }
    }
}
