package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.AddDateTypeBean;
import com.example.huoshangkou.jubowan.bean.BaseCenterBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：BaseCenterAdapter
 * 描述：
 * 创建时间：2019-08-28  09:48
 */

public class BaseCenterAdapter extends BaseAbstractAdapter<AddDateTypeBean> {
    public BaseCenterAdapter(Context context, List<AddDateTypeBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, AddDateTypeBean bean, int position) {
        TextView tvType = holder.getView(R.id.tv_type);
        ImageView imgCheck = holder.getView(R.id.iv_check);
        tvType.setText(bean.getType());
        if(bean.isCheck()){
            imgCheck.setVisibility(View.VISIBLE);
            tvType.setTextColor(context.getResources().getColor(R.color.main_tab_blue));
        }else {
            tvType.setTextColor(context.getResources().getColor(R.color.address_black_key));
            imgCheck.setVisibility(View.GONE);
        }

    }
}
