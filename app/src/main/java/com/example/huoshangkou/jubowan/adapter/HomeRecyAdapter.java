package com.example.huoshangkou.jubowan.adapter;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.MoreZbActivity;
import com.example.huoshangkou.jubowan.activity.YwChooseActivity;
import com.example.huoshangkou.jubowan.activity.ZbNewActivity;
import com.example.huoshangkou.jubowan.activity.BuyPjActivity;
import com.example.huoshangkou.jubowan.activity.BuyYuanActivity;
import com.example.huoshangkou.jubowan.activity.CountryCheckActivity;
import com.example.huoshangkou.jubowan.activity.FuBigActivity;
import com.example.huoshangkou.jubowan.activity.HotTieActivity;
import com.example.huoshangkou.jubowan.activity.JuBoNewActivity;
import com.example.huoshangkou.jubowan.activity.JuSchoolActivity;
import com.example.huoshangkou.jubowan.activity.JuServiceActivity;
import com.example.huoshangkou.jubowan.activity.ProjectGlassActivity;
import com.example.huoshangkou.jubowan.activity.ShopCarActivity;
import com.example.huoshangkou.jubowan.activity.YuanBigActivity;
import com.example.huoshangkou.jubowan.bean.HomeTypeBean;
import com.example.huoshangkou.jubowan.bean.NewHomeBean;
import com.example.huoshangkou.jubowan.fragment.function.HomeFunction;
import com.example.huoshangkou.jubowan.inter.OnCommonSuccessCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.utils.WxUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.path;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：HomeRecyAdapter
 * 描述：
 * 创建时间：2017-02-22  08:42
 */

public class HomeRecyAdapter extends RecyclerView.Adapter<HomeRecyAdapter.MyViewHolder> {
    //加载不同的数据类型
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private Context context;
    //添加头部View
    private View mHeaderView;
    private OnCommonSuccessCallBack successCallBack;
    private NewHomeBean homeBean;
    List<HomeTypeBean> homeList;

