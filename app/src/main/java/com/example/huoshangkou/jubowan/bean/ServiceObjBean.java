package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：ServiceObjBean
 * 描述：
 * 创建时间：2018-06-13  15:26
 */

public class ServiceObjBean {

    private List<RepairTypeListBean> WBList;
    private List<RepairTypeListBean> WXList;

    public List<RepairTypeListBean> getWBList() {
        return WBList;
    }

    public void setWBList(List<RepairTypeListBean> WBList) {
        this.WBList = WBList;
    }

    public List<RepairTypeListBean> getWXList() {
        return WXList;
    }

    public void setWXList(List<RepairTypeListBean> WXList) {
        this.WXList = WXList;
    }
}
