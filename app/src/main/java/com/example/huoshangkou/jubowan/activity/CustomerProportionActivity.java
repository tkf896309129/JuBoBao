package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.CustomerDataAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.CustomerProportionBean;
import com.example.huoshangkou.jubowan.bean.CustomerProportionListBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.view.MyPieChart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：CustomerProportionActivity
 * 描述：
 * 创建时间：2019-08-26  17:27
 */

public class CustomerProportionActivity extends BaseActivity {
    @Bind(R.id.circle_customer)
    MyPieChart pieChart;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_customer_data)
    ListView lvCustomerData;

    List<CustomerProportionListBean> list = new ArrayList<>();
    CustomerDataAdapter dataAdapter;

    private String userId = "";
    private int[] colors = {R.color.data_orange, R.color.yellow_deep, R.color.data_blue, R.color.red, R.color.gray, R.color.data_blue};
    private int[] radius = {90, 80, 70, 80, 75, 85};
    private String depId = "";
    private String typeId = "0";


    @Override
    public int initLayout() {
        return R.layout.activity_customer_proportion;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("客户类型比");
        String customerManageUserId = PersonConstant.getInstance().getCustomerManageUserId();
        depId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        typeId = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        if (StringUtils.isNoEmpty(customerManageUserId)) {
            userId = customerManageUserId;
        } else {
            userId = PersonConstant.getInstance().getUserId();
        }
        dataAdapter = new CustomerDataAdapter(this, list, R.layout.item_customer_data);
        lvCustomerData.setAdapter(dataAdapter);
        lvCustomerData.setDividerHeight(0);

        getSaleProportion();
    }

    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }

    //获取销售占比数据
    public void getSaleProportion() {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("adminId", typeId);
        map.put("areaRoleId", depId);
        String json = "{\n" +
                "\"saleInput\":" + JSON.toJSONString(map) + "}";
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().CUSTOMER_DATA_CENTER_URL + "GetCustomerTypeRatios", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                CustomerProportionBean proportionBean = JSON.parseObject(str, CustomerProportionBean.class);
                List<MyPieChart.PieEntry> pieEntriesChange = new ArrayList<>();
                List<CustomerProportionBean.DBean.ResultBean> result = proportionBean.getD().getResult();
                int num = result.size();
                for (int i = 0; i < num; i++) {
                    CustomerProportionListBean listBean = new CustomerProportionListBean();
                    listBean.setColor(colors[i]);
                    listBean.setCount(result.get(i).getCount());
                    listBean.setRatio(result.get(i).getRatio());
                    listBean.setName(result.get(i).getCustomerTypeName());
                    pieEntriesChange.add(new MyPieChart.PieEntry(result.get(i).getCount(), colors[i], true, radius[i], result.get(i).getCustomerTypeName(), result.get(i).getRatio()));
                    if (result.get(i).getCount() > 0) {
                        list.add(listBean);
                    }
                }
                dataAdapter.notifyDataSetChanged();

                pieChart.setPieEntries(pieEntriesChange);
            }

            @Override
            public void onFail() {

            }
        });

    }
}
