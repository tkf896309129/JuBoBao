package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：OrderFlListBean
 * 描述：
 * 创建时间：2017-04-06  09:34
 */

public class OrderFlListBean {

    private String BrandName;
    private String ClassName;
    private String Count;
    private String OnePrice;
    private String Pic;
    private String Xy;
    private double wlprice;

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

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }

    public String getOnePrice() {
        return OnePrice;
    }

    public void setOnePrice(String onePrice) {
        OnePrice = onePrice;
    }

    public String getPic() {
        return Pic;
    }

    public void setPic(String pic) {
        Pic = pic;
    }

    public String getXy() {
        return Xy;
    }

    public void setXy(String xy) {
        Xy = xy;
    }

    public double getWlprice() {
        return wlprice;
    }

    public void setWlprice(double wlprice) {
        this.wlprice = wlprice;
    }
}
