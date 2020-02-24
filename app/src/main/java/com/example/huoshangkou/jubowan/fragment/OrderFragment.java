package com.example.huoshangkou.jubowan.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.OrderSearchActivity;
import com.example.huoshangkou.jubowan.adapter.FragmentChangeAdapter;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.fragment.function.OrderFunction;
import com.example.huoshangkou.jubowan.utils.IntentUtils;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment
 * 类名：OrderFragment
 * 描述：我的订单界面
 * 创建时间：2017-01-04  10:17
 */

public class OrderFragment extends BaseFragment {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tab_order)
    TabLayout tabOrder;
    @Bind(R.id.vp_order)
    ViewPager vpOrder;

    FragmentChangeAdapter changeAdapter;
    List<Fragment> fragmentList;

    public static OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        //设置标题
        tvTitle.setText(R.string.order);
        //订单上面标题的分类
        OrderFunction.orderTypeBind(tabOrder, fragmentList, changeAdapter, getChildFragmentManager(), vpOrder);

    }

    @OnClick({R.id.iv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                IntentUtils.getInstance().toActivity(getActivity(), OrderSearchActivity.class);
                break;
        }
    }


    //跳转至订单界面
    @Subscriber(tag = "OrderFragment")
    public void toOrder(String str) {
        if (!str.isEmpty()) {
            vpOrder.setCurrentItem(1, false);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
