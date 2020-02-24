package com.example.huoshangkou.jubowan.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：SyYuanListBean
 * 描述：
 * 创建时间：2017-12-18  10:22
 */

public class SyYuanListBean implements Parcelable {
    private String Company;
    private String ID;

    protected SyYuanListBean(Parcel in) {
        Company = in.readString();
        ID = in.readString();
    }

    public SyYuanListBean() {
    }


    public static final Creator<SyYuanListBean> CREATOR = new Creator<SyYuanListBean>() {
        @Override
        public SyYuanListBean createFromParcel(Parcel in) {
            return new SyYuanListBean(in);
        }

        @Override
        public SyYuanListBean[] newArray(int size) {
            return new SyYuanListBean[size];
        }
    };

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Company);
        parcel.writeString(ID);
    }
}
