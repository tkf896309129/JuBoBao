package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.RecentlyTwoBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：DataLineAdapter
 * 描述：
 * 创建时间：2019-09-11  13:31
 */

public class DataLineAdapter extends BaseAbstractAdapter<RecentlyTwoBean> {
    public DataLineAdapter(Context context, List<RecentlyTwoBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, RecentlyTwoBean bean, int position) {
        TextView tvTime = holder.getView(R.id.tv_time);
        TextView tvType = holder.getView(R.id.tv_type);
        TextView tvNum = holder.getView(R.id.tv_num);

        tvTime.setText(bean.getMonth());
        tvType.setText(bean.getType());
        tvNum.setText(bean.getNums());

    }
}
