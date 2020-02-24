package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.BjListBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：BaoJinAdapter
 * 描述：
 * 创建时间：2018-01-10  16:09
 */

public class BaoJinAdapter extends BaseAbstractAdapter<BjListBean> {
    public BaoJinAdapter(Context context, List<BjListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, BjListBean bean, int position) {
        TextView tvData = holder.getView(R.id.tv_data);
        TextView tvMoney = holder.getView(R.id.tv_money);
        TextView tvState = holder.getView(R.id.tv_state);

        tvData.setText(bean.getBorrow_date());
        tvMoney.setText(bean.getMoney());
        tvState.setText(bean.getStatus());


        if (position % 2 == 1) {
            tvData.setBackgroundColor(context.getResources().getColor(R.color.bj_color_1));
            tvMoney.setBackgroundColor(context.getResources().getColor(R.color.bj_color_1));
            tvState.setBackgroundColor(context.getResources().getColor(R.color.bj_color_1));
        } else if (position % 2 == 0) {
            tvData.setBackgroundColor(context.getResources().getColor(R.color.bj_color_2));
            tvMoney.setBackgroundColor(context.getResources().getColor(R.color.bj_color_2));
            tvState.setBackgroundColor(context.getResources().getColor(R.color.bj_color_2));
        }


    }
}
