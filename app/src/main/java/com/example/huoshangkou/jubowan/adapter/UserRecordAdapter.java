package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.UseRecordBean;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：UserRecordAdapter
 * 描述：
 * 创建时间：2018-08-22  10:22
 */

public class UserRecordAdapter extends BaseAbstractAdapter<UseRecordBean.ReListBean> {
    public UserRecordAdapter(Context context, List<UseRecordBean.ReListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, UseRecordBean.ReListBean bean, int position) {
        ImageView ivState = holder.getView(R.id.iv_state);
        TextView tvOrderId = holder.getView(R.id.tv_order_id);
        TextView tvTime = holder.getView(R.id.tv_time);
        TextView tvPrice = holder.getView(R.id.tv_price);
        TextView tvRestDay = holder.getView(R.id.tv_rest_day);
        //状态  -1 审核未通过 0 待审核 1 未还款 2 还款中 3 已还款  4 无效（白条额度恢复）
        switch (bean.getState()) {
            case -1:
                ivState.setImageResource(R.mipmap.icon_weitongguo);
                break;
            case 0:
                ivState.setImageResource(R.mipmap.icon_daishenhen);
                break;
            case 1:
                ivState.setImageResource(R.mipmap.icon_weihuankuan);
                break;
            case 2:
                ivState.setImageResource(R.mipmap.icon_huankaunzhong);
                break;
            case 3:
                ivState.setImageResource(R.mipmap.icon_yihuankuan);
                break;
            case 4:
                ivState.setImageResource(R.mipmap.icon_yishixiao);
                break;
        }

        if (StringUtils.isNoEmpty(bean.getDiffdays() + "") && bean.getDiffdays()>0) {
            tvRestDay.setText("逾期：" + bean.getDiffdays() + "天");
        }else{
            tvRestDay.setText("");
        }

        tvPrice.setText(bean.getNeedRefundAmount() + "");
        tvTime.setText(DateUtils.getFormData(bean.getCreateTime()));
        tvOrderId.setText("订单号：" + bean.getOrderID());


    }
}
