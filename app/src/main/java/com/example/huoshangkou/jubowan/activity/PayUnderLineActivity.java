package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import org.simple.eventbus.EventBus;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：PayUnderLineActivity
 * 描述：
 * 创建时间：2017-05-16  09:18
 */

public class PayUnderLineActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_money)
    TextView tvMoney;
    @Bind(R.id.et_trade_num)
    EditText etTradeNum;
    @Bind(R.id.et_bank_name)
    EditText etBankName;

    private String bankName = "";
    private String tradeNum = "";
    private String orderId = "";
    private String price = "";


    @Override
    public int initLayout() {
        return R.layout.activity_pay_underline;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().orderList);
        tvTitle.setText("线下汇款");
        price = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        orderId = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        tvMoney.setText(price);

    }

    @OnClick({R.id.ll_back, R.id.tv_commit_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_commit_confirm:
                bankName = etBankName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(bankName)) {
                    ToastUtils.getMineToast("请输入汇款银行");
                    return;
                }

                tradeNum = etTradeNum.getText().toString();
                if (!StringUtils.isNoEmpty(tradeNum)) {
                    ToastUtils.getMineToast("请输入银行交易流水号");
                    return;
                }
                underLine();
                break;
        }
    }


    //线下付款
    public void underLine() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().UPDATE_PAY_TRADE_NO + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().ORDER_ID + "=" + orderId + "&"
                + FieldConstant.getInstance().PAY_TRADE_NO + "=" + tradeNum + "&"
                + FieldConstant.getInstance().BANK_NAME + "=" + EncodeUtils.getInstance().getEncodeString(bankName), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("付款成功，请等待审核");
                    EventBus.getDefault().post("initOrder", "initOrder");
                    ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().orderList);
                } else {
                    ToastUtils.getMineToast("付款失败");
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

}
