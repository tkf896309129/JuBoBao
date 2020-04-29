package com.example.huoshangkou.jubowan.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.UserRecordAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.UseRecordBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SmartUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
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
 * 类名：UseRecordActivity
 * 描述：借款记录
 * 创建时间：2018-08-21  15:19
 */

public class UseRecordActivity extends BaseActivity  {
    @Bind(R.id.lv_use_record)
    ListView lvUseRecord;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.swip_record)
    SmartRefreshLayout swipeRefreshLayout;

    UserRecordAdapter recordAdapter;
    List<UseRecordBean.ReListBean> list;
    private int page = 1;

    @Override
    public int initLayout() {
        return R.layout.activiy_use_record;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        list = new ArrayList<>();
        recordAdapter = new UserRecordAdapter(this, list, R.layout.item_use_record);
        lvUseRecord.setAdapter(recordAdapter);
        lvUseRecord.setDividerHeight(0);

        tvTitle.setText("使用记录");
        lvUseRecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentUtils.getInstance().toActivity(UseRecordActivity.this, BtLoanDetailActivity.class, list.get(i).getID() + "");
            }
        });
        getApplyRecord();

        swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
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


    //获取借款记录数据
    public void getApplyRecord() {
        OkhttpUtil.getInstance().setUnCacheData(this, "数据加载中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().BORROWING_RECORD
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().PAGE_INDEX + "=" + page, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                if(page ==1){
                    list.clear();
                }
                UseRecordBean recordBean = JSON.parseObject(json, UseRecordBean.class);
                list.addAll(recordBean.getReList());
                recordAdapter.notifyDataSetChanged();
                SmartUtils.finishSmart(swipeRefreshLayout);
            }

            @Override
            public void onFail() {
                SmartUtils.finishSmart(swipeRefreshLayout);
            }
        });
    }
}
