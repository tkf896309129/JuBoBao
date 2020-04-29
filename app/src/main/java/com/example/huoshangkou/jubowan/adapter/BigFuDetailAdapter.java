package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;

import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：BigFuDetailAdapter
 * 描述：
 * 创建时间：2019-03-25  10:31
 */

public class BigFuDetailAdapter extends BaseAbstractAdapter<String> {
    public BigFuDetailAdapter(Context context, List<String> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, String bean, int position) {

    }
}
