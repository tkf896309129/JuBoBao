package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.KfMemberBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：KfMemberAdapter
 * 描述：
 * 创建时间：2019-03-06  10:41
 */

public class KfMemberAdapter extends BaseAbstractAdapter<KfMemberBean.DBean.ReObjBean> {
    public KfMemberAdapter(Context context, List<KfMemberBean.DBean.ReObjBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, KfMemberBean.DBean.ReObjBean bean, int position) {
        TextView tvName = holder.getView(R.id.tv_name);
        tvName.setText(bean.getRealName());
    }
}
