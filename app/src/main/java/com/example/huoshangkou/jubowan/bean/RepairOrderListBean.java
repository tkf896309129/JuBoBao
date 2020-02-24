package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：RepairOrderListBean
 * 描述：
 * 创建时间：2017-04-27  08:42
 */

public class RepairOrderListBean {

    private String CreateTime;
    private String OrderID;
    private List<OrderListTypeBean> OrderList;
    private int OrderState;
    private String TotalPrice;
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

    public List<OrderListTypeBean> getOrderList() {
        return OrderList;
    }

    public void setOrderList(List<OrderListTypeBean> orderList) {
        OrderList = orderList;
    }

    public int getOrderState() {
        return OrderState;
    }

    public void setOrderState(int orderState) {
        OrderState = orderState;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }
}
