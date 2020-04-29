package com.example.huoshangkou.jubowan.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.MessageFunction;
import com.example.huoshangkou.jubowan.adapter.OrderNotifyAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.MessageTypeBean;
import com.example.huoshangkou.jubowan.bean.MessageTypeDetailListBean;
import com.example.huoshangkou.jubowan.bean.MessageTypeListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnCommonSuccessCallBack;
import com.example.huoshangkou.jubowan.inter.OnDeleteCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SignManUtils;
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
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：OrderNotifyActivity
 * 描述：
 * 创建时间：2017-04-24  09:21
 */

public class OrderNotifyActivity extends BaseActivity {

    OrderNotifyAdapter notifyAdapter;
    List<MessageTypeListBean> notifyList;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.x_refresh)
    SmartRefreshLayout xRefresh;
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;

    private String messageType = "";

    private int page = 1;

    @Override
    public int initLayout() {
        return R.layout.activity_order_notify;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {

        //0:系统 1:订单(原片订单) 2 辅料订单 3 维修维保通知 4 其他通知
        messageType = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        switch (messageType){
            case "0":
                tvTitle.setText("系统通知");
                break;
            case "1":
                tvTitle.setText("原片订单");
                break;
            case "2":
                tvTitle.setText("辅料订单");
                break;
            case "3":
                tvTitle.setText("维修订单");
                break;
            case "4":
                tvTitle.setText("审批消息");
                break;
            case "5":
                tvTitle.setText("论坛消息");
                break;
            case "6":
                tvTitle.setText("其他通知");
                break;
        }


        notifyList = new ArrayList<>();

        notifyAdapter = new OrderNotifyAdapter(getContext(), notifyList, R.layout.item_order_notify);
        lvRefresh.setAdapter(notifyAdapter);
        lvRefresh.setDividerHeight(0);

        getOrderNotify();

        xRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getOrderNotify();
            }
        });
        xRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                notifyList.clear();
                getOrderNotify();
            }
        });


        //删除通知
        notifyAdapter.setDeleteCallBack(new OnDeleteCallBack() {
            @Override
            public void onDelete(final String orderId, final int position) {
                SignManUtils.getInstance().getChooseDialog(getContext(), notifyList.get(position).getID() + "", "是否删除通知", true, new OnCommonSuccessCallBack() {
                    @Override
                    public void onSuccess() {
                        deleteMessage(orderId,position);
                    }

                    @Override
                    public void onFail() {

                    }
                });
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


    //获取订单通知
    public void getOrderNotify() {

        MessageFunction.getInstance().getOrderNotify(getContext(), messageType, page, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                MessageTypeBean typeBean = JSON.parseObject(str, MessageTypeBean.class);

                notifyList.addAll(typeBean.getReList());
                notifyAdapter.notifyDataSetChanged();
                if (xRefresh != null) {
                    xRefresh.finishLoadMore();
                    xRefresh.finishRefresh();
                }
            }

            @Override
            public void onFail() {
                if (xRefresh != null) {
                    xRefresh.finishLoadMore();
                    xRefresh.finishRefresh();
                }
            }
        });
    }

    public void deleteMessage(String id, final int position) {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().DELETE_MESSAGE + FieldConstant.getInstance().ID + "=" + id, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                notifyList.remove(position);
                notifyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
    }


}
