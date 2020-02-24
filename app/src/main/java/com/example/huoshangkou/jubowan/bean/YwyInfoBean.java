package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：YwyInfoBean
 * 描述：
 * 创建时间：2017-03-09  15:24
 */

public class YwyInfoBean {

    private String ErrMsg;
    private YwyInfoObjBean ReObj;
    private int Success;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public YwyInfoObjBean getReObj() {
        return ReObj;
    }

    public void setReObj(YwyInfoObjBean reObj) {
        ReObj = reObj;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }
}
