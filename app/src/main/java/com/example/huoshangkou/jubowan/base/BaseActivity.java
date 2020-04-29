package com.example.huoshangkou.jubowan.base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.LoginActivity;
import com.example.huoshangkou.jubowan.activity.MainActivity;
import com.example.huoshangkou.jubowan.activity.function.LoginFunction;
import com.example.huoshangkou.jubowan.chat.helper.ChatHelper;
import com.example.huoshangkou.jubowan.constant.Constant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.SharedValueConstant;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.AddressUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.NetUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IMEventListener;
import com.umeng.message.UTrack;
import com.zhy.http.okhttp.OkHttpUtils;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.ButterKnife;
import qiu.niorgai.StatusBarCompat;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.base
 * 类名：BaseActivity
 * 描述：
 * 创建时间：2016-12-28  09:43
 */
public abstract class BaseActivity extends AppCompatActivity {
    //判断网络是否连接
    protected boolean isNetConnect = false;
    //判断是否是wifi网络
    protected boolean isNetWifi = false;

    private LoginOutHandler handler = new LoginOutHandler(this);

    private AlertDialog alertDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.getBoolean(Constant.ACCOUNT_REMOVED, false)) {
            // 防止被移除后，没点确定按钮然后按了home键，长期在后台又进app导致的crash
            // 三个fragment里加的判断同理
            finish();
            startActivity(new Intent(this, MainActivity.class));
            return;
        } else if (savedInstanceState != null && savedInstanceState.getBoolean("isConflict", false)) {
            // 防止被T后，没点确定按钮然后按了home键，长期在后台又进app导致的crash
            // 三个fragment里加的判断同理
            finish();
            startActivity(new Intent(this, MainActivity.class));
            return;
        }
        //改变状态栏颜色
        //make sure activity_bind_blank will not in background if user is logged into another device or removed
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().activityAllList);
        ActivityUtils.getInstance().setCurrentActivity(this);
        //不需要全屏
        if (!setIsFull()) {
            StatusBarCompat.setStatusBarColor(this, getToolColor());
            //需要全屏
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                //透明状态栏
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //透明导航栏
//                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
            //透明状态栏
//            StatusBarCompat.translucentStatusBar(this);
        }
        //设置白色字体
        MIUISetStatusBarLightMode(getWindow(), false);
        FlymeSetStatusBarLightMode(getWindow(), false);
        //加载布局文件
        setContentView(initLayout());
        //黄油刀绑定
        ButterKnife.bind(this);
        //数据逻辑处理
        initData();

        //判断wifi 是否连接
        isNetWifi = NetUtils.isWifi(this);
        //判断网络是否连接
        isNetConnect = NetUtils.isConnected(this);
        TUIKit.addIMEventListener(mIMEventListener);
    }

    // 监听做成静态可以让每个子类重写时都注册相同的一份。
    private IMEventListener mIMEventListener = new IMEventListener() {
        @Override
        public void onForceOffline() {
            //清空用户登录信息
            LoginFunction.getInstance().unSaveLoginMessage(getContext());
            SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().LOGIN_STATE, SharedValueConstant.getInstance().LOGIN_FAIL);
            handler.sendEmptyMessage(1);
        }
    };

    private class LoginOutHandler extends Handler {
        private final WeakReference<AppCompatActivity> mActivity;

        public LoginOutHandler(AppCompatActivity activity) {
            mActivity = new WeakReference<AppCompatActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mActivity == null) {
                return;
            }
            final Context context = getContext();
            if (((AppCompatActivity) context).isFinishing()) {
                return;
            }
            alertDialog = CopyIosDialogUtils.getInstance().getErrorLoginDialogNoCancel(context, "您的帐号已在其它设备登录", new CopyIosDialogUtils.ErrDialogCallBack() {
                @Override
                public void confirm() {
                    ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().activityAllList);
                    IntentUtils.getInstance().toActivity(context, LoginActivity.class);
                    //退出登录
                    AddressUtils.getInstance().saveDefaultAddress("", "", "", "");
                    ChatHelper.getInstance().loginOutChat();
                    //解除绑定
                    BaseApp.getPush().removeAlias(PersonConstant.getInstance().getUserId(), "UserID", new UTrack.ICallBack() {
                        @Override
                        public void onMessage(boolean b, String s) {
                            SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().PUSH, "pushOff");
                        }
                    });

                }
            });
        }
    }

    /**
     * 模板方法,用于返回布局id
     *
     * @return
     */
    public abstract int initLayout();

    /**
     * 设置是否需要全屏沉浸式
     */
    protected abstract boolean setIsFull();

    /**
     * 获取状态栏颜色
     */
    protected int getToolColor() {
        //默认的是蓝色  如果需要改变颜色 可以进行重写改方法
        return getResources().getColor(R.color.main_tab_blue_dark);
    }

    /**
     * 数据逻辑处理
     */
    public abstract void initData();

    //返回当前的上下文
    protected Context getContext() {
        return this;
    }

    protected void setIsNeedHideKey(boolean flag) {
        isNeedHideKey = flag;
    }

    private boolean isNeedHideKey = false;

    protected boolean getIsNeedHideKey() {
        return isNeedHideKey;
    }

    //取消绑定
    @Override
    protected void onDestroy() {
        OkHttpUtils.getInstance().cancelTag(this);
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        super.onDestroy();
        ButterKnife.unbind(this);
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    //中文
//    protected boolean isZh() {
//        Locale locale = getResources().getConfiguration().locale;
//        String language = locale.getLanguage();
//        if (language.endsWith("zh"))
//            return true;
//        else
//            return false;
//    }

    //英文
//    protected boolean isEn() {
//        Locale locale = getResources().getConfiguration().locale;
//        String language = locale.getLanguage();
//        if (language.endsWith("en"))
//            return true;
//        else
//            return false;
//    }

    /**
     * 设置魅族手机状态栏图标颜色风格
     * 可以用来判断是否为Flyme用户
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    public static boolean FlymeSetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }


    /**
     * 设置小米手机状态栏字体图标颜色模式，需要MIUIV6以上
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    public static boolean MIUISetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (dark) {//状态栏透明且黑色字体
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);
                } else {//清除黑色字体
                    extraFlagField.invoke(window, 0, darkModeFlag);
                }
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }


}
