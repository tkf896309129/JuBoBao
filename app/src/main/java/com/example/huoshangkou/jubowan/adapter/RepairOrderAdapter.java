package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.CheckServerListActivity;
import com.example.huoshangkou.jubowan.activity.WriteListActivity;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.OrderListTypeBean;
import com.example.huoshangkou.jubowan.bean.OrderListsBean;
import com.example.huoshangkou.jubowan.constant.OrderTypeConstant;
import com.example.huoshangkou.jubowan.inter.OnDeleteCallBack;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.inter.OnRepairPriceClick;
import com.example.huoshangkou.jubowan.inter.OnStringPositionCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.PhoneUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：RepairOrderAdapter
 * 描述：
 * 创建时间：2017-04-27  09:19
 */

public class RepairOrderAdapter extends BaseAbstractAdapter<OrderListsBean> {

    OnPositionClick positionClick;

    //删除订单
    private OnDeleteCallBack deleteCallBack;

    //维修清单点击事件
    private OnRepairPriceClick repairPriceClick;

    StringCallBack stringCallBack;


    public RepairOrderAdapter(Context context, List<OrderListsBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, final OrderListsBean bean, final int position) {
        ScrollListView listView = holder.getView(R.id.lv_order);
        List<OrderListTypeBean> list = new ArrayList<>();
        List<OrderListTypeBean> orderList = bean.getOrderListw();
        int num = orderList.size();
        for (int i = 0; i < num; i++) {
            OrderListTypeBean typeListBean = new OrderListTypeBean();
            typeListBean.setMaintainName(orderList.get(i).getMaintainName());
            typeListBean.setBrandName(orderList.get(i).getBrandName());
            typeListBean.setBuyDate(orderList.get(i).getBuyDate());
            typeListBean.setDescript(orderList.get(i).getDescript());
            typeListBean.setPic(orderList.get(i).getPic());
            typeListBean.setType(OrderTypeConstant.getInstance().WX);
            list.add(typeListBean);
        }

        OrderProAdapter proAdapter = new OrderProAdapter(context, list, R.layout.item_order_product);

        listView.setAdapter(proAdapter);
        listView.setDividerHeight(0);

        proAdapter.setSuccessCallBack(new OnStringPositionCallBack() {
            @Override
            public void onClick(String type, int positions) {
                positionClick.onPositionClick(position);
            }
        });

        //订单编号
        TextView tvOrderNum = holder.getView(R.id.tv_order_num);
        tvOrderNum.setText(bean.getOrderID());
        TextView tvOrderTime = holder.getView(R.id.tv_order_time);
        tvOrderTime.setText(bean.getCreateTime());
        TextView tvPrice = holder.getView(R.id.tv_repair_price);

        TextView tvCheckType = holder.getView(R.id.tv_check_serve_list);

        //填写服务单
        TextView tvWriteList = holder.getView(R.id.tv_write_list);


        TextView tvState = holder.getView(R.id.tv_pay_type);

        if (!StringUtils.isNoEmpty(bean.getTotalPrice())) {
            tvPrice.setText("维修预付款：待确认");
        } else {
            tvPrice.setText("维修预付款：￥" + bean.getTotalPrice());
        }

        TextView tvDelete = holder.getView(R.id.tv_delete);
        TextView tvPay = holder.getView(R.id.tv_pay);
        TextView tvCommon = holder.getView(R.id.tv_common);
        TextView tvPayList = holder.getView(R.id.tv_pay_list);
        TextView tvLinkKf = holder.getView(R.id.tv_link_kf);
        TextView tvReceive = holder.getView(R.id.tv_confirm_receive);

        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCallBack.onDelete(bean.getOrderID(), position);
            }
        });

        tvPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!StringUtils.isNoEmpty(bean.getTotalPrice())) {
                    ToastUtils.getMineToast("订单错误，暂时无法支付");
                    return;
                }
                String price = "";
                String orderId = "";
                double totalPrice = Double.parseDouble(bean.getTotalPrice());
                double yfPrice = Double.parseDouble(bean.getYuFuPrice());
                double syPrice = 0.0;
                if (StringUtils.isNoEmpty(bean.getShengYuPrice())) {
                    syPrice = Double.parseDouble(bean.getShengYuPrice());
                }

                //没有支付
                if (syPrice == totalPrice) {
                    price = yfPrice + "";
                    orderId = bean.getOrderID() + "WD";
                    //支付过一次
                } else if (syPrice > 0 && syPrice < totalPrice) {
                    price = syPrice + "";
                    orderId = bean.getOrderID() + "WS";
                    //支付完成
                } else if (syPrice <= 0) {
//                        price = yfPrice + "";
//                        orderId = bean.getOrderID() + "WD";
                }
                IntentUtils.getInstance().toRepairPayActivity(context, price, orderId, "维系维保支付", "维系维保支付", "维系维保支付", null);
            }
        });

        tvCommon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringCallBack.onSuccess(bean.getOrderID());
            }
        });

        tvPayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repairPriceClick.onRepairClick(bean.getOrderID(), position);
            }
        });

        tvWriteList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.getInstance().toActivity(context, WriteListActivity.class, bean.getOrderID());
            }
        });

        //联系客服
        tvLinkKf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneUtils.getInstance().callPhoe(context);
            }
        });

        tvCheckType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.getInstance().toActivity(context, CheckServerListActivity.class, bean.getServeID() + "");
            }
        });


        //  0：待确认，1：待支付，2：待评价，3全部，4已完成
        //订单分类界面绑定 0：待确认，1：待支付，2：待收货，3全部，4已完成
        //2 全部 ---  0 待受理 --- 1 待支付 --- 3 待收货 --- 4 已完成
        if (!bean.isMyRepair()) {
            tvWriteList.setVisibility(View.GONE);
            switch (bean.getOrderState()) {
                case 0:
                    tvPay.setVisibility(View.GONE);
                    tvCommon.setVisibility(View.GONE);
                    tvLinkKf.setVisibility(View.GONE);
                    tvPayList.setVisibility(View.VISIBLE);
                    tvReceive.setVisibility(View.GONE);
                    if (bean.getPayTpye() != 0) {
                        tvLinkKf.setVisibility(View.VISIBLE);
                        tvDelete.setVisibility(View.GONE);
                    } else {
                        tvLinkKf.setVisibility(View.GONE);
                        tvDelete.setVisibility(View.VISIBLE);
                    }
                    break;
                case 1:
                    tvReceive.setVisibility(View.GONE);
                    tvLinkKf.setVisibility(View.GONE);
                    tvDelete.setVisibility(View.GONE);
                    tvPay.setVisibility(View.VISIBLE);
                    tvCommon.setVisibility(View.GONE);
                    tvPayList.setVisibility(View.GONE);
                    break;
//            case 3:
//                tvReceive.setVisibility(View.GONE);
//                tvDelete.setVisibility(View.GONE);
//                tvPay.setVisibility(View.GONE);
//                tvCommon.setVisibility(View.VISIBLE);
//                tvPayList.setVisibility(View.GONE);
//                tvLinkKf.setVisibility(View.GONE);
//                break;
                case 4:
                    tvReceive.setVisibility(View.GONE);
                    tvDelete.setVisibility(View.GONE);
                    tvPay.setVisibility(View.GONE);
                    tvCommon.setVisibility(View.GONE);
                    tvPayList.setVisibility(View.GONE);
                    tvLinkKf.setVisibility(View.VISIBLE);
                    break;
            }


            switch (bean.getPayWay()) {
                //微信支付
                case 1:
                    if (bean.getOrderState() == 3 || bean.getOrderState() == 4) {
                        tvState.setText("微信支付");
                    } else {
                        tvState.setText("");
                    }
                    break;
                //支付宝支付
                case 0:
                    if (bean.getOrderState() == 3 || bean.getOrderState() == 4) {
                        tvState.setText("支付宝支付");
                    } else {
                        tvState.setText("");
                    }
                    break;
            }

        } else {
            //待维修
            if (bean.getOrderState() == 1) {
                tvPayList.setVisibility(View.VISIBLE);
                tvWriteList.setVisibility(View.GONE);
                tvState.setText("用户已支付");
                //已维修
            } else if (bean.getOrderState() == 4) {
                tvPayList.setVisibility(View.VISIBLE);
                tvWriteList.setVisibility(View.GONE);
                tvState.setText("用户待支付");
            } else {
                tvPayList.setVisibility(View.GONE);

                tvState.setText("用户已评价");

                if (bean.getServeID() == 0) {
                    tvCheckType.setVisibility(View.GONE);
                    tvWriteList.setVisibility(View.VISIBLE);
                } else {
                    tvCheckType.setVisibility(View.VISIBLE);
                    tvWriteList.setVisibility(View.GONE);
                }
            }
        }
    }

    public void setPositionClick(OnPositionClick positionClick) {
        this.positionClick = positionClick;
    }

    public void setRepairPriceClick(OnRepairPriceClick repairPriceClick) {
        this.repairPriceClick = repairPriceClick;
    }

    public void setDeleteCallBack(OnDeleteCallBack deleteCallBack) {
        this.deleteCallBack = deleteCallBack;
    }

    public void setStringCallBack(StringCallBack stringCallBack) {
        this.stringCallBack = stringCallBack;
    }
}
