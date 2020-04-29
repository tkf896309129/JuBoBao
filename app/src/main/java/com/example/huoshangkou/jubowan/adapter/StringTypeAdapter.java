package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.OrderTypeBean;
import com.example.huoshangkou.jubowan.utils.LogUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：StringTypeAdapter
 * 描述：
 * 创建时间：2017-02-17  09:43
 */

public class StringTypeAdapter extends BaseAbstractAdapter<OrderTypeBean> {

    private int itemClickPosition = -1;
    //是否隐藏有色
    private boolean isYouSeHide = true;
    //是否隐藏膜系
    private boolean isLowHide = true;
    //是否隐藏等级
    private boolean isLevelHide = false;

    public StringTypeAdapter(Context context, List<OrderTypeBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, OrderTypeBean bean, int position) {
        TextView tvType = holder.getView(R.id.tv_order_type);
        tvType.setText(bean.getType());

        if (position == itemClickPosition) {
            tvType.setTextColor(context.getResources().getColor(R.color.main_tab_blue_all));
            tvType.setBackgroundColor(context.getResources().getColor(R.color.white_dark));
        } else {
            tvType.setTextColor(context.getResources().getColor(R.color.address_black_key));
            tvType.setBackgroundColor(context.getResources().getColor(R.color.choose_order_bg));
        }
// && bean.equals(context.getString(R.string.search_color))
        if (isYouSeHide && bean.getType().equals(context.getString(R.string.search_color))) {
            tvType.setVisibility(View.GONE);
        }
        else if(!isYouSeHide && bean.getType().equals(context.getString(R.string.search_color))){
            tvType.setVisibility(View.VISIBLE);
        }
//        else {
//            tvType.setVisibility(View.VISIBLE);
//        }

        if (isLowHide && bean.getType().equals(context.getString(R.string.search_low))) {
            tvType.setVisibility(View.GONE);
            LogUtils.i(bean.getType());
        }
        else if(!isLowHide && bean.getType().equals(context.getString(R.string.search_low))){
            tvType.setVisibility(View.VISIBLE);
        }


        if (isLevelHide && bean.getType().equals(context.getString(R.string.search_level))) {
            tvType.setVisibility(View.GONE);
            LogUtils.i(bean.getType());
        }
        else if(!isLevelHide && bean.getType().equals(context.getString(R.string.search_level))){
            tvType.setVisibility(View.VISIBLE);
        }
//        else {
//            tvType.setVisibility(View.VISIBLE);
//        }

    }

    public void setItemClickPosition(int position) {
        itemClickPosition = position;
    }

    public void setYouSeHide(boolean youSeHide) {
        isYouSeHide = youSeHide;
    }

    public void setLowHide(boolean lowHide) {
        isLowHide = lowHide;
    }

    public void setLevelHide(boolean levelHide) {
        isLevelHide = levelHide;
    }
}
