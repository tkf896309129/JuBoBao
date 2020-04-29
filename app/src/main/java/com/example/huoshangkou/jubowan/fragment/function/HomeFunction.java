package com.example.huoshangkou.jubowan.fragment.function;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.JuBoNewActivity;
import com.example.huoshangkou.jubowan.activity.WebActivity;
import com.example.huoshangkou.jubowan.adapter.TradeAdapter;
import com.example.huoshangkou.jubowan.bean.HomeBean;
import com.example.huoshangkou.jubowan.bean.HomeImgListBean;
import com.example.huoshangkou.jubowan.bean.JumpBean;
import com.example.huoshangkou.jubowan.bean.NewHomeBean;
import com.example.huoshangkou.jubowan.bean.TradeBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.HomeDataCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.utils.WxUtils;
import com.example.huoshangkou.jubowan.view.CustomVRecyclerView;
import com.example.huoshangkou.jubowan.view.LocalImageHolderView;
import com.example.huoshangkou.jubowan.view.ScrollGridView;
import com.example.huoshangkou.jubowan.view.ScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment.function
 * 类名：HomeFunction
 * 描述：主界面的功能封装模块
 * 创建时间：2016-12-29  15:31
 */

public class HomeFunction {
    private static class HomeHelper {
        private static HomeFunction INSTANCE = new HomeFunction();
    }

    public static HomeFunction getInstance() {
        return HomeHelper.INSTANCE;
    }

    int scrollY = 0;

    /**
     * 头部渐变过程
     */
    public void changeTopColor(RecyclerView appBarLayout, final RelativeLayout rlTooBar, final TextView tvTitle, Context context) {
        scrollY = 0;
        tvTitle.setTextColor(Color.argb(0, 255, 255, 255));
        appBarLayout.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                LogUtils.e(dx + "  --  " + dy ); 及你太美
                scrollY += dy;
                LogUtils.e(scrollY + "" + dy);
                //                //背景渐变
                double i = (float) scrollY / 400;
                LogUtils.i(i + "");
                //当透明度小于200的时候 渐变
                if (i > 0.1 && i < 1) {
                    rlTooBar.getBackground().setAlpha((int) (i * 255));
                    tvTitle.setTextColor(Color.argb((int) (i * 255), 255, 255, 255));
                } else if (i < 0.1) {
                    rlTooBar.getBackground().setAlpha(0);
                    tvTitle.setTextColor(Color.argb(0, 255, 255, 255));
                } else {
                    //透明度大于200的时候直接不透明
                    rlTooBar.getBackground().setAlpha(255);
                    tvTitle.setTextColor(Color.argb(255, 255, 255, 255));
                }
            }
        });
    }

    /**
     * 加载Banner 图
     *
     * @param banner
     */
    public void addBannerPic(final Context context, ConvenientBanner banner, final List<NewHomeBean.DBean.ResultBean.BannersBean> HomePageList) {
        if (HomePageList == null) {
            return;
        }
        List<String> imgList = new ArrayList<>();
        int num = HomePageList.size();
        for (int i = 0; i < num; i++) {
            imgList.add(HomePageList.get(i).getImage());
        }
        banner.startTurning(3000);
        banner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        }, imgList)
                .setPageIndicator(new int[]{R.mipmap.gray_dot, R.mipmap.white_dot})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);

        //banner图的点击事件
        banner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (HomePageList.get(position).getName().equals("抢红包")) {
                    IntentUtils.getInstance().toActivity(context, JuBoNewActivity.class);
                    return;
                }
                //1 app跳转 2 小程序跳转  3网页跳转
                switch (HomePageList.get(position).getJumpType()) {
                    case 1:
                        String json = HomePageList.get(position).getJumpValue();
                        JumpBean jumpBean = JSON.parseObject(json, JumpBean.class);
                        WxUtils.getInstance().jumpApp(context, jumpBean.getPointName(), jumpBean.getPointActivity(), jumpBean.getDownUrl(), jumpBean.getAppName());
                        break;
                    case 2:
                        String value = HomePageList.get(position).getJumpValue();
                        WxUtils.getInstance().toWxSmallPro(context, value);
                        break;
                    case 3:
                        IntentUtils.getInstance().toWebActivity(context, HomePageList.get(position).getLink(), HomePageList.get(position).getName());
                        break;
                    case 4:

                        String jsonActivity = HomePageList.get(position).getJumpValue();
                        JumpBean jumpBeanClass = JSON.parseObject(jsonActivity, JumpBean.class);
                        if (!StringUtils.isNoEmpty(jumpBeanClass.getAndroid_category())) {
                            ToastUtils.getMineToast("路径为空");
                            return;
                        }
                        Intent intent = new Intent();
                        intent.setAction(jumpBeanClass.getAndroid_class());
                        intent.addCategory(jumpBeanClass.getAndroid_category());
                        context.startActivity(intent);
                        break;
                }
            }
        });
    }

    public void addBannerPic(final Context context, ConvenientBanner banner, final List<String> imgList,
                             final List<HomeImgListBean> HomePageList) {
        banner.startTurning(3000);
        banner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        }, imgList)
                .setPageIndicator(new int[]{R.mipmap.gray_dot, R.mipmap.white_dot})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);

        //banner图的点击事件
        banner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (HomePageList.get(position).getTitle().equals("抢红包")) {
                    IntentUtils.getInstance().toActivity(context, JuBoNewActivity.class);
                    return;
                }
                IntentUtils.getInstance().toWebActivity(context, HomePageList.get(position).getUrl(), HomePageList.get(position).getTitle());

            }
        });
    }
}

