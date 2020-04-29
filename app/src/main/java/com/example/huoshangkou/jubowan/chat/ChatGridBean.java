package com.example.huoshangkou.jubowan.chat;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.chat
 * 类名：ChatGridBean
 * 描述：
 * 创建时间：2020-04-09  09:08
 */

public class ChatGridBean {

    private int typeImg;
    private String type;

    public ChatGridBean(Builder builder) {
        this.typeImg = builder.typeImg;
        this.type = builder.type;
    }

    public static class Builder {
        int typeImg;
        String type;

        public Builder setTypeImg(int typeImg) {
            this.typeImg = typeImg;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public ChatGridBean build() {
            return new ChatGridBean(this);
        }
    }

    public int getTypeImg() {
        return typeImg;
    }

    public void setTypeImg(int typeImg) {
        this.typeImg = typeImg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
