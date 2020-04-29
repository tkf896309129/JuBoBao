package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.LoginFunction;
import com.example.huoshangkou.jubowan.activity.function.RegeditFunction;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.SharedValueConstant;
import com.example.huoshangkou.jubowan.inter.OnCommonSuccessCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.SendCodeUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：RegeditActivity
 * 描述：
 * 创建时间：2017-02-08  13:31
 */

public class RegeditActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_get_code)
    TextView tvGetCode;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.et_psw)
    EditText etPsw;
    @Bind(R.id.tv_country_id)
    TextView tvCountryId;

    //倒计时时间
    private int codeTime;
    //验证码
    private String code;
    //手机号
    private String phone;
    //密码
    private String psw;
    //设备号
    private String deviceToken;
    //获取验证码
    private String getCode = "";
    //国家区号
    private String countryCode = "86";

    @Override
    public int initLayout() {
        return R.layout.activity_regedit;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().activityLoginList);
        //设置标题
        tvTitle.setText("注册");
        //获取设备号
        deviceToken = (String) SharedPreferencesUtils.getInstance().get(this, SharedKeyConstant.getInstance().DEVICE_TOKEN, "");

        LogUtils.i(deviceToken);

        //获取倒计时时间  获取倒计时的时间开始
//        codeTime = (int) SharedPreferencesUtils.getInstance().get(this, SharedKeyConstant.getInstance().CODE_TIME, 0);
//        String type = (String) SharedPreferencesUtils.getInstance().get(this, SharedKeyConstant.getInstance().CODE_TYPE, "");
//        if (codeTime > 1 && type.equals(SharedValueConstant.getInstance().BIND_PHONE)) {
//            ToastUtils.getMineToast("倒计时");
//            SendCodeUtils.getInstance().sendCode("", tvGetCode, this, codeTime, false, SharedValueConstant.getInstance().BIND_PHONE);
//        }
    }

    @OnClick({R.id.tv_country_id, R.id.ll_back, R.id.tv_back_login, R.id.tv_forget_psw, R.id.tv_get_code, R.id.tv_regedit, R.id.tv_rule})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_country_id:
//                IntentUtils.getInstance().toActivity(this,InternationalePhoneActivity.class);
                Intent intent = new Intent(this, InternationalePhoneActivity.class);
                startActivityForResult(intent, 1);
                break;
            //返回操作
            case R.id.tv_back_login:
            case R.id.ll_back:
                this.finish();
                break;
            //忘记密码
            case R.id.tv_forget_psw:
                IntentUtils.getInstance().toActivity(this, InternationalePhoneActivity.class);
//                IntentUtils.getInstance().toActivity(this, FindPswActivity.class);
                break;
            //获取验证码
            case R.id.tv_get_code:
                phone = etPhone.getText().toString().trim();
                if (!StringUtils.isNoEmpty(phone)) {
                    ToastUtils.getMineToast("请输入手机号");
                    return;
                }
                codeTime = 60;
                SendCodeUtils.getInstance().sendCode(phone, tvGetCode, countryCode,this, codeTime, true,
                        SharedValueConstant.getInstance().BIND_PHONE, new StringCallBack() {
                            @Override
                            public void onSuccess(String str) {
                                getCode = str;
                            }

                            @Override
                            public void onFail() {

                            }
                        });
                break;
            //注册
            case R.id.tv_regedit:
                phone = etPhone.getText().toString().trim();
                if (!StringUtils.isNoEmpty(phone)) {
                    ToastUtils.getMineToast("请输入手机号");
                    return;
                }
                code = etCode.getText().toString().trim();
                if (!StringUtils.isNoEmpty(code)) {
                    ToastUtils.getMineToast("请输入验证码");
                    return;
                }
                psw = etPsw.getText().toString().trim();
                if (!StringUtils.isNoEmpty(psw)) {
                    ToastUtils.getMineToast("请输入密码");
                    return;
                }
                if (code.equals(getCode)) {
                    RegeditFunction.getInstance().setRegeditFun(phone, psw,countryCode, deviceToken, this, new OnCommonSuccessCallBack() {
                        @Override
                        public void onSuccess() {
                            register(phone);
                        }

                        @Override
                        public void onFail() {

                        }
                    });
                } else {
                    ToastUtils.getMineToast("验证码错误,请确认后重新输入");
                }
                break;
            //服务条款
            case R.id.tv_rule:
                ToastUtils.getMineToast("服务条款");
                break;
        }
    }


    //环信注册功能
    public void regeisterHx(final String account) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    EMClient.getInstance().createAccount(account, "123456");
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void register(final String account) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(account, "123456");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!RegeditActivity.this.isFinishing()) {
                                LoginFunction.getInstance().loginChat(getContext(), phone, new OnCommonSuccessCallBack() {
                                    @Override
                                    public void onSuccess() {
                                        //保存登录信息
                                    }

                                    @Override
                                    public void onFail() {
                                        //保存登录信息
                                    }
                                });

                            }
                        }
                    });
                } catch (final HyphenateException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!RegeditActivity.this.isFinishing()) {
                                //// 注册成功
                                ///
                                int errorCode = e.getErrorCode();
                                if (errorCode == EMError.NETWORK_ERROR) {

                                } else if (errorCode == EMError.USER_ALREADY_EXIST) {

                                } else if (errorCode == EMError.USER_AUTHENTICATION_FAILED) {

                                } else if (errorCode == EMError.USER_ILLEGAL_ARGUMENT) {

                                } else {
                                }
                            }
                        }
                    });
                }
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null){
            return;
        }
        switch (requestCode) {
            case 1:
                String code = data.getStringExtra(IntentUtils.getInstance().TYPE);
                countryCode = code;
                tvCountryId.setText("+"+countryCode+" ");

                break;
        }
    }
}
