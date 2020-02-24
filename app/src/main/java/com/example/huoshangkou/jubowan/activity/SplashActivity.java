package com.example.huoshangkou.jubowan.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.SplashAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：SplashActivity
 * 描述：引导页界面
 * 创建时间：2017-05-03  13:10
 */

public class SplashActivity extends BaseActivity {

    @Bind(R.id.vp_splash)
    ViewPager vpSplash;

    SplashAdapter splashAdapter;
    private int[] imgList = {R.mipmap.page_1, R.mipmap.page_2, R.mipmap.page_3};


    @Override
    public int initLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected boolean setIsFull() {
        return true;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().activityLoginList);

        splashAdapter = new SplashAdapter(imgList, getContext());
        vpSplash.setAdapter(splashAdapter);

        vpSplash.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
