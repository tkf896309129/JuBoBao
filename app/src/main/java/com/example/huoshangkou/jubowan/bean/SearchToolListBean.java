package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：SearchToolListBean
 * 描述：
 * 创建时间：2017-06-20  11:43
 */

public class SearchToolListBean {

    private String Address;
    private String BrandName;
    private String Factory;
    private int ID;
    private String IsSaleNull;
    private String MaintainName;
    private String Pics;
    private String Time;
    private double Prices;

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public String getIsSaleNull() {
        return IsSaleNull;
    }

    public void setIsSaleNull(String isSaleNull) {
        IsSaleNull = isSaleNull;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getFactory() {
        return Factory;
    }

    public void setFactory(String factory) {
        Factory = factory;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaintainName() {
        return MaintainName;
    }

    public void setMaintainName(String maintainName) {
        MaintainName = maintainName;
    }

    public String getPics() {
        return Pics;
    }

    public void setPics(String pics) {
        Pics = pics;
    }

    public double getPrices() {
        return Prices;
    }

    public void setPrices(double prices) {
        Prices = prices;
    }
}
