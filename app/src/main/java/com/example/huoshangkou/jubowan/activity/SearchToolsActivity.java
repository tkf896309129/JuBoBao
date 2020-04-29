package com.example.huoshangkou.jubowan.activity;

import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.SearchToolFunction;
import com.example.huoshangkou.jubowan.adapter.OrderTypeDetailAdapter;
import com.example.huoshangkou.jubowan.adapter.StringTypeAdapter;
import com.example.huoshangkou.jubowan.adapter.ToolSearchAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.OrderTypeBean;
import com.example.huoshangkou.jubowan.bean.SearchToolBean;
import com.example.huoshangkou.jubowan.bean.SearchToolListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.utils.ToolSearchWindowUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：SearchToolsActivity
 * 描述：搜索设备界面
 * 创建时间：2017-06-15  10:59
 */

public class SearchToolsActivity extends BaseActivity {
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.x_refresh)
    XRefreshView xRefreshView;
    @Bind(R.id.lv_refresh)
    GridView listView;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_select)
    TextView tvRight;

    @Bind(R.id.lv_type)
    ListView lvType;
    @Bind(R.id.lv_detail)
    ListView lvDetail;
    @Bind(R.id.tv_confirm)
    TextView tvConfirm;
    @Bind(R.id.tv_again)
    TextView tvAgain;
    @Bind(R.id.tv_select_detail)
    TextView tvSelectDetail;
    
    @Bind(R.id.ll_no_data)
    LinearLayout llNodata;

    List<SearchToolListBean> searchList;
    ToolSearchAdapter searchAdapter;

    //左边的类别
    List<OrderTypeBean> toolTypeList;
    StringTypeAdapter typeAdapter;

    //适配器
    OrderTypeDetailAdapter detailAdapter;

    //品类集合
    private List<OrderTypeBean> toolClassList;

    //品牌集合
    private List<OrderTypeBean> toolBrandList;

    //类型集合
    private List<OrderTypeBean> toolTypeRightList;

    //详情数据类型
    List<OrderTypeBean> typeDetailList;

    //类型id
    private String maintainId = "";
    //品牌id
    private String brandId = "";
    //类别id
    private String typeId = "";

    private int page = 1;

    @Override
    public int initLayout() {
        return R.layout.activity_search_tool;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        searchList = new ArrayList<>();
        toolClassList = new ArrayList<>();
        toolBrandList = new ArrayList<>();
        toolTypeRightList = new ArrayList<>();
        toolTypeList = new ArrayList<>();
        typeDetailList = new ArrayList<>();
        tvRight.setVisibility(View.GONE);
        tvTitle.setText("机械设备");


        maintainId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        typeId = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        tvTitle.setText(getIntent().getStringExtra(IntentUtils.getInstance().STR));
        searchAdapter = new ToolSearchAdapter(getContext(), searchList, R.layout.item_tool_search);
        listView.setAdapter(searchAdapter);
//        listView.setDividerHeight(0);

        detailAdapter = new OrderTypeDetailAdapter(this, typeDetailList, R.layout.item_order_detail);
        lvDetail.setAdapter(detailAdapter);
        lvDetail.setDividerHeight(0);
        xRefreshView.setPullLoadEnable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentUtils.getInstance().toActivity(getContext(), ToolDetailsActivity.class, UrlConstant.getInstance().TOOL_UEL + searchList.get(i).getID(), searchList.get(i).getID() + "");
            }
        });

        //侧滑栏
        SearchToolFunction.getInstance().searchLeftSlide(toolTypeList, typeAdapter, getContext(),
                lvType, toolClassList, toolBrandList, toolTypeRightList, detailAdapter,
                typeDetailList, lvDetail, tvAgain, tvConfirm, tvSelectDetail, searchList,
                searchAdapter, drawerLayout, xRefreshView, llNodata, maintainId, brandId, typeId);
    }


    @OnClick({R.id.ll_back, R.id.tv_select, R.id.tv_search_tie})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_select:
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
//            //重置
//            case R.id.tv_again:
//                IntentUtils.getInstance().toActivity(getContext(), ToolDetailsActivity.class);
//                break;
//            //确认
//            case R.id.tv_confirm:
//                IntentUtils.getInstance().toActivity(getContext(), ToolSuggestActivity.class);
//                break;
            case R.id.tv_search_tie:
                ToolSearchWindowUtils.getInstance().getSearchPopupWindow(view, getContext());
                break;
        }
    }

//    public void getSearchData() {
//        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message),
//                UrlConstant.getInstance().URL + PostConstant.getInstance().GET_MAINTAIN_PRODUCT
//                        + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
//                        + FieldConstant.getInstance().MAINTAIN_ID + "=" + maintainId + "&"
//                        + FieldConstant.getInstance().BRAND_ID + "=" + brandId + "&"
//                        + FieldConstant.getInstance().CLASS_ID + "=" + typeId + "&"
//                        + FieldConstant.getInstance().PAGE_SIZE + "=" + page
//                , new OkhttpCallBack() {
//                    @Override
//                    public void onSuccess(String json) {
//                        SearchToolBean toolBean = JSON.parseObject(json, SearchToolBean.class);
//                        searchList.addAll(toolBean.getReList());
//                        searchAdapter.notifyDataSetChanged();
//                        xRefreshView.stopRefresh();
//                        xRefreshView.stopLoadMore();
//                        if (searchList.size() == 0) {
//                            llNodata.setVisibility(View.VISIBLE);
//                        } else {
//                            llNodata.setVisibility(View.GONE);
//                        }
//                    }
//
//                    @Override
//                    public void onFail() {
//                        xRefreshView.stopRefresh();
//                        xRefreshView.stopLoadMore();
//                    }
//                });
//    }
}
