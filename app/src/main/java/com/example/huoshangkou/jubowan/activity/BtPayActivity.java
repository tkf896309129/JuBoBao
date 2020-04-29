package com.example.huoshangkou.jubowan.activity;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ApproveBankListBean;
import com.example.huoshangkou.jubowan.bean.JbBtBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SendCodeUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.utils.TwoPointUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.simple.eventbus.EventBus;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：BtPayActivity
 * 描述：
 * 创建时间：2018-09-06  08:22
 */

public class BtPayActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_pay)
    TextView tvPay;
    @Bind(R.id.tv_unit)
    TextView tvUnit;
    @Bind(R.id.tv_price)
    EditText tvPrice;
    @Bind(R.id.tv_rest_pay_price)
    TextView tvRestPayPrice;
    @Bind(R.id.tv_total_money)
    TextView tvTotalMoney;
    @Bind(R.id.tv_rest_money)
    TextView tvRestMoney;
    @Bind(R.id.tv_days)
    TextView tvDays;
    @Bind(R.id.tv_rz_company)
    TextView tvRzCompany;
    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.tv_send_code)
    TextView tvSendCode;
    @Bind(R.id.tv_bt_yhj)
    TextView tvBtYhj;
    @Bind(R.id.cb_agree)
    CheckBox cbAgree;

    int codeTime = 0;
    String getCode = "";
    String phone = "";
    String orderId = "";
    String money = "";
    String bankId = "";
    String rulePay = "";
    //是否同意白条支付协议
    private boolean isAgreePayRule = false;

    private String couponID = "";
    private String name = "";

    @Override
    public int initLayout() {
        return R.layout.activity_bt_pay;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {

        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().orderList);
        tvTitle.setText("白条支付");
        money = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        orderId = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        btQua(this);
        if (StringUtils.isNoEmpty(money)) {
            tvPrice.setText(money);
        }
        cbAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isAgreePayRule = b;
            }
        });

        tvPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String price = charSequence.toString();
                if (StringUtils.isNoEmpty(price) && StringUtils.isNoEmpty(money)) {
                    if (price.equals(".")) {
                        return;
                    }
                    if (!StringUtils.isNoEmpty(price)) {
                        price = "0.00";
                    }
                    double calPrice = Double.parseDouble(money) - Double.parseDouble(price);

                    if (calPrice >= 0) {
                        tvRestPayPrice.setText(TwoPointUtils.getInstance().getTwoPoint(calPrice) + "");
                    } else {
                        tvRestPayPrice.setText("支付金额大于商品金额");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @OnClick({R.id.tv_send_code, R.id.tv_confirm_pay, R.id.rl_bt_yhj, R.id.rl_rz_company, R.id.iv_clear, R.id.ll_back, R.id.tv_pay_rule})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_send_code:
                if (!StringUtils.isNoEmpty(phone)) {
                    ToastUtils.getMineToast("验证码发送失败");
                    return;
                }
                codeTime = 60;

                SendCodeUtils.getInstance().setYiQianCode(this, tvSendCode, PersonConstant.getInstance().getUserId());
                break;
            case R.id.rl_rz_company:
                Intent intentBank = new Intent(this, BorrowBankActivity.class);
                startActivityForResult(intentBank, 3);
                break;
            case R.id.tv_confirm_pay:
                money = tvPrice.getText().toString().trim();
                if (!StringUtils.isNoEmpty(money)) {
                    ToastUtils.getMineToast("请输入支付金额");
                    return;
                }
                getCode = etCode.getText().toString().trim();
                if (!StringUtils.isNoEmpty(getCode)) {
                    ToastUtils.getMineToast("请获取验证码");
                    return;
                }
//                if (!StringUtils.isNoEmpty(bankId)) {
//                    ToastUtils.getMineToast("请选择入账单位");
//                    return;
//                }0
                if (!isAgreePayRule) {
                    ToastUtils.getMineToast("请阅读并同意白条支付协议");
                    return;
                }
                CopyIosDialogUtils.getInstance().getIosDialog(this, "是否支付", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        btPay(BtPayActivity.this, orderId, money, bankId, getCode);
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });

                break;
            case R.id.iv_clear:
                tvPrice.setText("");
                break;
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_pay_rule:
                IntentUtils.getInstance().toWebActivity(this, rulePay, "聚玻白条支付协议");
                break;
            case R.id.rl_bt_yhj:
//                IntentUtils.getInstance().toActivity(this, CouponActivity.class);
                Intent intent = new Intent(this, CouponActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    //获取相关协议
    public void getXyRule(String type, String code) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(this, UrlConstant.getInstance().URL
                + PostConstant.getInstance().IOUS_AGREEMENT_URL
                + FieldConstant.getInstance().TYPE + "=" + type + "&"
                + FieldConstant.getInstance().AGREEMENT_CODE + "=" + code, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    rulePay = jsonObject.getString("ReObj");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }

        switch (requestCode) {
            case 3:
                ApproveBankListBean bankListBean = (ApproveBankListBean) data.getSerializableExtra("bean");
                bankId = bankListBean.getID() + "";
                tvRzCompany.setText(bankListBean.getKHH() + " ");
                break;
            case 1:
                name = data.getStringExtra(IntentUtils.getInstance().VALUE);
                couponID = data.getStringExtra(IntentUtils.getInstance().ID);
                tvBtYhj.setText(name+"  ");
                break;
        }
    }

    //白条支付
    public void btPay(Context context, String orderId, String money, String bankId, String code) {
        OkhttpUtil.getInstance().setUnCacheData(context, "白条支付中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().BT_PAY
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().ORDER_ID + "=" + orderId + "&"
                + FieldConstant.getInstance().ACCOUNT_BANK_ID + "=" + bankId + "&"
                + FieldConstant.getInstance().MOBIE_CODE + "=" + code + "&"
                + FieldConstant.getInstance().COUPON_ID + "=" + couponID + "&"
                + FieldConstant.getInstance().MONEY + "=" + money, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("支付成功");
                    ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().orderList);
                    EventBus.getDefault().post("initOrder", "initOrder");
                } else {
                    ToastUtils.getMineToast(successBean.getErrMsg());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    //判断当前用户的白条资格
    public void btQua(Context context) {
        OkhttpUtil.getInstance().setUnCacheData(context, "数据加载中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().BT_QUA_GET
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                JbBtBean btBean = JSON.parseObject(json, JbBtBean.class);
                if (btBean.getReObj() == null) {
                    CopyIosDialogUtils.getInstance().getErrorDialogNoCancel(BtPayActivity.this, "您暂时没有白条额度", new CopyIosDialogUtils.ErrDialogCallBack() {
                        @Override
                        public void confirm() {
                            BtPayActivity.this.finish();
                        }
                    });
                    return;
                }
                if (StringUtils.isNoEmpty(money) && StringUtils.isNoEmpty(btBean.getReObj().getIousRemainingAmount() + "")) {
                    if (Double.parseDouble(money) > Double.parseDouble(btBean.getReObj().getIousRemainingAmount())) {
                        if (Double.parseDouble(btBean.getReObj().getIousRemainingAmount()) <= 0) {
                            CopyIosDialogUtils.getInstance().getErrorDialogNoCancel(BtPayActivity.this, "您的白条额度不足以支付该订单", new CopyIosDialogUtils.ErrDialogCallBack() {
                                @Override
                                public void confirm() {
                                    BtPayActivity.this.finish();
                                }
                            });
                            return;
                        }
                        tvPrice.setText(btBean.getReObj().getIousRemainingAmount() + "");
                    }
                }
                phone = btBean.getReObj().getTel();
                tvDays.setText(btBean.getReObj().getIousRemainingDays() + "天");
                tvRestMoney.setText(btBean.getReObj().getIousRemainingAmount() + "");
                tvTotalMoney.setText(btBean.getReObj().getIousTotalAmount() + "");

                getXyRule("2", btBean.getReObj().getContractNo());
            }

            @Override
            public void onFail() {

            }
        });
    }
}
