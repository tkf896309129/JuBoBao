package com.example.huoshangkou.jubowan.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.constant.SignConstant;
import com.example.huoshangkou.jubowan.fragment.CheckSignFragment;
import com.example.huoshangkou.jubowan.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.toolsfinal.adapter.FragmentAdapter;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：CheckSignActivity
 * 描述：补签审核
 * 创建时间：2017-04-18  16:18
 */

public class CheckSignActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tab_check_sign)
    TabLayout tabCheckSign;
    @Bind(R.id.vp_check_sign)
    ViewPager vpCheckSign;

    private String approveId = "";

    private FragmentAdapter fragmentAdapter;
    private List<Fragment> fragmentList;

    @Override
    public int initLayout() {
        return R.layout.activity_check_sign;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("我审核的补签");
        fragmentList = new ArrayList<>();

        approveId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        Fragment fragmentUnCheck = CheckSignFragment.newInstance();
        Bundle bundleUnCheck = new Bundle();
        bundleUnCheck.putString(IntentUtils.getInstance().VALUE, "-1");
        bundleUnCheck.putString(IntentUtils.getInstance().APPROVE_TYPE_ID, approveId);
        bundleUnCheck.putString(IntentUtils.getInstance().TYPE, SignConstant.getInstance().UN_CHECK_SIGN);
        fragmentUnCheck.setArguments(bundleUnCheck);

        Fragment fragmentCheck = CheckSignFragment.newInstance();
        Bundle bunCheck = new Bundle();
        bunCheck.putString(IntentUtils.getInstance().VALUE, "1");
        bunCheck.putString(IntentUtils.getInstance().APPROVE_TYPE_ID, approveId);
        bunCheck.putString(IntentUtils.getInstance().TYPE, SignConstant.getInstance().CHECK_SIGN);
        fragmentCheck.setArguments(bunCheck);

        fragmentList.add(fragmentUnCheck);
        fragmentList.add(fragmentCheck);

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        vpCheckSign.setAdapter(fragmentAdapter);

        tabCheckSign.addTab(tabCheckSign.newTab().setText("待审核"));
        tabCheckSign.addTab(tabCheckSign.newTab().setText("已审核"));


        vpCheckSign.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabCheckSign));
        tabCheckSign.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpCheckSign.setCurrentItem(tab.getPosition());
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
