package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.MonthSaleAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.MonthSaleDetailBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SmartUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
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
 * 类名：MonthSaleDetailActivity
 * 描述：
 * 创建时间：2019-09-25  13:14
 */

public class MonthSaleDetailActivity extends BaseActivity {
    @Bind(R.id.lv_detail)
    ListView lvMonthDetail;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.smart)
    SmartRefreshLayout smartRefreshLayout;

    MonthSaleAdapter saleAdapter;
    List<MonthSaleDetailBean.DBean.ResultBean> list = new ArrayList<>();

    private String userId = "";
    private String keyWord = "";
    private String startTime = "";
    private String endTime = "";
    private int page = 1;
    private String depId = "";
    private String typeId = "0";

    @Override
    public int initLayout() {
        return R.layout.activity_month_sale;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("月销售额");
        String customerManageUserId = PersonConstant.getInstance().getCustomerManageUserId();
        if (StringUtils.isNoEmpty(customerManageUserId)) {
            userId = customerManageUserId;
        } else {
            userId = PersonConstant.getInstance().getUserId();
        }
        depId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        typeId = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        saleAdapter = new MonthSaleAdapter(this, list, R.layout.item_month_sale_detail);
        lvMonthDetail.setAdapter(saleAdapter);
        lvMonthDetail.setDividerHeight(0);
        getMonthSale();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getMonthSale();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getMonthSale();
            }
        });
    }

    @OnClick({R.id.ll_back, R.id.iv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.iv_right:
                Intent intent = new Intent(this, SearchTimeKeyActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case 1:
                keyWord = data.getStringExtra(IntentUtils.getInstance().TYPE);
                startTime = data.getStringExtra(IntentUtils.getInstance().VALUE);
                endTime = data.getStringExtra(IntentUtils.getInstance().STR);
                page = 1;
                getMonthSale();
                break;
        }
    }

    //获取本月销售额数据
    public void getMonthSale() {
        Map<String, Object> map = new HashMap<>();
        map.put("StartDate", startTime);
        map.put("EndDate", endTime);
        map.put("UserId", userId);
        map.put("PageIndex", page);
        map.put("AdminId", typeId);
        map.put("AreaRoleId", depId);
        map.put("PageSize", "20");
        map.put("Keyword", keyWord);
        String json = "{pagedInput:" + JSON.toJSONString(map) + "}";

        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().CUSTOMER_DATA_CENTER_URL + "GetSalesAmountPaged", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                if (page == 1) {
                    list.clear();
                }
                MonthSaleDetailBean detailBean = JSON.parseObject(str, MonthSaleDetailBean.class);
                if (detailBean.getD().getResult() != null) {
                    list.addAll(detailBean.getD().getResult());
                }
                saleAdapter.notifyDataSetChanged();
                SmartUtils.finishSmart(smartRefreshLayout);
            }

            @Override
            public void onFail() {
                SmartUtils.finishSmart(smartRefreshLayout);
            }
        });
    }
}
