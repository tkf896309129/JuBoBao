package com.example.huoshangkou.jubowan.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;

import butterknife.Bind;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：RepairOrderActivity
 * 描述：
 * 创建时间：2017-04-21  09:03
 */

public class RepairOrderActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tab_repair)
    TabLayout tabRepair;
    @Bind(R.id.vp_repair)
    ViewPager vpRepair;

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

    }


}
