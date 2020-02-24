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
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：YjMoneyActivity
 * 描述：佣金提现
 * 创建时间：2017-05-26  13:35
 */

public class YjMoneyActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_alipay_account)
    EditText etAlipayAccount;
    @Bind(R.id.et_true_name)
    EditText etTrueName;
    @Bind(R.id.et_link_phone)
    EditText etLinkPhone;
    @Bind(R.id.et_score)
    TextView etScore;

    private String price = "";
    private String account = "";
    private String realName = "";
    private String tel = "";

    @Override
    public int initLayout() {
        return R.layout.activity_yj_tx;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("佣金提现");
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().forumList);

        price = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        etScore.setText(price + "元");
    }

    @OnClick({R.id.ll_back, R.id.tv_add_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_add_pay:
                account = etAlipayAccount.getText().toString().trim();
                if (!StringUtils.isNoEmpty(account)) {
                    ToastUtils.getMineToast("请输入支付宝账号");
                    return;
                }
                realName = etTrueName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(realName)) {
                    ToastUtils.getMineToast("请输入真实姓名");
                    return;
                }
                tel = etLinkPhone.getText().toString().trim();
                if (!StringUtils.isNoEmpty(tel)) {
                    ToastUtils.getMineToast("请输入手机号码");
                    return;
                }

                if (tel.length() != 11) {
                    ToastUtils.getMineToast("请输入正确的手机号");
                    return;
                }

                CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否提现", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        getYjMoney();
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });


                break;
        }
    }

    //佣金提现
    public void getYjMoney() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().PUT_TI_XIAN
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().BANK_NO + "=" + EncodeUtils.getInstance().getEncodeString(account) + "&"
                + FieldConstant.getInstance().REAL_NAME + "=" + EncodeUtils.getInstance().getEncodeString(realName) + "&"
                + FieldConstant.getInstance().TEL + "=" + tel, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("提现成功");
                    ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().forumList);
                }else{
                    ToastUtils.getMineToast(successBean.getErrMsg());
                }
            }

            @Override
            public void onFail() {
                ToastUtils.getMineToast("提现失败");
            }
        });
    }

}
