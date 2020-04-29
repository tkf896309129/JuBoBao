package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.DataLineAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.RecentlyTradeBean;
import com.example.huoshangkou.jubowan.bean.RecentlyTwoBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.MoneyUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.StudyBendLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：RecentlyTradeActivity
 * 描述：
 * 创建时间：2019-08-27  08:33
 */

public class RecentlyTradeActivity extends BaseActivity {
    //    @Bind(R.id.tab_view)
//    TableView tableView;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.study_view)
    StudyBendLine bendLine;
    @Bind(R.id.lv_data_line)
    ListView lvDataLine;

    private DataLineAdapter lineAdapter;
    private String type = "";
    private String title = "";
    private String userId = "";
    private String depId = "0";
    private String typeId = "0";

    ArrayList<String> time = new ArrayList<>();
    ArrayList<Long> score = new ArrayList<>();
    List<RecentlyTwoBean> list = new ArrayList<>();

    @Override
    public int initLayout() {
        return R.layout.activity_recently_trade;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        type = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        title = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        depId = getIntent().getStringExtra(IntentUtils.getInstance().STR);
        typeId = getIntent().getStringExtra(IntentUtils.getInstance().TXT);

        tvTitle.setText("全年" + title);
        String customerManageUserId = PersonConstant.getInstance().getCustomerManageUserId();
        if (StringUtils.isNoEmpty(customerManageUserId)) {
            userId = customerManageUserId;
        } else {
            userId = PersonConstant.getInstance().getUserId();
        }

        lineAdapter = new DataLineAdapter(this, list, R.layout.item_data_line);
        lvDataLine.setAdapter(lineAdapter);
        lvDataLine.setDividerHeight(0);

        getSixData();
    }

    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }

    //获取近六个月的数据图饼
    public void getSixData() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("chartType", type);
        map.put("adminId", typeId);
        map.put("areaRoleId", depId == null ? "0" : depId);
        String json = "{\n" +
                "\"saleInput\":" + JSON.toJSONString(map) + "}";
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().CUSTOMER_DATA_CENTER_URL + "GetRecentSixMonthData", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                RecentlyTradeBean tradeBean = JSON.parseObject(str, RecentlyTradeBean.class);
                List<RecentlyTradeBean.DBean.ResultBean> result = tradeBean.getD().getResult();
                int num = result.size();
                for (int i = 0; i < num; i++) {
                    RecentlyTwoBean twoBean = new RecentlyTwoBean();
                    twoBean.setMonth(result.get(i).getCurrentMonth() + "月");
                    if (type.equals("0")) {
                        twoBean.setNums(MoneyUtils.getInstance().getFormPrice(result.get(i).getAmountOfTransacte() + ""));
                    } else {
                        twoBean.setNums(result.get(i).getAnalyticalValue() + "");
                    }
                    twoBean.setType(title);
                    list.add(twoBean);
                    time.add(result.get(i).getCurrentMonth() + "月");
                    if (type.equals("0")) {
                        score.add((long) result.get(i).getAmountOfTransacte());
                    } else {
                        score.add((long) result.get(i).getAnalyticalValue());
                    }
                }
                lineAdapter.notifyDataSetChanged();
                if (bendLine != null) {
                    bendLine.updateTime(score, time);
                }
            }

            @Override
            public void onFail() {

            }
        });
    }
}
