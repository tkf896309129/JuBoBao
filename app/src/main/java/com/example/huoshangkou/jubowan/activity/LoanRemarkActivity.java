package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.utils.IntentUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：LoanRemarkActivity
 * 描述：
 * 创建时间：2017-09-08  10:00
 */

public class LoanRemarkActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Override
    public int initLayout() {
        return R.layout.activity_loan_remark;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("服务说明");
    }

    @OnClick({R.id.ll_back, R.id.tv_apply_loan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_apply_loan:
                IntentUtils.getInstance().toActivity(getContext(), FaceLivenessExpActivity.class);
                break;
        }
    }
}
