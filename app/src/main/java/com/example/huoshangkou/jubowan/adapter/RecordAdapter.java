package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：RecordAdapter
 * 描述：
 * 创建时间：2019-08-22  08:24
 */

public class RecordAdapter extends BaseAbstractAdapter<String> {
    public RecordAdapter(Context context, List<String> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, String bean, int position) {
        TextView tvRecord = holder.getView(R.id.tv_record);
        tvRecord.setText(bean);
    }
}
