package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.MoneyMessageListBean;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：MessageListAdapter
 * 描述：
 * 创建时间：2017-12-15  13:33
 */

public class MessageListAdapter extends BaseAbstractAdapter<MoneyMessageListBean> {
    //是否是齐家借款
    private String isQiJia = "";

    public MessageListAdapter(Context context, List<MoneyMessageListBean> listData, int resId) {
        super(context, listData, resId);
    }

    public MessageListAdapter(Context context, List<MoneyMessageListBean> listData, int resId,String isQiJia) {
        super(context, listData, resId);
        this.isQiJia = isQiJia;
    }

    @Override
    public void convert(ViewHolder holder, MoneyMessageListBean bean, int position) {
        //直接锁仓
        TextView tvCompany = holder.getView(R.id.tv_loan_company);
        TextView tvLoanMoney = holder.getView(R.id.tv_loan_money);
        TextView tvLoanId = holder.getView(R.id.tv_loan_id);
        TextView tvLoanTime = holder.getView(R.id.tv_loan_time);
        TextView tvLeft_1 = holder.getView(R.id.tv_left_1);
        TextView tvLeft_2 = holder.getView(R.id.tv_left_2);
        TextView tvLeft_3 = holder.getView(R.id.tv_left_3);
        TextView tvLeft_4 = holder.getView(R.id.tv_left_4);
        
        if(StringUtils.isNoEmpty(isQiJia) && isQiJia.equals("借据审批")){
            tvLeft_1.setText("借款金额：￥"+bean.getLoanAmount());
            tvLeft_2.setText("借款人名称");
            tvLeft_3.setText("借款时间");
            tvLeft_4.setText("借款借据号");

            tvLoanMoney.setText(bean.getLoanLinkMan());
            tvLoanId.setText(bean.getCreateTime());
            tvLoanTime.setText(bean.getContractNO());
        }else{
            tvCompany.setText(bean.getCompany());
            tvLoanMoney.setText(bean.getRzMoey());
            tvLoanId.setText(bean.getContractNO());
            tvLoanTime.setText(bean.getAuditTimeYea());
        }
    }
}
