package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：IndexBean
 * 描述：
 * 创建时间：2019-10-25  09:22
 */

public class IndexBean {

    private int index;
    private String str;

    public IndexBean() {
    }

    public IndexBean(int index, String str) {
        this.index = index;
        this.str = str;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
