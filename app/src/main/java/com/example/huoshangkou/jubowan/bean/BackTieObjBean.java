package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：BackTieObjBean
 * 描述：
 * 创建时间：2017-04-14  10:01
 */

public class BackTieObjBean {

    private String CreateTime;
    private String EndTime;
    private int ID;
    private int JobobiScore;
    private int PageView;
    private String Pics;
    private int PostState;
    private String PostText;
    private String PostTitle;
    private int PostTypeID;
    private int Replies;
    private int UserID;

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getJobobiScore() {
        return JobobiScore;
    }

    public void setJobobiScore(int jobobiScore) {
        JobobiScore = jobobiScore;
    }

    public int getPageView() {
        return PageView;
    }

    public void setPageView(int pageView) {
        PageView = pageView;
    }

    public String getPics() {
        return Pics;
    }

    public void setPics(String pics) {
        Pics = pics;
    }

    public int getPostState() {
        return PostState;
    }

    public void setPostState(int postState) {
        PostState = postState;
    }

    public String getPostText() {
        return PostText;
    }

    public void setPostText(String postText) {
        PostText = postText;
    }

    public String getPostTitle() {
        return PostTitle;
    }

    public void setPostTitle(String postTitle) {
        PostTitle = postTitle;
    }

    public int getPostTypeID() {
        return PostTypeID;
    }

    public void setPostTypeID(int postTypeID) {
        PostTypeID = postTypeID;
    }

    public int getReplies() {
        return Replies;
    }

    public void setReplies(int replies) {
        Replies = replies;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }
}
