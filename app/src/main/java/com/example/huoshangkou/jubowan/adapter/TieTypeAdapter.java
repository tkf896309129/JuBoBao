package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.TieTypeBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：TieTypeAdapter
 * 描述：
 * 创建时间：2017-02-13  11:55
 */

public class TieTypeAdapter extends BaseAbstractAdapter<TieTypeBean> {

    private int mItemClick = -1;

    public TieTypeAdapter(Context context, List<TieTypeBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, TieTypeBean bean, int position) {
        TextView tvType = holder.getView(R.id.tv_tie_type);
        ImageView imgType = holder.getView(R.id.iv_select);


//        if (bean.isCheck()) {
//            imgType.setVisibility(View.VISIBLE);
//        } else {
//            imgType.setVisibility(View.GONE);
//        }
        if (mItemClick == position) {
            imgType.setVisibility(View.VISIBLE);
            tvType.setTextColor(context.getResources().getColor(R.color.main_tab_blue));
            bean.setCheck(true);
        } else {
            imgType.setVisibility(View.GONE);
            tvType.setTextColor(context.getResources().getColor(R.color.address_black_key));
            bean.setCheck(false);
        }

        tvType.setText(bean.getType());
    }


    public void setItemClickPositoin(int positoin) {
        mItemClick = positoin;
    }
}
