package com.example.huoshangkou.jubowan.inter;

import com.example.huoshangkou.jubowan.bean.ApproveBankListBean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：OnApproveBankCallBack
 * 描述：
 * 创建时间：2017-03-21  13:33
 */

public interface OnApproveBankCallBack {

    void onDeleteBankInfo(String id,int position);


    void onEditBankInfo(ApproveBankListBean bean);
}
