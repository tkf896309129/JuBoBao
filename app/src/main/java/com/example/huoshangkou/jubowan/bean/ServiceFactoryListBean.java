package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：ServiceFactoryListBean
 * 描述：
 * 创建时间：2018-06-13  10:27
 */

public class ServiceFactoryListBean {
    private int ID;
    private String Name;
    private String Pic;
    private String DetailsUrl;

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

    public String getDetailsUrl() {
        return DetailsUrl;
    }

    public void setDetailsUrl(String detailsUrl) {
        DetailsUrl = detailsUrl;
    }
}
