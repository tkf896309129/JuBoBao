package com.example.huoshangkou.jubowan.fragment.function;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.example.huoshangkou.jubowan.adapter.FragmentChangeAdapter;
import com.example.huoshangkou.jubowan.fragment.ForumListFragment;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment.function
 * 类名：ForumFunction
 * 描述：论坛界面功能模块封装 手长在你自己身上
 * 创建时间：2017-01-03  13:08
 */

public class ForumFunction {

    /**
     * 维修维保专区【hot】 9
     * 原片交流专区 1
     * 辅料交流专区 2
     * 物流送货哪家强 3
     * 时事新闻 4
     * 搞笑内涵 5
     * 灌水吐槽 6
     * 生活那些事儿 7
     * 获取论坛数据
     */
    //切换论坛模块界面
    public static void changeForumPages(TabLayout tabForum, final ViewPager vpForum, FragmentManager fragmentManager, final OnPositionClick positionClick) {

        //论坛上面标题的分类
//        tabForum.addTab(tabForum.newTab().setText("聚玻早报"));
        tabForum.addTab(tabForum.newTab().setText("最新帖子"));
        tabForum.addTab(tabForum.newTab().setText("最热帖子"));
        tabForum.addTab(tabForum.newTab().setText("维修专区"));

        tabForum.addTab(tabForum.newTab().setText("原片专区"));
        tabForum.addTab(tabForum.newTab().setText("辅料专区"));
        tabForum.addTab(tabForum.newTab().setText("物流专区"));
        tabForum.addTab(tabForum.newTab().setText("时事新闻"));
        tabForum.addTab(tabForum.newTab().setText("搞笑内涵"));
        tabForum.addTab(tabForum.newTab().setText("热门话题"));
        tabForum.addTab(tabForum.newTab().setText("秀一秀"));

        tabForum.setTabMode(TabLayout.MODE_SCROLLABLE);
        //绑定ViewPager
        vpForum.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabForum));

        vpForum.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setBackPosition(position, positionClick);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabForum.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpForum.setCurrentItem(tab.getPosition(), false);
                setBackPosition(tab.getPosition(), positionClick);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        //初始化Fragment数据
        List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            ForumListFragment forumListFragment = ForumListFragment.newInstance();
            String index = "";
            //聚玻早报
//            if (i == 0) {
//                index = "11";
//            } else
            if (i == 0) {
                index = "8";
                //最热
            } else if (i == 1) {
                index = "10";
                //维修维保
            } else if (i == 2) {
                index = "9";
            } else {
                //其他
                index = (i - 2) + "";
            }
            Bundle bundle = new Bundle();
            bundle.putString(IntentUtils.getInstance().TYPE, index);
            forumListFragment.setArguments(bundle);
            fragmentList.add(forumListFragment);
        }

        FragmentChangeAdapter changeAdapter = new FragmentChangeAdapter(fragmentManager, fragmentList);

        vpForum.setAdapter(changeAdapter);
//        vpForum.setOffscreenPageLimit(10);
    }

    //设置返回数据
    public static void setBackPosition(int position, OnPositionClick positionClick) {
        int i = position;
        String index = "8";
        //最新
        if (i == 0) {
            index = "8";
            //最热
        } else if (i == 1) {
            index = "10";
            //维修维保
        } else if (i == 2) {
            index = "9";
        } else {
            //其他
            index = (i - 2) + "";
        }
        positionClick.onPositionClick(Integer.parseInt(index));
    }
}
