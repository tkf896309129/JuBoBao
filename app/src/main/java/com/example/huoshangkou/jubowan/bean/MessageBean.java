package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：MessageBean
 * 描述：
 * 创建时间：2017-04-21  09:37
 */

public class MessageBean {

    private int image;
    private String messageType;

    public MessageBean(Builder builder) {
        this.image = builder.img;
        this.messageType = builder.messageType;
    }

    public int getImage() {
        return image;
    }

    public String getMessageType() {
        return messageType;
    }

    public static class Builder {

        private int img;
        private String messageType;


        public Builder setImg(int img) {
            this.img = img;
            return this;
        }

        public Builder setMessageType(String messageType) {
            this.messageType = messageType;
            return this;
        }

        public MessageBean build() {
            return new MessageBean(this);
        }

    }

}
