package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.SignRecordListBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：AddSignRecordAdapter
 * 描述：
 * 创建时间：2017-04-18  16:10
 */

public class AddSignRecordAdapter extends BaseAbstractAdapter<SignRecordListBean> {
    public AddSignRecordAdapter(Context context, List<SignRecordListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, SignRecordListBean bean, int position) {

        TextView tvRemark = holder.getView(R.id.tv_remark);
        TextView tvSignTime = holder.getView(R.id.tv_sign_time);
        TextView tvSignState = holder.getView(R.id.tv_sign_state);
        TextView tvTime = holder.getView(R.id.tv_time);


        tvRemark.setText("备注：" + bean.getRemark());
        tvTime.setText(bean.getLeakTime() + " " + bean.getTimeSpan());
        tvSignTime.setText(bean.getCreateTime());
        if (bean.getApprovalOver() == -1) {
            tvSignState.setText("未审批");
            tvSignState.setTextColor(context.getResources().getColor(R.color.gray));
        } else if (bean.getApprovalOver() == 1) {
            tvSignState.setText("已通过");
            tvSignState.setTextColor(context.getResources().getColor(R.color.main_tab_blue));
        } else if (bean.getApprovalOver() == 0) {
            tvSignState.setText("未通过");
            tvSignState.setTextColor(context.getResources().getColor(R.color.red));
        }

    }
}
