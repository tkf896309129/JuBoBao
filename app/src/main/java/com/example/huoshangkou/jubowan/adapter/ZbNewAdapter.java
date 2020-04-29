package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.ZbListBean;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：ZbNewAdapter
 * 描述：
 * 创建时间：2019-04-03  10:38
 */

public class ZbNewAdapter extends BaseAbstractAdapter<ZbListBean> {

    public ZbNewAdapter(Context context, List<ZbListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, ZbListBean bean, int position) {
        ImageView imgPic = holder.getView(R.id.iv_zb_new);
        TextView tvDays = holder.getView(R.id.tv_days);
        TextView tvZbTime = holder.getView(R.id.tv_zb_time);
        TextView tvZbName = holder.getView(R.id.tv_zb_name);
        TextView tvArea = holder.getView(R.id.tv_area);
        TextView tvAlreadyZb = holder.getView(R.id.tv_alerady_zb);
        TextView tvProName = holder.getView(R.id.tv_pro_name);

        tvDays.setText(bean.getDayNumber() + "");
        tvZbTime.setText(DateUtils.getFormData(bean.getCreateTime()));
        tvProName.setText(bean.getProjectType());
        tvArea.setText(bean.getArea() + "㎡-" + bean.getArea1() + "㎡");
        tvZbName.setText(bean.getProjectTitle());
        GlideUtils.getInstance().displayImage(bean.getDefaultImg(), context, imgPic);
        if (bean.getIsRequestTo() == 1) {
            tvAlreadyZb.setText("已招标");
            tvAlreadyZb.setTextColor(context.getResources().getColor(R.color.orange_color));
        } else if (bean.getIsRequestTo() == 2) {
            tvAlreadyZb.setText("已定标");
            tvAlreadyZb.setTextColor(context.getResources().getColor(R.color.red));
        } else {
            tvAlreadyZb.setText("未招标");
            tvAlreadyZb.setTextColor(context.getResources().getColor(R.color.green_alpha));
        }
    }
}
