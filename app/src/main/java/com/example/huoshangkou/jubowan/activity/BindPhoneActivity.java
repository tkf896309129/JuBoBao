package com.example.huoshangkou.jubowan.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.LoginFunction;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.LoginMessageObjBean;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.SharedValueConstant;
import com.example.huoshangkou.jubowan.inter.OnThreeLoginBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.MineLoadingDialogUtils;
import com.example.huoshangkou.jubowan.utils.SendCodeUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：BindPhoneActivity
 * 描述：绑定手机号
 * 创建时间：2017-02-24  14:20
 */

public class BindPhoneActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.tv_get_code)
    TextView tvGetCode;
    @Bind(R.id.tv_country_id)
    TextView tvCountryId;

    private int codeTime;
    //验证码
    private String code;
    //手机号
    private String phone;
    //国家区号
    private String countryCode = "86";
    //第三方登录方式
    private int loginType;
    private String getCode = "";

    String open_id;
    String nick_name;
    String icon_url;

    private AlertDialog progressDialog;

    @Override
    public int initLayout() {
        return R.layout.activity_bind_phone;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().activityLoginList);
        tvTitle.setText("绑定手机号");


        Intent intent = getIntent();
        loginType = intent.getIntExtra("login_type", 999);
        open_id = intent.getStringExtra("open_id");
        nick_name = intent.getStringExtra("nick_name");
        icon_url = intent.getStringExtra("pic");


        //获取倒计时时间
//        codeTime = (int) SharedPreferencesUtils.getInstance().get(this, SharedKeyConstant.getInstance().CODE_TIME, 0);
//        String type = (String) SharedPreferencesUtils.getInstance().get(this, SharedKeyConstant.getInstance().CODE_TYPE, "");
//        if (codeTime > 1 && type.equals(SharedValueConstant.getInstance().REGISTER)) {
//            ToastUtils.getMineToast("倒计时");
//            SendCodeUtils.getInstance().sendCode("", tvGetCode, this, codeTime, false, SharedValueConstant.getInstance().REGISTER);
//        }
    }


    //点击事件
    @OnClick({R.id.tv_country_id, R.id.tv_get_code, R.id.tv_bind_phone, R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_country_id:
//                IntentUtils.getInstance().toActivity(this,InternationalePhoneActivity.class);
                Intent intent = new Intent(this, InternationalePhoneActivity.class);
                startActivityForResult(intent, 1);
                break;
            //获取验证码
            case R.id.tv_get_code:
                phone = etPhone.getText().toString().trim();
                if (!StringUtils.isNoEmpty(phone)) {
                    ToastUtils.getMineToast("请输入手机号");
                    return;
                }

                codeTime = 60;
                SendCodeUtils.getInstance().sendCode(phone, tvGetCode, countryCode, this, codeTime, true,
                        SharedValueConstant.getInstance().REGISTER, new StringCallBack() {
                            @Override
                            public void onSuccess(String str) {
                                getCode = str;
                            }

                            @Override
                            public void onFail() {

                            }
                        });

                break;
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_bind_phone:
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

                if (!code.equals(getCode)) {
                    ToastUtils.getMineToast("请输入正确的验证码");
                    return;
                }

                LoginFunction.getInstance().threeLogin(this, loginType, open_id, phone, icon_url, nick_name, new OnThreeLoginBack() {
                    @Override
                    public void threeLoginSuccess(LoginMessageObjBean loginMessageObjBean) {
                        //保存登录信息
                        LoginFunction.getInstance().saveLoginMessage(loginMessageObjBean, BindPhoneActivity.this);
                        SharedPreferencesUtils.getInstance().put(BindPhoneActivity.this, SharedKeyConstant.getInstance().LOGIN_STATE, SharedValueConstant.getInstance().LOGIN_SUCCESS);
                        IntentUtils.getInstance().toActivity(BindPhoneActivity.this, MainActivity.class);
                    }

                    @Override
                    public void threeLoginFail(String errMsg) {
                        Toast.makeText(BindPhoneActivity.this, errMsg, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case 1:
                String code = data.getStringExtra(IntentUtils.getInstance().TYPE);
                countryCode = code;
                tvCountryId.setText("+" + countryCode + " ");

                break;
        }
    }
}
