package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：CheckApplyBean
 * 描述：
 * 创建时间：2017-03-20  08:52
 */

public class CheckApplyBean {

    private int imgType;
    private String type;
    private int typeId;

    public int getImgType() {
        return imgType;
    }

    public CheckApplyBean setImgType(int imgType) {
        this.imgType = imgType;
        return this;
    }

    public String getType() {
        return type;
    }

    public CheckApplyBean setType(String type) {
        this.type = type;
        return this;
    }

    public int getTypeId() {
        return typeId;
    }

    public CheckApplyBean setTypeId(int typeId) {
        this.typeId = typeId;
        return this;
    }


    public abstract class Builder {
        public CheckApplyBean applyBean = new CheckApplyBean();

        public abstract void setImgType(int imageType);

        public abstract void setType(String type);

        public abstract void setTypeId(int typeId);
    }
}
