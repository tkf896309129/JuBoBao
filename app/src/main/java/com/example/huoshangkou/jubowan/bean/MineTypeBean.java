package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：MineTypeBean
 * 描述：个人中心的封装属性类
 * 创建时间：2017-02-06  09:25
 */

public class MineTypeBean {

    private int imgRes;
    private String mineType;
    private int intType;
    private int type;

    public MineTypeBean(){

    }

    public MineTypeBean(String mineType){
        this.mineType = mineType;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getMineType() {
        return mineType;
    }

    public void setMineType(String mineType) {
        this.mineType = mineType;
    }

    public int getIntType() {
        return intType;
    }

    public void setIntType(int intType) {
        this.intType = intType;
    }

    public boolean equals(Object obj) {
        MineTypeBean typeBean = (MineTypeBean) obj;
        return mineType.equals(typeBean.getMineType());
    }
}
