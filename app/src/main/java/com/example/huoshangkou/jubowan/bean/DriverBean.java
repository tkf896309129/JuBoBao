package com.example.huoshangkou.jubowan.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：DriverBean
 * 描述：
 * 创建时间：2018-04-18  11:13
 */

public class DriverBean implements Parcelable {

    private String name;
    private String carNum;
    private String phone;

    protected DriverBean(Parcel in) {
        name = in.readString();
        carNum = in.readString();
        phone = in.readString();
    }

    public DriverBean(){

    }

    public static final Creator<DriverBean> CREATOR = new Creator<DriverBean>() {
        @Override
        public DriverBean createFromParcel(Parcel in) {
            return new DriverBean(in);
        }

        @Override
        public DriverBean[] newArray(int size) {
            return new DriverBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(carNum);
        parcel.writeString(phone);
    }
}
