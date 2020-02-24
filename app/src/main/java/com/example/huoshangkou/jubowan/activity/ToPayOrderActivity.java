package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.ToPayOrderAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ToPayOrderBean;
import com.example.huoshangkou.jubowan.bean.ToPayOrderListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：ToPayOrderActivity
 * 描述：
 * 创建时间：2017-12-14  09:14
 */

public class ToPayOrderActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.lv_order)
    ListView lvOrder;

    List<ToPayOrderListBean> list;
    ToPayOrderAdapter orderAdapter;

    @Override
    public int initLayout() {
        return R.layout.activity_to_pay_order;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("待支付订单列表");
        tvRight.setText("提交");
        getPayOrderList();
        list = new ArrayList<>();
        orderAdapter = new ToPayOrderAdapter(getContext(), list, R.layout.item_to_pay_order);
        lvOrder.setAdapter(orderAdapter);
        lvOrder.setDividerHeight(0);

        lvOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!list.get(i).isChcek()) {
                    list.get(i).setChcek(true);
                } else {
                    list.get(i).setChcek(false);
                }
                orderAdapter.notifyDataSetChanged();


            }
        });

    }

    @OnClick({R.id.ll_back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_right:
                String orderList = "";
                int num = list.size();
                for (int i = 0; i < num; i++) {
                    if (list.get(i).isChcek()) {
                        orderList += list.get(i).getOrderID() + ",";
                    }
                }

                if (orderList.length() < 5) {
                    ToastUtils.getMineToast("请选择订单号");
                    return;
                }

                Intent intent = new Intent();
                intent.putExtra("orderId", orderList);
                setResult(2, intent);
                ToPayOrderActivity.this.finish();

                break;
        }
    }

    //获取待支付订单列表
    public void getPayOrderList() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), "数据加载中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().WAIT_PAY_ORDER_LSIT
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                ToPayOrderBean orderBean = JSON.parseObject(json, ToPayOrderBean.class);
                list.addAll(orderBean.getReList());
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
    }
}
