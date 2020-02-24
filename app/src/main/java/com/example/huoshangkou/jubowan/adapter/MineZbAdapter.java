package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.ZbListBean;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.StringBuilderUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：MineZbAdapter
 * 描述：
 * 创建时间：2017-04-07  14:46
 */

public class MineZbAdapter extends BaseAbstractAdapter<ZbListBean> {

    public MineZbAdapter(Context context, List<ZbListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, ZbListBean bean, int position) {
        TextView tvClassType = holder.getView(R.id.tv_class_type);
        TextView tvTime = holder.getView(R.id.tv_time);
        TextView tvRestDays = holder.getView(R.id.tv_rest_days);
        TextView tvStandard = holder.getView(R.id.tv_standard);
        tvClassType.setText("类型：" + bean.getProjectType());
        tvTime.setText(DateUtils.getFormData(bean.getEndTime()));

        String restDay = "剩余" + bean.getDayNumber() + "天";
        tvRestDays.setText(StringBuilderUtils.getInstance().setTextColor("剩余" + bean.getDayNumber() + "天",
                context.getResources().getColor(R.color.main_tab_blue), 2, restDay.length() - 1));
        tvStandard.setText("规模：" + bean.getArea() + "-" + bean.getArea1() + "(平方米)");

    }
}
