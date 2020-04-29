package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.OrderListTypeBean;
import com.example.huoshangkou.jubowan.constant.OrderTypeConstant;
import com.example.huoshangkou.jubowan.inter.OnStringPositionCallBack;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：OrderProAdapter
 * 描述：
 * 创建时间：2017-04-06  13:39
 */

public class OrderProAdapter extends BaseAbstractAdapter<OrderListTypeBean> {
    public OrderProAdapter(Context context, List<OrderListTypeBean> listData, int resId) {
        super(context, listData, resId);
    }

    OnStringPositionCallBack successCallBack;

    @Override
    public void convert(ViewHolder holder, final OrderListTypeBean bean, final int position) {
        ImageView imgPic = holder.getView(R.id.iv_order);
        TextView tvRepairType = holder.getView(R.id.tv_repair_type);
        TextView tvBrand = holder.getView(R.id.tv_brand);
        TextView tvTime = holder.getView(R.id.tv_time);


        if (bean.getType().equals(OrderTypeConstant.getInstance().YP)) {
            tvRepairType.setText("商品详情：" + bean.getClassName() + "/" + bean.getBrandName() + "/"
                    + bean.getLevelName() + "/" + bean.getWeight() + "/" + bean.getXy());

            tvBrand.setText("参考价格：￥" + bean.getOnePrice());
            tvTime.setText("商品数量：x" + bean.getCount());
            GlideUtils.getInstance().displayImage(bean.getPic(), context, imgPic);
        } else if (bean.getType().equals(OrderTypeConstant.getInstance().FL)) {
            tvRepairType.setText("商品详情：" + bean.getClassName() + "/"
                    + bean.getBrandName() + "/" + bean.getXy());
            tvBrand.setText("参考价格：￥" + bean.getOnePrice());
            tvTime.setText("商品数量：x" + bean.getCount());
            GlideUtils.getInstance().displayImage(bean.getPic(), context, imgPic);
        } else if (bean.getType().equals(OrderTypeConstant.getInstance().WX)) {
            //设备改造
            if (StringUtils.isNoEmpty(bean.getGuiGe())) {
                tvRepairType.setText("设备升级："+bean.getMaintainName());
            } else {
                tvRepairType.setText("故障类别：" + bean.getMaintainName());
            }

            tvBrand.setText("机械品牌：" + bean.getBrandName());
            tvBrand.setVisibility(View.VISIBLE);
            tvTime.setText("购买时间：" + DateUtils.getFormData(bean.getBuyDate()));
            GlideUtils.getInstance().displayImage(bean.getPic(), context, imgPic);
        } else if (bean.getType().equals(OrderTypeConstant.getInstance().PJ)) {
            tvRepairType.setText("商品详情：" + bean.getModelTitle() + "/"
                    + bean.getGuiGeVal() + "/" + bean.getVoltage());

            tvBrand.setText("参考单价：￥" + bean.getOnePrice());
            tvTime.setText("商品数量：x" + bean.getCount());
            GlideUtils.getInstance().displayImage(bean.getPic(), context, imgPic);
        }else if (bean.getType().equals(OrderTypeConstant.getInstance().YL))  {
            tvRepairType.setText("商品详情：" + bean.getCategoryName() + "/"+ bean.getClassName() + "/"
                    + bean.getBrandName() + "/" + bean.getLevelName());
            tvBrand.setText("参考价格：￥" + bean.getOnePrice());
            tvTime.setText("商品数量：x" + bean.getCount());
            GlideUtils.getInstance().displayImage(bean.getPic(), context, imgPic);
        }

        TextView tvClick = holder.getView(R.id.tv_click);

        tvClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (successCallBack != null) {
                    successCallBack.onClick(bean.getType(), position);
                }
            }
        });
    }

    public void setSuccessCallBack(OnStringPositionCallBack successCallBack) {
        this.successCallBack = successCallBack;
    }
}
