package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.MonthRegitAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.MonthRegitBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SmartUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
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
 * 类名：MonthRegitActivity
 * 描述：
 * 创建时间：2019-09-26  13:25
 */

public class MonthRegitActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.smart)
    SmartRefreshLayout smartRefreshLayout;
    @Bind(R.id.lv_month_regit)
    ListView lvDetail;

    private String keyWord = "";
    private String startTime = "";
    private String endTime = "";
    private String userId = "";
    private String phone = "";
    private int page = 1;
    private String postUrl = "";

    private String startData = "";
    private String endData = "";
    private String type = "";
    private String depId = "";
    private String typeId = "0";

    private List<MonthRegitBean.DBean.ResultBean> list = new ArrayList<>();
    private MonthRegitAdapter regitAdapter;

    @Override
    public int initLayout() {
        return R.layout.activity_month_regit;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ivRight.setImageResource(R.mipmap.search_icon_2);

        type = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        depId = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        typeId = getIntent().getStringExtra(IntentUtils.getInstance().STR);
        String customerManageUserId = PersonConstant.getInstance().getCustomerManageUserId();
        String customerManagePhone = PersonConstant.getInstance().getCustomerManagePhone();
        userId = StringUtils.isNoEmpty(customerManageUserId) ? customerManageUserId : PersonConstant.getInstance().getUserId();
        phone = StringUtils.isNoEmpty(customerManagePhone) ? customerManagePhone : PersonConstant.getInstance().getPhone(this);
        //注册数
        switch (type) {
            //月注册数
            case "1":
                postUrl = "GetRegistrateCustomerPaged";
                tvTitle.setText("月注册");
                break;
            //月转化数
            case "2":
                postUrl = "GettConvertCustomerPaged";
                tvTitle.setText("月转化");
                break;
        }
        getNumsDetail();

        regitAdapter = new MonthRegitAdapter(this, list, type, R.layout.item_month_regit);
        lvDetail.setAdapter(regitAdapter);
        lvDetail.setDividerHeight(0);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getNumsDetail();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getNumsDetail();
            }
        });
    }

    @OnClick({R.id.ll_back, R.id.iv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;//主要还是
            case R.id.iv_right:
                Intent intent = new Intent(this, SearchTimeKeyActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case 1:
                keyWord = data.getStringExtra(IntentUtils.getInstance().TYPE);
                startTime = data.getStringExtra(IntentUtils.getInstance().VALUE);
                endTime = data.getStringExtra(IntentUtils.getInstance().STR);
                page = 1;
                getNumsDetail();
                break;
        }
    }

    //获取注册数 或者 转化数详情
    public void getNumsDetail() {
        Map<String, Object> map = new HashMap<>();
        map.put("StartDate", startTime);
        map.put("EndDate", endTime);
        map.put("UserId", userId);
        map.put("LoginName", phone);
        map.put("PageIndex", page);
        map.put("PageSize", "20");
        map.put("AdminId", typeId);
        map.put("AreaRoleId", depId);
        map.put("Keyword", keyWord);
        String json = "{pagedInput:" + JSON.toJSONString(map) + "}";
        OkhttpUtil.getInstance().basePostCallDialog(this, json, UrlConstant.getInstance().CUSTOMER_DATA_CENTER_URL + postUrl, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                MonthRegitBean regitBean = JSON.parseObject(str, MonthRegitBean.class);
                if (page == 1) {
                    list.clear();
                }
                list.addAll(regitBean.getD().getResult());
                regitAdapter.notifyDataSetChanged();
                if (smartRefreshLayout == null) {
                    return;
                }
                SmartUtils.finishSmart(smartRefreshLayout);
            }

            @Override
            public void onFail() {
                if (smartRefreshLayout == null) {
                    return;
                }
                SmartUtils.finishSmart(smartRefreshLayout);
            }
        });
    }
}
