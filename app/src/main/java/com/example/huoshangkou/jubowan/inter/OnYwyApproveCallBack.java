package com.example.huoshangkou.jubowan.inter;

import com.example.huoshangkou.jubowan.bean.YwyInfoObjBean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：OnYwyApproveCallBack
 * 描述：获取业务员信息回调
 * 创建时间：2017-03-09  15:23
 */

public interface OnYwyApproveCallBack {

    void onSuccess(YwyInfoObjBean objBean);

    void onFail();

}
