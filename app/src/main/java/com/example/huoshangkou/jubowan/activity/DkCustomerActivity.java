package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.CompanyAdapter;
import com.example.huoshangkou.jubowan.adapter.DkCustomerAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.BankAccountBean;
import com.example.huoshangkou.jubowan.bean.CompanyListBean;
import com.example.huoshangkou.jubowan.bean.DkCustomerBean;
import com.example.huoshangkou.jubowan.bean.TradesBean;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SmartUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
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
 * 类名：DkCustomerActivity
 * 描述：
 * 创建时间：2019-03-05  09:05
 */

public class DkCustomerActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_dk_customer)
    ListView listView;
    @Bind(R.id.smart)
    SmartRefreshLayout smartRefreshLayout;
    @Bind(R.id.et_search)
    EditText etSearch;

    private DkCustomerAdapter searchAdapter;
    private String keyWord = "";
    private int page = 1;
    //审批人员
    List<String> memberList = new ArrayList<>();
    private String type = "";
    private String title = "";


    @Override
    public int initLayout() {
        return R.layout.activity_dk_customer;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        type = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        title = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        tvTitle.setText(title);
        //获取数据
//        switch (type) {
//            case "dk_customer":
//                tvTitle.setText(title);
//                break;
//            case "sk_account":
//                tvTitle.setText("收款账户");
//                break;
//            case "ck_account":
//                tvTitle.setText("出款账户");
//                break;
//            case "dk_company":
//                tvTitle.setText("打款公司");
//                break;
//            case "mys_trades":
//                tvTitle.setText("贸易商");
//                break;
//            case "sk_company":
//                tvTitle.setText("收款公司");
//                break;
//        }
        searchAdapter = new DkCustomerAdapter(this, memberList, R.layout.item_company_list);
        listView.setAdapter(searchAdapter);
        listView.setDividerHeight(0);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra(IntentUtils.getInstance().TYPE, memberList.get(i));
                setResult(1, intent);
                DkCustomerActivity.this.finish();
            }
        });
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                initTypeData();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initTypeData();
            }
        });
        initTypeData();
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                page = 1;
                keyWord = charSequence.toString();
                initTypeData();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    //获取数据
    public void initTypeData() {
        switch (type) {
            case "dk_customer":
                getPayAccount();
                break;
            case "sk_account":
            case "ck_account":
                getOutBank();
                break;
            case "dk_company":
                getGysBank();
                break;
            case "mys_trades":
                getTrades();
                break;
            case "sk_company":
                getSkCompany();
                break;
        }
    }

    @OnClick({R.id.ll_back, R.id.tv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_search:
                Intent intent = new Intent();
                intent.putExtra(IntentUtils.getInstance().TYPE, keyWord);
                setResult(1, intent);
                DkCustomerActivity.this.finish();
                break;
        }
    }

    //获取打款客户
    public void getPayAccount() {
        //fastJson将对象转成JSON字符串
        Map<String, String> map = new HashMap<>();
        map.put("keyword", keyWord);
        map.put("pageIndex", page + "");
        map.put("pageSize", "20");
        OkhttpUtil.getInstance().basePostCall(this, JSON.toJSONString(map), UrlConstant.getInstance().YW_URL + "GetPayingCustomerInfo", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                if (page == 1) {
                    memberList.clear();
                }
                DkCustomerBean customerBean = JSON.parseObject(str, DkCustomerBean.class);
                if (customerBean.getD() != null && customerBean.getD().getReList() != null) {
                    int num = customerBean.getD().getReList().size();
                    List<DkCustomerBean.DBean.ReListBean> reList = customerBean.getD().getReList();
                    for (int i = 0; i < num; i++) {
                        memberList.add(reList.get(i).getCompany());
                    }
                    searchAdapter.notifyDataSetChanged();
                }
                SmartUtils.finishSmart(smartRefreshLayout);
            }

            @Override
            public void onFail() {
                SmartUtils.finishSmart(smartRefreshLayout);
            }
        });
    }

    //获取出款账户
    public void getOutBank() {
        Map<String, String> map = new HashMap<>();
        map.put("keyword", keyWord);
        map.put("pageIndex", page + "");
        map.put("pageSize", "20");
        OkhttpUtil.getInstance().basePostCall(getContext(), JSON.toJSONString(map), UrlConstant.getInstance().YW_URL + "GetPlatformBankAccounts", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                if (page == 1) {
                    memberList.clear();
                }
                LogUtils.e(str);
                BankAccountBean accountBean = JSON.parseObject(str, BankAccountBean.class);
                List<BankAccountBean.DBean.ReListBean> reList = accountBean.getD().getReList();
                if(reList==null){
                    return;
                }
                if (reList.size() == 0) {
                    return;
                }
                if(reList!=null){
                    int num = reList.size();
                    for (int i = 0; i < num; i++) {
                        memberList.add(reList.get(i).getMsg());
                    }
                }
                searchAdapter.notifyDataSetChanged();
                SmartUtils.finishSmart(smartRefreshLayout);
            }

            @Override
            public void onFail() {
                SmartUtils.finishSmart(smartRefreshLayout);
            }
        });
    }

    public void getGysBank() {
        Map<String, String> map = new HashMap<>();
        map.put("keyword", keyWord);
        map.put("pageIndex", page + "");
        map.put("pageSize", "20");
        OkhttpUtil.getInstance().basePostCall(getContext(), JSON.toJSONString(map), UrlConstant.getInstance().YW_URL + "GetCustomerBankAccounts", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                if (page == 1) {
                    memberList.clear();
                }
                BankAccountBean accountBean = JSON.parseObject(str, BankAccountBean.class);
                List<BankAccountBean.DBean.ReListBean> reList = accountBean.getD().getReList();

                if(reList!=null){
                    for (int i = 0; i < reList.size(); i++) {
                        memberList.add(reList.get(i).getMsg());
                    }
                }
                searchAdapter.notifyDataSetChanged();
                SmartUtils.finishSmart(smartRefreshLayout);
            }

            @Override
            public void onFail() {
                SmartUtils.finishSmart(smartRefreshLayout);
            }
        });
    }

    //获取所属贸易商
    public void getTrades() {
        //fastJson将对象转成JSON字符串
        Map<String, String> map = new HashMap<>();
        map.put("keyword", keyWord);
        map.put("pageIndex", page + "");
        map.put("pageSize", "20");
        OkhttpUtil.getInstance().basePostCall(this, JSON.toJSONString(map), UrlConstant.getInstance().YW_URL + "GetTrades", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                if (page == 1) {
                    memberList.clear();
                }
                LogUtils.e(str);
                TradesBean tradesBean = JSON.parseObject(str, TradesBean.class);
                List<TradesBean.DBean.ReListBean> reList = tradesBean.getD().getReList();
                if(reList!=null){
                    for (int i = 0; i < reList.size(); i++) {
                        memberList.add(reList.get(i).getCompany());
                    }
                }

                searchAdapter.notifyDataSetChanged();
                SmartUtils.finishSmart(smartRefreshLayout);
            }

            @Override
            public void onFail() {
                SmartUtils.finishSmart(smartRefreshLayout);
            }
        });
    }

    public void getSkCompany() {
        Map<String, String> map = new HashMap<>();
        map.put("keyword", keyWord);
        map.put("pageIndex", page+"");
        map.put("pageSize", "20");
        OkhttpUtil.getInstance().basePostCall(getContext(), JSON.toJSONString(map), UrlConstant.getInstance().YW_URL + "GetCustomerBankAccounts", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                if (page == 1) {
                    memberList.clear();
                }
                LogUtils.e("获取贸易商收款账户:" + str);
                BankAccountBean accountBean = JSON.parseObject(str, BankAccountBean.class);
                List<BankAccountBean.DBean.ReListBean> reList = accountBean.getD().getReList();
                if(reList!=null){
                    for (int i = 0; i < reList.size(); i++) {
                        memberList.add(reList.get(i).getMsg());
                    }
                }
                searchAdapter.notifyDataSetChanged();
                SmartUtils.finishSmart(smartRefreshLayout);
            }

            @Override
            public void onFail() {
                SmartUtils.finishSmart(smartRefreshLayout);
            }
        });
    }
}
