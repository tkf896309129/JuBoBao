package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：RepairObjBean
 * 描述：
 * 创建时间：2017-03-02  14:04
 */

public class RepairObjBean {

    private boolean isCheck =false;
    private String CreateTime;
    private String Descript;
    private int ID;
    private String Name;
    private String Pic;
    private String WxBrandList;
    private String WxMaintainTypeList;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getWxBrandList() {
        return WxBrandList;
    }

    public void setWxBrandList(String wxBrandList) {
        WxBrandList = wxBrandList;
    }

    public String getWxMaintainTypeList() {
        return WxMaintainTypeList;
    }

    public void setWxMaintainTypeList(String wxMaintainTypeList) {
        WxMaintainTypeList = wxMaintainTypeList;
    }
}
