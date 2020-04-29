package com.example.huoshangkou.jubowan.activity.function;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.SingleOrderCommitActivity;
import com.example.huoshangkou.jubowan.adapter.BuyFuAdapter;
import com.example.huoshangkou.jubowan.adapter.OrderTypeDetailAdapter;
import com.example.huoshangkou.jubowan.adapter.StringTypeAdapter;
import com.example.huoshangkou.jubowan.bean.BuyFuBean;
import com.example.huoshangkou.jubowan.bean.BuyFuListBean;
import com.example.huoshangkou.jubowan.bean.CarCountBean;
import com.example.huoshangkou.jubowan.bean.OrderTypeBean;
import com.example.huoshangkou.jubowan.bean.YuanBrandBean;
import com.example.huoshangkou.jubowan.bean.YuanBrandListBean;
import com.example.huoshangkou.jubowan.bean.YuanClassBean;
import com.example.huoshangkou.jubowan.bean.YuanClassListBean;
import com.example.huoshangkou.jubowan.bean.YuanStandardBean;
import com.example.huoshangkou.jubowan.bean.YuanStandardListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.OrderTypeConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnAddCarClickBack;
import com.example.huoshangkou.jubowan.inter.OnSearchCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.AnimationUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SmartUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：BuyFuFunction
 * 描述：
 * 创建时间：2017-03-06  13:32
 */

public class BuyFuFunction {

    private String clickType;
    private boolean isFirst = false;

    //排序方式
    private int mSort = 0;

    private static class BuyFuHelper {
        private static BuyFuFunction INSTANCE = new BuyFuFunction();
    }

    public static BuyFuFunction getInstance() {
        return BuyFuHelper.INSTANCE;
    }

    //选择显示
    private TextView mTvSelect;

    private String className = "";
    private String brandName = "";
    private String standardName = "";

    //各项被选中位置
    private int classPosition = -1;
    private int brandPosition = -1;
    private int standardPosition = -1;

    private LinearLayout mLlNoData;
    private SmartRefreshLayout mXRefresh;
    private Context mContext;
    private BuyFuAdapter mBuyFuAdapter;
    private List<OrderTypeBean> mTypeDetailList;

    TextView mTvCarNum;

    private int page = 1;

    //品类集合
    List<OrderTypeBean> classList = new ArrayList<>();
    List<BuyFuListBean> mBuyFuList = new ArrayList<>();


