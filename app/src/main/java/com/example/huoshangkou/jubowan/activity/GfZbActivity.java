package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.ApproveFunction;
import com.example.huoshangkou.jubowan.activity.function.ImageAddFunction;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.GonYiListBean;
import com.example.huoshangkou.jubowan.bean.SerMap;
import com.example.huoshangkou.jubowan.bean.YuanFuBean;
import com.example.huoshangkou.jubowan.bean.ZbResultBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.ChooseDialogPositionCallBack;
import com.example.huoshangkou.jubowan.inter.OnApplyCommitCallBack;
import com.example.huoshangkou.jubowan.inter.OnTimeCallBack;
import com.example.huoshangkou.jubowan.inter.OnZbStandardCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.EditDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.KeyBoardUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.PickDialogUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.TimeDialogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：GfZbActivity
 * 描述：
 * 创建时间：2019-04-03  16:49
 */

public class GfZbActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ll_gf_zb)
    LinearLayout llGfZb;
    @Bind(R.id.ll_system_door_zb)
    LinearLayout llSystemDoorZb;
    @Bind(R.id.ll_system_xc)
    LinearLayout llSystemXc;
    @Bind(R.id.et_pro_name)
    EditText etProName;
    @Bind(R.id.tv_pro_spec)
    TextView tvProSpec;
    @Bind(R.id.et_pro_address)
    EditText etProAddress;
    @Bind(R.id.et_pro_days)
    EditText etProDays;
    @Bind(R.id.et_cx_yq)
    EditText etCxYq;
    @Bind(R.id.et_link_man)
    TextView etLinkMan;
    @Bind(R.id.et_link_phone)
    TextView etLinkPhone;
    @Bind(R.id.et_link_address)
    TextView etLinkAddress;
    @Bind(R.id.textView5)
    TextView textView5;
    @Bind(R.id.et_dczjlx)
    EditText etDczjlx;
    @Bind(R.id.et_fd_zhl)
    EditText etFdZhl;
    @Bind(R.id.et_js_intro)
    EditText etJsIntro;
    @Bind(R.id.et_color)
    EditText etColor;
    @Bind(R.id.et_tou_guan)
    EditText etTouGuan;
    @Bind(R.id.et_k_value)
    EditText etKValue;
    @Bind(R.id.et_zy_xs)
    EditText etZyXs;
    @Bind(R.id.et_fan_indoor)
    EditText etFanIndoor;
    @Bind(R.id.et_xczl)
    EditText etXczl;
    @Bind(R.id.et_mf_system)
    EditText etMfSystem;
    @Bind(R.id.ll_door_window)
    LinearLayout llDoorWindow;
    @Bind(R.id.ll_yuan_fu)
    LinearLayout llYuanFu;
    //需要样品
    @Bind(R.id.cb_need_pro)
    CheckBox cbNeedYp;
    //需要案例
    @Bind(R.id.cb_need_anli)
    CheckBox cbNeedAl;
    @Bind(R.id.et_brand)
    EditText etBrand;
    @Bind(R.id.et_door_color)
    EditText etDoorColor;
    @Bind(R.id.et_fl_five)
    EditText etFlFive;
    @Bind(R.id.et_gy_yq)
    EditText etGyYq;
    @Bind(R.id.et_glass_thick)
    EditText etGlassThick;
    @Bind(R.id.et_other_requair)
    EditText etOtherRequair;
    @Bind(R.id.tv_finish_time)
    TextView tvFinishTime;
    @Bind(R.id.tv_yuan_1)
    TextView tvYuan1;
    @Bind(R.id.tv_fu_1)
    TextView tvFu1;
    @Bind(R.id.tv_yuan_2)
    TextView tvYuan2;
    @Bind(R.id.tv_fu_2)
    TextView tvFu2;
    @Bind(R.id.tv_yuan_3)
    TextView tvYuan3;
    @Bind(R.id.tv_fu_3)
    TextView tvFu3;
    @Bind(R.id.tv_choose_yuan_1)
    TextView tvChooseYuan1;
    @Bind(R.id.tv_choose_yuan_2)
    TextView tvChooseYuan2;
    @Bind(R.id.tv_choose_yuan_3)
    TextView tvChooseYuan3;
    @Bind(R.id.tv_bao_req)
    TextView tvBaoReq;
    @Bind(R.id.tv_next)
    TextView tvNext;
    //图片显示GridView
    @Bind(R.id.grid_view_apply)
    ScrollGridView scrollGridView;
    @Bind(R.id.ll_bao_req)
    LinearLayout llBaoReq;
    @Bind(R.id.et_fan_outdoor)
    EditText etOutDoor;

    //加工工艺
    private List<GonYiListBean> gonYiList = new ArrayList<>();
    private ViewGroup.MarginLayoutParams lp;
    private String type = "";
    //项目名称
    private String projectName;
    //需求面积1
    private String area1;
    //需求面积2
    private String area2;
    //品牌
    private String brand;
    //膜系
    private String moxi;
    //辅材
    private String fuLiao;
    //工艺
    private String gonYi;
    //颜色
    private String color;
    //透光率
    private String touGuanLv;
    //内射率
    private String neiSheLv;
    //外反射
    private String waiFanShe;
    //型材要求
    private String profileRequair;
    //项目图片
    private String proPic;
    //型材种类
    private String profileTypes;
    //是否需提供案例
    private String isNeedAnLi;
    //是否需送样品
    private String isNeedYp;
    //K值
    private String kValue;
    //遮阳系数
    private String zheYangValue;
    //联系人
    private String linkMan;
    //联系电话
    private String linkPhone;
    //样品寄送地址
    private String sendAddress;
    //工期需求天数
    private String proDays;
    //项目所在地
    private String proAddress;
    //状态
    private String state;
    //开始时间
    private String startTime;
    //结束时间
    private String endTime;
    //需提供案例
    private String needAnLi;
    //是否需要送样品
    private String needYp;
    //电池组建种类
    private String dcType;
    //密封电子系统
    private String miFengSysytem;
    //发电转化率
    private String faDianZh;
    //关键指标描述
    private String gjZhiBiao;
    //工艺要求
    private String gyYq;
    //辅料五金
    private String flWuJin;
    //厚度
    private String thick;
    //长度
    private String length;
    //其他要求
    private String otherRequir;
    //完工时间
    private String finishTime;
    //地址选择
    private final int CHOOSE_ADDRESS = 2;
    //招标类型
    private String projectType;
    //幕墙招标
    private boolean isMqZb = false;
    //系统门窗
    private boolean isSystemDoor = false;
    //光伏玻璃
    private boolean isGuanFuWindow = false;
    //门窗定制招标
    private boolean isDoorWindow = false;
    //原片1、
    private final int YUNA_ONE = 3;
    //原片2
    private final int YUNA_TWO = 4;
    //原片3
    private final int YUNA_THREE = 5;
    private int imgNum = 9;
    //图片
    List<String> imgList = new ArrayList<>();
    //图片适配器
    ImageAddAdapter imageAddAdapter;

    private String yuan_type_1 = "";
    private String yuan_type_2 = "";
    private String yuan_type_3 = "";

    private String fu_type_1 = "";
    private String fu_type_2 = "";
    private String fu_type_3 = "";

    private String yuan_choose_1 = "";
    private String yuan_choose_2 = "";
    private String yuan_choose_3 = "";

    List<YuanFuBean.DBean.ResultBean> resultList = new ArrayList<>();

    private ArrayList<String> listBrand = new ArrayList<>();
    private ArrayList<ArrayList<String>> listChild = new ArrayList<>();

    private ArrayList<String> listFuBrand = new ArrayList<>();
    private ArrayList<ArrayList<String>> listFuChild = new ArrayList<>();

    @Override
    public int initLayout() {
        return R.layout.activity_gf_zb;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().newZbList);
        type = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        switch (type) {
            case "gf_zb":
                llGfZb.setVisibility(View.VISIBLE);
                tvTitle.setText("光伏幕墙");
                isGuanFuWindow = true;
                projectType = "光伏幕墙";
                break;
            case "system_zb":
                llSystemDoorZb.setVisibility(View.VISIBLE);
                llSystemXc.setVisibility(View.VISIBLE);
                tvTitle.setText("系统门窗");
                isSystemDoor = true;
                projectType = "系统门窗";
                break;
            case "mu_qiang_zb":
                tvTitle.setText("幕墙招标");
                llSystemDoorZb.setVisibility(View.VISIBLE);
                isMqZb = true;
                projectType = "幕墙招标";
                break;
            case "door_window":
                tvTitle.setText("门窗定制招标");
                llDoorWindow.setVisibility(View.VISIBLE);
                llYuanFu.setVisibility(View.GONE);
                isDoorWindow = true;
                llBaoReq.setVisibility(View.GONE);
                projectType = "门窗定制招标";
                tvNext.setText("提交");
                break;
        }
        //是否需要案例
        cbNeedAl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    isNeedAnLi = "1";
                } else {
                    isNeedAnLi = "0";
                }
            }
        });
        //是否需要样品
        cbNeedYp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    isNeedYp = "1";
                } else {
                    isNeedYp = "0";
                }
            }
        });

        getYuanFuData();

        //图片显示封装
        imageAddAdapter = new ImageAddAdapter(this, imgList, "");
        ImageAddFunction.addImageFun(imageAddAdapter, imgList, this, scrollGridView);

    }

    @OnClick({R.id.ll_back, R.id.tv_next,R.id.iv_apply_camera, R.id.ll_bao_req, R.id.tv_pro_spec, R.id.et_link_man, R.id.et_link_phone,
            R.id.et_link_address, R.id.tv_finish_time, R.id.rl_fc_lx_1, R.id.rl_fc_lx_2, R.id.rl_fc_lx_3,
            R.id.rl_yp_yq_1, R.id.rl_yp_yq_2, R.id.rl_yp_yq_3, R.id.tv_choose_yuan_1, R.id.tv_choose_yuan_2, R.id.tv_choose_yuan_3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_next:
                projectName = etProName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(projectName)) {
                    ToastUtils.getMineToast("请输入项目名称");
                    return;
                }
                if (!StringUtils.isNoEmpty(area1)) {
                    ToastUtils.getMineToast("请输入项目规模");
                    return;
                }
                if (!StringUtils.isNoEmpty(area2)) {
                    ToastUtils.getMineToast("请输入项目规模");
                    return;
                }
                proAddress = etProAddress.getText().toString().trim();
                if (!StringUtils.isNoEmpty(proAddress)) {
                    ToastUtils.getMineToast("请输入项目所在地");
                    return;
                }
                proDays = etProDays.getText().toString().trim();
                if (!StringUtils.isNoEmpty(proDays)) {
                    ToastUtils.getMineToast("请输入工期要求");
                    return;
                }
                profileRequair = etCxYq.getText().toString().trim();
                if (!StringUtils.isNoEmpty(profileRequair)) {
                    ToastUtils.getMineToast("请输入型材要求");
                    return;
                }
                linkMan = etLinkMan.getText().toString().trim();
                if (!StringUtils.isNoEmpty(linkMan)) {
                    ToastUtils.getMineToast("请选择联系人");
                    return;
                }
                linkPhone = etLinkPhone.getText().toString().trim();
                if (!StringUtils.isNoEmpty(linkPhone)) {
                    ToastUtils.getMineToast("请选择联系方式");
                    return;
                }
                sendAddress = etLinkAddress.getText().toString().trim();
                if (!StringUtils.isNoEmpty(sendAddress)) {
                    ToastUtils.getMineToast("请选择样品寄送地址");
                    return;
                }
                color = etColor.getText().toString().trim();
                if (!StringUtils.isNoEmpty(color) && (isMqZb || isSystemDoor)) {
                    ToastUtils.getMineToast("请输入颜色");
                    return;
                }
                touGuanLv = StringUtils.getNullStr(etTouGuan.getText().toString().trim());
                if (!StringUtils.isNoEmpty(touGuanLv) && (isMqZb || isSystemDoor)) {
                    ToastUtils.getMineToast("请输入透光率");
                    return;
                }
                kValue = StringUtils.getNullStr(etKValue.getText().toString().trim());
                if (!StringUtils.isNoEmpty(kValue) && (isMqZb || isSystemDoor)) {
                    ToastUtils.getMineToast("请输入K值");
                    return;
                }
                zheYangValue = StringUtils.getNullStr(etZyXs.getText().toString().trim());
                if (!StringUtils.isNoEmpty(zheYangValue) && (isMqZb || isSystemDoor)) {
                    ToastUtils.getMineToast("请输入遮阳系数");
                    return;
                }
                neiSheLv = StringUtils.getNullStr(etFanIndoor.getText().toString().trim());
                if (!StringUtils.isNoEmpty(neiSheLv) && (isMqZb || isSystemDoor)) {
                    ToastUtils.getMineToast("请输入室内反射率");
                    return;
                }
                waiFanShe = StringUtils.getNullStr(etOutDoor.getText().toString().trim());
                if (!StringUtils.isNoEmpty(waiFanShe) && (isMqZb || isSystemDoor)) {
                    ToastUtils.getMineToast("请输入室外反射率");
                    return;
                }
                profileTypes = etXczl.getText().toString().trim();
                if (!StringUtils.isNoEmpty(profileTypes) && isSystemDoor) {
                    ToastUtils.getMineToast("请输入型材种类");
                    return;
                }
                miFengSysytem = etMfSystem.getText().toString().trim();
                if (!StringUtils.isNoEmpty(miFengSysytem) && isSystemDoor) {
                    ToastUtils.getMineToast("请输入密封子系统");
                    return;
                }
                dcType = etDczjlx.getText().toString().trim();
                if (!StringUtils.isNoEmpty(dcType) && isGuanFuWindow) {
                    ToastUtils.getMineToast("请输入电池组件种类");
                    return;
                }
                faDianZh = etFdZhl.getText().toString().trim();
                if (!StringUtils.isNoEmpty(faDianZh) && isGuanFuWindow) {
                    ToastUtils.getMineToast("请输入发电转化率");
                    return;
                }
                gjZhiBiao = etJsIntro.getText().toString().trim();
                if (!StringUtils.isNoEmpty(gjZhiBiao) && isGuanFuWindow) {
                    ToastUtils.getMineToast("请输入关键技术指标描述");
                    return;
                }
                brand = etBrand.getText().toString().trim();
                if (!StringUtils.isNoEmpty(brand) && isDoorWindow) {
                    ToastUtils.getMineToast("请输入品牌");
                    return;
                }
                if (isDoorWindow) {
                    color = etDoorColor.getText().toString().trim();
                    if (!StringUtils.isNoEmpty(color)) {
                        ToastUtils.getMineToast("请输入颜色");
                        return;
                    }
                }
                flWuJin = etFlFive.getText().toString().trim();
                if (!StringUtils.isNoEmpty(flWuJin) && isDoorWindow) {
                    ToastUtils.getMineToast("请输入辅料五金");
                    return;
                }
                gyYq = etGyYq.getText().toString().trim();
                if (!StringUtils.isNoEmpty(gyYq) && isDoorWindow) {
                    ToastUtils.getMineToast("请输入工艺要求");
                    return;
                }
                thick = etGlassThick.getText().toString().trim();
                if (!StringUtils.isNoEmpty(thick) && isDoorWindow) {
                    ToastUtils.getMineToast("请输入与玻璃尺寸厚度");
                    return;
                }
                fuLiao = addYpAndFl();
                if (fuLiao.length() <= 10 && !isDoorWindow) {
                    ToastUtils.getMineToast("请选择原片和辅材");
                    return;
                }
                otherRequir = etOtherRequair.getText().toString().trim();
                if (isDoorWindow) {
                    PhotoUtils.getInstance().mutilLocalImageUp(imgList, GfZbActivity.this, new StringCallBack() {
                        @Override
                        public void onSuccess(String str) {
                            proPic = str;
                            postProZb(commitData().getMap());
                        }

                        @Override
                        public void onFail() {

                        }
                    });
                    return;
                }
                Intent intent = new Intent(this, GonYiChooseActivity.class);
                intent.putExtra(IntentUtils.getInstance().MAP, commitData());
                startActivity(intent);
//                commitData();
                break;
            case R.id.tv_pro_spec:
                EditDialogUtils.getInstance().showPostZbEdit(getContext(), etProDays, new OnZbStandardCallBack() {
                    @Override
                    public void onGetArea(String minAreas, String maxAreas) {
                        area1 = minAreas;
                        area2 = maxAreas;
                        tvProSpec.setText(area1 + "-" + area2 + "㎡  ");
                    }
                });
                break;
            //联系地址
            case R.id.et_link_man:
            case R.id.et_link_phone:
            case R.id.et_link_address:
                Intent intent1 = new Intent(getContext(), AddressListActivity.class);
                intent1.putExtra(IntentUtils.getInstance().TYPE, IntentUtils.getInstance().ADDRESS_CHOOSE);
                startActivityForResult(intent1, CHOOSE_ADDRESS);
                break;
            case R.id.tv_finish_time:
                TimeDialogUtils.getInstance().getTime(this, new OnTimeCallBack() {
                    @Override
                    public void getYearTime(String year) {
                        finishTime = year;
                        tvFinishTime.setText(finishTime);
                    }

                    @Override
                    public void getMinuteTime(String minute) {

                    }
                });
                break;
            case R.id.rl_yp_yq_1:
                KeyBoardUtils.closeKeybord(etBrand, this);
                PickDialogUtils.getInstance().getChooseDialog(this, "选择原片", listBrand, listChild, new ChooseDialogPositionCallBack() {
                    @Override
                    public void onGetMessagePosition(String choose, int position) {
                        tvYuan1.setText(choose);
                        if (choose.contains("Low-E") || choose.contains("有色")) {
                            tvChooseYuan1.setText("请选择  ");
                        } else {
                            tvChooseYuan1.setText("");
                        }
                        yuan_type_1 = choose;
                        yuan_choose_1 = resultList.get(position).getId() + "";
                    }
                });
                break;
            case R.id.rl_yp_yq_2:
                KeyBoardUtils.closeKeybord(etBrand, this);
                PickDialogUtils.getInstance().getChooseDialog(this, "选择原片", listBrand, listChild, new ChooseDialogPositionCallBack() {
                    @Override
                    public void onGetMessagePosition(String choose, int position) {
                        tvYuan2.setText(choose);
                        if (choose.contains("Low-E") || choose.contains("有色")) {
                            tvChooseYuan2.setText("请选择  ");
                        } else {
                            tvChooseYuan2.setText("");
                        }
                        yuan_type_2 = choose;
                        yuan_choose_2 = resultList.get(position).getId() + "";
                    }
                });
                break;
            case R.id.rl_yp_yq_3:
                KeyBoardUtils.closeKeybord(etBrand, this);
                PickDialogUtils.getInstance().getChooseDialog(this, "选择原片", listBrand, listChild, new ChooseDialogPositionCallBack() {
                    @Override
                    public void onGetMessagePosition(String choose, int position) {
                        tvYuan3.setText(choose);
                        if (choose.contains("Low-E") || choose.contains("有色")) {
                            tvChooseYuan3.setText("请选择  ");
                        } else {
                            tvChooseYuan3.setText("");
                        }
                        yuan_type_3 = choose;
                        yuan_choose_3 = resultList.get(position).getId() + "";
                    }
                });
                break;
            case R.id.rl_fc_lx_1:
                if (!StringUtils.isNoEmpty(yuan_type_1)) {
                    ToastUtils.getMineToast("请先选择原片类型");
                    return;
                }
                KeyBoardUtils.closeKeybord(etBrand, this);
                PickDialogUtils.getInstance().getChooseDialog(this, "选择辅材", listFuBrand, listFuChild, new ChooseDialogPositionCallBack() {
                    @Override
                    public void onGetMessagePosition(String choose, int position) {
                        tvFu1.setText(choose);
                        fu_type_1 = choose;
                    }
                });
                break;
            case R.id.rl_fc_lx_2:
                if (!StringUtils.isNoEmpty(yuan_type_2)) {
                    ToastUtils.getMineToast("请先选择原片类型");
                    return;
                }
                KeyBoardUtils.closeKeybord(etBrand, this);
                PickDialogUtils.getInstance().getChooseDialog(this, "选择辅材", listFuBrand, listFuChild, new ChooseDialogPositionCallBack() {
                    @Override
                    public void onGetMessagePosition(String choose, int position) {
                        tvFu2.setText(choose);
                        fu_type_2 = choose;
                    }
                });
                break;
            case R.id.rl_fc_lx_3:
                if (!StringUtils.isNoEmpty(yuan_type_3)) {
                    ToastUtils.getMineToast("请先选择原片类型");
                    return;
                }
                KeyBoardUtils.closeKeybord(etBrand, this);
                PickDialogUtils.getInstance().getChooseDialog(this, "选择辅材", listFuBrand, listFuChild, new ChooseDialogPositionCallBack() {
                    @Override
                    public void onGetMessagePosition(String choose, int position) {
                        tvFu3.setText(choose);
                        fu_type_3 = choose;
                    }
                });
                break;
            case R.id.tv_choose_yuan_1:
                Intent intentOne = new Intent(this, YuanFuSearchActivity.class);
                intentOne.putExtra(IntentUtils.getInstance().TYPE, yuan_choose_1);
                startActivityForResult(intentOne, YUNA_ONE);
                break;
            case R.id.tv_choose_yuan_2:
                Intent intentTwo = new Intent(this, YuanFuSearchActivity.class);
                intentTwo.putExtra(IntentUtils.getInstance().TYPE, yuan_choose_2);
                startActivityForResult(intentTwo, YUNA_TWO);
                break;
            case R.id.tv_choose_yuan_3:
                Intent intentThree = new Intent(this, YuanFuSearchActivity.class);
                intentThree.putExtra(IntentUtils.getInstance().TYPE, yuan_choose_3);
                startActivityForResult(intentThree, YUNA_THREE);
                break;
            case R.id.ll_bao_req:
                Intent intentBaoReq = new Intent(this, BaoRequirActivity.class);
                startActivityForResult(intentBaoReq, 6);
                break;
            //照片选择
            case R.id.iv_apply_camera:
                if (imgList.size() >= 9) {
                    ToastUtils.getMineToast("最多选择9张图片");
                    return;
                }
                int numImg = imgNum - imgList.size();

                PhotoUtils.getInstance().getMoreLocalPhoto(this, numImg, new PhotoCallBack() {
                    @Override
                    public void getPhoto(String path) {
                        String[] split = path.split(",");
                        if (split == null) {
                            return;
                        }

                        int num = split.length;
                        for (int i = 0; i < num; i++) {
                            imgList.add(split[i]);
                        }

                        imageAddAdapter.notifyDataSetChanged();
                    }
                });
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
            case CHOOSE_ADDRESS:
                if (data == null) {
                    return;
                }
                sendAddress = data.getStringExtra(IntentUtils.getInstance().ADDRESS_NAME);
                etLinkAddress.setText(sendAddress + "  ");
                linkMan = data.getStringExtra(IntentUtils.getInstance().ADDRESS_LINK_MAN);
                etLinkMan.setText(linkMan + "  ");
                linkPhone = data.getStringExtra(IntentUtils.getInstance().ADDRESS_LINK_TEL);
                etLinkPhone.setText(linkPhone + "  ");
                break;
            case YUNA_ONE:
                String type1 = data.getStringExtra(IntentUtils.getInstance().TYPE);
                if (StringUtils.isNoEmpty(yuan_type_1)) {
                    String[] split = yuan_type_1.split(",");
                    yuan_type_1 = split[0] + "," + split[1] + "," + type1;
                }
                tvYuan1.setText(yuan_type_1);
                break;
            case YUNA_TWO:
                String type2 = data.getStringExtra(IntentUtils.getInstance().TYPE);
                if (StringUtils.isNoEmpty(yuan_type_2)) {
                    String[] split = yuan_type_2.split(",");
                    yuan_type_2 = split[0] + "," + split[1] + "," + type2;
                }
                tvYuan2.setText(yuan_type_2);
                break;
            case YUNA_THREE:
                String type3 = data.getStringExtra(IntentUtils.getInstance().TYPE);
                if (StringUtils.isNoEmpty(yuan_type_3)) {
                    String[] split = yuan_type_3.split(",");
                    yuan_type_3 = split[0] + "," + split[1] + "," + type3;
                }
                tvYuan3.setText(yuan_type_3);
                break;
            case 6:
                String str = data.getStringExtra(IntentUtils.getInstance().TYPE);
                if (str.contains("需送样品")) {
                    isNeedYp = "1";
                } else {
                    isNeedYp = "0";
                }
                if (str.contains("需提供案例")) {
                    isNeedAnLi = "1";
                } else {
                    isNeedAnLi = "0";
                }
                tvBaoReq.setText(str);
                break;
        }
    }

    public SerMap commitData() {
        Map<String, String> map = new HashMap<>();
        map.put("ProjectTitle", projectName);
        map.put("ProjectType", projectType);
        map.put("Area", area1);
        map.put("Area1", area2);
        map.put("Brands", brand);
        map.put("MoxiName", moxi);
        map.put("Fuliaos", fuLiao);
        map.put("ColorVal", color);
        map.put("TouGuangVal", touGuanLv);
        map.put("FanseInVal", neiSheLv);
        map.put("FanseOutVal", waiFanShe);
        map.put("Kval", kValue);
        map.put("ZheyanVal", zheYangValue);
        map.put("LinkMan", linkMan);
        map.put("LinkTel", linkPhone);
        map.put("NeedDay", proDays);
        map.put("ProjectAddress", proAddress);
        map.put("ToState", state);
        map.put("YpAddress", sendAddress);
        map.put("BeginTime", startTime);
        map.put("EndTime", endTime);
        map.put("UserID", PersonConstant.getInstance().getUserId());
        map.put("IsNeedAnli", isNeedAnLi);
        map.put("IsNeedYp", isNeedYp);
        map.put("RequestPics", proPic);
        map.put("BatteryType", dcType);
        map.put("SealingSubsystem", miFengSysytem);
        map.put("ElectricRate", faDianZh);
        map.put("IndexDescript", gjZhiBiao);
        map.put("TechnologicalRequirements", gyYq);
        map.put("FLHardware", flWuJin);
        map.put("Thickness", thick);
        map.put("LengthAndHeight", length);
        map.put("OtherRequirements", otherRequir);
        map.put("ProfilesType", profileTypes);
        map.put("ProfilesRequest", profileRequair);
        map.put("Makespan", finishTime);
//        OkhttpUtil.getInstance().basePostCall(this, map, UrlConstant.getInstance().NEWS_URL + "YpRequestAdd", new StringCallBack() {
//            @Override
//            public void onSuccess(String str) {
//
//            }
//
//            @Override
//            public void onFail() {
//
//            }
//        });
        //让hashmap实现可序列化则要定义一个实现可序列化的类。
        SerMap serMap = new SerMap();
        serMap.setMap(map);
        return serMap;
    }

    //原片数据
    public void getYuanFuData() {
        OkhttpUtil.getInstance().basePostCall(this, UrlConstant.getInstance().YUAN_FU_URL + "YPClassAndGuiGe", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                YuanFuBean fuBean = JSON.parseObject(str, YuanFuBean.class);
                if (fuBean.getD().getErrorCode() != 0) {
                    return;
                }
                List<YuanFuBean.DBean.ResultBean> result = fuBean.getD().getResult();
                resultList.addAll(result);
                int num = result.size();
                for (int i = 0; i < num; i++) {
                    listBrand.add(result.get(i).getValue());
                    listChild.add(result.get(i).getListChild());
                }
                getFuData();
            }

            @Override
            public void onFail() {
                getFuData();
            }
        });
    }

    //辅材数据
    public void getFuData() {
        OkhttpUtil.getInstance().basePostCall(this, UrlConstant.getInstance().YUAN_FU_URL + "GetFLGuiGeSelect", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                YuanFuBean fuBean = JSON.parseObject(str, YuanFuBean.class);
                if (fuBean.getD().getErrorCode() != 0) {
                    return;
                }
                List<YuanFuBean.DBean.ResultBean> result = fuBean.getD().getResult();
                int num = result.size();
                for (int i = 0; i < num; i++) {
                    listFuBrand.add(result.get(i).getValue());
                    listFuChild.add(result.get(i).getListChild());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    //拼接原片和辅材
    public String addYpAndFl() {
        String str = "";
        str += getNoNullYpFu(yuan_type_1);
        str += getNoNullYpFu(fu_type_1);
        str += getNoNullYpFu(yuan_type_2);
        str += getNoNullYpFu(fu_type_2);
        str += getNoNullYpFu(yuan_type_3);
//        str += getNoNullYpFu(fu_type_3);
        return str;
    }

    public String getNoNullYpFu(String str) {
        if (StringUtils.isNoEmpty(str)) {
            return str + ";";
        }
        return ",;";
    }

    //发布项目招标
    public void postProZb(Map<String, String> map) {
//        String url = "http://192.168.10.120/webapi/ServiceInterface/JuboBao/ProjectRequest.asmx/YpRequestAdd";
        String json = JSON.toJSONString(map);
        String putJson = "{\"model\":" + json + "}";
        LogUtils.e(putJson);
        OkhttpUtil.getInstance().basePostCall(this, putJson, UrlConstant.getInstance().YUAN_FU_URL + "YpRequestAdd", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                ZbResultBean resultBean = JSON.parseObject(str, ZbResultBean.class);
                if (resultBean.getD().getResult().equals("sucess")) {
                    ToastUtils.getMineToast("提交成功");
                    ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().newZbList);
                } else {
                    ToastUtils.getMineToast(resultBean.getD().getMsg());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }
}
