package com.example.huoshangkou.jubowan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.WalletListBean;
import com.example.huoshangkou.jubowan.utils.IntentUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：WalletDetailActivity
 * 描述：提现姓名 支付宝账号 手机号
 * 创建时间：2017-10-23  10:47
 */

public class WalletDetailActivity extends BaseActivity {
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_alipay)
    TextView tvAlipay;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    WalletListBean walletListBean;

    @Override
    public int initLayout() {
        return R.layout.activity_wallet_detail;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        walletListBean = (WalletListBean) getIntent().getSerializableExtra(IntentUtils.getInstance().BEAN_TYPE);

        tvName.setText(walletListBean.getRealName());
        tvPhone.setText(walletListBean.getTel());
        tvAlipay.setText(walletListBean.getBankNo());

        tvTitle.setText("账单详情");

    }


    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }
}
