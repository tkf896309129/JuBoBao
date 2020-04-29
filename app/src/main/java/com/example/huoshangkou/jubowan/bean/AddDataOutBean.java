package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：AddDataOutBean
 * 描述：
 * 创建时间：2019-09-02  15:36
 */

public class AddDataOutBean {


    /**
     * d : {"repeate":[{"Type":0,"Name":"一次性"},{"Type":1,"Name":"每天"},{"Type":2,"Name":"每周"},{"Type":3,"Name":"每月"},{"Type":4,"Name":"工作日(周一至周五)"}],"reminder":[{"Type":0,"Name":"准时"},{"Type":5,"Name":"5分钟前"},{"Type":15,"Name":"15分钟前"},{"Type":30,"Name":"30分钟前"},{"Type":60,"Name":"1小时前"},{"Type":120,"Name":"2小时前"},{"Type":1440,"Name":"1天前"},{"Type":4320,"Name":"3天前"},{"Type":10080,"Name":"1周前"}],"endRepeate":[{"Type":0,"Name":"永不"},{"Type":1,"Name":"时间"},{"Type":2,"Name":"次数"},{"Type":-1,"Name":"无"}]}
     */

    private String d;

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }
}
