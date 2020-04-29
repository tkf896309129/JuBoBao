package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.SelectManBean;
import com.example.huoshangkou.jubowan.bean.SyYuanListBean;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.widget.ContactItemInterface;
import com.example.huoshangkou.jubowan.widget.ContactListAdapter;

import java.util.List;

import butterknife.Bind;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：SyYuanAdapter
 * 描述：
 * 创建时间：2017-12-18  10:36
 */

public class SyYuanAdapter extends ContactListAdapter {

    private String type = "";

    public SyYuanAdapter(Context _context, int _resource, List<SelectManBean> _items, String type) {
        super(_context, _resource, _items);
        this.type = type;
    }


    @Override
    public void populateDataForRow(View parentView, ContactItemInterface item, int position) {
        super.populateDataForRow(parentView, item, position);

        TextView textView = (TextView) parentView.findViewById(R.id.cityName);
        TextView tvSkdw = (TextView) parentView.findViewById(R.id.tv_skdw);
        TextView tvGroupName = (TextView) parentView.findViewById(R.id.tv_group_name);
        textView.setText("");
        tvGroupName.setText(item.getDisplayInfo());

        if (StringUtils.isNoEmpty(type)) {
            tvSkdw.setText(type);
        } else {
            tvSkdw.setText("收款单位");
        }

    }
}
