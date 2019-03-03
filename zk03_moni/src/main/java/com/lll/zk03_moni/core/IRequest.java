package com.lll.zk03_moni.core;

import com.lll.zk03_moni.bean.DetailsBean;
import com.lll.zk03_moni.bean.GoodsBanner;
import com.lll.zk03_moni.bean.GoodsList;
import com.lll.zk03_moni.bean.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface IRequest {

    /**
     * 轮播图
     * @return
     */
    @GET("small/commodity/v1/bannerShow")
    Observable<Result<List<GoodsBanner>>> banner();

    /**
     * 首页列表
     * @return
     */
     @GET("small/commodity/v1/commodityList")
    Observable<Result<GoodsList>> getlist();

    /**
     * 商品详情
     * @param commodityId
     * @return
     */
    @GET("small/commodity/v1/findCommodityDetailsById")
    Observable<Result<DetailsBean>> details(@Query("commodityId")int commodityId);
}
