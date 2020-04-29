package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.OrderTypeBean;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：OrderTypeDetailAdapter
 * 描述：筛选详情适配器
 * 创建时间：2017-02-17  10:18
 */

public class OrderTypeDetailAdapter extends BaseAbstractAdapter<OrderTypeBean> {

    private int itemClickPosition = -1;

    public OrderTypeDetailAdapter(Context context, List<OrderTypeBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, OrderTypeBean bean, int position) {
        TextView tvType = holder.getView(R.id.tv_type_detail);
        ImageView ivType = holder.getView(R.id.iv_gou);

        if (StringUtils.isNoEmpty(bean.getType())) {
            tvType.setText(bean.getType());
        } else if (StringUtils.isNoEmpty(bean.getClassTitle())) {
            tvType.setText(bean.getClassTitle());
        } else if (StringUtils.isNoEmpty(bean.getName())) {
            tvType.setText(bean.getName());
        } else {
            tvType.setText("");
        }

        if (bean.isCheck()) {
            ivType.setVisibility(View.VISIBLE);
            tvType.setTextColor(context.getResources().getColor(R.color.main_tab_blue_all));
        } else {
            ivType.setVisibility(View.GONE);
            tvType.setTextColor(context.getResources().getColor(R.color.address_black_key));
        }

//        if (position == itemClickPosition) {
//            ivType.setVisibility(View.VISIBLE);
//            tvType.setTextColor(context.getResources().getColor(R.color.main_tab_blue));
//        } else {
//            ivType.setVisibility(View.GONE);
//            tvType.setTextColor(context.getResources().getColor(R.color.address_black_key));
//        }
    }

    public void setItemClickPosition(int position) {
        itemClickPosition = position;
    }
}
