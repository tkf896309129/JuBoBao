package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：WeiTuoReobjBean
 * 描述：
 * 创建时间：2018-04-19  08:46
 */

public class WeiTuoReobjBean {

    private String CreateTime;
    private String DaoHuo;
    private String DeCompany;
    private List<WeiTuoListDetailsBean> Details;
    private String DriverChePai;
    private String DriverName;
    private String DriverTel;
    private String OrderID;
    private String TiHuo;

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getDaoHuo() {
        return DaoHuo;
    }

    public void setDaoHuo(String daoHuo) {
        DaoHuo = daoHuo;
    }

    public String getDeCompany() {
        return DeCompany;
    }

    public void setDeCompany(String deCompany) {
        DeCompany = deCompany;
    }

    public List<WeiTuoListDetailsBean> getDetails() {
        return Details;
    }

    public void setDetails(List<WeiTuoListDetailsBean> details) {
        Details = details;
    }

    public String getDriverChePai() {
        return DriverChePai;
    }

    public void setDriverChePai(String driverChePai) {
        DriverChePai = driverChePai;
    }

    public String getDriverName() {
        return DriverName;
    }

    public void setDriverName(String driverName) {
        DriverName = driverName;
    }

    public String getDriverTel() {
        return DriverTel;
    }

    public void setDriverTel(String driverTel) {
        DriverTel = driverTel;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getTiHuo() {
        return TiHuo;
    }

    public void setTiHuo(String tiHuo) {
        TiHuo = tiHuo;
    }
}
