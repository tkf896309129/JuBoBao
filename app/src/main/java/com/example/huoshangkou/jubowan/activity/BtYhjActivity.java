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
import com.example.huoshangkou.jubowan.fragment.BtYhjFragment;
import com.example.huoshangkou.jubowan.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：BtYhjActivity
 * 描述：
 * 创建时间：2018-12-10  08:46
 */

public class BtYhjActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tab_bt_yhj)
    TabLayout tabBtYhj;
    @Bind(R.id.vp_bt_yhj)
    ViewPager vpBtYhj;

    private FragmentChangeAdapter fragmentChangeAdapter;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public int initLayout() {
        return R.layout.activity_bt_yhj;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("优惠券");
        BtYhjFragment btYhjFragment = BtYhjFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString(IntentUtils.getInstance().STATE, "0");
        btYhjFragment.setArguments(bundle);
        BtYhjFragment btYhjFragment2 = BtYhjFragment.newInstance();
        Bundle bundle2 = new Bundle();
        bundle2.putString(IntentUtils.getInstance().STATE, "2");
        btYhjFragment2.setArguments(bundle2);
        fragmentList.add(btYhjFragment);
        fragmentList.add(btYhjFragment2);
        fragmentChangeAdapter = new FragmentChangeAdapter(getSupportFragmentManager(), fragmentList);
        vpBtYhj.setAdapter(fragmentChangeAdapter);

        tabBtYhj.addTab(tabBtYhj.newTab().setText("未使用"));
        tabBtYhj.addTab(tabBtYhj.newTab().setText("已过期"));
        tabBtYhj.setTabMode(TabLayout.MODE_FIXED);

        //绑定ViewPager
        vpBtYhj.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabBtYhj));

        vpBtYhj.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabBtYhj.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpBtYhj.setCurrentItem(tab.getPosition(), false);
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
