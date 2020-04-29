package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.CheckApplyBean;
import com.example.huoshangkou.jubowan.bean.QuaMenuBean;
import com.example.huoshangkou.jubowan.utils.GlideUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：CheckApplyAdapter
 * 描述：
 * 创建时间：2017-03-20  08:51
 */

public class CheckApplyAdapter extends BaseAbstractAdapter<QuaMenuBean.DBean.ReListBean> {

    public CheckApplyAdapter(Context context, List<QuaMenuBean.DBean.ReListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, QuaMenuBean.DBean.ReListBean bean, int position) {
        ImageView imgType = holder.getView(R.id.iv_approve_type);
        TextView tvType = holder.getView(R.id.tv_approve_type);

        GlideUtils.getInstance().displayImage(bean.getIcon(),context,imgType);
        tvType.setText(bean.getName());

    }
}
