package com.example.huoshangkou.jubowan.inter;

import com.example.huoshangkou.jubowan.bean.ApproveTypeBean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：OnApproveTypeCallBack
 * 描述：
 * 创建时间：2017-03-09  14:55
 */

public interface OnApproveTypeCallBack {

    void onSuccess(ApproveTypeBean typeBean);

    void onFail();

}
