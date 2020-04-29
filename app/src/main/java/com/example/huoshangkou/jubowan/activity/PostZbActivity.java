package com.example.huoshangkou.jubowan.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.PostZbFunction;
import com.example.huoshangkou.jubowan.adapter.LanImageShowAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.GYBean;
import com.example.huoshangkou.jubowan.bean.GYListBean;
import com.example.huoshangkou.jubowan.bean.GonYiBean;
import com.example.huoshangkou.jubowan.bean.GonYiListBean;
import com.example.huoshangkou.jubowan.bean.ZbBrandBean;
import com.example.huoshangkou.jubowan.bean.ZbBrandListBean;
import com.example.huoshangkou.jubowan.bean.ZbLowBean;
import com.example.huoshangkou.jubowan.bean.ZbLowListBean;
import com.example.huoshangkou.jubowan.inter.ChooseDialogPositionCallBack;
import com.example.huoshangkou.jubowan.inter.CityNameInterface;
import com.example.huoshangkou.jubowan.inter.OnCommonSuccessCallBack;
import com.example.huoshangkou.jubowan.inter.OnGYCallBack;
import com.example.huoshangkou.jubowan.inter.OnGonYiCallBack;
import com.example.huoshangkou.jubowan.inter.OnGyListCallBack;
import com.example.huoshangkou.jubowan.inter.OnZbBrandCallBack;
import com.example.huoshangkou.jubowan.inter.OnZbLowCallBack;
import com.example.huoshangkou.jubowan.inter.OnZbStandardCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.CityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.EditDialogUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.PickDialogUtils;
import com.example.huoshangkou.jubowan.utils.ShowHideUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.LiuShiLayoutView;
import com.example.huoshangkou.jubowan.view.ScrollRecylerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：PostZbActivity
 * 描述：
 * 创建时间：2017-04-08  14:35
 */

