package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：ApproveTypeListBean
 * 描述：
 * 创建时间：2017-03-09  14:53
 */

public class ApproveTypeListBean {
    private int Renzhengid;
    private int id;
    private String type;

    public int getRenzhengid() {
        return Renzhengid;
    }

    public void setRenzhengid(int renzhengid) {
        Renzhengid = renzhengid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
