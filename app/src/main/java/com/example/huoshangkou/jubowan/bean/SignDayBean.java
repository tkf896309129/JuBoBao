package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：SignDayBean
 * 描述：
 * 创建时间：2017-04-05  11:31
 */

public class SignDayBean {
    private String Address;
    private String CreateTime;
    private String PoiName;
    private String Company;
    private String LinkMan;
    private String Pic;
    private String VisitContent;
    private String Remark;
    private int VisitingObjectsId;
    private double X;
    private double Y;

    public int getVisitingObjectsId() {
        return VisitingObjectsId;
    }

    public void setVisitingObjectsId(int visitingObjectsId) {
        VisitingObjectsId = visitingObjectsId;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getLinkMan() {
        return LinkMan;
    }

    public void setLinkMan(String linkMan) {
        LinkMan = linkMan;
    }

    public String getVisitContent() {
        return VisitContent;
    }

    public void setVisitContent(String visitContent) {
        VisitContent = visitContent;
    }

    public String getPoiName() {
        return PoiName;
    }

    public void setPoiName(String poiName) {
        PoiName = poiName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getPic() {
        return Pic;
    }

    public void setPic(String pic) {
        Pic = pic;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }
}
