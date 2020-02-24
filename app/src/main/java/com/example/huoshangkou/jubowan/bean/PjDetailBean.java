package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：PjDetailBean
 * 描述：
 * 创建时间：2017-12-01  14:04
 */

public class PjDetailBean {
    private String ErrMsg;
    private PjDetailObjBean ReObj;
    private int Success;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public PjDetailObjBean getReObj() {
        return ReObj;
    }

    public void setReObj(PjDetailObjBean reObj) {
        ReObj = reObj;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }
}
