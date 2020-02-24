package com.example.huoshangkou.jubowan.photo.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.photo.bean
 * 类名：ImageUrl
 * 描述：
 * 创建时间：2017-01-04  14:16
 */

public class ImageUrl {
    private PicUrl ReObj;
    private int Success;
    private String ErrMsg;

    public PicUrl getReObj() {
        return ReObj;
    }

    public void setReObj(PicUrl reObj) {
        ReObj = reObj;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }
}
