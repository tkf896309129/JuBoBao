package com.example.huoshangkou.jubowan.inter;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：OnToCommonCallBack
 * 描述：
 * 创建时间：2017-05-18  14:28
 */

public interface OnToCommonCallBack {
    void onToCommon(String id,int position);

    void onToRepair(String id,int position);

    void onDianPay(String id,int position,int advanceState);

}
