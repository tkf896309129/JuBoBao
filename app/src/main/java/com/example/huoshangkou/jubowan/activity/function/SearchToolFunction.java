package com.example.huoshangkou.jubowan.activity.function;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.OrderTypeDetailAdapter;
import com.example.huoshangkou.jubowan.adapter.StringTypeAdapter;
import com.example.huoshangkou.jubowan.adapter.ToolSearchAdapter;
import com.example.huoshangkou.jubowan.bean.OrderTypeBean;
import com.example.huoshangkou.jubowan.bean.SearchToolBean;
import com.example.huoshangkou.jubowan.bean.SearchToolListBean;
import com.example.huoshangkou.jubowan.bean.TypeBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：SearchToolFunction
 * 描述：
 * 创建时间：2017-06-20  15:58
 */

public class SearchToolFunction {

    private static class SearchToolHelper {
        private static SearchToolFunction Instance = new SearchToolFunction();
    }

    public static SearchToolFunction getInstance() {
        return SearchToolHelper.Instance;
    }

    //品类集合
    private List<OrderTypeBean> mToolClassList;

    //品牌集合
    private List<OrderTypeBean> mToolBrandList;

    //类型集合
    private List<OrderTypeBean> mToolTypeList;

    OrderTypeDetailAdapter mDetailAdapter;
    List<OrderTypeBean> mTypeDetailList;
    ToolSearchAdapter mSearchAdapter;
    List<SearchToolListBean> mSearchList;
    LinearLayout mLlNodata;

    private Context mContext;

    TextView mTvSelectDetail;

    //判断选择的类型
    private String checkType = "classType";

    //记录点击下标
    private int classPosition = -1;
    private int brandPosition = -1;
    private int typePosition = -1;

    private String classType = "";
    private String brandType = "";
    private String Type = "";
    //类型id
    private String maintainId = "";
    //品牌id
    private String brandId = "";
    //类别id
    private String typeId = "";

    DrawerLayout mDrawerLayout;
    XRefreshView mXRefreshView;
    private int page = 1;

