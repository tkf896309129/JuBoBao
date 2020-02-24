package com.example.huoshangkou.jubowan.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：ToolOldClassBean
 * 描述：
 * 创建时间：2018-06-13  13:42
 */

public class ToolOldClassBean implements Parcelable {
    private String ClassTitle;
    private int ID;
    private String Pic;

    public ToolOldClassBean(){

    }

    protected ToolOldClassBean(Parcel in) {
        ClassTitle = in.readString();
        ID = in.readInt();
        Pic = in.readString();
    }

    public static final Creator<ToolOldClassBean> CREATOR = new Creator<ToolOldClassBean>() {
        @Override
        public ToolOldClassBean createFromParcel(Parcel in) {
            return new ToolOldClassBean(in);
        }

        @Override
        public ToolOldClassBean[] newArray(int size) {
            return new ToolOldClassBean[size];
        }
    };

    public String getClassTitle() {
        return ClassTitle;
    }

    public void setClassTitle(String classTitle) {
        ClassTitle = classTitle;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
        parcel.writeString(ClassTitle);
        parcel.writeInt(ID);
        parcel.writeString(Pic);
    }
}
