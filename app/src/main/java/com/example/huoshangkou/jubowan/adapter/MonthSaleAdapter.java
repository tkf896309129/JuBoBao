package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.MonthSaleDetailBean;
import com.example.huoshangkou.jubowan.utils.MoneyUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：MonthSaleAdapter
 * 描述：
 * 创建时间：2019-09-25  13:25
 */

public class MonthSaleAdapter extends BaseAbstractAdapter<MonthSaleDetailBean.DBean.ResultBean> {
    public MonthSaleAdapter(Context context, List<MonthSaleDetailBean.DBean.ResultBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, MonthSaleDetailBean.DBean.ResultBean bean, int position) {
        TextView tvTime = holder.getView(R.id.tv_time);
        TextView tvMoney = holder.getView(R.id.tv_money);
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvSaleName = holder.getView(R.id.tv_sale_name);

        tvSaleName.setText(bean.getRemittedTime());
        tvMoney.setText(bean.getSumTotalPrice() > 0 ? "+" + MoneyUtils.getInstance().getFormPrice(bean.getSumTotalPrice() + "") : "-" + MoneyUtils.getInstance().getFormPrice(bean.getSumTotalPrice() + ""));
        tvName.setText(StringUtils.getNoEmptyStr(bean.getCustomerName()));
        tvTime.setText("所属销售：" + StringUtils.getNoEmptyStr(bean.getSalesMan()));
    }
}
