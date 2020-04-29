package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;

import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：MonthBtRecordAdapter
 * 描述：
 * 创建时间：2019-08-28  14:32
 */

public class MonthBtRecordAdapter extends BaseAbstractAdapter<String> {
    public MonthBtRecordAdapter(Context context, List<String> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, String bean, int position) {

    }
}
