package com.example.huoshangkou.jubowan.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.CustomerMessageAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.CustomerMessageBean;
import com.example.huoshangkou.jubowan.bean.SuccessDBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
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
 * 类名：CustomerMessageActivity
 * 描述：
 * 创建时间：2019-08-28  16:28
 */

public class CustomerMessageActivity extends BaseActivity {
    @Bind(R.id.lv_customer_message)
    ListView lvCustomerMessage;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.smart)
    SmartRefreshLayout smartRefreshLayout;

    List<CustomerMessageBean.DBean.ResultBean> list = new ArrayList<>();
    CustomerMessageAdapter messageAdapter;

    private int page = 1;

    @Override
    public int initLayout() {
        return R.layout.activity_customer_message;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("消息");

        messageAdapter = new CustomerMessageAdapter(this, list, R.layout.item_customer_message);
        lvCustomerMessage.setAdapter(messageAdapter);
        lvCustomerMessage.setDividerHeight(0);

        getCustomerMessage();

        //同意
        messageAdapter.setAgreeCallback(new OnPositionClick() {
            @Override
            public void onPositionClick(final int position) {
                CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否同意该请求", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        checkMessage(list.get(position).getId() + "", "1");
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });


            }
        });

        //拒绝
        messageAdapter.setDisagreeCallback(new OnPositionClick() {
            @Override
            public void onPositionClick(final int position) {
                CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否拒绝该请求", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        checkMessage(list.get(position).getId() + "", "-1");
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });

            }
        });

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getCustomerMessage();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page ++;
                getCustomerMessage();
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


    public void getCustomerMessage() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", PersonConstant.getInstance().getUserId());
        map.put("pageIndex", page);
        map.put("pageSize", 20);
        String json = JSON.toJSONString(map);
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().CUSTOMER_DATA_URL + "GetTransferApplyMsg", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                CustomerMessageBean messageBean = JSON.parseObject(str, CustomerMessageBean.class);
                if (messageBean.getD() == null) {
                    return;
                }
                if (page == 1) {
                    list.clear();
                }
                list.addAll(messageBean.getD().getResult());
                messageAdapter.notifyDataSetChanged();
                SmartUtils.finishSmart(smartRefreshLayout);
            }

            @Override
            public void onFail() {
                SmartUtils.finishSmart(smartRefreshLayout);
            }
        });
    }

    //审批消息
    public void checkMessage(String id, String state) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("state", state);
        String json = JSON.toJSONString(map);
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().CUSTOMER_DATA_URL + "TransferApply", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                SuccessDBean dBean = JSON.parseObject(str, SuccessDBean.class);
                if (dBean.getD().getErrorCode() == 0) {
                    page = 1;
                    getCustomerMessage();
                }
                ToastUtils.getMineToast(dBean.getD().getResult());
            }

            @Override
            public void onFail() {

            }
        });
    }
}
