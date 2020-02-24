package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.AfterOrderBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：AfterOrderAdapter
 * 描述：
 * 创建时间：2017-05-16  15:08
 */

public class AfterOrderAdapter extends BaseAbstractAdapter<AfterOrderBean> {
    public AfterOrderAdapter(Context context, List<AfterOrderBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, AfterOrderBean bean, int position) {
        TextView tvLeft = holder.getView(R.id.tv_left);
        TextView tvRight = holder.getView(R.id.tv_right);

        tvLeft.setText(bean.getLeft());
        tvRight.setText(bean.getRight());
    }
}
