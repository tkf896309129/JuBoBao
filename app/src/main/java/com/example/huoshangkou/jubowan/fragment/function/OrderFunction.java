package com.example.huoshangkou.jubowan.fragment.function;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.example.huoshangkou.jubowan.adapter.FragmentChangeAdapter;
import com.example.huoshangkou.jubowan.constant.BundleConstant;
import com.example.huoshangkou.jubowan.fragment.OrderListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment.function
 * 类名：OrderCommitFunction
 * 描述：订单功能工具类
 * 创建时间：2017-02-09  13:24
 */

public class OrderFunction {

    //订单分类界面绑定 0：待确认，1：待支付，2：待收货，3全部，4已完成
    //2 全部 ---  0 待受理 --- 1 待支付 --- 3 待收货 --- 4 已完成
    public static void orderTypeBind(TabLayout tabOrder, List<Fragment> fragmentList, FragmentChangeAdapter changeAdapter,
                                     FragmentManager fragmentManager, final ViewPager vpOrder) {
        tabOrder.addTab(tabOrder.newTab().setText("全部"));
        tabOrder.addTab(tabOrder.newTab().setText("待受理"));
        tabOrder.addTab(tabOrder.newTab().setText("待支付"));
        tabOrder.addTab(tabOrder.newTab().setText("待收货"));
        tabOrder.addTab(tabOrder.newTab().setText("已完成"));

        tabOrder.setTabMode(TabLayout.MODE_FIXED);

        fragmentList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int state = 0;
            if (i == 0) {
                state = 2;
            } else if (i < 3) {
                state = i - 1;
            } else {
                state = i;
            }

            Fragment fragment = OrderListFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putString(BundleConstant.getInstance().ORDER_STATE, state + "");
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
        }

        changeAdapter = new FragmentChangeAdapter(fragmentManager, fragmentList);
        vpOrder.setAdapter(changeAdapter);

        vpOrder.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabOrder));

        tabOrder.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpOrder.setCurrentItem(tab.getPosition(),false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
