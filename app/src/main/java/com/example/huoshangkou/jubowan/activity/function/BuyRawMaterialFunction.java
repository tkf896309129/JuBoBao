package com.example.huoshangkou.jubowan.activity.function;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.SingleOrderCommitActivity;
import com.example.huoshangkou.jubowan.adapter.OrderTypeDetailAdapter;
import com.example.huoshangkou.jubowan.adapter.RawMaterialAdapter;
import com.example.huoshangkou.jubowan.adapter.StringTypeAdapter;
import com.example.huoshangkou.jubowan.bean.CarCountBean;
import com.example.huoshangkou.jubowan.bean.OrderTypeBean;
import com.example.huoshangkou.jubowan.bean.RawCateroyBean;
import com.example.huoshangkou.jubowan.bean.RawMaterialBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.OrderTypeConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
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
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：BuyRawMaterialFunction
 * 描述：
 * 创建时间：2020-03-02  14:21
 */

public class BuyRawMaterialFunction {
    private SmartRefreshLayout mXRefresh;
    private RawMaterialAdapter materialAdapter;
    private StringTypeAdapter mTypeAdapter;
    private OrderTypeDetailAdapter mDetailAdapter;
    private List<RawMaterialBean.ReListBean> listRawMaterial;
    private List<OrderTypeBean> mTypeList;
    private List<OrderTypeBean> mTypeDetailList;
    private ListView lvRefresh;
    private Context mContext;
    private TextView mTvCarNum;
    private String clickType;
    private String cateryId = "";
    private String cateryName = "";
    private String brandName = "";
    private String className = "";
    private String levelName = "";
    private String mSort = "";

    private int cateryPosition = -1;
    private int brandPosition = -1;
    private int classPosition = -1;
    private int levelPosition = -1;
    private int page = 1;

    private TextView mTvSelect;

    //链接
    private final String CATERY = "GetC_Category";
    private final String BRAND = "GetC_Brand";
    private final String CLASS_NAME = "GetC_Class";
    private final String LEVEL = "GetLevels";

    private volatile static BuyRawMaterialFunction instance;

    public static BuyRawMaterialFunction getInstance() {
        if (instance == null) {
            synchronized (BuyRawMaterialFunction.class) {
                if (instance == null) {
                    instance = new BuyRawMaterialFunction();
                }
            }
        }
        return instance;
    }

