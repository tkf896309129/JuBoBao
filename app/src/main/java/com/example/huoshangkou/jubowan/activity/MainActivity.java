package com.example.huoshangkou.jubowan.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.FragmentChangeAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.base.BaseApp;
import com.example.huoshangkou.jubowan.chat.ChatActivity;
import com.example.huoshangkou.jubowan.chat.helper.ChatHelper;
import com.example.huoshangkou.jubowan.chat.helper.HUAWEIHepler;
import com.example.huoshangkou.jubowan.chat.helper.MeiZuHelper;
import com.example.huoshangkou.jubowan.chat.helper.NotifyDialogHelper;
import com.example.huoshangkou.jubowan.chat.mine.MineMessageListActivity;
import com.example.huoshangkou.jubowan.chat.signature.GenerateChatUserSig;
import com.example.huoshangkou.jubowan.chat.thirdpush.MIUIHelper;
import com.example.huoshangkou.jubowan.chat.thirdpush.ThirdPushTokenMgr;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.fragment.ForumFragment;
import com.example.huoshangkou.jubowan.fragment.HomeFragment;
import com.example.huoshangkou.jubowan.fragment.MineFragment;
import com.example.huoshangkou.jubowan.fragment.OrderFragment;
import com.example.huoshangkou.jubowan.inter.SuccessCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.LoginUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToApproveUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.utils.UpdateDialogUtils;
import com.example.huoshangkou.jubowan.utils.VersionUtils;
import com.example.huoshangkou.jubowan.view.CustomViewPager;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.utils.IMFunc;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationManagerKit;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;
import com.umeng.message.UTrack;
import com.vivo.push.IPushActionListener;
import com.vivo.push.PushClient;
import com.vivo.push.PushManager;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import agent.HMSAgent;
import agent.common.handler.ConnectHandler;
import agent.push.handler.GetTokenHandler;
import butterknife.Bind;

import static com.just.agentweb.ActionActivity.REQUEST_CODE;
import static com.umeng.socialize.utils.ContextUtil.getPackageName;

public class MainActivity extends BaseActivity implements TIMMessageListener {
    //Fragment集合
    List<Fragment> fragmentList;

    @Bind(R.id.navigation_header_container)
    BottomNavigationView bottomNavigationView;
    @Bind(R.id.vp_main)
    CustomViewPager viewPager;
    TextView tvDot;
    FragmentManager fragmentManager;
    FragmentChangeAdapter fragmentChangeAdapter;

    private String position = "";
    private String type = "";
    private int chooose = 0;
    private String json;
    private String account;
    private String psw;
    private String purchaseType;

    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean setIsFull() {
        return true;
    }

    @Override
    public void initData() {
        BaseApp.getPush().addAlias(PersonConstant.getInstance().getUserId(), "UserID", new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean b, String s) {
                if (b) {
                    LogUtils.i("成功" + s);
                    SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().PUSH, "pushOn");
                } else {
                    LogUtils.i("失败" + s);
                }
            }
        });
        //设置消息监听器，收到新消息时，通过此监听器回调
        TIMManager.getInstance().addMessageListener(this);
        //判断聊天是否登录
        ChatHelper.getInstance().loginChatYON(this);
        SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().FIRST_LOGIN, "no");
        //聚易联采购
        Intent intent = getIntent();
        json = intent.getStringExtra("args");
        account = intent.getStringExtra("UserAccount");
        psw = intent.getStringExtra("UserPassword");
        purchaseType = intent.getStringExtra("PurchaseType");

        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().activityLoginList);
        EventBus.getDefault().register(this);
        fragmentManager = getSupportFragmentManager();
        fragmentList = new ArrayList<>();
        HomeFragment homeFragment = HomeFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString("args", json);
        bundle.putString("UserAccount", account);
        bundle.putString("UserPassword", psw);
        bundle.putString("PurchaseType", purchaseType);
        homeFragment.setArguments(bundle);
        fragmentList.add(homeFragment);
        fragmentList.add(ForumFragment.newInstance());
        fragmentList.add(OrderFragment.newInstance());
        fragmentList.add(MineFragment.newInstance());
        fragmentChangeAdapter = new FragmentChangeAdapter(fragmentManager, fragmentList);
        viewPager.setAdapter(fragmentChangeAdapter);
        viewPager.setOffscreenPageLimit(4);
        disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_home:
                        viewPager.setCurrentItem(0, false);
                        chooose = 0;
                        break;
                    case R.id.item_luntan:
                        chooose = 1;
                        viewPager.setCurrentItem(1, false);
                        break;
                    //暂时隐藏
