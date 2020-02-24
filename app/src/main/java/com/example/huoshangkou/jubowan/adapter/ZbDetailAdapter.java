package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.ZbDetailBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：ZbDetailAdapter
 * 描述：
 * 创建时间：2017-04-20  09:17
 */

public class ZbDetailAdapter extends BaseAbstractAdapter<ZbDetailBean> {
    public ZbDetailAdapter(Context context, List<ZbDetailBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, ZbDetailBean bean, int position) {
        TextView tvTypeName = holder.getView(R.id.tv_type_name);
        TextView tvContent = holder.getView(R.id.tv_content);

        tvTypeName.setText(bean.getType());
        tvContent.setText(bean.getValue());
    }
}
