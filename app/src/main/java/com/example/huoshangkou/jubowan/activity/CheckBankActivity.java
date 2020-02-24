package com.example.huoshangkou.jubowan.activity;

import android.view.View;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;

import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：CheckBankActivity
 * 描述：验证银行卡界面
 * 创建时间：2017-02-21  10:25
 */

public class CheckBankActivity extends BaseActivity {


    @Override
    public int initLayout() {
        return R.layout.activity_check_bank;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {

    }


    //点击事件
    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }


}
