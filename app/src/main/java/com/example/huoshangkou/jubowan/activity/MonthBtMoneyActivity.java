package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.DataBtBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.MoneyUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.view.CircleBarView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：MonthBtMoneyActivity
 * 描述：
 * 创建时间：2019-08-28  13:31
 */

public class MonthBtMoneyActivity extends BaseActivity {
    @Bind(R.id.cb_bt)
    CircleBarView barView;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tv_money)
    TextView tvMoney;
    @Bind(R.id.tv_total)
    TextView tvTotal;
    @Bind(R.id.tv_used)
    TextView tvUsed;

    private String userId = "";
    private String depId = "";
    private String typeId = "0";

    @Override
    public int initLayout() {
        return R.layout.activity_month_bt_money;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        String customerManageUserId = PersonConstant.getInstance().getCustomerManageUserId();
        depId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        typeId = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        if (StringUtils.isNoEmpty(customerManageUserId)) {
            userId = customerManageUserId;
        } else {
            userId = PersonConstant.getInstance().getUserId();
        }
        tvTitle.setText("白条售额信息");
        getBtMoney();
    }

    //湖区白条额度
    public void getBtMoney() {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("adminId", typeId);
        map.put("areaRoleId", depId);

        String json = "{\n" +
                "\"saleInput\":" + JSON.toJSONString(map) + "}";
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().CUSTOMER_DATA_CENTER_URL + "GetIousCreditAnalysis", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                DataBtBean btBean = JSON.parseObject(str, DataBtBean.class);
                DataBtBean.DBean.ResultBean result = btBean.getD().getResult();
                tvUsed.setText(MoneyUtils.getInstance().getFormPrice((result.getIousTotalAmount() - result.getShouldBackAmount()) + ""));
                tvMoney.setText(MoneyUtils.getInstance().getFormPrice(result.getShouldBackAmount() + ""));
                tvTotal.setText(MoneyUtils.getInstance().getFormPrice(result.getIousTotalAmount() + ""));
                barView.setProgressNum((float) (result.getShouldBackAmount()), 1000, (float) result.getIousTotalAmount());
            }

            @Override
            public void onFail() {

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
                IntentUtils.getInstance().toActivity(this, MonthBtRecordActivity.class);
                break;
        }
    }

}
