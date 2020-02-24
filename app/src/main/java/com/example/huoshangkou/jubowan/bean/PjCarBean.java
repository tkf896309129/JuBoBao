package com.example.huoshangkou.jubowan.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：PjCarBean
 * 描述：
 * 创建时间：2017-12-04  13:42
 */

public class PjCarBean implements Parcelable{

    private String ErrMsg;
    private int PageCount;
    private int PageIndex;
    private List<PjCarListBean> ReList;
    private int Success;
    private int TotalCount;
    private String TotalSum;

    public PjCarBean(){

    }


    protected PjCarBean(Parcel in) {
        ErrMsg = in.readString();
        PageCount = in.readInt();
        PageIndex = in.readInt();
        ReList = in.createTypedArrayList(PjCarListBean.CREATOR);
        Success = in.readInt();
        TotalCount = in.readInt();
        TotalSum = in.readString();
    }

    public static final Creator<PjCarBean> CREATOR = new Creator<PjCarBean>() {
        @Override
        public PjCarBean createFromParcel(Parcel in) {
            return new PjCarBean(in);
        }

        @Override
        public PjCarBean[] newArray(int size) {
            return new PjCarBean[size];
        }
    };

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public int getPageCount() {
        return PageCount;
    }

    public void setPageCount(int pageCount) {
        PageCount = pageCount;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
    }

    public List<PjCarListBean> getReList() {
        return ReList;
    }

    public void setReList(List<PjCarListBean> reList) {
        ReList = reList;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int totalCount) {
        TotalCount = totalCount;
    }

    public String getTotalSum() {
        return TotalSum;
    }

    public void setTotalSum(String totalSum) {
        TotalSum = totalSum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ErrMsg);
        parcel.writeInt(PageCount);
        parcel.writeInt(PageIndex);
        parcel.writeTypedList(ReList);
        parcel.writeInt(Success);
        parcel.writeInt(TotalCount);
        parcel.writeString(TotalSum);
    }
}
