package com.lll.zk03_moni.bean;

public class Result<T> {
//    "result"
//            "message": "查询成功",
//            ""status": "0000"
    private T result;
    private String message;
    private String status;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
