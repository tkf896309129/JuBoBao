package com.example.huoshangkou.jubowan.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.ServiceHotAdapter;
import com.example.huoshangkou.jubowan.adapter.ServiceSecondAdapter;
import com.example.huoshangkou.jubowan.adapter.ServiceSellerAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.JuServiceBean;
import com.example.huoshangkou.jubowan.bean.ServiceFactoryListBean;
import com.example.huoshangkou.jubowan.bean.ServicePjListBean;
import com.example.huoshangkou.jubowan.bean.ServiceSheBeiListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.fragment.function.HomeFunction;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
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
 * 类名：JuServiceActivity
 * 描述：
 * 创建时间：2018-06-12  11:08
 */

public class JuServiceActivity extends BaseActivity {
    @Bind(R.id.cb_ju_service)
    ConvenientBanner convenientBanner;
    @Bind(R.id.recycler_second)
    RecyclerView recyclerViewSecond;
    @Bind(R.id.recycler_hot)
    RecyclerView recyclerViewHot;
    @Bind(R.id.recycler_good_seller)
    RecyclerView recyclerViewSeller;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    ServiceSecondAdapter serviceSecondAdapter;
    ServiceHotAdapter hotAdapter;
    ServiceSellerAdapter sellerAdapter;

    //Banner图集合
    List<String> imgList;

    private List<ServicePjListBean> peiJianList;
    private List<ServiceSheBeiListBean> sheBeiList;
    private List<ServiceFactoryListBean> factoryList;

    @Override
    public int initLayout() {
        return R.layout.activity_ju_service;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("聚服务");

        imgList = new ArrayList<>();
        factoryList = new ArrayList<>();
        peiJianList = new ArrayList<>();
        sheBeiList = new ArrayList<>();

        serviceSecondAdapter = new ServiceSecondAdapter(sheBeiList, this);
        recyclerViewSecond.setAdapter(serviceSecondAdapter);
        recyclerViewSecond.setLayoutManager(getLayoutManager());
        serviceSecondAdapter.setPositionClick(new OnPositionClick() {
            @Override
            public void onPositionClick(int position) {
                IntentUtils.getInstance().toActivity(getContext(), ToolDetailsActivity.class, UrlConstant.getInstance().TOOL_UEL + sheBeiList.get(position).getID(), sheBeiList.get(position).getID() + "");
            }
        });

        hotAdapter = new ServiceHotAdapter(peiJianList, this);
        recyclerViewHot.setAdapter(hotAdapter);
        recyclerViewHot.setLayoutManager(getLayoutManager());
        hotAdapter.setPositionClick(new OnPositionClick() {
            @Override
            public void onPositionClick(int i) {
                IntentUtils.getInstance().toActivity(getContext(), PjDetailsActivity.class, peiJianList.get(i).getClassID(), peiJianList.get(i).getModelID() + "");
            }
        });

        sellerAdapter = new ServiceSellerAdapter(factoryList, this);
        recyclerViewSeller.setAdapter(sellerAdapter);
        recyclerViewSeller.setLayoutManager(getLayoutManager());
        sellerAdapter.setPositionClick(new OnPositionClick() {
            @Override
            public void onPositionClick(int i) {
//                IntentUtils.getInstance().toActivity(getContext(), PjDetailsActivity.class, peiJianList.get(i).getClassID(), peiJianList.get(i).getID() + "");
            IntentUtils.getInstance().toWebActivity(JuServiceActivity.this,factoryList.get(i).getDetailsUrl(),factoryList.get(i).getName());
            }
        });

        getServiceIndex();
    }

    @OnClick({R.id.ll_back, R.id.tv_repair, R.id.tv_find_pj, R.id.tv_find_tool})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_repair:
                IntentUtils.getInstance().toActivity(getContext(), RepairActivity.class);
                break;
            case R.id.tv_find_pj:
                IntentUtils.getInstance().toActivity(getContext(), BuyPjActivity.class);
                break;
            case R.id.tv_find_tool:
                IntentUtils.getInstance().toActivity(getContext(), BuyToolActivity.class);
                break;
            case R.id.ll_back:
                this.finish();
                break;
        }
    }

    private StaggeredGridLayoutManager getLayoutManager() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL);
        return layoutManager;
    }

    //获取首页数据
    public void getServiceIndex() {
        OkhttpUtil.getInstance().setUnCacheData(this, "数据加载中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().SERVE_INDEX
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                JuServiceBean serviceBean = JSON.parseObject(json, JuServiceBean.class);
                int num = serviceBean.getHomePageList().size();
                for (int i = 0; i < num; i++) {
                    imgList.add(serviceBean.getHomePageList().get(i).getImageUrl());
                }
                HomeFunction.getInstance().addBannerPic(getContext(), convenientBanner, imgList, serviceBean.getHomePageList());
                peiJianList.addAll(serviceBean.getPeiJianList());
                sheBeiList.addAll(serviceBean.getSheBeiList());
                factoryList.addAll(serviceBean.getFactoryList());
                sellerAdapter.notifyDataSetChanged();
                hotAdapter.notifyDataSetChanged();
                serviceSecondAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
    }
}
