package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.ToPayOrderListBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：ToPayOrderAdapter
 * 描述：
 * 创建时间：2017-12-14  09:44
 */

public class ToPayOrderAdapter extends BaseAbstractAdapter<ToPayOrderListBean> {
    public ToPayOrderAdapter(Context context, List<ToPayOrderListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, ToPayOrderListBean bean, int position) {
        TextView tvOrderId = holder.getView(R.id.tv_order_id);
        TextView tvPrice = holder.getView(R.id.tv_price);
        ImageView imgCheck = holder.getView(R.id.iv_check);

        tvOrderId.setText("订单号：" + bean.getOrderID());
        tvPrice.setText("价格：" + bean.getPrice() + "元");

        if (bean.isChcek()) {
            imgCheck.setVisibility(ImageView.VISIBLE);
        } else {
            imgCheck.setVisibility(ImageView.GONE);
        }


    }
}
