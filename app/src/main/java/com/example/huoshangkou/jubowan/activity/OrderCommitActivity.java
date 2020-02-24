package com.example.huoshangkou.jubowan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.utils.IntentUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：OrderCommitActivity
 * 描述：提交订单界面
 * 创建时间：2017-02-17  13:38
 */

public class OrderCommitActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.tv_ywy)
    TextView tvYwy;

    @Override
    public int initLayout() {
        return R.layout.activity_commit_order;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {

        tvTitle.setText("提交订单");
    }


    //点击事件
    @OnClick({R.id.ll_back, R.id.tv_commit_order})
    public void onClick(View view) {
        switch (view.getId()) {
            //退出该界面
            case R.id.ll_back:
                this.finish();
                break;
            //立即下单
            case R.id.tv_commit_order:
//                IntentUtils.getInstance().toActivity(this, AddBankCardActivity.class);
                break;
        }
    }


}
