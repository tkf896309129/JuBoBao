package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.PjCarListBean;
import com.example.huoshangkou.jubowan.utils.GlideUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：PjOrderDetailAdapter
 * 描述：立即下单界面
 * 创建时间：2017-12-06  14:08
 */

public class PjOrderDetailAdapter extends BaseAbstractAdapter<PjCarListBean> {
    public PjOrderDetailAdapter(Context context, List<PjCarListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, PjCarListBean bean, int position) {
        ImageView imgPic = holder.getView(R.id.iv_shop_car);
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvCarDesc = holder.getView(R.id.tv_price);
        RelativeLayout rlCar = holder.getView(R.id.rl_car);
        TextView tvNum = holder.getView(R.id.tv_num);

        GlideUtils.getInstance().displayImage(bean.getPic(), context, imgPic);
        tvName.setText("配件信息：" + bean.getName() + "/"  + bean.getGuiGeVal() + "/" + bean.getModelTitle());
        tvCarDesc.setText("￥" + bean.getPrice());
        tvNum.setText("x" + bean.getNumber());

    }
}
