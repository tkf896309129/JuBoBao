package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：PjListBean
 * 描述：
 * 创建时间：2017-12-01  11:13
 */

public class PjListBean {

    private String ClassID;
    private String CreateTime;
    private String Descript;
    private String ID;
    private String ModelTitle;
    private String Pic;
    private String PriceRange;




    public String getClassID() {
        return ClassID;
    }

    public void setClassID(String classID) {
        ClassID = classID;
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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getModelTitle() {
        return ModelTitle;
    }

    public void setModelTitle(String modelTitle) {
        ModelTitle = modelTitle;
    }

    public String getPic() {
        return Pic;
    }

    public void setPic(String pic) {
        Pic = pic;
    }

    public String getPriceRange() {
        return PriceRange;
    }

    public void setPriceRange(String priceRange) {
        PriceRange = priceRange;
    }
}
