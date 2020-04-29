package com.example.huoshangkou.jubowan.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.FragmentChangeAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.fragment.CustomerDataFragment;
import com.example.huoshangkou.jubowan.fragment.CustomerDateFragment;
import com.example.huoshangkou.jubowan.fragment.CustomerFragment;
import com.example.huoshangkou.jubowan.fragment.ForumFragment;
import com.example.huoshangkou.jubowan.fragment.MineFragment;
import com.example.huoshangkou.jubowan.fragment.OrderFragment;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.view.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：CustomerManagerActivity
 * 描述：
 * 创建时间：2019-08-21  15:11
 */

public class CustomerManagerActivity extends BaseActivity {
    @Bind(R.id.vp_main)
    CustomViewPager viewPager;
    @Bind(R.id.rg_customer_manager)
    RadioGroup rgCustomer;
    @Bind(R.id.rb_date)
    RadioButton rbDate;

    FragmentManager fragmentManager;
    FragmentChangeAdapter fragmentChangeAdapter;
    //Fragment集合
    List<Fragment> fragmentList;

    private String id = "";
    private String depId = "";
    private String depName = "";
    private String phone = "";
    private String type = "";
    private String roleId = "";
    //是否是超管
    private boolean isSuper = false;

    @Override
    public int initLayout() {
        return R.layout.activity_customer_manager;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        id = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        phone = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        depId = getIntent().getStringExtra(IntentUtils.getInstance().STR);
        depName = getIntent().getStringExtra(IntentUtils.getInstance().TXT);
        type = getIntent().getStringExtra(IntentUtils.getInstance().ROLE_MANAGE_TYPE);
        roleId = getIntent().getStringExtra(IntentUtils.getInstance().ROLE_MANAGE_ID);

        //保存销售员userid
        SharedPreferencesUtils.getInstance().put(this, SharedKeyConstant.getInstance().USER_MANAGE_ID, StringUtils.getNoNullStr(id));
        SharedPreferencesUtils.getInstance().put(this, SharedKeyConstant.getInstance().USER_MANAGE_PHONE_ID, StringUtils.getNoNullStr(phone));
        fragmentManager = getSupportFragmentManager();
        fragmentList = new ArrayList<>();
        CustomerDateFragment customerDateFragment = CustomerDateFragment.newInstance();
        Bundle bundleDate = new Bundle();
        bundleDate.putString(IntentUtils.getInstance().ROLE_MANAGE_TYPE, type);
        bundleDate.putString(IntentUtils.getInstance().ROLE_MANAGE_ID, roleId);

        customerDateFragment.setArguments(bundleDate);
        fragmentList.add(customerDateFragment);
//        if (!(StringUtils.isNoEmpty(type) && type.equals("3"))) {
//        } else {
//            isSuper = true;
//            rbDate.setVisibility(View.GONE);
//        }
        CustomerDataFragment customerDataFragment = CustomerDataFragment.newInstance();
        Bundle bundleData = new Bundle();
        bundleData.putString(IntentUtils.getInstance().ROLE_MANAGE_TYPE, type);
        bundleData.putString(IntentUtils.getInstance().ROLE_MANAGE_ID, roleId);
        bundleData.putString(IntentUtils.getInstance().TYPE, depId);
        bundleData.putString(IntentUtils.getInstance().VALUE, depName);
        customerDataFragment.setArguments(bundleData);
        fragmentList.add(customerDataFragment);
        CustomerFragment customerFragment = CustomerFragment.newInstance();
        Bundle bundleCustomer = new Bundle();
        bundleCustomer.putString(IntentUtils.getInstance().ROLE_MANAGE_TYPE, type);
        bundleCustomer.putString(IntentUtils.getInstance().ROLE_MANAGE_ID, roleId);
        customerFragment.setArguments(bundleCustomer);
        fragmentList.add(customerFragment);
        fragmentChangeAdapter = new FragmentChangeAdapter(fragmentManager, fragmentList);
        viewPager.setAdapter(fragmentChangeAdapter);
        viewPager.setOffscreenPageLimit(5);
//        if (isSuper) {
//            ((RadioButton) rgCustomer.getChildAt(1)).setChecked(true);
//        } else {
            ((RadioButton) rgCustomer.getChildAt(0)).setChecked(true);
//        }
        rgCustomer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_date:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_data:
//                        if (isSuper) {
//                            viewPager.setCurrentItem(0);
//                            return;
//                        }
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_customer:
//                        if (isSuper) {
//                            viewPager.setCurrentItem(1);
//                            return;
//                        }
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferencesUtils.getInstance().put(this, SharedKeyConstant.getInstance().USER_MANAGE_ID, "");
        SharedPreferencesUtils.getInstance().put(this, SharedKeyConstant.getInstance().USER_MANAGE_PHONE_ID, "");
    }
}
