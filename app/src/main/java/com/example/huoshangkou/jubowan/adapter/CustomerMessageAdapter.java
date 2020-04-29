package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.CustomerMessageBean;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.inter.StringPositionCallBack;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：CustomerMessageAdapter
 * 描述：
 * 创建时间：2019-08-28  19:28
 */

public class CustomerMessageAdapter extends BaseAbstractAdapter<CustomerMessageBean.DBean.ResultBean> {

    private OnPositionClick agreeCallback;
    private OnPositionClick disagreeCallback;

    public CustomerMessageAdapter(Context context, List<CustomerMessageBean.DBean.ResultBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, CustomerMessageBean.DBean.ResultBean bean, final int position) {
        TextView tvCompanyName = holder.getView(R.id.tv_company_name);
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvRoleName = holder.getView(R.id.tv_role_name);
        TextView tvDisagree = holder.getView(R.id.tv_disagree);
        TextView tvAgree = holder.getView(R.id.tv_agree);

        tvCompanyName.setText(bean.getCompany());
        tvName.setText(bean.getName());
        tvRoleName.setText(bean.getProfession());


        tvDisagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disagreeCallback.onPositionClick(position);
            }
        });
        tvAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agreeCallback.onPositionClick(position);
            }
        });
    }

    public void setAgreeCallback(OnPositionClick agreeCallback) {
        this.agreeCallback = agreeCallback;
    }

    public void setDisagreeCallback(OnPositionClick disagreeCallback) {
        this.disagreeCallback = disagreeCallback;
    }
}