    public HomeRecyAdapter(NewHomeBean homeBean, Context context) {
        this.homeBean = homeBean;
        this.context = context;
        homeList = new ArrayList<>();
        HomeTypeBean homeTypeForumBean = new HomeTypeBean();
        homeTypeForumBean.setType("zb");
        HomeTypeBean homeTypeForumBeanZb = new HomeTypeBean();
        homeTypeForumBeanZb.setType("zb");
        if (homeBean.getD().getResult() != null) {
            homeTypeForumBeanZb.setList(homeBean.getD().getResult().getProjectBidding());
        }
        homeList.add(homeTypeForumBean);
        homeList.add(homeTypeForumBeanZb);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载头部布局
        if (mHeaderView != null && viewType == TYPE_HEADER)
            return new MyViewHolder(mHeaderView);
        //加载普通布局
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_out, parent, false);
        return new MyViewHolder(layout);
    }

    //添加头部view
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
//        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //加载头部View
        if (getItemViewType(position) == TYPE_HEADER) return;
        //绑定数据
        holder.setData(homeList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        //加载头部View
        if (mHeaderView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return homeList == null ? 0 : homeList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        //ImageView imgType;
        RelativeLayout rlMore;
        ScrollGridView listView;
        TextView tvTop;
        TextView tvZb;
        TextView tvZbDes;
        RelativeLayout rlNews;
        ScrollGridView gridMenu;
        ConvenientBanner cbBanner;
        //更多辅料
        RelativeLayout rlFuBig;
        //原片大牌
        ImageView imgBigYuan;
        //辅料大牌
        ImageView imgBigFu;
        //工程玻璃
        ImageView imgProjectGlass;
        //大牌专卖
        TextView tvDpzm;
        //大牌介绍
        TextView tvDpDes;
        TextView tvYuanName;
        TextView tvFuName;
        TextView tvProjectName;
        ScrollGridView gridServe;

        public MyViewHolder(View itemView) {
            super(itemView);
            //加载头部View
            if (itemView == mHeaderView) {
                gridServe = mHeaderView.findViewById(R.id.grid_view_reserve);
                rlNews = mHeaderView.findViewById(R.id.rl_news);
                tvTop = mHeaderView.findViewById(R.id.tv_news_top);
                gridMenu = mHeaderView.findViewById(R.id.grid_menu);
                cbBanner = mHeaderView.findViewById(R.id.cb_banner);
                rlFuBig = mHeaderView.findViewById(R.id.rl_big_fu);
                imgBigYuan = mHeaderView.findViewById(R.id.img_yp_glass);
                imgBigFu = mHeaderView.findViewById(R.id.img_fl);
                imgProjectGlass = mHeaderView.findViewById(R.id.img_project_glass);
                tvDpzm = mHeaderView.findViewById(R.id.tv_dpzm);
                tvDpDes = mHeaderView.findViewById(R.id.tv_dp_des);
                tvYuanName = mHeaderView.findViewById(R.id.tv_yuan_name);
                tvFuName = mHeaderView.findViewById(R.id.tv_fu_name);
                tvProjectName = mHeaderView.findViewById(R.id.tv_project_name);

                rlNews.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        IntentUtils.getInstance().toActivity(context, JuBoNewActivity.class);
                    }
                });
                //菜单
                if (homeBean.getD().getResult() == null) {
                    return;
                }
                final List<NewHomeBean.DBean.ResultBean.FriendDealerBean> friendDealer = homeBean.getD().getResult().getFriendDealer();
                ReserveSysAdapter sysAdapter = new ReserveSysAdapter(context, friendDealer, R.layout.item_reserve_sys);
                gridServe.setAdapter(sysAdapter);
                gridServe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        IntentUtils.getInstance().toWebActivity(context, friendDealer.get(i).getLink(), friendDealer.get(i).getName());
                    }
                });

                final List<NewHomeBean.DBean.ResultBean.NavigationBarBean> navigationBar = homeBean.getD().getResult().getNavigationBar();
                HomeMenuAdapter menuAdapter = new HomeMenuAdapter(context, navigationBar, R.layout.item_home_menu);
                gridMenu.setAdapter(menuAdapter);
                //加载Banner图
                HomeFunction.getInstance().addBannerPic(context, cbBanner, homeBean.getD().getResult().getBanners());
                //更多
