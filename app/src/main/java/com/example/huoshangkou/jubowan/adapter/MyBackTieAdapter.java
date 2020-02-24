package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;

import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：MyBackTieAdapter
 * 描述：
 * 创建时间：2017-04-10  15:04
 */

public class MyBackTieAdapter extends BaseAbstractAdapter<String> {
    public MyBackTieAdapter(Context context, List<String> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, String bean, int position) {

    }
}
