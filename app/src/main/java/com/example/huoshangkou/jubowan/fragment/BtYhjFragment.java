package com.example.huoshangkou.jubowan.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.YhjAdapter;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.bean.YhjBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SmartUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment
 * 类名：BtYhjFragment
 * 描述：
 * 创建时间：2018-12-10  08:39
 */

public class BtYhjFragment extends BaseFragment {
    YhjAdapter yhjAdapter;
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;
    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.x_refresh)
    SmartRefreshLayout xRefresh;
    private List<YhjBean.ReObjBean> list = new ArrayList<>();

    private int page = 1;
    private String state = "";

    public static BtYhjFragment newInstance() {
        BtYhjFragment fragment = new BtYhjFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_bt_yhj;
    }

    @Override
    public void initData() {
        yhjAdapter = new YhjAdapter(getActivity(), list, R.layout.item_yhj);
        state = getArguments().getString(IntentUtils.getInstance().STATE);
        lvRefresh.setAdapter(yhjAdapter);
        lvRefresh.setDividerHeight(0);
        getYhj();

        xRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getYhj();
            }
        });
        xRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getYhj();
            }
        });
    }

    //获取优惠券
    public void getYhj() {
        OkhttpUtil.getInstance().setUnCacheData(getActivity(), "数据加载中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_All_USING_COUPON
                + FieldConstant.getInstance().PAGE + "=" + page + "&"
                + FieldConstant.getInstance().STATES + "=" + state + "&"//优惠券状态（0 未使用，1已使用    2已过期    其它值或不填为查询全部）
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                YhjBean yhjBean = JSON.parseObject(json, YhjBean.class);
                if (page == 1) {
                    list.clear();
                }
                list.addAll(yhjBean.getReObj());
                yhjAdapter.notifyDataSetChanged();
                SmartUtils.finishSmart(xRefresh);
            }

            @Override
            public void onFail() {
                SmartUtils.finishSmart(xRefresh);
            }
        });
    }
}
