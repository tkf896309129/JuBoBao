package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.CalculatorBean;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：CalculatorAdapter
 * 描述：
 * 创建时间：2017-04-14  16:02
 */

public class CalculatorAdapter extends BaseAbstractAdapter<CalculatorBean> {

    private String type;

    public CalculatorAdapter(Context context, List<CalculatorBean> listData, int resId) {
        super(context, listData, resId);
    }

    public CalculatorAdapter(Context context, List<CalculatorBean> listData, int resId, String type) {
        super(context, listData, resId);
        this.type = type;
    }

    @Override
    public void convert(ViewHolder holder, CalculatorBean bean, int position) {
        TextView tvLeft = holder.getView(R.id.tv_left);
        TextView tvRight = holder.getView(R.id.tv_right);

        tvLeft.setText(bean.getThick());

        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (StringUtils.isNoEmpty(type)) {

            spannableStringBuilder.append(bean.getPrice());
            int num = bean.getPrice().length();
            //字体颜色
            ForegroundColorSpan backgroundColorSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.red));
            spannableStringBuilder.setSpan(backgroundColorSpan, 0, num - 2, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            tvRight.setText(spannableStringBuilder);
        } else {
            tvRight.setText(bean.getArea());
            spannableStringBuilder.append(bean.getArea());
            int index = bean.getArea().length();
            //字体颜色
            ForegroundColorSpan backgroundColorSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.red));
            spannableStringBuilder.setSpan(backgroundColorSpan, 0, index - 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            tvRight.setText(spannableStringBuilder);
        }


    }
}
