package com.example.huoshangkou.jubowan.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.ShopCarActivity;
import com.example.huoshangkou.jubowan.activity.function.LoginFunction;
import com.example.huoshangkou.jubowan.adapter.HomeRecyAdapter;
import com.example.huoshangkou.jubowan.adapter.TradeAdapter;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.bean.AdvBean;
import com.example.huoshangkou.jubowan.bean.BuyOrderListBean;
import com.example.huoshangkou.jubowan.bean.BuyToYuanBean;
import com.example.huoshangkou.jubowan.bean.HomeTitleBean;
import com.example.huoshangkou.jubowan.bean.JylBuyResultBean;
import com.example.huoshangkou.jubowan.bean.NewHomeBean;
import com.example.huoshangkou.jubowan.bean.ToJBBuyBean;
import com.example.huoshangkou.jubowan.bean.ToJBFUBean;
import com.example.huoshangkou.jubowan.bean.TradeBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.SharedValueConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.fragment.function.HomeFunction;
import com.example.huoshangkou.jubowan.inter.OnLoginSuccessBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.SmartUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.utils.UpdateDialogUtils;
import com.example.huoshangkou.jubowan.utils.VersionUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment
 * 类名：HomeFragment
 * 描述：首页界面
 * 创建时间：2016-12-29  13:56
 */

public class HomeFragment extends BaseFragment {
    @Bind(R.id.tv_home_title)
    TextView tvHomeTitle;
    @Bind(R.id.rl_home_top)
    RelativeLayout rlHomeTop;
    //主体数据 
    @Bind(R.id.list_view)
    RecyclerView listView;
    @Bind(R.id.smart)
    SmartRefreshLayout smartRefreshLayout;
    //交易额数据
    List<TradeBean> tradeList;
    //交易额适配器
    TradeAdapter tradeAdapter;
    //主体数据
    List<HomeTitleBean> homeTitleBeanList;
    //RecylerView适配器
    HomeRecyAdapter recyAdapter;
    private String versionData = "";
    private String json;
    private String account;
    private String psw;
    private String purchaseType = "";

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData() {
        tradeList = new ArrayList<>();
        homeTitleBeanList = new ArrayList<>();
        smartRefreshLayout.setEnableLoadMore(false);
        rlHomeTop.setBackgroundColor(Color.parseColor("#FF058FF9"));
        rlHomeTop.getBackground().setAlpha(0);
        Bundle arguments = getArguments();
        json = arguments.getString("args");
        account = arguments.getString("UserAccount");
        psw = arguments.getString("UserPassword");
        purchaseType = arguments.getString("PurchaseType");
//        account = "15172486580";
//        psw = "4QrcOUm6Wau+VuBX8g+IPg==";
        getHomeData("");
        //关于我们
        SharedPreferencesUtils.getInstance().put(getActivity(), SharedKeyConstant.getInstance().RULE_URL, "http://www.atjubo.com/serviceagree/webapp/views/JBW.aspx");
        //抢红包界面
//        DialogUtils.getInstance().
//        RedPacketDialogUtils.updataDialogShow(getActivity());
//        if(!NetUtils.isConnected(getActivity())){
//            String json = (String) SharedPreferencesUtils.getInstance().get(getActivity(), SharedKeyConstant.getInstance().HOME_DATA, "");
//            if (StringUtils.isNoEmpty(json)) {
//                initUnitData(json);
//            }
//        }
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getHomeData("refresh");
            }
        });

    }

    //获取主页数据
    public void getHomeData(final String refresh) {
        String json = (String) SharedPreferencesUtils.getInstance().get(getActivity(), SharedKeyConstant.getInstance().HOME_DATA, "");
        if (StringUtils.isNoEmpty(json) && !StringUtils.isNoEmpty(refresh)) {
            initUnitData(json);
        }
        Map<String, String> map = new HashMap<>();
//        map.put("VERSION", VersionUtils.getInstance().getVersionNo(getActivity()) + "");
        versionData = (String) SharedPreferencesUtils.getInstance().get(getActivity(), SharedKeyConstant.getInstance().DATA_VERSION, "");
        map.put("VERSION", VersionUtils.getInstance().getLocalVersionNo() + "");
//        map.put("Content-Type", "application/json");
        map.put("DEVICEID", PersonConstant.getInstance().getPhoneTypeId());

        OkhttpUtil.getInstance().basePostCall(getActivity(), JSON.toJSONString(map), UrlConstant.getInstance().HOME_URL + "/HomePageService.asmx/HomeConfig", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
//                String str = response.body().string();
                getHolidayDialog();
                if (StringUtils.isNoEmpty(str)) {
                    NewHomeBean homeBean = null;
                    try {
                        homeBean = JSON.parseObject(str, NewHomeBean.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (homeBean != null && homeBean.getD() != null && homeBean.getD().getResult() != null) {
                        if (StringUtils.isNoEmpty(refresh) || (!StringUtils.isNoEmpty(versionData) || (homeBean.getD().getResult().getVersion() != null && !homeBean.getD().getResult().getVersion().equals(versionData)))) {
                            SharedPreferencesUtils.getInstance().put(getActivity(), SharedKeyConstant.getInstance().HOME_DATA, str);
//                            SharedPreferencesUtils.getInstance().put(getActivity(), SharedKeyConstant.getInstance().USER_STATE, homeBean.getD().getResult().getNavigationBar());
                            SharedPreferencesUtils.getInstance().put(getActivity(), SharedKeyConstant.getInstance().DATA_VERSION, homeBean.getD().getResult().getVersion());
                            initUnitData(str);
                            LogUtils.e("步骤4");
                        }
                        LogUtils.e("步骤3");
                    }
                }
                if (smartRefreshLayout == null) {
                    return;
                }
                SmartUtils.finishSmart(smartRefreshLayout);
            }

            @Override
            public void onFail() {
                getHolidayDialog();
                if (smartRefreshLayout == null) {
                    return;
                }
                SmartUtils.finishSmart(smartRefreshLayout);
            }
        });
    }

    //解密测试
