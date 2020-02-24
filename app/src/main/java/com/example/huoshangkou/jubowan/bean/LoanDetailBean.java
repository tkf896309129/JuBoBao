package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：LoanDetailBean
 * 描述：
 * 创建时间：2017-09-18  10:05
 */

public class LoanDetailBean {

    private String ErrMsg;
    private LoanDetailObjBean ReObj;
    private int Success;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public LoanDetailObjBean getReObj() {
        return ReObj;
    }

    public void setReObj(LoanDetailObjBean reObj) {
        ReObj = reObj;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }
}
