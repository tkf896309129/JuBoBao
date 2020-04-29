package com.example.huoshangkou.jubowan.bean;

import java.io.Serializable;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：ShopCarListBean
 * 描述：
 * 创建时间：2017-03-14  10:13
 */

public class ShopCarListBean implements Serializable{
    //用来区分是原片还是辅材 yuan  原片   fu  辅材
    private String type;
    private String BrandName;
    private String ClassName;
    private String GuigeName;
    private String HaveCount;
    private String CategoryName;
    private String Reserve;
    private String ID;
    private String LevelName;
    private String MoxiName;
    private String NameUnit;
    private String Pic;
    private String Price;
    private String SaleCount;
    private String ToNum;
    private String Weight;
    private String Xy;
    private boolean isCheck = false;

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getReserve() {
        return Reserve;
    }

    public void setReserve(String reserve) {
        Reserve = reserve;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
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

    public String getSaleCount() {
        return SaleCount;
    }

    public void setSaleCount(String saleCount) {
        SaleCount = saleCount;
    }

    public String getToNum() {
        return ToNum;
    }

    public void setToNum(String toNum) {
        ToNum = toNum;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getXy() {
        return Xy;
    }

    public void setXy(String xy) {
        Xy = xy;
    }
}
