package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.MoreZbFunction;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ZbMessageBean;
import com.example.huoshangkou.jubowan.bean.ZbMessageListBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PhotoConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：NewZbDetailActivity
 * 描述：
 * 创建时间：2019-04-18  10:28
 */

public class NewZbDetailActivity extends BaseActivity {
    @Bind(R.id.ll_gf_zb)
    LinearLayout llGfZb;
    @Bind(R.id.ll_system_door_zb)
    LinearLayout llSystemDoorZb;
    @Bind(R.id.ll_system_xc)
    LinearLayout llSystemXc;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ll_door_window)
    LinearLayout llDoorWindow;
    @Bind(R.id.ll_yuan_fu)
    LinearLayout llYuanFu;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tv_pro_name)
    TextView tvProName;
    @Bind(R.id.tv_pro_spec)
    TextView tvProSpec;
    @Bind(R.id.tv_pro_address)
    TextView tvProAddress;
    @Bind(R.id.tv_pro_days)
    TextView tvProDays;
    @Bind(R.id.tv_cx_yq)
    TextView tvCxYq;
    @Bind(R.id.tv_price_yq)
    TextView tvPriceYq;
    @Bind(R.id.tv_link_man)
    TextView tvLinkMan;
    @Bind(R.id.tv_link_phone)
    TextView tvLinkPhone;
    @Bind(R.id.tv_link_address)
    TextView tvLinkAddress;
    @Bind(R.id.tv_dczjlx)
    TextView tvDczjlx;
    @Bind(R.id.tv_fd_zhl)
    TextView tvFdZhl;
    @Bind(R.id.tv_js_intro)
    TextView tvJsIntro;
    @Bind(R.id.tv_color)
    TextView tvColor;
    @Bind(R.id.tv_tou_guan)
    TextView tvTouGuan;
    @Bind(R.id.tv_k_value)
    TextView tvKValue;
    @Bind(R.id.tv_zy_xs)
    TextView tvZyXs;
    @Bind(R.id.tv_fan_indoor)
    TextView tvFanIndoor;
    @Bind(R.id.tv_xczl)
    TextView tvXczl;
    @Bind(R.id.tv_mf_system)
    TextView tvMfSystem;
    @Bind(R.id.tv_yuan_fu)
    TextView tvYuanFu;
    @Bind(R.id.tv_jg_gy)
    TextView tvJiaGy;
    @Bind(R.id.tv_brand)
    TextView tvBrand;
    @Bind(R.id.tv_five_gold)
    TextView tvFiveGold;
    @Bind(R.id.tv_gy_yq)
    TextView tvGyYq;
    @Bind(R.id.tv_glass_thick)
    TextView tvGlassThick;
    @Bind(R.id.tv_other_requir)
    TextView tvOtherRequir;
    @Bind(R.id.tv_finish_time)
    TextView tvFinishTime;
    @Bind(R.id.tv_door_color)
    TextView tvDoorColor;
    @Bind(R.id.tv_next)
    TextView tvNext;
    @Bind(R.id.ll_zb_price)
    LinearLayout llZbPrice;
    @Bind(R.id.tv_zb_price)
    TextView tvPrice;
    @Bind(R.id.grid_view_apply)
    ScrollGridView scrollGridView;
    @Bind(R.id.tv_fan_outdoor)
    TextView tvOutDoor;
    @Bind(R.id.ll_bao_req)
    LinearLayout llBaoReq;
    @Bind(R.id.ll_jg_gy)
    LinearLayout llJgGy;

    private String type;
    //幕墙招标
    private boolean isMqZb = false;
    //系统门窗
    private boolean isSystemDoor = false;
    //光伏玻璃
    private boolean isGuanFuWindow = false;
    //门窗定制招标
    private boolean isDoorWindow = false;
    //id
    private String id = "";
    //招标类型
    private String typeZb = "";
    //是否已审核通过
    private boolean isCheck = false;

    @Override
    public int initLayout() {
        return R.layout.activity_new_zb_detail;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().newZbList);
        type = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        id = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        String checkType = getIntent().getStringExtra(IntentUtils.getInstance().STR);
        if (StringUtils.isNoEmpty(checkType)) {
            isCheck = true;
        }
        switch (type) {
            case "gf_zb":
                llGfZb.setVisibility(View.VISIBLE);
                tvTitle.setText("光伏玻璃招标详情");
                typeZb = "光伏玻璃招标";
                isGuanFuWindow = true;
                break;
            case "system_zb":
                llSystemDoorZb.setVisibility(View.VISIBLE);
                llSystemXc.setVisibility(View.VISIBLE);
                tvTitle.setText("系统门窗招标详情");
                typeZb = "系统门窗招标";
                isSystemDoor = true;
                break;
            case "mu_qiang_zb":
                tvTitle.setText("幕墙招标详情");
                typeZb = "幕墙招标";
                llSystemDoorZb.setVisibility(View.VISIBLE);
                isMqZb = true;
                break;
            case "door_window":
                tvTitle.setText("门窗定制详情");
                typeZb = "门窗定制";
                llDoorWindow.setVisibility(View.VISIBLE);
                llYuanFu.setVisibility(View.GONE);
                isDoorWindow = true;
                llBaoReq.setVisibility(View.GONE);
                llJgGy.setVisibility(View.GONE);
                break;
        }
        toZbDetail(id);
    }

    //跳转到招标详情
    public void toZbDetail(String id) {
        MoreZbFunction.getInstance().getZbDetail(this, id, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                ZbMessageBean zbMessageBean = JSON.parseObject(str, ZbMessageBean.class);
                if (zbMessageBean.getSuccess() == -1) {
                    return;
                }
                if (zbMessageBean.getReList().size() != 0 && zbMessageBean.getReList().get(0) != null) {
//                    IntentUtils.getInstance().toActivity(context, ZbDetailActivity.class, zbMessageBean.getReList().get(0));
                    ZbMessageListBean listBean = zbMessageBean.getReList().get(0);
                    tvProName.setText(listBean.getProjectTitle());
                    tvProSpec.setText(listBean.getArea() + "-" + listBean.getArea1() + "㎡");
                    tvProAddress.setText(listBean.getProjectAddress());
                    tvProDays.setText(listBean.getNeedDay() + "天");
                    tvCxYq.setText(listBean.getProfilesRequest());
                    String bjYq = "需报价";
                    if (listBean.getIsNeedYp() == 1) {
                        bjYq += ",需送样品";
                    }
                    if (listBean.getIsNeedAnli() == 1) {
                        bjYq += ",需提供案例";
                    }
                    tvPriceYq.setText(bjYq);
                    tvLinkMan.setText(listBean.getLinkMan());
                    tvLinkPhone.setText(listBean.getLinkTel());
                    tvLinkAddress.setText(listBean.getYpAddress());
                    tvColor.setText(listBean.getColorVal());
                    tvTouGuan.setText(listBean.getTouGuangVal() + "");
                    tvKValue.setText(listBean.getKval() + "");
                    tvZyXs.setText(listBean.getZheyanVal() + "");
                    tvFanIndoor.setText(listBean.getFanseInVal() + "");
                    String[] splitFl = listBean.getFuliaos().split(";");
                    String yuanFu = "";
                    for (int i = 0; i < splitFl.length; i++) {
                        if (splitFl[i].length() > 3) {
                            yuanFu += splitFl[i] + "+";
                        }
                    }
                    if (StringUtils.isNoEmpty(yuanFu) && yuanFu.length() > 1) {
                        tvYuanFu.setText(yuanFu.substring(0, yuanFu.length() - 1));
                    }
                    String[] split = listBean.getGongyis().split(";");
                    String gonYi = "";
                    for (int i = 0; i < split.length; i++) {
                        if (split[i].length() > 3) {
                            gonYi += split[i];
                        }
                    }
                    tvJiaGy.setText(gonYi);
                    tvDczjlx.setText(listBean.getBatteryType());
                    tvFdZhl.setText(listBean.getElectricRate() + "");
                    tvJsIntro.setText(listBean.getIndexDescript());
                    tvBrand.setText(listBean.getBrands());
                    tvDoorColor.setText(listBean.getColorVal());
                    tvFiveGold.setText(listBean.getFLHardware());
                    tvGyYq.setText(listBean.getTechnologicalRequirements());
                    tvGlassThick.setText(listBean.getThickness());
                    tvOtherRequir.setText(listBean.getOtherRequirements());
                    tvFinishTime.setText(DateUtils.getFormData(listBean.getEndTime()));
                    tvXczl.setText(listBean.getProfilesType());
                    tvMfSystem.setText(listBean.getSealingSubsystem());
                    tvOutDoor.setText(StringUtils.getNoEmptyStr(listBean.getFanseOutVal()));

                    if (!StringUtils.isNoEmpty(listBean.getToBrandPrice())) {
                        if (isCheck && PersonConstant.getInstance().getRoleType(getContext()).equals("2")) {
                            tvNext.setVisibility(View.VISIBLE);
                        }
                        llZbPrice.setVisibility(View.GONE);
                    } else {
                        tvNext.setVisibility(View.GONE);
                        llZbPrice.setVisibility(View.VISIBLE);
                    }
                    List<String> imgList = PhotoUtils.getInstance().getListImage(listBean.getRequestPics());
                    ImageAddAdapter imageAddAdapter = new ImageAddAdapter(NewZbDetailActivity.this, imgList, PhotoConstant.getInstance().IS_NO_DELETE);
                    scrollGridView.setAdapter(imageAddAdapter);
                    tvPrice.setText(listBean.getToBrandPrice());
                } else {
                    ToastUtils.getMineToast("获取信息失败");
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    @OnClick({R.id.ll_back, R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_next:
                IntentUtils.getInstance().toActivity(this, ZbPriceActivity.class, id, typeZb);
                break;
        }
    }
}
