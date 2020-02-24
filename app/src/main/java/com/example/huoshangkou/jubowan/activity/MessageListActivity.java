package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.MessageListAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.MoneyMessageBean;
import com.example.huoshangkou.jubowan.bean.MoneyMessageListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：MessageListActivity
 * 描述：
 * 创建时间：2017-12-15  10:22
 */
public class MessageListActivity extends BaseActivity {

    MessageListAdapter listAdapter;
    List<MoneyMessageListBean> listData;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_message_list)
    ListView lvMessageList;

    private String postConstant = "";
    private String checkType = "";

    @Override
    public int initLayout() {
        return R.layout.activity_message_list;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("信息列表");

        listData = new ArrayList<>();
        checkType = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        listAdapter = new MessageListAdapter(getContext(), listData, R.layout.item_money_message,checkType);
        lvMessageList.setAdapter(listAdapter);
        lvMessageList.setDividerHeight(0);

        if (checkType.equals("借据审批")) {
            postConstant = PostConstant.getInstance().GET_RZ_DUE_BILL;
        } else if (checkType.equals("信用额度")) {
            postConstant = PostConstant.getInstance().GET_RZ_ORDER;
        }


        lvMessageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra(IntentUtils.getInstance().BEAN_TYPE,listData.get(i));
                setResult(1,intent);
                MessageListActivity.this.finish();
            }
        });

        getMessageList();
    }

    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }

    //获取信息列表
    public void getMessageList() {

        OkhttpUtil.getInstance().setUnCacheData(getContext(), "数据加载中", UrlConstant.getInstance().URL
                + postConstant
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() , new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                LogUtils.i(json);
                MoneyMessageBean messageBean = JSON.parseObject(json, MoneyMessageBean.class);
                listData.addAll(messageBean.getReList());
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
    }


}
