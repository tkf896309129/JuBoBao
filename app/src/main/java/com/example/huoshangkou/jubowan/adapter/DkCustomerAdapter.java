package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.CompanyListBean;
import com.example.huoshangkou.jubowan.bean.DkCustomerBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：CompanyAdapter
 * 描述：
 * 创建时间：2018-01-23  08:59
 */

public class DkCustomerAdapter extends BaseAbstractAdapter<String> {
    public DkCustomerAdapter(Context context, List<String> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, String bean, int position) {
        TextView tvCompnay = holder.getView(R.id.tv_company);
        tvCompnay.setText(bean);
    }
}
