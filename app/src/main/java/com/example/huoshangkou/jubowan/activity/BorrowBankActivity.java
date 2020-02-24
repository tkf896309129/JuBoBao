package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.BorrowBankAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ApproveBankBean;
import com.example.huoshangkou.jubowan.bean.ApproveBankListBean;
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
 * 类名：BorrowBankActivity
 * 描述：
 * 创建时间：2018-02-09  12:42
 */

public class BorrowBankActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_bank_borrow)
    ListView lvBank;

    BorrowBankAdapter bankAdapter;
    List<ApproveBankListBean> bankList;

    @Override
    public int initLayout() {
        return R.layout.activity_borrow_bank;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        bankList = new ArrayList<>();
        bankAdapter = new BorrowBankAdapter(getContext(),bankList,R.layout.item_approve_bank);
        lvBank.setAdapter(bankAdapter);
        lvBank.setDividerHeight(0);

        tvTitle.setText("借款指定入账单位");

        lvBank.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra("bean", bankList.get(i));
                setResult(105, intent);
                BorrowBankActivity.this.finish();
            }
        });

        getBankList();
    }

    @OnClick({R.id.ll_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ll_back:
              this.finish();
                break;
        }
    }

    //获取银行信息
    private void getBankList() {
        bankList.clear();
        OkhttpUtil.getInstance().setUnCacheData(this, getString(R.string.loading_message), UrlConstant.getInstance().URL + PostConstant.getInstance().GET_BANK_LIST_1
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                ApproveBankBean bankBean = JSON.parseObject(json, ApproveBankBean.class);
                bankList.addAll(bankBean.getReList());
                bankAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
    }

}
