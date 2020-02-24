package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.PhotoViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.finalteam.galleryfinal.widget.zoonview.PhotoView;
import cn.finalteam.galleryfinal.widget.zoonview.PhotoViewAttacher;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：ImageShowActivity
 * 描述：图片轮播展示界面
 * 创建时间：2017-01-05  08:44
 */

public class ImageShowActivity extends BaseActivity {
    @Bind(R.id.vp_img)
    PhotoViewPager viewPager;
    @Bind(R.id.tv_pages)
    TextView tvPages;

    //图片集合
    private List<String> list;
    //传过来的图片当前的下标
    private int position;

    @Override
    public int initLayout() {
        return R.layout.activity_image_show;
    }

    @Override
    protected boolean setIsFull() {
        return true;
    }

    @Override
    public void initData() {
        list = new ArrayList<>();
        Intent intent = getIntent();
        //获取到图片集合
        list.addAll(intent.getStringArrayListExtra("imgList"));

        //获取到点击图片的下标
        position = intent.getIntExtra("position", 0);
        //显示当前点击的图片的下标
        tvPages.setText((position + 1) + "/" + list.size());
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                try {
                    final PhotoView view = new PhotoView(ImageShowActivity.this);
                    view.setAdjustViewBounds(true);
                    view.canZoom();
                    Glide.with(ImageShowActivity.this).load(list.get(position)).placeholder(R.mipmap.default_img).
                            fitCenter().into(view);
                    //点击图片结束掉该界面
                    view.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                        @Override
                        public void onPhotoTap(View view, float x, float y) {
                            ImageShowActivity.this.finish();
                        }
                    });
                    container.addView(view);
                    return view;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        //显示当前点击的那张图片
        viewPager.setCurrentItem(position);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int positions, float positionOffset, int positionOffsetPixels) {
                tvPages.setText((positions + 1) + "/" + list.size());
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
