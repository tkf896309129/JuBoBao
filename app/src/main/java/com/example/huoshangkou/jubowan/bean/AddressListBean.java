package com.example.huoshangkou.jubowan.bean;

import java.io.Serializable;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：AddressListBean
 * 描述：
 * 创建时间：2017-02-22  10:28
 */

public class AddressListBean implements Serializable {


    private int AdressID;
    private boolean DefaultAddress;
    private String DetailAddress;
    private String LinkMan;
    private String LinkTel;
    private String Provinces;


    public int getAdressID() {
        return AdressID;
    }

    public void setAdressID(int adressID) {
        AdressID = adressID;
    }

    public boolean isDefaultAddress() {
        return DefaultAddress;
    }

    public void setDefaultAddress(boolean defaultAddress) {
        DefaultAddress = defaultAddress;
    }

    public String getDetailAddress() {
        return DetailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        DetailAddress = detailAddress;
    }

    public String getLinkMan() {
        return LinkMan;
    }

    public void setLinkMan(String linkMan) {
        LinkMan = linkMan;
    }

    public String getLinkTel() {
        return LinkTel;
    }

    public void setLinkTel(String linkTel) {
        LinkTel = linkTel;
    }

    public String getProvinces() {
        return Provinces;
    }

    public void setProvinces(String provinces) {
        Provinces = provinces;
    }
}
