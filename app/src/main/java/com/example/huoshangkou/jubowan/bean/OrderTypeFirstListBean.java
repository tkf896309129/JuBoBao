package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：OrderTypeFirstListBean
 * 描述：
 * 创建时间：2017-04-06  10:38
 */

public class OrderTypeFirstListBean {

    private String type;
    private String CreateTime;
    private String OrderID;
    private List<OrderTypeListBean> OrderList;
    private String addtime;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public List<OrderTypeListBean> getOrderList() {
        return OrderList;
    }

    public void setOrderList(List<OrderTypeListBean> orderList) {
        OrderList = orderList;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }
}
