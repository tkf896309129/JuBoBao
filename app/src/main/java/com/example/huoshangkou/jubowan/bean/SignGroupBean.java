package com.example.huoshangkou.jubowan.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：SignGroupBean
 * 描述：
 * 创建时间：2017-04-24  14:22
 */

public class SignGroupBean implements Serializable,Parcelable {

    private String groupName;
    private String manCount;

    protected SignGroupBean(Parcel in) {
        groupName = in.readString();
        manCount = in.readString();
    }

    public SignGroupBean(){

    }

    public static final Creator<SignGroupBean> CREATOR = new Creator<SignGroupBean>() {
        @Override
        public SignGroupBean createFromParcel(Parcel in) {
            return new SignGroupBean(in);
        }

        @Override
        public SignGroupBean[] newArray(int size) {
            return new SignGroupBean[size];
        }
    };

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getManCount() {
        return manCount;
    }

    public void setManCount(String manCount) {
        this.manCount = manCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(groupName);
        parcel.writeString(manCount);
    }
}
