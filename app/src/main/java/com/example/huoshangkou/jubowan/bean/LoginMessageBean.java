package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：LoginMessageBean
 * 描述：登录信息返回
 * 创建时间：2017-02-28  09:12
 */

public class LoginMessageBean {

    private String ErrMsg;
    private LoginMessageObjBean ReObj;
    private int Success;


    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public LoginMessageObjBean getReObj() {
        return ReObj;
    }

    public void setReObj(LoginMessageObjBean reObj) {
        ReObj = reObj;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }
}
