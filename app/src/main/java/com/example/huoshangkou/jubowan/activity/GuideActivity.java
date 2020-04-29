package com.example.huoshangkou.jubowan.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.base.HideBaseActivity;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.SuccessCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：GuideActivity
 * 描述：引导页
 * 创建时间：2017-02-24  14:59
 */

public class GuideActivity extends BaseActivity {
    @Bind(R.id.img_guide)
    ImageView imgGuide;
    private String json = "";
    private String account = "";
    private String psw = "";
    private String loginState = "";
    private String purchaseType = "";
    private String timeSpan = DateUtils.getTime();

    @Override
    public int initLayout() {
        return R.layout.activity_guide;
    }

    @Override
    protected boolean setIsFull() {
        return true;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().activityLoginList);
        loginState = (String) SharedPreferencesUtils.getInstance().get(this, SharedKeyConstant.getInstance().LOGIN_STATE, "");

        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, 0);
        translateAnimation.setDuration(2500);
        imgGuide.setAnimation(translateAnimation);
        //聚易联采购
        Intent intent = getIntent();
        json = intent.getStringExtra("args");
        account = intent.getStringExtra("UserAccount");
        psw = intent.getStringExtra("UserPassword");
        purchaseType = intent.getStringExtra("PurchaseType");
        LogUtils.e("GuideActivity：" + json + "  " + account + "   " + psw + "   " + purchaseType);

        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                String isFisrt = (String) SharedPreferencesUtils.getInstance().get(getContext(), SharedKeyConstant.getInstance().FIRST_LOGIN, "");
                //第一次登录
                if (!StringUtils.isNoEmpty(isFisrt)) {
                    Intent intent1 = new Intent(GuideActivity.this, SplashActivity.class);
                    intent1.putExtra("args", json);
                    intent1.putExtra("UserAccount", account);
                    intent1.putExtra("UserPassword", psw);
                    intent1.putExtra("PurchaseType", purchaseType);
                    startActivity(intent1);
                } else {
                    Intent intent1 = new Intent(GuideActivity.this, MainActivity.class);
                    intent1.putExtra("args", json);
                    intent1.putExtra("UserAccount", account);
                    intent1.putExtra("UserPassword", psw);
                    intent1.putExtra("PurchaseType", purchaseType);
                    startActivity(intent1);
//                    if (StringUtils.isNoEmpty(loginState) && loginState.equals(SharedValueConstant.getInstance().LOGIN_SUCCESS)) {
//                        IntentUtils.getInstance().toActivity(GuideActivity.this, MainActivity.class);
//                    } else {
//                        IntentUtils.getInstance().toActivity(GuideActivity.this, LoginActivity.class);
//                    }
                }
                GuideActivity.this.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        this.finish();
    }

}
