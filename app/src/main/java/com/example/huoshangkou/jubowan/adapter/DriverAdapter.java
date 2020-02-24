package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.DriverBean;

import java.sql.Driver;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：DriverAdapter
 * 描述: 
 * 创建时间：2018-04-18  14:12
 */

public class DriverAdapter extends BaseAbstractAdapter<DriverBean> {
    public DriverAdapter(Context context, List<DriverBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, DriverBean bean, int position) {
        TextView tvPhone = holder.getView(R.id.tv_phone);
        TextView tvCarNum = holder.getView(R.id.tv_car_num);
        TextView tvName = holder.getView(R.id.tv_name);

        tvName.setText(bean.getName());
        tvCarNum.setText(bean.getCarNum());
        tvPhone.setText(bean.getPhone());

    }
}
