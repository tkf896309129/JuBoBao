package com.example.huoshangkou.jubowan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：FragmentChangeAdapter
 * 描述：Fragment切换的适配器
 * 创建时间：2017-01-03  10:01
 */

public class FragmentChangeAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragmentList;

    public FragmentChangeAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
