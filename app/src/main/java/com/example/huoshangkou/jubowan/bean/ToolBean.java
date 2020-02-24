package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：ToolBean
 * 描述：
 * 创建时间：2018-06-13  13:40
 */

public class ToolBean {

    private String ErrMsg;
    private int Success;
    private ToolObjBean ReObj;

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

    public ToolObjBean getReObj() {
        return ReObj;
    }

    public void setReObj(ToolObjBean reObj) {
        ReObj = reObj;
    }
}
