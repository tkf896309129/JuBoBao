package com.example.huoshangkou.jubowan.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.CustomerVisitorRecordAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.CustomerVisitorBean;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SmartUtils;
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
 * 类名：CustomerVisitorRecordActivity
 * 描述：
 * 创建时间：2019-08-29  13:13
 */

public class CustomerVisitorRecordActivity extends BaseActivity {
    @Bind(R.id.lv_customer_visitor)
    ListView lvCustomerVisitor;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.smart)
    SmartRefreshLayout smartRefreshLayout;

    List<CustomerVisitorBean.DBean.ReListBean> list = new ArrayList<>();
    CustomerVisitorRecordAdapter recordAdapter;

    private String id = "";

    private String data = "";
    private String content = "";
    private String evluate = "";
    private int page = 1;
    private int pageSize = 20;

    @Override
    public int initLayout() {
        return R.layout.activity_customer_visitor_record;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("拜访记录");
        id = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);


        recordAdapter = new CustomerVisitorRecordAdapter(this, list, R.layout.item_customer_visitor);
        lvCustomerVisitor.setAdapter(recordAdapter);
        lvCustomerVisitor.setDividerHeight(0);

        getVisitorRecord();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getVisitorRecord();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getVisitorRecord();
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

    //获取拜访记录
    public void getVisitorRecord() {
        Map<String, Object> map = new HashMap<>();
        map.put("customerId", id);
        map.put("pageIndex", page);
        map.put("pageSize", pageSize);
        String json = JSON.toJSONString(map);
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().CUSTOMER_MANAGE_URL + "GetVisitRecords", new StringCallBack() {
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
                    recordAdapter.notifyDataSetChanged();
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
