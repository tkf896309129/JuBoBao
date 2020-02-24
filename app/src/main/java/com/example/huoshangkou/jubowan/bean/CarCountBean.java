package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：CarCountBean
 * 描述：
 * 创建时间：2017-03-16  14:44
 */

public class CarCountBean {

    private String ErrMsg;
    private SuccessObjBean ReObj;
    private int Success;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public SuccessObjBean getReObj() {
        return ReObj;
    }

    public void setReObj(SuccessObjBean reObj) {
        ReObj = reObj;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }
}
