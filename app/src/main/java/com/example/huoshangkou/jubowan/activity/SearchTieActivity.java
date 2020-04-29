package com.example.huoshangkou.jubowan.activity;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
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
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.TieDetailConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnForumItemClick;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：SearchTieActivity
 * 描述：
 * 创建时间：2017-02-23  10:24
 */

public class SearchTieActivity extends BaseActivity implements TextView.OnEditorActionListener {
    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.x_refresh)
    XRefreshView xRefresh;
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;
    private int pageSize = 0;
    @Bind(R.id.et_search_tie)
    EditText etSearch;

    TieAdapter tieAdapter;

    //帖子相关
    List<ForumListBean> detailBeanList;

    private String keyWord = "";

    @Override
    public int initLayout() {
        return R.layout.activity_search_tie;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        detailBeanList = new ArrayList<>();
        tieAdapter = new TieAdapter(getContext(), detailBeanList, R.layout.item_tie_layout);
        lvRefresh.setAdapter(tieAdapter);
        lvRefresh.setDividerHeight(0);

        etSearch.setOnEditorActionListener(this);

        //点击事件
        tieAdapter.setItemClick(new OnForumItemClick() {
            @Override
            public void onForumClick(int position) {
                IntentUtils.getInstance().toActivity(getContext(), TieDetailsActivity.class, detailBeanList.get(position), TieDetailConstant.getInstance().SHARE);
            }

            @Override
            public void onMainZanClick(int position, String postId) {

            }
        });

        xRefresh.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                searchTie(keyWord);
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
    }

    //点击事件
    @OnClick({R.id.ll_back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            //返回按钮
            case R.id.ll_back:
                this.finish();
                break;
            //搜索
            case R.id.tv_right:
                keyWord = etSearch.getText().toString().trim();
                if (!StringUtils.isNoEmpty(keyWord)) {
                    ToastUtils.getMineToast("请输入搜索关键字");
                    return;
                }
                searchTie(keyWord);
                break;
        }
    }


    //搜索帖子
    public void searchTie(String keyWord) {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().SEARCH_TIE
                + FieldConstant.getInstance().KEY_WORD + "=" + EncodeUtils.getInstance().getEncodeString(keyWord) + "&"
                + FieldConstant.getInstance().PAGE_SIZE + "=" + pageSize, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                ForumBean forumBean = JSON.parseObject(json, ForumBean.class);
                detailBeanList.clear();
                detailBeanList.addAll(forumBean.getReList());
                tieAdapter.notifyDataSetChanged();
                if (xRefresh != null) {
                    xRefresh.stopRefresh();
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

            }
        });
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_DONE) {
            keyWord = etSearch.getText().toString().trim();
            if (!StringUtils.isNoEmpty(keyWord)) {
                ToastUtils.getMineToast("请输入搜索关键字");
                return true;
            }
            searchTie(keyWord);
            return true;
        }
        return false;
    }
}
