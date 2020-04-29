package com.example.huoshangkou.jubowan.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.LoginActivity;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.SharedValueConstant;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.NetUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.ShowHideUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.chat.EMClient;
import com.umeng.message.UTrack;

import java.util.Locale;

import butterknife.ButterKnife;
import qiu.niorgai.StatusBarCompat;

import static com.example.huoshangkou.jubowan.base.BaseActivity.FlymeSetStatusBarLightMode;
import static com.example.huoshangkou.jubowan.base.BaseActivity.MIUISetStatusBarLightMode;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.base
 * 类名：HideBaseActivity
 * 描述：
 * 创建时间：2017-06-02  10:02
 */

public abstract class HideBaseActivity extends AppCompatActivity {
    //判断网络是否连接
    protected boolean isNetConnect = false;
    //判断是否是wifi网络
    protected boolean isNetWifi = false;

    private boolean isShowDialog = false;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 206:
                    if (isShowDialog) {
                        return;
                    }
                    isShowDialog = true;

                    // 显示帐号在其他设备登录
                    CopyIosDialogUtils.getInstance().getNoCancelErrorDialog(getContext(), "您的账号已在其他设备登录", new CopyIosDialogUtils.ErrDialogCallBack() {
                        @Override
                        public void confirm() {
                            BaseApp.getPush().removeAlias(PersonConstant.getInstance().getUserId(), "UserID", new UTrack.ICallBack() {
                                @Override
                                public void onMessage(boolean b, String s) {
                                    if (b) {
                                        LogUtils.i(s);
                                    } else {
                                        LogUtils.i("失败");
                                    }
                                }
                            });
                            EMClient.getInstance().logout(true);
                            SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().LOGIN_STATE, SharedValueConstant.getInstance().LOGIN_FAIL);
                            IntentUtils.getInstance().toActivity(getContext(), LoginActivity.class);
                            ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().activityLoginList);
                        }
                    });
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //改变状态栏颜色
        //make sure activity will not in background if user is logged into another device or removed
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().activityAllList);
        ActivityUtils.getInstance().setCurrentActivity(this);

        //不需要全屏
        if (!setIsFull()) {
            StatusBarCompat.setStatusBarColor(this, getToolColor());
            //需要全屏
        } else {
            //透明状态栏
            StatusBarCompat.translucentStatusBar(this);
        }
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

        //获取当前的语言
//        String language = (String) SharedPreferencesUtils.getInstance().get(this, SharedKeyConstant.getInstance().NOW_LANGUAGE, "");
//        if (language.equals(SharedValueConstant.getInstance().CHINESE)) {
//
//        }

        //判断语言的改变
//        if (isEn()) {
//            if (!language.equals(SharedValueConstant.getInstance().ENGLISH)) {
//                ToastUtils.getMineToast("Language Is Changed");
//                languageChangeExit();
//            }
//        } else {
//            if (!language.equals(SharedValueConstant.getInstance().CHINESE)) {
//                ToastUtils.getMineToast("语言已经改变");
//                languageChangeExit();
//            }
//        }

        //注册一个监听连接状态的listener
//        EMClient.getInstance().addConnectionListener(new MyConnectionListener());
        //设置白色字体
        MIUISetStatusBarLightMode(getWindow(),false);
        FlymeSetStatusBarLightMode(getWindow(),false);
    }

    //实现ConnectionListener接口
    private class MyConnectionListener implements EMConnectionListener {
        @Override
        public void onConnected() {

        }

        @Override
        public void onDisconnected(final int error) {
            LogUtils.i("Base" + error);
            handler.sendEmptyMessage(error);
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
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    protected void onResume() {
        super.onResume();
//        if (isNeedCheck) {
//            checkPermissions(needPermissions);
//        }
    }

    protected void onPause() {
        super.onPause();
    }

    //中文
    protected boolean isZh() {
        Locale locale = getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.endsWith("zh"))
            return true;
        else
            return false;
    }

    //英文
    protected boolean isEn() {
        Locale locale = getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.endsWith("en"))
            return true;
        else
            return false;
    }

    //语言改变退出程序
    private void languageChangeExit() {
        CopyIosDialogUtils.getInstance().getErrorDialog(this, "语言已经改变，需要退出该应用重新进入", new CopyIosDialogUtils.ErrDialogCallBack() {
            @Override
            public void confirm() {
                System.exit(0);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (ShowHideUtils.isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }


}
