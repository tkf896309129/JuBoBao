package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.MessageTypeListBean;
import com.example.huoshangkou.jubowan.utils.StringBuilderUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：MineZanAdapter
 * 描述：
 * 创建时间：2017-04-21  15:13
 */

public class MineZanAdapter extends BaseAbstractAdapter<MessageTypeListBean> {

    public MineZanAdapter(Context context, List<MessageTypeListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, MessageTypeListBean bean, int position) {
        TextView tvTitle = holder.getView(R.id.tv_title);
        TextView tvTime = holder.getView(R.id.tv_time);
        TextView tvContent = holder.getView(R.id.tv_content);

        tvTitle.setText(bean.getMessageTitle());

        tvTime.setText(bean.getCreatTime());

        int length = bean.getMessageContent().indexOf("回复");
        if (length > 0) {
            SpannableStringBuilder spannableStringBuilder = StringBuilderUtils.getInstance().setTextColor(bean.getMessageContent(), context.getResources().getColor(R.color.back_tie_blue), 0, length);
            tvContent.setText(spannableStringBuilder);
        } else {
            tvContent.setText(bean.getMessageContent());
        }


    }
}
