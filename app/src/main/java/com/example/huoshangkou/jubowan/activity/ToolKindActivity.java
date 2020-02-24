package com.example.huoshangkou.jubowan.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.ToolListAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.OrderTypeBean;
import com.example.huoshangkou.jubowan.bean.TypeBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：ToolKindActivity
 * 描述：
 * 创建时间：2018-03-06  08:41
 */

public class ToolKindActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.grid_view)
    GridView gridView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @Bind(R.id.tv_right)
    TextView tvRight;
    List<OrderTypeBean> list;
    ToolListAdapter toolAdapter;

    //是否是品类
    private boolean isKind = true;
    //是否是类型
    private boolean isType = false;
    private String classId = "";

    @Override
    public int initLayout() {
        return R.layout.activity_tool_list;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        classId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        if (StringUtils.isNoEmpty(classId)) {
            isKind = false;
            isType = true;
            tvTitle.setText("设备类型");
        } else {
            isType = false;
            isKind = true;
            tvTitle.setText("设备品类");
        }
        list = new ArrayList<>();
        toolAdapter = new ToolListAdapter(getContext(), list, R.layout.item_buy_tools, isType);
        gridView.setAdapter(toolAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (isKind && !isType) {
                    IntentUtils.getInstance().toActivity(getContext(), ToolKindActivity.class, list.get(i).getID() + "");
                } else {
                    IntentUtils.getInstance().toActivity(getContext(), SearchToolsActivity.class, list.get(i).getID() + "", classId, list.get(i).getName());
                }
            }
        });

        if (isKind && !isType) {
            getClassData();
        } else if (!isKind && isType) {
            getTypeRight(classId);
        }

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isKind && !isType) {
                    getClassData();
                } else if (!isKind && isType) {
                    getTypeRight(classId);
                }
            }
        });
    }

    //获取品类
    private void getClassData() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_MAINTAIN_CLASS
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                TypeBean typeBean = JSON.parseObject(json, TypeBean.class);
                list.clear();
                list.addAll(typeBean.getReList());
                toolAdapter.notifyDataSetChanged();
                swipeRefresh.setRefreshing(false);
            }

            @Override
            public void onFail() {

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

    private void getTypeRight(String classId) {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                        + PostConstant.getInstance().GET_MAINTAIN_TYPE
                        + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                        + FieldConstant.getInstance().CLASS_ID + "=" + classId
                , new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        TypeBean typeBean = JSON.parseObject(json, TypeBean.class);
                        list.clear();
                        list.addAll(typeBean.getReList());
                        toolAdapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }

                    @Override
                    public void onFail() {

                    }
                });
    }
}
