package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.FindPswFunction;
import com.example.huoshangkou.jubowan.activity.function.RegeditFunction;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.SharedValueConstant;
import com.example.huoshangkou.jubowan.inter.OnFindPswBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
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
 * 类名：FindPswActivity
 * 描述：
 * 创建时间：2017-02-09  09:14
 */

public class FindPswActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_get_code)
    TextView tvGetCode;
    @Bind(R.id.tv_country_id)
    TextView tvCountryId;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.et_psw)
    EditText etPsw;

    private int codeTime;
    //验证码
    private String code;
    //手机号
    private String phone;
    //密码
    private String psw;
    //国家区号
    private String countryCode = "86";
    private String getCode = "";


    @Override
    public int initLayout() {
        return R.layout.activity_find_psw;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().activityLoginList);
        //设置标题
        tvTitle.setText("密码找回");


        //获取倒计时时间
//        codeTime = (int) SharedPreferencesUtils.getInstance().get(this, SharedKeyConstant.getInstance().CODE_TIME, 0);
//        String type = (String) SharedPreferencesUtils.getInstance().get(this, SharedKeyConstant.getInstance().CODE_TYPE, "");
//        if (codeTime > 1 && type.equals(SharedValueConstant.getInstance().FIND_PSW)) {
//            SendCodeUtils.getInstance().sendCode("", tvGetCode, this, codeTime, false, SharedValueConstant.getInstance().REGISTER);
//        }

    }


    //点击事件
    @OnClick({R.id.tv_country_id, R.id.tv_back_login, R.id.tv_regedit, R.id.ll_back, R.id.tv_get_code, R.id.tv_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_country_id:
//                IntentUtils.getInstance().toActivity(this,InternationalePhoneActivity.class);
                Intent intent = new Intent(this, InternationalePhoneActivity.class);
                startActivityForResult(intent, 1);
                break;
            //返回操作
            case R.id.ll_back:
                //返回登录
            case R.id.tv_back_login:
                this.finish();
                break;
            //注册账号
            case R.id.tv_regedit:
                IntentUtils.getInstance().toActivity(this, RegeditActivity.class);
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
                        SharedValueConstant.getInstance().FIND_PSW, new StringCallBack() {
                            @Override
                            public void onSuccess(String str) {
                                getCode = str;
                            }

                            @Override
                            public void onFail() {

                            }
                        });
                break;
            //确定
            case R.id.tv_confirm:

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
                    FindPswFunction.getInstance().getBackPsw(FindPswActivity.this, phone, psw, new OnFindPswBack() {
                        @Override
                        public void onFindPswCallBack() {
                            FindPswActivity.this.finish();
                        }
                    });
                } else {
                    ToastUtils.getMineToast("验证码错误,请确认后重新输入");
                }
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
