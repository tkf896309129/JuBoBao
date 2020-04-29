package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.InternalPhoneBean;
import com.example.huoshangkou.jubowan.bean.SelectManBean;
import com.example.huoshangkou.jubowan.widget.ContactItemInterface;
import com.example.huoshangkou.jubowan.widget.ContactListAdapter;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：InternalPhoneAdapter
 * 描述：
 * 创建时间：2019-08-23  08:45
 */

public class InternalPhoneAdapter extends ContactListAdapter {
    public InternalPhoneAdapter(Context _context, int _resource, List<SelectManBean> _items) {
        super(_context, _resource, _items);
    }

    @Override
    public void populateDataForRow(View parentView, ContactItemInterface item, int position) {
        super.populateDataForRow(parentView, item, position);
//
        TextView textView = (TextView) parentView.findViewById(R.id.cityName);
        textView.setText(item.getDisplayInfo() + "(" + item.getGroupName() + ")");
    }

    //    public InternalPhoneAdapter(Context context, List<InternalPhoneBean> listData, int resId) {
//        super(context, listData, resId);
//    }
//
//    @Override
//    public void convert(ViewHolder holder, InternalPhoneBean bean, int position) {
//        TextView textView = holder.getView(R.id.tv_country);
//        textView.setText(bean.getZh() + "(" + bean.getEn() + ")");
//
//    }
}
