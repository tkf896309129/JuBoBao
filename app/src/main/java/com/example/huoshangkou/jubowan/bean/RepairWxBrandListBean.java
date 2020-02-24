package com.example.huoshangkou.jubowan.bean;

import java.io.Serializable;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：RepairWxBrandListBean
 * 描述：
 * 创建时间：2017-03-24  09:07
 */

public class RepairWxBrandListBean implements Serializable{

    private int ID;
    private int MaintainID;
    private String Name;
    private int Sort;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getMaintainID() {
        return MaintainID;
    }

    public void setMaintainID(int maintainID) {
        MaintainID = maintainID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getSort() {
        return Sort;
    }

    public void setSort(int sort) {
        Sort = sort;
    }
}
