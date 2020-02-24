package com.example.huoshangkou.jubowan.inter;

import android.graphics.drawable.Drawable;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：OnAddCarClickBack
 * 描述：
 * 创建时间：2017-03-14  09:17
 */

public interface OnAddCarClickBack {

    //购物车点击事件
    void onAddCarClick(String id, Drawable drawable, int[] startLocation);

}
