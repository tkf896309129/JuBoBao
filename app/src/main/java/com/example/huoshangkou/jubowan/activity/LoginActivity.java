package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.LoginFunction;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.LoginMessageObjBean;
import com.example.huoshangkou.jubowan.chat.helper.UpdateMesssageHelper;
import com.example.huoshangkou.jubowan.chat.signature.GenerateChatUserSig;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.SharedValueConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnCommonSuccessCallBack;
import com.example.huoshangkou.jubowan.inter.OnLoginSuccessBack;
import com.example.huoshangkou.jubowan.inter.OnThreeLoginBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.MD5Utils;
import com.example.huoshangkou.jubowan.utils.MineLoadingDialogUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：LoginActivity
 * 描述：
 * 创建时间：2017-02-08  13:31
 */

public class LoginActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_psw)
    EditText etPsw;
    @Bind(R.id.iv_delete)
    ImageView imgDelete;
    @Bind(R.id.tv_country_id)
    TextView tvCountryId;
    //账号
    private String phone;
    //密码
    private String psw;

    private UMShareAPI umShareAPI;
    //第三方登录方式
    private int loginType;
    String open_id;
    String nick_name;
    String icon_url;

    private AlertDialog alertDialog;
    //国家区号
    private String countryCode;

    @Override
    public int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().activityLoginList);
        tvTitle.setText("登录");

        //获取当前手机号
        String phone = (String) SharedPreferencesUtils.getInstance().get(getContext(), SharedKeyConstant.getInstance().CURRENT_PHONE, "");
        if (StringUtils.isNoEmpty(phone)) {
            imgDelete.setVisibility(View.VISIBLE);
        }
        etPhone.setText(phone);
        umShareAPI = UMShareAPI.get(this);
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String str = charSequence.toString();
                if (StringUtils.isNoEmpty(str)) {
                    imgDelete.setVisibility(View.VISIBLE);
                } else {
                    imgDelete.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    //点击事件
    @OnClick({R.id.tv_login, R.id.tv_regedit, R.id.tv_qq, R.id.tv_wechat,
            R.id.tv_weibo, R.id.tv_forget_psw, R.id.tv_rule, R.id.iv_delete, R.id.tv_country_id})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_country_id:
                Intent intent = new Intent(this, InternationalePhoneActivity.class);
                startActivityForResult(intent, 1);
                break;
            //登录功能
            case R.id.tv_login:
                phone = etPhone.getText().toString().trim();
                if (!StringUtils.isNoEmpty(phone)) {
                    ToastUtils.getMineToast("请输入手机号");
                    return;
                }
                psw = etPsw.getText().toString().trim();
                if (!StringUtils.isNoEmpty(psw)) {
                    ToastUtils.getMineToast("请输入密码");
                    return;
                }

                byte[] pswMd5 = MD5Utils.getInstance().md5Byte(psw);
                String base64String = EncodeUtils.getInstance().getBase64String(pswMd5);
                LogUtils.e(base64String);

                //登录操作
                LoginFunction.getInstance().setLogin(getContext(), phone, base64String, new OnLoginSuccessBack() {
                    //登录成功回调
                    @Override
                    public void onLoginSuccess(String id, String name, String pic) {
                        loginChat(id, name, pic);
                        //保存当前登录手机号
                        SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().CURRENT_PHONE, phone);
                        SharedPreferencesUtils.getInstance().put(LoginActivity.this, SharedKeyConstant.getInstance().LOGIN_STATE, SharedValueConstant.getInstance().LOGIN_SUCCESS);
                        IntentUtils.getInstance().toActivity(LoginActivity.this, MainActivity.class);

                    }

                    //登录失败回调
                    @Override
                    public void onLoginFail() {
                        SharedPreferencesUtils.getInstance().put(LoginActivity.this, SharedKeyConstant.getInstance().LOGIN_STATE, SharedValueConstant.getInstance().LOGIN_FAIL);
                    }
                });
                break;
            //注册功能
            case R.id.tv_regedit:
                IntentUtils.getInstance().toActivity(this, RegeditActivity.class);
                break;
            //QQ登录
            case R.id.tv_qq:
                loginType = LoginFunction.getInstance().QQ;
                umShareAPI.getPlatformInfo(this, SHARE_MEDIA.QQ, umAuthListener);
                break;
            //微信登录
            case R.id.tv_wechat:
                loginType = LoginFunction.getInstance().WE_CHAT;
                umShareAPI.getPlatformInfo(this, SHARE_MEDIA.WEIXIN, umAuthListener);
                break;
            //微博登录
            case R.id.tv_weibo:
                loginType = LoginFunction.getInstance().WEI_BO;
                umShareAPI.getPlatformInfo(this, SHARE_MEDIA.SINA, umAuthListener);
                break;
            //找回密码
            case R.id.tv_forget_psw:
                IntentUtils.getInstance().toActivity(this, FindPswActivity.class);
                break;
            //服务条款
            case R.id.tv_rule:
                IntentUtils.getInstance().toWebActivity(getContext(), UrlConstant.getInstance().SERVICE_URL, "服务条款");
                break;
            case R.id.iv_delete:
                etPhone.setText("");
                break;
        }
    }

    UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            alertDialog = MineLoadingDialogUtils.updateDialog(getContext(), "正在登录");
        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            open_id = map.get("openid");
            nick_name = map.get("screen_name");
            icon_url = map.get("iconurl");
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            //第三方登录
            LoginFunction.getInstance().threeLogin(LoginActivity.this, loginType, open_id, "", icon_url, nick_name, new OnThreeLoginBack() {
                @Override
                public void threeLoginSuccess(final LoginMessageObjBean loginMessageObjBean) {
                    //保存登录信息
                    LoginFunction.getInstance().saveLoginMessage(loginMessageObjBean, LoginActivity.this);
                    loginChat(loginMessageObjBean.getID()+"",loginMessageObjBean.getRealName(),loginMessageObjBean.getHeadPic());
                    SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().CURRENT_PHONE, loginMessageObjBean.getTel());
                    LoginFunction.getInstance().loginChat(getContext(), loginMessageObjBean.getTel(), new OnCommonSuccessCallBack() {
                        @Override
                        public void onSuccess() {
                            SharedPreferencesUtils.getInstance().put(LoginActivity.this, SharedKeyConstant.getInstance().LOGIN_STATE, SharedValueConstant.getInstance().LOGIN_SUCCESS);
                            IntentUtils.getInstance().toActivity(LoginActivity.this, MainActivity.class);
                        }

                        @Override
                        public void onFail() {
                            registerThree(loginMessageObjBean.getTel());
                            SharedPreferencesUtils.getInstance().put(LoginActivity.this, SharedKeyConstant.getInstance().LOGIN_STATE, SharedValueConstant.getInstance().LOGIN_SUCCESS);
                            IntentUtils.getInstance().toActivity(LoginActivity.this, MainActivity.class);
                        }
                    });
                }

                @Override
                public void threeLoginFail(String errMsg) {
                    ToastUtils.getMineToast(errMsg);
                    //跳转到手机号绑定界面
                    IntentUtils.getInstance().toBindPhoneActivity(LoginActivity.this, nick_name, icon_url, loginType, open_id);
                }
            });
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            ToastUtils.getMineToast("授权失败");
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            ToastUtils.getMineToast("授权取消");
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
        }
    };

    //如果使用的是qq或者新浪精简版jar，需要在您使用分享或授权的Activity（fragment不行）中添加如下回调代码
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
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

    //即时通讯登录
    public void loginChat(String id, final String nickName, final String headPic) {
        String userSig = GenerateChatUserSig.genTestUserSig(id);
        TUIKit.login(id, userSig, new IUIKitCallBack() {
            @Override
            public void onError(String module, final int code, final String desc) {

            }

            @Override
            public void onSuccess(Object data) {
                UpdateMesssageHelper.getInstance().updateProfile(headPic, nickName);
            }
        });
    }

    public void registerThree(final String account) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(account, "123456");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                } catch (final HyphenateException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!LoginActivity.this.isFinishing()) {
                                //// 注册成功
                                ///
                                int errorCode = e.getErrorCode();
                                if (errorCode == EMError.NETWORK_ERROR) {

                                } else if (errorCode == EMError.USER_ALREADY_EXIST) {
                                    login(account);
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

    //登录
    public void login(String account) {
        LoginFunction.getInstance().loginChat(getContext(), account, new OnCommonSuccessCallBack() {
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
