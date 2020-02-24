package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.LoanIntroListBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：LoanIouAdapter
 * 描述：
 * 创建时间：2017-09-12  09:32
 */

public class LoanIouAdapter extends BaseAbstractAdapter<LoanIntroListBean> {
    public LoanIouAdapter(Context context, List<LoanIntroListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, LoanIntroListBean bean, int position) {
        TextView tvMoney = holder.getView(R.id.tv_money);
        TextView tvState = holder.getView(R.id.tv_state);
        TextView tvOrderNum = holder.getView(R.id.tv_order_num);
        TextView tvLoanTime = holder.getView(R.id.tv_loan_time);

        tvMoney.setText("借款 ￥" + bean.getLoanAmount());
        tvState.setText(bean.getToState());
        tvOrderNum.setText(bean.getNO());
        tvLoanTime.setText(bean.getCreateTime());

    }
}
