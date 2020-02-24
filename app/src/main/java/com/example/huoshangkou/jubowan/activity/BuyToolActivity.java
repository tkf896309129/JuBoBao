package com.example.huoshangkou.jubowan.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.FragmentChangeAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ToolBean;
import com.example.huoshangkou.jubowan.bean.ToolNewClassBean;
import com.example.huoshangkou.jubowan.bean.ToolOldClassBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.fragment.NewToolFragment;
import com.example.huoshangkou.jubowan.fragment.OldToolFragment;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：BuyToolActivity
 * 描述：
 * 创建时间：2018-06-07  14:10
 */

public class BuyToolActivity extends BaseActivity {
    @Bind(R.id.tab_buy_tool)
    TabLayout tabLayout;
    @Bind(R.id.vp_buy_tool)
    ViewPager vpBuyTool;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    List<Fragment> fragmentList;
    FragmentChangeAdapter fragmentChangeAdapter;

    private ArrayList<ToolNewClassBean> newClass;
    private ArrayList<ToolNewClassBean> oldClass;

    @Override
    public int initLayout() {
        return R.layout.activity_buy_tool;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("找设备");
        tabLayout.addTab(tabLayout.newTab().setText("新设备"));
        tabLayout.addTab(tabLayout.newTab().setText("二手设备"));
        newClass = new ArrayList<>();
        oldClass = new ArrayList<>();
        fragmentList = new ArrayList<>();

        //绑定ViewPager
        vpBuyTool.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpBuyTool.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        getToolData();
    }

    //获取设备数据
    public void getToolData() {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(this,  UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_MAINTAIN_ALL_CLASS
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                ToolBean toolBean = JSON.parseObject(json, ToolBean.class);
                newClass.addAll(toolBean.getReObj().getNewClass());
                oldClass.addAll(toolBean.getReObj().getOldClass());
                addFragment();
            }

            @Override
            public void onFail() {
                addFragment();
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

    //添加数据
    public void addFragment() {
        Fragment fragmentNew = NewToolFragment.newInstance();
        Bundle bundleNew = new Bundle();
        bundleNew.putParcelableArrayList(IntentUtils.getInstance().LIST, newClass);
        fragmentNew.setArguments(bundleNew);
        fragmentList.add(fragmentNew);

        Fragment fragmentOld = OldToolFragment.newInstance();
        Bundle bundleOld = new Bundle();
        bundleOld.putParcelableArrayList(IntentUtils.getInstance().LIST, oldClass);
        fragmentOld.setArguments(bundleOld);
        fragmentList.add(fragmentOld);

        fragmentChangeAdapter = new FragmentChangeAdapter(getSupportFragmentManager(), fragmentList);
        vpBuyTool.setAdapter(fragmentChangeAdapter);
    }
}
