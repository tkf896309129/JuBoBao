package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：OrderDetailBean
 * 描述：
 * 创建时间：2017-05-10  10:40
 */

public class OrderDetailBean {

    private String ErrMsg;
    private List<OrderDetailObjBean> ReList;
    private int Success;


    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public List<OrderDetailObjBean> getReList() {
        return ReList;
    }

    public void setReList(List<OrderDetailObjBean> reList) {
        ReList = reList;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }
}
