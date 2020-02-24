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
import com.example.huoshangkou.jubowan.constant.TieDetailConstant;
import com.example.huoshangkou.jubowan.fragment.AboutMeFragment;
import com.example.huoshangkou.jubowan.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：MineBackTieActivity
 * 描述：与我相关
 * 创建时间：2017-04-10  14:34
 */

public class MineBackTieActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tab_about_me)
    TabLayout tabAboutMe;
    @Bind(R.id.vp_about_me)
    ViewPager vpAboutMe;

    private FragmentChangeAdapter fragmentChangeAdapter;
    private List<Fragment> fragmentList;

    @Override
    public int initLayout() {
        return R.layout.activity_back_tie;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("与我相关");

        tabAboutMe.addTab(tabAboutMe.newTab().setText("我的评论"));
        tabAboutMe.addTab(tabAboutMe.newTab().setText("回复我的"));
        fragmentList = new ArrayList<>();
        Fragment fragmentCommon = AboutMeFragment.newInstance();
        Bundle bundleCommon = new Bundle();
        bundleCommon.putString(IntentUtils.getInstance().TYPE, TieDetailConstant.getInstance().MINE_COMMON);
        fragmentCommon.setArguments(bundleCommon);

        fragmentList.add(fragmentCommon);

        Fragment fragmentRepick = AboutMeFragment.newInstance();
        Bundle bundleRepick = new Bundle();
        bundleRepick.putString(IntentUtils.getInstance().TYPE, TieDetailConstant.getInstance().BACK_MINE);
        fragmentRepick.setArguments(bundleRepick);
        fragmentList.add(fragmentRepick);

        fragmentChangeAdapter = new FragmentChangeAdapter(getSupportFragmentManager(), fragmentList);
        vpAboutMe.setAdapter(fragmentChangeAdapter);

        vpAboutMe.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabAboutMe));

        tabAboutMe.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpAboutMe.setCurrentItem(tab.getPosition());
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
