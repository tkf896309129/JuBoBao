package com.example.huoshangkou.jubowan.widget;

public interface ContactItemInterface {
    public String getItemForIndex();

    public String getDisplayInfo();

    public String getHeadImagePic();

    public String getId();

    //获取部门名字
    public String getGroupName();

    //获取手机号
    public String getPhone();

    //公司名称
    public String getCompany();

    //部门状态
    public int getNowPattern();

    //工作状态
    public int getNowState();

    //是否被选中
    public boolean isCheck();



}
