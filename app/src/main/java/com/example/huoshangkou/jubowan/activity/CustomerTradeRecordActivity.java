package com.example.huoshangkou.jubowan.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.CustomerTradeAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.CustomerVisitorBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SmartUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：CustomerTradeRecordActivity
 * 描述：
 * 创建时间：2019-08-29  13:48
 */

public class CustomerTradeRecordActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_customer_trade)
    ListView lvCustomerTrade;
    @Bind(R.id.smart)
    SmartRefreshLayout smartRefreshLayout;

    List<CustomerVisitorBean.DBean.ReListBean> list = new ArrayList<>();
    CustomerTradeAdapter tradeAdapter;
    private String id = "";
    private String saleId = "";
    private int page = 1;
    private int pageSize = 20;

    @Override
    public int initLayout() {
        return R.layout.activity_customer_trade;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("交易记录");
        id = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        saleId = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        tradeAdapter = new CustomerTradeAdapter(this, list, R.layout.item_customer_trade);
        lvCustomerTrade.setAdapter(tradeAdapter);
        lvCustomerTrade.setDividerHeight(0);
        getTradeRecord();

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            page = 1;
                getTradeRecord();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getTradeRecord();
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


    //获取交易记录
    public void getTradeRecord() {
        Map<String, Object> map = new HashMap<>();
        map.put("customerid", id);
        map.put("pageIndex", page);
        map.put("pageSize", pageSize);
        map.put("saleId", saleId);
        String json = JSON.toJSONString(map);
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().CUSTOMER_MANAGE_URL + "GetSaleRecords", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                if(page == 1){
                    list.clear();
                }
                CustomerVisitorBean visitorBean = JSON.parseObject(str, CustomerVisitorBean.class);
                if (visitorBean.getD().getSuccess() == 1) {
                    if (visitorBean.getD().getReList() != null) {
                        list.addAll(visitorBean.getD().getReList());
                    }
                    tradeAdapter.notifyDataSetChanged();
                } else {
                    ToastUtils.getMineToast(visitorBean.getD().getErrMsg());
                }
                SmartUtils.finishSmart(smartRefreshLayout);
            }

            @Override
            public void onFail() {
                SmartUtils.finishSmart(smartRefreshLayout);
            }
        });
    }
}
