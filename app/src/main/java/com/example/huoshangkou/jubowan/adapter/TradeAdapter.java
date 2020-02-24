package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.HomeBrandListBean;
import com.example.huoshangkou.jubowan.bean.TradeBean;
import com.example.huoshangkou.jubowan.utils.GlideUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：TradeAdapter
 * 描述：
 * 创建时间：2017-02-07  09:59
 */

public class TradeAdapter extends BaseAbstractAdapter<HomeBrandListBean> {

    public TradeAdapter(Context context, List<HomeBrandListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, HomeBrandListBean bean, int position) {
//        TextView tvTradeType = holder.getView(R.id.tv_trade_type);
//        TextView tvTradeNum = holder.getView(R.id.tv_trade_num);

        ImageView imgBrand = holder.getView(R.id.img_brand);
        TextView tvBrands = holder.getView(R.id.tv_brands);

        tvBrands.setText(bean.getBrandTitle());

        GlideUtils.getInstance().displayImage(bean.getPics(), context, imgBrand);
//        tvTradeType.setText(bean.getTradeType());
//        tvTradeNum.setText(bean.getTradeNum());

    }
}
