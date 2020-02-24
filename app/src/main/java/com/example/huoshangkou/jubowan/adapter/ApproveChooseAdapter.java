package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.ApproveChooseBean;
import com.example.huoshangkou.jubowan.bean.ApproveTypeListBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：ApproveChooseAdapter
 * 描述：
 * 创建时间：2017-03-07  09:04
 */

public class ApproveChooseAdapter extends BaseAbstractAdapter<ApproveTypeListBean> {

    public ApproveChooseAdapter(Context context, List<ApproveTypeListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, ApproveTypeListBean bean, int position) {

        TextView tvApproveType = holder.getView(R.id.tv_approve_type);
        tvApproveType.setText(bean.getType());
    }
}
