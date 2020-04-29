package com.example.huoshangkou.jubowan.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：ApproveListBean
 * 描述：
 * 创建时间：2017-03-22  11:26
 */

public class ApproveListBean implements Serializable,Parcelable {


    /**
     * ID : 0
     * ApprovalID : 33047
     * ApprovalUserID : 1298
     * ApprovalUserName : 来一军
     * ApprovalOver : 1
     * Approvalremark :
     * ApprovalPic : ,http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20191111/20191111190521_3234.png
     * CreateTime : 2019-11-11 19:05:21
     * HeadPic : http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20170630/20170630172256_9347.jpg
     */

    private int ID;
    private int ApprovalID;
    private int ApprovalUserID;
    private String ApprovalUserName;
    private int ApprovalOver;
    private String Approvalremark;
    private String ApprovalPic;
    private String CreateTime;
    private String HeadPic;

    public ApproveListBean(){

    }

    protected ApproveListBean(Parcel in) {
        ID = in.readInt();
        ApprovalID = in.readInt();
        ApprovalUserID = in.readInt();
        ApprovalUserName = in.readString();
        ApprovalOver = in.readInt();
        Approvalremark = in.readString();
        ApprovalPic = in.readString();
        CreateTime = in.readString();
        HeadPic = in.readString();
    }


    public static final Creator<ApproveListBean> CREATOR = new Creator<ApproveListBean>() {
        @Override
        public ApproveListBean createFromParcel(Parcel in) {
            return new ApproveListBean(in);
        }

        @Override
        public ApproveListBean[] newArray(int size) {
            return new ApproveListBean[size];
        }
    };

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getApprovalID() {
        return ApprovalID;
    }

    public void setApprovalID(int ApprovalID) {
        this.ApprovalID = ApprovalID;
    }

    public int getApprovalUserID() {
        return ApprovalUserID;
    }

    public void setApprovalUserID(int ApprovalUserID) {
        this.ApprovalUserID = ApprovalUserID;
    }

    public String getApprovalUserName() {
        return ApprovalUserName;
    }

    public void setApprovalUserName(String ApprovalUserName) {
        this.ApprovalUserName = ApprovalUserName;
    }

    public int getApprovalOver() {
        return ApprovalOver;
    }

    public void setApprovalOver(int ApprovalOver) {
        this.ApprovalOver = ApprovalOver;
    }

    public String getApprovalremark() {
        return Approvalremark;
    }

    public void setApprovalremark(String Approvalremark) {
        this.Approvalremark = Approvalremark;
    }

    public String getApprovalPic() {
        return ApprovalPic;
    }

    public void setApprovalPic(String ApprovalPic) {
        this.ApprovalPic = ApprovalPic;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getHeadPic() {
        return HeadPic;
    }

    public void setHeadPic(String HeadPic) {
        this.HeadPic = HeadPic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ID);
        parcel.writeInt(ApprovalID);
        parcel.writeInt(ApprovalUserID);
        parcel.writeString(ApprovalUserName);
        parcel.writeInt(ApprovalOver);
        parcel.writeString(Approvalremark);
        parcel.writeString(ApprovalPic);
        parcel.writeString(CreateTime);
        parcel.writeString(HeadPic);
    }
}
