package com.example.huoshangkou.jubowan.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.WalletListAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.WalletBean;
import com.example.huoshangkou.jubowan.bean.WalletListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：WalletListActivity
 * 描述：
 * 创建时间：2017-10-19  13:19
 */

public class WalletListActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.x_refresh)
    SmartRefreshLayout xRefresh;
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;

    List<WalletListBean> list;
    WalletListAdapter listAdapter;

    @Override
    public int initLayout() {
        return R.layout.activity_wallet_list;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        list = new ArrayList<>();

        tvTitle.setText("余额明细");

        listAdapter = new WalletListAdapter(getContext(), list, R.layout.item_wallet_list);
        lvRefresh.setAdapter(listAdapter);
        lvRefresh.setDividerHeight(0);
//        xRefresh.setPullLoadEnable(true);

        lvRefresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (list.get(i).getAddorSub().equals("-")) {
                    IntentUtils.getInstance().toActivity(getContext(), WalletDetailActivity.class, list.get(i));
                } else if (list.get(i).getAddorSub().equals("+")) {
                    IntentUtils.getInstance().toActivity(getContext(), RedpacketDetailActivity.class, list.get(i).getLuckyMoney(),list.get(i).getCreateTime());
                }
            }
        });

        xRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                list.clear();
                getWalletList();
            }
        });
        xRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }
        });



        getWalletList();
    }

    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }


    //明细列表 
    public void getWalletList() {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(getContext(), UrlConstant.getInstance().URL
                + PostConstant.getInstance().WALLET_LIST + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                WalletBean walletBean = JSON.parseObject(json, WalletBean.class);
                list.addAll(walletBean.getReList());
                listAdapter.notifyDataSetChanged();
                if(xRefresh!=null){
                    xRefresh.finishRefresh();
                    xRefresh.finishLoadMore();
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

}
