package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：CompanyBean
 * 描述：
 * 创建时间：2018-01-23  08:41
 */

public class CompanyBean {

    private String ErrMsg;

    private int Success;

    private List<CompanyListBean> ReList;

    public List<CompanyListBean> getReList() {
        return ReList;
    }

    public void setReList(List<CompanyListBean> reList) {
        ReList = reList;
    }

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
}
