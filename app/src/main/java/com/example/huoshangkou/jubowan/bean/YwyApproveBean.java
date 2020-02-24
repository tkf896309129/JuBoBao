package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：YwyApproveBean
 * 描述：
 * 创建时间：2017-03-08  15:07
 */

public class YwyApproveBean {
    private String ErrMsg;
    private YwyApproveObjBean ReObj;
    private int Success;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public YwyApproveObjBean getReObj() {
        return ReObj;
    }

    public void setReObj(YwyApproveObjBean reObj) {
        ReObj = reObj;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }
}
