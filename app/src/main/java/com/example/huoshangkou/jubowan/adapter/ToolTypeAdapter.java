package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.OrderTypeBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：ToolTypeAdapter
 * 描述：
 * 创建时间：2017-06-20  14:50
 */

public class ToolTypeAdapter extends BaseAbstractAdapter<OrderTypeBean> {

    private int itemClickPosition = -1;

    public ToolTypeAdapter(Context context, List<OrderTypeBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, OrderTypeBean bean, int position) {

        TextView tvType = holder.getView(R.id.tv_order_type);
        tvType.setText(bean.getType());

        if (position == itemClickPosition) {
            tvType.setTextColor(context.getResources().getColor(R.color.main_tab_blue));
            tvType.setBackgroundColor(context.getResources().getColor(R.color.white));
        } else {
            tvType.setTextColor(context.getResources().getColor(R.color.address_black_key));
            tvType.setBackgroundColor(context.getResources().getColor(R.color.choose_order_bg));
        }

    }
}
