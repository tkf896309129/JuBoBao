package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.YuanFuSearchBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：YuanFuSearchAdapter
 * 描述：
 * 创建时间：2019-04-29  11:08
 */

public class YuanFuSearchAdapter extends BaseAbstractAdapter<YuanFuSearchBean.DBean.ResultBean>{

    public YuanFuSearchAdapter(Context context, List<YuanFuSearchBean.DBean.ResultBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, YuanFuSearchBean.DBean.ResultBean bean, int position) {
        TextView tvCompnay = holder.getView(R.id.tv_company);
        tvCompnay.setText(bean.getValue());
    }
}
