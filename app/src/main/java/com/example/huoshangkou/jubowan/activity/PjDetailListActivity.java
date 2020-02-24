package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.BuyToolAdapter;
import com.example.huoshangkou.jubowan.adapter.PjDetailListAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.PjBean;
import com.example.huoshangkou.jubowan.bean.PjListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：PjDetailListActivity
 * 描述：
 * 创建时间：2017-11-28  09:38
 */

public class PjDetailListActivity extends BaseActivity {

    List<PjListBean> list;
    PjDetailListAdapter toolAdapter;
    @Bind(R.id.grid_view)
    GridView gridView;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    private String classId = "";
    private int pageIndex = 1;

    @Override
    public int initLayout() {
        return R.layout.activity_pj_detail_list;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        list = new ArrayList<>();
        tvTitle.setText(getIntent().getStringExtra(IntentUtils.getInstance().VALUE));

        classId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        toolAdapter = new PjDetailListAdapter(getContext(), list);
        gridView.setAdapter(toolAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentUtils.getInstance().toActivity(getContext(), PjDetailsActivity.class, list.get(i).getClassID(), list.get(i).getID());
            }
        });

        pjList(classId);
    }

    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }


    //配件列表
    public void pjList(String classId) {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_PJ_LIST
                + FieldConstant.getInstance().CLASS_ID + "=" + classId, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                PjBean pjBean = JSON.parseObject(json, PjBean.class);
                list.addAll(pjBean.getReList());
                toolAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
    }

}
