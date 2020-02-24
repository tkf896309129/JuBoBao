package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.PayOrderBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：PayOrderAdapter
 * 描述：
 * 创建时间：2017-03-23  15:41
 */

public class PayOrderAdapter extends BaseAbstractAdapter<PayOrderBean> {


    private int itemClick = -1;

    public PayOrderAdapter(Context context, List<PayOrderBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, PayOrderBean bean, int position) {
        ImageView imgType = holder.getView(R.id.iv_pay_type);
        TextView tvPayType = holder.getView(R.id.tv_pay_type);
        TextView tvIntro = holder.getView(R.id.tv_intro);
        ImageView imgCheck = holder.getView(R.id.img_check);

        imgType.setImageResource(bean.getImageType());
        tvPayType.setText(bean.getBankType());
        tvIntro.setText(bean.getBankIntro());
        imgCheck.setImageResource(R.mipmap.gouxuany_icon_off);

        if (itemClick == position) {
            imgCheck.setImageResource(R.mipmap.gouxuany_icon_on);
        } else {
            imgCheck.setImageResource(R.mipmap.gouxuany_icon_off);
        }
    }


    public void setItemClick(int position) {
        itemClick = position;
    }


}
