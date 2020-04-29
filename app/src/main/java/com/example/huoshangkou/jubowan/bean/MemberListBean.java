package com.example.huoshangkou.jubowan.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/2.
 */
public class MemberListBean implements Parcelable,Serializable {
    private String Address;
    private String Company;
    private String CreateTime;
    private String Descript;
    private String DzZhangPic;
    private String Email;
    private String FarenName;
    private String FarenNo;
    private int ID;
    private boolean IsDel;
    private String LinkMan;
    private String LinkManCardNo;
    private String LinkManCardPic;
    private String LinkManPost;
    private String LoginName;
    private String ParentID;
    private String Passowrd;
    private String PicFaren;
    private String PicFarenBack;
    private String PicYyzz;
    private String PicZzjg;
    private String Role;
    private String RzBackInfo;
    private String SignAccountID;
    private String SignSealData;
    private String Tel;
    private String TypeID;
    private String UmengID;
    private int UserState;
    private String WebUrl;
    private String WxOpenID;
    private String YyzzNo;
    private String ZzjgNo;
    private String Pic1;
    private int color;
    private int NowPattern;
    private int NowState;
    private boolean isCheck = false;

    protected MemberListBean(Parcel in) {
        Address = in.readString();
        Company = in.readString();
        CreateTime = in.readString();
        Descript = in.readString();
        DzZhangPic = in.readString();
        Email = in.readString();
        FarenName = in.readString();
        FarenNo = in.readString();
        ID = in.readInt();
        IsDel = in.readByte() != 0;
        LinkMan = in.readString();
        LinkManCardNo = in.readString();
        LinkManCardPic = in.readString();
        LinkManPost = in.readString();
        LoginName = in.readString();
        ParentID = in.readString();
        Passowrd = in.readString();
        PicFaren = in.readString();
        PicFarenBack = in.readString();
        PicYyzz = in.readString();
        PicZzjg = in.readString();
        Role = in.readString();
        RzBackInfo = in.readString();
        SignAccountID = in.readString();
        SignSealData = in.readString();
        Tel = in.readString();
        TypeID = in.readString();
        UmengID = in.readString();
        UserState = in.readInt();
        WebUrl = in.readString();
        WxOpenID = in.readString();
        YyzzNo = in.readString();
        ZzjgNo = in.readString();
        Pic1 = in.readString();
        color = in.readInt();
        NowPattern = in.readInt();
        NowState = in.readInt();
        isCheck = in.readByte() != 0;
    }

    public static final Creator<MemberListBean> CREATOR = new Creator<MemberListBean>() {
        @Override
        public MemberListBean createFromParcel(Parcel in) {
            return new MemberListBean(in);
        }

        @Override
        public MemberListBean[] newArray(int size) {
            return new MemberListBean[size];
        }
    };

    public int getNowPattern() {
        return NowPattern;
    }

    public void setNowPattern(int nowPattern) {
        NowPattern = nowPattern;
    }

