package com.example.huoshangkou.jubowan.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.ProjectGlassAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ProjectGlassBean;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SmartUtils;
import com.example.huoshangkou.jubowan.utils.TimeDialogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
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
 * 类名：ProjectGlassActivity
 * 描述：
 * 创建时间：2019-03-26  09:21
 */

public class ProjectGlassActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.lv_project_glass)
    ListView lvProjectGlass;
    @Bind(R.id.smart)
    SmartRefreshLayout smart;

    ProjectGlassAdapter glassAdapter;
    List<ProjectGlassBean.DBean.ResultBean> list;

    private int page = 1;

    @Override
    public int initLayout() {
        return R.layout.activity_project_glass;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("工程玻璃");
        ivRight.setImageResource(R.mipmap.search_icon_2);
        ivRight.setVisibility(View.GONE);
        list = new ArrayList<>();

        glassAdapter = new ProjectGlassAdapter(this, list, R.layout.item_project_glass);
        lvProjectGlass.setAdapter(glassAdapter);
        lvProjectGlass.setDividerHeight(0);
        lvProjectGlass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentUtils.getInstance().toWebActivity(ProjectGlassActivity.this, list.get(i).getPageUrl(), list.get(i).getCompany());
            }
        });
        getFactory();

        smart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getFactory();
            }
        });
        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getFactory();
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

    public void getFactory() {
        Map<String, String> map = new HashMap<>();
        map.put("pageIndex", page + "");
        map.put("pageSize", "20");
        OkhttpUtil.getInstance().basePostCall(this, map, UrlConstant.getInstance().JU_BIG_BRAND + "GetFactory", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                if (page == 1) {
                    list.clear();
                }
                ProjectGlassBean glassBean = JSON.parseObject(str, ProjectGlassBean.class);
                if (glassBean.getD().getErrorCode() != 0) {
                    ToastUtils.getMineToast(glassBean.getD().getMsg());
                    return;
                }
                list.addAll(glassBean.getD().getResult());
                glassAdapter.notifyDataSetChanged();
                SmartUtils.finishSmart(smart);
            }

            @Override
            public void onFail() {
                SmartUtils.finishSmart(smart);
            }
        });
    }
}
