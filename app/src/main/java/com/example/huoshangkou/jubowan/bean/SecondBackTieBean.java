package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：SecondBackTieBean
 * 描述：
 * 创建时间：2017-04-19  10:52
 */

public class SecondBackTieBean {

    private String CreateTime;
    private int ID;
    private String Nickname;
    private String Nicknamed;
    private int ParentID;
    private String RepliesText;
    private int UserID;


    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public String getNicknamed() {
        return Nicknamed;
    }

    public void setNicknamed(String nicknamed) {
        Nicknamed = nicknamed;
    }

    public int getParentID() {
        return ParentID;
    }

    public void setParentID(int parentID) {
        ParentID = parentID;
    }

    public String getRepliesText() {
        return RepliesText;
    }

    public void setRepliesText(String repliesText) {
        RepliesText = repliesText;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }
}
