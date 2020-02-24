package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.CheckApplyBean;
import com.example.huoshangkou.jubowan.utils.GlideUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：YwChooseAdapter
 * 描述：
 * 创建时间：2019-11-19  13:56
 */

public class YwChooseAdapter extends BaseAbstractAdapter<CheckApplyBean> {
    public YwChooseAdapter(Context context, List<CheckApplyBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, CheckApplyBean bean, int position) {
        ImageView imgType = holder.getView(R.id.iv_approve_type);
        TextView tvType = holder.getView(R.id.tv_approve_type);
        imgType.setImageResource(bean.getImgType());
        tvType.setText(bean.getType());
    }
}
