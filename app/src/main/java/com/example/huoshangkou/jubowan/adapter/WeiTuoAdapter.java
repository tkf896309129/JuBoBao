package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.WeiTuoListBean;
import com.example.huoshangkou.jubowan.bean.WeiTuoListDetailsBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：WeiTuoAdapter
 * 描述：
 * 创建时间：2018-04-17  10:21
 */

public class WeiTuoAdapter extends BaseAbstractAdapter<WeiTuoListDetailsBean> {
    public WeiTuoAdapter(Context context, List<WeiTuoListDetailsBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, WeiTuoListDetailsBean bean, int position) {
        TextView tvClass = holder.getView(R.id.tv_class);
        TextView tvBrand = holder.getView(R.id.tv_brand);
        TextView tvLevel = holder.getView(R.id.tv_level);
        TextView tvThick = holder.getView(R.id.tv_thick);
        TextView tvStandard = holder.getView(R.id.tv_standard);
        TextView tvNum = holder.getView(R.id.tv_num);
        TextView tvUnit = holder.getView(R.id.tv_unit);
        tvClass.setText(bean.getClassTitle());
        tvBrand.setText(bean.getBrandTitle());
        tvLevel.setText(bean.getLevelTitle());
        tvThick.setText(bean.getWeight());
        tvStandard.setText(bean.getWidth()+"*"+bean.getHeight()+"");
        tvNum.setText(bean.getBaoNum());
        tvUnit.setText(bean.getPianNum());
    }
}
