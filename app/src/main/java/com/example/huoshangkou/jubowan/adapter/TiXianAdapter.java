package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.TiXianListBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：TiXianAdapter
 * 描述：
 * 创建时间：2017-05-26  11:35
 */

public class TiXianAdapter extends BaseAbstractAdapter<TiXianListBean> {
    public TiXianAdapter(Context context, List<TiXianListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, TiXianListBean bean, int position) {

        TextView tvType = holder.getView(R.id.tv_type);
        TextView tvTime = holder.getView(R.id.tv_time);
        TextView tvMoney = holder.getView(R.id.tv_money);

        tvTime.setText(bean.getTxTime());
        String price = bean.getTxMoney()+"元";

        int index = price.indexOf(".");

        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append(price);
        spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.main_tab_blue)), 0, price.length()-1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(66);
        spannableString.setSpan(sizeSpan, 0, index, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        tvMoney.setText(spannableString);
        tvType.setText(bean.getTxState());

    }
}
