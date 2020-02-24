package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：ReobjDBean
 * 描述：
 * 创建时间：2019-09-23  08:55
 */

public class ReobjDBean {


    /**
     * ErrMsg :
     * ReObj : 0
     * Success : 1
     */

    private String ErrMsg;
    private int ReObj;
    private int Success;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String ErrMsg) {
        this.ErrMsg = ErrMsg;
    }

    public int getReObj() {
        return ReObj;
    }

    public void setReObj(int ReObj) {
        this.ReObj = ReObj;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int Success) {
        this.Success = Success;
    }
}