//    public void jiemi() {
//        OkhttpUtil.getInstance().setUnCacheDataNoDialog(getActivity(), UrlConstant.getInstance().WEB_URL + "Value/ResponseDecrypt?data=FNL2dudEv3vZ0oJYSA8pjUdmQ4mmo5xoQ/JXfQZEuUq8ZwSLuoEC2sf+Y0CMVfez0DTlq7/SsrK2asV71/WiuQf7mnEtq6BatJ3XuUW+ATYz+MNn6RB9OeXVtWYcBE55wLHltQoTU6GweNAlHnsNauO0GXvehxnEp3jyaoM0kF/exbSGAtQ84nGdGEKYb/duUFNMcAdTXb+T+cv6sSXoHlzHqKlRSrdXbiStquX7TSjKtR0cP0f6D1NYuAd7SdjcxbPU/aus9wTPipW2oxNBiXH2kHMJj3b2Eyr8w5BbCwYxEzEZxD0kuq1cFovgmxXkFsb4h3hQGGndXNJUF2IdOA==&timestamp=" + DateUtils.getTime(), new OkhttpCallBack() {
//            @Override
//            public void onSuccess(String json) {
//                LogUtils.e("解密数据：" + json);
//            }
//
//            @Override
//            public void onFail() {
//
//            }
//        });
//    }

    private RecyclerView.LayoutManager getLayoutManager() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        return manager;
    }

    public void initUnitData(String str) {
        if (!StringUtils.isNoEmpty(str)) {
            return;
        }
        NewHomeBean homeBean = JSON.parseObject(str, NewHomeBean.class);
        if (homeBean != null && homeBean.getD() != null) {
            recyAdapter = new HomeRecyAdapter(homeBean, getActivity());
            listView.setAdapter(recyAdapter);
            listView.setLayoutManager(getLayoutManager());
            //添加头部数据 
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_trade_num, null);
//                cbBanner = (ConvenientBanner) view.findViewById(R.id.cb_banner);
            recyAdapter.setHeaderView(view);
            //头部渐变
            //设置成蓝色
            HomeFunction.getInstance().changeTopColor(listView, rlHomeTop, tvHomeTitle, getActivity());
            //加载Banner图
//                HomeFunction.getInstance().addBannerPic(getActivity(), cbBanner, imgList, homeBean.getHomePageList());
            //交易数据
//                HomeFunction.getInstance().getTradeData(tradeList, homeBean);
        }
    }

    //聚易联跳转聚玻宝采购
    public void toJuBoBaoBuy(String json, String accountSaas, String pswSaas) {
        if (!StringUtils.isNoEmpty(json)) {
            return;
        }
        if (!StringUtils.isNoEmpty(account)) {
            return;
        }
        //辅料采购
        if (purchaseType.equals("辅材")) {
            ToastUtils.getMineToast("辅料采购");
            BuyOrderListBean listBean = JSON.parseObject(json, BuyOrderListBean.class);
            List<ToJBFUBean.AddModelBean.ListGoodsBean> listGoods = new ArrayList<>();
            List<BuyOrderListBean.ChildernBean> returnData = listBean.getChildern();
            int num = returnData.size();
            for (int i = 0; i < num; i++) {
                ToJBFUBean.AddModelBean.ListGoodsBean goodsBean = new ToJBFUBean.AddModelBean.ListGoodsBean();
                goodsBean.setVal(returnData.get(i).getSpec());
                goodsBean.setBrandName(returnData.get(i).getBrand());
                goodsBean.setClassName(returnData.get(i).getType());
                goodsBean.setNumber(Integer.parseInt(returnData.get(i).getNumber()));
                listGoods.add(goodsBean);
            }
            ToJBFUBean buyBean = new ToJBFUBean();
            ToJBFUBean.AddModelBean addModel = new ToJBFUBean.AddModelBean();
            addModel.setPassword(pswSaas);
            addModel.setUserName(accountSaas);
            addModel.setListGoods(listGoods);
            buyBean.setAddModel(addModel);
            String buyJson = JSON.toJSONString(buyBean);
            OkhttpUtil.getInstance().basePostCall(getActivity(), buyJson, UrlConstant.getInstance().WEB_URL + "ServiceInterface/AtjuboWeb/SassAddShoppingCar.asmx/FLAddShoppingCar", new StringCallBack() {
                @Override
                public void onSuccess(String str) {
                    LogUtils.e("聚易联采购json：" + account);
                    account = "";
                    JylBuyResultBean resultBean = JSON.parseObject(str, JylBuyResultBean.class);
                    if (resultBean.getD() != null && resultBean.getD().getResult() != null && resultBean.getD().getResult().getSatate().equals("error")) {
                        ToastUtils.getMineToast(resultBean.getD().getResult().getReturnMsg());
                        return;
                    }
                    EventBus.getDefault().post(account, "toJuYiLianBuy");
                    SharedPreferencesUtils.getInstance().put(getActivity(), SharedKeyConstant.getInstance().CURRENT_PHONE, account.trim());
                    SharedPreferencesUtils.getInstance().put(getActivity(), SharedKeyConstant.getInstance().LOGIN_STATE, SharedValueConstant.getInstance().LOGIN_SUCCESS);
                    IntentUtils.getInstance().toActivity(getActivity(), ShopCarActivity.class);

                }

                @Override
                public void onFail() {
                    LogUtils.e("聚易联采购：失败");
                }
            });
            return;
        }

        BuyToYuanBean listBean = JSON.parseObject(json, BuyToYuanBean.class);
        List<ToJBBuyBean.AddModelBean.ListGoodsBean> listGoods = new ArrayList<>();
        List<BuyToYuanBean.ReturnDataBean> returnData = listBean.getReturnData();
        int num = returnData.size();
        for (int i = 0; i < num; i++) {
            ToJBBuyBean.AddModelBean.ListGoodsBean goodsBean = new ToJBBuyBean.AddModelBean.ListGoodsBean();
            String[] split1 = returnData.get(i).getSpec().split("\\*");
            goodsBean.setClassName(returnData.get(i).getClassName());
            goodsBean.setBrandName(returnData.get(i).getBrandName());
            goodsBean.setHeight(split1[0]);
            goodsBean.setWidth(split1[1]);
            goodsBean.setProperty(returnData.get(i).getProperty());
            String weight = returnData.get(i).getWeight().substring(0, returnData.get(i).getWeight().length() - 2);
            goodsBean.setWeight(weight);
            goodsBean.setLevel(returnData.get(i).getLevelID());
            goodsBean.setNumber(returnData.get(i).getNumber());
            listGoods.add(goodsBean);
        }
        ToJBBuyBean buyBean = new ToJBBuyBean();
        ToJBBuyBean.AddModelBean addModel = new ToJBBuyBean.AddModelBean();
        addModel.setPassword(pswSaas);
        addModel.setUserName(accountSaas);
        addModel.setListGoods(listGoods);
        buyBean.setAddModel(addModel);
        String buyJson = JSON.toJSONString(buyBean);
        LogUtils.e(buyJson);
        OkhttpUtil.getInstance().basePostCall(getActivity(), buyJson, UrlConstant.getInstance().WEB_URL + "ServiceInterface/AtjuboWeb/SassAddShoppingCar.asmx/AddShoppingCar", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e("聚易联采购json：" + account);
                account = "";
                JylBuyResultBean resultBean = JSON.parseObject(str, JylBuyResultBean.class);
                if (resultBean.getD() != null && resultBean.getD().getResult() != null && resultBean.getD().getResult().getSatate().equals("error")) {
                    ToastUtils.getMineToast(resultBean.getD().getResult().getReturnMsg());
                    return;
                }
                EventBus.getDefault().post(account, "toJuYiLianBuy");
                SharedPreferencesUtils.getInstance().put(getActivity(), SharedKeyConstant.getInstance().CURRENT_PHONE, account.trim());
                SharedPreferencesUtils.getInstance().put(getActivity(), SharedKeyConstant.getInstance().LOGIN_STATE, SharedValueConstant.getInstance().LOGIN_SUCCESS);
                IntentUtils.getInstance().toActivity(getActivity(), ShopCarActivity.class);
            }

            @Override
            public void onFail() {
                LogUtils.e("聚易联采购：失败");
            }
        });
    }

    //广告弹窗接口 
    public void getHolidayDialog() {
        Map<String, String> map = new HashMap<>();
        map.put("UserID", PersonConstant.getInstance().getUserId());
        OkhttpUtil.getInstance().basePostCall(getActivity(), map, UrlConstant.getInstance().ADV_URL, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                isAccount();
                AdvBean advBean = JSON.parseObject(str, AdvBean.class);
                if (advBean.getD() == null) {
                    return;
                }
                if (advBean.getD().getSuccess() == -1) {
                    return;
                }
                UpdateDialogUtils.getHolidayDialog(getContext(), advBean.getD().getReObj());
//                UpdateDialogUtils.updataDialogShow(getContext(), VersionUtils.getInstance().getVersionIntro(getContext()));
            }

            @Override
            public void onFail() {
                isAccount();
            }
        });
    }

    //判断账号是否一致
    public void isAccount() {
        String phone = PersonConstant.getInstance().getPhone(getActivity());
        String hint = "";
        if (!StringUtils.isNoEmpty(account)) {
            return;
        }
        if (StringUtils.isNoEmpty(phone) && StringUtils.isNoEmpty(account) && phone.equals(account)) {
            toJuBoBaoBuy(json, account, psw);
            return;
        }
        if (!StringUtils.isNoEmpty(phone) && StringUtils.isNoEmpty(account)) {
            hint = "检查到您的聚玻宝未登录，是否登录聚易联账号并购买";
        }
        if (StringUtils.isNoEmpty(account) && StringUtils.isNoEmpty(phone) && !phone.equals(account)) {
            hint = "检查到您的聚易联账号与聚玻宝账号不一致，是否登录聚易联账号并购买";
        }
        CopyIosDialogUtils.getInstance().getErrorDialog(getActivity(), hint, new CopyIosDialogUtils.ErrDialogCallBack() {
            @Override
            public void confirm() {
                //登录操作
                LoginFunction.getInstance().setLogin(getContext(), account, psw, new OnLoginSuccessBack() {
                    //登录成功回调
                    @Override
                    public void onLoginSuccess() {
                        //保存当前登录手机号
                        SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().CURRENT_PHONE, account);
                        SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().LOGIN_STATE, SharedValueConstant.getInstance().LOGIN_SUCCESS);
                        toJuBoBaoBuy(json, account, psw);
                    }

                    //登录失败回调
                    @Override
                    public void onLoginFail() {
                        SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().LOGIN_STATE, SharedValueConstant.getInstance().LOGIN_FAIL);
                    }
                });

//                        //登录操作
//                        LoginFunction.getInstance().setLogin(getContext(), account, psw, new OnLoginSuccessBack() {
//                            //登录成功回调
//                            @Override
//                            public void onLoginSuccess() {
//                                //保存当前登录手机号
//                                SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().CURRENT_PHONE, account);
//                                SharedPreferencesUtils.getInstance().put(getActivity(), SharedKeyConstant.getInstance().LOGIN_STATE, SharedValueConstant.getInstance().LOGIN_SUCCESS);
//                                IntentUtils.getInstance().toActivity(getActivity(), ShopCarActivity.class);
//                                ToastUtils.getMineToast("登录成功");
////                                    EventBus.getDefault().post("toJuYiLianBuy","toJuYiLianBuy");
//                            }
//
//                            //登录失败回调
//                            @Override
//                            public void onLoginFail() {
//                                SharedPreferencesUtils.getInstance().put(getActivity(), SharedKeyConstant.getInstance().LOGIN_STATE, SharedValueConstant.getInstance().LOGIN_FAIL);
//                            }
//                        });
            }
        });
    }
}
