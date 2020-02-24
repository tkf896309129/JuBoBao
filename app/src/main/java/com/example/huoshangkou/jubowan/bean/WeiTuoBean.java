package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：WeiTuoBean
 * 描述：
 * 创建时间：2018-04-19  08:45
 */

public class WeiTuoBean {

    private String ErrMsg;
    private WeiTuoReobjBean ReObj;
    private int Success;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public WeiTuoReobjBean getReObj() {
        return ReObj;
    }

    public void setReObj(WeiTuoReobjBean reObj) {
        ReObj = reObj;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }
}
