package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.CustomerDetailBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：AddCustomerAdapter
 * 描述：
 * 创建时间：2019-08-29  11:14
 */

public class AddCustomerAdapter extends BaseAbstractAdapter<CustomerDetailBean> {
    public AddCustomerAdapter(Context context, List<CustomerDetailBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, CustomerDetailBean bean, int position) {
        TextView tvType = holder.getView(R.id.tv_type);
        TextView tvContent = holder.getView(R.id.tv_content);

        tvType.setText(bean.getType());
        tvContent.setText(bean.getContent());
    }
}
