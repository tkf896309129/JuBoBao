package com.example.huoshangkou.jubowan.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.MainActivity;
import com.example.huoshangkou.jubowan.activity.SplashActivity;
import com.example.huoshangkou.jubowan.chat.helper.ConfigHelper;
import com.example.huoshangkou.jubowan.chat.helper.NotifyChatHelper;
import com.example.huoshangkou.jubowan.chat.signature.GenerateChatUserSig;
import com.example.huoshangkou.jubowan.chat.signature.PrivateConstants;
import com.example.huoshangkou.jubowan.chat.thirdpush.ThirdPushTokenMgr;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.SharedValueConstant;
import com.example.huoshangkou.jubowan.db.DBHelper;
import com.example.huoshangkou.jubowan.photo.GlideImageLoader;
import com.example.huoshangkou.jubowan.service.MyPushIntentService;
import com.example.huoshangkou.jubowan.utils.BaseAppLifeCycle;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.meizu.cloud.pushsdk.PushManager;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.imsdk.TIMBackgroundParam;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMOfflinePushNotification;
import com.tencent.imsdk.TIMOfflinePushSettings;
import com.tencent.imsdk.session.SessionWrapper;
import com.tencent.imsdk.utils.IMFunc;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IMEventListener;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.vivo.push.PushClient;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.zxy.tiny.Tiny;

import org.xutils.x;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import agent.HMSAgent;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan
 * 类名：BaseApp
 * 描述：程序入口基类
 * 创建时间：2016-12-26  17:27
 */
public class BaseApp extends MultiDexApplication {
    private static Context context;
    public static PushAgent mPushAgent;
    int pid;
    String processAppName;
    private static Resources sRes;

    private static DBHelper dbHelper;

    /**全局设置SmartRefreshLayout的 Header 和 Footer*/
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.gray_bg, R.color.black);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    //do{}while();
    //while(){};
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initX5();
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
        //分享平台key的配置
        PlatformConfig.setQQZone("1105185985", "YTqzQKgWeiOftRaC");
        PlatformConfig.setWeixin("wx077bb576b1c7629f", "e056eb66e06f92bdc28cbe7de6f31afd");
        PlatformConfig.setSinaWeibo("1452954120", "7ec2d27d3c72c2307ac4dd182e367626", "https://sns.whalecloud.com/sina2/callback");
        //小米通道
//        MiPushRegistar.register(this, " 2882303761517466185", "5741746678185");
//华为通道
//        HuaWeiRegister.register(this);
//魅族通道
//        MeizuRegister.register(this, MEIZU_APPID, MEIZU_APPKEY);

        if (SessionWrapper.isMainProcess(getApplicationContext())) {
            /**
             * TUIKit的初始化函数
             *
             * @param context  应用的上下文，一般为对应应用的ApplicationContext
             * @param sdkAppID 您在腾讯云注册应用时分配的sdkAppID
             * @param configs  TUIKit的相关配置项，一般使用默认即可，需特殊配置参考API文档
             */
            TUIKit.init(this, GenerateChatUserSig.SDKAPPID, new ConfigHelper().getConfigs());

            if (IMFunc.isBrandXiaoMi()) {
                // 小米离线推送
                MiPushClient.registerPush(this, PrivateConstants.XM_PUSH_APPID, PrivateConstants.XM_PUSH_APPKEY);
            } else if (IMFunc.isBrandHuawei()) {
                // 华为离线推送
                HMSAgent.init(this);
            } else if (MzSystemUtils.isBrandMeizu(this)) {
                // 魅族离线推送
                PushManager.register(this, PrivateConstants.MZ_PUSH_APPID, PrivateConstants.MZ_PUSH_APPKEY);
            } else if (IMFunc.isBrandVivo()) {
                // vivo离线推送
                PushClient.getInstance(getApplicationContext()).initialize();
            }
            registerActivityLifecycleCallbacks(new StatisticActivityLifecycleCallback());
        }

        TIMOfflinePushSettings settings = new TIMOfflinePushSettings();
        //开启离线推送
        settings.setEnabled(true);
        TIMManager.getInstance().setOfflinePushSettings(settings);

        closeAndroidPDialog();
        //debug模式打开 打包的时候最好取消debug
        Config.DEBUG = true;
        x.Ext.init(this);
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(false);
        UMShareAPI.get(this).setShareConfig(config);
        //购买配件功能添加，垫付款修改，借款借据修改。
        mPushAgent = PushAgent.getInstance(this);
        mPushAgent.setDisplayNotificationNumber(20);
        mPushAgent.setPushIntentServiceClass(MyPushIntentService.class);

        //记录当前的语言
//        if (isZh()) {
        SharedPreferencesUtils.getInstance().put(this, SharedKeyConstant.getInstance().NOW_LANGUAGE, SharedValueConstant.getInstance().CHINESE);
