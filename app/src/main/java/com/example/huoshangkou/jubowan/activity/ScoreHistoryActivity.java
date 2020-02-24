package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.ScoreHistoryAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ScoreHistoryBean;
import com.example.huoshangkou.jubowan.bean.ScoreHistoryListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：ScoreHistoryActivity
 * 描述：积分历史
 * 创建时间：2017-04-10  11:30
 */

public class ScoreHistoryActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.x_refresh)
    XRefreshView xRefresh;
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;

    //    String url = "https://atjubo.com:8443/atapi/GetLT_Integral_History/?Userid=234&PageSize=1";
    String url = "";

    ScoreHistoryAdapter historyAdapter;
    private int pageSize = 1;
    List<ScoreHistoryListBean> historyListBeanList;

    @Override
    public int initLayout() {
        return R.layout.activity_score_history;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("积分历史");
        historyListBeanList = new ArrayList<>();

        historyAdapter = new ScoreHistoryAdapter(getContext(), historyListBeanList, R.layout.item_score_history);
        lvRefresh.setAdapter(historyAdapter);

        lvRefresh.setDividerHeight(0);



        xRefresh.setAutoRefresh(true);

        xRefresh.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                pageSize = 1;
                historyListBeanList.clear();
                getScoreHistory();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                pageSize++;
                getScoreHistory();
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


    //获取积分历史数据
    public void getScoreHistory() {

        url = UrlConstant.getInstance().URL + PostConstant.getInstance().SCORE_HISTORY
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().PAGE_SIZE + "=" + pageSize;


        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), url, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                ScoreHistoryBean historyBean = JSON.parseObject(json, ScoreHistoryBean.class);
                historyListBeanList.addAll(historyBean.getReList());
                historyAdapter.notifyDataSetChanged();
                if (xRefresh != null) {
                    xRefresh.stopLoadMore();
                    xRefresh.stopRefresh();
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


}
