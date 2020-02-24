package com.example.huoshangkou.jubowan.activity.function;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.UserInfoBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.fragment.ForumFragment;
import com.example.huoshangkou.jubowan.fragment.HomeFragment;
import com.example.huoshangkou.jubowan.fragment.MineFragment;
import com.example.huoshangkou.jubowan.fragment.OrderFragment;
import com.example.huoshangkou.jubowan.fragment.RepairFragment;
import com.example.huoshangkou.jubowan.utils.FragmentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：MainFunction
 * 描述：
 * 创建时间：2016-12-29  16:04
 */

public class MainFunction {

    private static class MainHelper {
        private static MainFunction INSTANCE = new MainFunction();
    }

    public static MainFunction getInstance() {
        return MainHelper.INSTANCE;
    }

    /**
     * 界面的切换
     */
    public void changePages(List<Fragment> fragmentList, FragmentManager fragmentManager, Context context, RadioGroup rgMainTab) {
        //加载界面
        fragmentList = new ArrayList<>();
        fragmentList.add(HomeFragment.newInstance());
//        fragmentList.add(ForumFragment.newInstance());
//        fragmentList.add(ForumFragment.newInstance());
        fragmentList.add(ForumFragment.newInstance());
        fragmentList.add(RepairFragment.newInstance());
        fragmentList.add(OrderFragment.newInstance());
        fragmentList.add(MineFragment.newInstance());

        //切换界面
//        new FragmentUtils(fragmentManager, fragmentList, R.id.ll_home_include, rgMainTab, context).getFragment();
    }


    //获取业务员认证信息
//    public void getYwyInfo(Context context) {
//        OkhttpUtil.getInstance().setUnCacheData(context,context.getString(R.string.loading_message),UrlConstant.getInstance().URL + PostConstant.getInstance().GET_USER_INFO
//                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
//            @Override
//            public void onSuccess(String json) {
//                LogUtils.i(json);
//                UserInfoBean userInfoBean = JSON.parseObject(json, UserInfoBean.class);
//                if(userInfoBean.getSuccess() == -1){
//
//                }
//            }
//
//            @Override
//            public void onFail() {
//
//            }
//        });
//    }


}
