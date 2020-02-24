package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：SuccessBean
 * 描述：
 * 创建时间：2017-02-22  13:10
 */

public class SuccessBean {

    private String ErrMsg;
//    private SuccessObjBean ReObj;
    private int Success;

//    public SuccessObjBean getReObj() {
//        return ReObj;
//    }
//
//    public void setReObj(SuccessObjBean reObj) {
//        ReObj = reObj;
//    }

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }
}
