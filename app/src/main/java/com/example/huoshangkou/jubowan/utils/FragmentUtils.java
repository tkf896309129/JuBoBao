package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.LoginActivity;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：FragmentUtils
 * 描述：
 * 创建时间：2016-12-29  14:03
 */

public class FragmentUtils {
    //FragmentManager管理器
    private FragmentManager fragmentManager;
    //Fragment集合
    private List<Fragment> fragmentList;
    //布局控件
    private int containerId;
    //上下文
    private Context context;
    //菜单
    private RadioGroup radioGroup;
    //事务
    private FragmentTransaction fragmentTransaction;

    //维修维保
    private LinearLayout llRepair;
    //论坛
    private LinearLayout llForum;

    private static class FragmentHepler {
        private static FragmentUtils INSTANCE = new FragmentUtils();
    }

    public static FragmentUtils getInstance() {
        return FragmentHepler.INSTANCE;
    }

    public FragmentUtils() {

    }

    //, LinearLayout llRepair, LinearLayout llForum
    public FragmentUtils(FragmentManager fragmentManager, List<Fragment> fragmentList1, int containerId,
                         RadioGroup radioGroup, Context context) {
        this.fragmentManager = fragmentManager;
        this.fragmentList = fragmentList1;
        this.containerId = containerId;
        this.radioGroup = radioGroup;
        this.context = context;
//        this.llForum = llForum;
//        this.llRepair = llRepair;

        showFragment(0);
        RadioButton radioButton = (RadioButton) radioGroup.getChildAt(0);
        radioButton.setChecked(true);
    }

    /**
     * 显示对应的界面
     *
     * @param index
     */
    public void showFragment(int index) {
        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        fragment = fragmentList.get(index);
        if (!fragment.isAdded()) {
            fragmentTransaction.add(containerId, fragment);
        }
        for (int i = 0; i < fragmentList.size(); i++) {
            fragment = fragmentList.get(i);
            if (i == index) {
                fragmentTransaction.show(fragment);
            } else {
                fragmentTransaction.hide(fragment);
            }
        }
        fragmentTransaction.commit();
    }

    private int index = 0;

    /**
     * Home界面的点击事件
     */
//    public void getFragment() {
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId) {
//                    case R.id.rb_home:
//                        showFragment(0);
//                        index = 0;
//                        break;
//                    case R.id.rb_lun_tan:
//                        showFragment(1);
//                        index = 1;
//                        break;
//                    case R.id.rb_repair:
//                        showFragment(2);
//                        index = 2;
//                        break;
//                    case R.id.rb_order:
//                        RadioButton radioButton_3 = (RadioButton) radioGroup.getChildAt(3);
//                        RadioButton radioButton_check_3 = (RadioButton) radioGroup.getChildAt(index);
//                        if (!LoginUtils.getInstance().isLogin(context)) {
//                            IntentUtils.getInstance().toActivity(context, LoginActivity.class);
//                            radioButton_3.setChecked(false);
//                            radioButton_check_3.setChecked(true);
//                        } else {
//                            showFragment(3);
//                        }
//
//                        break;
//                    case R.id.rb_mine:
//
//                        RadioButton radioButton = (RadioButton) radioGroup.getChildAt(4);
//                        RadioButton radioButton_check = (RadioButton) radioGroup.getChildAt(index);
//                        if (!LoginUtils.getInstance().isLogin(context)) {
//                            IntentUtils.getInstance().toActivity(context, LoginActivity.class);
//                            radioButton.setChecked(false);
//                            radioButton_check.setChecked(true);
//                        } else {
//                            showFragment(4);
//                        }
//                        break;
//                }
//            }
//        });


//        llRepair.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showFragment(2);
//            }
//        });
//
//
//
//        llForum.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showFragment(1);
//            }
//        });

//    }
    //跳转至论坛
}
