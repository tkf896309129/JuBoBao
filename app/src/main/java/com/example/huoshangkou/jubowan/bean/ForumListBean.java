package com.example.huoshangkou.jubowan.bean;

import java.io.Serializable;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：ForumListBean
 * 描述：
 * 创建时间：2017-04-10  08:54
 */

public class ForumListBean implements Serializable {

    private String CreateTime;
    private int GiveUpCount;
    private int GiveUpState;
    private int ID;
    private int JobobiScore;
    private String Nickname;
    private int PageView;
    private String Pics;
    private int PostState;
    private String PostText;
    private String PostTitle;
    private int PostTypeID;
    private int Replies;
    private String RepliesList;
    private int UserID;
    private String UserPic;
    private String PhoneType;

    public int getGiveUpCount() {
        return GiveUpCount;
    }

    public void setGiveUpCount(int giveUpCount) {
        GiveUpCount = giveUpCount;
    }

    public int getGiveUpState() {
        return GiveUpState;
    }

    public void setGiveUpState(int giveUpState) {
        GiveUpState = giveUpState;
    }

    public String getPhoneType() {
        return PhoneType;
    }

    public void setPhoneType(String phoneType) {
        PhoneType = phoneType;
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

    public int getJobobiScore() {
        return JobobiScore;
    }

    public void setJobobiScore(int jobobiScore) {
        JobobiScore = jobobiScore;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
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

    public String getRepliesList() {
        return RepliesList;
    }

    public void setRepliesList(String repliesList) {
        RepliesList = repliesList;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUserPic() {
        return UserPic;
    }

    public void setUserPic(String userPic) {
        UserPic = userPic;
    }
}
