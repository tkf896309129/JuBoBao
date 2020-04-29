package com.example.huoshangkou.jubowan.chat.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.chat.adapter
 * 类名：SlideAdapter
 * 描述：
 * 创建时间：2020-04-21  08:58
 */

public class SlideAdapter extends BaseAbstractAdapter<String> {
    public SlideAdapter(Context context, List<String> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, String bean, int position) {
        TextView tvSlide = holder.getView(R.id.tv_slide);
        tvSlide.setText(bean);
    }
}
