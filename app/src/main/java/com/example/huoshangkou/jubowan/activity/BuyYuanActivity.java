package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;

import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.BuyFuFunction;
import com.example.huoshangkou.jubowan.activity.function.BuyFunction;
import com.example.huoshangkou.jubowan.activity.function.BuyRawMaterialFunction;
import com.example.huoshangkou.jubowan.adapter.BuyFuAdapter;
import com.example.huoshangkou.jubowan.adapter.BuyYuanAdapter;
import com.example.huoshangkou.jubowan.adapter.OrderTypeDetailAdapter;
import com.example.huoshangkou.jubowan.adapter.RawMaterialAdapter;
import com.example.huoshangkou.jubowan.adapter.StringTypeAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.base.HideBaseActivity;
import com.example.huoshangkou.jubowan.bean.BuyFuListBean;
import com.example.huoshangkou.jubowan.bean.BuyYaunListBean;
import com.example.huoshangkou.jubowan.bean.OrderTypeBean;
import com.example.huoshangkou.jubowan.bean.RawMaterialBean;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.inter.OnSearchCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.AnimationUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LoginUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：BuyYuanActivity
 * 描述：买原片界面
 * 创建时间：2017-02-16  16:11
 */

public class BuyYuanActivity extends HideBaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tab_buy)
    TabLayout tabBuy;
    @Bind(R.id.tv_select)
    TextView tvSelect;
    //侧滑菜单
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    //左边的类型
    @Bind(R.id.lv_type)
    ListView lvType;
    //右边的详情
    @Bind(R.id.lv_detail)
    ListView lvDetail;
    @Bind(R.id.tv_select_detail)
    TextView tvSelectDetail;
    //重置
    @Bind(R.id.tv_again)
    TextView tvAgain;
    //确定
    @Bind(R.id.tv_confirm)
    TextView tvConfirm;
    //类型数据
    List<OrderTypeBean> typeList;
    //适配器
    StringTypeAdapter typeAdapter;
    //详情数据类型
    List<OrderTypeBean> typeDetailList;
    //适配器
    OrderTypeDetailAdapter detailAdapter;
    //原片数据
    List<BuyYaunListBean> buyYuanList;
    //辅材数据
    List<BuyFuListBean> buyFuList;
    String type;
    //买原片适配器
    BuyYuanAdapter buyYuanAdapter;
    //买辅材适配器
    BuyFuAdapter buyFuAdapter;
    //买原料适配器
    RawMaterialAdapter materialAdapter;
    List<RawMaterialBean.ReListBean> listRawMaterial = new ArrayList<>();

    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.x_refresh)
    SmartRefreshLayout xRefresh;
    //没有数据的展示界面
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;
    //判断是否是买原片
    boolean isYuan = false;
    //是否购是买原料
    boolean isRawMaterial = false;
    //是否是买辅料
    boolean isFu = false;
    //搜索内容
    @Bind(R.id.et_search_content)
    EditText etSearchContent;
    @Bind(R.id.et_small)
    EditText etSmall;
    @Bind(R.id.et_large)
    EditText etLarge;
    @Bind(R.id.tv_line_to)
    TextView tvLineTo;
    //购物车图表
    @Bind(R.id.iv_right)
    ImageView imgCar;
    //购物车数量
    @Bind(R.id.tv_car_num)
    TextView tvCarNum;
    //搜索
    @Bind(R.id.ll_search)
    LinearLayout llSearch;
    @Bind(R.id.iv_voice)
    ImageView imgVoice;
    @Bind(R.id.smart)
    SmartRefreshLayout smartRefreshLayout;
    private String carType = "";

    //动画
    private FrameLayout viewGroup;
    private int animationDuration = 1000;
    private String keyWord = "";

    @Override
    public int initLayout() {
        return R.layout.activity_buy_yuan;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().shopCarList);
        type = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        keyWord = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        //初始化动画视图层
        ViewGroup rootView = (ViewGroup) getWindow().getDecorView();
        viewGroup = AnimationUtils.createAnimLayout(rootView, getContext());
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED); //关闭手势
        switch (type) {
            case "yuan":
                isYuan = true;
                isRawMaterial = false;
                carType = "0";
                isFu = false;
                //设置标题
                tvTitle.setText(R.string.buy_yuan);
                break;
            case "fu":
                isYuan = false;
                isRawMaterial = false;
                carType = "1";
                isFu = true;
                //设置标题
//            imgVoice.setVisibility(View.GONE);
                tvTitle.setText(R.string.buy_fu);
                break;
            case "yuanLiao":
                isYuan = false;
                carType = "2";
                isFu = false;
                isRawMaterial = true;
                //设置标题
//            imgVoice.setVisibility(View.GONE);
                tvTitle.setText("买原料");
                break;
        }

        typeList = new ArrayList<>();
        typeDetailList = new ArrayList<>();
        buyYuanList = new ArrayList<>();
        buyFuList = new ArrayList<>();

        //测试数据制造
        //买原片适配器
        typeAdapter = new StringTypeAdapter(this, typeList, R.layout.item_order_type);
        buyYuanAdapter = new BuyYuanAdapter(this, buyYuanList, R.layout.item_buy_yuan);
        detailAdapter = new OrderTypeDetailAdapter(this, typeDetailList, R.layout.item_order_detail);
        buyFuAdapter = new BuyFuAdapter(this, buyFuList, R.layout.item_buy_fu);
        materialAdapter = new RawMaterialAdapter(this, listRawMaterial, R.layout.item_buy_raw_material);

        lvRefresh.setDividerHeight(0);
        lvType.setAdapter(typeAdapter);
        lvType.setDividerHeight(0);

        try {
            if (isYuan) {
                //买原片数据绑定
                BuyFunction.getInstance().bindYuanData(tvCarNum, etSearchContent, llNoData, drawerLayout, this, typeList, typeAdapter, lvType, typeDetailList,
                        detailAdapter, lvDetail, lvRefresh, buyYuanAdapter, buyYuanList,
                        xRefresh, tvSelectDetail, tvAgain, tvConfirm, animationDuration, viewGroup,
                        imgCar, etSmall, etLarge, tvLineTo, llSearch, keyWord,smartRefreshLayout, new OnSearchCallBack() {
                            @Override
                            public void onConfirm() {
                                drawerLayout.closeDrawer(Gravity.RIGHT);
                            }
                        });
            } else if (isFu) {
                smartRefreshLayout.setEnableRefresh(false);
                smartRefreshLayout.setEnableLoadMore(false);
                //买辅材数据绑定
                BuyFuFunction.getInstance().bindFuData(tvCarNum, llNoData, drawerLayout, tvSelectDetail, this, typeList, typeAdapter, lvType,
                        typeDetailList, detailAdapter, lvDetail, lvRefresh, buyFuAdapter,
                        buyFuList, xRefresh, tvAgain, tvConfirm, animationDuration, viewGroup, imgCar, llSearch, keyWord, new OnSearchCallBack() {
                            @Override
                            public void onConfirm() {
                                drawerLayout.closeDrawer(Gravity.RIGHT);
                            }
                        });
            } else {
                imgVoice.setVisibility(View.GONE);
                lvRefresh.setAdapter(materialAdapter);
                smartRefreshLayout.setEnableRefresh(false);
                smartRefreshLayout.setEnableLoadMore(false);
                BuyRawMaterialFunction.getInstance().buyRawMaterial(this, materialAdapter, listRawMaterial, lvRefresh,
                        animationDuration, tvCarNum, viewGroup, imgCar, typeDetailList, detailAdapter, typeList, typeAdapter,
                        lvType, lvDetail, tvSelectDetail, tvAgain, tvConfirm, xRefresh, new OnSearchCallBack() {
                            @Override
                            public void onConfirm() {
                                drawerLayout.closeDrawer(Gravity.RIGHT);
                            }
                        });
            }
        } catch (Exception e) {

        }

        //分类
        tabBuy.addTab(tabBuy.newTab().setText(R.string.all_sort));
        tabBuy.addTab(tabBuy.newTab().setText(R.string.sale_sort));
        tabBuy.addTab(tabBuy.newTab().setText(R.string.price_sort));

        tabBuy.setTabMode(TabLayout.MODE_FIXED);
        //排序方式点击
        tabBuy.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (isYuan) {
                    BuyFunction.getInstance().setSort(tab.getPosition(), buyYuanAdapter, buyYuanList, llNoData);
                }
                if (isFu) {
                    BuyFuFunction.getInstance().setSort(tab.getPosition(), buyFuList, buyFuAdapter);
                }
                if (isRawMaterial) {
                    BuyRawMaterialFunction.getInstance().setSort(tab.getPosition() + "");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * 点击事件
     */
    @OnClick({R.id.iv_right, R.id.ll_back, R.id.iv_voice, R.id.tv_select, R.id.iv_caulate, R.id.ll_click, R.id.tv_select_detail})
    public void OnClick(View view) {
        switch (view.getId()) {
            //购物车点击
            case R.id.iv_right:
                if (!LoginUtils.getInstance().isLogin(getContext())) {
                    IntentUtils.getInstance().toActivity(getContext(), LoginActivity.class);
                    return;
                }
                if (!drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    IntentUtils.getInstance().toActivity(this, ShopCarActivity.class, carType);
                }
                break;
            //退出
            case R.id.ll_back:
                this.finish();
                break;
            //筛选
            case R.id.tv_select:
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.iv_caulate:
                IntentUtils.getInstance().toActivity(getContext(), CalculatorActivity.class);
                break;
            case R.id.ll_click:
            case R.id.tv_select_detail:
                break;
            case R.id.iv_voice:
//                IntentUtils.getInstance().toActivity(this,VoiceOrderActivity.class,type);
                Intent intent = new Intent(this, VoiceOrderActivity.class);
                intent.putExtra(IntentUtils.getInstance().TYPE, type);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case 1:
                String brand = data.getStringExtra(IntentUtils.getInstance().TYPE);
                String className = data.getStringExtra(IntentUtils.getInstance().VALUE);
                String standard = data.getStringExtra(IntentUtils.getInstance().STR);
                String thick = data.getStringExtra(IntentUtils.getInstance().TXT);
                String level = data.getStringExtra(IntentUtils.getInstance().LEVEL);
                if (isYuan) {
                    BuyFunction.getInstance().voiceData(brand, className, standard, thick, level);
                } else {
                    BuyFuFunction.getInstance().voiceData(brand, className, standard);
                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
            } else {
                BuyYuanActivity.this.finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onRestart() {
        super.onRestart();

        String str = (String) SharedPreferencesUtils.getInstance().get(getContext(), SharedKeyConstant.getInstance().INIT_BUY, "");
        if (!StringUtils.isNoEmpty(str)) {
            return;
        }
        SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().INIT_BUY, "");
        try {
            if (isYuan) {
                buyYuanList.clear();
                //买原片数据绑定
                BuyFunction.getInstance().bindYuanData(tvCarNum, etSearchContent, llNoData, drawerLayout, this, typeList, typeAdapter, lvType, typeDetailList,
                        detailAdapter, lvDetail, lvRefresh, buyYuanAdapter, buyYuanList,
                        xRefresh, tvSelectDetail, tvAgain, tvConfirm, animationDuration, viewGroup,
                        imgCar, etSmall, etLarge, tvLineTo, llSearch, keyWord,smartRefreshLayout, new OnSearchCallBack() {
                            @Override
                            public void onConfirm() {
                                drawerLayout.closeDrawer(Gravity.RIGHT);
                            }
                        });
            } else if (isFu) {
                buyFuList.clear();
                //买辅材数据绑定
                BuyFuFunction.getInstance().bindFuData(tvCarNum, llNoData, drawerLayout, tvSelectDetail, this, typeList, typeAdapter, lvType,
                        typeDetailList, detailAdapter, lvDetail, lvRefresh, buyFuAdapter,
                        buyFuList, xRefresh, tvAgain, tvConfirm, animationDuration, viewGroup, imgCar, llSearch, keyWord, new OnSearchCallBack() {
                            @Override
                            public void onConfirm() {
                                drawerLayout.closeDrawer(Gravity.RIGHT);
                            }
                        });
            } else {
                imgVoice.setVisibility(View.GONE);
                lvRefresh.setAdapter(materialAdapter);
                BuyRawMaterialFunction.getInstance().buyRawMaterial(this, materialAdapter, listRawMaterial, lvRefresh,
                        animationDuration, tvCarNum, viewGroup, imgCar, typeDetailList, detailAdapter, typeList, typeAdapter,
                        lvType, lvDetail, tvSelectDetail, tvAgain, tvConfirm, xRefresh, new OnSearchCallBack() {
                            @Override
                            public void onConfirm() {
                                drawerLayout.closeDrawer(Gravity.RIGHT);
                            }
                        });
            }
        } catch (Exception e) {

        }
    }
}
