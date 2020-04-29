package com.example.huoshangkou.jubowan.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.BtMonthDetailActivity;
import com.example.huoshangkou.jubowan.activity.CustomerProportionActivity;
import com.example.huoshangkou.jubowan.activity.MonthBtMoneyActivity;
import com.example.huoshangkou.jubowan.activity.MonthRegitActivity;
import com.example.huoshangkou.jubowan.activity.MonthSaleDetailActivity;
import com.example.huoshangkou.jubowan.activity.RecentlyTradeActivity;
import com.example.huoshangkou.jubowan.adapter.CustomerDataNewAdapter;
import com.example.huoshangkou.jubowan.adapter.DataHomeListAdapter;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.bean.CheckGroupBean;
import com.example.huoshangkou.jubowan.bean.CustomerDataListBean;
import com.example.huoshangkou.jubowan.bean.CustomerHomeDataBean;
import com.example.huoshangkou.jubowan.bean.DReobjBean;
import com.example.huoshangkou.jubowan.bean.MineTypeBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.inter.StringPositionCallBack;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment
 * 类名：CustomerDataFragment
 * 描述：
 * 创建时间：2019-08-26  11:28
 */

public class CustomerDataFragment extends BaseFragment {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.grid_data)
    ScrollGridView gridData;
    @Bind(R.id.lv_current)
    ScrollGridView gridDataTop;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.iv_down)
    ImageView imgDown;
    
    List<CustomerDataListBean> list = new ArrayList<>();
    List<CustomerHomeDataBean.DBean.ResultBean.ItemsBean> currentList = new ArrayList<>();
    CustomerDataNewAdapter dataNewAdapter;
    DataHomeListAdapter currentAdapter;

    private int[] images = {R.mipmap.data_regit_num, R.mipmap.data_zh_numbers, R.mipmap.data_trade, R.mipmap.data_customer, R.mipmap.data_bt};
    private int[] imagesMonth = {R.mipmap.sale_money, R.mipmap.bt_month_money, R.mipmap.regit_num, R.mipmap.zh_nums};
    private String[] names = {"注册数", "转化数", "交易额", "客户类型比", "白条售额信息"};
    private String[] sort = {"月销售额", "月白条授信额度", "月注册数", "月转化数"};
    private String type = "";
    private String userId = "";
    private String typeData = "";
    private String roleID = "";
    private String depId = "0";
    private String depName = "";
    private String typeId = "0";
    private boolean isManage = false;

    private List<String> nameType = new ArrayList<>();
    private List<String> nameId = new ArrayList<>();
    private List<CheckGroupBean.ReObjBean.DepListBean> depList = new ArrayList<>();

    public static CustomerDataFragment newInstance() {
        CustomerDataFragment fragment = new CustomerDataFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_customer_data_new;
    }
    
    @Override
    public void initData() {
        tvTitle.setText("个人数据");
        String customerManageUserId = PersonConstant.getInstance().getCustomerManageUserId();
        if (StringUtils.isNoEmpty(customerManageUserId)) {
            userId = customerManageUserId;
        } else {
            userId = PersonConstant.getInstance().getUserId();
        }
        typeData = getArguments().getString(IntentUtils.getInstance().ROLE_MANAGE_TYPE);
        roleID = getArguments().getString(IntentUtils.getInstance().ROLE_MANAGE_ID);
//        depId = getArguments().getString(IntentUtils.getInstance().TYPE);
        depName = getArguments().getString(IntentUtils.getInstance().VALUE);

        for (int i = 0; i < images.length; i++) {
            list.add(new CustomerDataListBean(names[i], images[i]));
        }

        currentAdapter = new DataHomeListAdapter(getActivity(), currentList, R.layout.item_data_home);
        gridDataTop.setAdapter(currentAdapter);

        gridDataTop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (currentList.get(i).getSatatisTypeName()) {
                    case "月销售额":
                        IntentUtils.getInstance().toActivity(getActivity(), MonthSaleDetailActivity.class, depId, typeId);
                        break;
                    case "月白条授信额度":
                        IntentUtils.getInstance().toActivity(getActivity(), BtMonthDetailActivity.class, depId, typeId);
                        break;
                    case "月注册数":
                        IntentUtils.getInstance().toActivity(getActivity(), MonthRegitActivity.class, "1", depId, typeId);
                        break;
                    case "月转化数":
                        IntentUtils.getInstance().toActivity(getActivity(), MonthRegitActivity.class, "2", depId, typeId);
                        break;
                }
            }
        });

        dataNewAdapter = new CustomerDataNewAdapter(getActivity(), list, R.layout.item_customer_data_new);
        gridData.setAdapter(dataNewAdapter);

        gridData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (list.get(i).getName()) {
                    case "客户类型比":
                        IntentUtils.getInstance().toActivity(getActivity(), CustomerProportionActivity.class, depId, typeId);
                        break;
                    case "白条售额信息":
                        IntentUtils.getInstance().toActivity(getActivity(), MonthBtMoneyActivity.class, depId, typeId);
                        break;
                    case "注册数":
                        type = "1";
                        IntentUtils.getInstance().toActivity(getActivity(), RecentlyTradeActivity.class, type, list.get(i).getName(), depId, typeId);
                        break;
                    case "转化数":
                        type = "2";
                        IntentUtils.getInstance().toActivity(getActivity(), RecentlyTradeActivity.class, type, list.get(i).getName(), depId, typeId);
                        break;
                    case "交易额":
                        type = "0";
                        IntentUtils.getInstance().toActivity(getActivity(), RecentlyTradeActivity.class, type, list.get(i).getName(), depId, typeId);
                        break;
                }
            }
        });

        getDaySignTimes();
        getMemberData();
        getCustomerData();
    }

    //获取数据
    public void getCustomerData() {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("areaRoleId", depId);
        map.put("adminId", typeId);
        String json = "{\n" +
                "\"saleInput\":" + JSON.toJSONString(map) + "}";
        OkhttpUtil.getInstance().basePostCall(getActivity(), json, UrlConstant.getInstance().CUSTOMER_DATA_CENTER_URL + "GetAppSaleStatisics", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e("首页数据：" + str);
                CustomerHomeDataBean dataBean = JSON.parseObject(str, CustomerHomeDataBean.class);
                if (dataBean.getD().getResult() == null) {
                    return;
                }
                List<CustomerHomeDataBean.DBean.ResultBean.ItemsBean> items = dataBean.getD().getResult().getItems();
                int j = 0;
                currentList.clear();
                if (items.size() != 0) {
                    while (currentList.size() < 4) {
                        for (int i = 0; i < 4; i++) {
                            CustomerHomeDataBean.DBean.ResultBean.ItemsBean itemsBean = items.get(i);
                            if (items.get(i).getSatatisTypeName().equals(sort[j])) {
                                itemsBean.setImg(imagesMonth[j]);
                                currentList.add(itemsBean);
                                break;
                            }
                        }
                        j++;
                    }
                }
                currentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
    }

    public void getDaySignTimes() {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        String json = JSON.toJSONString(map);
        OkhttpUtil.getInstance().basePostCall(getActivity(), json, UrlConstant.getInstance().CUSTOMER_MANAGE_URL + "IsManager", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e("身份权限：" + str);
                DReobjBean dReobjBean = JSON.parseObject(str, DReobjBean.class);
                int roleTypeManager = dReobjBean.getD().getReObj().getType();
                if ((roleTypeManager == 3 || roleTypeManager == 2) && imgDown != null) {
                    imgDown.setVisibility(View.VISIBLE);
                    isManage = true;
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    //获取员工数据
    public void getMemberData() {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(getActivity(), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GROUP_PRO
                + FieldConstant.getInstance().TYPE + "=&"
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().GROUP_ID + "=5", new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                LogUtils.e(json);
                CheckGroupBean groupBean = JSON.parseObject(json, CheckGroupBean.class);
                depList.addAll(groupBean.getReObj().getDepList());
                int size = depList.size();
                nameType.add("个人数据");
                nameType.add("公司数据");
                for (int i = 0; i < size; i++) {
                    nameType.add(depList.get(i).getRoleName());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    @OnClick({R.id.ll_back, R.id.tv_title})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                getActivity().finish();
                break;
            case R.id.tv_title:
                if (!isManage) {
                    return;
                }
                DialogUtils.getInstance().getBaseDialog(getActivity(), nameType, new StringPositionCallBack() {
                    @Override
                    public void onStringPosition(String str, int position) {
                        typeId = position + "";
                        if (position > 1) {
                            depId = depList.get(position - 2).getID() + "";
                            typeId = "1";
                        } else {
                            depId = "0";
                        }
                        switch (str) {
                            case "个人数据":
                                typeId = "0";
                                break;
                            case "公司数据":
                                typeId = "2";
                                break;
                        }
                        tvTitle.setText(str);
                        getCustomerData();
                    }
                });
                break;
        }
    }
}
