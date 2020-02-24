package com.example.huoshangkou.jubowan.activity;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.TieAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ForumBean;
import com.example.huoshangkou.jubowan.bean.ForumListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnForumItemClick;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
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
 * 类名：MineReceiveTieActivity
 * 描述：
 * 创建时间：2017-04-10  13:14
 */

public class MineReceiveTieActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.x_refresh)
    SmartRefreshLayout xRefresh;
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;

    TieAdapter tieAdapter;

    List<ForumListBean> detailBeanList;

    private boolean isReceiveTie = true;
    String url = "";

    String url_1 = "https://atjubo.com:8443/atapi/GetLT_PostCollection/?Userid=234&PageSize=1";

    String url_2 = "https://atjubo.com:8443/atapi/GetLT_PostList/?posttypeid=&Userid=234&hot=&PostState=1&PageSize=1";


    private int pageSize = 1;
    String tieType = "";

    @Override
    public int initLayout() {
        return R.layout.activity_mine_receive;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tieType = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        if (tieType.equals("Receive")) {
            isReceiveTie = true;
        } else {
            isReceiveTie = false;
        }

        if (isReceiveTie) {
            tvTitle.setText("我的收藏");
        } else {
            tvTitle.setText("我的帖子");
        }

        detailBeanList = new ArrayList<>();

        tieAdapter = new TieAdapter(getContext(), detailBeanList, R.layout.item_tie_layout);
        lvRefresh.setAdapter(tieAdapter);
        lvRefresh.setDividerHeight(0);

        //点击事件
        tieAdapter.setItemClick(new OnForumItemClick() {
            @Override
            public void onForumClick(int position) {
                IntentUtils.getInstance().toActivity(getContext(), TieDetailsActivity.class, detailBeanList.get(position));
            }

            @Override
            public void onMainZanClick(int position, String postId) {

            }
        });

        xRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageSize = 1;
                detailBeanList.clear();
                getReceiveData();
            }
        });

        xRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageSize++;
                getReceiveData();
            }
        });

        getReceiveData();
    }

    @OnClick({R.id.ll_back})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }


    //获取数据
    public void getReceiveData() {
        //收藏
        if (isReceiveTie) {
            url = UrlConstant.getInstance().URL + PostConstant.getInstance().GET_SAVE_TIE
                    + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                    + FieldConstant.getInstance().PAGE_SIZE + "=" + pageSize;
        } else {
            //我的帖子
            url = UrlConstant.getInstance().URL + PostConstant.getInstance().GET_MINE_TIE
                    + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                    + FieldConstant.getInstance().PAGE_SIZE + "=" + pageSize;
        }

        LogUtils.i(url);
        OkhttpUtil.getInstance().setUnCacheData(getContext(),getString(R.string.loading_message), url, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                ForumBean forumBean = JSON.parseObject(json, ForumBean.class);

                detailBeanList.addAll(forumBean.getReList());
                tieAdapter.notifyDataSetChanged();
                if (xRefresh != null) {
                    xRefresh.finishLoadMore();
                    xRefresh.finishRefresh();
                }

                if (llNoData == null) {
                    return;
                }
                if (detailBeanList.size() == 0) {
                    llNoData.setVisibility(View.VISIBLE);
                } else {
                    llNoData.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFail() {
                if (xRefresh != null) {
                    xRefresh.finishLoadMore();
                }
            }
        });
    }

}
