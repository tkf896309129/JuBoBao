package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.ApproveBankListBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：BorrowBankAdapter
 * 描述：
 * 创建时间：2018-02-09  12:46
 */

public class BorrowBankAdapter extends BaseAbstractAdapter<ApproveBankListBean> {
    public BorrowBankAdapter(Context context, List<ApproveBankListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, ApproveBankListBean bean, int position) {
        RelativeLayout rlEdit = holder.getView(R.id.rl_edit);
        rlEdit.setVisibility(View.GONE);

        TextView tvBankName = holder.getView(R.id.tv_bank);
        TextView tvRzdw = holder.getView(R.id.tv_rzdw);
        TextView tvComName = holder.getView(R.id.tv_company_name);
        TextView tvBankAccount = holder.getView(R.id.tv_bank_account);

        tvBankAccount.setText(bean.getZH());
        tvBankName.setText(bean.getName());
        tvComName.setText(bean.getKHH());
        tvRzdw.setText("入账单位：");

    }
}