    //买辅材数据绑定显示
    public void bindFuData(final TextView tvCarNum, LinearLayout llNoData, DrawerLayout drawerLayout, TextView tvSelect, final Context context,
                           List<OrderTypeBean> typeList, StringTypeAdapter typeAdapter,
                           ListView lvType, final List<OrderTypeBean> typeDetailList, final OrderTypeDetailAdapter detailAdapter,
                           ListView lvDetail, ListView lvRefresh, final BuyFuAdapter buyFuAdapter, final List<BuyFuListBean> buyFuList,
                           final SmartRefreshLayout xRefresh, TextView tvAgain, TextView tvConfirm,
                           final int animationDuration, final FrameLayout viewGroup, final ImageView imgCar, LinearLayout llSearch,
                           String keyWord,
                           final OnSearchCallBack searchCallBack) {
        mTvCarNum = tvCarNum;
        mTvSelect = tvSelect;
        mLlNoData = llNoData;
        mContext = context;
        mBuyFuList = buyFuList;
        mBuyFuAdapter = buyFuAdapter;
        mXRefresh = xRefresh;
        mTypeDetailList =typeDetailList;
        //重置数据
        fuReset(detailAdapter);
        lvRefresh.setAdapter(buyFuAdapter);
        //隐藏输入框
        llSearch.setVisibility(View.GONE);

        brandName = StringUtils.getNoNullStr(keyWord);
        getFuData(buyFuList, buyFuAdapter, null);

        setChooseData(typeList, typeAdapter, lvType, typeDetailList, detailAdapter, lvDetail);


        xRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                buyFuList.clear();
                getFuData(buyFuList, buyFuAdapter, xRefresh);
            }
        });
        xRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getFuData(buyFuList, buyFuAdapter, xRefresh);
            }
        });

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                if (isFirst) {
                    clickType = "类别";
                    getClassType(typeDetailList, detailAdapter);
                    isFirst = false;
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        //重置
        tvAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fuReset(detailAdapter);
            }
        });

        //确认
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchCallBack.onConfirm();
                page = 1;
                buyFuList.clear();
                getFuData(buyFuList, buyFuAdapter, null);
            }
        });

        //加入购物车
        buyFuAdapter.setCarClickBack(new OnAddCarClickBack() {
            @Override
            public void onAddCarClick(String id, final Drawable drawable, final int[] startLocation) {
                BuyFunction.getInstance().addToYuanCar("1", mContext, id, OrderTypeConstant.getInstance().ADD_CAR, new StringCallBack() {
                    @Override
                    public void onSuccess(String carCount) {
                        CarCountBean successBean = JSON.parseObject(carCount, CarCountBean.class);
                        ToastUtils.getMineToast(mContext.getString(R.string.add_success));
                        AnimationUtils.setAnim(drawable, startLocation, animationDuration, context, viewGroup, imgCar);
                        mTvCarNum.setText(successBean.getReObj().getCatCount() + "");
                    }

                    @Override
                    public void onFail() {
                        ToastUtils.getMineToast(mContext.getString(R.string.add_fail));
                    }
                });
            }
        });

        lvRefresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentUtils.getInstance().toActivity(context, SingleOrderCommitActivity.class, buyFuList.get(i), OrderTypeConstant.getInstance().FL);
            }
        });
    }

    //语音搜索数据
    public void voiceData(String brand, String className, String standard) {
        this.brandName = brand;
        this.className = className;
        this.standardName = standard;
        mBuyFuList.clear();
        getFuData(mBuyFuList, mBuyFuAdapter, mXRefresh);
    }

    //获取辅料数据
    public void getFuData(final List<BuyFuListBean> buyFuList, final BuyFuAdapter buyFuAdapter, final SmartRefreshLayout xRefresh) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(mContext, UrlConstant.getInstance().URL + PostConstant.getInstance().GET_PRODUCT_DATA
                + FieldConstant.getInstance().USER_ID + "="
                + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().CLASS_NAME + "=" + EncodeUtils.getInstance().getEncodeString(className) + "&"
                + FieldConstant.getInstance().BRAND_NAME + "=" + EncodeUtils.getInstance().getEncodeString(brandName) + "&"
                + FieldConstant.getInstance().STANDARD_FU + "=" + EncodeUtils.getInstance().getEncodeString(standardName) + "&"
                + FieldConstant.getInstance().PRODUCT_TYPE_DEC + "=1"  + "&"
                + FieldConstant.getInstance().SORT + "=" + mSort + "&"
                + FieldConstant.getInstance().PAGE_INDEX + "=" + page, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                BuyFuBean buyFuBean = JSON.parseObject(json, BuyFuBean.class);
                buyFuList.addAll(buyFuBean.getReList());
                buyFuAdapter.notifyDataSetChanged();

                if (buyFuList.size() == 0 && mLlNoData != null) {
                    mLlNoData.setVisibility(View.VISIBLE);
                } else if (mLlNoData != null && buyFuList.size() > 0) {
                    mLlNoData.setVisibility(View.GONE);
                }

                SmartUtils.finishSmart(xRefresh);

                mTvCarNum.setText(buyFuBean.getTotalCount() + "");
            }

            @Override
            public void onFail() {
                if (mLlNoData != null) {
                    mLlNoData.setVisibility(View.VISIBLE);
                }
                SmartUtils.finishSmart(xRefresh);
            }
        });
    }


    //设置筛选数据
    public void setChooseData(final List<OrderTypeBean> typeList, final StringTypeAdapter typeAdapter, ListView lvType,
                              final List<OrderTypeBean> typeDetailList, final OrderTypeDetailAdapter detailAdapter, ListView lvDetail) {

        typeList.clear();

        OrderTypeBean orderTypeBean = new OrderTypeBean();
        orderTypeBean.setType("类别");
        typeList.add(orderTypeBean);

        OrderTypeBean orderTypeBean2 = new OrderTypeBean();
        orderTypeBean2.setType("品牌");
        typeList.add(orderTypeBean2);

        OrderTypeBean orderTypeBean3 = new OrderTypeBean();
        orderTypeBean3.setType("规格");
        typeList.add(orderTypeBean3);

        lvType.setAdapter(typeAdapter);
        typeAdapter.notifyDataSetChanged();
        lvType.setDividerHeight(0);

        lvDetail.setAdapter(detailAdapter);
        lvDetail.setDividerHeight(0);

        lvType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (typeList.get(i).getType()) {
                    case "类别":
                        clickType = "类别";

                        if (classList.size() == 0) {
                            getClassType(typeDetailList, detailAdapter);
                        } else {
                            typeDetailList.clear();
                            typeDetailList.addAll(classList);

                            if (typeDetailList.size() < classPosition) {
                                classPosition = -1;
                            }
                            setIsCheck(detailAdapter, classPosition, typeDetailList);
                        }

                        break;
                    case "品牌":
                        if (!StringUtils.isNoEmpty(className)) {
                            ToastUtils.getMineToast("请先选择品类");
                            return;
                        }
                        clickType = "品牌";
                        getBrand(typeDetailList, detailAdapter);
                        break;
                    case "规格":
                        if (!StringUtils.isNoEmpty(className)) {
                            ToastUtils.getMineToast("请先选择品类");
                            return;
                        }
                        clickType = "规格";
                        getStandard(typeDetailList, detailAdapter);
                        break;
                }
                typeAdapter.setItemClickPosition(i);
                typeAdapter.notifyDataSetChanged();
            }
        });


        //默认第一条被选中
        typeAdapter.setItemClickPosition(0);
        typeAdapter.notifyDataSetChanged();

        //点击事件
        lvDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (clickType) {
                    case "类别":
                        setIsCancelClick(detailAdapter, i, typeDetailList, "className", typeDetailList.get(i).getType());
                        break;
                    case "品牌":
                        setIsCancelClick(detailAdapter, i, typeDetailList, "brand", typeDetailList.get(i).getType());
                        break;
                    case "规格":
                        setIsCancelClick(detailAdapter, i, typeDetailList, "standard", typeDetailList.get(i).getType());
                        break;
                }
            }
        });
    }

    //可取消的点击事件
    private void setIsCancelClick(OrderTypeDetailAdapter detailAdapter, int i, List<OrderTypeBean> typeDetailList, String type, String value) {

        OrderTypeBean typeBean = typeDetailList.get(i);
        if (!typeBean.isCheck()) {
            detailAdapter.setItemClickPosition(i);
            detailAdapter.notifyDataSetChanged();
            typeBean.setCheck(true);
            //设置其他的为未选中
            setFalse(typeDetailList, i);
            getSwitch(type, i, value);
        } else {
            detailAdapter.setItemClickPosition(-1);
            detailAdapter.notifyDataSetChanged();
            typeBean.setCheck(false);
            getSwitch(type, -1, "");
        }

        getSelectData();
    }

    private void getSelectData() {
        mTvSelect.setText("已选择：" + className + getShowStr(brandName) + getShowStr(standardName));
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



    private void getSwitch(String type, int i, String value) {
        switch (type) {
            case "className":
                classPosition = i;
                className = value;
                break;
            case "brand":
                brandPosition = i;
                brandName = value;
                break;
            case "standard":
                standardPosition = i;
                standardName = value;
                break;
        }
    }

    public String getShowStr(String str) {
        if (StringUtils.isNoEmpty(str)) {
            str = "/" + str;
        }
        return str;
    }

    //获取类别
    private void getClassType(final List<OrderTypeBean> typeDetailList, final OrderTypeDetailAdapter detailAdapter) {
        typeDetailList.clear();
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(mContext, UrlConstant.getInstance().URL + PostConstant.getInstance().GET_FU_CLASS
                + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                if (typeDetailList == null) {
                    return;
                }
                YuanClassBean yuanClassBean = JSON.parseObject(json, YuanClassBean.class);
                List<YuanClassListBean> classListBeen = yuanClassBean.getReList();
                int num = classListBeen.size();
                for (int i = 0; i < num; i++) {
                    OrderTypeBean typeBean = new OrderTypeBean();
                    typeBean.setType(classListBeen.get(i).getClassName());
                    typeDetailList.add(typeBean);
                }

                classList.clear();
                classList.addAll(typeDetailList);


                if (typeDetailList.size() < classPosition) {
                    classPosition = -1;
                }
                setIsCheck(detailAdapter, classPosition, typeDetailList);
            }

            @Override
            public void onFail() {

            }
        });
    }

    //获取品牌
    private void getBrand(final List<OrderTypeBean> typeDetailList, final OrderTypeDetailAdapter detailAdapter) {
        typeDetailList.clear();
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(mContext,  UrlConstant.getInstance().URL + PostConstant.getInstance().GET_FU_BRAND
                + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().CLASS_NAME + "=" + EncodeUtils.getInstance().getEncodeString(className), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                if (typeDetailList == null) {
                    return;
                }
                YuanBrandBean yuanBrandBean = JSON.parseObject(json, YuanBrandBean.class);
                List<YuanBrandListBean> classListBeen = yuanBrandBean.getReList();
                int num = classListBeen.size();

                for (int i = 0; i < num; i++) {
                    OrderTypeBean typeBean = new OrderTypeBean();
                    typeBean.setType(classListBeen.get(i).getBrandName());
                    typeDetailList.add(typeBean);
                }
                if (typeDetailList.size() < brandPosition) {
                    brandPosition = -1;
                }
                setIsCheck(detailAdapter, brandPosition, typeDetailList);

            }

            @Override
            public void onFail() {

            }
        });
    }

    //获取规格
    private void getStandard(final List<OrderTypeBean> typeDetailList, final OrderTypeDetailAdapter detailAdapter) {
        typeDetailList.clear();
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(mContext,  UrlConstant.getInstance().URL + PostConstant.getInstance().GET_FU_STANDARD
                + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().CLASS_NAME + "=" + EncodeUtils.getInstance().getEncodeString(className), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {

                if (typeDetailList == null) {
                    return;
                }
                YuanStandardBean standardBean = JSON.parseObject(json, YuanStandardBean.class);
                List<YuanStandardListBean> classListBeen = standardBean.getReList();
                int num = classListBeen.size();
                for (int i = 0; i < num; i++) {
                    OrderTypeBean typeBean = new OrderTypeBean();
                    typeBean.setType(classListBeen.get(i).getGuigeName());
                    typeDetailList.add(typeBean);
                }
                if (typeDetailList.size() < standardPosition) {
                    standardPosition = -1;
                }
                setIsCheck(detailAdapter, standardPosition, typeDetailList);
            }

            @Override
            public void onFail() {

            }
        });
    }

    private void setIsCheck(OrderTypeDetailAdapter detailAdapter, int position, List<OrderTypeBean> typeDetailList) {
        if (position >= 0) {
            detailAdapter.setItemClickPosition(position);
            typeDetailList.get(position).setCheck(true);
        } else {
            detailAdapter.setItemClickPosition(position);
        }

        detailAdapter.notifyDataSetChanged();
    }

    //辅料重置
    public void fuReset(OrderTypeDetailAdapter detailAdapter) {
        //品类
        className = "";
        brandName = "";
        standardName = "";

        //点击类型
//        clickType = "";
        //第一次
        isFirst = true;
        //各项被选中位置
        classPosition = -1;
        brandPosition = -1;
        standardPosition = -1;
        int num = mTypeDetailList.size();
        for (int i = 0; i < num; i++) {
            mTypeDetailList.get(i).setCheck(false);
        }
        detailAdapter.setItemClickPosition(-1);
        detailAdapter.notifyDataSetChanged();

        //重置
        getSelectData();
    }

    //设置排序方式
    public void setSort(int sort, List<BuyFuListBean> buyFuList, final BuyFuAdapter buyFuAdapter) {
        mSort = sort;
        page = 1;
        buyFuList.clear();
        getFuData(buyFuList, buyFuAdapter, null);
    }



}
