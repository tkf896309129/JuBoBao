package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：RedPacketBean
 * 描述：
 * 创建时间：2017-10-18  15:30
 */

public class RedPacketBean {

    private String ErrMsg;
    private RedPacketPriceBean ReObj;
    private int Success;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public RedPacketPriceBean getReObj() {
        return ReObj;
    }

    public void setReObj(RedPacketPriceBean reObj) {
        ReObj = reObj;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }
}
