package com.example.huoshangkou.jubowan.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.fragment.AreaToWeightFragment;
import com.example.huoshangkou.jubowan.fragment.BoLiPriceFragment;
import com.example.huoshangkou.jubowan.fragment.CalculatePriceFragment;
import com.example.huoshangkou.jubowan.fragment.WeightToAreaFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.finalteam.toolsfinal.adapter.FragmentAdapter;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：CalculatorActivity
 * 描述：
 * 创建时间：2017-04-07  10:34
 */

public class CalculatorActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tab_calculator)
    TabLayout tabCalculator;
    @Bind(R.id.vp_calculator)
    ViewPager vpCalculator;

    private FragmentAdapter fragmentAdapter;
    private List<Fragment> fragmentList;

    @Override
    public int initLayout() {
        return R.layout.activity_calculator;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("转换工具");
        fragmentList = new ArrayList<>();
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);

        fragmentList.add(WeightToAreaFragment.newInstance());
        fragmentList.add(AreaToWeightFragment.newInstance());
        fragmentList.add(CalculatePriceFragment.newInstance());
        fragmentList.add(BoLiPriceFragment.newInstance());

        tabCalculator.addTab(tabCalculator.newTab().setText("重量转面积"));
        tabCalculator.addTab(tabCalculator.newTab().setText("面积转重量"));
        tabCalculator.addTab(tabCalculator.newTab().setText("价格计算"));
        tabCalculator.addTab(tabCalculator.newTab().setText("建筑玻璃核价"));

        tabCalculator.setTabMode(TabLayout.MODE_SCROLLABLE);

        vpCalculator.setAdapter(fragmentAdapter);
        vpCalculator.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabCalculator));

        tabCalculator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpCalculator.setCurrentItem(tab.getPosition());
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
