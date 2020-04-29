package com.example.huoshangkou.jubowan.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.OrderCommitFunction;
import com.example.huoshangkou.jubowan.adapter.PayOrderAdapter;
import com.example.huoshangkou.jubowan.alipay.PayResult;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.OrderListsBean;
import com.example.huoshangkou.jubowan.bean.PayOrderBean;
import com.example.huoshangkou.jubowan.constant.BundleConstant;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnDialogCallBack;
import com.example.huoshangkou.jubowan.inter.SuccessCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.EditDialogUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.utils.TwoPointUtils;
import com.example.huoshangkou.jubowan.utils.WechatPayUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：PayOrderActivity
 * 描述：
 * 创建时间：2017-03-23  15:22
 */
public class PayOrderActivity extends BaseActivity {
    private PayOrderAdapter payOrderAdapter;
    private List<PayOrderBean> orderBeanList;
    @Bind(R.id.lv_pay_order)
    ListView lvPayOrder;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_money)
    TextView tvMoney;
    @Bind(R.id.tv_order_num)
    TextView tvOrderNum;
    private int checkType = -1;

    //判断是否是支付宝支付
    private boolean isAliPay = false;
    //配件支付
    private boolean isUnderPay = false;
    //价格
    private String price = "";
    //订单id
    private String orderId = "";
    private String introName;
    private String body;
    private String scheme;
    //配件
    private static final int SDK_PAY_FLAG = 1;
    //垫付款支付
    private OrderListsBean orderListsBean;
    //垫付款金额
    private String dianMoney = "";
    private int state;
    //是否没有白条支付
    private boolean isNoBtPay = false;

    private final int PAY_RESULT = 101;
    private final int PAY_RESULT_SUCCESS = 102;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    LogUtils.i(resultInfo);
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {

                        Toast.makeText(getApplicationContext(), "支付成功", Toast.LENGTH_SHORT).show();
                        IntentUtils.getInstance().toActivity(getContext(), AfterCommitOrderActivity.class, price);
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(getApplicationContext(), "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
//                            builder.show();
                            CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "支付失败，是否重新支付", new CopyIosDialogUtils.iosDialogClick() {
                                @Override
                                public void confimBtn() {
                                    pay(sign);
                                }

                                @Override
                                public void cancelBtn() {

                                }
                            });
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    private String url = "";

    @Override
    public int initLayout() {
        return R.layout.activity_pay_order;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().orderList);
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().padPayList);
        tvTitle.setText("支付订单");

//        price = "0.01";
        price = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        orderId = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        introName = getIntent().getStringExtra(IntentUtils.getInstance().INTRO_NAME);
        body = getIntent().getStringExtra(IntentUtils.getInstance().BODY);
        scheme = getIntent().getStringExtra(IntentUtils.getInstance().SCHEME);

        String strBt = getIntent().getStringExtra(IntentUtils.getInstance().STR);
        if (StringUtils.isNoEmpty(strBt)) {
            isNoBtPay = true;
        }
