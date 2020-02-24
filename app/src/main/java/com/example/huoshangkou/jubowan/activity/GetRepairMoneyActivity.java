package com.example.huoshangkou.jubowan.activity;

import android.os.Bundle;
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
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhoneFormatCheckUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：GetRepairMoneyActivity
 * 描述：积分提现
 * 创建时间：2017-05-02  11:29
 */

public class GetRepairMoneyActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_alipay_account)
    EditText etAlipayAccount;
    @Bind(R.id.et_true_name)
    EditText etTrueName;
    @Bind(R.id.et_link_phone)
    EditText etLinkPhone;
    @Bind(R.id.et_score)
    EditText etScore;

    @Bind(R.id.tv_current_score)
    TextView tvCurrentScore;

    private String currentScore;

    private String aliName;
    private String trueName;
    private String linkPhone;
    private String score;


    @Override
    public int initLayout() {
        return R.layout.activity_get_repair_money;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {

        tvTitle.setText("积分提现");
        currentScore = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        tvCurrentScore.setText("当前积分：" + currentScore);
    }

    @OnClick({R.id.ll_back, R.id.tv_add_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_add_pay:

                aliName = etAlipayAccount.getText().toString().trim();
                trueName = etTrueName.getText().toString().trim();
                linkPhone = etLinkPhone.getText().toString().trim();
                score = etScore.getText().toString().trim();

                if (!StringUtils.isNoEmpty(aliName)) {
                    ToastUtils.getMineToast("请输入支付宝账号");
                    return;
                }

                if (!StringUtils.isNoEmpty(trueName)) {
                    ToastUtils.getMineToast("请输入姓名");
                    return;
                }

                if (!StringUtils.isNoEmpty(linkPhone)) {
                    ToastUtils.getMineToast("请输入联系电话");
                    return;
                }

                if (!PhoneFormatCheckUtils.getInstance().isPhoneLegal(linkPhone)) {
                    ToastUtils.getMineToast("请输入正确的手机号");
                    return;
                }

                if (!StringUtils.isNoEmpty(score)) {
                    ToastUtils.getMineToast("请输入提现分数");
                    return;
                }

                int num = Integer.parseInt(score);

                if (num < 100) {
                    CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "提现积分不得低于100积分", new CopyIosDialogUtils.ErrDialogCallBack() {
                        @Override
                        public void confirm() {

                        }
                    });

                    return;
                }

                int count = num % 100;
                if (count != 0) {
                    CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "只能输入100的倍数", new CopyIosDialogUtils.ErrDialogCallBack() {
                        @Override
                        public void confirm() {

                        }
                    });
                    return;
                }

                if (!StringUtils.isNoEmpty(currentScore)) {
                    ToastUtils.getMineToast("获取当前积分失败");
                    return;
                }

                if (StringUtils.isNoEmpty(currentScore) && Integer.parseInt(currentScore) < Integer.parseInt(score)) {
                    CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "余额不足，无法提现", new CopyIosDialogUtils.ErrDialogCallBack() {
                        @Override
                        public void confirm() {

                        }
                    });
                    return;
                }

                String roleType = PersonConstant.getInstance().getRoleType(this);
                if (StringUtils.isNoEmpty(roleType) && roleType.equals("2") && StringUtils.isNoEmpty(currentScore) && (Integer.parseInt(currentScore) - 1000) < Integer.parseInt(score)) {
                    CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "由于您为加工厂，系统送的1000积分不参与提现，余额不足", new CopyIosDialogUtils.ErrDialogCallBack() {
                        @Override
                        public void confirm() {

                        }
                    });
                    return;
                }

                CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "提现金额" + num / 10 + "元", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        getTrueMoney(aliName, trueName, linkPhone, score);
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });


                break;
        }
    }


    //积分提现
    public void getTrueMoney(String aliName, String trueName, String tel, String score) {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                        + PostConstant.getInstance().CASH_POST_SCORE + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                        + FieldConstant.getInstance().ALIPAY + "=" + EncodeUtils.getInstance().getEncodeString(aliName) + "&"
                        + FieldConstant.getInstance().ALIPAY_NAME + "=" + EncodeUtils.getInstance().getEncodeString(trueName) + "&"
                        + FieldConstant.getInstance().TEL + "=" + tel + "&"
                        + FieldConstant.getInstance().SCORE + "=" + score, new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                        if (successBean.getSuccess() == 1) {
                            ToastUtils.getMineToast("提交成功，请等待后台审核");
                            GetRepairMoneyActivity.this.finish();
                        } else {
                            ToastUtils.getMineToast("提交失败");
                        }
                    }

                    @Override
                    public void onFail() {

                    }
                }
        );
    }


}
