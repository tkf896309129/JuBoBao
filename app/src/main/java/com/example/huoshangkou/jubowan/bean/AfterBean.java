package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：AfterBean
 * 描述：
 * 创建时间：2017-05-16  16:07
 */

public class AfterBean {

    private String ErrMsg;
    private AfterObjBean ReObj;
    private int Success;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public AfterObjBean getReObj() {
        return ReObj;
    }

    public void setReObj(AfterObjBean reObj) {
        ReObj = reObj;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }
}
