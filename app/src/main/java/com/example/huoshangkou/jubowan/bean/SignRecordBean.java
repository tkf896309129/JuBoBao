package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：SignRecordBean
 * 描述：
 * 创建时间：2017-08-01  14:11
 */

public class SignRecordBean {


    private List<SignRecordListBean> ReList;
    private int Success;

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }

    public List<SignRecordListBean> getReList() {
        return ReList;
    }

    public void setReList(List<SignRecordListBean> reList) {
        ReList = reList;
    }
}
