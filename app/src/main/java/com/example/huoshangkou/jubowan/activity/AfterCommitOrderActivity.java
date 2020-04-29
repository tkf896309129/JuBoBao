package com.example.huoshangkou.jubowan.activity;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.AfterOrderAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.AfterOrderBean;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.PhoneUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：AfterCommitOrderActivity
 * 描述：
 * 创建时间：2017-05-16  14:36
 */

public class AfterCommitOrderActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_price)
    TextView tvPrice;

    private String price = "";


    @Override
    public int initLayout() {
        return R.layout.activity_after_commit_order;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().orderList);
        tvTitle.setText("下单成功");

        price = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        tvPrice.setText("￥" + price);
    }

    @OnClick({R.id.ll_back, R.id.tv_link_kf, R.id.tv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_link_kf:
                PhoneUtils.getInstance().callPhoe(getContext());
                break;
            case R.id.ll_back:
                EventBus.getDefault().post("initOrder", "initOrder");
                ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().orderList);
                break;
            case R.id.tv_back:
                EventBus.getDefault().post("initOrder", "initOrder");
                ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().orderList);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            EventBus.getDefault().post("initOrder", "initOrder");
            ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().orderList);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
