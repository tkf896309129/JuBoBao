package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：RepliesListBean
 * 描述：
 * 创建时间：2017-04-11  13:05
 */

public class RepliesListBean {

    private String CreateTime;
    private int GiveUpCount;
    private int GiveUpState;
    private int ID;
    private String Nickname;
    private int PostID;
    private BackTieObjBean PostMode;
    private String PostNickname;
    private String PostTitle;
    private int PostTypeID;
    private String PostUserPic;
    private int RepliesState;
    private String RepliesText;
    private int UserID;
    private String UserPic;
    private int floorID;
    private List<SecondBackTieBean> reGetLTRepliesToo;


    public int getPostTypeID() {
        return PostTypeID;
    }

    public void setPostTypeID(int postTypeID) {
        PostTypeID = postTypeID;
    }

    public List<SecondBackTieBean> getReGetLTRepliesToo() {
        return reGetLTRepliesToo;
    }

    public void setReGetLTRepliesToo(List<SecondBackTieBean> reGetLTRepliesToo) {
        this.reGetLTRepliesToo = reGetLTRepliesToo;
    }

    public BackTieObjBean getPostMode() {
        return PostMode;
    }

    public void setPostMode(BackTieObjBean postMode) {
        PostMode = postMode;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

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

    public int getPostID() {
        return PostID;
    }

    public void setPostID(int postID) {
        PostID = postID;
    }

    public String getPostNickname() {
        return PostNickname;
    }

    public void setPostNickname(String postNickname) {
        PostNickname = postNickname;
    }

    public String getPostTitle() {
        return PostTitle;
    }

    public void setPostTitle(String postTitle) {
        PostTitle = postTitle;
    }

    public String getPostUserPic() {
        return PostUserPic;
    }

    public void setPostUserPic(String postUserPic) {
        PostUserPic = postUserPic;
    }

    public int getRepliesState() {
        return RepliesState;
    }

    public void setRepliesState(int repliesState) {
        RepliesState = repliesState;
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

    public String getUserPic() {
        return UserPic;
    }

    public void setUserPic(String userPic) {
        UserPic = userPic;
    }

    public int getFloorID() {
        return floorID;
    }

    public void setFloorID(int floorID) {
        this.floorID = floorID;
    }
}
