package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.WeiTuoShuActivity;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.OrderListTypeBean;
import com.example.huoshangkou.jubowan.bean.OrderListsBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.OrderTypeConstant;
import com.example.huoshangkou.jubowan.inter.ConfirmReceive;
import com.example.huoshangkou.jubowan.inter.OnDeleteCallBack;
import com.example.huoshangkou.jubowan.inter.OnOrderListCallBack;
import com.example.huoshangkou.jubowan.inter.OnRepairPriceClick;
import com.example.huoshangkou.jubowan.inter.OnStringPositionCallBack;
import com.example.huoshangkou.jubowan.inter.OnToCommonCallBack;
import com.example.huoshangkou.jubowan.utils.BlueTxtUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.PhoneUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.utils.TwoPointUtils;
import com.example.huoshangkou.jubowan.view.ScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：OrderListAdapter
 * 描述：
 * 创建时间：2017-04-06  09:09
 */

public class OrderListAdapter extends BaseAbstractAdapter<OrderListsBean> {
    private OnOrderListCallBack onOrderListCallBack;

    //维修清单点击事件
    private OnRepairPriceClick repairPriceClick;

    //删除订单
    private OnDeleteCallBack deleteCallBack;

    //评价回调
    OnToCommonCallBack toCommonCallBack;
    ConfirmReceive confirmReceive;

    private boolean isPay = true;

    //是否是垫付款
    public boolean isDianFu = false;

    public OrderListAdapter(Context context, List<OrderListsBean> listData, int resId) {
        super(context, listData, resId);
    }

    public OrderListAdapter(Context context, List<OrderListsBean> listData, int resId, String dianFuKuan) {
        super(context, listData, resId);
        if (StringUtils.isNoEmpty(dianFuKuan)) {
            isDianFu = true;
        }
    }

