package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.BtDetailBean;
import com.example.huoshangkou.jubowan.utils.MoneyUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：BtMonthDetailAdapter
 * 描述：
 * 创建时间：2019-09-29  13:35
 */

public class BtMonthDetailAdapter extends BaseAbstractAdapter<BtDetailBean.DBean.ResultBean> {
    public BtMonthDetailAdapter(Context context, List<BtDetailBean.DBean.ResultBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, BtDetailBean.DBean.ResultBean bean, int position) {
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvTime = holder.getView(R.id.tv_sale_name);
        TextView tvAllMoney = holder.getView(R.id.tv_all_money);
        TextView tvSaleMoney = holder.getView(R.id.tv_rest_money);

        tvTime.setText("申请客户："+ StringUtils.getNoEmptyStr(bean.getLinkMan()));
        tvAllMoney.setText("总额度：" + MoneyUtils.getInstance().getFormPrice(bean.getIousTotalAmount()+""));
        tvSaleMoney.setText("剩余额度：" + MoneyUtils.getInstance().getFormPrice(bean.getIousRemainingAmount()+""));
        tvName.setText(bean.getApplyUnit() + "");
    }
}
