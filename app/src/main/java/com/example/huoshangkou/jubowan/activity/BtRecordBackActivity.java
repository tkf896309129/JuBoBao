package com.example.huoshangkou.jubowan.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.BtRecordBackAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.BackMoneyBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：BtRecordBackActivity
 * 描述：
 * 创建时间：2018-08-22  11:41
 */

public class BtRecordBackActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.lv_record_back)
    ListView lvBackRecord;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.swip_refresh)
    SwipeRefreshLayout refreshLayout;

    BtRecordBackAdapter recordBackAdapter;
    List<BackMoneyBean.ReObjBean> list;

    private String orderId = "";

    @Override
    public int initLayout() {
        return R.layout.activity_bt_back_record;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("还款记录");
        orderId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        list = new ArrayList<>();
        recordBackAdapter = new BtRecordBackAdapter(this, list, R.layout.item_bt_back_record);
        lvBackRecord.setAdapter(recordBackAdapter);
        lvBackRecord.setDividerHeight(0);

        getBackRecord(orderId);
        refreshLayout.setOnRefreshListener(this);

    }

    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }

    //获取还款记录数据
    public void getBackRecord(String orderId) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(this, UrlConstant.getInstance().URL
                + PostConstant.getInstance().BACK_RECORD
                + FieldConstant.getInstance().ID + "=" + orderId, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                BackMoneyBean backMoneyBean = JSON.parseObject(json, BackMoneyBean.class);
                list.clear();
                list.addAll(backMoneyBean.getReObj());
                recordBackAdapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFail() {
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        getBackRecord(orderId);
    }
}
