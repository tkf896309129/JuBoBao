package com.example.huoshangkou.jubowan.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.AddCustomerActivity;
import com.example.huoshangkou.jubowan.activity.CustomerDetailsActivity;
import com.example.huoshangkou.jubowan.activity.CustomerMessageActivity;
import com.example.huoshangkou.jubowan.activity.SignManActivity;
import com.example.huoshangkou.jubowan.adapter.CustomerAllAdapter;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.bean.AllCustomerBean;
import com.example.huoshangkou.jubowan.bean.SuccessDBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.inter.StringPositionCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SmartUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
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
 * 包名：com.example.huoshangkou.jubowan.fragment
 * 类名：CustomerFragment
 * 描述：
 * 创建时间：2019-08-26  09:50
 */

public class CustomerFragment extends BaseFragment {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_customer)
    ListView lvCustomer;
    @Bind(R.id.smart)
    SmartRefreshLayout smartRefreshLayout;
    @Bind(R.id.iv_message)
    ImageView imgMessage;
    @Bind(R.id.iv_right)
    ImageView imgAdd;
    @Bind(R.id.tv_right)
    TextView tvRight;

    List<AllCustomerBean.DBean.ReListBean> list = new ArrayList<>();
    CustomerAllAdapter allAdapter;
    List<String> listTitle = new ArrayList<>();

    private int page = 1;
    private String typeData = "";
    private String roleID = "";
    private String type = "";
    private String userId = "";
    //是否是我的无意向客户
    private int isMe = 0;  //是否是管理员查看
    private boolean isManage = false;


    public static CustomerFragment newInstance() {
        CustomerFragment fragment = new CustomerFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_customer;
    }

    @Override
    public void initData() {
        tvTitle.setText("全部客户");
        //管理员查看
        String customerManageUserId = PersonConstant.getInstance().getCustomerManageUserId();
        if (StringUtils.isNoEmpty(customerManageUserId)) {
            userId = customerManageUserId;
            isManage = true;
        } else {
            userId = PersonConstant.getInstance().getUserId();
        }
        typeData = getArguments().getString(IntentUtils.getInstance().ROLE_MANAGE_TYPE);
        roleID = getArguments().getString(IntentUtils.getInstance().ROLE_MANAGE_ID);
//        if (StringUtils.isNoEmpty(typeData) && (typeData.equals("2") || typeData.equals("3"))) {
//            tvRight.setText("查看所有");
//            tvRight.setVisibility(View.VISIBLE);
        if (!StringUtils.isNoEmpty(typeData) && isManage) {
            imgMessage.setVisibility(View.GONE);
            imgAdd.setVisibility(View.GONE);
        }
//        }
        allAdapter = new CustomerAllAdapter(getActivity(), list, isManage,userId, R.layout.item_customer_manager);
        lvCustomer.setAdapter(allAdapter);
        lvCustomer.setDividerHeight(0);

        lvCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentUtils.getInstance().toActivity(getActivity(), CustomerDetailsActivity.class, list.get(i), typeData);
            }
        });
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getCustomer();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getCustomer();
            }
        });

        listTitle.add("全部客户");
        listTitle.add("风险客户");
        listTitle.add("一般客户");
        listTitle.add("主要客户");
        listTitle.add("重要客户");
        listTitle.add("无意向客户(我的)");
        listTitle.add("无意向客户(其他)");

        //索要
        allAdapter.setPutCallBack(new OnPositionClick() {
            @Override
            public void onPositionClick(final int position) {
                CopyIosDialogUtils.getInstance().getIosDialog(getActivity(), "是否索要该客户", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        putCustomer(list.get(position).getId(), list.get(position).getSaleManId(), PersonConstant.getInstance().getUserId());
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }
        });
    }

    @OnClick({R.id.ll_back, R.id.iv_right, R.id.tv_right, R.id.iv_message, R.id.tv_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                getActivity().finish();
                break;
            case R.id.iv_right:
                IntentUtils.getInstance().toActivity(getActivity(), AddCustomerActivity.class);
                break;
            case R.id.iv_message:
                IntentUtils.getInstance().toActivity(getActivity(), CustomerMessageActivity.class);
                break;
            case R.id.tv_title:
                DialogUtils.getInstance().getBaseDialog(getActivity(), listTitle, new StringPositionCallBack() {
                    @Override
                    public void onStringPosition(String str, int position) {
                        tvTitle.setText(str + " ");
                        type = position + "";
                        switch (str) {
                            case "全部客户":
                                type = "";
                                break;
                            case "风险客户":
                                type = "1";
                                break;
                            case "一般客户":
                                type = "2";
                                break;
                            case "主要客户":
                                type = "3";
                                break;
                            case "重要客户":
                                type = "4";
                                break;
                            case "无意向客户(我的)":
                                type = "0";
                                isMe = 1;
                                break;
                            case "无意向客户(其他)":
                                type = "0";
                                isMe = 0;
                                break;
                        }
                        page = 1;
                        getCustomer();
                    }
                });
                break;
            case R.id.tv_right:
                IntentUtils.getInstance().toRoleTypeActivity(getActivity(), SignManActivity.class, roleID, "成员列表", "", "2", "saleManage");
                break;
        }
    }

    public void getCustomer() {
        Map<String, Object> map = new HashMap<>();
        map.put("saleId", userId);
        map.put("pageIndex", page);
        map.put("pageSize", 20);
        map.put("isMe", isMe);
        map.put("customerType", type);
        String json = JSON.toJSONString(map);

        OkhttpUtil.getInstance().basePostCall(getActivity(), json, UrlConstant.getInstance().GET_ALL_CUSTOMER, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e("客户数据：" + str);
                if (page == 1) {
                    list.clear();
                }
                AllCustomerBean customerBean = JSON.parseObject(str, AllCustomerBean.class);
                if (customerBean.getD().getSuccess() == 1) {
                    list.addAll(customerBean.getD().getReList());
                    allAdapter.notifyDataSetChanged();
                }
                SmartUtils.finishSmart(smartRefreshLayout);
            }

            @Override
            public void onFail() {
                SmartUtils.finishSmart(smartRefreshLayout);
            }
        });
    }


    //索要客户
    public void putCustomer(int custId, int saleId, String applyUserId) {
        Map<String, Object> map = new HashMap<>();
        map.put("CustomerId", custId);
        map.put("ApplyUserId", applyUserId);
        map.put("SaleManId", saleId);
        String json = JSON.toJSONString(map);
        String putJson = "{\"model\":" + json + "}";
        OkhttpUtil.getInstance().basePostCall(getActivity(), putJson, UrlConstant.getInstance().CUSTOMER_DATA_URL + "AddtransferApplye", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                SuccessDBean dBean = JSON.parseObject(str, SuccessDBean.class);
                if (dBean.getD().getResult().equals("sucess")) {
                    ToastUtils.getMineToast("索要成功");
                    page = 1;
                    getCustomer();
                } else {
                    ToastUtils.getMineToast("索要失败");
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        page = 1;
        getCustomer();
    }
}
