package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.CustomerVisitorBean;
import com.example.huoshangkou.jubowan.utils.DateUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：CustomerVisitorRecordAdapter
 * 描述：
 * 创建时间：2019-08-29  13:28
 */

public class CustomerVisitorRecordAdapter extends BaseAbstractAdapter<CustomerVisitorBean.DBean.ReListBean> {
    public CustomerVisitorRecordAdapter(Context context, List<CustomerVisitorBean.DBean.ReListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, CustomerVisitorBean.DBean.ReListBean bean, int position) {
        TextView tvTime = holder.getView(R.id.tv_time);
        TextView tvContent = holder.getView(R.id.tv_content);
        TextView tvComment = holder.getView(R.id.tv_comment);

        tvTime.setText(DateUtils.getFormMinuteDesData(bean.getVisitDate()));
        tvComment.setText(bean.getEvaluate());
        tvContent.setText(bean.getVisitContent());

    }
}
