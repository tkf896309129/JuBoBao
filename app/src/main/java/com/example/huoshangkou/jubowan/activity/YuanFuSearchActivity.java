package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.YuanFuSearchAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.YuanFuSearchBean;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SmartUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：YuanFuSearchActivity
 * 描述：
 * 创建时间：2019-04-28  13:36
 */

public class YuanFuSearchActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_yuan_search)
    ListView listView;
    @Bind(R.id.smart)
    SmartRefreshLayout smartRefreshLayout;
    @Bind(R.id.et_search)
    EditText etSearch;
    //
    private String id = "";
    private List<YuanFuSearchBean.DBean.ResultBean> list = new ArrayList<>();
    private YuanFuSearchAdapter searchAdapter;
    private String keyWord = "";

    @Override
    public int initLayout() {
        return R.layout.activity_yuan_fu_search;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("类型选择");
        id = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        searchAdapter = new YuanFuSearchAdapter(this, list, R.layout.item_company_list);
        listView.setAdapter(searchAdapter);
        listView.setDividerHeight(0);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra(IntentUtils.getInstance().TYPE, list.get(i).getValue());
                setResult(1, intent);
                YuanFuSearchActivity.this.finish();
            }
        });

        getYuanFuSearchData();

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                keyWord = charSequence.toString();
                getYuanFuSearchData();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void getYuanFuSearchData() {
        Map<String, String> map = new HashMap<>();
        map.put("classId", id);
        map.put("keyWord", keyWord);
        String json = JSON.toJSONString(map);
        LogUtils.e(json);
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().YUAN_FU_URL + "GetMoxiOrColor", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                list.clear();
                LogUtils.e(str);
                YuanFuSearchBean searchBean = JSON.parseObject(str, YuanFuSearchBean.class);
                list.addAll(searchBean.getD().getResult());
                searchAdapter.notifyDataSetChanged();
                SmartUtils.finishSmart(smartRefreshLayout);
            }

            @Override
            public void onFail() {
                SmartUtils.finishSmart(smartRefreshLayout);
            }
        });
    }

    public void searchData() {
        Map<String, String> map = new HashMap<>();
        map.put("keyWord", keyWord);
        String json = JSON.toJSONString(map);
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().YUAN_FU_URL + "SelectMoxiOrColor", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                list.clear();
                YuanFuSearchBean searchBean = JSON.parseObject(str, YuanFuSearchBean.class);
                list.addAll(searchBean.getD().getResult());
                searchAdapter.notifyDataSetChanged();
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
}
