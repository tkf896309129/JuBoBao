package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：CheckHintBean
 * 描述：
 * 创建时间：2018-06-14  17:45
 */

public class CheckHintBean {

    private String ErrMsg;
    private CheckHintObjBean ReObj;
    private int Success;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public CheckHintObjBean getReObj() {
        return ReObj;
    }

    public void setReObj(CheckHintObjBean reObj) {
        ReObj = reObj;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }
}
