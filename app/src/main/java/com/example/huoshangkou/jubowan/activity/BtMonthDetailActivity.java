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
import com.example.huoshangkou.jubowan.adapter.BtMonthDetailAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.BtDetailBean;
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
 * 类名：BtMonthDetailActivity
 * 描述：
 * 创建时间：2019-09-26  17:30
 */

public class BtMonthDetailActivity extends BaseActivity {
    @Bind(R.id.smart)
    SmartRefreshLayout smartRefreshLayout;
    @Bind(R.id.lv_bt_detail)
    ListView lvBt;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_right)
    ImageView ivRight;

    private String userId = "";
    private String keyWord = "";
    private int page = 1;
    private List<BtDetailBean.DBean.ResultBean> list = new ArrayList<>();
    private BtMonthDetailAdapter detailAdapter;
    private String depId = "";
    private String typeId = "0";

    @Override
    public int initLayout() {
        return R.layout.activity_month_bt;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ivRight.setImageResource(R.mipmap.search_icon_2);
        tvTitle.setText("月白条授信额度");
        String customerManageUserId = PersonConstant.getInstance().getCustomerManageUserId();
        userId = StringUtils.isNoEmpty(customerManageUserId) ? customerManageUserId : PersonConstant.getInstance().getUserId();
        depId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        typeId = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        detailAdapter = new BtMonthDetailAdapter(this, list, R.layout.item_bt_month_detail);
        lvBt.setAdapter(detailAdapter);
        lvBt.setDividerHeight(0);
        getNumsDetail();
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
                break;
            case R.id.iv_right:
                Intent intent = new Intent(this, SearchTimeKeyActivity.class);
                intent.putExtra(IntentUtils.getInstance().TYPE, "btDetail");
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
                page = 1;
                getNumsDetail();
                break;
        }
    }

    //获取注册数 或者 转化数详情
    public void getNumsDetail() {
        Map<String, Object> map = new HashMap<>();
        map.put("UserId", userId);
        map.put("PageIndex", page);
        map.put("AdminId", typeId);
        map.put("AreaRoleId", depId);
        map.put("PageSize", "20");
        map.put("Keyword", keyWord);
        String json = "{pagedInput:" + JSON.toJSONString(map) + "}";
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().CUSTOMER_DATA_CENTER_URL + "GetIousCrediedPaged", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                if (page == 1) {
                    list.clear();
                }
                BtDetailBean detailBean = JSON.parseObject(str, BtDetailBean.class);
                list.addAll(detailBean.getD().getResult());
                detailAdapter.notifyDataSetChanged();
                SmartUtils.finishSmart(smartRefreshLayout);
            }

            @Override
            public void onFail() {
                SmartUtils.finishSmart(smartRefreshLayout);
            }
        });
    }
}