    //侧滑栏操作
    public void searchLeftSlide(List<OrderTypeBean> toolTypeList, StringTypeAdapter typeAdapter,
                                Context context, ListView lvType, List<OrderTypeBean> toolClassList,
                                List<OrderTypeBean> toolBrandList, List<OrderTypeBean> toolTypeRightList,
                                final OrderTypeDetailAdapter detailAdapter, final List<OrderTypeBean> typeDetailList,
                                ListView lvDetail, TextView tvConfirm, TextView tvAgain, TextView tvSelectDetail,
                                final List<SearchToolListBean> searchList, ToolSearchAdapter searchAdapter,
                                DrawerLayout drawerLayout, XRefreshView xRefreshView, LinearLayout llNodata,String maintainIds,String brandIds,String typeIds) {
        mToolBrandList = toolBrandList;
        mToolClassList = toolClassList;
        mToolTypeList = toolTypeRightList;
        mContext = context;
        mTypeDetailList = typeDetailList;
        mDetailAdapter = detailAdapter;
        mTvSelectDetail = tvSelectDetail;
        mSearchList = searchList;
        mSearchAdapter = searchAdapter;
        mDrawerLayout = drawerLayout;
        mXRefreshView = xRefreshView;
        mLlNodata = llNodata;
        page = 1;


        //类型id
        maintainId = maintainIds;
        //品牌id
        brandId = brandIds;
        //类别id
        typeId = typeIds;
        classType = "";
        brandType = "";
        Type = "";

        classPosition = -1;
        brandPosition = -1;
        typePosition = -1;

        //侧滑栏
        OrderTypeBean tyClass = new OrderTypeBean();
        tyClass.setType("品类");
        toolTypeList.add(tyClass);

        OrderTypeBean tyType = new OrderTypeBean();
        tyType.setType("类型");
        toolTypeList.add(tyType);

        OrderTypeBean tyBrand = new OrderTypeBean();
        tyBrand.setType("品牌");
        toolTypeList.add(tyBrand);

        typeAdapter = new StringTypeAdapter(context, toolTypeList, R.layout.item_order_type);
        lvType.setAdapter(typeAdapter);
        lvType.setDividerHeight(0);

        typeAdapter.setItemClickPosition(0);
        typeAdapter.notifyDataSetChanged();

        final StringTypeAdapter finalTypeAdapter = typeAdapter;

        lvType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                finalTypeAdapter.setItemClickPosition(i);
                finalTypeAdapter.notifyDataSetChanged();
                switch (i) {
                    //品类
                    case 0:
                        checkType = "classType";
                        if (mToolClassList.size() != 0) {
                            mTypeDetailList.clear();
                            mTypeDetailList.addAll(mToolClassList);
                            mDetailAdapter.notifyDataSetChanged();

                            detailAdapter.setItemClickPosition(classPosition);
                            detailAdapter.notifyDataSetChanged();
                            return;
                        }
                        getClassData();
                        break;
                    //类型
                    case 1:
                        checkType = "type";
                        if (mToolTypeList.size() != 0) {
                            mTypeDetailList.clear();
                            mTypeDetailList.addAll(mToolTypeList);
                            mDetailAdapter.notifyDataSetChanged();

                            detailAdapter.setItemClickPosition(typePosition);
                            detailAdapter.notifyDataSetChanged();
                            return;
                        }
                        getTypeRight();
                        break;
                    //品牌
                    case 2:
                        checkType = "brandType";
//                        if (mToolBrandList.size() != 0) {
//                            mTypeDetailList.clear();
//                            mTypeDetailList.addAll(mToolBrandList);
//                            mDetailAdapter.notifyDataSetChanged();
//
//                            detailAdapter.setItemClickPosition(brandPosition);
//                            detailAdapter.notifyDataSetChanged();
//                            return;
//                        }
                        getBrandData();
                        break;
                }
            }
        });

        lvDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (checkType) {
                    //品类
                    case "classType":
                        if (!typeDetailList.get(i).isCheck()) {
                            classPosition = i;
                            classType = typeDetailList.get(i).getClassTitle();
                            typeId = typeDetailList.get(i).getID() + "";
                            typeDetailList.get(i).setCheck(true);
                            //设置其他的为未选中
                            setFalse(typeDetailList, i);
                        } else {
                            classPosition = -1;
                            classType = "";
                            typeId = "";
                            typeDetailList.get(i).setCheck(false);
                        }
                        setTextData();
                        detailAdapter.setItemClickPosition(classPosition);
                        detailAdapter.notifyDataSetChanged();
                        break;
                    //品牌
                    case "brandType":
                        if (!typeDetailList.get(i).isCheck()) {
                            brandPosition = i;
                            brandType = typeDetailList.get(i).getName();
                            typeDetailList.get(i).setCheck(true);
                            brandId = typeDetailList.get(i).getID() + "";
                            //设置其他的为未选中
                            setFalse(typeDetailList, i);
                        } else {
                            brandPosition = -1;
                            brandType = "";
                            typeDetailList.get(i).setCheck(false);
                            brandId = "";
                        }
                        setTextData();
                        detailAdapter.setItemClickPosition(brandPosition);
                        detailAdapter.notifyDataSetChanged();

                        setTextData();
                        break;
                    //类型
                    case "type":
                        if (!typeDetailList.get(i).isCheck()) {
                            typePosition = i;
                            Type = typeDetailList.get(i).getName();
                            typeDetailList.get(i).setCheck(true);
                            //设置其他的为未选中
                            setFalse(typeDetailList, i);
                            maintainId = typeDetailList.get(i).getID() + "";
                        } else {
                            typePosition = -1;
                            Type = "";
                            maintainId = "";
                            typeDetailList.get(i).setCheck(false);
                        }
                        setTextData();
                        detailAdapter.setItemClickPosition(typePosition);
                        detailAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });

        getClassData();

        tvAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page = 1;
                searchList.clear();
                //搜索数据
                getSearchData();
                mDrawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });//差不多就行了

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //类型id
                maintainId = "";
                //品牌id
                brandId = "";
                //类别id
                typeId = "";
                classType = "";
                brandType = "";
                Type = "";
                setTextData();
            }
        });

        //搜索数据
        getSearchData();
        xRefreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                page = 1;
                searchList.clear();
                //搜索数据
                getSearchData();

            }

            @Override
            public void onLoadMore(boolean isSilence) {
                page++;
                //搜索数据
                getSearchData();
            }

            @Override
            public void onRelease(float direction) {
//                562439276
            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
    }

    //获取品牌
    private void getBrandData() {
        OkhttpUtil.getInstance().setUnCacheData(mContext, mContext.getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_MAINTAIN_BRAND
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().MAINTAIN_ID + "=" + maintainId, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                TypeBean typeBean = JSON.parseObject(json, TypeBean.class);
                mToolBrandList.clear();
                mToolBrandList.addAll(typeBean.getReList());
                mTypeDetailList.clear();
                mTypeDetailList.addAll(mToolBrandList);
                mDetailAdapter.setItemClickPosition(brandPosition);
                mDetailAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
    }

    //获取品类
    private void getClassData() {
        OkhttpUtil.getInstance().setUnCacheData(mContext, mContext.getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_MAINTAIN_CLASS
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                TypeBean typeBean = JSON.parseObject(json, TypeBean.class);
                mToolClassList.clear();
                mToolClassList.addAll(typeBean.getReList());
                mTypeDetailList.clear();
                mTypeDetailList.addAll(mToolClassList);
                mDetailAdapter.setItemClickPosition(classPosition);
                mDetailAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
    }

    private void getTypeRight() {
        OkhttpUtil.getInstance().setUnCacheData(mContext, mContext.getString(R.string.loading_message), UrlConstant.getInstance().URL
                        + PostConstant.getInstance().GET_MAINTAIN_TYPE
                        + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId()
                , new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        TypeBean typeBean = JSON.parseObject(json, TypeBean.class);
                        mToolTypeList.clear();
                        mToolTypeList.addAll(typeBean.getReList());
                        mTypeDetailList.clear();
                        mTypeDetailList.addAll(mToolTypeList);
                        mDetailAdapter.setItemClickPosition(typePosition);
                        mDetailAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFail() {

                    }
                });
    }

    //赋值
    private void setTextData() {
        String classs = "";
        String brands = "";
        String type = "";

        if (StringUtils.isNoEmpty(classType)) {
            classs = classType + "/";
        }
        if (StringUtils.isNoEmpty(brandType)) {
            brands = brandType + "/";
        }
        if (StringUtils.isNoEmpty(Type)) {
            type = Type + "/";
        }

        mTvSelectDetail.setText("已选择：" + classs + brands + type);
    }

    /**
     * 全部设置为false
     */
    private void setFalse(List<OrderTypeBean> typeBeanList, int position) {
        int num = typeBeanList.size();
        for (int i = 0; i < num; i++) {
            if (i != position) {
                typeBeanList.get(i).setCheck(false);
            }
        }
    }

    //获取搜索输数据  http://192.168.10.120/webapi/atapi/GetMaintainProduct/?UserId=230
    public void getSearchData() {
        OkhttpUtil.getInstance().setUnCacheData(mContext, mContext.getString(R.string.loading_message),
                UrlConstant.getInstance().URL + PostConstant.getInstance().GET_MAINTAIN_PRODUCT
                        + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                        + FieldConstant.getInstance().MAINTAIN_ID + "=" + maintainId + "&"
                        + FieldConstant.getInstance().BRAND_ID + "=" + brandId + "&"
                        + FieldConstant.getInstance().CLASS_ID + "=" + typeId + "&"
                        + FieldConstant.getInstance().PAGE_SIZE + "=" + page
                , new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        SearchToolBean toolBean = JSON.parseObject(json, SearchToolBean.class);
                        mSearchList.addAll(toolBean.getReList());
                        mSearchAdapter.notifyDataSetChanged();
                        mXRefreshView.stopRefresh();
                        mXRefreshView.stopLoadMore();
                        if (mSearchList.size() == 0) {
                            mLlNodata.setVisibility(View.VISIBLE);
                        } else {
                            mLlNodata.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFail() {
                        mXRefreshView.stopRefresh();
                        mXRefreshView.stopLoadMore();
                    }
                });
    }
}
