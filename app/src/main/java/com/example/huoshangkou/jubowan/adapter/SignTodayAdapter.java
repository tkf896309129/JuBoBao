package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.SignTimesBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：SignTodayAdapter
 * 描述：
 * 创建时间：2019-07-10  09:50
 */

public class SignTodayAdapter extends BaseAbstractAdapter<SignTimesBean.UserTrackBean> {
    public SignTodayAdapter(Context context, List<SignTimesBean.UserTrackBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, SignTimesBean.UserTrackBean bean, int position) {
        TextView tvLine = holder.getView(R.id.tv_line);
        TextView tvTime = holder.getView(R.id.tv_time);
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvAddress = holder.getView(R.id.tv_address);
        TextView tvDot = holder.getView(R.id.tv_dot);
        if (position == (listData.size() - 1)) {
            tvLine.setVisibility(View.GONE);
        }else {
            tvLine.setVisibility(View.VISIBLE);
        }
        tvTime.setText("上报时间 "+bean.getCreateTime());
        tvAddress.setText(bean.getAddress());
        tvName.setText(bean.getPoiName());
        if(position==0){
            tvDot.setBackground(context.getResources().getDrawable(R.drawable.blue_circle));
        }else {
            tvDot.setBackground(context.getResources().getDrawable(R.drawable.gray_circle));
        }

    }
}
