package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：MessageTypeListBean
 * 描述：
 * 创建时间：2017-05-12  14:46
 */

public class MessageTypeListBean {

    private String CreatTime;
    private List<MessageTypeDetailListBean> FLList;
    private int ID;
    private String MessageContent;
    private String MessageTitle;
    private String MessageType;
    private String OrderID;
    private int ToState;
    private int UserID;
    private List<MessageTypeDetailListBean> WXList;
    private List<MessageTypeDetailListBean> YPList;

    public String getCreatTime() {
        return CreatTime;
    }

    public void setCreatTime(String creatTime) {
        CreatTime = creatTime;
    }

    public List<MessageTypeDetailListBean> getFLList() {
        return FLList;
    }

    public void setFLList(List<MessageTypeDetailListBean> FLList) {
        this.FLList = FLList;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMessageContent() {
        return MessageContent;
    }

    public void setMessageContent(String messageContent) {
        MessageContent = messageContent;
    }

    public String getMessageTitle() {
        return MessageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        MessageTitle = messageTitle;
    }

    public String getMessageType() {
        return MessageType;
    }

    public void setMessageType(String messageType) {
        MessageType = messageType;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public int getToState() {
        return ToState;
    }

    public void setToState(int toState) {
        ToState = toState;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public List<MessageTypeDetailListBean> getWXList() {
        return WXList;
    }

    public void setWXList(List<MessageTypeDetailListBean> WXList) {
        this.WXList = WXList;
    }

    public List<MessageTypeDetailListBean> getYPList() {
        return YPList;
    }

    public void setYPList(List<MessageTypeDetailListBean> YPList) {
        this.YPList = YPList;
    }
}
