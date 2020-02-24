package com.example.huoshangkou.jubowan.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.fragment.ForumListFragment;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.toolsfinal.adapter.FragmentAdapter;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：TieManagerActivity
 * 描述：论坛管理
 * 创建时间：2017-04-11  08:46
 */

public class TieManagerActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tab_tie_manager)
    TabLayout tabTieManager;
    @Bind(R.id.vp_tie_manager)
    ViewPager vpTieManager;

    private List<Fragment> fragmentList;
    private FragmentAdapter fragmentAdapter;

    @Override
    public int initLayout() {
        return R.layout.activity_tie_manager;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("论坛管理");

        tabTieManager.addTab(tabTieManager.newTab().setText("待审帖子"));
        tabTieManager.addTab(tabTieManager.newTab().setText("待审回帖"));
        tabTieManager.addTab(tabTieManager.newTab().setText("锁定中"));


        fragmentList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            ForumListFragment forumFragment = ForumListFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putString(IntentUtils.getInstance().CHECK_TYPE, i + "");
            forumFragment.setArguments(bundle);
            fragmentList.add(forumFragment);
        }


        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        vpTieManager.setAdapter(fragmentAdapter);

        vpTieManager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabTieManager));
        tabTieManager.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpTieManager.setCurrentItem(tab.getPosition());
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

//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        String isInit = (String) SharedPreferencesUtils.getInstance().get(getContext(), SharedKeyConstant.getInstance().PASS_CHECK, "");
//        if (!StringUtils.isNoEmpty(isInit)) {
//            return;
//        }
//        TieManagerActivity.this.finish();
//        SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().PASS_CHECK, "");
//    }
}
