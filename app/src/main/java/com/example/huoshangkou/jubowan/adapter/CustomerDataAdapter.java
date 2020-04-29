package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.CustomerProportionListBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：CustomerDataAdapter
 * 描述：
 * 创建时间：2019-08-27  08:15
 */

public class CustomerDataAdapter extends BaseAbstractAdapter<CustomerProportionListBean> {
    public CustomerDataAdapter(Context context, List<CustomerProportionListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, CustomerProportionListBean bean, int position) {

        TextView tvRec = holder.getView(R.id.tv_rec_dot);
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvPercent = holder.getView(R.id.tv_percent);

        tvRec.setBackgroundColor(context.getResources().getColor(bean.getColor()));
        tvName.setText(bean.getName());
        tvPercent.setText(bean.getCount() + " | " + bean.getRatio());


    }
}
