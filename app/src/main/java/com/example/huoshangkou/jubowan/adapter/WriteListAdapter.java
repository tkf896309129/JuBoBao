package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.WriteListBean;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：WriteListAdapter
 * 描述：
 * 创建时间：2017-08-09  14:22
 */

public class WriteListAdapter extends BaseAbstractAdapter<WriteListBean> {
    public WriteListAdapter(Context context, List<WriteListBean> listData, int resId) {
        super(context, listData, resId);

    }

    @Override
    public void convert(ViewHolder holder, WriteListBean bean, int position) {
        TextView tvTitle = holder.getView(R.id.tv_title);
        TextView tvContent = holder.getView(R.id.tv_content);
        TextView tvDesc = holder.getView(R.id.tv_write_desc);

        if (!StringUtils.isNoEmpty(bean.getDesc())) {
            tvContent.setVisibility(View.VISIBLE);
        } else {
            tvContent.setVisibility(View.GONE);
        }

        tvTitle.setText(bean.getTitle());
        tvContent.setText(bean.getContent());
        tvDesc.setText(bean.getDesc());

    }
}
