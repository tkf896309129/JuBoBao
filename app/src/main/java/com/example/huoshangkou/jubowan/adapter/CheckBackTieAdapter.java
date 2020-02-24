package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.RepliesListBean;
import com.example.huoshangkou.jubowan.utils.DateUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：CheckBackTieAdapter
 * 描述：
 * 创建时间：2017-04-14  09:43
 */

public class CheckBackTieAdapter extends BaseAbstractAdapter<RepliesListBean> {

    public CheckBackTieAdapter(Context context, List<RepliesListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, RepliesListBean bean, int position) {

        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvContent = holder.getView(R.id.tv_content);
        TextView tvTime = holder.getView(R.id.tv_time);


        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append(bean.getNickname()+":"+bean.getRepliesText());
        spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.main_tab_blue)), 0, bean.getNickname().length()+1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        tvName.setText(spannableString);
        tvContent.setText("回复主贴："+bean.getPostTitle());
        tvTime.setText(DateUtils.getFormMinuteData(bean.getCreateTime()));

    }
}
