package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：LoginMessageObjBean
 * 描述：
 * 创建时间：2017-02-28  09:14
 */

public class LoginMessageObjBean {

    private String Address;
    private String CompanyName;
    private String HeadPic;
    private int ID;
    private boolean IsTrade;
    private int JuboLevel;
    private int Jubobi;
    private int RoleID;
    private String LinkManCardPic;
    private String LoginType;
    private String Nickname;
    private int ParentID;
    private String PicYyzz;
    private String QQOpenID;
    private String RealName;
    private String Remark;
    private String Tel;
    private String TwoParentID;
    private String UserCardNo;
    private String UserState;
    private int UserType;
    private String WbOpenID;
    private String WxOpenID;
    private String WxjiqiName;
    private String ZzjgNo;
    private String OnCode;

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int roleID) {
        RoleID = roleID;
    }

    public String getOnCode() {
        return OnCode;
    }

    public void setOnCode(String onCode) {
        OnCode = onCode;
    }

    public boolean isTrade() {
        return IsTrade;
    }

    public void setTrade(boolean trade) {
        IsTrade = trade;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getHeadPic() {
        return HeadPic;
    }

    public void setHeadPic(String headPic) {
        HeadPic = headPic;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getJuboLevel() {
        return JuboLevel;
    }

    public void setJuboLevel(int juboLevel) {
        JuboLevel = juboLevel;
    }

    public int getJubobi() {
        return Jubobi;
    }

    public void setJubobi(int jubobi) {
        Jubobi = jubobi;
    }

    public String getLinkManCardPic() {
        return LinkManCardPic;
    }

    public void setLinkManCardPic(String linkManCardPic) {
        LinkManCardPic = linkManCardPic;
    }

    public String getLoginType() {
        return LoginType;
    }

    public void setLoginType(String loginType) {
        LoginType = loginType;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public int getParentID() {
        return ParentID;
    }

    public void setParentID(int parentID) {
        ParentID = parentID;
    }

    public String getPicYyzz() {
        return PicYyzz;
    }

    public void setPicYyzz(String picYyzz) {
        PicYyzz = picYyzz;
    }

    public String getQQOpenID() {
        return QQOpenID;
    }

    public void setQQOpenID(String QQOpenID) {
        this.QQOpenID = QQOpenID;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getTwoParentID() {
        return TwoParentID;
    }

    public void setTwoParentID(String twoParentID) {
        TwoParentID = twoParentID;
    }

    public String getUserCardNo() {
        return UserCardNo;
    }

    public void setUserCardNo(String userCardNo) {
        UserCardNo = userCardNo;
    }

    public String getUserState() {
        return UserState;
    }

    public void setUserState(String userState) {
        UserState = userState;
    }

    public int getUserType() {
        return UserType;
    }

    public void setUserType(int userType) {
        UserType = userType;
    }

    public String getWbOpenID() {
        return WbOpenID;
    }

    public void setWbOpenID(String wbOpenID) {
        WbOpenID = wbOpenID;
    }

    public String getWxOpenID() {
        return WxOpenID;
    }

    public void setWxOpenID(String wxOpenID) {
        WxOpenID = wxOpenID;
    }

    public String getWxjiqiName() {
        return WxjiqiName;
    }

    public void setWxjiqiName(String wxjiqiName) {
        WxjiqiName = wxjiqiName;
    }

    public String getZzjgNo() {
        return ZzjgNo;
    }

    public void setZzjgNo(String zzjgNo) {
        ZzjgNo = zzjgNo;
    }

    @Override
    public String toString() {
        return "LoginMessageObjBean{" +
                "Address='" + Address + '\'' +
                ", CompanyName='" + CompanyName + '\'' +
                ", HeadPic='" + HeadPic + '\'' +
                ", ID=" + ID +
                ", JuboLevel=" + JuboLevel +
                ", Jubobi=" + Jubobi +
                ", LinkManCardPic='" + LinkManCardPic + '\'' +
                ", LoginType='" + LoginType + '\'' +
                ", Nickname='" + Nickname + '\'' +
                ", ParentID=" + ParentID +
                ", PicYyzz='" + PicYyzz + '\'' +
                ", QQOpenID='" + QQOpenID + '\'' +
                ", RealName='" + RealName + '\'' +
                ", Remark='" + Remark + '\'' +
                ", Tel='" + Tel + '\'' +
                ", TwoParentID='" + TwoParentID + '\'' +
                ", UserCardNo='" + UserCardNo + '\'' +
                ", UserState='" + UserState + '\'' +
                ", UserType=" + UserType +
                ", WbOpenID='" + WbOpenID + '\'' +
                ", WxOpenID='" + WxOpenID + '\'' +
                ", WxjiqiName='" + WxjiqiName + '\'' +
                ", ZzjgNo='" + ZzjgNo + '\'' +
                '}';
    }
}
