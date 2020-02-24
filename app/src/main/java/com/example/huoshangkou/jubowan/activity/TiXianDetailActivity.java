package com.example.huoshangkou.jubowan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.TiXianAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.TiXianBean;
import com.example.huoshangkou.jubowan.bean.TiXianListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：TiXianDetailActivity
 * 描述：
 * 创建时间：2017-05-26  11:20
 */

public class TiXianDetailActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.x_refresh)
    XRefreshView xRefresh;
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;
    private List<TiXianListBean> tiXianListBeanList;
    TiXianAdapter tiXianAdapter;

    @Override
    public int initLayout() {
        return R.layout.activity_tixian_detail;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("佣金明细");

        tiXianListBeanList = new ArrayList<>();
        tiXianAdapter = new TiXianAdapter(getContext(), tiXianListBeanList, R.layout.item_ti_xian);
        lvRefresh.setAdapter(tiXianAdapter);
        lvRefresh.setDividerHeight(0);


        xRefresh.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                tiXianListBeanList.clear();
                getTiXianRecord();
            }

            @Override
            public void onLoadMore(boolean isSilence) {

            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });

        getTiXianRecord();
    }


    //获取提现记录 + PersonConstant.getInstance().getUserId()
    public void getTiXianRecord() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_TIXIAN_LIST
                + FieldConstant.getInstance().USER_ID + "=5319", new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                TiXianBean tiXianBean = JSON.parseObject(json, TiXianBean.class);
                if (tiXianBean.getSuccess() == 1) {
                    tiXianListBeanList.addAll(tiXianBean.getReList());
                    tiXianAdapter.notifyDataSetChanged();

                    if (llNoData == null) {
                        return;
                    }

                    if (tiXianListBeanList.size() == 0) {
                        llNoData.setVisibility(View.VISIBLE);
                    } else {
                        llNoData.setVisibility(View.GONE);
                    }

                    xRefresh.stopRefresh();
                }
            }

            @Override
            public void onFail() {
                xRefresh.stopRefresh();
            }
        });
    }

    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }
}
