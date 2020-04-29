package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;

import java.util.List;


/**
 * 作者：唐先生
 * 包名：atjubo.saas.huoshankou.com.saasjuboapp.adapter
 * 类名：BaseDialogAdapter
 * 描述：
 * 创建时间：2018-07-06  11:10
 */

public class BaseDialogAdapter extends BaseAbstractAdapter<String> {
    public BaseDialogAdapter(Context context, List<String> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, String bean, int position) {
        TextView textView = holder.getView(R.id.tv_content);
        textView.setText(bean);
    }
}