public class PostZbActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    //招标选择
    @Bind(R.id.tv_brand_choose)
    TextView tvZbChoose;
    //加工工艺
    @Bind(R.id.liu_shi_zb)
    LiuShiLayoutView liuShiLayoutView;
    //添加附件
    @Bind(R.id.recyler_gon_yi)
    ScrollRecylerView recylerView;
    LanImageShowAdapter lanImageShowAdapter;
    @Bind(R.id.tv_yuan_1)
    TextView tvYuan1;
    @Bind(R.id.tv_yuan_standard_1)
    TextView tvYuanStandard1;
    @Bind(R.id.tv_fu_1)
    TextView tvFu1;
    @Bind(R.id.tv_fu_standard_1)
    TextView tvFuStandard1;
    @Bind(R.id.tv_yuan_2)
    TextView tvYuan2;
    @Bind(R.id.tv_yuan_standard_2)
    TextView tvYuanStandard2;
    @Bind(R.id.tv_fu_2)
    TextView tvFu2;
    @Bind(R.id.tv_fu_standard_2)
    TextView tvFuStandard2;
    @Bind(R.id.tv_yuan_3)
    TextView tvYuan3;
    @Bind(R.id.tv_yuan_standard_3)
    TextView tvYuanStandard3;
    @Bind(R.id.tv_unit)
    TextView tvUnit;
    @Bind(R.id.tv_low_color)
    TextView tvLowColor;
    @Bind(R.id.tv_low_tgl)
    TextView tvLowTgl;
    @Bind(R.id.tv_low_in)
    TextView tvLowIn;
    @Bind(R.id.tv_low_out)
    TextView tvLowOut;
    @Bind(R.id.tv_low_k)
    TextView tvLowK;
    @Bind(R.id.tv_low_zy)
    TextView tvLowZy;
    @Bind(R.id.iv_zb_color)
    ImageView imgZbColor;
    @Bind(R.id.iv_floor_color)
    ImageView imgFloorColor;
    @Bind(R.id.tv_floor_color)
    TextView tvFloorColor;
    @Bind(R.id.tv_low_name)
    TextView tvLowName;
    //项目所在地
    @Bind(R.id.tv_pro_address)
    TextView tvProAddress;
    //项目规模
    @Bind(R.id.tv_pro_standard)
    TextView tvProStandard;
    @Bind(R.id.tv_link_man)
    TextView tvLinkMan;
    @Bind(R.id.tv_link_phone)
    TextView tvLinkPhone;
    @Bind(R.id.tv_link_address)
    TextView tvLinkAddress;
    //需要样品
    @Bind(R.id.cb_need_pro)
    CheckBox cbNeedYp;
    //需要案例
    @Bind(R.id.cb_need_anli)
    CheckBox cbNeedAl;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.et_pro_days)
    EditText etProDays;
    //地址id
    private String addressID = "";
    private int imgNum = 9;
    //招标品牌信息
    private List<ZbBrandListBean> zbBrandList;
    private ArrayList<String> zbBrandStringList;
    //加工工艺
    private List<GonYiListBean> gonYiList;
    //附件
    List<String> imgList;
    private ViewGroup.MarginLayoutParams lp;
    //选择品牌
    private String zbBrandChoose = "";
    //地址选择
    private final int CHOOSE_ADDRESS = 2;
    //原片
    private final String YUAN = "0";
    //辅料
    private final String FU = "2";
    private String fatherId_yuan_1 = "";
    private String fatherId_yuan_2 = "";
    private String fatherId_yuan_3 = "";
    private String fatherId_fu_1 = "";
    private String fatherId_fu_2 = "";
    private String yuan_type_1 = "";
    private String yuan_type_2 = "";
    private String yuan_type_3 = "";
    private String fu_type_1 = "";
    private String fu_type_2 = "";
    private String yuan_standard_1 = "";
    private String yuan_standard_2 = "";
    private String yuan_standard_3 = "";
    private String fu_standard_1 = "";
    private String fu_standard_2 = "";
    //原片数据工艺
    private ArrayList<GYListBean> gyListBeanYuanList;
    //辅材数据工艺
    private ArrayList<GYListBean> gyListBeanFuList;
    //原片规格数据
    private ArrayList<GYListBean> gyListBeanYuanStandardList;
    //辅材规格数据
    private ArrayList<GYListBean> gyListBeanStandardFuList;
    //墙体颜色
    private ArrayList<String> floorColor;
    //工程类型
    @Bind(R.id.rg_pro_type)
    RadioGroup rgProType;
    @Bind(R.id.et_pro_name)
    EditText etProName;
    //项目名称
    private String proName;
    //工程类型
    private String proType = "";
    //项目规模
    private String minArea = "";
    private String maxArea = "";
    //项目所在地
    private String proAddress = "";
    //样品寄送地址
    private String ypAddress = "";
    //工期要求
    private String proDays = "";
    //是否需要案例
    private String isNeedAnLi = "0";
    //是否需要样品
    private String isNeedYp = "0";
    //联系人
    private String linkMan = "";
    //联系方式
    private String linkTel = "";
    //品牌
    private String brandId = "";
    //膜系
    private String lowId = "";
    private String lowName = "";
    //墙体颜色
    private String floorColorMessage = "";
    //加工工艺
    private String jgGonYi = "";
    //附件图片
    private String fjPic = "";
    //原片和辅材
    private String ypAndFl = "";
    //是否是查看招标详情
    private boolean isCheckDetail = false;
    private double price = 0;
    private double price_1 = 0;
    private double price_2 = 0;
    private double price_3 = 0;
    private double price_4 = 0;
    private double price_5 = 0;
    private double price_6 = 0;

    @Override
    public int initLayout() {
        return R.layout.activity_post_zb;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("发布招标");
        zbBrandList = new ArrayList<>();
        zbBrandStringList = new ArrayList<>();
        gonYiList = new ArrayList<>();
        imgList = new ArrayList<>();
        gyListBeanYuanStandardList = new ArrayList<>();
        gyListBeanStandardFuList = new ArrayList<>();
        floorColor = new ArrayList<>();
        int num = PostZbFunction.getInstance().qiang.length;
        for (int i = 0; i < num; i++) {
            floorColor.add(PostZbFunction.getInstance().qiang[i]);
        }
        gyListBeanYuanList = new ArrayList<>();
        gyListBeanFuList = new ArrayList<>();

        lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;

        //获取加工工艺
        getJGGonYiData();
        lanImageShowAdapter = new LanImageShowAdapter(imgList, getContext());
        recylerView.setAdapter(lanImageShowAdapter);
        recylerView.setLayoutManager(getLayoutManager());


        //删除图片
        lanImageShowAdapter.setDeleteImg(new LanImageShowAdapter.deleteClick() {
            @Override
            public void deleteImgClick(final int position) {
                CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否删除图片", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        imgList.remove(position);
                        lanImageShowAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }
        });

        //工程类型
        rgProType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_wall_pro:
                        proType = "幕墙项目";
                        break;
                    case R.id.rb_door_pro:
                        proType = "门窗项目";
                        break;
                    case R.id.rb_other_pro:
                        proType = "其他";
                        break;
                }
            }
        });

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
    }

    @OnClick({R.id.ll_back, R.id.ll_pro_address, R.id.rl_brand_choose, R.id.ll_add_file, R.id.ll_yuan_1, R.id.ll_yuan_2, R.id.ll_yuan_3,
            R.id.ll_yuan_standard_1, R.id.ll_yuan_standard_2, R.id.ll_yuan_standard_3, R.id.ll_fu_1, R.id.ll_fu_2,
            R.id.ll_fu_standard_1, R.id.ll_fu_standard_2, R.id.ll_low_choose, R.id.ll_color_choose, R.id.tv_post_zb,
            R.id.ll_pro_standard, R.id.rl_link_man, R.id.rl_link_address, R.id.rl_link_phone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.ll_pro_address:
                CityUtils.getInstance().getAllCity(getContext(), new CityNameInterface() {
                    @Override
                    public void getCityName(String area) {
                        proAddress = area;
                        tvProAddress.setText(proAddress);
                    }
                });
                break;
            //品牌选择
            case R.id.rl_brand_choose:

                if (zbBrandList.size() != 0) {
                    getShowBrandData();
                    return;
                }

                PostZbFunction.getInstance().getBrandData(getContext(), new OnZbBrandCallBack() {
                    @Override
                    public void onSuccess(ZbBrandBean zbBrandBean) {
                        zbBrandList.addAll(zbBrandBean.getReList());
                        getShowBrandData();
                    }
                });
                break;
            //添加附件
            case R.id.ll_add_file:

                if (imgList.size() >= 9) {
                    ToastUtils.getMineToast("最多选择9张图片");
                    return;
                }
                int num = imgNum - imgList.size();

                PhotoUtils.getInstance().getMoreLocalPhoto(PostZbActivity.this, num, new PhotoCallBack() {
                    @Override
                    public void getPhoto(String path) {
                        imgList.addAll(PhotoUtils.getInstance().getListImage(path));
                        lanImageShowAdapter.notifyDataSetChanged();
                    }
                });
                break;
            case R.id.ll_yuan_1:
                getZbType(YUAN, "请选择原片类型", "", 1, tvYuan1);
                break;
            case R.id.ll_yuan_2:
                getZbType(YUAN, "请选择原片类型", "", 2, tvYuan2);
                break;
            case R.id.ll_yuan_3:
                getZbType(YUAN, "请选择原片类型", "", 3, tvYuan3);
                break;
            case R.id.ll_yuan_standard_1:
                if (!StringUtils.isNoEmpty(fatherId_yuan_1)) {
                    ToastUtils.getMineToast("请选择原片类型");
                    return;
                }
                getZbType(YUAN, "请选择原片类型", fatherId_yuan_1, 1, tvYuanStandard1);
                break;
            case R.id.ll_yuan_standard_2:
                if (!StringUtils.isNoEmpty(fatherId_yuan_2)) {
                    ToastUtils.getMineToast("请选择原片类型");
                    return;
                }
                getZbType(YUAN, "请选择原片类型", fatherId_yuan_2, 2, tvYuanStandard2);
                break;
            case R.id.ll_yuan_standard_3:
                if (!StringUtils.isNoEmpty(fatherId_yuan_3)) {
                    ToastUtils.getMineToast("请选择原片类型");
                    return;
                }
                getZbType(YUAN, "请选择原片类型", fatherId_yuan_3, 3, tvYuanStandard3);
                break;
            case R.id.ll_fu_1:
                getZbType(FU, "请选择辅料类型", "", 4, tvFu1);
                break;
            case R.id.ll_fu_2:
                getZbType(FU, "请选择辅料类型", "", 5, tvFu2);
                break;
            case R.id.ll_fu_standard_1:
                if (!StringUtils.isNoEmpty(fatherId_fu_1)) {
                    ToastUtils.getMineToast("请选择辅料类型");
                    return;
                }
                getZbType(YUAN, "请选择原片类型", fatherId_fu_1, 4, tvFuStandard1);
                break;
            case R.id.ll_fu_standard_2:
                if (!StringUtils.isNoEmpty(fatherId_fu_2)) {
                    ToastUtils.getMineToast("请选择辅料类型");
                    return;
                }
                getZbType(YUAN, "请选择原片类型", fatherId_fu_2, 5, tvFuStandard2);
                break;
            //膜系选择
            case R.id.ll_low_choose:
                if (!StringUtils.isNoEmpty(brandId)) {
                    ToastUtils.getMineToast("请选择品牌");
                    return;
                }
                PostZbFunction.getInstance().getLowData(getContext(), brandId, new OnZbLowCallBack() {
                    @Override
                    public void onZbLowCallBack(final ZbLowBean lowBean) {
                        ArrayList<String> lowList = new ArrayList<>();
                        int num = lowBean.getReList().size();
                        for (int i = 0; i < num; i++) {
                            lowList.add(lowBean.getReList().get(i).getMoxiName());
                        }
                        PickDialogUtils.getInstance().getChoosePositionDialog(getContext(), "请选择膜系", lowList, new ChooseDialogPositionCallBack() {
                            @Override
                            public void onGetMessagePosition(String message, int position) {
                                lowId = lowBean.getReList().get(position).getID() + "";
                                lowName = lowBean.getReList().get(position).getMoxiName();
                                tvLowName.setText(message + "  ");
                                showLowData(lowBean.getReList().get(position));
                            }
                        });
                    }

                    @Override
                    public void onFail() {

                    }
                });
                break;
            //颜色获取
            case R.id.ll_color_choose:
                PickDialogUtils.getInstance().getChoosePositionDialog(getContext(), "请选择墙体颜色", floorColor, new ChooseDialogPositionCallBack() {
                    @Override
                    public void onGetMessagePosition(String message, int position) {
                        tvFloorColor.setText(message + "  ");
                        floorColorMessage = message;
                        GlideUtils.getInstance().displayImage(getContext(), PostZbFunction.getInstance().qiangImg[position], imgFloorColor);
                    }
                });
                break;
            //发布招标
            case R.id.tv_post_zb:
                proName = etProName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(proName)) {
                    ToastUtils.getMineToast("请输入项目名称");
                    return;
                }

                if (!StringUtils.isNoEmpty(proType)) {
                    ToastUtils.getMineToast("请选择工程类型");
                    return;
                }
                if (!StringUtils.isNoEmpty(minArea) || !StringUtils.isNoEmpty(maxArea)) {
                    ToastUtils.getMineToast("请选择项目规模");
                    return;
                }

                if (!StringUtils.isNoEmpty(proAddress)) {
                    ToastUtils.getMineToast("请选择项目所在地");
                    return;
                }

                proDays = etProDays.getText().toString().trim();
                if (!StringUtils.isNoEmpty(proDays)) {
                    ToastUtils.getMineToast("请输入工期要求");
                    return;
                }

                if (!StringUtils.isNoEmpty(ypAddress) || !StringUtils.isNoEmpty(linkMan) || !StringUtils.isNoEmpty(linkTel)) {
                    ToastUtils.getMineToast("请选择联系地址");
                    return;
                }

                if (!StringUtils.isNoEmpty(brandId)) {
                    ToastUtils.getMineToast("请选择品牌");
                    return;
                }

                if (!StringUtils.isNoEmpty(lowId)) {
                    ToastUtils.getMineToast("请选择膜系");
                }

                if (!StringUtils.isNoEmpty(floorColorMessage)) {
                    ToastUtils.getMineToast("请选择墙体颜色");
                }

                ypAndFl = addYpAndFl();

                if (ypAndFl.length() <= 10) {
                    ToastUtils.getMineToast("请选择原片和辅材");
                    return;
                }

                int size = gonYiList.size();
                for (int i = 0; i < size; i++) {
                    if (gonYiList.get(i).isCheck()) {
                        jgGonYi += ",;";
                    }
                }

                if (!StringUtils.isNoEmpty(jgGonYi)) {
                    ToastUtils.getMineToast("请选择加工工艺");
                    return;
                }

                CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否发布招标", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        PhotoUtils.getInstance().mutilLocalImageUp(imgList, PostZbActivity.this, new StringCallBack() {
                            @Override
                            public void onSuccess(String str) {
                                PostZbFunction.getInstance().putOnLineZb(getContext(), proName, proType, minArea, maxArea, linkMan, linkTel,
                                        proDays, proAddress, isNeedAnLi, isNeedYp, ypAddress, brandId,
                                        lowName, lowId, ypAndFl, jgGonYi, str, new OnCommonSuccessCallBack() {
                                            @Override
                                            public void onSuccess() {
                                                PostZbActivity.this.finish();
                                            }

                                            @Override
                                            public void onFail() {

                                            }
                                        });
                            }

                            @Override
                            public void onFail() {

                            }
                        });

                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
                break;
            //项目规模
            case R.id.ll_pro_standard:
                EditDialogUtils.getInstance().showPostZbEdit(getContext(), etProDays, new OnZbStandardCallBack() {
                    @Override
                    public void onGetArea(String minAreas, String maxAreas) {
                        minArea = minAreas;
                        maxArea = maxAreas;
                        tvProStandard.setText(minArea + "-" + maxArea + "㎡  ");
                    }
                });
                break;
            //地址选择
            case R.id.rl_link_man:
            case R.id.rl_link_phone:
            case R.id.rl_link_address:
                Intent intent1 = new Intent(getContext(), AddressListActivity.class);
                intent1.putExtra(IntentUtils.getInstance().TYPE, IntentUtils.getInstance().ADDRESS_CHOOSE);
                startActivityForResult(intent1, CHOOSE_ADDRESS);
                break;
        }
    }

    private String oldBrand = "";

    public void getShowBrandData() {
        zbBrandStringList.clear();
        int num = zbBrandList.size();
        for (int i = 0; i < num; i++) {
            zbBrandStringList.add(zbBrandList.get(i).getBrandTitle());
        }

        PickDialogUtils.getInstance().getChoosePositionDialog(getContext(), "请选择品牌", zbBrandStringList, new ChooseDialogPositionCallBack() {
            @Override
            public void onGetMessagePosition(String message, int position) {
                if (!oldBrand.equals(message)) {
                    lowId = "";
                    lowName = "";
                    tvLowName.setText("  ");
                }

                zbBrandChoose = message;
                brandId = zbBrandList.get(position).getID() + "";
                tvZbChoose.setText(message + "  ");

                oldBrand = message;
            }
        });
    }

    //获取加工工艺
    public void getJGGonYiData() {
        PostZbFunction.getInstance().getGonYiData(getContext(), new OnGonYiCallBack() {
            @Override
            public void onSuccess(GonYiBean gonYiBean) {
                liuShiLayoutView.removeAllViews();
                gonYiList.clear();
                gonYiList.addAll(gonYiBean.getReList());
                int num = gonYiList.size();
                for (int i = 0; i < num; i++) {
                    CheckBox textView = new CheckBox(getContext());
                    textView.setText(gonYiList.get(i).getTitle());
                    textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.gong_yi_check));
                    textView.setButtonDrawable(R.drawable.et_blank_bg);
                    textView.setTextColor(getResources().getColor(R.color.calculator_text_color));
                    textView.setPadding(10, 10, 10, 10);
                    liuShiLayoutView.addView(textView, lp);
                }

                int num2 = liuShiLayoutView.getChildCount();
                for (int i = 0; i < num2; i++) {
                    final int finalI = i;
                    final CheckBox childAt = (CheckBox) liuShiLayoutView.getChildAt(i);

                    childAt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if (b) {
                                gonYiList.get(finalI).setCheck(true);
                                childAt.setTextColor(getResources().getColor(R.color.white));
                                price_6 += gonYiList.get(finalI).getToPrice();
                            } else {
                                gonYiList.get(finalI).setCheck(false);
                                childAt.setTextColor(getResources().getColor(R.color.address_black_key));
                                price_6 -= gonYiList.get(finalI).getToPrice();
                            }
                            getPrie();
                        }
                    });

                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    //获取工艺   0为原片工艺，2为辅料工艺
    public void getGyData(final String type, final String fatherId, final OnGyListCallBack listCallBack) {
        if (gyListBeanYuanList.size() != 0 && type.equals("0") && !StringUtils.isNoEmpty(fatherId)) {
            listCallBack.getGyListSuccess(gyListBeanYuanList);
            return;
        }

        if (gyListBeanFuList.size() != 0 && type.equals("2") && !StringUtils.isNoEmpty(fatherId)) {
            listCallBack.getGyListSuccess(gyListBeanFuList);
            return;
        }

        //获取工艺数据
        PostZbFunction.getInstance().getGYiData(getContext(), type, fatherId, new OnGYCallBack() {
            @Override
            public void onSuccess(GYBean gyBean) {
                //原片工艺
                if (type.equals("0")) {
                    //获取规格
                    if (StringUtils.isNoEmpty(fatherId)) {
                        gyListBeanYuanStandardList.clear();
                        gyListBeanYuanStandardList.addAll(gyBean.getReList());
                        listCallBack.getGyListSuccess(gyListBeanYuanStandardList);
                    } else {
                        gyListBeanYuanList.clear();
                        gyListBeanYuanList.addAll(gyBean.getReList());
                        listCallBack.getGyListSuccess(gyListBeanYuanList);
                    }

                    //辅料工艺
                } else if (type.equals("2")) {
                    //获取规格
                    if (StringUtils.isNoEmpty(fatherId)) {
                        gyListBeanStandardFuList.clear();
                        gyListBeanStandardFuList.addAll(gyBean.getReList());
                        listCallBack.getGyListSuccess(gyListBeanStandardFuList);
                    } else {
                        gyListBeanFuList.clear();
                        gyListBeanFuList.addAll(gyBean.getReList());
                        listCallBack.getGyListSuccess(gyListBeanFuList);
                    }
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    //类型展示
    public void getZbType(String type, final String title, final String fatherId, final int typePosition, final TextView textView) {
        getGyData(type, fatherId, new OnGyListCallBack() {
            @Override
            public void getGyListSuccess(final ArrayList<GYListBean> gyListBeanList) {
                ArrayList<String> typeList = new ArrayList<>();
                int num = gyListBeanList.size();
                for (int i = 0; i < num; i++) {
                    typeList.add(gyListBeanList.get(i).getTitle());
                }
                PickDialogUtils.getInstance().getChoosePositionDialog(getContext(), title, typeList, new ChooseDialogPositionCallBack() {
                    @Override
                    public void onGetMessagePosition(String message, int position) {
                        textView.setText(message);
                        switch (typePosition) {
                            case 1:
                                if (StringUtils.isNoEmpty(fatherId)) {
                                    yuan_standard_1 = gyListBeanList.get(position).getTitle();
                                    price_1 = gyListBeanList.get(position).getToPrice();
                                    getPrie();
                                } else {
                                    fatherId_yuan_1 = gyListBeanList.get(position).getID() + "";
                                    yuan_type_1 = gyListBeanList.get(position).getTitle();
                                    price_1 = 0;
                                    yuan_standard_1 = "";
                                    tvYuanStandard1.setText("规格");
                                    getPrie();
                                }
                                break;
                            case 2:
                                if (StringUtils.isNoEmpty(fatherId)) {
                                    yuan_standard_2 = gyListBeanList.get(position).getTitle();
                                    price_2 = gyListBeanList.get(position).getToPrice();
                                    getPrie();
                                } else {
                                    fatherId_yuan_2 = gyListBeanList.get(position).getID() + "";
                                    yuan_type_2 = gyListBeanList.get(position).getTitle();
                                    price_2 = 0;
                                    yuan_standard_2 = "";
                                    tvYuanStandard2.setText("规格");
                                    getPrie();
                                }
                                break;
                            case 3:
                                if (StringUtils.isNoEmpty(fatherId)) {
                                    yuan_standard_3 = gyListBeanList.get(position).getTitle();
                                    price_3 = gyListBeanList.get(position).getToPrice();
                                    getPrie();
                                } else {
                                    fatherId_yuan_3 = gyListBeanList.get(position).getID() + "";
                                    yuan_type_3 = gyListBeanList.get(position).getTitle();
                                    price_3 = 0;
                                    yuan_standard_3 = "";
                                    tvYuanStandard3.setText("规格");
                                    getPrie();
                                }
                                break;
                            case 4:
                                if (StringUtils.isNoEmpty(fatherId)) {
                                    fu_standard_1 = gyListBeanList.get(position).getTitle();
                                    price_4 = gyListBeanList.get(position).getToPrice();
                                    getPrie();
                                } else {
                                    fatherId_fu_1 = gyListBeanList.get(position).getID() + "";
                                    fu_type_1 = gyListBeanList.get(position).getTitle();
                                    price_4 = 0;
                                    fu_standard_1 = "";
                                    tvFuStandard1.setText("规格");
                                    getPrie();
                                }
                                break;
                            case 5:
                                if (StringUtils.isNoEmpty(fatherId)) {
                                    fu_standard_2 = gyListBeanList.get(position).getTitle();
                                    price_5 = gyListBeanList.get(position).getToPrice();
                                    getPrie();
                                } else {
                                    fatherId_fu_2 = gyListBeanList.get(position).getID() + "";
                                    fu_type_2 = gyListBeanList.get(position).getTitle();
                                    price_5 = 0;
                                    fu_standard_2 = "";
                                    tvFuStandard2.setText("规格");
                                    getPrie();
                                }
                                break;
                        }
                    }
                });
            }
        });
    }


    //获取膜系及数据展示
    public void showLowData(ZbLowListBean lowListBean) {
        tvLowColor.setText(lowListBean.getColorName());
        tvLowTgl.setText(lowListBean.getTouGuangVal() + "");
        tvLowIn.setText(lowListBean.getFanseInVal() + "");
        tvLowOut.setText(lowListBean.getFanseOutVal() + "");
        tvLowK.setText(lowListBean.getKval() + "");
        tvLowZy.setText(lowListBean.getZeyanVal() + "");

        GlideUtils.getInstance().displayImage(lowListBean.getShowPic(), getContext(), imgZbColor);
    }

    //点击隐藏输入框
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (ShowHideUtils.isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    //拼接原片和辅材
    public String addYpAndFl() {
        String str = "";
        str += yuan_type_1 + ",";
        str += yuan_standard_1 + ";";
        str += yuan_type_2 + ",";
        str += yuan_standard_2 + ";";
        str += yuan_type_3 + ",";
        str += yuan_standard_3 + ";";
        str += fu_type_1 + ",";
        str += fu_standard_1 + ";";
        str += fu_type_1 + ",";
        str += fu_standard_2 + ";";

        return str;
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

                ypAddress = data.getStringExtra(IntentUtils.getInstance().ADDRESS_NAME);
                tvLinkAddress.setText(ypAddress + "  ");
                linkMan = data.getStringExtra(IntentUtils.getInstance().ADDRESS_LINK_MAN);
                tvLinkMan.setText(linkMan + "  ");
                linkTel = data.getStringExtra(IntentUtils.getInstance().ADDRESS_LINK_TEL);
                tvLinkPhone.setText(linkTel + "  ");
                addressID = data.getStringExtra(IntentUtils.getInstance().ADDRESS_ID);
                break;
        }
    }


    //计算价格
    private void getPrie() {
        price = price_1 + price_2 + price_3 + price_4 + price_5 + price_6;
        tvPrice.setText(price + "");
    }


    private StaggeredGridLayoutManager getLayoutManager() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL);
        return layoutManager;
    }

}
