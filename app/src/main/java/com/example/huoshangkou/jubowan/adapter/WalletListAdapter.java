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
import com.example.huoshangkou.jubowan.bean.WalletListBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：WalletListAdapter
 * 描述：
 * 创建时间：2017-10-19  13:58
 */

public class WalletListAdapter extends BaseAbstractAdapter<WalletListBean> {
    public WalletListAdapter(Context context, List<WalletListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, WalletListBean bean, int position) {
        TextView tvPrice = holder.getView(R.id.tv_price);
        TextView tvTime = holder.getView(R.id.tv_time);
        TextView tvType = holder.getView(R.id.tv_1);

        if (bean.getAddorSub().equals("+")) {
            tvType.setText("收到红包");
        } else if (bean.getAddorSub().equals("-")) {
            tvType.setText("提现");
        }


        tvTime.setText(bean.getCreateTime());

        String price = bean.getAddorSub() + bean.getLuckyMoney();

        int linePosition = price.length();

        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append(price);
        spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.red)), 0, linePosition - 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        //字体大小
        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(48);
        spannableString.setSpan(sizeSpan, 1, linePosition - 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        tvPrice.setText(spannableString);

    }
}
