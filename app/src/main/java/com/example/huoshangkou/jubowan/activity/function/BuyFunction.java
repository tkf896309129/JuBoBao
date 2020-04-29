package com.example.huoshangkou.jubowan.activity.function;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.LocalWebActivity;
import com.example.huoshangkou.jubowan.activity.SingleOrderCommitActivity;
import com.example.huoshangkou.jubowan.adapter.BuyYuanAdapter;
import com.example.huoshangkou.jubowan.adapter.OrderTypeDetailAdapter;
import com.example.huoshangkou.jubowan.adapter.StringTypeAdapter;
import com.example.huoshangkou.jubowan.base.BaseApp;
import com.example.huoshangkou.jubowan.bean.BuyYaunListBean;
import com.example.huoshangkou.jubowan.bean.BuyYuanBean;
import com.example.huoshangkou.jubowan.bean.CarCountBean;
import com.example.huoshangkou.jubowan.bean.OrderTypeBean;
import com.example.huoshangkou.jubowan.bean.OrderWebDetailBean;
import com.example.huoshangkou.jubowan.bean.YuanBrandBean;
import com.example.huoshangkou.jubowan.bean.YuanBrandListBean;
import com.example.huoshangkou.jubowan.bean.YuanClassBean;
import com.example.huoshangkou.jubowan.bean.YuanClassListBean;
import com.example.huoshangkou.jubowan.bean.YuanLevelBean;
import com.example.huoshangkou.jubowan.bean.YuanLevelListBean;
import com.example.huoshangkou.jubowan.bean.YuanLowBean;
import com.example.huoshangkou.jubowan.bean.YuanLowListBean;
import com.example.huoshangkou.jubowan.bean.YuanStandardBean;
import com.example.huoshangkou.jubowan.bean.YuanStandardListBean;
import com.example.huoshangkou.jubowan.bean.YuanThickBean;
import com.example.huoshangkou.jubowan.bean.YuanThickListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.OrderTypeConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnAddCarClickBack;
import com.example.huoshangkou.jubowan.inter.OnSearchCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.AnimationUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.KeyBoardUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.SmartUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：BuyFunction
 * 描述：
 * 创建时间：2017-02-17  10:03
 */

public class BuyFunction implements TextView.OnEditorActionListener {
    private static class BuyHelper {
        private static BuyFunction Instance = new BuyFunction();
    }

    public static BuyFunction getInstance() {
        return BuyHelper.Instance;
    }

    //品类
    public String className = "";
    //颜色
    public String colorName = "";
    //品牌
    public String brand = "";
    //膜系
    public String low = "";
    //厚度
    public String thick = "";
    //级别
    public String level = "";
    //规格
    public String standard = "";
    //点击类型
    private String clickType = "";
    //各项被选中位置
    private int classPosition = -1;
    private int colorPosition = -1;
    private int brandPosition = -1;
    private int moxiPosition = -1;
    private int levelPosition = -1;
    private int thickPosition = -1;
    private int standardPosition = -1;
    //是否是有色
    private boolean isYouSe = false;
    //是否是第一次
    private boolean isFirst = true;
    //第一次选的品牌
    private String oldBrand = "";
    //上次的厚度
    private String oldThick = "";
    private String brandId = "";
    private String classId = "";

    //排序方式  默认是0 综合排序
    private int mSort = 0;
    //数据改变
    private boolean isDataChage = false;
    //是否刷新规格
    private boolean isInitStandard = false;

    EditText mEtSearch;
    EditText mEtSmall;
    EditText mELarge;
    TextView mTvLineTo;
    LinearLayout mLlSearch;

    OrderTypeDetailAdapter mDetailAdapter;
    OnSearchCallBack mSearchCallBack;
    List<BuyYaunListBean> mBuyYuanList;
    BuyYuanAdapter mBuyYuanAdapter;
    LinearLayout mLlNoData;

    //购物车数量
    TextView mTvCarNum;
    private String thickType = "thick";
    private String standardType = "standard";
    //是否搜索厚度
    private boolean isSearchThick = false;
    //是否搜索规格
    private boolean isSearchStandard = false;
    private SmartRefreshLayout mSmartRefreshLayout;

    //品类集合
    List<OrderTypeBean> classList = new ArrayList<>();
    //颜色集合
    List<OrderTypeBean> colorList = new ArrayList<>();
    //品牌集合
    List<OrderTypeBean> brandList = new ArrayList<>();
    //膜系集合
    List<OrderTypeBean> lowList = new ArrayList<>();
    //级别集合
    List<OrderTypeBean> levelList = new ArrayList<>();
    //厚度集合
    List<OrderTypeBean> thickList = new ArrayList<>();
    //规格集合
    List<OrderTypeBean> standardList = new ArrayList<>();
    //刷新所需要的集合
    List<OrderTypeBean> mTypeDetailList;

    private Context mContext;
    private StringTypeAdapter mTypeAdapter;

    //是否需要膜系刷新
    private boolean isMoXiRefresh = true;
    //是否需要厚度刷新
    private boolean isThickRefresh = true;
    //是否需要规格刷新
    private boolean isStandardRefresh = true;

    private int page = 1;
    //类型id
    private int typeId = -1;

