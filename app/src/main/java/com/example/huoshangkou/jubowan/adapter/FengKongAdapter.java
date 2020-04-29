package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.FengKongListBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：FengKongAdapter
 * 描述：
 * 创建时间：2019-07-23  09:11
 */

public class FengKongAdapter extends BaseAbstractAdapter<FengKongListBean.DBean.ResultBean> {
    public FengKongAdapter(Context context, List<FengKongListBean.DBean.ResultBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, FengKongListBean.DBean.ResultBean bean, int position) {
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvState = holder.getView(R.id.tv_state);
        tvName.setText(bean.getTitle());
        //0待审核1有效-1无效-2为空
        switch (bean.getState()) {
            case 0:
                tvState.setText("待审核  ");
                break;
            case 1:
                tvState.setText("审核通过  ");
                break;
            case -1:
                tvState.setText("审核未通过  ");
                break;
            case -2:
                tvState.setText("");
                break;
        }
    }
}
