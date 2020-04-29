package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.CountryCheckBean;
import com.example.huoshangkou.jubowan.utils.StringBuilderUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：CountryCheckAdapter
 * 描述：
 * 创建时间：2019-03-27  11:12
 */

public class CountryCheckAdapter extends BaseAbstractAdapter<CountryCheckBean.DBean.ResultBean.ChildBean> {
    public CountryCheckAdapter(Context context, List<CountryCheckBean.DBean.ResultBean.ChildBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, CountryCheckBean.DBean.ResultBean.ChildBean bean, int position) {
        TextView tvTitle = holder.getView(R.id.tv_title);
        TextView tvContent = holder.getView(R.id.tv_content);

        tvTitle.setText(bean.getChildTitle());
        tvContent.setText(bean.getChildDescribe()+"   ");
    }
}
