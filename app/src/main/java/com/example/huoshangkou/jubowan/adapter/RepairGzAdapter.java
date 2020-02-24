package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.RepairTypeListBean;
import com.example.huoshangkou.jubowan.inter.OnRepairClick;
import com.example.huoshangkou.jubowan.utils.BlueTxtUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：RepairGzAdapter
 * 描述：
 * 创建时间：2018-06-08  08:25
 */

public class RepairGzAdapter extends BaseAbstractAdapter<RepairTypeListBean> {

    private OnRepairClick repairClick;

    public RepairGzAdapter(Context context, List<RepairTypeListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, RepairTypeListBean bean, final int position) {
        ImageView imgRepair = holder.getView(R.id.iv_buy_tool);
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvNum = holder.getView(R.id.tv_repair_num);

        GlideUtils.getInstance().displayImage(bean.getPic(), context, imgRepair);
        tvName.setText(bean.getName());
        tvNum.setText(Html.fromHtml("(已报" + BlueTxtUtils.getInstance().getBlueTxt(bean.getCount() + "") + "单)"));

        imgRepair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repairClick.onTopClick(position);
            }
        });

        tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repairClick.onBottomClick(position);
            }
        });
        tvNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repairClick.onBottomClick(position);
            }
        });


    }

    public void setRepairClick(OnRepairClick repairClick) {
        this.repairClick = repairClick;
    }


}
