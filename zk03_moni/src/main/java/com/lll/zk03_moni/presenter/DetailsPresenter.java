package com.lll.zk03_moni.presenter;


import com.lll.zk03_moni.core.BasePresenter;
import com.lll.zk03_moni.core.DataCall;
import com.lll.zk03_moni.core.IRequest;
import com.lll.zk03_moni.http.NetWork;

import java.util.List;

import io.reactivex.Observable;

public class DetailsPresenter extends BasePresenter {

    public DetailsPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    public Observable observable(Object... args) {
        IRequest iRequest = NetWork.netWork().create(IRequest.class);
        return iRequest.details((int)args[0]);
    }
}
