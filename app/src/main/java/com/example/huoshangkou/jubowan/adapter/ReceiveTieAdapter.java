package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.SecondBackTieBean;
import com.example.huoshangkou.jubowan.utils.StringBuilderUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：ReceiveTieAdapter
 * 描述：贴中贴
 * 创建时间：2017-04-07  16:16
 */

public class ReceiveTieAdapter extends BaseAbstractAdapter<SecondBackTieBean> {

    private String name;

    public ReceiveTieAdapter(Context context, List<SecondBackTieBean> listData, int resId, String name) {
        super(context, listData, resId);
        this.name = name;
    }

    @Override
    public void convert(ViewHolder holder, SecondBackTieBean bean, int position) {

        TextView tvReplies = holder.getView(R.id.tv_receive_tie);

        String str = "";
//        if (StringUtils.isNoEmpty(name) && StringUtils.isNoEmpty(bean.getNicknamed()) && name.equals(bean.getNicknamed())) {
//            str = bean.getNickname() + "：" + bean.getRepliesText();
//            SpannableStringBuilder spannableStringBuilder = StringBuilderUtils.getInstance().setTextColor(str, context.getResources().getColor(R.color.back_tie_blue), 0, bean.getNickname().length());
//            tvReplies.setText(spannableStringBuilder);
//        } else {

            String nickName = "<font color=\"#1C86EE\">" + bean.getNickname() + "</font>";
            String nickNamed = "<font color=\"#1C86EE\">" + bean.getNicknamed() + "</font>";
            str = nickName + "回复" + nickNamed + "：" + bean.getRepliesText();

            tvReplies.setText(Html.fromHtml(str));
//        }

    }
}
