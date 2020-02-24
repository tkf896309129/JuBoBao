package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：PjOrderListBean
 * 描述：
 * 创建时间：2017-12-01  13:37
 */

public class PjOrderListBean {

    private List<GuiGeListBean> GuiGeList;
    private String Name;
    private String Pic;
    private String Price;
    private String ProductID;
    private String ModelTitle;
    private String ClassTitle;
    private String CreateTime;
    private String Descript;
    private String GuiGeVal;
    private String Voltage;
    private List<VAListBean> VAList;

    public String getModelTitle() {
        return ModelTitle;
    }

    public void setModelTitle(String modelTitle) {
        ModelTitle = modelTitle;
    }

    public String getClassTitle() {
        return ClassTitle;
    }

    public void setClassTitle(String classTitle) {
        ClassTitle = classTitle;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getDescript() {
        return Descript;
    }

    public void setDescript(String descript) {
        Descript = descript;
    }

    public String getGuiGeVal() {
        return GuiGeVal;
    }

    public void setGuiGeVal(String guiGeVal) {
        GuiGeVal = guiGeVal;
    }

    public String getVoltage() {
        return Voltage;
    }

    public void setVoltage(String voltage) {
        Voltage = voltage;
    }

    public List<GuiGeListBean> getGuiGeList() {
        return GuiGeList;
    }

    public void setGuiGeList(List<GuiGeListBean> guiGeList) {
        GuiGeList = guiGeList;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public List<VAListBean> getVAList() {
        return VAList;
    }

    public void setVAList(List<VAListBean> VAList) {
        this.VAList = VAList;
    }
}
