package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;

import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.CheckSignListBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：CheckedSignAdapter
 * 描述：
 * 创建时间：2017-04-19  09:46
 */

public class CheckedSignAdapter extends BaseAbstractAdapter<CheckSignListBean> {

    public CheckedSignAdapter(Context context, List<CheckSignListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, CheckSignListBean bean, int position) {

    }

}
