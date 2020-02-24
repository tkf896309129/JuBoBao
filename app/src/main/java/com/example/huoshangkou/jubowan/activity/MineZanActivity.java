package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.MessageFunction;
import com.example.huoshangkou.jubowan.adapter.MineZanAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.MessageTypeBean;
import com.example.huoshangkou.jubowan.bean.MessageTypeListBean;
import com.example.huoshangkou.jubowan.inter.StringCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：MineZanActivity
 * 描述：我的点赞
 * 创建时间：2017-04-21  13:48
 */

public class MineZanActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.x_refresh)
    XRefreshView xRefresh;
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;

    List<MessageTypeListBean> notifyList;
    MineZanAdapter zanAdapter;

    private String messageType = "";
    private int page = 1;

    @Override
    public int initLayout() {
        return R.layout.activity_mine_zan;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        notifyList = new ArrayList<>();
        tvTitle.setText("系统通知");

        zanAdapter = new MineZanAdapter(getContext(), notifyList, R.layout.item_mine_zan);
        lvRefresh.setAdapter(zanAdapter);
        lvRefresh.setDividerHeight(0);

        getOrderNotify();

        xRefresh.setPullLoadEnable(true);

        xRefresh.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                page = 1;
                notifyList.clear();
                getOrderNotify();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                page++;
                getOrderNotify();
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

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


    //获取订单通知
    public void getOrderNotify() {

        MessageFunction.getInstance().getOrderNotify(getContext(), messageType, page, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                MessageTypeBean typeBean = JSON.parseObject(str, MessageTypeBean.class);

                notifyList.addAll(typeBean.getReList());
                zanAdapter.notifyDataSetChanged();
                if (xRefresh != null) {
                    xRefresh.stopRefresh();
                    xRefresh.stopLoadMore();
                }
            }

            @Override
            public void onFail() {
                if (xRefresh != null) {
                    xRefresh.stopRefresh();
                    xRefresh.stopLoadMore();
                }
            }
        });
    }
}
