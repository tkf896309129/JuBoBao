package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.OrderListAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.OrderListBean;
import com.example.huoshangkou.jubowan.bean.OrderListsBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
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
 * 类名：MyAllOrderAcitivty
 * 描述：
 * 创建时间：2017-11-03  15:09
 */

public class MyAllOrderAcitivty extends BaseActivity {

    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;
    @Bind(R.id.x_refresh)
    XRefreshView xRefreshView;
    @Bind(R.id.lv_refresh)
    ListView listView;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    private String advanceId = "";
    OrderListAdapter listAdapter;

    List<OrderListsBean> orderList;

    @Override
    public int initLayout() {
        return R.layout.activity_my_all_order;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        orderList = new ArrayList<>();
        advanceId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        listAdapter = new OrderListAdapter(getContext(), orderList, R.layout.item_order_repair, "dianfu");
        listView.setAdapter(listAdapter);
        listView.setDividerHeight(0);

        tvTitle.setText("我的订单");


        getAllOrder(advanceId);

        xRefreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                orderList.clear();
                getAllOrder(advanceId);
            }

            @Override
            public void onLoadMore(boolean isSilence) {

            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });

    }

    //获取我的所有的订单
    public void getAllOrder(String orderId) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(getContext(), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_ORDER_LIST_AD + FieldConstant.getInstance().ADVANCE_ORDER_ID + "=" + orderId, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                OrderListBean listBean = JSON.parseObject(json, OrderListBean.class);
                orderList.addAll(listBean.getReList());
                listAdapter.notifyDataSetChanged();
                xRefreshView.stopRefresh();
                if (orderList.size() == 0) {
                    llNoData.setVisibility(View.VISIBLE);
                } else {
                    llNoData.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFail() {
                xRefreshView.stopRefresh();
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

}
