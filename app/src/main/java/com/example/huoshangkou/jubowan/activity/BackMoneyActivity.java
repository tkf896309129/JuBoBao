package com.example.huoshangkou.jubowan.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.BackMoneyPriceBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.KeyboardChangeListener;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：BackMoneyActivity
 * 描述：
 * 创建时间：2018-08-23  14:43
 */

public class BackMoneyActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.et_price)
    EditText etBackMoney;
    @Bind(R.id.tv_service_price)
    TextView tvServicePrice;
    @Bind(R.id.tv_back_bj)
    TextView tvBackBj;
    @Bind(R.id.tv_yq_price)
    TextView tvYqPrice;
    @Bind(R.id.tv_receive_account)
    TextView tvReceiveAccount;
    @Bind(R.id.tv_receive_account_num)
    TextView tvReceiveAccountNum;

    private String orderId = "";
    private String backMoney = "";
    private String backBjMoney = "";
    private String servicePrice = "";
    private String yqPrice = "";
    private String receiveAccount = "";
    private String receiveAcc = "";
    private String latePrice = "";

    @Override
    public int initLayout() {
        return R.layout.activity_back_money;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        orderId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        backMoney = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        servicePrice = getIntent().getStringExtra(IntentUtils.getInstance().STR);
        backBjMoney = getIntent().getStringExtra(IntentUtils.getInstance().CHECK_APPROVE);
        String accountValue = getIntent().getStringExtra(IntentUtils.getInstance().ISAGREE);
        if (StringUtils.isNoEmpty(accountValue)) {
            String[] split = accountValue.split(",");
            if (split.length >= 3) {
                receiveAccount = split[0];
                receiveAcc = split[1];
                latePrice = split[2];
            }
        }
        
        tvRight.setText("还款记录");
        tvTitle.setText("还款");
        getOtherFeed(orderId, backMoney);
        etBackMoney.setText(backMoney);

        tvReceiveAccount.setText(StringUtils.getNoNullStr(receiveAccount));
        tvReceiveAccountNum.setText(StringUtils.getNoNullStr(receiveAcc));

        new KeyboardChangeListener(this).setKeyBoardListener(new KeyboardChangeListener.KeyBoardListener() {
            @Override
            public void onKeyboardChange(boolean isShow, int keyboardHeight) {
                if (!isShow) {
                    String price = etBackMoney.getText().toString().trim();
                    if (!StringUtils.isNoEmpty(price)) {
                        return;
                    }
                    getOtherFeed(orderId, price);
                }
            }
        });

    }

    @OnClick({R.id.ll_back, R.id.tv_right, R.id.tv_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_right:
                IntentUtils.getInstance().toActivity(this, BtRecordBackActivity.class, orderId);
                break;
            case R.id.tv_commit:
                backMoney = etBackMoney.getText().toString().trim();
                if (!StringUtils.isNoEmpty(backMoney)) {
                    ToastUtils.getMineToast("请输入还款金额");
                    return;
                }
                toBackMoney(backMoney, orderId);
                break;
        }
    }

    //获取当前还款金额的其他费用
    public void getOtherFeed(String id, String nowPrice) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(this, UrlConstant.getInstance().URL
                + PostConstant.getInstance().COST_CALCULATION
                + FieldConstant.getInstance().ID + "=" + id + "&"
                + FieldConstant.getInstance().REPAY_PRINCIPAL + "=" + nowPrice, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                BackMoneyPriceBean priceBean = JSON.parseObject(json, BackMoneyPriceBean.class);
                tvServicePrice.setText(priceBean.getReObj().getServiceCharge());
                tvYqPrice.setText(priceBean.getReObj().getOverdueCost());
                tvBackBj.setText(priceBean.getReObj().getTotalMoney());
            }

            @Override
            public void onFail() {

            }
        });
    }


    //立即还款
    public void toBackMoney(String money, String id) {
        OkhttpUtil.getInstance().setUnCacheData(this, "还款中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().REPAYMENT
                + FieldConstant.getInstance().ID + "=" + id + "&"
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().MONEY + "=" + money, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("还款成功");
                } else {
                    ToastUtils.getMineToast(successBean.getErrMsg());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

}
