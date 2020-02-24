package com.example.huoshangkou.jubowan.inter;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：OnShopCarChooseCallBack
 * 描述：
 * 创建时间：2017-03-13  10:58
 */

public interface OnShopCarChooseCallBack {

    //父控件点击
    void onGroupClick(int groupPosition);

    //子控件点击
    void onChildClick(int groupPosition,int childPosition);

}