    //设置筛选数据
    public void setChooseData(final List<OrderTypeBean> typeList, final StringTypeAdapter typeAdapter, ListView lvType,
                              final List<OrderTypeBean> typeDetailList, final OrderTypeDetailAdapter detailAdapter, ListView lvDetail) {
        typeList.clear();

        OrderTypeBean orderTypeBean = new OrderTypeBean();
        orderTypeBean.setType(mContext.getString(R.string.search_kind));
        orderTypeBean.setTypeId(1);
        typeList.add(orderTypeBean);

        OrderTypeBean orderTypeBean2 = new OrderTypeBean();
        orderTypeBean2.setType(mContext.getString(R.string.search_color));
        orderTypeBean2.setTypeId(2);
        typeList.add(orderTypeBean2);

        OrderTypeBean orderTypeBean3 = new OrderTypeBean();
        orderTypeBean3.setType(mContext.getString(R.string.search_brand));
        orderTypeBean3.setTypeId(3);
        typeList.add(orderTypeBean3);

        OrderTypeBean orderTypeBean4 = new OrderTypeBean();
        orderTypeBean4.setType(mContext.getString(R.string.search_low));
        orderTypeBean4.setTypeId(4);
        typeList.add(orderTypeBean4);

        OrderTypeBean orderTypeBean5 = new OrderTypeBean();
        orderTypeBean5.setType(mContext.getString(R.string.search_level));
        orderTypeBean5.setTypeId(5);
        typeList.add(orderTypeBean5);

        OrderTypeBean orderTypeBean6 = new OrderTypeBean();
        orderTypeBean6.setType(mContext.getString(R.string.search_thick));
        orderTypeBean6.setTypeId(6);
        typeList.add(orderTypeBean6);

        OrderTypeBean orderTypeBean7 = new OrderTypeBean();
        orderTypeBean7.setType(mContext.getString(R.string.search_standard));
        orderTypeBean7.setTypeId(7);
        typeList.add(orderTypeBean7);

        lvType.setAdapter(typeAdapter);
        typeAdapter.notifyDataSetChanged();
        lvType.setDividerHeight(0);
        clickType = "品类";
        lvType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                isDataChage = false;
                typeId = typeList.get(i).getTypeId();
                switch (typeList.get(i).getTypeId()) {
                    case 1:
                        hide();
                        //重置有色
                        isYouSe = false;
                        clickType = "品类";
                        if (classList.size() == 0) {
                            getYuanKind(typeDetailList, detailAdapter);
                        } else {
                            typeDetailList.clear();
                            typeDetailList.addAll(classList);

                            if (typeDetailList.size() < classPosition) {
                                classPosition = -1;
                            }
                            setIsCheck(detailAdapter, classPosition, typeDetailList);
                        }
                        break;
                    case 2:
                        hide();
                        if (!StringUtils.isNoEmpty(className)) {
                            ToastUtils.getMineToast("请先选择品类");
                            return;
                        }
                        if (!className.equals("有色")) {
                            ToastUtils.getMineToast("请选择有色");
                            return;
                        }
                        isYouSe = true;
                        clickType = "颜色";

                        if (colorList.size() == 0) {
                            getYuanKind(typeDetailList, detailAdapter);
                        } else {
                            typeDetailList.clear();
                            typeDetailList.addAll(colorList);

                            if (typeDetailList.size() < colorPosition) {
                                colorPosition = -1;
                            }
                            setIsCheck(detailAdapter, colorPosition, typeDetailList);
                        }
                        break;
                    case 3:
                        hide();
                        clickType = "品牌";
                        getBrand(typeDetailList, detailAdapter);
                        break;
                    case 4:
                        hide();
                        if (!StringUtils.isNoEmpty(className)) {
                            ToastUtils.getMineToast("请先选择品类");
                            return;
                        }
                        if (!className.equals("Low-E")) {
                            ToastUtils.getMineToast("请选择Low-E");
                            return;
                        }
                        clickType = "膜系";
                        if (isMoXiRefresh) {
                            getMoXi(typeDetailList, detailAdapter);
                        } else {
                            typeDetailList.clear();
                            typeDetailList.addAll(lowList);

                            if (typeDetailList.size() < moxiPosition) {
                                moxiPosition = -1;
                            }
                            setIsCheck(detailAdapter, moxiPosition, typeDetailList);

                        }
                        break;
                    case 5:
                        clickType = "级别";
                        hide();
                        getLevel(typeDetailList, detailAdapter);
                        break;
                    case 6:
                        clickType = "厚度";
                        isHide(thickType);
                        if (isThickRefresh) {
                            weightIndex = 1;
                            getThick(typeDetailList, detailAdapter);
                        } else {
                            typeDetailList.clear();
                            typeDetailList.addAll(thickList);

                            if (typeDetailList.size() < thickPosition) {
                                thickPosition = -1;
                            }
                            setIsCheck(detailAdapter, thickPosition, typeDetailList);
                        }
                        break;
                    case 7:
                        clickType = "规格";
                        isHide(standardType);

                        if (isInitStandard) {
                            standard = "";
                        }
                        standardIndex = 1;
                        getStandard(typeDetailList, detailAdapter);
                        break;
                }
                typeAdapter.setItemClickPosition(i);
                typeAdapter.notifyDataSetChanged();
            }
        });

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                switch (typeId) {
                    case 6:
                        weightIndex = 1;
                        getThick(typeDetailList, detailAdapter);
                        break;
                    case 7:
                        standardIndex = 1;
                        getStandard(typeDetailList, detailAdapter);
                        break;
                    default:
                        if (mSmartRefreshLayout != null) {
                            mSmartRefreshLayout.finishLoadMore();
                            mSmartRefreshLayout.finishRefresh();
                        }
                        break;
                }
            }
        });
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                switch (typeId) {
                    case 6:
                        weightIndex++;
                        getThick(typeDetailList, detailAdapter);
                        break;
                    case 7:
                        standardIndex++;
                        getStandard(typeDetailList, detailAdapter);
                        break;
                    default:
                        if (mSmartRefreshLayout != null) {
                            mSmartRefreshLayout.finishLoadMore();
                            mSmartRefreshLayout.finishRefresh();
                        }
                        break;
                }
            }
        });

        lvDetail.setAdapter(detailAdapter);
        lvDetail.setDividerHeight(0);

        //默认第一条被选中
        typeAdapter.setItemClickPosition(0);
        typeAdapter.notifyDataSetChanged();

        //点击事件
        lvDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (clickType) {
                    case "品类":
                        brand = "";
                        brandPosition = -1;
                        thickPosition = -1;
                        thick = "";
                        setIsCancelClick(detailAdapter, i, typeDetailList, "className", typeDetailList.get(i).getType(), typeDetailList.get(i).getID() + "");
                        //如果不是有色  有色的类别被清空
                        if (!className.equals("有色")) {
                            colorName = "";
                            colorPosition = -1;
                        }
                        break;
                    case "颜色":
                        brand = "";
                        brandPosition = -1;
                        thickPosition = -1;
                        thick = "";
                        setIsCancelClick(detailAdapter, i, typeDetailList, "colorName", typeDetailList.get(i).getType(), typeDetailList.get(i).getID() + "");
                        break;
                    case "品牌":
                        setIsCancelClick(detailAdapter, i, typeDetailList, "brand", typeDetailList.get(i).getType(), typeDetailList.get(i).getID() + "");
                        break;
                    case "膜系":
                        setIsCancelClick(detailAdapter, i, typeDetailList, "low", typeDetailList.get(i).getType(), typeDetailList.get(i).getID() + "");
                        break;
                    case "级别":
                        setIsCancelClick(detailAdapter, i, typeDetailList, "level", typeDetailList.get(i).getType(), typeDetailList.get(i).getID() + "");
                        break;
                    case "厚度":
                        setIsCancelClick(detailAdapter, i, typeDetailList, "thick", typeDetailList.get(i).getType(), typeDetailList.get(i).getID() + "");
                        break;
                    case "规格":
                        int num = typeDetailList.size();
                        int chooseNum = 0;

                        for (int j = 0; j < num; j++) {
                            if (typeDetailList.get(j).isCheck()) {
                                chooseNum += 1;
                            }
                        }
                        if (chooseNum > 4) {
                            ToastUtils.getMineToast("最多选五个规格");
                            return;
                        }
                        setMoreCancelClick(detailAdapter, i, typeDetailList, "standard", typeDetailList.get(i).getType(), typeDetailList.get(i).getID() + "");
//                        setIsCancelClick(detailAdapter, i, typeDetailList, "standard", typeDetailList.get(i).getType(), typeDetailList.get(i).getID() + "");
                        break;
                }
            }
        });
    }

    //选择显示
    private TextView mTvSelect;
    //厚度页码
    private int weightIndex = 1;
    //规格页码
    private int standardIndex = 1;

    //买原片数据绑定显示
    public void bindYuanData(TextView tvCarNum, EditText etSearch, final LinearLayout llNoData, DrawerLayout drawerLayout, final Context context, List<OrderTypeBean> typeList, StringTypeAdapter typeAdapter,
                             ListView lvType, final List<OrderTypeBean> typeDetailList, final OrderTypeDetailAdapter detailAdapter,
                             ListView lvDetail, ListView lvRefresh, final BuyYuanAdapter buyYuanAdapter, final List<BuyYaunListBean> buyYuanList,
                             final SmartRefreshLayout xRefresh, TextView tvSelect, TextView tvAgain, TextView tvConfirm,
                             final int animationDuration, final FrameLayout viewGroup, final ImageView imgCar,
                             EditText etSmall, EditText etLarge, TextView tvLineTo, LinearLayout llSearche, String keyWord, SmartRefreshLayout smartRefreshLayout,
                             final OnSearchCallBack searchCallBack) {
        mTypeAdapter = typeAdapter;
        mSmartRefreshLayout = smartRefreshLayout;
        mTvCarNum = tvCarNum;
        mTvSelect = tvSelect;
        mTypeDetailList = typeDetailList;
        mContext = context;
        mEtSearch = etSearch;
        mEtSmall = etSmall;
        mELarge = etLarge;
        mTvLineTo = tvLineTo;
        mLlSearch = llSearche;
        mDetailAdapter = detailAdapter;
        mSearchCallBack = searchCallBack;
        mBuyYuanList = buyYuanList;
        mBuyYuanAdapter = buyYuanAdapter;
        mLlNoData = llNoData;
        mSort = 0;
        mEtSearch.setOnEditorActionListener(this);
        mEtSmall.setOnEditorActionListener(this);
        mELarge.setOnEditorActionListener(this);

        //重置数据
        yuanReset(detailAdapter);

        if (StringUtils.isNoEmpty(keyWord)) {
            brand = keyWord;
        }
        //加载品类数据
        getYuanKind(typeDetailList, detailAdapter);

        lvRefresh.setAdapter(buyYuanAdapter);
        getYuanData(buyYuanAdapter, buyYuanList, null, llNoData);

        BuyFunction.getInstance().setChooseData(typeList, typeAdapter, lvType, typeDetailList, detailAdapter, lvDetail);

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                if (isFirst) {
                    isYouSe = false;
                    clickType = "品类";
                    getYuanKind(typeDetailList, detailAdapter);
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
        xRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                buyYuanList.clear();
                getYuanData(buyYuanAdapter, buyYuanList, xRefresh, llNoData);
            }
        });
        xRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getYuanData(buyYuanAdapter, buyYuanList, xRefresh, llNoData);
            }
        });

        //重置
        tvAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yuanReset(detailAdapter);
            }
        });
        //确认
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSearchStandard) {
                    String small = mEtSmall.getText().toString().trim();
                    String large = mELarge.getText().toString().trim();
                    LogUtils.e(small + "  " + large);
                    if ((StringUtils.isNoEmpty(small) && !StringUtils.isNoEmpty(large))) {
                        ToastUtils.getMineToast("请完善规格");
                        return;
                    }
                    if (!StringUtils.isNoEmpty(small) && StringUtils.isNoEmpty(large)) {
                        ToastUtils.getMineToast("请完善规格");
                        return;
                    }
                    if (StringUtils.isNoEmpty(small) && StringUtils.isNoEmpty(large)) {
                        standard = small + "*" + large + ",";
                        standardPosition = -1;
                        detailAdapter.setItemClickPosition(standardPosition);
                        detailAdapter.notifyDataSetChanged();
                        getSelectData();
                    }
                }
                searchCallBack.onConfirm();
                page = 1;
                buyYuanList.clear();

                getYuanData(buyYuanAdapter, buyYuanList, null, llNoData);
            }
        });
        //输入框改变的时候改变厚度值
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (StringUtils.isNoEmpty(charSequence.toString())) {
                    thick = charSequence.toString() + "mm";
                    thickPosition = -1;
                    detailAdapter.setItemClickPosition(-1);
                    detailAdapter.notifyDataSetChanged();
                }

                getSelectData();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
