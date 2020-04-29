package com.example.huoshangkou.jubowan.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.CommonActivity;
import com.example.huoshangkou.jubowan.activity.OrderDetailsActivity;
import com.example.huoshangkou.jubowan.activity.RepairDetailActivity;
import com.example.huoshangkou.jubowan.activity.RepairPriceActivity;
import com.example.huoshangkou.jubowan.activity.function.OrderCommitFunction;
import com.example.huoshangkou.jubowan.adapter.OrderListAdapter;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.base.BaseListFragment;
import com.example.huoshangkou.jubowan.bean.OrderListBean;
import com.example.huoshangkou.jubowan.bean.OrderListsBean;
import com.example.huoshangkou.jubowan.constant.BundleConstant;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.OrderTypeConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.fragment.function.OrderListFunction;
import com.example.huoshangkou.jubowan.inter.ConfirmReceive;
import com.example.huoshangkou.jubowan.inter.OnAddWorkCallBack;
import com.example.huoshangkou.jubowan.inter.OnDeleteCallBack;
import com.example.huoshangkou.jubowan.inter.OnDialogCallBack;
import com.example.huoshangkou.jubowan.inter.OnGetOrderListCallBack;
import com.example.huoshangkou.jubowan.inter.OnOrderListCallBack;
import com.example.huoshangkou.jubowan.inter.OnRepairPriceClick;
import com.example.huoshangkou.jubowan.inter.OnToCommonCallBack;
import com.example.huoshangkou.jubowan.inter.SuccessCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.EditDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.utils.TwoPointUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment
 * 类名：OrderListFragment
 * 描述：订单列表
 * 创建时间：2017-02-09  11:43
 */

public class OrderListFragment extends BaseFragment {
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;
    @Bind(R.id.x_refresh)
    SmartRefreshLayout xRefresh;
    @Bind(R.id.lv_refresh)
    ListView listView;

    OrderListAdapter listAdapter;
    List<OrderListsBean> orderList;

    //状态
    private String state = "";
    //地址链接
    private String url = "";
    //清单回调
    private final int CODED = 1;
    //详情回调
    private final int DETAIL_CODE = 2;
    //评价回调
    private final int COMMON_BACK = 3;
    //垫付款金额
    private String dianMoney = "";
    private int pageSize = 1;

    private final String INIT = "init";
    private final String LOAD_MORE = "";

