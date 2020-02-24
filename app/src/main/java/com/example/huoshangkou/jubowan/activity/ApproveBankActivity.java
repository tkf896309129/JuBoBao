package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
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
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.SmartUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
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
 * 类名：ApproveBankActivity
 * 描述：审批银行信息界面   20000 + 16000 + 60000 + 16000 ==
 * 创建时间：2017-03-21  11:38
 */

public class ApproveBankActivity extends BaseActivity {
    ApproveBankAdapter bankAdapter;
    List<ApproveBankListBean> bankList;
    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.x_refresh)
    SmartRefreshLayout xRefreshView;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_right)
    ImageView imgRight;


    @Override
    public int initLayout() {
        return R.layout.activity_approve_bank;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("账户详情");
        imgRight.setImageResource(R.mipmap.search_icon_2);

        bankList = new ArrayList<>();
        bankAdapter = new ApproveBankAdapter(this, bankList, R.layout.item_approve_bank);
        lvRefresh.setAdapter(bankAdapter);

        lvRefresh.setDividerHeight(0);
        xRefreshView.setEnableLoadMore(false);
        xRefreshView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getBankList();
            }
        });

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
                ApproveBankActivity.this.finish();
            }
        });
    }


    //获取银行信息
    private void getBankList() {
        OkhttpUtil.getInstance().setUnCacheData(this, getString(R.string.loading_message), UrlConstant.getInstance().URL + PostConstant.getInstance().GET_BANK_LIST
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
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


    //点击事件
    @OnClick({R.id.ll_back, R.id.tv_add_new, R.id.iv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add_new:
                IntentUtils.getInstance().toActivity(getContext(), ApproveAddBankActivity.class);
                break;
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.iv_right:
                Intent intent = new Intent(getContext(), SearchBankActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBankList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case 1:
                ApproveBankListBean bankListBean = (ApproveBankListBean) data.getSerializableExtra("bean");
                Intent intent = new Intent();
                intent.putExtra("bean", bankListBean);
                setResult(105, intent);
                ApproveBankActivity.this.finish();
                break;
        }
    }
}
