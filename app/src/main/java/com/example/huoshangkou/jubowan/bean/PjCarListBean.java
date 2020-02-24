package com.example.huoshangkou.jubowan.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：PjCarListBean
 * 描述：
 * 创建时间：2017-12-04  13:42
 */

public class PjCarListBean implements Parcelable{

    private String ClassTitle;
    private String CreateTime;
    private String Descript;
    private String GuiGeVal;
    private String ID;
    private String ModelTitle;
    private String Name;
    private String Number;
    private String Pic;
    private String Price;
    private String ProductID;
    private String TotalPrice;
    private String UserID;
    private String Voltage;

    private boolean isCheck = false;

    public PjCarListBean(){

    }

    protected PjCarListBean(Parcel in) {
        ClassTitle = in.readString();
        CreateTime = in.readString();
        Descript = in.readString();
        GuiGeVal = in.readString();
        ID = in.readString();
        ModelTitle = in.readString();
        Name = in.readString();
        Number = in.readString();
        Pic = in.readString();
        Price = in.readString();
        ProductID = in.readString();
        TotalPrice = in.readString();
        UserID = in.readString();
        Voltage = in.readString();
        isCheck = in.readByte() != 0;
    }

    public static final Creator<PjCarListBean> CREATOR = new Creator<PjCarListBean>() {
        @Override
        public PjCarListBean createFromParcel(Parcel in) {
            return new PjCarListBean(in);
        }

        @Override
        public PjCarListBean[] newArray(int size) {
            return new PjCarListBean[size];
        }
    };

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getClassTitle() {
        return ClassTitle;
    }

    public void setClassTitle(String classTitle) {
        ClassTitle = classTitle;
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

    public String getGuiGeVal() {
        return GuiGeVal;
    }

    public void setGuiGeVal(String guiGeVal) {
        GuiGeVal = guiGeVal;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getModelTitle() {
        return ModelTitle;
    }

    public void setModelTitle(String modelTitle) {
        ModelTitle = modelTitle;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getPic() {
        return Pic;
    }

    public void setPic(String pic) {
        Pic = pic;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getVoltage() {
        return Voltage;
    }

    public void setVoltage(String voltage) {
        Voltage = voltage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ClassTitle);
        parcel.writeString(CreateTime);
        parcel.writeString(Descript);
        parcel.writeString(GuiGeVal);
        parcel.writeString(ID);
        parcel.writeString(ModelTitle);
        parcel.writeString(Name);
        parcel.writeString(Number);
        parcel.writeString(Pic);
        parcel.writeString(Price);
        parcel.writeString(ProductID);
        parcel.writeString(TotalPrice);
        parcel.writeString(UserID);
        parcel.writeString(Voltage);
        parcel.writeByte((byte) (isCheck ? 1 : 0));
    }
}
