package com.example.huoshangkou.jubowan.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.NewZbDetailActivity;
import com.example.huoshangkou.jubowan.activity.ZbDetailActivity;
import com.example.huoshangkou.jubowan.activity.function.MoreZbFunction;
import com.example.huoshangkou.jubowan.adapter.MineZbAdapter;
import com.example.huoshangkou.jubowan.adapter.ZbNewAdapter;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.bean.ZbBean;
import com.example.huoshangkou.jubowan.bean.ZbListBean;
import com.example.huoshangkou.jubowan.bean.ZbMessageBean;
import com.example.huoshangkou.jubowan.inter.OnZbBeanCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.SmartUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
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
 * 类名：MineZbFragment
 * 描述：我的招标
 * 创建时间：2017-04-07  14:32
 */

public class MineZbFragment extends BaseFragment {
    @Bind(R.id.grid_zb_new)
    GridView gridZbNew;
    @Bind(R.id.smart)
    SmartRefreshLayout smartRefreshLayout;
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;

    private List<ZbListBean> list;
    private String toState = "";
    private ZbNewAdapter zbNewAdapter;
    private int page = 1;

    public static MineZbFragment newInstance() {
        MineZbFragment fragment = new MineZbFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_zb;
    }

    @Override
    public void initData() {
        list = new ArrayList<>();
        toState = getArguments().getString(IntentUtils.getInstance().TYPE);

        getSearchZb();

        zbNewAdapter = new ZbNewAdapter(getActivity(), list, R.layout.item_new_zb);
        gridZbNew.setAdapter(zbNewAdapter);
        gridZbNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                IntentUtils.getInstance().toActivity(getContext(), ZbPriceActivity.class);
                switch (list.get(i).getProjectType()) {
                    case "幕墙招标":
                        IntentUtils.getInstance().toActivity(getContext(), NewZbDetailActivity.class, IntentUtils.getInstance().MUQ_ZB, list.get(i).getRequestID() + "");
                        break;
                    case "系统门窗":
                        IntentUtils.getInstance().toActivity(getContext(), NewZbDetailActivity.class, IntentUtils.getInstance().SYSTEM_ZB, list.get(i).getRequestID() + "");
                        break;
                    case "光伏幕墙":
                        IntentUtils.getInstance().toActivity(getContext(), NewZbDetailActivity.class, IntentUtils.getInstance().GF_ZB, list.get(i).getRequestID() + "");
                        break;
                    case "门窗定制招标":
                        IntentUtils.getInstance().toActivity(getContext(), NewZbDetailActivity.class, IntentUtils.getInstance().DOOR_WINDOW, list.get(i).getRequestID() + "");
                        break;
                }
            }
        });

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getSearchZb();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getSearchZb();
            }
        });

    }

    //搜索招标数据
    public void getSearchZb() {
        MoreZbFunction.getInstance().getZbList(getContext(), toState, page, new OnZbBeanCallBack() {
            @Override
            public void onSuccess(ZbBean zbBean) {
                if (page == 1) {
                    list.clear();
                }
                list.addAll(zbBean.getReList());
                zbNewAdapter.notifyDataSetChanged();
                SmartUtils.finishSmart(smartRefreshLayout);
                if(list.size()==0){
                    llNoData.setVisibility(View.VISIBLE);
                }else {
                    llNoData.setVisibility(View.GONE    );
                }
            }

            @Override
            public void onFail() {
                SmartUtils.finishSmart(smartRefreshLayout);
            }
        });
    }
}
