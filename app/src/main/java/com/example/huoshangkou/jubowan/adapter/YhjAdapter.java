package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.YhjBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：YhjAdapter
 * 描述：
 * 创建时间：2018-12-07  15:45
 */

public class YhjAdapter extends BaseAbstractAdapter<YhjBean.ReObjBean> {
    public YhjAdapter(Context context, List<YhjBean.ReObjBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, YhjBean.ReObjBean bean, int position) {
        RelativeLayout rlYhj = holder.getView(R.id.rl_yhj);
        TextView tvDays = holder.getView(R.id.tv_days);
        TextView tvTimes = holder.getView(R.id.tv_times);
        TextView tvRules = holder.getView(R.id.tv_rules);
        switch (bean.getState()) {
            case "未使用":
                rlYhj.setBackgroundResource(R.mipmap.yhj_deep);
                break;
            case "已过期":
            case "已使用":
                rlYhj.setBackgroundResource(R.mipmap.yhj_alpha);
                break;
        }

        tvDays.setText(bean.getName());
        tvTimes.setText(bean.getPeriodOfValidity());
        tvRules.setText(bean.getDescription());
    }
}
