package com.example.huoshangkou.jubowan.activity;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.AfterOrderAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.AfterOrderBean;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.PhoneUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：AfterPayActivity
 * 描述：
 * 创建时间：2017-05-18  09:31
 */

public class AfterPayActivity extends BaseActivity {
    private String orderId = "";
    private String time = "";

    List<AfterOrderBean> afterList;

    AfterOrderAdapter afterOrderAdapter;

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_order)
    ListView lvOrder;

    @Override
    public int initLayout() {
        return R.layout.activity_after_pay;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().orderList);
        afterList = new ArrayList<>();
        orderId = getIntent().getStringExtra(IntentUtils.getInstance().ORDER_ID);
        time = getIntent().getStringExtra(IntentUtils.getInstance().TIME);

        tvTitle.setText("下单成功");

        getData();

        afterOrderAdapter = new AfterOrderAdapter(getContext(), afterList, R.layout.item_after_order);
        lvOrder.setAdapter(afterOrderAdapter);
        lvOrder.setDividerHeight(0);

    }

    private void getData() {
        if (StringUtils.isNoEmpty(orderId)) {
            AfterOrderBean afterOrderBean = new AfterOrderBean();
            afterOrderBean.setLeft("下单编号：");
            afterOrderBean.setRight(orderId);

            afterList.add(afterOrderBean);
        }

        AfterOrderBean afterOrderBean = new AfterOrderBean();
        afterOrderBean.setLeft("下单时间：");
        afterOrderBean.setRight(time);

        afterList.add(afterOrderBean);
    }


    @OnClick({R.id.ll_back, R.id.tv_link_kf})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().orderList);
                break;
            case R.id.tv_link_kf:
                PhoneUtils.getInstance().callPhoe(getContext());
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().orderList);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
