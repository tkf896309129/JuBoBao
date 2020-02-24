package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.PositionBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：ChangePositionAdapter
 * 描述：
 * 创建时间：2017-03-29  11:37
 */

public class ChangePositionAdapter extends BaseAbstractAdapter<PositionBean> {

    private int itemClick = -1;


    public ChangePositionAdapter(Context context, List<PositionBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, PositionBean bean, int position) {

        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvAddress = holder.getView(R.id.tv_address);
        ImageView imgCheck = holder.getView(R.id.iv_check);
        tvName.setText(bean.getName());
        tvAddress.setText(bean.getAddress());

        if (position == itemClick) {
            imgCheck.setVisibility(View.VISIBLE);
        } else {
            imgCheck.setVisibility(View.GONE);
        }

    }


    public void setItemClickPosition(int position) {
        itemClick = position;
    }
}
