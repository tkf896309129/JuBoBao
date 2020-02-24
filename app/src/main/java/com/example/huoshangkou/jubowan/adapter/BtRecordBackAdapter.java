package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.BackMoneyBean;
import com.example.huoshangkou.jubowan.utils.DateUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：BtRecordBackAdapter
 * 描述：
 * 创建时间：2018-08-22  11:44
 */

public class BtRecordBackAdapter extends BaseAbstractAdapter<BackMoneyBean.ReObjBean> {
    public BtRecordBackAdapter(Context context, List<BackMoneyBean.ReObjBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, BackMoneyBean.ReObjBean bean, int position) {
        TextView tvBackTime = holder.getView(R.id.tv_back_time);
        TextView tvBackMoney = holder.getView(R.id.tv_back_bj);
        TextView tvServicePrice = holder.getView(R.id.tv_service_price);
        TextView tvYqPrice = holder.getView(R.id.tv_yq_price);
        TextView tvNowPrice = holder.getView(R.id.tv_now_price);
        TextView tvState = holder.getView(R.id.tv_state);

        tvBackTime.setText(DateUtils.getFormData(bean.getCreateTime()));
        tvBackMoney.setText(bean.getNowMoney() + "元");
        tvServicePrice.setText(bean.getServiceMoney() + "元");
        tvYqPrice.setText(bean.getOverDueMoney() + "元");
        tvNowPrice.setText(bean.getBuyMoney() + "元");
        switch (bean.getState()){
            case 0:
                tvState.setText("还款中");
                break;
            case 1:
                tvState.setText("已还款");
                break;
            case -1:
                tvState.setText("已失效");
                break;
        }

    }
}
