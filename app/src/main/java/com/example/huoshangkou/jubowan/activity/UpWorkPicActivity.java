package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.constant.SharedValueConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.SendCodeUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：UpWorkPicActivity
 * 描述：
 * 创建时间：2018-08-21  09:36
 */

public class UpWorkPicActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.tv_up_pic)
    TextView tvUpPic;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.tv_get_code)
    TextView tvGetCode;
    @Bind(R.id.et_get_code)
    EditText etGetCode;
    @Bind(R.id.et_email)
    EditText etEmail;
    @Bind(R.id.tv_country_id)
    TextView tvCountryId;

    private int codeTime = 0;
    private String getCode = "";
    private String code = "";
    private String pic = "";
    private String phone = "";
    private String email = "";
    //国家区号
    private String countryCode = "86";

    @Override
    public int initLayout() {
        return R.layout.activity_up_pic;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().btList.add(this);
        tvTitle.setText("执照上传");
    }

    @OnClick({R.id.ll_back, R.id.tv_commit, R.id.rl_up_yyzz, R.id.tv_get_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_commit:
                code = etCode.getText().toString().trim();
                if (!StringUtils.isNoEmpty(code)) {
                    ToastUtils.getMineToast("请输入企业统一识别码");
                    return;
                }
                if (!StringUtils.isNoEmpty(pic)) {
                    ToastUtils.getMineToast("请上传营业执照");
                    return;
                }
                phone = etPhone.getText().toString().trim();
                if (!StringUtils.isNoEmpty(phone)) {
                    ToastUtils.getMineToast("请输入手机号");
                    return;
                }
                String codes = etGetCode.getText().toString().trim();
                if (!StringUtils.isNoEmpty(codes)) {
                    ToastUtils.getMineToast("请输入验证码");
                    return;
                }
                if (!StringUtils.isNoEmpty(getCode)) {
                    ToastUtils.getMineToast("请获取验证码");
                    return;
                }
                if (!getCode.equals(codes)) {
                    ToastUtils.getMineToast("请输入正确的验证码");
                    return;
                }
                email = etEmail.getText().toString().trim();
                if (!StringUtils.isNoEmpty(email)) {
                    ToastUtils.getMineToast("请输入邮箱");
                    return;
                }
//                IntentUtils.getInstance().toActivity(this, BindBlankActivity.class, code, pic, phone);
                IntentUtils.getInstance().toActivity(this, BindBlankActivity.class, code, pic, phone, email);
                break;
            case R.id.rl_up_yyzz:
                PhotoUtils.getInstance().updateSingleDialog(this, new PhotoCallBack() {
                    @Override
                    public void getPhoto(String path) {
                        pic = path;
                        tvUpPic.setText("营业执照已上传");
                    }
                });
                break;
            case R.id.tv_get_code:
                phone = etPhone.getText().toString().trim();
                if (!StringUtils.isNoEmpty(phone)) {
                    ToastUtils.getMineToast("请输入手机号");
                    return;
                }
                if (phone.length() != 11) {
                    ToastUtils.getMineToast("请输入正确的手机号");
                    return;
                }
                codeTime = 60;
                SendCodeUtils.getInstance().sendCode(phone, tvGetCode, countryCode, this, codeTime, true,
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
