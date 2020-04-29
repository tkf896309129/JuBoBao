package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：ZbListBean
 * 描述：
 * 创建时间：2017-04-17  13:16
 */

public class ZbListBean {

    private double Area;
    private double Area1;
    private int DayNumber;
    private String EndTime;
    private String CreateTime;
    private String ProjectType;
    private String Img;
    private String DefaultImg;
    private String ProjectTitle;
    private int RequestID;
    private int IsRequestTo;

    public String getProjectTitle() {
        return ProjectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        ProjectTitle = projectTitle;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public int getIsRequestTo() {
        return IsRequestTo;
    }

    public void setIsRequestTo(int isRequestTo) {
        IsRequestTo = isRequestTo;
    }

    public String getDefaultImg() {
        return DefaultImg;
    }

    public void setDefaultImg(String defaultImg) {
        DefaultImg = defaultImg;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public double getArea() {
        return Area;
    }

    public void setArea(double area) {
        Area = area;
    }

    public double getArea1() {
        return Area1;
    }

    public void setArea1(double area1) {
        Area1 = area1;
    }

    public int getDayNumber() {
        return DayNumber;
    }

    public void setDayNumber(int dayNumber) {
        DayNumber = dayNumber;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getProjectType() {
        return ProjectType;
    }

    public void setProjectType(String projectType) {
        ProjectType = projectType;
    }

    public int getRequestID() {
        return RequestID;
    }

    public void setRequestID(int requestID) {
        RequestID = requestID;
    }
}
