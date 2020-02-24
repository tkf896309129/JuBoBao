package com.example.huoshangkou.jubowan.fragment.function;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.OrderDetailsActivity;
import com.example.huoshangkou.jubowan.adapter.OrderAdapter;
import com.example.huoshangkou.jubowan.bean.OrderBean;
import com.example.huoshangkou.jubowan.bean.OrderListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnGetOrderListCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment.function
 * 类名：OrderListFunction
 * 描述：
 * 创建时间：2017-02-09  13:51
 */

public class OrderListFunction {

    private static class OrderListHelper {
        private static OrderListFunction INSTANCE = new OrderListFunction();
    }

    public static OrderListFunction getInstance() {
        return OrderListHelper.INSTANCE;
    }


    //数据加载
    public void getOrderListData(List<OrderBean> orderList, final Context context, OrderAdapter orderAdapter, ListView lvRefresh, final XRefreshView xRefresh) {
        for (int i = 0; i < 10; i++) {
            OrderBean orderBean = new OrderBean();
            orderList.add(orderBean);
        }

        orderAdapter = new OrderAdapter(context, orderList, R.layout.item_order_repair);
        lvRefresh.setAdapter(orderAdapter);
        lvRefresh.setDividerHeight(0);

        xRefresh.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //模拟数据加载失败的情况
                        xRefresh.stopRefresh();
                        //或者
//                        outView.stopRefresh(success);
                    }
                }, 2000);
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

        //点击事件
        lvRefresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentUtils.getInstance().toActivity(context, OrderDetailsActivity.class);
            }
        });
    }


    //获取订单信息
    public void getOrderInfo(Context context, String url, final OnGetOrderListCallBack callBack) {

        OkhttpUtil.getInstance().setUnCacheDataNoDialog(context, url, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                OrderListBean listBean = JSON.parseObject(json, OrderListBean.class);
                callBack.onSuccess(listBean);
            }


            @Override
            public void onFail() {
                callBack.onFail();
            }
        });
    }

}
