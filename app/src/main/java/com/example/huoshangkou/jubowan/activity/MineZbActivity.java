package com.example.huoshangkou.jubowan.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.fragment.MineZbFragment;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.finalteam.toolsfinal.adapter.FragmentAdapter;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：MineZbActivity
 * 描述：我的招标
 * 创建时间：2017-04-07  14:16
 */

public class MineZbActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tab_mine_zb)
    TabLayout tabMineZb;
    @Bind(R.id.vp_mine_zb)
    ViewPager vpMineZb;

    private FragmentAdapter fragmentAdapter;
    private List<Fragment> fragmentList;

    @Override
    public int initLayout() {
        return R.layout.activity_mine_zb;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        fragmentList = new ArrayList<>();

        //审核中
        MineZbFragment mineZbFragment_1 = MineZbFragment.newInstance();
        Bundle bundle_1 = new Bundle();
        bundle_1.putString(IntentUtils.getInstance().TYPE, "-1");
        mineZbFragment_1.setArguments(bundle_1);
        //招标中
        MineZbFragment mineZbFragment_2 = MineZbFragment.newInstance();
        Bundle bundle_2 = new Bundle();
        bundle_2.putString(IntentUtils.getInstance().TYPE, "0");
        mineZbFragment_2.setArguments(bundle_2);
        //已定标
        MineZbFragment mineZbFragment_3 = MineZbFragment.newInstance();
        Bundle bundle_3 = new Bundle();
        bundle_3.putString(IntentUtils.getInstance().TYPE, "1");
        mineZbFragment_3.setArguments(bundle_3);

        String roleType = PersonConstant.getInstance().getRoleType(this);

        //建筑商
        if(roleType.equals("1")){
            fragmentList.add(mineZbFragment_1);
            fragmentList.add(mineZbFragment_2);
            fragmentList.add(mineZbFragment_3);

            tabMineZb.addTab(tabMineZb.newTab().setText("审核中"));
            tabMineZb.addTab(tabMineZb.newTab().setText("招标中"));
            tabMineZb.addTab(tabMineZb.newTab().setText("已定标"));

            //加工厂
        }else  if(roleType.equals("2")){
            fragmentList.add(mineZbFragment_2);
            fragmentList.add(mineZbFragment_3);

            tabMineZb.addTab(tabMineZb.newTab().setText("已投标"));
            tabMineZb.addTab(tabMineZb.newTab().setText("已定标"));
        }

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        vpMineZb.setAdapter(fragmentAdapter);
        vpMineZb.setOffscreenPageLimit(2);
        tvTitle.setText("我的招标");

        vpMineZb.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabMineZb));
        tabMineZb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpMineZb.setCurrentItem(tab.getPosition());
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
