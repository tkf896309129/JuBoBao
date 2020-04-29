package com.example.huoshangkou.jubowan.inter;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：OnShopCarAddDecreaseCallBack
 * 描述：
 * 创建时间：2017-03-14  14:33
 */

public interface OnShopCarAddDecreaseCallBack {
    //类型  订单id  数量
    void onAddCar(String type, String id, String num, int groupPosition, int childPosition);
//    void onAddCar(String type, String id, String num, int position);

//    void onDecrease(String type, String id, int num, double toNum, int groupPosition, int childPosition);

    //点击回调
    void onClickPosition(String type, String id, double toNum, int groupPosition, int childPosition);
//    void onClickPosition(String type, String id, double toNum, int position);


    //删除订单
    void deleteOrder(String type, int groupPosition,String id);
//    void deleteOrder(String type, int position,String id);
}