//                    case R.id.item_wx:
//                        chooose = 2;
//                        viewPager.setCurrentItem(2, false);
//                        break;
                    case R.id.item_order:
                        if (!LoginUtils.getInstance().isLogin(getContext())) {
                            IntentUtils.getInstance().toActivity(getContext(), LoginActivity.class);
                        } else {
                            chooose = 2;
                            viewPager.setCurrentItem(2, false);
                        }
                        break;
                    case R.id.item_mine:
                        if (!LoginUtils.getInstance().isLogin(getContext())) {
                            IntentUtils.getInstance().toActivity(getContext(), LoginActivity.class);
                        } else {
                            chooose = 3;
                            viewPager.setCurrentItem(3, false);
                        }
                        break;
                }
                setCheck(chooose);
                return true;
            }
        });

        //获取版本最新的信息
        VersionUtils.getInstance().getVersionFromWeb(this);
        //从通知点击过来进行跳转
        position = (String) SharedPreferencesUtils.getInstance().get(getContext(), SharedKeyConstant.getInstance().NOTIFY_CLICK, "");
        if (StringUtils.isNoEmpty(position)) {
            EventBus.getDefault().post("OrderFragment", "OrderFragment");
            SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().NOTIFY_CLICK, "");
        }

        //注册一个监听连接状态的listener
//        EMClient.getInstance().addConnectionListener(new MyConnectionListener());+
        //登录成功
        //每次登录进来的时候需要判断是否认证
        String str = (String) SharedPreferencesUtils.getInstance().get(getContext(), SharedKeyConstant.getInstance().IS_LOGIN_TO_MAIN, "");
        String state = (String) SharedPreferencesUtils.getInstance().get(getContext(), SharedKeyConstant.getInstance().USER_STATE, "");
        if (StringUtils.isNoEmpty(str) && state.equals("未认证")) {
            ToApproveUtils.updataDialogShow(getContext());
            //登录成功
            SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().IS_LOGIN_TO_MAIN, "");
        }
//        File file = new File(Environment.getExternalStorageDirectory() + "/tang");
//        if (!file.exists() && !file.mkdir()) {
//            return;
//        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.SYSTEM_ALERT_WINDOW) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.REQUEST_INSTALL_PACKAGES) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.REQUEST_INSTALL_PACKAGES}, 1);
        } else {
            //获取设备唯一标识
            TelephonyManager mTelephony = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
            String id = "";
            if (mTelephony.getDeviceId() != null) {
                id = mTelephony.getDeviceId();
            } else {
                id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            }
            String deviceId = PersonConstant.getInstance().getPhoneTypeId();
            if (!StringUtils.isNoEmpty(deviceId)) {
                SharedPreferencesUtils.getInstance().put(this, SharedKeyConstant.getInstance().ONLY_ONE_DEVICE, id);
            }
        }

        requestSettingCanDrawOverlays();
        prepareThirdPushToken();

        //获取整个的NavigationView
