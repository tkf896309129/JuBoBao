package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：BaseCheckBean
 * 描述：17.9亿股 1790w*1400
 * 创建时间：2019-11-27  09:30
 */

public class BaseCheckBean {
    //ui类型
    private int type;
    //选择类型 时间/账户等
    private String chooseType;
    //左边提示内容
    private String hintType;
    //上传内容
    private String content;
    //上传字段
    private String interfaceKey;
    private boolean isMust;
    //文本类型 1 数字 2 通用
    private int editType = 2;
    //是否隐藏
    private boolean isHide = false;
    //账户字段
    private String accountKey;

    public BaseCheckBean(int type, String chooseType, String hintType, String content, String interfaceKey, boolean isMust, int editType, boolean isHide, String accountKey) {
        this.type = type;
        this.chooseType = chooseType;
        this.hintType = hintType;
        this.content = content;
        this.interfaceKey = interfaceKey;
        this.isMust = isMust;
        this.editType = editType;
        this.isHide = isHide;
        this.accountKey = accountKey;
    }

    public BaseCheckBean(){

    }


    public String getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
    }

    public boolean isHide() {
        return isHide;
    }

    public void setHide(boolean hide) {
        isHide = hide;
    }

    public int getEditType() {
        return editType;
    }

    public void setEditType(int editType) {
        this.editType = editType;
    }

    public boolean isMust() {
        return isMust;
    }

    public void setMust(boolean must) {
        isMust = must;
    }

    public String getInterfaceKey() {
        return interfaceKey;
    }

    public void setInterfaceKey(String interfaceKey) {
        this.interfaceKey = interfaceKey;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHintType() {
        return hintType;
    }

    public void setHintType(String hintType) {
        this.hintType = hintType;
    }

    public String getChooseType() {
        return chooseType;
    }

    public void setChooseType(String chooseType) {
        this.chooseType = chooseType;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
