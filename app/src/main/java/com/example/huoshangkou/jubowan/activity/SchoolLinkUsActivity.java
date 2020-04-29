package com.example.huoshangkou.jubowan.activity;

import android.view.View;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.utils.IntentUtils;

import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：SchoolLinkUsActivity
 * 描述：
 * 创建时间：2019-03-27  09:21
 */

public class SchoolLinkUsActivity extends BaseActivity {
    @Override
    public int initLayout() {
        return R.layout.activity_school_link;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_at_jb})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_at_jb:
                IntentUtils.getInstance().toWebActivity(this,"http://www.atjubo.com","聚玻网");
                break;
        }
    }
}
