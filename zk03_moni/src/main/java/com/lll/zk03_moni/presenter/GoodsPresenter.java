package com.lll.zk03_moni.presenter;

import com.lll.zk03_moni.core.BasePresenter;
import com.lll.zk03_moni.core.DataCall;
import com.lll.zk03_moni.core.IRequest;
import com.lll.zk03_moni.http.NetWork;

import io.reactivex.Observable;

public class GoodsPresenter extends BasePresenter {
    public GoodsPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    public Observable observable(Object... args) {
        IRequest iRequest = NetWork.netWork().create(IRequest.class);
        return iRequest.getlist();
    }
}
