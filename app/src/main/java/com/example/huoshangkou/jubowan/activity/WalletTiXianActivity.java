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
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：WalletTiXianActivity
 * 描述：
 * 创建时间：2017-10-19  16:01
 */

public class WalletTiXianActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_alipay_account)
    EditText etAlipayAccount;
    @Bind(R.id.et_true_name)
    EditText etTrueName;
    @Bind(R.id.et_link_phone)
    EditText etLinkPhone;

    //支付宝账号
    private String aliPay = "";
    //真实姓名
    private String trueName = "";
    //联系电话
    private String linkPhone = "";

    @Override
    public int initLayout() {
        return R.layout.activity_wallet_tixian;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("余额提现");
    }

    @OnClick({R.id.ll_back, R.id.tv_add_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_add_pay:
                aliPay = etAlipayAccount.getText().toString().trim();
                if (!StringUtils.isNoEmpty(aliPay)) {
                    ToastUtils.getMineToast("请输入支付宝账号");
                    return;
                }
                trueName = etTrueName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(trueName)) {
                    ToastUtils.getMineToast("请输入真实姓名");
                    return;
                }
                linkPhone = etLinkPhone.getText().toString().trim();
                if (!StringUtils.isNoEmpty(linkPhone)) {
                    ToastUtils.getMineToast("请输入联系电话");
                    return;
                }

                putTiXian(aliPay, trueName, linkPhone);
                break;
        }
    }


    //余额提现
    public void putTiXian(String bankNo, String realName, String tel) {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().RED_TI_XIAN
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().BANK_NO + "=" + EncodeUtils.getInstance().getEncodeString(bankNo) + "&"
                + FieldConstant.getInstance().REAL_NAME + "=" + EncodeUtils.getInstance().getEncodeString(realName) + "&"
                + FieldConstant.getInstance().TEL + "=" + EncodeUtils.getInstance().getEncodeString(tel), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("提现成功");
                    WalletTiXianActivity.this.finish();
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
