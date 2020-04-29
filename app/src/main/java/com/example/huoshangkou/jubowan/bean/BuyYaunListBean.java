package com.example.huoshangkou.jubowan.bean;

import java.io.Serializable;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：BuyYaunListBean
 * 描述：
 * 创建时间：2017-03-03  09:56
 */

public class BuyYaunListBean implements Serializable {

    private String BrandName;
    private String ClassName;
    private int HaveCount;
    private int ID;
    private String LevelName;
    private String MoxiName;
    private String Pic;
    private String Price;
    private int SaleCount;
    private double Weight;
    private String Xy;
    private String Reserve;
    private String NameUnit;
    private String SaleNum;

    public String getSaleNum() {
        return SaleNum;
    }

    public void setSaleNum(String saleNum) {
        SaleNum = saleNum;
    }

    public String getNameUnit() {
        return NameUnit;
    }

    public void setNameUnit(String nameUnit) {
        NameUnit = nameUnit;
    }

    public String getReserve() {
        return Reserve;
    }

    public void setReserve(String reserve) {
        Reserve = reserve;
    }

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

    public int getHaveCount() {
        return HaveCount;
    }

    public void setHaveCount(int haveCount) {
        HaveCount = haveCount;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLevelName() {
        return LevelName;
    }

    public void setLevelName(String levelName) {
        LevelName = levelName;
    }

    public String getMoxiName() {
        return MoxiName;
    }

    public void setMoxiName(String moxiName) {
        MoxiName = moxiName;
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

    public int getSaleCount() {
        return SaleCount;
    }

    public void setSaleCount(int saleCount) {
        SaleCount = saleCount;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }

    public String getXy() {
        return Xy;
    }

    public void setXy(String xy) {
        Xy = xy;
    }
}
