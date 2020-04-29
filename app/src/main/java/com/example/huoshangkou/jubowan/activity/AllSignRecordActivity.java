package com.example.huoshangkou.jubowan.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.constant.SignConstant;
import com.example.huoshangkou.jubowan.fragment.CheckSignFragment;
import com.example.huoshangkou.jubowan.utils.IntentUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：AllSignRecordActivity
 * 描述：
 * 创建时间：2017-08-03  10:24
 */

public class AllSignRecordActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Override
    public int initLayout() {
        return R.layout.activity_all_sign_record;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {

        tvTitle.setText("补签列表");

        Fragment fragmentCheck = CheckSignFragment.newInstance();
        Bundle bunCheck = new Bundle();
        bunCheck.putString(IntentUtils.getInstance().VALUE, "1");
        bunCheck.putString(IntentUtils.getInstance().STR, "all_check");
        bunCheck.putString(IntentUtils.getInstance().TYPE, SignConstant.getInstance().CHECK_SIGN);
        fragmentCheck.setArguments(bunCheck);
//        getSupportFragmentManager().beginTransaction().add(R.id.ll_include, fragmentCheck).commit();
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
