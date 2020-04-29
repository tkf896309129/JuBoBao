package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.CustomerDataListBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：CustomerDataNewAdapter
 * 描述：
 * 创建时间：2019-08-28  13:17
 */

public class CustomerDataNewAdapter extends BaseAbstractAdapter<CustomerDataListBean> {
    public CustomerDataNewAdapter(Context context, List<CustomerDataListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, CustomerDataListBean bean, int position) {
        ImageView imgPic = holder.getView(R.id.iv_pic);
        TextView tvName = holder.getView(R.id.tv_name);

        tvName.setText(bean.getName());
        imgPic.setImageResource(bean.getImg());

    }
}
