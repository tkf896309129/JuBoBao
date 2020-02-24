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
import com.example.huoshangkou.jubowan.fragment.MyRepairFragment;
import com.example.huoshangkou.jubowan.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：MyRepairActivity
 * 描述：
 * 创建时间：2017-08-08  14:07
 */

public class MyRepairActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.vp_my_repair)
    ViewPager vpMyRepair;
    @Bind(R.id.tab_my_repair)
    TabLayout tabMyRepair;

    FragmentChangeAdapter changeAdapter;
    List<Fragment> fragmentList;


    @Override
    public int initLayout() {
        return R.layout.activity_my_repair;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }


    @Override
    public void initData() {
        tvTitle.setText("我的维修");
        fragmentList = new ArrayList<>();

        tabMyRepair.addTab(tabMyRepair.newTab().setText("待维修"));
        tabMyRepair.addTab(tabMyRepair.newTab().setText("已维修"));

        Fragment fragmentLeft = MyRepairFragment.newInstance();
        Bundle bundleLeft = new Bundle();
        bundleLeft.putString(IntentUtils.getInstance().TYPE, "1");
        fragmentLeft.setArguments(bundleLeft);
        fragmentList.add(fragmentLeft);

        Fragment fragmentRight = MyRepairFragment.newInstance();
        Bundle bundleRight = new Bundle();
        bundleRight.putString(IntentUtils.getInstance().TYPE, "4");
        fragmentRight.setArguments(bundleRight);
        fragmentList.add(fragmentRight);

        vpMyRepair.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabMyRepair));

        changeAdapter = new FragmentChangeAdapter(getSupportFragmentManager(), fragmentList);
        vpMyRepair.setAdapter(changeAdapter);
        tabMyRepair.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpMyRepair.setCurrentItem(tab.getPosition());
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