//                rlFuBig.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        IntentUtils.getInstance().toActivity(context, FuBigActivity.class);
//                    }
//                });
                //原片大牌
                imgBigYuan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        IntentUtils.getInstance().toActivity(context, YuanBigActivity.class);
                    }
                });
                //辅料大牌
                imgBigFu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        IntentUtils.getInstance().toActivity(context, FuBigActivity.class);
                    }
                });
                //工程玻璃
                imgProjectGlass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        IntentUtils.getInstance().toActivity(context, ProjectGlassActivity.class);
                    }
                });
                List<NewHomeBean.DBean.ResultBean.BigNameMonopolyBean.ChildBean> child = homeBean.getD().getResult().getBigNameMonopoly().get(0).getChild();
                GlideUtils.getInstance().displayFitImage(child.get(0).getImage(), context, imgBigYuan);
                GlideUtils.getInstance().displayFitImage(child.get(1).getImage(), context, imgBigFu);
                GlideUtils.getInstance().displayFitImage(child.get(2).getImage(), context, imgProjectGlass);
                tvYuanName.setText(child.get(0).getName());
                tvFuName.setText(child.get(1).getName());
                tvProjectName.setText(child.get(2).getName());
                tvDpzm.setText(homeBean.getD().getResult().getBigNameMonopoly().get(0).getName());
                tvDpDes.setText(homeBean.getD().getResult().getBigNameMonopoly().get(0).getDescription());
                tvTop.setText(homeBean.getD().getResult().getJuboInformation().get(0).getDescription());
                gridMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (navigationBar.get(i).getName()) {
                            case "买原料":
                                IntentUtils.getInstance().toActivity(context, BuyYuanActivity.class, "yuanLiao");
                                break;
                            case "买原片":
                                IntentUtils.getInstance().toActivity(context, BuyYuanActivity.class, "yuan");
                                break;
                            case "买辅材":
                                IntentUtils.getInstance().toActivity(context, BuyYuanActivity.class, "fu");
                                break;
                            case "维修维保":
                                IntentUtils.getInstance().toActivity(context, JuServiceActivity.class);
                                break;
                            case "买原材":
                                IntentUtils.getInstance().toActivity(context, ShopCarActivity.class);
                                break;
                            case "直通国检":
                                IntentUtils.getInstance().toActivity(context, CountryCheckActivity.class);
                                break;
                            case "聚玻学院":
                                IntentUtils.getInstance().toActivity(context, JuSchoolActivity.class);
                                break;
                            case "聚马物流":
                                String pointName = "com.example.tang.tangdemo";
                                String pointActivity = "com.example.tang.tangdemo.activity.StartPageActivity";
                                String downUrl = "https://appstore.huawei.com/app/C10892156";
                                String appName = "聚马物流";
                                WxUtils.getInstance().jumpApp(context, pointName, pointActivity, downUrl, appName);
                                break;
                            case "聚玻白条":
                                IntentUtils.getInstance().toActivity(context, YwChooseActivity.class);
                                break;
                            case "聚玻定制":
                            case "聚玻门窗":
                                String proId = "gh_96366a005c77"; //小程序原始id
                                WxUtils.getInstance().toWxSmallPro(context, proId);
                                break;
                            case "聚易联":
                                String pointNameJyl = "atjubo.saas.huoshankou.com.saasjuboapp";
                                String pointActivityJyl = "atjubo.saas.huoshankou.com.saasjuboapp.activity.StartPageActivity";
                                String downUrlJyl = "http://saas.atjubo.com:8087/H5Page/AndroidAppDownload.html";
                                String appNameJyl = "聚易联";
                                WxUtils.getInstance().jumpApp(context, pointNameJyl, pointActivityJyl, downUrlJyl, appNameJyl);
                                break;
                            case "购物车":
                                IntentUtils.getInstance().toActivity(context, ShopCarActivity.class);
                                break;
                        }
                    }
                });
                return;
            }

            //数据
            listView = (ScrollGridView) itemView.findViewById(R.id.lv_type_data);
            rlMore = (RelativeLayout) itemView.findViewById(R.id.rl_more);
            tvZb = (TextView) itemView.findViewById(R.id.tv_xmzb);
            tvZbDes = (TextView) itemView.findViewById(R.id.tv_zb_des);
            if (homeBean.getD().getResult() == null) {
                return;
            }
            tvZb.setText(homeBean.getD().getResult().getProjectBidding().get(0).getName());
            tvZbDes.setText(homeBean.getD().getResult().getProjectBidding().get(0).getDescription());

        }

        public void setData(final HomeTypeBean homeTitleBean) {
            final List<NewHomeBean.DBean.ResultBean.ProjectBiddingBean.ChildBeanX> list = new ArrayList<>();
            if (homeTitleBean.getList() != null) {
                list.addAll(homeTitleBean.getList().get(0).getChild());
            }
            HomeDataAdapter homeDataAdapter = new HomeDataAdapter(context, list, R.layout.item_home_zb);
            listView.setAdapter(homeDataAdapter);
            rlMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //招标
                    if (homeTitleBean.getType().equals("zb")) {
                        IntentUtils.getInstance().toActivity(context, ZbNewActivity.class);
//                        IntentUtils.getInstance().toActivity(context, MoreZbActivity.class);
                        //论坛
                    } else if (homeTitleBean.getType().equals("lt")) {
                        IntentUtils.getInstance().toActivity(context, HotTieActivity.class);
                    }
                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    IntentUtils.getInstance().toActivity(context, ZbNewActivity.class, list.get(position).getName());
                }
            });
        }
    }

    public void setSuccessCallBack(OnCommonSuccessCallBack successCallBack) {
        this.successCallBack = successCallBack;
    }


}
