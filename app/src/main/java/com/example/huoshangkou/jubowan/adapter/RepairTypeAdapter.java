package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.RepairTypeBean;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.RepairTypeListBean;
import com.example.huoshangkou.jubowan.inter.OnRepairClick;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：RepairTypeAdapter
 * 描述：
 * 创建时间：2017-02-06  16:15
 */

public class RepairTypeAdapter extends BaseAbstractAdapter<RepairTypeListBean> {

    private OnRepairClick repairClick;

    public RepairTypeAdapter(Context context, List<RepairTypeListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, RepairTypeListBean bean, final int position) {
        ImageView imgRepair = holder.getView(R.id.iv_repair_pic);
        TextView tvRepair = holder.getView(R.id.tv_repair_type);
        TextView tvRepairNum = holder.getView(R.id.tv_repair_num);

        LinearLayout tvTop = holder.getView(R.id.ll_top);
        LinearLayout tvBottom = holder.getView(R.id.ll_bottom);

        tvTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repairClick.onTopClick(position);
            }
        });

        tvBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repairClick.onBottomClick(position);
            }
        });

        tvRepair.setText(bean.getName());

        GlideUtils.getInstance().displayCricleImage(context, bean.getPic(), imgRepair);
        String count = "(已报修" + bean.getCount() + "单)";
        SpannableString spannableString = new SpannableString(count);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.main_tab_blue));
        spannableString.setSpan(colorSpan, 0, count.length() - 2, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        tvRepairNum.setText(spannableString);
    }


    public void setRepairClick(OnRepairClick repairClick) {
        this.repairClick = repairClick;
    }
}
