package com.example.huoshangkou.jubowan.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.fragment.ForumListFragment;
import com.example.huoshangkou.jubowan.utils.IntentUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：HotTieActivity
 * 描述：
 * 创建时间：2017-06-09  13:44
 */

public class HotTieActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Override
    public int initLayout() {
        return R.layout.activity_hot_tie;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("论坛热帖");
        Fragment forumListFragment = ForumListFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString(IntentUtils.getInstance().TYPE, "10");
        forumListFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().add(R.id.ll_hot_include, forumListFragment).commit();
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