//        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
//        //这里就是获取所添加的每一个Tab(或者叫menu)，
//        View tab = menuView.getChildAt(3);
//        BottomNavigationItemView itemView = (BottomNavigationItemView) tab;
//        //下标数据
//        //加载我们的角标View，新创建的一个布局
//        View badge = LayoutInflater.from(this).inflate(R.layout.item_red_dot, menuView, false);
//        //添加到Tab上
//        itemView.addView(badge);
//        tvDot= badge.findViewById(R.id.tv_dot);
//        tvDot.setText(String.valueOf(1));
//        ChatHelper.getInstance().unReadShow(ChatHelper.getInstance().getUnread(), tvDot);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(tvDot!=null){
            ChatHelper.getInstance().unReadShow(ChatHelper.getInstance().getUnread(), tvDot);
        }
    }

    @Override
    public boolean onNewMessages(List<TIMMessage> list) {
        ChatHelper.getInstance().unReadShow(ChatHelper.getInstance().getUnread(), tvDot);
        return false;
    }

    private void prepareThirdPushToken() {
        ThirdPushTokenMgr.getInstance().setPushTokenToTIM();
        if (ThirdPushTokenMgr.USER_GOOGLE_FCM) {
            return;
        }
        if (IMFunc.isBrandHuawei()) {
            // 华为离线推送
            HMSAgent.connect(this, new ConnectHandler() {
                @Override
                public void onConnect(int rst) {

                }
            });
            getHuaWeiPushToken();
        }
        if (IMFunc.isBrandVivo()) {
            // vivo离线推送
            PushClient.getInstance(getApplicationContext()).turnOnPush(new IPushActionListener() {
                @Override
                public void onStateChanged(int state) {
                    if (state == 0) {
                        String regId = PushClient.getInstance(getApplicationContext()).getRegId();
                        ThirdPushTokenMgr.getInstance().setThirdPushToken(regId);
                        ThirdPushTokenMgr.getInstance().setPushTokenToTIM();
                    } else {
                        // 根据vivo推送文档说明，state = 101 表示该vivo机型或者版本不支持vivo推送，链接：https://dev.vivo.com.cn/documentCenter/doc/156
                    }
                }
            });
        }
    }

    private void getHuaWeiPushToken() {
        HMSAgent.Push.getToken(new GetTokenHandler() {
            @Override
            public void onResult(int rtnCode) {
            }
        });
    }

    @Subscriber(tag = "change")
    public void recreateData(String str) {
        if (StringUtils.isNoEmpty(str) && str.equals("changeTheme")) {
            recreate();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case 101:
                ToastUtils.getMineToast("未知应用安装设置");
                break;
        }
    }


    public void setCheck(int position) {
        for (int i = 0; i < 4; i++) {
            if (i == position) {
                bottomNavigationView.getMenu().getItem(i).setChecked(true);
            } else {
                bottomNavigationView.getMenu().getItem(i).setChecked(false);
            }
        }
    }

    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // 设置第一次按的时间
    long currentMillions = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 第一次的时间
            if (System.currentTimeMillis() - currentMillions > 2000) {
                ToastUtils.getMineToast(R.string.exit_again);
                // 获取第二次点击的时间 然后判断两次时间差
                currentMillions = System.currentTimeMillis();
            } else {
                ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().activityAllList);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //跳转至维修维保
    @Subscriber(tag = "repairClick")
    public void toRepair(String str) {
        if (!str.isEmpty()) {
//            viewPager.setCurrentItem(2);
//            bottomNavigationView.getMenu().findItem(2).setChecked(true);
        }
    }

    //跳转至论坛界面
    @Subscriber(tag = "forumClick")
    public void toForum(String str) {
        if (!str.isEmpty()) {
//            viewPager.setCurrentItem(1);
//            bottomNavigationView.getMenu().findItem(1).setChecked(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void requestSettingCanDrawOverlays() {
//        requestAlertWindowPermission(getContext());
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= Build.VERSION_CODES.O) {//8.0以上
            if (StringUtils.isNoEmpty((String) SharedPreferencesUtils.getInstance().get(this, SharedKeyConstant.getInstance().NOTIY_DIALOG, ""))) {
                return;
            }
            DialogUtils.getInstance().toFaceCheck(this, "是否打开桌面角标权限、以便接收通知显示桌面红点角标", new SuccessCallBack() {
                @Override
                public void onSuccess() {
                    Intent intent = new Intent();
                    intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                    intent.putExtra(Settings.EXTRA_APP_PACKAGE, "com.example.huoshangkou.jubowan");
                    startActivityForResult(intent, 1);
                    SharedPreferencesUtils.getInstance().put(MainActivity.this, SharedKeyConstant.getInstance().NOTIY_DIALOG, "putOnDot");
                }

                @Override
                public void onFail() {
                }
            });
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1010:
                UpdateDialogUtils.downLoadApk(this);
                break;
        }
    }
}
