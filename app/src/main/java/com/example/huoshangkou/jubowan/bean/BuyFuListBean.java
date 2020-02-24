package com.example.huoshangkou.jubowan.bean;

import java.io.Serializable;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：BuyFuListBean
 * 描述：
 * 创建时间：2017-03-06  13:02
 */

public class BuyFuListBean implements Serializable {

    private String BrandName;
    private String ClassName;
    private String GuigeName;
    private String HaveCount;
    private String ID;
    private String NameUnit;
    private String Pic;
    private String Price;

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getGuigeName() {
        return GuigeName;
    }

    public void setGuigeName(String guigeName) {
        GuigeName = guigeName;
    }

    public String getHaveCount() {
        return HaveCount;
    }

    public void setHaveCount(String haveCount) {
        HaveCount = haveCount;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNameUnit() {
        return NameUnit;
    }

    public void setNameUnit(String nameUnit) {
        NameUnit = nameUnit;
    }

    public String getPic() {
        return Pic;
    }

    public void setPic(String pic) {
        Pic = pic;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
