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
import com.example.huoshangkou.jubowan.bean.RepairTypeListBean;
import com.example.huoshangkou.jubowan.bean.ServiceRepairBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.fragment.RepairToolFragment;
import com.example.huoshangkou.jubowan.fragment.RepariGzFragment;
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
 * 类名：RepairActivity
 * 描述：
 * 创建时间：2018-06-08  08:12
 */

public class RepairActivity extends BaseActivity {
    @Override
    public int initLayout() {
        return R.layout.activity_repair;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Bind(R.id.tab_repair)
    TabLayout tabLayout;
    @Bind(R.id.vp_repair)
    ViewPager vpBuyTool;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    List<Fragment> fragmentList;
    FragmentChangeAdapter fragmentChangeAdapter;

    private ArrayList<RepairTypeListBean> wBList;
    private ArrayList<RepairTypeListBean> wXList;

    @Override
    public void initData() {
        tvTitle.setText("维修维保");
        tabLayout.addTab(tabLayout.newTab().setText("设备维修"));
        tabLayout.addTab(tabLayout.newTab().setText("设备升级改造搬迁"));

        fragmentList = new ArrayList<>();
        wBList = new ArrayList<>();
        wXList = new ArrayList<>();


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


        getRepairData();
    }


    //获取设备数据
    public void getRepairData() {
        OkhttpUtil.getInstance().setUnCacheData(this, "数据加载中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_REPAIR_INDEX
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                ServiceRepairBean toolBean = JSON.parseObject(json, ServiceRepairBean.class);
                wBList.addAll(toolBean.getReObj().getWBList());
                wXList.addAll(toolBean.getReObj().getWXList());
                addFragment();
            }

            @Override
            public void onFail() {
                addFragment();
            }
        });
    }


    //添加数据
    public void addFragment() {
        Fragment fragmentRepair = RepairToolFragment.newInstance();
        Bundle bundleOld = new Bundle();
        bundleOld.putParcelableArrayList(IntentUtils.getInstance().LIST, wXList);
        fragmentRepair.setArguments(bundleOld);
        fragmentList.add(fragmentRepair);

        Fragment fragmentGz = RepariGzFragment.newInstance();
        Bundle bundleNew = new Bundle();
        bundleNew.putParcelableArrayList(IntentUtils.getInstance().LIST, wBList);
        fragmentGz.setArguments(bundleNew);
        fragmentList.add(fragmentGz);

        fragmentChangeAdapter = new FragmentChangeAdapter(getSupportFragmentManager(), fragmentList);
        vpBuyTool.setAdapter(fragmentChangeAdapter);
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
