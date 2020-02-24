package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：ToPayOrderListBean
 * 描述：
 * 创建时间：2017-12-14  09:40
 */

public class ToPayOrderListBean {

    private String OrderID;
    private String Price;
    private boolean isChcek = false;

    public boolean isChcek() {
        return isChcek;
    }

    public void setChcek(boolean chcek) {
        isChcek = chcek;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
