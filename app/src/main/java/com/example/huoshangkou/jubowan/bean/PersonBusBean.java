package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：PersonBusBean
 * 描述：
 * 创建时间：2018-03-01  08:59
 */

public class PersonBusBean {

    private String ErrMsg;
    private PersonBusListBean ReObj;
    private int Success;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public PersonBusListBean getReObj() {
        return ReObj;
    }

    public void setReObj(PersonBusListBean reObj) {
        ReObj = reObj;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }
}
