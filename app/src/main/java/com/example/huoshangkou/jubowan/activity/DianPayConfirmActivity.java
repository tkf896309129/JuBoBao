package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.SuccessDBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SendCodeUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：DianPayConfirmActivity
 * 描述：
 * 创建时间：2019-11-12  09:34
 */

public class DianPayConfirmActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.cb_confirm)
    CheckBox cbConfirm;
    @Bind(R.id.tv_send_code)
    TextView tvSendCode;
    @Bind(R.id.et_code)
    EditText etCode;

    private String phone = "";
    private String orderId = "";
    private String code = "";
    private int codeTime = 0;

    @Override
    public int initLayout() {
        return R.layout.activity_dian_pay;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().padPayList);
        phone = PersonConstant.getInstance().getPhone(this);
        orderId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        tvTitle.setText("垫付款支付确认");
    }

    @OnClick({R.id.ll_back, R.id.tv_pay, R.id.tv_send_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_pay:
                code = etCode.getText().toString().trim();
                if (!StringUtils.isNoEmpty(code)) {
                    ToastUtils.getMineToast("请输入验证码");
                    return;
                }
                if (!cbConfirm.isChecked()) {
                    ToastUtils.getMineToast("请阅读并同意提示信息");
                    return;
                }
                paySign(code);
                break;
            case R.id.tv_send_code:
                if (!StringUtils.isNoEmpty(phone)) {
                    ToastUtils.getMineToast("验证码发送失败");
                    return;
                }
                codeTime = 60;
                SendCodeUtils.getInstance().dianFuCode(this, tvSendCode);
                break;
        }
    }

    //支付签章
    public void paySign(String code) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", PersonConstant.getInstance().getUserId());
        map.put("orderId", orderId);
        map.put("mobileCode", code);
        String json = JSON.toJSONString(map);
        OkhttpUtil.getInstance().basePostCallDialog(this, json, UrlConstant.getInstance().PADPAY_MANAGE + "PadPaymentPay", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                SuccessDBean dBean = JSON.parseObject(str, SuccessDBean.class);
                if (dBean.getD().getState() == 1) {
                    ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().padPayList);
                }
                ToastUtils.getMineToast(dBean.getD().getMessage());
            }

            @Override
            public void onFail() {

            }
        });
    }
}
