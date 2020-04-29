package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.ApproveBankAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ApproveBankBean;
import com.example.huoshangkou.jubowan.bean.ApproveBankListBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnApproveBankCallBack;
import com.example.huoshangkou.jubowan.inter.OnDeleteBankCallBack;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.SmartUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：SearchBankActivity
 * 描述：
 * 创建时间：2018-10-19  14:57
 */

public class SearchBankActivity extends BaseActivity {
    ApproveBankAdapter bankAdapter;
    List<ApproveBankListBean> bankList;
    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.x_refresh)
    SmartRefreshLayout xRefreshView;
    @Bind(R.id.et_search)
    EditText etSearch;

    @Override
    public int initLayout() {
        return R.layout.activity_search_bank;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {

        bankList = new ArrayList<>();
        bankAdapter = new ApproveBankAdapter(this, bankList, R.layout.item_approve_bank);
        lvRefresh.setAdapter(bankAdapter);

        lvRefresh.setDividerHeight(0);


        bankAdapter.setBankCallBack(new OnApproveBankCallBack() {
            @Override
            public void onDeleteBankInfo(final String id, final int position) {
                CopyIosDialogUtils.getInstance().getIosDialog(getContext(),"公司名称："+bankList.get(position).getCompany()+"\n"+"银行名称："+bankList.get(position).getBankName()+"\n"+"银行账号："+bankList.get(position).getBankAccount(), new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        deleteOrder(id, new OnDeleteBankCallBack() {
                            @Override
                            public void onSuccess() {
                                bankList.remove(position);
                                bankAdapter.notifyDataSetChanged();
                                ToastUtils.getMineToast("删除成功");
                            }

                            @Override
                            public void onFail() {

                            }
                        });
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }

            @Override
            public void onEditBankInfo(ApproveBankListBean bean) {
                Intent intent = new Intent(getContext(), ApproveAddBankActivity.class);
                intent.putExtra(IntentUtils.getInstance().BANK_INFO, bean);
                startActivity(intent);
            }
        });

        bankAdapter.setPositionClick(new OnPositionClick() {
            @Override
            public void onPositionClick(int position) {
                Intent intent = new Intent();
                intent.putExtra("bean", bankList.get(position));
                setResult(105, intent);
                SearchBankActivity.this.finish();
            }
        });




        xRefreshView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getBankList(name);
            }
        });

    }


    //获取银行信息
    private void getBankList(String bankName) {
        bankList.clear();
        OkhttpUtil.getInstance().setUnCacheData(this, getString(R.string.loading_message),
                UrlConstant.getInstance().URL
                        + PostConstant.getInstance().GET_BANK_LIST
                        + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                        + FieldConstant.getInstance().COMPANY_BIG_NAME + "=" + EncodeUtils.getInstance().getEncodeString(bankName), new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        ApproveBankBean bankBean = JSON.parseObject(json, ApproveBankBean.class);
                        bankList.clear();
                        bankList.addAll(bankBean.getReList());
                        bankAdapter.notifyDataSetChanged();
                        SmartUtils.finishSmart(xRefreshView);
                    }

                    @Override
                    public void onFail() {

                    }
                });
    }


    //删除订单信息
    private void deleteOrder(String id, final OnDeleteBankCallBack callBack) {
        OkhttpUtil.getInstance().setUnCacheData(this, getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().DELETE_BANK + FieldConstant.getInstance().ID + "=" + id, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    callBack.onSuccess();
                } else {
                    callBack.onFail();
                }

            }

            @Override
            public void onFail() {
                callBack.onFail();
            }
        });
    }

    String name = "";

    @OnClick({R.id.ll_back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                SearchBankActivity.this.finish();
                break;
            case R.id.tv_right:
                name = etSearch.getText().toString().trim();
                if (!StringUtils.isNoEmpty(name)) {
                    ToastUtils.getMineToast("请输入搜索内容");
                    return;
                }
                getBankList(name);
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        String init = (String) SharedPreferencesUtils.getInstance().get(getContext(), SharedKeyConstant.getInstance().BANK_EDIT, "");
        if (init.equals("yes")) {
            getBankList(name);
            SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().BANK_EDIT, "");
        }
    }
}
