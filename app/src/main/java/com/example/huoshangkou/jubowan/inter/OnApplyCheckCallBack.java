package com.example.huoshangkou.jubowan.inter;

import com.example.huoshangkou.jubowan.bean.ApproveCheckBean;
import com.example.huoshangkou.jubowan.bean.ApproveCheckListBean;
import com.example.huoshangkou.jubowan.bean.GetApplyBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：OnApplyCheckCallBack
 * 描述：
 * 创建时间：2017-03-21  09:44
 */

public interface OnApplyCheckCallBack {
    void onApplyCheckBean( List<ApproveCheckListBean> checkBean);
}
