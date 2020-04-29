package com.example.huoshangkou.jubowan.inter;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：NewShopActionCallBack
 * 描述：
 * 创建时间：2020-03-05  16:00
 */

public interface NewShopActionCallBack {

    void onAddCar(String type, String id, String num, int position);

//    void onDecrease(String type, String id, int num, double toNum, int groupPosition, int childPosition);

    //点击回调
//    void onClickPosition(String type, String id, double toNum, int groupPosition, int childPosition);
    void onClickPosition(String type, String id, double toNum, int position);


    //删除订单
//    void deleteOrder(String type, int groupPosition,String id);
    void deleteOrder( int position);
}