//        watchSearch(detailAdapter, searchCallBack, buyYuanList, buyYuanAdapter, llNoData);
        //加入购物车
        buyYuanAdapter.setCarClickBack(new OnAddCarClickBack() {
            @Override
            public void onAddCarClick(String id, final Drawable drawable, final int[] startLocation) {
                //加一个到购物车
                addToYuanCar("0", mContext, id, OrderTypeConstant.getInstance().ADD_CAR, new StringCallBack() {
                    @Override
                    public void onSuccess(String carCount) {
                        CarCountBean successBean = JSON.parseObject(carCount, CarCountBean.class);
                        if (successBean.getSuccess() == 1) {
                            ToastUtils.getMineToast(mContext.getString(R.string.add_success));
                            AnimationUtils.setAnim(drawable, startLocation, animationDuration, context, viewGroup, imgCar);
                            mTvCarNum.setText(successBean.getReObj().getCatCount() + "");
                        } else {
                            ToastUtils.getMineToast(mContext.getString(R.string.add_fail));
                        }
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
                IntentUtils.getInstance().toActivity(context, SingleOrderCommitActivity.class, buyYuanList.get(i), OrderTypeConstant.getInstance().YP);
            }
        });
    }

    //原片重置
    private void yuanReset(OrderTypeDetailAdapter detailAdapter) {
        //品类
        className = "";
        //颜色
        colorName = "";
        //品牌
        brand = "";
        //膜系
        low = "";
        //厚度
        thick = "";
        //级别
        level = "";
        //规格
        standard = "";
        //点击类型
//        clickType = "";
        //第一次
//        isFirst = true;
        //各项被选中位置
        classPosition = -1;
        colorPosition = -1;
        brandPosition = -1;
        moxiPosition = -1;
        levelPosition = -1;
        thickPosition = -1;
        standardPosition = -1;

        //是否是有色
        isYouSe = false;
        int num = mTypeDetailList.size();
        for (int i = 0; i < num; i++) {
            mTypeDetailList.get(i).setCheck(false);
        }
        detailAdapter.notifyDataSetChanged();
        mELarge.setText("");
        mEtSmall.setText("");
        mEtSearch.setText("");
        mTypeAdapter.setYouSeHide(true);
        mTypeAdapter.notifyDataSetChanged();
        //重置
        getSelectData();
    }

    //语音搜索数据
    public void voiceData(String brand, String className, String standard, String thick, String level) {
        this.brand = brand;
        this.className = className;
        this.standard = standard;
        this.thick = thick;
        this.level = level;
        mBuyYuanList.clear();
        getYuanData(mBuyYuanAdapter, mBuyYuanList, null, mLlNoData);
    }

    /**
     * 搜索原片数据
     *
     * @param buyYuanAdapter
     * @param buyYuanList
     */
    public void getYuanData(final BuyYuanAdapter buyYuanAdapter, final List<BuyYaunListBean> buyYuanList,
                            final SmartRefreshLayout xRefresh, final LinearLayout llNoData) {
        String endStantdard = "";
        if (standard.length() > 1) {
            endStantdard = standard.substring(0, standard.length() - 1);
        } else {
            endStantdard = standard;
        }
        LogUtils.e(standard + " --- " + endStantdard);
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(mContext, UrlConstant.getInstance().URL + PostConstant.getInstance().GET_PRODUCT_DATA
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().BRAND_NAME + "=" + EncodeUtils.getInstance().getEncodeString(brand) + "&"
                + FieldConstant.getInstance().CLASS_NAME + "=" + EncodeUtils.getInstance().getEncodeString(className) + "&"
                + FieldConstant.getInstance().LEVEL + "=" + EncodeUtils.getInstance().getEncodeString(level) + "&"
                + FieldConstant.getInstance().WEIGHT + "=" + EncodeUtils.getInstance().getEncodeString(thick) + "&"
                + FieldConstant.getInstance().XY + "=" + endStantdard + "&"
                + FieldConstant.getInstance().PRODUCT_TYPE_DEC + "=0" + "&"
                + FieldConstant.getInstance().COLOR_NAME + "=" + EncodeUtils.getInstance().getEncodeString(colorName) + "&"
                + FieldConstant.getInstance().LOW_NAME + "=" + EncodeUtils.getInstance().getEncodeString(low) + "&"
                + FieldConstant.getInstance().SORT + "=" + mSort + "&"
                + FieldConstant.getInstance().PAGE_INDEX + "=" + page, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                if (buyYuanList == null) {
                    return;
                }
                BuyYuanBean buyYuanBean = JSON.parseObject(json, BuyYuanBean.class);
                if (buyYuanBean == null) {
                    return;
                }
                buyYuanList.addAll(buyYuanBean.getReList());
                buyYuanAdapter.notifyDataSetChanged();

                SmartUtils.finishSmart(xRefresh);

                if (buyYuanList.size() == 0) {
                    llNoData.setVisibility(View.VISIBLE);
                } else {
                    llNoData.setVisibility(View.GONE);
                }
                //购物车中的数量
                mTvCarNum.setText(buyYuanBean.getTotalCount() + "");
            }

            @Override
            public void onFail() {
                SmartUtils.finishSmart(xRefresh);
                llNoData.setVisibility(View.VISIBLE);
            }
        });
    }

    int isSe;

    //获取品类
    public void getYuanKind(final List<OrderTypeBean> typeDetailList, final OrderTypeDetailAdapter detailAdapter) {

        if (isYouSe) {
            isSe = 1;
        } else {
            isSe = 0;
        }
        typeDetailList.clear();
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(mContext, UrlConstant.getInstance().URL + PostConstant.getInstance().GET_YUAN_KIND
                + FieldConstant.getInstance().IS_YOUSE + "=" + isSe, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                YuanClassBean yuanClassBean = JSON.parseObject(json, YuanClassBean.class);

                if (typeDetailList == null) {
                    return;
                }
                List<YuanClassListBean> classListBeen = yuanClassBean.getReList();
                int num = classListBeen.size();
                for (int i = 0; i < num; i++) {
                    OrderTypeBean typeBean = new OrderTypeBean();
                    typeBean.setType(classListBeen.get(i).getClassName());
                    typeBean.setID(classListBeen.get(i).getClassID());
                    typeDetailList.add(typeBean);
                }
                detailAdapter.notifyDataSetChanged();

                //如果是有色的
                if (isYouSe) {
                    colorList.clear();
                    colorList.addAll(typeDetailList);

                    if (typeDetailList.size() < colorPosition) {
                        colorPosition = -1;
                    }
                    setIsCheck(detailAdapter, colorPosition, typeDetailList);
                    //品类
                } else {
                    classList.clear();
                    classList.addAll(typeDetailList);

                    if (typeDetailList.size() < classPosition) {
                        classPosition = -1;
                    }
                    setIsCheck(detailAdapter, classPosition, typeDetailList);
                }

                isDataChage = true;
            }

            @Override
            public void onFail() {
            }
        });
    }

    //获取品牌
    public void getBrand(final List<OrderTypeBean> typeDetailList, final OrderTypeDetailAdapter detailAdapter) {
        String str = "";
        if (StringUtils.isNoEmpty(colorName)) {
            str = colorName;
        } else {
            str = className;
        }
        typeDetailList.clear();
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(mContext, UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_YUAN_BRAND_NEW
                + FieldConstant.getInstance().COLOR_NAME + "=" + EncodeUtils.getInstance().getEncodeString(colorName) + "&"
                + FieldConstant.getInstance().CLASS_NAME + "=" + EncodeUtils.getInstance().getEncodeString(str) + "&"
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
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
                    if (StringUtils.isNoEmpty(classListBeen.get(i).getBrandName())) {
                        typeBean.setType(classListBeen.get(i).getBrandName());
                    } else {
                        typeBean.setType("暂无");
                    }

                    typeBean.setID(classListBeen.get(i).getBrandID());
                    LogUtils.i(classListBeen.get(i).getBrandName() + "  " + classListBeen.get(i).getBrandID());
                    typeDetailList.add(typeBean);
                }
                detailAdapter.notifyDataSetChanged();
                brandList.clear();
                brandList.addAll(typeDetailList);
                if (typeDetailList.size() < brandPosition) {
                    brandPosition = -1;
                }
                setIsCheck(detailAdapter, brandPosition, typeDetailList);
                isDataChage = true;
            }

            @Override
            public void onFail() {
            }
        });
    }

    //获取膜系
    public void getMoXi(final List<OrderTypeBean> typeDetailList, final OrderTypeDetailAdapter detailAdapter) {
        typeDetailList.clear();
        lowList.clear();
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(mContext, UrlConstant.getInstance().URL + PostConstant.getInstance().GET_YUAN_MOXI
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().COLOR_NAME + "=" + EncodeUtils.getInstance().getEncodeString(colorName) + "&"
                + FieldConstant.getInstance().BRAND_NAME + "=" + EncodeUtils.getInstance().getEncodeString(brand), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                if (typeDetailList == null) {
                    return;
                }
                YuanLowBean lowBean = JSON.parseObject(json, YuanLowBean.class);
                List<YuanLowListBean> classListBeen = lowBean.getReList();
                int num = classListBeen.size();
                for (int i = 0; i < num; i++) {
                    OrderTypeBean typeBean = new OrderTypeBean();
                    typeBean.setType(classListBeen.get(i).getMoxiName());
                    typeDetailList.add(typeBean);
                    lowList.add(typeBean);
                }
                detailAdapter.notifyDataSetChanged();

                if (typeDetailList.size() < moxiPosition) {
                    moxiPosition = -1;
                }
                setIsCheck(detailAdapter, moxiPosition, typeDetailList);
                isDataChage = true;
            }

            @Override
            public void onFail() {
            }
        });
    }

    //获取等级
    public void getLevel(final List<OrderTypeBean> typeDetailList, final OrderTypeDetailAdapter detailAdapter) {
        typeDetailList.clear();
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(mContext, UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_YUAN_LEVEL_NEW
                + FieldConstant.getInstance().BRAND_NAME + "=" + EncodeUtils.getInstance().getEncodeString(brand) + "&"
                + FieldConstant.getInstance().CLASS_NAME + "=" + EncodeUtils.getInstance().getEncodeString(className) + "&"
                + FieldConstant.getInstance().COLOR_NAME + "=" + EncodeUtils.getInstance().getEncodeString(colorName) + "&"
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                if (typeDetailList == null) {
                    return;
                }
                YuanLevelBean levelBean = JSON.parseObject(json, YuanLevelBean.class);
                List<YuanLevelListBean> classListBeen = levelBean.getReList();
                int num = classListBeen.size();
                for (int i = 0; i < num; i++) {
                    OrderTypeBean typeBean = new OrderTypeBean();
                    typeBean.setType(classListBeen.get(i).getLevelName());
                    typeDetailList.add(typeBean);
                }
                detailAdapter.notifyDataSetChanged();

                levelList.clear();
                levelList.addAll(typeDetailList);

                if (typeDetailList.size() < levelPosition) {
                    levelPosition = -1;
                }
                setIsCheck(detailAdapter, levelPosition, typeDetailList);

                isDataChage = true;
            }

            @Override
            public void onFail() {
            }
        });
    }

    //获取厚度
    public void getThick(final List<OrderTypeBean> typeDetailList, final OrderTypeDetailAdapter detailAdapter) {
//        typeDetailList.clear();
//        thickList.clear();
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(mContext, UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_YUAN_THICKNESS
                + FieldConstant.getInstance().PAGE_INDEX + "=" + weightIndex + "&"
                + FieldConstant.getInstance().COLOR_NAME + "=" + EncodeUtils.getInstance().getEncodeString(colorName) + "&"
                + FieldConstant.getInstance().BRAND_NAME + "=" + EncodeUtils.getInstance().getEncodeString(brand), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                if (typeDetailList == null) {
                    return;
                }
                if (weightIndex == 1) {
                    typeDetailList.clear();
                    thickList.clear();
                }
                YuanThickBean thickBean = JSON.parseObject(json, YuanThickBean.class);
                List<YuanThickListBean> classListBeen = thickBean.getReList();
                int num = classListBeen.size();
                for (int i = 0; i < num; i++) {
                    OrderTypeBean typeBean = new OrderTypeBean();
                    typeBean.setType(classListBeen.get(i).getWeight() + "mm");
                    typeDetailList.add(typeBean);
                    thickList.add(typeBean);
                }
                detailAdapter.notifyDataSetChanged();
                if (typeDetailList.size() < thickPosition) {
                    thickPosition = -1;
                }
                isDataChage = true;
                setIsCheck(detailAdapter, thickPosition, typeDetailList);
                if (mSmartRefreshLayout != null) {
                    mSmartRefreshLayout.finishLoadMore();
                    mSmartRefreshLayout.finishRefresh();
                }
            }

            @Override
            public void onFail() {
                if (mSmartRefreshLayout != null) {
                    mSmartRefreshLayout.finishLoadMore();
                    mSmartRefreshLayout.finishRefresh();
                }
            }
        });
    }

    //获取规格
    public void getStandard(final List<OrderTypeBean> typeDetailList, final OrderTypeDetailAdapter detailAdapter) {
//        typeDetailList.clear();
//        standardList.clear();
        String thickStandard = thick;
        if (StringUtils.isNoEmpty(thick)) {
            thickStandard = thick.substring(0, thick.indexOf("mm"));
        }

        OkhttpUtil.getInstance().setUnCacheDataNoDialog(mContext, UrlConstant.getInstance().URL + PostConstant.getInstance().GET_YUAN_SATNDARD
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().WEIGHT + "=" + thickStandard + "&"
                + FieldConstant.getInstance().PAGE_INDEX + "=" + standardIndex + "&"
                + FieldConstant.getInstance().COLOR_NAME + "=" + EncodeUtils.getInstance().getEncodeString(colorName) + "&"
                + FieldConstant.getInstance().CLASS_NAME + "=" + EncodeUtils.getInstance().getEncodeString(className) + "&"
                + FieldConstant.getInstance().BRAND_NAME + "=" + EncodeUtils.getInstance().getEncodeString(brand), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                if (typeDetailList == null) {
                    return;
                }
                if (standardIndex == 1) {
                    typeDetailList.clear();
                    standardList.clear();
                }
                YuanStandardBean standardBean = JSON.parseObject(json, YuanStandardBean.class);
                List<YuanStandardListBean> classListBeen = standardBean.getReList();
                int num = classListBeen.size();
                for (int i = 0; i < num; i++) {
                    OrderTypeBean typeBean = new OrderTypeBean();
                    typeBean.setType(classListBeen.get(i).getXy());
                    typeDetailList.add(typeBean);
                    standardList.add(typeBean);
                }
                String[] split = standard.split("/");
                int num2 = split.length;

                for (int j = 0; j < num2; j++) {
                    for (int k = 0; k < num; k++) {
                        if (split[j].equals(typeDetailList.get(k).getType())) {
                            typeDetailList.get(k).setCheck(true);
                        }
                    }
                    LogUtils.i(split[j]);
                }

                detailAdapter.notifyDataSetChanged();

                isDataChage = true;
                if (mSmartRefreshLayout != null) {
                    mSmartRefreshLayout.finishLoadMore();
                    mSmartRefreshLayout.finishRefresh();
                }
            }

            @Override
            public void onFail() {
                if (mSmartRefreshLayout != null) {
                    mSmartRefreshLayout.finishLoadMore();
                    mSmartRefreshLayout.finishRefresh();
                }
            }
        });
    }

    //原片已选择数据
    public void getSelectData() {
        mTvSelect.setText("已选择：" + className + getShowStr(colorName) + getShowStr(brand) + getShowStr(low) + getShowStr(level) + getShowStr(thick) + getShowStr(standard));
    }

    //多选可取消的点击事件
    private void setMoreCancelClick(OrderTypeDetailAdapter detailAdapter, int i,
                                    List<OrderTypeBean> typeDetailList, String type, String value, String id) {
        if (!isDataChage) {
            return;
        }
        //点击品牌的时候
        OrderTypeBean typeBean = typeDetailList.get(i);
        if (!typeBean.isCheck()) {
            typeBean.setCheck(true);
            detailAdapter.setItemClickPosition(i);
            //设置其他的为未选中
//            setFalse(typeDetailList, i);
            getSwitch(type, i, value, id);
        } else {
            typeBean.setCheck(false);
            detailAdapter.setItemClickPosition(-1);
            getSwitch(type, -1, "", "");
        }
        int num = typeDetailList.size();
        standard = "";
        for (int j = 0; j < num; j++) {
            if (typeDetailList.get(j).isCheck()) {
                standard += typeDetailList.get(j).getType() + ",";
            }
        }
        detailAdapter.notifyDataSetChanged();
        getSelectData();
    }

    //可取消的点击事件
    private void setIsCancelClick(OrderTypeDetailAdapter detailAdapter, int i,
                                  List<OrderTypeBean> typeDetailList, String type, String value, String id) {
        if (!isDataChage) {
            return;
        }
        //点击品牌的时候
        OrderTypeBean typeBean = typeDetailList.get(i);
        if (!typeBean.isCheck()) {
            typeBean.setCheck(true);
            detailAdapter.setItemClickPosition(i);
            detailAdapter.notifyDataSetChanged();
            //设置其他的为未选中
            setFalse(typeDetailList, i);
            getSwitch(type, i, value, id);
            //两次选择的品牌是一样的时候 厚度的选择位置不变
            if (type.equals("brand")) {
                if (StringUtils.isNoEmpty(oldBrand) && !oldBrand.equals(typeBean.getType())) {
                    thick = "";
                    thickPosition = -1;
                    isMoXiRefresh = true;
                    isThickRefresh = true;
                    isStandardRefresh = true;
                } else {
                    isStandardRefresh = false;
                }
                //获取上次的位置
                oldBrand = typeBean.getType();
            } else if (type.equals("thick")) {
                oldThick = typeBean.getType();
            }
        } else {
            typeBean.setCheck(false);
            detailAdapter.setItemClickPosition(-1);
            detailAdapter.notifyDataSetChanged();
            getSwitch(type, -1, "", "");
            if (type.equals("brand")) {
                thick = "";
                thickPosition = -1;
            }
        }
        getSelectData();
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

    private void getSwitch(String type, int i, String value, String id) {
        switch (type) {
            case "className":
                classPosition = i;
                className = value;
                classId = id;
                mTypeAdapter.setYouSeHide(true);
                mTypeAdapter.setLowHide(true);
                levelPosition = -1;
                //如果选的不是有色
                if (!value.equals("有色")) {
                    colorPosition = -1;
                    colorName = "";
//                    mTypeAdapter.setYouSeHide(true);
                } else {
                    mTypeAdapter.setYouSeHide(false);
                }
                if (!value.equals("Low-E")) {
                    moxiPosition = -1;
                    low = "";
                    mTypeAdapter.setLowHide(true);
                    mTypeAdapter.setLevelHide(false);
                } else {
                    levelPosition = -1;
                    level = "";
                    mTypeAdapter.setLowHide(false);
                    mTypeAdapter.setLevelHide(true);
                }
                mTypeAdapter.notifyDataSetChanged();
                isInitStandard = false;
                break;
            case "colorName":
                colorPosition = i;
                colorName = value;
                isInitStandard = false;
                break;
            case "brand":
                isInitStandard = true;
                brandPosition = i;
                brand = value;
                brandId = id;
                standardPosition = -1;
                standard = "";
                moxiPosition = -1;
                levelPosition = -1;
                low = "";
                mELarge.setText("");
                mEtSmall.setText("");
                break;
            case "low":
                moxiPosition = i;
                low = value;
                isInitStandard = false;
                break;
            case "thick":
                thickPosition = i;
                isInitStandard = true;
                thick = value;
                mEtSearch.setText("");
                SharedPreferencesUtils.getInstance().put(BaseApp.getInstance(), SharedKeyConstant.getInstance().THICK_POSITION, i);
                SharedPreferencesUtils.getInstance().put(BaseApp.getInstance(), SharedKeyConstant.getInstance().THICK_VALUE, value);
                standardPosition = -1;
                standard = "";
                break;
            case "level":
                levelPosition = i;
                level = value;
                isInitStandard = false;
                break;
            case "standard":
                standardPosition = i;
                standard = value;
                mEtSmall.setText("");
                mELarge.setText("");
                break;
        }
    }

    private void setIsCheck(OrderTypeDetailAdapter detailAdapter, int position, List<OrderTypeBean> detailList) {
        if (position >= 0 && detailList.size() >= (position + 1)) {
            detailAdapter.setItemClickPosition(position);
            detailList.get(position).setCheck(true);
        } else {
//            detailList.get(position).setCheck(false);
            detailAdapter.setItemClickPosition(-1);
        }
        detailAdapter.notifyDataSetChanged();
        isDataChage = true;
    }

    public String getShowStr(String str) {
        if (StringUtils.isNoEmpty(str)) {
            str = "/" + str;
        }
        return str;
    }

    //设置排序方式
    public void setSort(int sort, BuyYuanAdapter buyYuanAdapter, List<BuyYaunListBean> buyYuanList, LinearLayout llNoData) {
        mSort = sort;
        page = 1;
        buyYuanList.clear();
        getYuanData(buyYuanAdapter, buyYuanList, null, llNoData);
    }

    //添加原片购物车  addCarType 0 原片   1 辅材 2原料
    public void addToYuanCar(String addCarType, Context context, String id, String num, final StringCallBack carCallBack) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(context, UrlConstant.getInstance().URL
                + PostConstant.getInstance().ADD_YUAN_CAR
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().ID + "=" + id + "&"
                + FieldConstant.getInstance().TO_NUM + "=" + num + "&"
                + FieldConstant.getInstance().ADD_CAR_TYPE + "=" + addCarType, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                carCallBack.onSuccess(json);
            }

            @Override
            public void onFail() {
                carCallBack.onFail();
            }
        });
    }

    //判断隐藏规格还是厚度
    public void isHide(String str) {
        mLlSearch.setVisibility(View.VISIBLE);
        if (str.equals("thick")) {
            mEtSearch.setVisibility(View.VISIBLE);
            mEtSmall.setVisibility(View.GONE);
            mELarge.setVisibility(View.GONE);
            mTvLineTo.setVisibility(View.GONE);
            isSearchThick = true;
            isSearchStandard = false;
        } else if (str.equals("standard")) {
            mEtSearch.setVisibility(View.GONE);
            mEtSmall.setVisibility(View.VISIBLE);
            mELarge.setVisibility(View.VISIBLE);
            mTvLineTo.setVisibility(View.VISIBLE);
            isSearchThick = false;
            isSearchStandard = true;
        }
    }

    public void hide() {
        mLlSearch.setVisibility(View.GONE);
        isSearchThick = false;
        isSearchStandard = false;
    }

    /**
     * @方法说明:监控软键盘的的搜索按钮
     * @方法名称:watchSearch
     * @返回值:void
     */
    public void watchSearch(final OrderTypeDetailAdapter detailAdapter, final OnSearchCallBack searchCallBack,
                            final List<BuyYaunListBean> buyYuanList, final BuyYuanAdapter buyYuanAdapter, final LinearLayout llNoData) {
        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    KeyBoardUtils.closeKeybord(mEtSearch, mContext);
                    searchData(detailAdapter, searchCallBack, buyYuanList, buyYuanAdapter, llNoData);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_DONE) {

            isThickStandard();
            KeyBoardUtils.closeKeybord(mEtSearch, mContext);
//            searchData(mDetailAdapter, mSearchCallBack, mBuyYuanList, mBuyYuanAdapter, mLlNoData);
            return true;
        }
        return false;
    }

    //判断是否是厚度还是规格
    public void isThickStandard() {
        if (isSearchStandard) {
            String small = mEtSmall.getText().toString().trim();
            String large = mELarge.getText().toString().trim();
            if ((StringUtils.isNoEmpty(small) && !StringUtils.isNoEmpty(large))) {
                ToastUtils.getMineToast("请完善规格");
                return;
            }
            if (!StringUtils.isNoEmpty(small) && StringUtils.isNoEmpty(large)) {
                ToastUtils.getMineToast("请完善规格");
                return;
            }
            standard = small + "*" + large;
            standardPosition = -1;
            mDetailAdapter.setItemClickPosition(standardPosition);
            mDetailAdapter.notifyDataSetChanged();
            getSelectData();
        } else {
            thick = mEtSearch.getText().toString().trim() + "mm";
            thickPosition = -1;
            mDetailAdapter.setItemClickPosition(thickPosition);
            mDetailAdapter.notifyDataSetChanged();
            getSelectData();
        }
    }

    private void searchData(OrderTypeDetailAdapter detailAdapter, OnSearchCallBack searchCallBack,
                            List<BuyYaunListBean> buyYuanList, BuyYuanAdapter buyYuanAdapter, LinearLayout llNoData) {
        if (isSearchStandard) {
            String small = mEtSmall.getText().toString().trim();
            String large = mELarge.getText().toString().trim();
            if ((StringUtils.isNoEmpty(small) && !StringUtils.isNoEmpty(large))) {
                ToastUtils.getMineToast("请完善规格");
                return;
            }
            if (!StringUtils.isNoEmpty(small) && StringUtils.isNoEmpty(large)) {
                ToastUtils.getMineToast("请完善规格");
                return;
            }
            standard = small + "*" + large;
            standardPosition = -1;
            detailAdapter.setItemClickPosition(standardPosition);
            detailAdapter.notifyDataSetChanged();
            getSelectData();
        }
        searchCallBack.onConfirm();
        page = 1;
        buyYuanList.clear();
        getYuanData(buyYuanAdapter, buyYuanList, null, llNoData);
    }

    private String locaHead = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <meta charset=\"utf-8\">\n" +
            "    <meta content=\"width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no\" name=\"viewport\">\n" +
            "    <meta content=\"yes\" name=\"apple-mobile-web-app-capable\">\n" +
            "    <meta content=\"black\" name=\"apple-mobile-web-app-status-bar-style\">\n" +
            "    <meta content=\"yes\" name=\"apple-mobile-web-app-capable\">\n" +
            "    <meta content=\"black\" name=\"apple-mobile-web-app-status-bar-style\">\n" +
            "    <meta content=\"telephone=no\" name=\"format-detection\">\n" +
            "    <meta content=\"email=no\" name=\"format-detection\">\n" +
            "　　<link rel=\"stylesheet\" href=\"css/*.css\" type=\"text/css\">\n" +
            "<title>主结构</title>\n" +
            "</head>\n" +
            "<body>";
    private String localEnd = "<script type=\"text/javascript\" src=\"js/*.js\"></script>\n" +
            "</body>\n" +
            "</html>";

    //订单详情
    public void getOrderDetail(final Context context, String proType, String id) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(context, UrlConstant.getInstance().LOCAL_URL + "GetProductDetail/?ProductType=" + proType + "&Id=" + id, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                LogUtils.e(json);
                OrderWebDetailBean detailBean = JSON.parseObject(json, OrderWebDetailBean.class);
                if (detailBean.getSuccess() == -1) {
                    ToastUtil.toastLongMessage(detailBean.getErrMsg());
                    return;
                }
                String detailData = detailBean.getReObj().getDescription();
                if (StringUtils.isNoEmpty(detailData)) {
                    String data = locaHead + detailData + localEnd;
                    IntentUtils.getInstance().toActivity(context, LocalWebActivity.class, data, "订单详情");
                }else {
                    ToastUtils.getMineToast("暂无详情内容");
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

}
