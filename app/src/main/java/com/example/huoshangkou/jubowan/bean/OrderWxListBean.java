package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：OrderWxListBean
 * 描述：
 * 创建时间：2017-04-06  09:40
 */

public class OrderWxListBean {

    private String BrandName;
    private String BuyDate;
    private String Descript;
    private String MaintainName;

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public String getBuyDate() {
        return BuyDate;
    }

    public void setBuyDate(String buyDate) {
        BuyDate = buyDate;
    }

    public String getDescript() {
        return Descript;
    }

    public void setDescript(String descript) {
        Descript = descript;
    }

    public String getMaintainName() {
        return MaintainName;
    }

    public void setMaintainName(String maintainName) {
        MaintainName = maintainName;
    }
}
