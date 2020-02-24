package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.MonthRegitBean;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：MonthRegitAdapter
 * 描述：
 * 创建时间：2019-09-29  09:43
 */

public class MonthRegitAdapter extends BaseAbstractAdapter<MonthRegitBean.DBean.ResultBean> {

    private String type = "";

    public MonthRegitAdapter(Context context, List<MonthRegitBean.DBean.ResultBean> listData, String type, int resId) {
        super(context, listData, resId);
        this.type = type;
    }

    @Override
    public void convert(ViewHolder holder, MonthRegitBean.DBean.ResultBean bean, int position) {
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvTime = holder.getView(R.id.tv_time);
        TextView tvSaleName = holder.getView(R.id.tv_sale_name);
        TextView tvRegitName = holder.getView(R.id.tv_regit_name);
        switch (type) {
            case "1":
                tvRegitName.setText("客户名称：" + bean.getLinkMan());
                tvSaleName.setText("注册经理：" +StringUtils.getNoEmptyStr(bean.getRegistrateManager())+"("+StringUtils.getNoEmptyStr(bean.getWarArea())+")");
                break;
            case "2":
                tvSaleName.setText("转化经理：" + StringUtils.getNoEmptyStr(bean.getConvertManager())+"("+StringUtils.getNoEmptyStr(bean.getWarArea())+")");
                tvRegitName.setText("客户名称：" + bean.getLinkMan());
                break;
        }
        tvName.setText( bean.getCompany());
        tvTime.setText(bean.getFormatCreateTime());

    }
}
