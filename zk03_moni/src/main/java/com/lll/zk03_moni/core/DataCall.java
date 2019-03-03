package com.lll.zk03_moni.core;

import com.lll.zk03_moni.bean.Result;

public interface DataCall<T> {
    void success(T result);
    void fail(Result result);
}
