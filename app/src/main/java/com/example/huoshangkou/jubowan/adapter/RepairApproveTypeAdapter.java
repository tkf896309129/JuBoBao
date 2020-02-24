package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.RepairObjBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：RepairApproveTypeAdapter
 * 描述：
 * 创建时间：2017-03-02  14:17
 */

public class RepairApproveTypeAdapter extends BaseAbstractAdapter<RepairObjBean> {

    public int itemClick = -1;

    public RepairApproveTypeAdapter(Context context, List<RepairObjBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, RepairObjBean bean, int position) {

        TextView tvType = holder.getView(R.id.tv_type);

        tvType.setText(bean.getName());
        if(bean.isCheck()){
            tvType.setBackground(context.getResources().getDrawable(R.mipmap.rz_lbxz_icon_on));
        }else{
            tvType.setBackground(context.getResources().getDrawable(R.drawable.gray_corner_bg));
        }

    }

    public int getItemClick() {
        return itemClick;
    }

    public void setItemClick(int itemClick) {
        this.itemClick = itemClick;
    }
}
