package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：LoanIntroDetailBean
 * 描述：
 * 创建时间：2017-09-19  11:00
 */

public class LoanIntroDetailBean {
    private String ErrMsg;
    private LoanIntroDetailObjBean ReObj;
    private int Success;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public LoanIntroDetailObjBean getReObj() {
        return ReObj;
    }

    public void setReObj(LoanIntroDetailObjBean reObj) {
        ReObj = reObj;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }
}
