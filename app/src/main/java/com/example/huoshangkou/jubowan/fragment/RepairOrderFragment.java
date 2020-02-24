package com.example.huoshangkou.jubowan.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.CommonActivity;
import com.example.huoshangkou.jubowan.activity.RepairDetailActivity;
import com.example.huoshangkou.jubowan.activity.RepairPriceActivity;
import com.example.huoshangkou.jubowan.activity.function.OrderCommitFunction;
import com.example.huoshangkou.jubowan.adapter.RepairOrderAdapter;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.bean.OrderListBean;
import com.example.huoshangkou.jubowan.bean.OrderListsBean;
import com.example.huoshangkou.jubowan.constant.BundleConstant;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.OrderTypeConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnDeleteCallBack;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.inter.OnRepairPriceClick;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.inter.SuccessCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
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
 * 类名：RepairOrderFragment
 * 描述：维修订单
 * 创建时间：2017-04-26  16:53
 */

public class RepairOrderFragment extends BaseFragment {

    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.x_refresh)
    XRefreshView xRefresh;
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;

    //维修清单回调
    private final int CODED = 1;

    //详情回调
    private final int DETAIL_CODE = 2;
    private int pageSize = 1;

    public static RepairOrderFragment newInstance() {
        RepairOrderFragment fragment = new RepairOrderFragment();
        return fragment;
    }

    private List<OrderListsBean> orderListBeanList;

    private RepairOrderAdapter orderAdapter;

    //状态
    private String state = "";
    //地址链接
    private String url = "";
    //维修id
    private String mainTainId = "";

    @Override
    public int getLayoutId() {
        return R.layout.framgne_repair_order;
    }

    @Override
    public void initData() {
        orderListBeanList = new ArrayList<>();

        xRefresh.setPullLoadEnable(true);

        Bundle bundle = getArguments();
        state = bundle.getString(BundleConstant.getInstance().ORDER_STATE);
        mainTainId = bundle.getString(IntentUtils.getInstance().TYPE);

        orderAdapter = new RepairOrderAdapter(getActivity(), orderListBeanList, R.layout.item_order_repair);
        lvRefresh.setAdapter(orderAdapter);
        lvRefresh.setDividerHeight(0);

        getRepairOrder(getActivity());

        orderAdapter.setPositionClick(new OnPositionClick() {
            @Override
            public void onPositionClick(int position) {
                Intent intent = new Intent(getActivity(), RepairDetailActivity.class);
                intent.putExtra(IntentUtils.getInstance().BEAN_TYPE, orderListBeanList.get(position));
                intent.putExtra(IntentUtils.getInstance().POSITION, position);
                startActivityForResult(intent, DETAIL_CODE);
            }
        });

        xRefresh.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                pageSize = 1;
                orderListBeanList.clear();
                getRepairOrder(getActivity());
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                pageSize++;
                getRepairOrder(getActivity());
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });


        orderAdapter.setRepairPriceClick(new OnRepairPriceClick() {
            @Override
            public void onRepairClick(String orderId, int position) {
                String urlRepair = UrlConstant.getInstance().URL + PostConstant.getInstance().SHOW_MAINTAIN_PRICE
                        + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                        + FieldConstant.getInstance().ORDER_ID + "=" + orderId;

                Intent intent = new Intent(getActivity(), RepairPriceActivity.class);
                intent.putExtra(IntentUtils.getInstance().TYPE, urlRepair);
                intent.putExtra(IntentUtils.getInstance().VALUE, orderId);
                intent.putExtra(IntentUtils.getInstance().POSITION, position);
                intent.putExtra(IntentUtils.getInstance().STATE, state);
                startActivityForResult(intent, CODED);
            }
        });

        //删除订单
        orderAdapter.setDeleteCallBack(new OnDeleteCallBack() {
            @Override
            public void onDelete(final String orderId, final int position) {
                CopyIosDialogUtils.getInstance().getIosDialog(getActivity(), "是否删除订单：" + orderId, new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        OrderCommitFunction.getInstance().deleteOrder(orderId, getActivity(), new SuccessCallBack() {
                            @Override
                            public void onSuccess() {
                                orderListBeanList.remove(position);
                                orderAdapter.notifyDataSetChanged();
                                ToastUtils.getMineToast("删除成功");
                                SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().REPAIR, "yes");
                            }

                            @Override
                            public void onFail() {
                                ToastUtils.getMineToast("删除失败");
                            }
                        });
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }
        });

        //评价
        orderAdapter.setStringCallBack(new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                IntentUtils.getInstance().toActivity(getActivity(), CommonActivity.class, str, OrderTypeConstant.getInstance().REPAIR_ORDER);
            }

            @Override
            public void onFail() {

            }
        });
    }

    //获取维修订单
    public void getRepairOrder(Context context) {

        url = UrlConstant.getInstance().URL + PostConstant.getInstance().GET_MAINTAIN_LIST
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().STATE + "=" + state + "&"
                + FieldConstant.getInstance().MAINTAINER_ID + "=" + mainTainId + "&"
                + FieldConstant.getInstance().PAGE + "=" + pageSize;

        OkhttpUtil.getInstance().setUnCacheData(context, getString(R.string.loading_message), url, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                OrderListBean listBean = JSON.parseObject(json, OrderListBean.class);
                changeState(listBean.getReList());
                orderListBeanList.addAll(listBean.getReList());
                orderAdapter.notifyDataSetChanged();
                if (xRefresh != null) {
                    xRefresh.stopRefresh();
                    xRefresh.stopLoadMore();
                }

                if (llNoData == null) {
                    return;
                }

                if (orderListBeanList.size() == 0) {
                    llNoData.setVisibility(View.VISIBLE);
                } else {
                    llNoData.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFail() {
                if (xRefresh != null) {
                    xRefresh.stopRefresh();
                    xRefresh.stopLoadMore();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case CODED:
                String price = data.getStringExtra(IntentUtils.getInstance().TYPE);
                int position = data.getIntExtra(IntentUtils.getInstance().POSITION, -1);
                if (position != -1) {
                    try {
                        orderListBeanList.get(position).setTotalPrice(price);
                        orderAdapter.notifyDataSetChanged();
                    } catch (Exception ex) {

                    }
                }
                break;
            case DETAIL_CODE:
                String prices = data.getStringExtra(IntentUtils.getInstance().PRICE);
                int positions = data.getIntExtra(IntentUtils.getInstance().POSITION, -1);

                String isDelete = data.getStringExtra(IntentUtils.getInstance().TYPE);
                if (StringUtils.isNoEmpty(isDelete) && positions != -1) {
                    orderListBeanList.remove(positions);
                    orderAdapter.notifyDataSetChanged();
                }

                if (positions != -1 && StringUtils.isNoEmpty(prices)) {
                    try {
                        orderListBeanList.get(positions).setTotalPrice(prices);
                        orderAdapter.notifyDataSetChanged();
                    } catch (Exception ex) {

                    }
                }
                break;
        }
    }

    //状态转换
    public void changeState(List<OrderListsBean> reList) {
//        2 全部 ---  0 待受理 --- 1 待支付 --- 3 待收货 --- 4 已完成
        //0 待确认  -1待支付 1 待评价  2已完成
        int num = reList.size();
        for (int i = 0; i < num; i++) {
            if (reList.get(i).getOrderListw() != null) {
                switch (reList.get(i).getOrderState()) {
                    case 0:
                        reList.get(i).setOrderState(0);
                        break;
                    case -1:
                        reList.get(i).setOrderState(1);
                        break;
//                    case 1:
//                        reList.get(i).setOrderState(2);
//                        break;
                    case 2:
                        reList.get(i).setOrderState(4);
                        break;
                }
            }
        }
    }


    //刷新数据
    @Subscriber(tag = "initRepairOrder")
    public void initOrder(String str) {
        pageSize = 1;
        orderListBeanList.clear();
        getRepairOrder(getActivity());
    }


    @Override
    public void onResume() {
        super.onResume();
        String isInit = (String) SharedPreferencesUtils.getInstance().get(getActivity(), SharedKeyConstant.getInstance().IS_INIT_ORDER, "");
        if (StringUtils.isNoEmpty(isInit)) {
            ToastUtils.getMineToast("刷新");
            EventBus.getDefault().post("initRepairOrder", "initRepairOrder");
            SharedPreferencesUtils.getInstance().put(getActivity(), SharedKeyConstant.getInstance().IS_INIT_ORDER, "");
        }
    }
}
