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

import com.lll.zk03_moni.R;
import com.lll.zk03_moni.adapter.FinAdapter;
import com.lll.zk03_moni.bean.GoodsList;
import com.lll.zk03_moni.bean.Result;
import com.lll.zk03_moni.core.DataCall;
import com.lll.zk03_moni.presenter.GoodsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Frag_02 extends Fragment {
    @BindView(R.id.recy_fin)
    RecyclerView recyFin;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_02, container, false);
        //创建对象
        GoodsPresenter goodsPresenter = new GoodsPresenter(new GoodsCall());
        goodsPresenter.reqeust();
        unbinder = ButterKnife.bind(this, view);
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
            GoodsList.MlssBean mlss = result1.getMlss();
            List<GoodsList.MlssBean.CommodityListBeanXX> mlsscommodityList = mlss.getCommodityList();

            //自定义适配器
            FinAdapter finAdapter = new FinAdapter();
            finAdapter.AddAll(mlsscommodityList);
            //设置布局
            StaggeredGridLayoutManager fin = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            recyFin.setLayoutManager(fin);
            recyFin.setAdapter(finAdapter);
        }

        @Override
        public void fail(Result result) {

        }
    }
}
