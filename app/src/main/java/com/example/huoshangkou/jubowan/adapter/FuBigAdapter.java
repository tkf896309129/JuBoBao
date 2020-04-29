package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.BigFuTypeBean;
import com.example.huoshangkou.jubowan.bean.BigYuanBrandBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：FuBigAdapter
 * 描述：
 * 创建时间：2019-03-25  09:10
 */

public class FuBigAdapter extends BaseAbstractAdapter<BigYuanBrandBean.DBean.ResultBean> {
    public FuBigAdapter(Context context, List<BigYuanBrandBean.DBean.ResultBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, BigYuanBrandBean.DBean.ResultBean bean, int position) {
        TextView tvName = holder.getView(R.id.tv_order_type);
        tvName.setText(bean.getBrandName());
        if(bean.isCheck()){
            tvName.setTextColor(context.getResources().getColor(R.color.main_tab_blue_all));
        }else {
            tvName.setTextColor(context.getResources().getColor(R.color.address_alpha_black));
        }
    }
}
