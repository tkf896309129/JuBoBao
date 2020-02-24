package com.example.huoshangkou.jubowan.inter;

import com.example.huoshangkou.jubowan.bean.ApproveCheckBean;
import com.example.huoshangkou.jubowan.bean.ApproveCheckListBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：OnGetCheckCallBack
 * 描述：
 * 创建时间：2017-03-20  13:47
 */

public interface OnGetCheckCallBack {

    void onGetCheckBean(List<ApproveCheckListBean> checkBean);

}