    /**
     * 买原料
     */
    public void buyRawMaterial(final Context context, RawMaterialAdapter materialAdapter, final List<RawMaterialBean.ReListBean> listRawMaterial, ListView lvRefresh, final int animationDuration,
                               TextView tvCarNum, final FrameLayout viewGroup, final ImageView imgCar, final List<OrderTypeBean> typeDetailList, final OrderTypeDetailAdapter detailAdapter,
                               List<OrderTypeBean> typeList, StringTypeAdapter typeAdapter, ListView lvType, ListView lvDetail, TextView tvSelectDetail,
                               TextView tvAgain, TextView tvConfirm, SmartRefreshLayout xRefresh, final OnSearchCallBack searchCallBack) {
        this.materialAdapter = materialAdapter;
        this.listRawMaterial = listRawMaterial;
        this.lvRefresh = lvRefresh;
        mDetailAdapter = detailAdapter;
        mTvCarNum = tvCarNum;
        mTvSelect = tvSelectDetail;
        mContext = context;
        mTypeList = typeList;
        mTypeDetailList = typeDetailList;
        mTypeAdapter = typeAdapter;
        mXRefresh = xRefresh;
        lvRefresh.setAdapter(materialAdapter);
        lvDetail.setAdapter(detailAdapter);
        lvDetail.setDividerHeight(0);
        reset();
        getRawMaterialList(context);
        initTypeData(typeList, typeAdapter, lvType, typeDetailList, detailAdapter, lvDetail);
        getSelectData();

        xRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getRawMaterialList(context);
            }
        });
        xRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getRawMaterialList(context);
            }
        });

        materialAdapter.setCarClickBack(new OnAddCarClickBack() {
            @Override
            public void onAddCarClick(String id, final Drawable drawable, final int[] startLocation) {
                BuyFunction.getInstance().addToYuanCar("2", mContext, id, OrderTypeConstant.getInstance().ADD_CAR, new StringCallBack() {
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
                IntentUtils.getInstance().toActivity(context, SingleOrderCommitActivity.class, listRawMaterial.get(i), OrderTypeConstant.getInstance().YL);
            }
        });

        //点击事件
        lvDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (clickType) {
                    case "类别":
                        setIsCancelClick(detailAdapter, i, typeDetailList, clickType, typeDetailList.get(i).getType());
                        break;
                    case "品牌":
                        setIsCancelClick(detailAdapter, i, typeDetailList, clickType, typeDetailList.get(i).getType());
                        break;
                    case "品类":
                        setIsCancelClick(detailAdapter, i, typeDetailList, clickType, typeDetailList.get(i).getType());
                        break;
                    case "等级":
                        setIsCancelClick(detailAdapter, i, typeDetailList, clickType, typeDetailList.get(i).getType());
                        break;
                }
            }
        });

        tvAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchCallBack.onConfirm();
                page = 1;
                getRawMaterialList(context);
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
            getSwitch(type, i, value, typeDetailList);
        } else {
            detailAdapter.setItemClickPosition(-1);
            detailAdapter.notifyDataSetChanged();
            typeBean.setCheck(false);
            getSwitch(type, -1, "", typeDetailList);
        }

        getSelectData();
    }

    //原片已选择数据
    public void getSelectData() {
        mTvSelect.setText("已选择：" + cateryName + getShowStr(brandName) + getShowStr(className) + getShowStr(levelName));
    }

    //原片重置
    private void reset() {
        //品类
        className = "";
        //颜色
        cateryName = "";
        cateryId = "";
        //品牌
        brandName = "";

        //级别
        levelName = "";

        classPosition = -1;
        cateryPosition = -1;
        brandPosition = -1;
        levelPosition = -1;


        int num = mTypeDetailList.size();
        for (int i = 0; i < num; i++) {
            mTypeDetailList.get(i).setCheck(false);
        }
        setIsCheck(mDetailAdapter, -1, mTypeDetailList);
        mDetailAdapter.notifyDataSetChanged();
        mTypeAdapter.notifyDataSetChanged();
        //重置
        getSelectData();
    }

    public String getShowStr(String str) {
        if (StringUtils.isNoEmpty(str)) {
            str = "/" + str;
        }
        return str;
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

    private void getSwitch(String type, int i, String value, List<OrderTypeBean> typeDetailList) {
        switch (type) {
            case "品类":
                classPosition = i;
                className = value;
                break;
            case "品牌":
                brandPosition = i;
                brandName = value;
                break;
            case "类别":
                cateryPosition = i;
                cateryName = value;
                if (i != -1) {
                    cateryId = typeDetailList.get(i).getID() + "";
                } else {
                    cateryId = "";
                }
                break;
            case "等级":
                levelPosition = i;
                levelName = value;
                break;
        }
    }

    //获取原料列表
    public void getRawMaterialList(Context context) {
//        final DialogShowUtils dialogShowUtils = new DialogShowUtils(context);
//        dialogShowUtils.showLoading();
        OkhttpUtil.getInstance().setUnCacheData(context, "数据加载中", UrlConstant.getInstance().LOCAL_URL + "GetProductList/?"
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().CATEFORY_NAME + "=" + EncodeUtils.getInstance().getEncodeString(cateryName) + "&"
                + FieldConstant.getInstance().CLASS_NAME + "=" + EncodeUtils.getInstance().getEncodeString(className) + "&"
                + FieldConstant.getInstance().BRAND_NAME + "=" + EncodeUtils.getInstance().getEncodeString(brandName) + "&"
                + FieldConstant.getInstance().LEVEL_NAME + "=" + EncodeUtils.getInstance().getEncodeString(levelName) + "&"
                + FieldConstant.getInstance().PRODUCT_TYPE_DEC + "=2"  + "&"
                + FieldConstant.getInstance().SORT + "=" + mSort + "&"
                + FieldConstant.getInstance().PAGE_INDEX + "=" + page, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                if (page == 1) {
                    listRawMaterial.clear();
                }
                RawMaterialBean materialBean = JSON.parseObject(json, RawMaterialBean.class);
                listRawMaterial.addAll(materialBean.getReList());
                materialAdapter.notifyDataSetChanged();
                mXRefresh.finishLoadMore();
                mXRefresh.finishRefresh();
                mTvCarNum.setText(materialBean.getTotalCount() + "");
//                dialogShowUtils.dismissLoading();
            }

            @Override
            public void onFail() {

            }
        });
    }


    public void initTypeData(final List<OrderTypeBean> typeList, final StringTypeAdapter typeAdapter, ListView lvType,
                             final List<OrderTypeBean> typeDetailList, final OrderTypeDetailAdapter detailAdapter, ListView lvDetail) {
        typeList.clear();
        OrderTypeBean orderTypeBean = new OrderTypeBean();
        orderTypeBean.setType("类别");
        orderTypeBean.setTypeId(1);
        typeList.add(orderTypeBean);

        OrderTypeBean orderTypeBean2 = new OrderTypeBean();
        orderTypeBean2.setType("品牌");
        orderTypeBean2.setTypeId(2);
        typeList.add(orderTypeBean2);

        OrderTypeBean orderTypeBean3 = new OrderTypeBean();
        orderTypeBean3.setType("品类");
        orderTypeBean3.setTypeId(3);
        typeList.add(orderTypeBean3);

        OrderTypeBean orderTypeBean4 = new OrderTypeBean();
        orderTypeBean4.setType("等级");
        orderTypeBean4.setTypeId(4);
        typeList.add(orderTypeBean4);

        clickType = typeList.get(0).getType();
        getCatData(typeDetailList, CATERY, detailAdapter, 0, typeAdapter);
        typeAdapter.setItemClickPosition(0);
        typeAdapter.notifyDataSetChanged();
        lvType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                clickType = typeList.get(i).getType();
                typeAdapter.setItemClickPosition(i);
                typeAdapter.notifyDataSetChanged();
                switch (typeList.get(i).getTypeId()) {
                    case 1:
                        getCatData(typeDetailList, CATERY, detailAdapter, i, typeAdapter);
                        break;
                    case 2:
                        getCatData(typeDetailList, BRAND, detailAdapter, i, typeAdapter);
                        break;
                    case 3:
                        getCatData(typeDetailList, CLASS_NAME, detailAdapter, i, typeAdapter);
                        break;
                    case 4:
                        getCatData(typeDetailList, LEVEL, detailAdapter, i, typeAdapter);
                        break;
                }
            }
        });
    }

    //获取类别
    private void getCatData(final List<OrderTypeBean> typeDetailList, final String postUrl, final OrderTypeDetailAdapter detailAdapter, int i, StringTypeAdapter typeAdapter) {
        Map<String, String> map = new HashMap<>();
        switch (postUrl) {
            case CATERY:
                break;
            case BRAND:
//                if (!StringUtils.isNoEmpty(cateryName)) {
//                    ToastUtils.getMineToast("请选择类别");
//                    return;
//                }
                map.put("CategoryName", cateryName);
                break;
            case CLASS_NAME:
//                if (!StringUtils.isNoEmpty(cateryName)) {
//                    ToastUtils.getMineToast("请选择类别");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(brandName)) {
//                    ToastUtils.getMineToast("请选择品牌");
//                    return;
//                }
                map.put("CategoryName", cateryName);
                map.put("BrandName", brandName);
                break;
            case LEVEL:
//                if (!StringUtils.isNoEmpty(cateryName)) {
//                    ToastUtils.getMineToast("请选择类别");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(brandName)) {
//                    ToastUtils.getMineToast("请选择品牌");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(className)) {
//                    ToastUtils.getMineToast("请选择品类");
//                    return;
//                }
                map.put("CategoryName", cateryName);
                map.put("BrandName", brandName);
                map.put("ClassName", className);
                break;
        }

        String json = JSON.toJSONString(map);
        OkhttpUtil.getInstance().basePostCall(mContext, json, UrlConstant.getInstance().CHOOSE_YL + postUrl, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                typeDetailList.clear();
                RawCateroyBean cateroyBean = JSON.parseObject(str, RawCateroyBean.class);
                List<RawCateroyBean.DBean.ReListBean> classListBeen = cateroyBean.getD().getReList();
                int num = classListBeen.size();
                for (int i = 0; i < num; i++) {
                    OrderTypeBean typeBean = new OrderTypeBean();
                    switch (postUrl) {
                        case CATERY:
                            typeBean.setType(classListBeen.get(i).getCategoryName());
                            break;
                        case BRAND:
                            typeBean.setType(classListBeen.get(i).getBrandName());
                            break;
                        case CLASS_NAME:
                            typeBean.setType(classListBeen.get(i).getClassName());
                            break;
                        case LEVEL:
                            typeBean.setType(classListBeen.get(i).getLevelName());
                            break;
                    }
                    typeBean.setID(classListBeen.get(i).getCategoryId());
                    typeDetailList.add(typeBean);
                }
                switch (postUrl) {
                    case CATERY:
                        if (typeDetailList.size() < cateryPosition) {
                            cateryPosition = -1;
                        }
                        setIsCheck(detailAdapter, cateryPosition, typeDetailList);
                        break;
                    case BRAND:
                        if (typeDetailList.size() < brandPosition) {
                            brandPosition = -1;
                        }
                        setIsCheck(detailAdapter, brandPosition, typeDetailList);
                        break;
                    case CLASS_NAME:
                        if (typeDetailList.size() < classPosition) {
                            classPosition = -1;
                        }
                        setIsCheck(detailAdapter, classPosition, typeDetailList);
                        break;
                    case LEVEL:
                        if (typeDetailList.size() < levelPosition) {
                            levelPosition = -1;
                        }
                        setIsCheck(detailAdapter, levelPosition, typeDetailList);
                        break;
                }
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

    public void setSort(String sort) {
        mSort = sort;
        page = 1;
        getRawMaterialList(mContext);
    }

}
