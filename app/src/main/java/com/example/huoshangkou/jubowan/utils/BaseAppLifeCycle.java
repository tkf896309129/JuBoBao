package com.example.huoshangkou.jubowan.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import me.leolin.shortcutbadger.ShortcutBadger;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：BaseAppLifeCycle
 * 描述：
 * 创建时间：2019-12-02  09:40
 */

public class BaseAppLifeCycle implements Application.ActivityLifecycleCallbacks {

    public  void init(Application application) {
        application.registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    //注册该监听以后，app的每一个activity的生命周期都会调用该callback的对应方法
    @Override
    public void onActivityResumed(Activity activity) {
        //app不管哪个界面可见，桌面红点即消失
        ShortcutBadger.applyCount(activity,0);
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

}
