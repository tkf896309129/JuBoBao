package com.example.huoshangkou.jubowan.activity.function;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.adapter.FragmentChangeAdapter;
import com.example.huoshangkou.jubowan.constant.ApproveConstant;
import com.example.huoshangkou.jubowan.fragment.ApproveFragment;
import com.example.huoshangkou.jubowan.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：MineApproveFunction
 * 描述：我的审批工具类
 * 创建时间：2017-03-17  11:42
 */

public class MineApproveFunction {

//    private static class MineApproveHelper {
//        private static MineApproveFunction INSTANCE = new MineApproveFunction();
//    }
//
//    public static MineApproveFunction getInstance() {
//        return MineApproveHelper.INSTANCE;
//    }

    //我的审批
    public static void getMineApprove(TextView tvTitle, List<Fragment> fragmentList, TabLayout tabMineApprove,
                                      FragmentChangeAdapter fragmentChangeAdapter, FragmentManager fragmentManager,
                                      final ViewPager vpMineApprove) {

        //我的申请
        tvTitle.setText("我的审批");


        tabMineApprove.addTab(tabMineApprove.newTab().setText("未审批"));
        tabMineApprove.addTab(tabMineApprove.newTab().setText("已审批"));


        fragmentList = new ArrayList<>();

        ApproveFragment approveCheckFragment = ApproveFragment.newInstance();
        Bundle bunCheck = new Bundle();
        bunCheck.putString(IntentUtils.getInstance().TYPE, ApproveConstant.getInstance().UN_CHECK + "");
        bunCheck.putString(IntentUtils.getInstance().APPROVE_TYPE, ApproveConstant.getInstance().APPROVE);
        approveCheckFragment.setArguments(bunCheck);
        fragmentList.add(approveCheckFragment);


        ApproveFragment approveFragment = ApproveFragment.newInstance();
        Bundle bunUnCheck = new Bundle();
        bunUnCheck.putString(IntentUtils.getInstance().TYPE, ApproveConstant.getInstance().CHECK + "");
        bunUnCheck.putString(IntentUtils.getInstance().APPROVE_TYPE, ApproveConstant.getInstance().APPROVE);
        approveFragment.setArguments(bunUnCheck);

        fragmentList.add(approveFragment);


        fragmentChangeAdapter = new FragmentChangeAdapter(fragmentManager, fragmentList);
        vpMineApprove.setAdapter(fragmentChangeAdapter);


        tabMineApprove.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpMineApprove.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        //绑定viewpager
        vpMineApprove.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabMineApprove));


    }


    //我的申请
    public static void getMineApply(TextView tvTitle, List<Fragment> fragmentList, TabLayout tabMineApprove,
                                    FragmentChangeAdapter fragmentChangeAdapter, FragmentManager fragmentManager,
                                    final ViewPager vpMineApprove) {

        //我的申请
        tvTitle.setText("我的申请");

        tabMineApprove.addTab(tabMineApprove.newTab().setText("申请中"));
        tabMineApprove.addTab(tabMineApprove.newTab().setText("申请完成"));

        fragmentList = new ArrayList<>();
        ApproveFragment approveFragment = ApproveFragment.newInstance();
        Bundle bunUnCheck = new Bundle();
        //判断申请中还是申请完成
        bunUnCheck.putString(IntentUtils.getInstance().TYPE, ApproveConstant.getInstance().UN_APPLY + "");
        bunUnCheck.putString(IntentUtils.getInstance().APPROVE_TYPE, ApproveConstant.getInstance().APPLY);
        approveFragment.setArguments(bunUnCheck);

        fragmentList.add(approveFragment);

        ApproveFragment approveCheckFragment = ApproveFragment.newInstance();
        Bundle bunCheck = new Bundle();
        //判断申请中还是申请完成
        bunCheck.putString(IntentUtils.getInstance().TYPE, ApproveConstant.getInstance().HAS_APPLY + "");
        bunCheck.putString(IntentUtils.getInstance().APPROVE_TYPE, ApproveConstant.getInstance().APPLY);
        approveCheckFragment.setArguments(bunCheck);

        fragmentList.add(approveCheckFragment);


        fragmentChangeAdapter = new FragmentChangeAdapter(fragmentManager, fragmentList);
        vpMineApprove.setAdapter(fragmentChangeAdapter);

        tabMineApprove.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpMineApprove.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //绑定viewpager
        vpMineApprove.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabMineApprove));
    }

    //我的知会
    public static void getMineZhApply(TextView tvTitle, List<Fragment> fragmentList, TabLayout tabMineApprove,
                                      FragmentChangeAdapter fragmentChangeAdapter, FragmentManager fragmentManager,
                                      final ViewPager vpMineApprove) {

        //我的申请
        tvTitle.setText("我的知会");

        tabMineApprove.addTab(tabMineApprove.newTab().setText("未读"));
        tabMineApprove.addTab(tabMineApprove.newTab().setText("已读"));

        fragmentList = new ArrayList<>();
        ApproveFragment approveFragment = ApproveFragment.newInstance();
        Bundle bunUnCheck = new Bundle();
        //判断申请中还是申请完成
        bunUnCheck.putString(IntentUtils.getInstance().TYPE, ApproveConstant.getInstance().UN_ZH + "");
        bunUnCheck.putString(IntentUtils.getInstance().APPROVE_TYPE, ApproveConstant.getInstance().MINE_ZH);
        approveFragment.setArguments(bunUnCheck);

        fragmentList.add(approveFragment);

        ApproveFragment approveCheckFragment = ApproveFragment.newInstance();
        Bundle bunCheck = new Bundle();
        //判断申请中还是申请完成
        bunCheck.putString(IntentUtils.getInstance().TYPE, ApproveConstant.getInstance().HAS_ZH + "");
        bunCheck.putString(IntentUtils.getInstance().APPROVE_TYPE, ApproveConstant.getInstance().MINE_ZH);
        approveCheckFragment.setArguments(bunCheck);
        fragmentList.add(approveCheckFragment);

        fragmentChangeAdapter = new FragmentChangeAdapter(fragmentManager, fragmentList);
        vpMineApprove.setAdapter(fragmentChangeAdapter);

        tabMineApprove.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpMineApprove.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        //绑定viewpager
        vpMineApprove.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabMineApprove));

    }


}
