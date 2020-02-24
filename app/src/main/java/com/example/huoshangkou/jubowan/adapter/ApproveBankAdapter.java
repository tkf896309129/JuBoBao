package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.ApproveBankListBean;
import com.example.huoshangkou.jubowan.inter.OnApproveBankCallBack;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：ApproveBankAdapter
 * 描述：
 * 创建时间：2017-03-21  13:11
 */

public class ApproveBankAdapter extends BaseAbstractAdapter<ApproveBankListBean> {

    private OnApproveBankCallBack bankCallBack;
    private OnPositionClick positionClick;

    public ApproveBankAdapter(Context context, List<ApproveBankListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, final ApproveBankListBean bean, final int position) {

        TextView imgEdit = holder.getView(R.id.iv_edit);
        TextView imgDelete = holder.getView(R.id.iv_delete);
        TextView tvBankName = holder.getView(R.id.tv_bank);
        TextView tvComName = holder.getView(R.id.tv_company_name);
        TextView tvBankAccount = holder.getView(R.id.tv_bank_account);
        LinearLayout llClick = holder.getView(R.id.ll_lick);

        tvBankAccount.setText(bean.getBankAccount());
        tvBankName.setText(bean.getBankName());
        tvComName.setText(bean.getCompany());

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bankCallBack.onEditBankInfo(bean);
            }
        });

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bankCallBack.onDeleteBankInfo(bean.getID() + "", position);
            }
        });
        llClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                positionClick.onPositionClick(position);
            }
        });
    }

    public void setBankCallBack(OnApproveBankCallBack bankCallBack) {
        this.bankCallBack = bankCallBack;
    }

    public void setPositionClick(OnPositionClick positionClick) {
        this.positionClick = positionClick;
    }
}
