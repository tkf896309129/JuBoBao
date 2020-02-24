package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：RepairApproveBean
 * 描述：获取维修认证数据
 * 创建时间：2017-03-10  09:37
 */

public class RepairApproveBean {

    private String ErrMsg;
    private RepairApproveObjBean ReObj;
    private int Success;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public RepairApproveObjBean getReObj() {
        return ReObj;
    }

    public void setReObj(RepairApproveObjBean reObj) {
        ReObj = reObj;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }
}
