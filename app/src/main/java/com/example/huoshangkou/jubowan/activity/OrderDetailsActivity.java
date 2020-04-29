package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.OrderCommitFunction;
import com.example.huoshangkou.jubowan.adapter.OrderProAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.OrderListTypeBean;
import com.example.huoshangkou.jubowan.bean.OrderListsBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.OrderTypeConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.SuccessCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.BlueTxtUtils;
import com.example.huoshangkou.jubowan.utils.CommonStarUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhoneUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.utils.TwoPointUtils;
import com.example.huoshangkou.jubowan.view.ScrollListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：OrderDetailsActivity
 * 描述：订单详情
 * 创建时间：2017-02-09  14:14
 */

public class OrderDetailsActivity extends BaseActivity {
    @Bind(R.id.ll_all_common)
    LinearLayout llAllCommon;

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_order_num)
    TextView tvOrderNum;
    @Bind(R.id.tv_order_time)
    TextView tvOrderTime;

    @Bind(R.id.tv_pro_price)
    TextView tvProPrice;
    @Bind(R.id.tv_trans_price)
    TextView tvTransPrice;

    @Bind(R.id.tv_all_price)
    TextView tvAllPrice;

    @Bind(R.id.tv_receive_man)
    TextView tvReceiveMan;
    @Bind(R.id.tv_link_phone)
    TextView tvLinkPhone;
    @Bind(R.id.tv_receive_address)
    TextView tvReceiveAddress;
    @Bind(R.id.lv_order)
    ScrollListView scrollListView;

    private String url = "";
    OrderListsBean listsBean;

    @Bind(R.id.tv_delete)
    TextView tvDelete;
    @Bind(R.id.tv_common)
    TextView tvCommon;
    @Bind(R.id.tv_pay)
    TextView tvPay;
    @Bind(R.id.tv_link_kf)
    TextView tvLinkKf;
    @Bind(R.id.tv_descript)
    TextView tvDescipt;

    @Bind(R.id.tv_state)
    TextView tvPayType;
    @Bind(R.id.ll_all)
    LinearLayout llAll;

    @Bind(R.id.ll_link_address)
    LinearLayout llLinkAddress;
    @Bind(R.id.tv_confirm_receive)
    TextView tvConfirmReceive;
    @Bind(R.id.tv_wei_tuo_shu)
    TextView tvWeiTuo;
    @Bind(R.id.tv_pay_list)
    TextView tvPayList;

    //是否申请委托书
    private String isWeiTuo = "";
    //评价订单
    private final int COMMON = 1;
    private int position = -1;
    private String id = "";

    private boolean isYp = false;
    private boolean isWx = false;

    //初始化布局
    @Override
    public int initLayout() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().orderList);
        url = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        listsBean = (OrderListsBean) getIntent().getSerializableExtra(IntentUtils.getInstance().BEAN_TYPE);

        position = getIntent().getIntExtra(IntentUtils.getInstance().POSITION, -1);
        //设置标题
        tvTitle.setText("订单详情");

        getOrderDetail(listsBean);
    }


    //获取订单详情
    public void getOrderDetail(OrderListsBean reObj) {
        List<OrderListTypeBean> typeList = new ArrayList<>();
        if (reObj.getOrderListf() != null) {
            setType(OrderTypeConstant.getInstance().FL, reObj.getOrderListf());
            typeList.addAll(reObj.getOrderListf());
            if (reObj.getIsPtwl().equals("0")) {
                llLinkAddress.setVisibility(View.GONE);
            }
        }
        if (reObj.getOrderListw() != null) {
            isWx = true;
            setType(OrderTypeConstant.getInstance().WX, reObj.getOrderListw());
            typeList.addAll(reObj.getOrderListw());
        }
        if (reObj.getOrderListp() != null) {
            setType(OrderTypeConstant.getInstance().PJ, reObj.getOrderListp());
            typeList.addAll(reObj.getOrderListp());
        }
        if (reObj.getOrderListy() != null) {
            isYp = true;
            setType(OrderTypeConstant.getInstance().YP, reObj.getOrderListy());
            typeList.addAll(reObj.getOrderListy());
            if (reObj.getIsPtwl().equals("0")) {
                llLinkAddress.setVisibility(View.GONE);
            }
        }
        if (reObj.getOrderListyl() != null) {
            //和原片支付方式一样
            isYp = true;
            setType(OrderTypeConstant.getInstance().YL, reObj.getOrderListyl());
            typeList.addAll(reObj.getOrderListyl());
            if (reObj.getIsPtwl().equals("0")) {
                llLinkAddress.setVisibility(View.GONE);
            }
        }

        isWeiTuo = reObj.getIsShengChengWeiTuoShu();

        if (StringUtils.isNoEmpty(reObj.getIsShengChengWeiTuoShu())) {
            //申请
            if (reObj.getIsShengChengWeiTuoShu().equals("0")) {
                //委托书
                tvWeiTuo.setText("提货委托书");
                //查看
            } else if (reObj.getIsShengChengWeiTuoShu().equals("1")) {
                tvWeiTuo.setText("查看委托书");
            }
        }

        OrderProAdapter proAdapter = new OrderProAdapter(getContext(), typeList, R.layout.item_order_product);
        scrollListView.setAdapter(proAdapter);
        scrollListView.setDividerHeight(0);
        id = reObj.getOrderID();

        tvOrderNum.setText(reObj.getOrderID());
        tvOrderTime.setText(reObj.getCreateTime());


//        tvAllPrice.setText("￥" + reObj.getTotalPrice());
        String pricePro = "0.00";
        if (StringUtils.isNoEmpty(listsBean.getTotalPrice()) && !isWx) {
            tvAllPrice.setText(Html.fromHtml("" + BlueTxtUtils.getInstance().getBlueTxt("￥" + listsBean.getTotalPrice())));
            pricePro = listsBean.getTotalPrice();

//            if (StringUtils.isNoEmpty(listsBean.getNeedPayAmount()) && listsBean.getPayTpye() != 7) {
//                tvAllPrice.setText(Html.fromHtml("" + BlueTxtUtils.getInstance().getBlueTxt("￥" + listsBean.getNeedPayAmount())));
//                pricePro = listsBean.getNeedPayAmount();
//            } else {
//                tvAllPrice.setText(Html.fromHtml("" + BlueTxtUtils.getInstance().getBlueTxt("￥" + listsBean.getTotalPrice())));
//                pricePro = listsBean.getTotalPrice();
//            }
        } else if (!StringUtils.isNoEmpty(listsBean.getTotalPrice()) && isWx) {
            tvAllPrice.setText("待确认");
            pricePro = "带确认";
        } else if (StringUtils.isNoEmpty(listsBean.getTotalPrice()) && isWx) {

            String price = "";
            double totalPrice = Double.parseDouble(listsBean.getTotalPrice());
            double yfPrice = Double.parseDouble(listsBean.getYuFuPrice());
            double syPrice = 0.0;
            if (StringUtils.isNoEmpty(listsBean.getShengYuPrice())) {
                syPrice = Double.parseDouble(listsBean.getShengYuPrice());
            }
            LogUtils.i(totalPrice + "  " + yfPrice + "  " + syPrice);

            if (listsBean.getOrderType().equals("维保")) {
                //没有支付
                if (syPrice == totalPrice) {
                    tvAllPrice.setText(Html.fromHtml("" + BlueTxtUtils.getInstance().getBlueTxt(listsBean.getTotalPrice()) + "元  " + "预付金额：" + BlueTxtUtils.getInstance().getBlueTxt(yfPrice + "") + "元"));
                    pricePro = listsBean.getTotalPrice();
                    //支付过一次
                } else if (syPrice > 0 && syPrice < totalPrice) {
                    pricePro = syPrice + "";
                    tvAllPrice.setText(Html.fromHtml("" + BlueTxtUtils.getInstance().getBlueTxt(syPrice + "") + "元"));
                    //支付完成
                } else if (syPrice <= 0) {
                    pricePro = listsBean.getTotalPrice();
                    tvAllPrice.setText(Html.fromHtml("" + BlueTxtUtils.getInstance().getBlueTxt(listsBean.getTotalPrice()) + "元"));
                }
            } else {
                pricePro = listsBean.getTotalPrice();
                tvAllPrice.setText(Html.fromHtml("" + BlueTxtUtils.getInstance().getBlueTxt(listsBean.getTotalPrice()) + "元"));
            }
        }

        tvReceiveMan.setText(reObj.getLinkMan());
        tvLinkPhone.setText(reObj.getLinkTel());
        tvReceiveAddress.setText(reObj.getProvinces());


        if (StringUtils.isNoEmpty(reObj.getTotalPrice()) && StringUtils.isNoEmpty(reObj.getWlPrice())) {
//            tvProPrice.setText("￥" + TwoPointUtils.getInstance().getTwoPoint(Double.parseDouble(reObj.getTotalPrice()) - Double.parseDouble(reObj.getWlPrice())));
            tvProPrice.setText("￥" + pricePro);
        }


        //物流价格
        if (StringUtils.isNoEmpty(reObj.getWlPrice())) {
            tvTransPrice.setText("￥" + reObj.getWlPrice());
        } else {
            tvTransPrice.setText("￥ 0.00");
        }
        if (reObj.getCommitScore() > 0) {
            llAll.setVisibility(View.VISIBLE);
        } else {
            llAll.setVisibility(View.GONE);
        }

        //整体评分
        CommonStarUtils.getInstance().setCommonStarsNum(reObj.getCommitScore(), llAllCommon);
        tvDescipt.setText(reObj.getCommitTxt());

        // payTape 付款方式 0 1 2 线上支付  线下支付  贷付支付  // 审核时 0 1 2 订单审核  线下支付审核  贷付审核
        // 待支付  默认返回的是 0
        // payTape 付款方式 0 1 2 线上支付  线下支付  贷付支付  // 审核时 0 1 2 订单审核  线下支付审核  贷付审核
        // 待支付  默认返回的是 0  都不显示
        // 维修维保  审核的时候  订单审核  其他状态  支付宝支付
        switch (listsBean.getPayTpye()) {
            //未支付
            case 0:
                if (listsBean.getOrderState() == 0) {
                    tvPayType.setText("订单审核中");
                } else if (listsBean.getOrderState() == 1) {
                    tvPayType.setText("");
                } else if (isWx && listsBean.getOrderState() == 0) {
                    tvPayType.setText("订单审核中");
                } else if (isWx && (listsBean.getOrderState() == 4 || listsBean.getOrderState() == 2)) {
//                    tvPayType.setText("支付宝支付");
                    if (listsBean.getPayTpye() != 4 && listsBean.getPayTpye() != 1 && listsBean.getPayTpye() != 2) {
                        switch (listsBean.getPayWay()) {
                            //微信支付
                            case 1:
                                if (listsBean.getOrderState() == 2 || listsBean.getOrderState() == 3 || listsBean.getOrderState() == 4) {
                                    tvPayType.setText("微信支付");
                                } else {
                                    tvPayType.setText("");
                                }
                                break;
                            //支付宝支付
                            case 0:
                                if (listsBean.getOrderState() == 2 || listsBean.getOrderState() == 3 || listsBean.getOrderState() == 4) {
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
                if (listsBean.getOrderState() == 0) {
                    tvPayType.setText("线下支付审核");
                } else if (isWx && listsBean.getOrderState() != 0) {
                    tvPayType.setText("支付宝支付");
                } else {
                    tvPayType.setText("线下支付");
                }
                break;
            //融资支付
            case 2:
                if (listsBean.getOrderState() == 0) {
                    tvPayType.setText("融资支付审核");
                } else if (isWx && listsBean.getOrderState() != 0) {
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
                if (StringUtils.isNoEmpty(listsBean.getTotalPrice()) && StringUtils.isNoEmpty(listsBean.getNeedPayAmount())) {
                    double price = (Double.parseDouble(listsBean.getTotalPrice()) - Double.parseDouble(listsBean.getNeedPayAmount()));
                    tvPayType.setText("白条支付\n(支付金额：" + TwoPointUtils.getInstance().getTwoPoint(price) + "元)");
                }
                break;
            case -7:
                tvPayType.setText("白条支付审核中");
                break;
        }

        switch (listsBean.getPayWay()) {
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

        //  0：待确认，1：待支付，2：待评价，3全部，4已完成
        //2 全部 ---  0 待受理 --- 1 待支付 --- 3 待收货 --- 4 已完成
        //  0：待确认，1：待支付，2：待评价，3全部，4已完成
        //2 全部 ---  0 待受理 --- 1 待支付 --- 3 待收货 --- 4 已完成
        //0：待受理，1：已受理，2：已支付，3=已发货，4=已收货
        switch (listsBean.getOrderState()) {
            case 0:
                tvPay.setVisibility(View.GONE);
                tvCommon.setVisibility(View.GONE);
                tvLinkKf.setVisibility(View.GONE);
                tvConfirmReceive.setVisibility(View.GONE);
                if (isWx) {
                    if (listsBean.getOrderType().equals("维保")) {
                        tvLinkKf.setVisibility(View.VISIBLE);
                        tvPayList.setVisibility(View.GONE);
                    } else {
                        tvLinkKf.setVisibility(View.GONE);
                        tvPayList.setVisibility(View.VISIBLE);
                    }
                } else {
                    tvPayList.setVisibility(View.GONE);
                }
                if (listsBean.getPayTpye() != 0) {
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
                if (isYp && (listsBean.getPayTpye() != 7 && listsBean.getPayTpye() != -7 && listsBean.getPayTpye() != 6 && listsBean.getPayTpye() != -6)) {
                    if (listsBean.getAdvanceState() > 0) {
                        tvPayType.setText("垫付款审核中");
                    } else if (listsBean.getAdvanceState() == 0) {
                        tvPayType.setText("垫付款未支付");
                    } else if (listsBean.getAdvanceState() == -1) {
                        tvPayType.setText("垫付款审核失败");
                    }
                }
//                if (bean.getAdvanceState() == 0 && isYp) {
//                    tvDianPay.setVisibility(View.VISIBLE);
//                } else if (bean.getAdvanceState() != 0 && isYp) {
//                    tvDianPay.setVisibility(View.GONE);
//                }
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
                break;
            case 2:
                tvDelete.setVisibility(View.GONE);
                tvPay.setVisibility(View.GONE);
                tvCommon.setVisibility(View.GONE);
                tvConfirmReceive.setVisibility(View.GONE);
                tvWeiTuo.setVisibility(View.GONE);
                tvPayList.setVisibility(View.GONE);
                tvLinkKf.setVisibility(View.VISIBLE);
                break;
            case 3:
                tvDelete.setVisibility(View.GONE);
                tvPay.setVisibility(View.GONE);
                tvCommon.setVisibility(View.GONE);
                tvConfirmReceive.setVisibility(View.VISIBLE);

                if (isYp) {
                    if (reObj.getPayTpye() == 4) {
                        tvWeiTuo.setVisibility(View.VISIBLE);
                    } else {
                        tvWeiTuo.setVisibility(View.GONE);
                    }
                } else {
                    tvWeiTuo.setVisibility(View.GONE);
                }

                tvPayList.setVisibility(View.GONE);
                tvLinkKf.setVisibility(View.GONE);
                break;
            case 4:
                if (listsBean.getIsEvaluate() == 0) {
                    tvCommon.setVisibility(View.VISIBLE);
                } else {
                    tvCommon.setVisibility(View.GONE);
                }
                tvConfirmReceive.setVisibility(View.GONE);
                tvDelete.setVisibility(View.GONE);
                tvWeiTuo.setVisibility(View.GONE);
                tvPay.setVisibility(View.GONE);
                tvPayList.setVisibility(View.GONE);
                tvLinkKf.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void setType(String type, List<OrderListTypeBean> typeList) {
        int num = typeList.size();
        for (int i = 0; i < num; i++) {
            typeList.get(i).setType(type);
        }
    }

    @OnClick({R.id.tv_pay, R.id.tv_wei_tuo_shu, R.id.tv_delete, R.id.tv_common, R.id.ll_back, R.id.tv_link_kf, R.id.tv_confirm_receive})
    public void onClick(View view) {
        switch (view.getId()) {
            //评价
            case R.id.tv_common:
                if (listsBean == null) {
                    ToastUtils.getMineToast("获取订单信息失败");
                    return;
                }
                IntentUtils.getInstance().toActivity(getContext(), CommonActivity.class, listsBean.getOrderID(), OrderTypeConstant.getInstance().COMMON_ORDER);
                break;
            //删除
            case R.id.tv_delete:
                if (listsBean == null) {
                    return;
                }
                CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否删除订单：" + listsBean.getOrderID(), new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        OrderCommitFunction.getInstance().deleteOrder(listsBean.getOrderID(), getContext(), new SuccessCallBack() {
                            @Override
                            public void onSuccess() {
                                //刷新数据
                                Intent intent1 = new Intent();
                                intent1.putExtra(IntentUtils.getInstance().POSITION, position);
                                intent1.putExtra(IntentUtils.getInstance().TYPE, "delete");
                                setResult(103, intent1);
                                OrderDetailsActivity.this.finish();
                                ToastUtils.getMineToast("删除成功");
                            }

                            @Override
                            public void onFail() {
                                ToastUtils.getMineToast("删除失败");
                            }
                        });
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
                break;
            //支付
            case R.id.tv_pay:
                if (listsBean == null) {
                    return;
                }
                if (listsBean.getPayWay() == 2 || listsBean.getPayWay() == 4) {
                    ToastUtils.getMineToast("线下订单订单审核中");
                    return;
                }
                //维修维保
                if (listsBean.getOrderListw() != null || listsBean.getOrderListwb() != null) {
                    String price = "";
                    String orderId = "";
                    double totalPrice = Double.parseDouble(listsBean.getTotalPrice());
                    double yfPrice = Double.parseDouble(listsBean.getYuFuPrice());
                    double syPrice = 0.0;
                    if (StringUtils.isNoEmpty(listsBean.getShengYuPrice())) {
                        syPrice = Double.parseDouble(listsBean.getShengYuPrice());
                    }

                    //没有支付
                    if (syPrice == totalPrice) {
                        price = yfPrice + "";
                        orderId = listsBean.getOrderID() + "WD";
                        //支付过一次
                    } else if (syPrice > 0 && syPrice < totalPrice) {
                        price = syPrice + "";
                        orderId = listsBean.getOrderID() + "WS";
                        //支付完成
                    } else if (syPrice <= 0) {
                        price = totalPrice + "";
                        orderId = listsBean.getOrderID();
                    }
                    IntentUtils.getInstance().toRepairPayActivity(OrderDetailsActivity.this, price, orderId, "维系维保支付", "维系维保支付", "维系维保支付", listsBean);
                    //配件
                } else if (listsBean.getOrderListp() != null) {
                    IntentUtils.getInstance().toPayActivity(OrderDetailsActivity.this, listsBean.getTotalPrice(), listsBean.getOrderID(), "配件支付", "配件支付", "配件支付", OrderTypeConstant.getInstance().PJ, listsBean);
                    //其他
                } else {
                    String price = "0.00";
                    if (StringUtils.isNoEmpty(listsBean.getNeedPayAmount())) {
                        price = listsBean.getNeedPayAmount();
                    } else {
                        price = listsBean.getTotalPrice();
                    }
                    String type = "";
                    if ((listsBean.getPayTpye() == 7 || listsBean.getPayTpye() == -7 || listsBean.getPayTpye() == 6 || listsBean.getPayTpye() == -6)) {
                        type = "btPay";
                    }
                    IntentUtils.getInstance().toPayActivity(OrderDetailsActivity.this, price, listsBean.getOrderID(), listsBean, type);
                }
                break;
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_link_kf:
                PhoneUtils.getInstance().callPhoe(getContext());
                break;
            //确认收货
            case R.id.tv_confirm_receive:
                CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否确认收货", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        String url = UrlConstant.getInstance().CONFIRM_SIGN + id;
                        IntentUtils.getInstance().toNextWebActivity(OrderDetailsActivity.this, url, "订单确认书", "订单确认书", "确认收货", id);

//                        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
//                                + PostConstant.getInstance().CONFIRM_RECEPIT
//                                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
//                                + FieldConstant.getInstance().ORDER_ID + "=" + id, new OkhttpCallBack() {
//                            @Override
//                            public void onSuccess(String json) {
//
//                            }
//
//                            @Override
//                            public void onFail() {
//                                ToastUtils.getMineToast("确认失败");
//                            }
//                        });
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
                break;
            case R.id.tv_wei_tuo_shu:
                if (!StringUtils.isNoEmpty(isWeiTuo)) {
                    return;
                }
                if (isWeiTuo.equals("0")) {
                    //委托书
                    IntentUtils.getInstance().toActivity(this, WeiTuoShuActivity.class, id);
                    //查看
                } else if (isWeiTuo.equals("1")) {
                    //http://api3.atjubo.com/pages/WebApp/views/LadingBillProxy.aspx
                    String url = "http://api3.atjubo.com/pages/WebApp/views/LadingBillProxy.aspx" + "/?" + FieldConstant.getInstance().ORDER_ID + "=" + id;
                    IntentUtils.getInstance().toWebActivity(this, url, "查看委托书", id);
                }
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case COMMON:
                //总评分 服务评分 物流速度
                String common = data.getStringExtra(IntentUtils.getInstance().TYPE);
                String[] split = common.split(",");
                if (split != null && split[0] != null) {
                    listsBean.setCommitScore(Integer.parseInt(split[0]));
                }
                if (Integer.parseInt(split[0]) > 0) {
                    llAll.setVisibility(View.VISIBLE);
                } else {
                    llAll.setVisibility(View.GONE);
                }
                CommonStarUtils.getInstance().setCommonStarsNum(Integer.parseInt(split[0]), llAllCommon);
                break;
        }
    }
}