//        }
//        if (isEn()) {
//            SharedPreferencesUtils.getInstance().put(this, SharedKeyConstant.getInstance().NOW_LANGUAGE, SharedValueConstant.getInstance().ENGLISH);
//        }
        mPushAgent.getRegistrationId();
        //注册推送服务 每次调用register都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                LogUtils.i("device token: " + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                LogUtils.i("register failed: " + s + " " + s1);
            }
        });

        //相机配置
        ThemeConfig themeConfig = new ThemeConfig.Builder()
                .setTitleBarBgColor(Color.rgb(5, 143, 249))
                .setIconBack(R.mipmap.back)
                .setIconCamera(R.mipmap.picture)
                .setCheckNornalColor(getResources().getColor(R.color.gray_all))//选择框未选颜色
                .setCheckSelectedColor(getResources().getColor(R.color.main_tab_blue_all))//选择框选中颜色
                .setFabNornalColor(getResources().getColor(R.color.main_tab_blue_all))//设置Floating按钮Nornal状态颜色
                .build();
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(false)
                .setEnableCrop(false)
                .setEnableRotate(false)
                .setCropSquare(false)
                .setEnablePreview(false)
                .build();
        ImageLoader imageLoader = new GlideImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(this, imageLoader, themeConfig)
                .setFunctionConfig(functionConfig)
                .setNoAnimcation(true)
                .build();

        GalleryFinal.init(coreConfig);
        //获取设备信息  android.os.Build.MANUFACTURER
        SharedPreferencesUtils.getInstance().put(getInstance(), SharedKeyConstant.getInstance().PHOE_TYPE, android.os.Build.MODEL);

        pid = android.os.Process.myPid();
        processAppName = getAppName(pid);

        new BaseAppLifeCycle().init(this);
        //异常处理
//        AppException appException = AppException.getInstance();
//        appException.init(getInstance());
        String type = (String) SharedPreferencesUtils.getInstance().get(this, SharedKeyConstant.getInstance().NIGHT_DEFAULT, "");
        switch (type) {
            case "night":
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            default:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
        }

        if (processAppName == null || !processAppName.equalsIgnoreCase(context.getPackageName())) {
            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }

        //环信 初始化
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        //初始化
        EMClient.getInstance().init(getApplicationContext(), options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(false);

        //初始化数据库
        dbHelper = new DBHelper(context);

        Tiny.getInstance().init(this);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            builder.detectFileUriExposure();
        }
    }

    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    private static void closeAndroidPDialog() {
        try {
            Class aClass = Class.forName("android.content.pm.PackageParser$Package");
            Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread = declaredMethod.invoke(null);
            Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initX5() {
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {

            @Override
            public void onCoreInitFinished() {
                //x5内核初始化完成回调接口，此接口回调并表示已经加载起来了x5，有可能特殊情况下x5内核加载失败，切换到系统内核。
                Log.e("@@", "onCoreInitFinished:");
            }

            @Override
            public void onViewInitFinished(boolean b) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.e("@@", "加载内核是否成功:" + b);
            }
        });
    }

    public static Context getInstance() {
        return context;
    }

    public static Resources getsRes() {
        return sRes;
    }

    public static DBHelper getDbHelper() {
        return dbHelper;
    }

    public static PushAgent getPush() {
        return mPushAgent;
    }

    @Override
    public void attachBaseContext(Context base) {
        MultiDex.install(base);
        super.attachBaseContext(base);
    }

    //中文
    private boolean isZh() {
        Locale locale = getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.endsWith("zh"))
            return true;
        else
            return false;
    }

    //英文
    private boolean isEn() {
        Locale locale = getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.endsWith("en"))
            return true;
        else
            return false;
    }

    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }

    class StatisticActivityLifecycleCallback implements ActivityLifecycleCallbacks {
        private int foregroundActivities = 0;
        private boolean isChangingConfiguration;
        private IMEventListener mIMEventListener = new IMEventListener() {
            @Override
            public void onNewMessages(List<TIMMessage> msgs) {
//                if (CustomMessage.convert2VideoCallData(msgs) != null) {
//                    // 会弹出接电话的对话框，不再需要通知
//                    return;
//                }
                for (TIMMessage msg : msgs) {
                    // 小米手机需要在设置里面把demo的"后台弹出权限"打开才能点击Notification跳转。TIMOfflinePushNotification后续不再维护，如有需要，建议应用自己调用系统api弹通知栏消息。
                    TIMOfflinePushNotification notification = new TIMOfflinePushNotification(BaseApp.this, msg);
                    NotifyChatHelper.getInstance().notifyChat(getApplicationContext(), msg);
                }
            }
        };

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            if (bundle != null) { // 若bundle不为空则程序异常结束
                // 重启整个程序
            }
        }

        @Override
        public void onActivityStarted(Activity activity) {
            foregroundActivities++;
            if (foregroundActivities == 1 && !isChangingConfiguration) {
                // 应用切到前台
                TIMManager.getInstance().doForeground(new TIMCallBack() {
                    @Override
                    public void onError(int code, String desc) {
                    }

                    @Override
                    public void onSuccess() {
                    }
                });
                TUIKit.removeIMEventListener(mIMEventListener);
            }
            isChangingConfiguration = false;
        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {
            foregroundActivities--;
            if (foregroundActivities == 0) {
                // 应用切到后台
                int unReadCount = 0;
                List<TIMConversation> conversationList = TIMManager.getInstance().getConversationList();
                for (TIMConversation timConversation : conversationList) {
                    unReadCount += timConversation.getUnreadMessageNum();
                }
                TIMBackgroundParam param = new TIMBackgroundParam();
                param.setC2cUnread(unReadCount);
                TIMManager.getInstance().doBackground(param, new TIMCallBack() {
                    @Override
                    public void onError(int code, String desc) {

                    }

                    @Override
                    public void onSuccess() {
                    }
                });
                // 应用退到后台，消息转化为系统通知
                TUIKit.addIMEventListener(mIMEventListener);
            }
            isChangingConfiguration = activity.isChangingConfigurations();
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }
}
