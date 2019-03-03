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

import com.lll.zk03_moni.adapter.NewsAdapter;
import com.lll.zk03_moni.R;
import com.lll.zk03_moni.bean.GoodsList;
import com.lll.zk03_moni.bean.Result;
import com.lll.zk03_moni.core.DataCall;
import com.lll.zk03_moni.presenter.GoodsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Frag_01 extends Fragment {
    @BindView(R.id.recy_news)
    RecyclerView recyNews;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_01, container, false);
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
            GoodsList.RxxpBean rxxp = result1.getRxxp();
            List<GoodsList.RxxpBean.CommodityListBean> rxxpcommodityList = rxxp.getCommodityList();

            //自定义适配器
            NewsAdapter newsAdapter = new NewsAdapter();
            newsAdapter.AddAll(rxxpcommodityList);
            //设置布局
            StaggeredGridLayoutManager news = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
            recyNews.setLayoutManager(news);
            recyNews.setAdapter(newsAdapter);
        }

        @Override
        public void fail(Result result) {
            Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
        }
    }
}
