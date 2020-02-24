package com.example.huoshangkou.jubowan.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.FragmentChangeAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.constant.BundleConstant;
import com.example.huoshangkou.jubowan.fragment.RepairOrderFragment;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：MyRepairOrderActivity
 * 描述：我的维修订单
 * 创建时间：2017-04-26  14:01
 */

public class MyRepairOrderActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tab_repair)
    TabLayout tabRepair;
    @Bind(R.id.vp_repair)
    ViewPager vpRepair;

    private String repairId = "";
    private FragmentChangeAdapter changeAdapter;
    private List<Fragment> fragmentList;
    //是否是规格改造
    private boolean isGz = false;

    @Override
    public int initLayout() {
        return R.layout.activity_repair_order;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("我的维修订单");

        repairId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        tabRepair.addTab(tabRepair.newTab().setText("全部"));
        tabRepair.addTab(tabRepair.newTab().setText("待受理"));
        tabRepair.addTab(tabRepair.newTab().setText("待支付"));
        tabRepair.addTab(tabRepair.newTab().setText("已完成"));

        String gz = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        //不为空表示为规格改造
        if(StringUtils.isNoEmpty(gz)){
            isGz = true;
        }
        if(isGz){
            ToastUtils.getMineToast("规格改造");
        }

        tabRepair.setTabMode(TabLayout.MODE_FIXED);

        //2 全部 ---  0 待受理 --- 1 待支付 --- 3 待收货 --- 4 已完成
        fragmentList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Fragment fragment = RepairOrderFragment.newInstance();
            Bundle bundle = new Bundle();
            int state = 0;
            if (i == 0) {
                state = 2;
            } else if (i < 3) {
                state = i - 1;
            } else {
                state = i + 1;
            }

            bundle.putString(BundleConstant.getInstance().ORDER_STATE, state + "");
            bundle.putString(IntentUtils.getInstance().TYPE, repairId);
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
        }

        changeAdapter = new FragmentChangeAdapter(getSupportFragmentManager(), fragmentList);
        vpRepair.setAdapter(changeAdapter);
        vpRepair.setOffscreenPageLimit(3);

        vpRepair.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabRepair));

        tabRepair.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpRepair.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }
}
