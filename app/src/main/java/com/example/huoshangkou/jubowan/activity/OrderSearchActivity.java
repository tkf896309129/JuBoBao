package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.OrderCommitFunction;
import com.example.huoshangkou.jubowan.adapter.OrderListAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.OrderListBean;
import com.example.huoshangkou.jubowan.bean.OrderListsBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.OrderTypeConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.ConfirmReceive;
import com.example.huoshangkou.jubowan.inter.OnDeleteCallBack;
import com.example.huoshangkou.jubowan.inter.OnOrderListCallBack;
import com.example.huoshangkou.jubowan.inter.OnRepairPriceClick;
import com.example.huoshangkou.jubowan.inter.OnToCommonCallBack;
import com.example.huoshangkou.jubowan.inter.SuccessCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：OrderSearchActivity
 * 描述：
 * 创建时间：2017-07-03  08:17
 */

public class OrderSearchActivity extends BaseActivity {
    @Bind(R.id.et_search_tie)
    EditText etSearch;
    @Bind(R.id.lv_refresh)
    ListView listView;
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;
    @Bind(R.id.x_refresh)
    XRefreshView xRefreshView;

    OrderListAdapter listAdapter;

    List<OrderListsBean> orderList;

    //搜索关键字
    private String searchKey = "";

    private int pageSize = 1;

    //清单回调
    private final int CODED = 1;

    //详情回调
    private final int DETAIL_CODE = 2;

    @Override
    public int initLayout() {
        return R.layout.activity_order_search;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        orderList = new ArrayList<>();

        listAdapter = new OrderListAdapter(getContext(), orderList, R.layout.item_order_repair);
        listView.setAdapter(listAdapter);
        listView.setDividerHeight(0);

        //数据刷新
        xRefreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                pageSize = 1;
                orderList.clear();
                searchOrderData();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                pageSize++;
                searchOrderData();
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });

        //点击事件
        listAdapter.setOnOrderListCallBack(new OnOrderListCallBack() {
            @Override
            public void onChildClick(String type, String orderId, int position) {
                if (type.equals(OrderTypeConstant.getInstance().WX)) {
                    Intent intent = new Intent(getContext(), RepairDetailActivity.class);
                    intent.putExtra(IntentUtils.getInstance().BEAN_TYPE, orderList.get(position));
                    intent.putExtra(IntentUtils.getInstance().POSITION, position);
                    startActivityForResult(intent, DETAIL_CODE);
                } else {
                    Intent intent = new Intent(getContext(), OrderDetailsActivity.class);
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
                CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否删除订单：" + orderId, new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        OrderCommitFunction.getInstance().deleteOrder(orderId, getContext(), new SuccessCallBack() {
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

        //维修清单点击事件
        listAdapter.setRepairPriceClick(new OnRepairPriceClick() {
            @Override
            public void onRepairClick(String orderId, int position) {
                String urlRepair = UrlConstant.getInstance().URL + PostConstant.getInstance().SHOW_MAINTAIN_PRICE
                        + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                        + FieldConstant.getInstance().ORDER_ID + "=" + orderId;

                Intent intent = new Intent(getContext(), RepairPriceActivity.class);
                intent.putExtra(IntentUtils.getInstance().TYPE, urlRepair);
                intent.putExtra(IntentUtils.getInstance().VALUE, orderId);
                intent.putExtra(IntentUtils.getInstance().POSITION, position);
                intent.putExtra(IntentUtils.getInstance().STATE, orderList.get(position).getState() + "");
                startActivityForResult(intent, CODED);
            }
        });

        //评价
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
            public void onDianPay(String id, int position,int state) {

            }
        });

        //确认收货
        listAdapter.setConfirmReceive(new ConfirmReceive() {
            @Override
            public void confirmReceive(final String id) {
                CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否确认收货", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                                + PostConstant.getInstance().CONFIRM_RECEPIT
                                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                                + FieldConstant.getInstance().ORDER_ID + "=" + id, new OkhttpCallBack() {
                            @Override
                            public void onSuccess(String json) {
                                pageSize = 1;
                                orderList.clear();
                                searchOrderData();
                            }

                            @Override
                            public void onFail() {

                            }
                        });
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
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

                searchKey = etSearch.getText().toString().trim();
                if (!StringUtils.isNoEmpty(searchKey)) {
                    ToastUtils.getMineToast("请输入搜索内容");
                    return;
                }
                orderList.clear();
                searchOrderData();

                break;

        }
    }


    //搜索订单信息
    public void searchOrderData() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().SEARCH_ORDER_INFO
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().KEY_WORD + "=" + EncodeUtils.getInstance().getEncodeString(searchKey), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                OrderListBean listBean = JSON.parseObject(json, OrderListBean.class);
                if (listBean == null) {
                    return;
                }
                changeState(listBean.getReList());
                orderList.addAll(listBean.getReList());

                listAdapter.notifyDataSetChanged();
                if (xRefreshView != null) {
                    xRefreshView.stopLoadMore();
                    xRefreshView.stopRefresh();
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

    @Override
    public void onResume() {
        super.onResume();
        String isInit = (String) SharedPreferencesUtils.getInstance().get(getContext(), SharedKeyConstant.getInstance().IS_INIT_ORDER, "");
        if (StringUtils.isNoEmpty(isInit)) {

            EventBus.getDefault().post("initOrder", "initOrder");

            SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().IS_INIT_ORDER, "");
        }
    }

}
