package com.example.huoshangkou.jubowan.inter;

import com.example.huoshangkou.jubowan.bean.YwyApproveBean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：OnYwyApproveTypeCallBack
 * 描述：
 * 创建时间：2017-03-08  15:14
 */

public interface OnYwyApproveTypeCallBack {


    void onSuccess(YwyApproveBean approveBean);

    void onFail();

}
