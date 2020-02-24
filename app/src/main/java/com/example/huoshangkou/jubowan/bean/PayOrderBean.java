package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：PayOrderBean
 * 描述：
 * 创建时间：2017-03-23  15:41
 */

public class PayOrderBean {
    private int imageType;
    private String bankType;
    private String bankIntro;
    private int type;
    private boolean isCheck = false;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getImageType() {
        return imageType;
    }

    public void setImageType(int imageType) {
        this.imageType = imageType;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getBankIntro() {
        return bankIntro;
    }

    public void setBankIntro(String bankIntro) {
        this.bankIntro = bankIntro;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
