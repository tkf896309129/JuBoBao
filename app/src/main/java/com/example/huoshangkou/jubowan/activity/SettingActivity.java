package com.example.huoshangkou.jubowan.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.base.BaseApp;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;

import org.apache.log4j.chainsaw.Main;
import org.simple.eventbus.EventBus;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：SettingActivity
 * 描述：
 * 创建时间：2017-02-08  10:16
 */

public class SettingActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.push)
    Switch aSwitch;
    @Bind(R.id.push_night)
    Switch aSwitchNight;

    private PushAgent pushAgent;

    @Override
    public int initLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("个人设置");
        pushAgent = BaseApp.getPush();
        aSwitch.setChecked(pushAgent.isPushCheck());
        String nightTheme = (String) SharedPreferencesUtils.getInstance().get(this, SharedKeyConstant.getInstance().NIGHT_DEFAULT, "");
        if (StringUtils.isNoEmpty(nightTheme) && nightTheme.equals("night")) {
            aSwitchNight.setChecked(true);
        } else {
            aSwitchNight.setChecked(false);
        }
        String push = PersonConstant.getInstance().getIsPush();
        if (StringUtils.isNoEmpty(push)) {
            switch (push) {
                case "pushOn":
                    aSwitch.setChecked(true);
                    NotificationManagerCompat notification = NotificationManagerCompat.from(this);
                    boolean isEnabled = notification.areNotificationsEnabled();

                    if (!isEnabled) {
                        //未打开通知
                        AlertDialog alertDialog = new AlertDialog.Builder(this)
                                .setTitle("提示")
                                .setMessage("请在“通知”中打开通知权限")
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                })
                                .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        Intent intent = new Intent();
                                        if (Build.VERSION.SDK_INT >= 0) {
                                            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                                            intent.putExtra("android.provider.extra.APP_PACKAGE", SettingActivity.this.getPackageName());
                                        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {  //5.0
                                            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                                            intent.putExtra("app_package", SettingActivity.this.getPackageName());
                                            intent.putExtra("app_uid", SettingActivity.this.getApplicationInfo().uid);
                                            startActivity(intent);
                                        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {  //4.4
                                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            intent.addCategory(Intent.CATEGORY_DEFAULT);
                                            intent.setData(Uri.parse("package:" + SettingActivity.this.getPackageName()));
                                        } else if (Build.VERSION.SDK_INT >= 15) {
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                            intent.setData(Uri.fromParts("package", SettingActivity.this.getPackageName(), null));
                                        }
                                        startActivity(intent);

                                    }
                                })
                                .create();
                        alertDialog.show();
                        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                    }

                    break;
                case "pushOff":
                    aSwitch.setChecked(false);
                    break;
            }
        } else {
            aSwitch.setChecked(false);
        }


        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    BaseApp.getPush().addAlias(PersonConstant.getInstance().getUserId(), "UserID", new UTrack.ICallBack() {
                        @Override
                        public void onMessage(boolean b, String s) {
                            SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().PUSH, "pushOn");
                        }
                    });
                } else {
                    BaseApp.getPush().removeAlias(PersonConstant.getInstance().getUserId(), "UserID", new UTrack.ICallBack() {
                        @Override
                        public void onMessage(boolean b, String s) {
                            SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().PUSH, "pushOff");
                        }
                    });
                }
                pushAgent.setPushCheck(b);
            }
        });
        aSwitchNight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferencesUtils.getInstance().put(SettingActivity.this, SharedKeyConstant.getInstance().NIGHT_DEFAULT, "night");
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferencesUtils.getInstance().put(SettingActivity.this, SharedKeyConstant.getInstance().NIGHT_DEFAULT, "day");
                }
                SharedPreferencesUtils.getInstance().put(SettingActivity.this,SharedKeyConstant.getInstance().NIGHT_DEFAULT_CHANGE,"change");
                SharedPreferencesUtils.getInstance().put(SettingActivity.this,SharedKeyConstant.getInstance().MINE_NIGHT_DEFAULT_CHANGE,"change");
                EventBus.getDefault().post("changeTheme","change");
                changeTheme();
            }
        });
    }

    private void changeTheme() {
        finish();
        startActivity(new Intent(this, SettingActivity.class));
        overridePendingTransition(R.anim.start_anim, R.anim.out_anim);
    }


    //点击事件
    @OnClick({R.id.ll_back, R.id.rl_change_psw, R.id.rl_no_noise})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.rl_change_psw:
                IntentUtils.getInstance().toActivity(this, ChangePswActivity.class);
                break;
            case R.id.rl_no_noise:
                IntentUtils.getInstance().toActivity(this, CheckSettingActivity.class);
                break;
        }
    }
}
