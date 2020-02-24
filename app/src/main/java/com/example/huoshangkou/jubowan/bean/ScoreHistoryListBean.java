package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：ScoreHistoryListBean
 * 描述：
 * 创建时间：2017-04-10  13:45
 */

public class ScoreHistoryListBean {

    private String AddorSub;
    private String CreateTime;
    private int ID;
    private int JubobiCount;
    private String Source;
    private int UserID;

    public String getAddorSub() {
        return AddorSub;
    }

    public void setAddorSub(String addorSub) {
        AddorSub = addorSub;
    }

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

    public int getJubobiCount() {
        return JubobiCount;
    }

    public void setJubobiCount(int jubobiCount) {
        JubobiCount = jubobiCount;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }
}
