package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：PjOrderBean
 * 描述：
 * 创建时间：2017-12-01  13:15
 */

public class PjOrderBean {
    private String ErrMsg;
    private PjOrderListBean ReObj;
    private int Success;


    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }


    public PjOrderListBean getReObj() {
        return ReObj;
    }

    public void setReObj(PjOrderListBean reObj) {
        ReObj = reObj;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }

}
