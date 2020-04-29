package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：GonYiListBean
 * 描述：
 * 创建时间：2017-04-12  09:50
 */

public class GonYiListBean {

    private int ID;
    private String Title;
    private String content;
    private int ToPrice;
    private boolean isCheck = false;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getToPrice() {
        return ToPrice;
    }

    public void setToPrice(int toPrice) {
        ToPrice = toPrice;
    }
}
