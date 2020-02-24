package com.example.huoshangkou.jubowan.inter;

import com.example.huoshangkou.jubowan.bean.GYListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：OnGyListCallBack
 * 描述：
 * 创建时间：2017-04-12  13:41
 */

public interface OnGyListCallBack {

    void getGyListSuccess(ArrayList<GYListBean> gyListBeanList);

}
