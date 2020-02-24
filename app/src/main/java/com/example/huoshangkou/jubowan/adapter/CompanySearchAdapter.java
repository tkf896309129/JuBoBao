package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.widget.ContactItemInterface;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：CompanySearchAdapter
 * 描述：
 * 创建时间：2018-05-03  16:28
 */

public class CompanySearchAdapter extends BaseAbstractAdapter<ContactItemInterface> {

    private String title;

    public CompanySearchAdapter(Context context, String title, List<ContactItemInterface> listData, int resId) {
        super(context, listData, resId);
        this.title = title;
    }

    @Override
    public void convert(ViewHolder holder, ContactItemInterface bean, int position) {
        TextView textView = holder.getView(R.id.cityName);
        TextView tvSkdw = holder.getView(R.id.tv_skdw);
        TextView tvGroupName = holder.getView(R.id.tv_group_name);
        textView.setText("");
        tvGroupName.setText(bean.getDisplayInfo());
        if (StringUtils.isNoEmpty(title)) {
            tvSkdw.setText(title);
        } else {
            tvSkdw.setText("收款单位");
        }
    }
}
