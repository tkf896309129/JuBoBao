package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：BuyInfoBean
 * 描述：
 * 创建时间：2017-03-10  08:50
 */

public class BuyInfoBean {


    private String ErrMsg;
    private BuyInfoObjBean ReObj;
    private int Success;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public BuyInfoObjBean getReObj() {
        return ReObj;
    }

    public void setReObj(BuyInfoObjBean reObj) {
        ReObj = reObj;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }
}
