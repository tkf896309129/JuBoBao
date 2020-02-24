package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：UserInfoBean
 * 描述：
 * 创建时间：2017-03-07  15:27
 */

public class UserInfoBean {

    private String ErrMsg;
    private UserInfoObjBean ReObj;
    private int Success;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public UserInfoObjBean getReObj() {
        return ReObj;
    }

    public void setReObj(UserInfoObjBean reObj) {
        ReObj = reObj;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }
}
