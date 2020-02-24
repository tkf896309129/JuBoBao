package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.TrustRecordBean;
import com.example.huoshangkou.jubowan.bean.UseRecordBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：TrustRecordAdapter
 * 描述：
 * 创建时间：2018-08-22  13:30
 */

public class TrustRecordAdapter extends BaseAbstractAdapter<TrustRecordBean.ReObjBean> {
    public TrustRecordAdapter(Context context, List<TrustRecordBean.ReObjBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, TrustRecordBean.ReObjBean bean, int position) {
        TextView tvTime = holder.getView(R.id.tv_apply_time);
        TextView tvMoneyEd = holder.getView(R.id.tv_money_ed);
        TextView tvEdTime = holder.getView(R.id.tv_ed_time);
        TextView tvTrustState = holder.getView(R.id.tv_trust_state);
        TextView tvApplyMan = holder.getView(R.id.tv_apply_man);

        tvTime.setText(bean.getDateTime());
        tvMoneyEd.setText(bean.getQuota());
        tvEdTime.setText(bean.getTimeLimit());
        tvTrustState.setText(bean.getState());
        tvApplyMan.setText(bean.getLinkMan());
    }
}
