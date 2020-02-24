package com.example.huoshangkou.jubowan.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：RepairTypeListBean
 * 描述：
 * 创建时间：2017-03-24  09:06
 */

public class RepairTypeListBean implements Serializable,Parcelable {

//    private String CreateTime;
//    private String Descript;
//    private int ID;
//    private String Name;
//    private String Pic;
//    private List<RepairWxBrandListBean> WxBrandList;
//    private List<RepairWxMaintainBean> WxMaintainTypeList;

    private int Count;
    private int ID;
    private String Name;
    private String Pic;

    public RepairTypeListBean(){

    }

    protected RepairTypeListBean(Parcel in) {
        Count = in.readInt();
        ID = in.readInt();
        Name = in.readString();
        Pic = in.readString();
    }

    public static final Creator<RepairTypeListBean> CREATOR = new Creator<RepairTypeListBean>() {
        @Override
        public RepairTypeListBean createFromParcel(Parcel in) {
            return new RepairTypeListBean(in);
        }

        @Override
        public RepairTypeListBean[] newArray(int size) {
            return new RepairTypeListBean[size];
        }
    };

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPic() {
        return Pic;
    }

    public void setPic(String pic) {
        Pic = pic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Count);
        parcel.writeInt(ID);
        parcel.writeString(Name);
        parcel.writeString(Pic);
    }
}
