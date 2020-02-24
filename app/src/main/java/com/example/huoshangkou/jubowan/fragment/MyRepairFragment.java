package com.example.huoshangkou.jubowan.fragment;

import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.RepairDetailActivity;
import com.example.huoshangkou.jubowan.activity.RepairDetailListActivity;
import com.example.huoshangkou.jubowan.activity.RepairPriceActivity;
import com.example.huoshangkou.jubowan.adapter.RepairOrderAdapter;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.bean.OrderListBean;
import com.example.huoshangkou.jubowan.bean.OrderListsBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.inter.OnRepairPriceClick;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment
 * 类名：MyRepairFragment
 * 描述：我靠
 * 创建时间：2017-08-08  14:46
 */

public class MyRepairFragment extends BaseFragment {
    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.x_refresh)
    XRefreshView xRefresh;
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;

    //MyRepairAdapter repairAdapter;
    private RepairOrderAdapter orderAdapter;
    List<OrderListsBean> list;

    private String orderState = "";
    private int pageSize = 1;
    String url = "";

    public static MyRepairFragment newInstance() {
        MyRepairFragment fragment = new MyRepairFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my_repair;
    }

    @Override
    public void initData() {
        list = new ArrayList<>();
        EventBus.getDefault().register(this);
        orderState = getArguments().getString(IntentUtils.getInstance().TYPE);

        orderAdapter = new RepairOrderAdapter(getActivity(), list, R.layout.item_order_repair);
        lvRefresh.setAdapter(orderAdapter);
        lvRefresh.setDividerHeight(0);

        getData("");
        xRefresh.setPullLoadEnable(true);
        orderAdapter.setPositionClick(new OnPositionClick() {
            @Override
            public void onPositionClick(int position) {
                Intent intent = new Intent(getActivity(), RepairDetailActivity.class);
                intent.putExtra(IntentUtils.getInstance().BEAN_TYPE, list.get(position));
                intent.putExtra(IntentUtils.getInstance().POSITION, position);
                intent.putExtra(IntentUtils.getInstance().TYPE, "isServiceList");
                startActivity(intent);
            }
        });

        orderAdapter.setRepairPriceClick(new OnRepairPriceClick() {
            @Override
            public void onRepairClick(String orderId, int position) {
                String urlRepair = UrlConstant.getInstance().URL + PostConstant.getInstance().SHOW_MAINTAIN_PRICE
                        + FieldConstant.getInstance().USER_ID + "=" + list.get(position).getUserID() + "&"
                        + FieldConstant.getInstance().ORDER_ID + "=" + orderId;
                IntentUtils.getInstance().toActivity(getActivity(), RepairDetailListActivity.class, urlRepair);
//                Intent intent = new Intent(getActivity(), RepairPriceActivity.class);
//                intent.putExtra(IntentUtils.getInstance().TYPE, urlRepair);
//                intent.putExtra(IntentUtils.getInstance().VALUE, orderId);
//                intent.putExtra(IntentUtils.getInstance().POSITION, position);
//                intent.putExtra(IntentUtils.getInstance().IS_EDIT, "no");
//                startActivity(intent);
            }
        });

        xRefresh.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                list.clear();
                pageSize = 1;
                getData("");
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                pageSize++;
                getData("");
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
    }

    //刷新数据
    @Subscriber(tag = "initService")
    public void initOrder(String str) {
        pageSize = 1;
        getData("clear");
        orderAdapter.notifyDataSetChanged();
    }


    public void getData(final String str) {

//        url = "http://192.168.10.120/webapi/atapi/GetMaintainListEnginner/?userid=5320&orderstate=" + orderState + "&page=" + pageSize;
        url = UrlConstant.getInstance().URL + PostConstant.getInstance().GET_MAINTAIN_LIST_ENGINEER + FieldConstant.getInstance().USER_ID
                + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().ORDER_STATE + "=" + orderState + "&"
                + FieldConstant.getInstance().PAGE + "=" + pageSize;
        OkhttpUtil.getInstance().setUnCacheData(getActivity(), getString(R.string.loading_message), url, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                OrderListBean listBean = JSON.parseObject(json, OrderListBean.class);
                List<OrderListsBean> reList = listBean.getReList();
                int num = reList.size();
                for (int i = 0; i < num; i++) {
                    OrderListsBean bean = reList.get(i);
                    bean.setMyRepair(true);
                }

                if (StringUtils.isNoEmpty(str)) {
                    list.clear();
                }

                list.addAll(reList);
                orderAdapter.notifyDataSetChanged();

                xRefresh.stopLoadMore();
                xRefresh.stopRefresh();
            }

            @Override
            public void onFail() {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        String str = (String) SharedPreferencesUtils.getInstance().get(getContext(), SharedKeyConstant.getInstance().SERVICE_LIST, "");
        if (StringUtils.isNoEmpty(str)) {
            EventBus.getDefault().post("initService", "initService");
            SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().SERVICE_LIST, "");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
