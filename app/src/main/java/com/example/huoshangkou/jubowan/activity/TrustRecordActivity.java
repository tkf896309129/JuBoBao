package com.example.huoshangkou.jubowan.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.TrustRecordAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.TrustRecordBean;
import com.example.huoshangkou.jubowan.bean.UseRecordBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SmartUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：TrustRecordActivity
 * 描述：
 * 创建时间：2018-08-22  13:27
 */

public class TrustRecordActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.lv_trust_record)
    ListView lvTrustRecord;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.swip_refresh)
    SmartRefreshLayout swipeRefreshLayout;

    private int page = 1;
    TrustRecordAdapter recordAdapter;
    List<TrustRecordBean.ReObjBean> list = new ArrayList<>();

    @Override
    public int initLayout() {
        return R.layout.activity_trust_record;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("授信记录");
        recordAdapter = new TrustRecordAdapter(this, list, R.layout.item_sx_record);
        lvTrustRecord.setAdapter(recordAdapter);
        lvTrustRecord.setDividerHeight(0);

        getApplyRecord();
        swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getApplyRecord();
            }
        });
        swipeRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getApplyRecord();
            }
        });
    }

    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }


    //获取授信记录数据
    public void getApplyRecord() {
        OkhttpUtil.getInstance().setUnCacheData(this, "数据加载中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().APPLY_RECORD
                + FieldConstant.getInstance().PAGE_INDEX + "=" + page + "&"
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                TrustRecordBean trustRecordBean = JSON.parseObject(json, TrustRecordBean.class);
                list.clear();
                list.addAll(trustRecordBean.getReObj());
                recordAdapter.notifyDataSetChanged();
                SmartUtils.finishSmart(swipeRefreshLayout);
            }

            @Override
            public void onFail() {
                SmartUtils.finishSmart(swipeRefreshLayout);
            }
        });
    }

    @Override
    public void onRefresh() {
        getApplyRecord();
    }
}