    @Override
    public void convert(ViewHolder holder, final OrderListsBean bean, final int position) {

        ScrollListView listView = holder.getView(R.id.lv_order);

        boolean isWx = false;
        boolean isYp = false;

        List<OrderListTypeBean> typeList = new ArrayList<>();
        if (bean.getOrderListf() != null) {
            setType(OrderTypeConstant.getInstance().FL, bean.getOrderListf());
            typeList.addAll(bean.getOrderListf());
        }
        if (bean.getOrderListw() != null) {
            isWx = true;
            setType(OrderTypeConstant.getInstance().WX, bean.getOrderListw());
            typeList.addAll(bean.getOrderListw());
        }

        //设备改造
        if (bean.getOrderListwb() != null) {
            isWx = true;
            setType(OrderTypeConstant.getInstance().WX, bean.getOrderListwb());
            typeList.addAll(bean.getOrderListwb());
        }

        if (bean.getOrderListy() != null) {
            isYp = true;
            setType(OrderTypeConstant.getInstance().YP, bean.getOrderListy());
            typeList.addAll(bean.getOrderListy());
        }
        if (bean.getOrderListp() != null) {
            setType(OrderTypeConstant.getInstance().PJ, bean.getOrderListp());
            typeList.addAll(bean.getOrderListp());
        }
        if (bean.getOrderListyl() != null) {
            setType(OrderTypeConstant.getInstance().YL, bean.getOrderListyl());
            typeList.addAll(bean.getOrderListyl());
        }
        //
        OrderProAdapter proAdapter = new OrderProAdapter(context, typeList, R.layout.item_order_product);
        listView.setAdapter(proAdapter);
        listView.setDividerHeight(0);

        proAdapter.setSuccessCallBack(new OnStringPositionCallBack() {
            @Override
            public void onClick(String type, int positions) {
                if (onOrderListCallBack != null) {
                    onOrderListCallBack.onChildClick(type, bean.getOrderID(), position);
                }
            }
        });

        //订单编号
        TextView tvOrderNum = holder.getView(R.id.tv_order_num);
        tvOrderNum.setText(bean.getOrderID());
        TextView tvOrderTime = holder.getView(R.id.tv_order_time);
        tvOrderTime.setText(bean.getCreateTime());
        TextView tvProNum = holder.getView(R.id.tv_pro_num);
        TextView tvWeiTuo = holder.getView(R.id.tv_wei_tuo_shu);

        RelativeLayout rlBottom = holder.getView(R.id.rl_bottom);
        if (isDianFu) {
            rlBottom.setVisibility(View.GONE);
        } else {
            rlBottom.setVisibility(View.VISIBLE);
        }

        TextView tvLinkKf = holder.getView(R.id.tv_link_kf);
        TextView tvPayType = holder.getView(R.id.tv_pay_type);
        if (isWx) {
            tvProNum.setText("");
        } else {
            tvProNum.setText("共" + bean.getNumber() + "件商品");
        }
        TextView tvPrice = holder.getView(R.id.tv_repair_price);
        if (StringUtils.isNoEmpty(bean.getTotalPrice()) && !isWx) {
            if (StringUtils.isNoEmpty(bean.getNeedPayAmount()) && bean.getPayTpye() == 7) {
                tvPrice.setText(Html.fromHtml("剩余支付金额：" + BlueTxtUtils.getInstance().getBlueTxt("￥" + bean.getNeedPayAmount())));
            } else {
                tvPrice.setText(Html.fromHtml("合计：" + BlueTxtUtils.getInstance().getBlueTxt("￥" + bean.getTotalPrice())));
            }
        } else if (!StringUtils.isNoEmpty(bean.getTotalPrice()) && isWx) {
            tvPrice.setText("维修预付款：待确认");
        } else if (StringUtils.isNoEmpty(bean.getTotalPrice()) && isWx) {

            String price = "";
            double totalPrice = Double.parseDouble(bean.getTotalPrice());
            double yfPrice = Double.parseDouble(bean.getYuFuPrice());
            double syPrice = 0.0;
            if (StringUtils.isNoEmpty(bean.getShengYuPrice())) {
                syPrice = Double.parseDouble(bean.getShengYuPrice());
            }

            if (bean.getOrderType().equals("维保")) {
                //没有支付
                if (syPrice == totalPrice) {
                    tvPrice.setText(Html.fromHtml("总金额：" + BlueTxtUtils.getInstance().getBlueTxt(bean.getTotalPrice()) + "元  " + "预付金额：" + BlueTxtUtils.getInstance().getBlueTxt(yfPrice + "") + "元"));
                    //支付过一次
                } else if (syPrice > 0 && syPrice < totalPrice) {
                    tvPrice.setText(Html.fromHtml("剩余金额：" + BlueTxtUtils.getInstance().getBlueTxt(syPrice + "") + "元"));
                    //支付完成
                } else if (syPrice <= 0) {
                    tvPrice.setText(Html.fromHtml("合计：" + BlueTxtUtils.getInstance().getBlueTxt(bean.getTotalPrice()) + "元"));
                }
            } else {
                tvPrice.setText(Html.fromHtml("维修预付款：" + BlueTxtUtils.getInstance().getBlueTxt(bean.getTotalPrice()) + "元"));
            }
        }

        switch (bean.getPayTpye()) {
            //线下支付未确认
            case -1:
                tvPayType.setText("线下支付未确认");
                break;
            //未支付
            case 0:
                if (bean.getOrderState() == 0) {
                    tvPayType.setText("订单审核中");
                } else if (bean.getOrderState() == 1) {
                    tvPayType.setText("");
                } else if (isWx && bean.getOrderState() == 0) {
                    tvPayType.setText("订单审核中");
                } else if (isWx && (bean.getOrderState() == 4 || bean.getOrderState() == 2)) {
//                    tvPayType.setText("支付宝支付");
                    if (bean.getPayTpye() != 4 && bean.getPayTpye() != 1 && bean.getPayTpye() != 2) {
                        switch (bean.getPayWay()) {
                            //微信支付
                            case 1:
                                if (bean.getOrderState() == 2 || bean.getOrderState() == 3 || bean.getOrderState() == 4) {
                                    tvPayType.setText("微信支付");
                                } else {
                                    tvPayType.setText("");
                                }
                                break;
                            //支付宝支付
                            case 0:
                                if (bean.getOrderState() == 2 || bean.getOrderState() == 3 || bean.getOrderState() == 4) {
                                    tvPayType.setText("支付宝支付");
                                } else {
                                    tvPayType.setText("");
                                }
                                break;
                            //垫付款支付
                        }
                    }
                } else {
                    tvPayType.setText("");
                }
                break;
            //线下支付
            case 1:
                if (bean.getOrderState() == 0) {
                    tvPayType.setText("线下支付审核");
                } else if (isWx && bean.getOrderState() != 0) {
                    tvPayType.setText("支付宝支付");
                } else {
                    tvPayType.setText("线下支付");
                }
                break;
            //融资支付
            case 2:
                if (bean.getOrderState() == 0) {
                    tvPayType.setText("融资支付审核");
                } else if (isWx && bean.getOrderState() != 0) {
                    tvPayType.setText("支付宝支付");
                } else {
                    tvPayType.setText("融资支付");
                }
                break;
            case 3:
                tvPayType.setText("");
                break;
            case 4:
                if (isYp) {
                    tvPayType.setText("垫付款支付");
                }
                break;
            case 6:
                tvPayType.setText("白条支付已确认");
                break;
            case -6:
                tvPayType.setText("白条支付未确认");
                break;
            case 7:
                if (StringUtils.isNoEmpty(bean.getTotalPrice()) && StringUtils.isNoEmpty(bean.getNeedPayAmount())) {
                    double price = (Double.parseDouble(bean.getTotalPrice()) - Double.parseDouble(bean.getNeedPayAmount()));
                    tvPayType.setText("白条支付\n(支付金额：" + TwoPointUtils.getInstance().getTwoPoint(price) + "元)");
                }
                break;
            case -7:
                tvPayType.setText("白条支付审核中");
                break;
            default:
                tvPayType.setText("");
                break;
        }

        switch (bean.getPayWay()) {
            //微信支付
            case 2:
                tvPayType.setText("定金审核中");
                break;
            //支付宝支付
            case 3:
                tvPayType.setText("定金已付款");
                break;
            case 4:
                tvPayType.setText("剩余金额付款审核中");
                break;
            case 5:
                tvPayType.setText("线下付款");
                break;
        }

        TextView tvDelete = holder.getView(R.id.tv_delete);
        TextView tvPay = holder.getView(R.id.tv_pay);
        TextView tvDianPay = holder.getView(R.id.tv_dian_pay);
        TextView tvCommon = holder.getView(R.id.tv_common);
        TextView tvPayList = holder.getView(R.id.tv_pay_list);
        TextView tvConfirmReceive = holder.getView(R.id.tv_confirm_receive);

        //  0：待确认，1：待支付，2：待评价，3全部，4已完成
        //2 全部 ---  0 待受理 --- 1 待支付 --- 3 待收货 --- 4 已完成
        //0：待受理，1：已受理，2：已支付，3=已发货，4=已收货
        switch (bean.getOrderState()) {
            case 0:
                tvPay.setVisibility(View.GONE);
                tvCommon.setVisibility(View.GONE);
                tvLinkKf.setVisibility(View.GONE);
                tvConfirmReceive.setVisibility(View.GONE);
                tvDianPay.setVisibility(View.GONE);
                if (isWx) {
                    if (bean.getOrderType().equals("维保")) {
                        tvLinkKf.setVisibility(View.VISIBLE);
                        tvPayList.setVisibility(View.GONE);
                    } else {
                        tvLinkKf.setVisibility(View.GONE);
                        tvPayList.setVisibility(View.VISIBLE);
                    }
                } else {
                    tvPayList.setVisibility(View.GONE);
                }
                if (bean.getPayTpye() != 0) {
                    tvLinkKf.setVisibility(View.VISIBLE);
                    tvDelete.setVisibility(View.GONE);
                } else {
                    tvLinkKf.setVisibility(View.GONE);
                    tvDelete.setVisibility(View.VISIBLE);
                }
                tvWeiTuo.setVisibility(View.GONE);
                break;
            case 1:
                tvLinkKf.setVisibility(View.GONE);
                tvDelete.setVisibility(View.GONE);
                tvPay.setVisibility(View.VISIBLE);
                tvCommon.setVisibility(View.GONE);
                tvConfirmReceive.setVisibility(View.GONE);
                tvPayList.setVisibility(View.GONE);
                if (isYp && (bean.getPayTpye() != 7 && bean.getPayTpye() != -7 && bean.getPayTpye() != 6 && bean.getPayTpye() != -6)) {
                    if (bean.getAdvanceState() > 0) {
                        tvPayType.setText("垫付款审核中");
                    } else if (bean.getAdvanceState() == 0) {
                        tvPayType.setText("");
                    } else if (bean.getAdvanceState() == -1) {
                        tvPayType.setText("垫付款审核失败");
                    }
                }
//                if (bean.getAdvanceState() == 0 && isYp) {
//                    tvDianPay.setVisibility(View.VISIBLE);
//                } else if (bean.getAdvanceState() != 0 && isYp) {
//                    tvDianPay.setVisibility(View.GONE);
//                }
                tvDianPay.setVisibility(View.GONE);
                tvWeiTuo.setVisibility(View.GONE);
                break;
            case -1:
                tvDelete.setVisibility(View.GONE);
                tvPay.setVisibility(View.VISIBLE);
                tvCommon.setVisibility(View.GONE);
                tvConfirmReceive.setVisibility(View.GONE);
                tvWeiTuo.setVisibility(View.GONE);
                tvPayList.setVisibility(View.GONE);
                tvLinkKf.setVisibility(View.GONE);
                tvDianPay.setVisibility(View.GONE);
                break;
            case 2:
                tvDelete.setVisibility(View.GONE);
                tvPay.setVisibility(View.GONE);
                tvCommon.setVisibility(View.GONE);
                tvConfirmReceive.setVisibility(View.GONE);
                tvWeiTuo.setVisibility(View.GONE);
                tvPayList.setVisibility(View.GONE);
                tvLinkKf.setVisibility(View.VISIBLE);
                tvDianPay.setVisibility(View.GONE);
                break;
            case 3:
                tvDelete.setVisibility(View.GONE);
                tvPay.setVisibility(View.GONE);
                tvCommon.setVisibility(View.GONE);
                tvConfirmReceive.setVisibility(View.VISIBLE);
                if (isYp) {
                    if (bean.getPayTpye() == 4) {
                        tvWeiTuo.setVisibility(View.VISIBLE);
                    } else {
                        tvWeiTuo.setVisibility(View.GONE);
                    }
                } else {
                    tvWeiTuo.setVisibility(View.GONE);
                }
                tvPayList.setVisibility(View.GONE);
                tvLinkKf.setVisibility(View.GONE);
                tvDianPay.setVisibility(View.GONE);
                break;
            case 4:
                if (bean.getIsEvaluate() == 0) {
                    tvCommon.setVisibility(View.VISIBLE);
                } else {
                    tvCommon.setVisibility(View.GONE);
                }
                tvConfirmReceive.setVisibility(View.GONE);
                tvDelete.setVisibility(View.GONE);
                tvWeiTuo.setVisibility(View.GONE);
                tvPay.setVisibility(View.GONE);
                tvPayList.setVisibility(View.GONE);
                tvDianPay.setVisibility(View.GONE);
                tvLinkKf.setVisibility(View.VISIBLE);
                break;
        }

        //立即支付
        tvPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (!isPay) {
//                    ToastUtils.getMineToast("线下订单订单审核中");
//                    return;
//                }

                if (bean.getPayWay() == 2 || bean.getPayWay() == 4) {
                    ToastUtils.getMineToast("线下订单订单审核中");
                    return;
                }

                //维修维保
                if (bean.getOrderListw() != null || bean.getOrderListwb() != null) {
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
                        price = totalPrice + "";
                        orderId = bean.getOrderID();
                    }

                    IntentUtils.getInstance().toRepairPayActivity(context, price, orderId, "维系维保支付", "维系维保支付", "维系维保支付", bean);
                    //配件
                } else if (bean.getOrderListp() != null) {
                    IntentUtils.getInstance().toPayActivity(context, bean.getTotalPrice(), bean.getOrderID(), "配件支付", "配件支付", "配件支付", OrderTypeConstant.getInstance().PJ, bean);
                    //其他
                } else {
                    String price = "0.00";
                    if (StringUtils.isNoEmpty(bean.getNeedPayAmount())) {
                        price = bean.getNeedPayAmount();
                    } else {
                        price = bean.getTotalPrice();
                    }
                    String type = "";
                    if ((bean.getPayTpye() == 7 || bean.getPayTpye() == -7 || bean.getPayTpye() == 6 || bean.getPayTpye() == -6)) {
                        type = "btPay";
                    }
                    IntentUtils.getInstance().toPayActivity(context, price, bean.getOrderID(), bean, type);
                }
            }
        });

        //删除订单
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCallBack.onDelete(bean.getOrderID(), position);
            }
        });

        //订单评价
        tvCommon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        if (bean.getOrderListw() != null) {
                            toCommonCallBack.onToRepair(bean.getOrderID(), position);
                        } else {
                            toCommonCallBack.onToCommon(bean.getOrderID(), position);
                        }
            }
        });

        //垫付款支付
        tvDianPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toCommonCallBack.onDianPay(bean.getOrderID(), position, bean.getAdvanceState());
            }
        });

        //维修清单
        tvPayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repairPriceClick.onRepairClick(bean.getOrderID(), position);
            }
        });

        //联系客服
        tvLinkKf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneUtils.getInstance().callPhoe(context);
            }
        });

        //确认收货
        tvConfirmReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmReceive.confirmReceive(bean.getOrderID());
            }
        });

        //申请
        if (bean.getIsShengChengWeiTuoShu().equals("0")) {
            //委托书
            tvWeiTuo.setText("提货委托书");
            //查看
        } else if (bean.getIsShengChengWeiTuoShu().equals("1")) {
            tvWeiTuo.setText("查看委托书");
        }
        //委托书
        tvWeiTuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bean.getIsShengChengWeiTuoShu().equals("0")) {
                    //委托书
                    IntentUtils.getInstance().toActivity(context, WeiTuoShuActivity.class, bean.getOrderID());
                    //查看
                } else if (bean.getIsShengChengWeiTuoShu().equals("1")) {
                    //http://api3.atjubo.com/pages/WebApp/views/LadingBillProxy.aspx
                    String url = "http://api3.atjubo.com/pages/WebApp/views/LadingBillProxy.aspx" + "/?" + FieldConstant.getInstance().ORDER_ID + "=" + bean.getOrderID();
                    IntentUtils.getInstance().toWeiTuoWebActivity(context, url, "查看委托书", bean.getOrderID(), bean.getTIHuoWeiTuoShuEdit());
                }
            }
        });
    }

    private void setType(String type, List<OrderListTypeBean> typeList) {
        int num = typeList.size();
        for (int i = 0; i < num; i++) {
            typeList.get(i).setType(type);
        }
    }

    public void setOnOrderListCallBack(OnOrderListCallBack onOrderListCallBack) {
        this.onOrderListCallBack = onOrderListCallBack;
    }

    public void setDeleteCallBack(OnDeleteCallBack deleteCallBack) {
        this.deleteCallBack = deleteCallBack;
    }

    public void setRepairPriceClick(OnRepairPriceClick repairPriceClick) {
        this.repairPriceClick = repairPriceClick;
    }

    public void setToCommonCallBack(OnToCommonCallBack toCommonCallBack) {
        this.toCommonCallBack = toCommonCallBack;
    }

    public void setConfirmReceive(ConfirmReceive confirmReceive) {
        this.confirmReceive = confirmReceive;
    }
}
