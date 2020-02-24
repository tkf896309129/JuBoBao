package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：OrderWxBean
 * 描述：
 * 创建时间：2017-04-06  09:30
 */

public class OrderWxBean {


    private String CreateTime;
    private String OrderID;
    private List<OrderWxListBean> OrderList;
    private String addtime;


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

    public List<OrderWxListBean> getOrderList() {
        return OrderList;
    }

    public void setOrderList(List<OrderWxListBean> orderList) {
        OrderList = orderList;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }
}
