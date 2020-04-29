package com.example.huoshangkou.jubowan.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：ActivityUtils
 * 描述：
 * 创建时间：2016-12-28  09:53
 */

public class ActivityUtils {

    private static class ActivityHelper {
        private static ActivityUtils INSTANCE = new ActivityUtils();
    }

    public static ActivityUtils getInstance() {
        return ActivityHelper.INSTANCE;
    }


    private Activity mCurrentActivity;
    //组织架构
    public List<Activity> memberList = new ArrayList<>();
    public List<Activity> chatList = new ArrayList<>();
    //地址
    public List<Activity> areaList = new ArrayList<>();
    //贷款申请
    public List<Activity> activityLoan = new ArrayList<>();
    //存放所有的Activity 用于结束使用
    public List<Activity> activityAllList = new ArrayList<>();
    //登录界面
    public List<Activity> activityLoginList = new ArrayList<>();
    //主界面
    public List<Activity> mainList = new ArrayList<>();
    //认证界面
    public List<Activity> approveList = new ArrayList<>();
    //审批界面
    public List<Activity> approveCheckList = new ArrayList<>();
    //购物车界面
    public List<Activity> shopCarList = new ArrayList<>();
    //下单
    public List<Activity> orderList = new ArrayList<>();
    //佣金
    public List<Activity> forumList = new ArrayList<>();
    //垫付款
    public List<Activity> dianList = new ArrayList<>();
    //聚玻白条
    public List<Activity> btList = new ArrayList<>();
    //项目招标提交
    public List<Activity> newZbList = new ArrayList<>();
    //客户管理
    public List<Activity> customerList = new ArrayList<>();
    //PadPay
    public List<Activity> padPayList = new ArrayList<>();
    public List<Activity> allList = new ArrayList<>();


    /**
     * 添加到集合中
     *
     * @param activity
     * @param activityList
     */
    public void addActivity(Activity activity, List<Activity> activityList) {

        activityList.add(activity);

    }


    /**
     * 结束掉Activity
     */
    public void finishActivity(List<Activity> activityList) {
        int num = activityList.size();
        for (int i = 0; i < num; i++) {
            Activity activity = activityList.get(i);
            //如果没有结束掉 就结束改界面
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public void setCurrentActivity(Activity activity) {
        mCurrentActivity = activity;
    }

    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

}