//        ToastUtils.getMineToast(strBt);
//        if (StringUtils.isNoEmpty(strBt)) {
//            String[] split = strBt.split(",");
//            if (split.length >= 2) {
//                if (split[0].equals("6") || split[0].equals("-6") || split[0].equals("7") || split[0].equals("-7")) {
//                    if (StringUtils.isNoEmpty(split[1])) {
//                        isNoBtPay = true;
//                    }
//                }
//            }
//        }

        orderListsBean = (OrderListsBean) getIntent().getSerializableExtra(IntentUtils.getInstance().BEAN_TYPE);
        if (orderListsBean != null) {
            //垫付款状态
            state = orderListsBean.getAdvanceState();
        }
        String str = getIntent().getStringExtra(IntentUtils.getInstance().STR);
        if (StringUtils.isNoEmpty(str)) {
            isUnderPay = true;
        }

        //阿里支付
        if (StringUtils.isNoEmpty(introName)) {
            isAliPay = true;
            //支付宝支付链接
            url = UrlConstant.getInstance().URL + PostConstant.getInstance().ALIPAY
                    + FieldConstant.getInstance().ALL_PRICE + "=" + price + "&"
                    + FieldConstant.getInstance().PAY_ORDER_ID + "=" + orderId + "&"
                    + FieldConstant.getInstance().PAY_SUBJECT + "=" + EncodeUtils.getInstance().getEncodeString(introName) + "&"
                    + FieldConstant.getInstance().BODY + "=" + EncodeUtils.getInstance().getEncodeString(body) + "&"
                    + FieldConstant.getInstance().APP_SCHEME + "=" + EncodeUtils.getInstance().getEncodeString(scheme);

            LogUtils.i(url);
        }

        tvMoney.setText("￥" + price);
        tvOrderNum.setText("订单号：" + orderId);

        orderBeanList = new ArrayList<>();
        payOrderAdapter = new PayOrderAdapter(getContext(), orderBeanList, R.layout.item_pay_order);
        lvPayOrder.setAdapter(payOrderAdapter);
        lvPayOrder.setDividerHeight(0);
        initBanData();
        lvPayOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                              @Override
                                              public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                  checkType = orderBeanList.get(i).getType();
                                                  payOrderAdapter.setItemClick(i);
                                                  payOrderAdapter.notifyDataSetChanged();
                                              }
                                          }
        );
    }

    @OnClick({R.id.ll_back, R.id.tv_confirm_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_confirm_pay:
                switch (checkType) {
                    //线上付款
                    case 0:
                        CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "请您登陆PC端(聚玻网)www.atjubo.com待支付订单中进行支付", new CopyIosDialogUtils.ErrDialogCallBack() {
                            @Override
                            public void confirm() {

                            }
                        });
                        break;
                    //线下
                    case 1:
                        IntentUtils.getInstance().toActivity(getContext(), PayUnderLineActivity.class, price, orderId);
                        break;
                    //融资支付
                    case 2:
                        IntentUtils.getInstance().toActivity(getContext(), CompanyMoneyActivity.class, price);
                        break;
                    //支付宝支付
                    case 3:
                        getLocalCommit();
                        break;
                    case -1:
                        ToastUtils.getMineToast("请选择支付方式");
                        break;
                    //微信支付
                    case 4:
//                        price = 0.1 + "";
                        WechatPayUtils.getInstance().wechatPay(getContext(), orderId, (int) (Double.parseDouble(price) * 100) + "");
                        break;
                    //垫付款支付
                    case 5:
                        String url = "http://www.atjubo.com/jbpay/Pay/HeTongAdvancePayment?OrderID=" + orderId;
                        IntentUtils.getInstance().toNextWebActivity(this,  url, "合同预览", "垫付款支付", "下一步", orderId);

//                        if (orderListsBean == null) {
//                            return;
//                        }
//                        dianMoney = orderListsBean.getTotalPrice();
//                        if (state != 0) {
//                            CopyIosDialogUtils.getInstance().getErrorDialog(this, "垫付款已支付", new CopyIosDialogUtils.ErrDialogCallBack() {
//                                @Override
//                                public void confirm() {
//
//                                }
//                            });
//                            return;
//                        }
//                        EditDialogUtils.getInstance().showEditTextCancelDialog("num", this, view, "订单总金额：" + price + "元，运费总额"
//                                + orderListsBean.getWlPrice() + "元,合计"
//                                + TwoPointUtils.getInstance().getTwoPoint((Double.parseDouble(price)
//                                + Double.parseDouble(orderListsBean.getWlPrice())))
//                                + "元，请输入您需要的垫付款金额", new OnDialogCallBack() {
//                            @Override
//                            public void addWorkExp(String content, PopupWindow popupSearchWindow) {
//                                if (!StringUtils.isNoEmpty(content)) {
//                                    ToastUtils.getMineToast("请输入垫付款金额");
//                                    return;
//                                }
//                                Double dianPrice = Double.parseDouble(content);
//                                if (dianPrice < 0) {
//                                    ToastUtils.getMineToast("垫付金额不能小于零");
//                                    return;
//                                }
//                                if (dianPrice > Double.parseDouble(price)
//                                        + Double.parseDouble(orderListsBean.getWlPrice())) {
//                                    ToastUtils.getMineToast("垫付金额不能大于合计金额");
//                                    return;
//                                }
//                                popupSearchWindow.dismiss();
//                                OrderCommitFunction.getInstance().dianPay(orderId, PayOrderActivity.this, content, new SuccessCallBack() {
//                                    @Override
//                                    public void onSuccess() {
//                                        IntentUtils.getInstance().toActivity(getContext(), AfterCommitOrderActivity.class, price);
//                                        ToastUtils.getMineToast("提交成功");
//                                    }
//
//                                    @Override
//                                    public void onFail() {
//                                        ToastUtils.getMineToast("提交失败");
//                                    }
//                                });
//                            }
//                        });
                        break;
                    case 6:
                        IntentUtils.getInstance().toActivity(this, BtPayActivity.class, price, orderId);
//                        DialogUtils.getInstance().btPayDialog(this, price, orderId, new SuccessCallBack() {
//                            @Override
//                            public void onSuccess() {
//                                PayOrderActivity.this.finish();
//                            }
//
//                            @Override
//                            public void onFail() {
//
//                            }
//                        });
                        break;
                }
        }
    }

    private void initBanData() {
        PayOrderBean underLine = new PayOrderBean();
        underLine.setBankIntro("支持多家银行汇款");
        underLine.setBankType("线下汇款");
        underLine.setType(1);
        underLine.setImageType(R.mipmap.under_line);


        PayOrderBean btPay = new PayOrderBean();
        btPay.setBankIntro("支付后前三个自然日免服务费");
        btPay.setBankType("白条支付");
        btPay.setType(6);
        btPay.setImageType(R.mipmap.icon_zhifu);

        PayOrderBean rongZi = new PayOrderBean();
        rongZi.setBankType("融资支付");
        rongZi.setBankIntro("推荐资金周转困难用户使用");
        rongZi.setImageType(R.mipmap.rz_pay);
        rongZi.setType(2);

        PayOrderBean onLinePay = new PayOrderBean();
        onLinePay.setBankType("线上支付");
        onLinePay.setBankIntro("更安全、更便捷");
        onLinePay.setImageType(R.mipmap.onlinepay_icon);
        onLinePay.setType(0);

        PayOrderBean aliPay = new PayOrderBean();
        aliPay.setBankType("支付宝支付");
        aliPay.setBankIntro("让支付更便捷");
        aliPay.setImageType(R.mipmap.zhifubao_icon);
        aliPay.setType(3);

        //微信支付
        PayOrderBean wxPay = new PayOrderBean();
        wxPay.setBankType("微信支付");
        wxPay.setBankIntro("推荐使用微信");
        wxPay.setImageType(R.mipmap.weixinpay_icon);
        wxPay.setType(4);

        //垫付款支付
        PayOrderBean dianFuPay = new PayOrderBean();
        dianFuPay.setBankType("垫付款支付");
        dianFuPay.setBankIntro("没钱找垫付");
        dianFuPay.setImageType(R.mipmap.icon_dianhu);
        dianFuPay.setType(5);

        if (isAliPay) {
            orderBeanList.add(aliPay);
            orderBeanList.add(wxPay);
            if (isAliPay && isUnderPay) {
                orderBeanList.clear();
                orderBeanList.add(aliPay);
                orderBeanList.add(wxPay);
                orderBeanList.add(underLine);
            }
            if (orderListsBean != null && orderListsBean.getOrderListwb() != null) {
                orderBeanList.add(underLine);
            }
        } else {
            orderBeanList.add(onLinePay);
            orderBeanList.add(underLine);
            orderBeanList.add(rongZi);
            if (!isNoBtPay) {
                orderBeanList.add(btPay);
            }
        }
        if (orderListsBean != null && orderListsBean.getIsAllowPadPaymentPay() == 1) {
            orderBeanList.add(dianFuPay);
        }
        payOrderAdapter.notifyDataSetChanged();
    }

    /**
     * call alipay sdk pay. 调用SDK支付
     */
    public void pay(String signOrder) {
        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        final String payInfo = signOrder;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(PayOrderActivity.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    String sign;

    //服务器回调
    public void getLocalCommit() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), url, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                try {
                    sign = new JSONObject(json).getString("ReObj");
                    pay(sign);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    //微信支付成功
    @Subscriber(tag = "wx_pay")
    public void wxPaySuccess(String str) {
        if (StringUtils.isNoEmpty(str)) {
            Toast.makeText(getApplicationContext(), "支付成功", Toast.LENGTH_SHORT).show();
            IntentUtils.getInstance().toActivity(getContext(), AfterCommitOrderActivity.class, price);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
