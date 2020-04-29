package com.example.huoshangkou.jubowan.chat.mine;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.chat.mine
 * 类名：MineMessageBean
 * 描述：
 * 创建时间：2020-04-22  09:13
 */

public class MineMessageBean {
    private int chatType;
    private String title;
    private String lastMessage;
    private long time;
    private String pic;
    private String chatId;
    private boolean isGroup;
    private long unRead;


    public MineMessageBean (Builder builder){
        this.chatType = builder.chatType;
        this.title = builder.title;
        this.lastMessage = builder.lastMessage;
        this.time = builder.time;
        this.pic = builder.pic;
        this.chatId = builder.chatId;
        this.isGroup = builder.isGroup;
        this.unRead = builder.unRead;
    }


    public static class Builder {
        int chatType;
        String title;
        String lastMessage;
        long time;
        long unRead;
        String pic;
        String chatId;
        boolean isGroup;

        public Builder setChatType(int chatType) {
            this.chatType = chatType;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setLastMessage(String lastMessage) {
            this.lastMessage = lastMessage;
            return this;
        }

        public Builder setTime(long time) {
            this.time = time;
            return this;
        }

        public Builder setPic(String pic) {
            this.pic = pic;
            return this;
        }

        public Builder setChatId(String chatId) {
            this.chatId = chatId;
            return this;
        }

        public Builder setGroup(boolean group) {
            isGroup = group;
            return this;
        }

        public Builder setUnRead(long unRead) {
            this.unRead = unRead;
            return this;
        }

        public MineMessageBean build() {
            return new MineMessageBean(this);
        }
    }

    public boolean isGroup() {
        return isGroup;
    }

    public long getUnRead() {
        return unRead;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    public int getChatType() {
        return chatType;
    }

    public void setChatType(int chatType) {
        this.chatType = chatType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
}
