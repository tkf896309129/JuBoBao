package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.OrderBean;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：OrderAdapter
 * 描述：
 * 创建时间：2017-02-09  11:30
 */

public class OrderAdapter extends BaseAbstractAdapter<OrderBean> implements View.OnClickListener {

    public OrderAdapter(Context context, List<OrderBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, OrderBean bean, int position) {
        TextView tvOrderNum = holder.getView(R.id.tv_order_num);
        TextView tvOrderTime = holder.getView(R.id.tv_order_time);
        TextView tvType = holder.getView(R.id.tv_repair_type);
        TextView tvBrand = holder.getView(R.id.tv_brand);
        TextView tvIntro = holder.getView(R.id.tv_intro);
        TextView tvTime = holder.getView(R.id.tv_time);
        TextView tvProNum = holder.getView(R.id.tv_pro_num);
        TextView tvRepairPrice = holder.getView(R.id.tv_repair_price);

        TextView tvDelete = holder.getView(R.id.tv_delete);
        TextView tvPay = holder.getView(R.id.tv_pay);
        TextView tvCommon = holder.getView(R.id.tv_common);

        //删除
        tvDelete.setOnClickListener(this);
        //支付
        tvPay.setOnClickListener(this);
        //评价
        tvCommon.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_delete:
                ToastUtils.getMineToast("删除");
                break;
            case R.id.tv_pay:
                ToastUtils.getMineToast("支付");
                break;
            case R.id.tv_common:
                ToastUtils.getMineToast("评价");
                break;
        }
    }
}