    public void setDel(boolean del) {
        IsDel = del;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public int getNowState() {
        return NowState;
    }

    public void setNowState(int nowState) {
        NowState = nowState;
    }

    public MemberListBean() {

    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }



    public boolean isCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getDescript() {
        return Descript;
    }

    public void setDescript(String descript) {
        Descript = descript;
    }

    public String getDzZhangPic() {
        return DzZhangPic;
    }

    public void setDzZhangPic(String dzZhangPic) {
        DzZhangPic = dzZhangPic;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFarenName() {
        return FarenName;
    }

    public void setFarenName(String farenName) {
        FarenName = farenName;
    }

    public String getFarenNo() {
        return FarenNo;
    }

    public void setFarenNo(String farenNo) {
        FarenNo = farenNo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isDel() {
        return IsDel;
    }

    public void setIsDel(boolean isDel) {
        IsDel = isDel;
    }

    public String getLinkMan() {
        return LinkMan;
    }

    public void setLinkMan(String linkMan) {
        LinkMan = linkMan;
    }

    public String getLinkManCardNo() {
        return LinkManCardNo;
    }

    public void setLinkManCardNo(String linkManCardNo) {
        LinkManCardNo = linkManCardNo;
    }

    public String getLinkManCardPic() {
        return LinkManCardPic;
    }

    public void setLinkManCardPic(String linkManCardPic) {
        LinkManCardPic = linkManCardPic;
    }

    public String getLinkManPost() {
        return LinkManPost;
    }

    public void setLinkManPost(String linkManPost) {
        LinkManPost = linkManPost;
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    public String getParentID() {
        return ParentID;
    }

    public void setParentID(String parentID) {
        ParentID = parentID;
    }

    public String getPassowrd() {
        return Passowrd;
    }

    public void setPassowrd(String passowrd) {
        Passowrd = passowrd;
    }

    public String getPic1() {
        return Pic1;
    }

    public void setPic1(String pic1) {
        Pic1 = pic1;
    }

    public String getPicFaren() {
        return PicFaren;
    }

    public void setPicFaren(String picFaren) {
        PicFaren = picFaren;
    }

    public String getPicFarenBack() {
        return PicFarenBack;
    }

    public void setPicFarenBack(String picFarenBack) {
        PicFarenBack = picFarenBack;
    }

    public String getPicYyzz() {
        return PicYyzz;
    }

    public void setPicYyzz(String picYyzz) {
        PicYyzz = picYyzz;
    }

    public String getPicZzjg() {
        return PicZzjg;
    }

    public void setPicZzjg(String picZzjg) {
        PicZzjg = picZzjg;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getRzBackInfo() {
        return RzBackInfo;
    }

    public void setRzBackInfo(String rzBackInfo) {
        RzBackInfo = rzBackInfo;
    }

    public String getSignAccountID() {
        return SignAccountID;
    }

    public void setSignAccountID(String signAccountID) {
        SignAccountID = signAccountID;
    }

    public String getSignSealData() {
        return SignSealData;
    }

    public void setSignSealData(String signSealData) {
        SignSealData = signSealData;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getTypeID() {
        return TypeID;
    }

    public void setTypeID(String typeID) {
        TypeID = typeID;
    }

    public String getUmengID() {
        return UmengID;
    }

    public void setUmengID(String umengID) {
        UmengID = umengID;
    }

    public int getUserState() {
        return UserState;
    }

    public void setUserState(int userState) {
        UserState = userState;
    }

    public String getWebUrl() {
        return WebUrl;
    }

    public void setWebUrl(String webUrl) {
        WebUrl = webUrl;
    }

    public String getWxOpenID() {
        return WxOpenID;
    }

    public void setWxOpenID(String wxOpenID) {
        WxOpenID = wxOpenID;
    }

    public String getYyzzNo() {
        return YyzzNo;
    }

    public void setYyzzNo(String yyzzNo) {
        YyzzNo = yyzzNo;
    }

    public String getZzjgNo() {
        return ZzjgNo;
    }

    public void setZzjgNo(String zzjgNo) {
        ZzjgNo = zzjgNo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Address);
        dest.writeString(Company);
        dest.writeString(CreateTime);
        dest.writeString(Descript);
        dest.writeString(DzZhangPic);
        dest.writeString(Email);
        dest.writeString(FarenName);
        dest.writeString(FarenNo);
        dest.writeInt(ID);
        dest.writeByte((byte) (IsDel ? 1 : 0));
        dest.writeString(LinkMan);
        dest.writeString(LinkManCardNo);
        dest.writeString(LinkManCardPic);
        dest.writeString(LinkManPost);
        dest.writeString(LoginName);
        dest.writeString(ParentID);
        dest.writeString(Passowrd);
        dest.writeString(Pic1);
        dest.writeString(PicFaren);
        dest.writeString(PicFarenBack);
        dest.writeString(PicYyzz);
        dest.writeString(PicZzjg);
        dest.writeString(Role);
        dest.writeString(RzBackInfo);
        dest.writeString(SignAccountID);
        dest.writeString(SignSealData);
        dest.writeString(Tel);
        dest.writeString(TypeID);
        dest.writeString(UmengID);
        dest.writeInt(UserState);
        dest.writeString(WebUrl);
        dest.writeString(WxOpenID);
        dest.writeString(YyzzNo);
        dest.writeString(ZzjgNo);
        dest.writeByte((byte) (isCheck ? 1 : 0));
    }
}
