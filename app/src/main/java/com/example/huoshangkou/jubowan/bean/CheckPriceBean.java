package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：CheckPriceBean
 * 描述：
 * 创建时间：2018-08-31  09:56
 */

public class CheckPriceBean {


    /**
     * ErrMsg :
     * ReObj : 0.15
     * Success : 1
     */

    private String ErrMsg;
    private String ReObj;
    private int Success;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String ErrMsg) {
        this.ErrMsg = ErrMsg;
    }

    public String getReObj() {
        return ReObj;
    }

    public void setReObj(String ReObj) {
        this.ReObj = ReObj;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int Success) {
        this.Success = Success;
    }
}
