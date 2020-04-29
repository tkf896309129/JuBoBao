package com.example.huoshangkou.jubowan.bean;

import com.example.huoshangkou.jubowan.widget.ContactItemInterface;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.checkapply
 * 类名：SelectManBean
 * 描述：
 * 创建时间：2016-12-08  15:28
 */
public class SelectManBean implements ContactItemInterface {

    private String nickName;
    private String fullName;
    private String headPic;
    private String ID;
    private String groupName;
    private String phone;
    private String company;
    private int nowState;
    private int stateParrent;
    private boolean isCheck;

    public SelectManBean(String nickName, String fullName, String headPic, String ID, String groupName, String phone, int nowState, int stateParrent) {
        this.nickName = nickName;
        this.setFullName(fullName);
        this.headPic = headPic;
        this.ID = ID;
        this.groupName = groupName;
        this.phone = phone;
        this.nowState = nowState;
        this.stateParrent = stateParrent;
    }

    public SelectManBean(String nickName, String fullName, String phone) {
        this.nickName = nickName;
        this.setFullName(fullName);
        this.phone = phone;
    }

    public SelectManBean(String company) {
        this.nickName = company;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    @Override
    public String getItemForIndex() {
        return fullName;
    }

    @Override
    public String getDisplayInfo() {
        return nickName;
    }

    @Override
    public String getHeadImagePic() {
        return headPic;
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getGroupName() {
        return groupName;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public String getCompany() {
        return company;
    }

    @Override
    public int getNowPattern() {
        return stateParrent;
    }

    @Override
    public int getNowState() {
        return nowState;
    }

    @Override
    public boolean isCheck() {
        return isCheck;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
