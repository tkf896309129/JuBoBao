package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：ServiceRepairBean
 * 描述：
 * 创建时间：2018-06-13  15:26
 */

public class ServiceRepairBean {



    private String ErrMsg;
    private int Success;
    private ServiceObjBean ReObj;

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

    public ServiceObjBean getReObj() {
        return ReObj;
    }

    public void setReObj(ServiceObjBean reObj) {
        ReObj = reObj;
    }
}