    public static OrderListFragment newInstance() {
        OrderListFragment fragment = new OrderListFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order_list;
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        orderList = new ArrayList<>();

        Bundle bundle = getArguments();
        state = bundle.getString(BundleConstant.getInstance().ORDER_STATE);

        listAdapter = new OrderListAdapter(getActivity(), orderList, R.layout.item_order_repair);
        listView.setAdapter(listAdapter);
        listView.setDividerHeight(0);

        getData(INIT);
        xRefresh.setEnableRefresh(true);
        xRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageSize = 1;
                getData(INIT);
            }
        });
        xRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageSize++;
                getData(LOAD_MORE);
            }
        });

        //点击事件
        listAdapter.setOnOrderListCallBack(new OnOrderListCallBack() {
            @Override
            public void onChildClick(String type, String orderId, int position) {
                if (type.equals(OrderTypeConstant.getInstance().WX)) {
                    Intent intent = new Intent(getActivity(), RepairDetailActivity.class);
                    intent.putExtra(IntentUtils.getInstance().BEAN_TYPE, orderList.get(position));
                    intent.putExtra(IntentUtils.getInstance().POSITION, position);
                    startActivityForResult(intent, DETAIL_CODE);
                } else {
                    Intent intent = new Intent(getActivity(), OrderDetailsActivity.class);
                    intent.putExtra(IntentUtils.getInstance().POSITION, position);
                    intent.putExtra(IntentUtils.getInstance().BEAN_TYPE, orderList.get(position));
                    startActivityForResult(intent, DETAIL_CODE);
                }
            }
        });

        //删除订单
        listAdapter.setDeleteCallBack(new OnDeleteCallBack() {
            @Override
            public void onDelete(final String orderId, final int position) {
                CopyIosDialogUtils.getInstance().getIosDialog(getActivity(), "是否删除订单：" + orderId, new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        OrderCommitFunction.getInstance().deleteOrder(orderId, getActivity(), new SuccessCallBack() {
                            @Override
                            public void onSuccess() {
                                orderList.remove(position);
                                listAdapter.notifyDataSetChanged();
                                ToastUtils.getMineToast("删除成功");
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

        listAdapter.setRepairPriceClick(new OnRepairPriceClick() {
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

        listAdapter.setToCommonCallBack(new OnToCommonCallBack() {
            @Override
            public void onToCommon(String id, int position) {
                IntentUtils.getInstance().toActivity(getContext(), CommonActivity.class, id, OrderTypeConstant.getInstance().COMMON_ORDER);
            }

            @Override
            public void onToRepair(String id, int position) {
                IntentUtils.getInstance().toActivity(getContext(), CommonActivity.class, id, OrderTypeConstant.getInstance().REPAIR_ORDER);
            }

            @Override
            public void onDianPay(final String id, final int position, int state) {
                dianMoney = orderList.get(position).getTotalPrice();

                if (state != 0) {
                    CopyIosDialogUtils.getInstance().getErrorDialog(getActivity(), "垫付款已支付", new CopyIosDialogUtils.ErrDialogCallBack() {
                        @Override
                        public void confirm() {

                        }
                    });
                    return;
                }

                EditDialogUtils.getInstance().showEditTextCancelDialog("num", getActivity(), listView, "订单总金额：" + dianMoney + "元，运费总额"
                        + orderList.get(position).getWlPrice() + "元,合计"
                        + TwoPointUtils.getInstance().getTwoPoint((Double.parseDouble(dianMoney)
                        + Double.parseDouble(orderList.get(position).getWlPrice())))
                        + "元，请输入您需要的垫付款金额", new OnDialogCallBack() {
                    @Override
                    public void addWorkExp(String content, AlertDialog popupSearchWindow) {
                        if (!StringUtils.isNoEmpty(content)) {
                            ToastUtils.getMineToast("请输入垫付款金额");
                            return;
                        }
                        Double dianPrice = Double.parseDouble(content);
                        if (dianPrice < 0) {
                            ToastUtils.getMineToast("垫付金额不能小于零");
                            return;
                        }

                        if (dianPrice > Double.parseDouble(dianMoney)
                                + Double.parseDouble(orderList.get(position).getWlPrice())) {
                            ToastUtils.getMineToast("垫付金额不能大于合计金额");
                            return;
                        }

                        popupSearchWindow.dismiss();
                        OrderCommitFunction.getInstance().dianPay(id, getActivity(), content, new SuccessCallBack() {
                            @Override
                            public void onSuccess() {
                                ToastUtils.getMineToast("提交成功");
                                pageSize = 1;
                                getData(INIT);
                            }

                            @Override
                            public void onFail() {
                                ToastUtils.getMineToast("提交失败");
                            }
                        });
                    }
                });
            }
        });

        //确认收货
        listAdapter.setConfirmReceive(new ConfirmReceive() {
            @Override
            public void confirmReceive(final String id) {
                CopyIosDialogUtils.getInstance().getIosDialog(getActivity(), "是否确认收货", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        String url = UrlConstant.getInstance().CONFIRM_SIGN + id;
                        IntentUtils.getInstance().toNextWebActivity(getActivity(), url, "订单确认书", "订单确认书", "确认收货", id);

//                        OkhttpUtil.getInstance().setUnCacheData(getActivity(), getString(R.string.loading_message), UrlConstant.getInstance().URL
//                                + PostConstant.getInstance().CONFIRM_RECEPIT
//                                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
//                                + FieldConstant.getInstance().ORDER_ID + "=" + id, new OkhttpCallBack() {
//                            @Override
//                            public void onSuccess(String json) {
//                                pageSize = 1;
//                                getData(INIT);
//                            }
//
//                            @Override
//                            public void onFail() {
//
//                            }
//                        });
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }
        });
    }

    @Subscriber(tag = "toJuYiLianBuy")
    public void juYiLianBuyRefresh(String str) {
        if (!str.isEmpty()) {
            getData(INIT);
        }
    }

    //获取数据
    private void getData(final String init) {
        //所有订单链接
        url = UrlConstant.getInstance().URL + PostConstant.getInstance().GET_ALL_ORDER_LIST
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().STATE + "=" + state + "&"
                + FieldConstant.getInstance().PAGE + "=" + pageSize;

        //获取订单信息
        OrderListFunction.getInstance().getOrderInfo(getActivity(), url, new OnGetOrderListCallBack() {
            @Override
            public void onSuccess(OrderListBean listBean) {
                if (listBean == null) {
                    return;
                }

                if (StringUtils.isNoEmpty(init)) {
                    orderList.clear();
                }
                changeState(listBean.getReList());
                orderList.addAll(listBean.getReList());

                listAdapter.notifyDataSetChanged();
                if (xRefresh != null) {
                    xRefresh.finishLoadMore();
                    xRefresh.finishRefresh();
                }

                if (llNoData == null) {
                    return;
                }

                if (orderList.size() == 0) {
                    llNoData.setVisibility(View.VISIBLE);
                } else {
                    llNoData.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFail() {
                if (xRefresh != null) {
                    xRefresh.finishLoadMore();
                    xRefresh.finishRefresh();
                }
                if (llNoData != null) {
                    llNoData.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    //状态转换
    public void changeState(List<OrderListsBean> reList) {

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
                    case 1:
                        reList.get(i).setOrderState(2);
                        break;
                    case 2:
                        reList.get(i).setOrderState(4);
                        break;
                }
            }
        }
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
                        orderList.get(position).setTotalPrice(price);
                        listAdapter.notifyDataSetChanged();
                    } catch (Exception ex) {

                    }
                }
                break;
            case DETAIL_CODE:
                String prices = data.getStringExtra(IntentUtils.getInstance().PRICE);
                int positions = data.getIntExtra(IntentUtils.getInstance().POSITION, -1);

                String isDelete = data.getStringExtra(IntentUtils.getInstance().TYPE);
                if (StringUtils.isNoEmpty(isDelete) && positions != -1) {
                    orderList.remove(positions);
                    listAdapter.notifyDataSetChanged();
                }

                if (positions != -1 && StringUtils.isNoEmpty(prices)) {
                    try {
                        orderList.get(positions).setTotalPrice(prices);
                        listAdapter.notifyDataSetChanged();
                    } catch (Exception ex) {

                    }
                }
                break;
        }
    }

    //跳转至订单界面
    @Subscriber(tag = "OrderFragment")
    public void toOrder(String str) {
        if (!str.isEmpty() && StringUtils.isNoEmpty(url) && state.equals("1")) {
            getData(INIT);
        }
    }

    //刷新数据
    @Subscriber(tag = "initOrder")
    public void initOrder(String str) {
        pageSize = 1;
        getData(INIT);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        String isInit = (String) SharedPreferencesUtils.getInstance().get(getActivity(), SharedKeyConstant.getInstance().IS_INIT_ORDER, "");
        if (StringUtils.isNoEmpty(isInit)) {
            EventBus.getDefault().post("initOrder", "initOrder");
            SharedPreferencesUtils.getInstance().put(getActivity(), SharedKeyConstant.getInstance().IS_INIT_ORDER, "");
        }
    }


}
