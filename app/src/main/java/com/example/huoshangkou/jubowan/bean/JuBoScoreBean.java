package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：JuBoScoreBean
 * 描述：
 * 创建时间：2017-05-09  17:26
 */

public class JuBoScoreBean {

    private String ErrMsg;
    private JuBoScoreObjBean ReObj;
    private int Success;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public JuBoScoreObjBean getReObj() {
        return ReObj;
    }

    public void setReObj(JuBoScoreObjBean reObj) {
        ReObj = reObj;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }
}
