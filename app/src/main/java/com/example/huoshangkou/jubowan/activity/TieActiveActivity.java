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
 * 类名：TieActiveActivity
 * 描述：论坛活跃度
 * 创建时间：2017-04-10  13:08
 */

public class TieActiveActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_level)
    TextView tvLevel;


    private String level = "";

    @Override
    public int initLayout() {
        return R.layout.activity_tie_active;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        level = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        tvTitle.setText("论坛活跃度");

        tvLevel.setText(level);

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
