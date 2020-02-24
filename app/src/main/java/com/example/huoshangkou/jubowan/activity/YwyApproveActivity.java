package com.example.huoshangkou.jubowan.activity;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.LoginFunction;
import com.example.huoshangkou.jubowan.activity.function.YwyApproveFunction;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.AreaBean;
import com.example.huoshangkou.jubowan.bean.BrandBean;
import com.example.huoshangkou.jubowan.bean.ClassBean;
import com.example.huoshangkou.jubowan.bean.YwyApproveBean;
import com.example.huoshangkou.jubowan.bean.YwyInfoObjBean;
import com.example.huoshangkou.jubowan.inter.ApproveCallBack;
import com.example.huoshangkou.jubowan.inter.ChooseDialogCallBack;
import com.example.huoshangkou.jubowan.inter.OnYwyApproveCallBack;
import com.example.huoshangkou.jubowan.inter.OnYwyApproveTypeCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CardUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.EditTextUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.PickDialogUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：YwyApproveActivity
 * 描述：实名认证界面
 * 创建时间：2017-03-01  09:59
 */

public class YwyApproveActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.iv_up_image)
    ImageView ivUpImage;
    @Bind(R.id.iv_down_image)
    ImageView ivDownImage;
    @Bind(R.id.iv_work_image)
    ImageView ivWorkImage;
    @Bind(R.id.iv_work_second)
    ImageView ivWorkImageSecond;

    @Bind(R.id.et_true_name)
    EditText etTrueName;
    @Bind(R.id.et_card_no)
    EditText etCardNo;
    @Bind(R.id.et_company_name)
    EditText etCompanyName;
    @Bind(R.id.et_link_mail)
    EditText etLinkMail;
    @Bind(R.id.et_web_url)
    EditText etWebUrl;

    @Bind(R.id.tv_class_type)
    TextView tvClassType;
    @Bind(R.id.tv_brand_type)
    TextView tvBrandType;
    @Bind(R.id.tv_area_type)
    TextView tvAreaType;


    //身份选择
    private String manChoose;
    //合伙人类型
    private String manType;
    //负责品类
    private String classType;
    //负责品牌
    private String brandType;
    //负责地区
    private String areaType;
    //真实姓名
    private String trueName;
    //身份证号
    private String cardNo;
    //企业名称
    private String companyName;
    //联系邮箱
    private String linkMail;
    //公司官网
    private String companyWeb;
    //身份证正面
    private String onCard;
    //身份证反面
    private String backCard;
    //工作证1
    private String work_1;
    //工作证2
    private String work_2;

    private String typeChoose = "";

    private ArrayList<AreaBean> areaList;
    private ArrayList<BrandBean> brandList;
    private ArrayList<ClassBean> classList;
    private String manChooseType = "";

    //是否认证完成
    private boolean isApproveFinish = false;

    String state;

    private List<String> imgList = new ArrayList<>();

    @Override
    public int initLayout() {
        return R.layout.activity_true_name;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().approveList);
        manChooseType = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);

        state = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);

        //已认证
        if (state.equals(getString(R.string.has_checking))) {
            isApproveFinish = true;
            tvRight.setText("");
            //审核中
        } else if (state.equals(getString(R.string.checking))) {
            isApproveFinish = false;
        }


        switch (manChooseType) {
            //加工厂
            case "3":
                typeChoose = "3";
                tvTitle.setText("原片厂商销售员认证");
                break;
            //建筑商
            case "4":
                typeChoose = "4";
                tvTitle.setText("加工厂业务合伙人认证");
                break;
            //机械配件厂
            case "5":
                typeChoose = "5";
                tvTitle.setText("辅料业务合伙人认证");
                break;
            //机械配件厂
            case "6":
                typeChoose = "6";
                tvTitle.setText("建筑商业务合伙人认证");
                break;

        }

        tvRight.setText("提交");

        areaList = new ArrayList<>();
        brandList = new ArrayList<>();
        classList = new ArrayList<>();
        //获取负责类型
        YwyApproveFunction.getInstance().getYpCBA(this, new OnYwyApproveTypeCallBack() {
            @Override
            public void onSuccess(YwyApproveBean approveBean) {
                classList.addAll(approveBean.getReObj().getClassList());
                brandList.addAll(approveBean.getReObj().getBrandList());
                areaList.addAll(approveBean.getReObj().getAreaList());

                getYwyInfo();
            }

            @Override
            public void onFail() {
                getYwyInfo();
            }
        });

        //编辑完成输入框不可操作
        if (isApproveFinish) {
            EditTextUtils.getInstance().setUnEdit(etTrueName);
            EditTextUtils.getInstance().setUnEdit(etCardNo);
            EditTextUtils.getInstance().setUnEdit(etCompanyName);
            EditTextUtils.getInstance().setUnEdit(etLinkMail);
            EditTextUtils.getInstance().setUnEdit(etWebUrl);
        }
    }

    //获取认证数据
    public void getYwyInfo() {

        //获取认证数据
        YwyApproveFunction.getInstance().getYwyApproveData(this, new OnYwyApproveCallBack() {
            @Override
            public void onSuccess(YwyInfoObjBean objBean) {
                classType = objBean.getClassName();
                tvClassType.setText(objBean.getClassName() + "  ");
                brandType = objBean.getBrandName();
                tvBrandType.setText(objBean.getBrandName() + "  ");
                areaType = objBean.getAreaName();
                tvAreaType.setText(objBean.getAreaName() + "  ");
                trueName = objBean.getRealName();
                etTrueName.setText(objBean.getRealName());
                cardNo = objBean.getCardNo();
                etCardNo.setText(objBean.getCardNo());
                companyName = objBean.getCompany();
                etCompanyName.setText(objBean.getCompany());
                linkMail = objBean.getEmail();
                etLinkMail.setText(objBean.getEmail());
                companyWeb = objBean.getWeburl();
                etWebUrl.setText(objBean.getWeburl());

                try {
                    onCard = objBean.getCardPic().substring(0, objBean.getCardPic().indexOf(","));
                    backCard = objBean.getCardPic().substring(objBean.getCardPic().indexOf(",") + 1, objBean.getCardPic().length());


                    GlideUtils.getInstance().displayImage(onCard, YwyApproveActivity.this, ivUpImage);
                    GlideUtils.getInstance().displayImage(backCard, YwyApproveActivity.this, ivDownImage);
                    work_1 = objBean.getWorkPic().substring(0, objBean.getCardPic().indexOf(","));
                    work_2 = objBean.getWorkPic().substring(objBean.getWorkPic().indexOf(",") + 1, objBean.getWorkPic().length());
                    GlideUtils.getInstance().displayImage(work_1, YwyApproveActivity.this, ivWorkImage);
                    GlideUtils.getInstance().displayImage(work_2, YwyApproveActivity.this, ivWorkImageSecond);
                } catch (Exception e) {

                }


            }

            @Override
            public void onFail() {

            }
        });
    }


    @OnClick({R.id.tv_right, R.id.ll_back, R.id.iv_up_image, R.id.iv_down_image,
            R.id.iv_work_image, R.id.iv_work_second, R.id.rl_class_type, R.id.rl_brand_type,
            R.id.rl_area_type})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_right:

                if (!StringUtils.isNoEmpty(classType)) {
                    ToastUtils.getMineToast("请选择品类类型");
                    return;
                }

                if (!StringUtils.isNoEmpty(brandType)) {
                    ToastUtils.getMineToast("请选择品牌类型");
                    return;
                }

                if (!StringUtils.isNoEmpty(areaType)) {
                    ToastUtils.getMineToast("请选择地区");
                    return;
                }

                trueName = etTrueName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(trueName)) {
                    ToastUtils.getMineToast("请输入真实姓名");
                    return;
                }

                cardNo = etCardNo.getText().toString().trim();
                if (!StringUtils.isNoEmpty(cardNo)) {
                    ToastUtils.getMineToast("请输入身份证号");
                    return;
                }

                if(!CardUtils.isIdCard(cardNo)){
                    ToastUtils.getMineToast("请输入正确的身份证号");
                    return;
                }

                companyName = etCompanyName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(companyName)) {
                    ToastUtils.getMineToast("请输入企业名称");
                    return;
                }

                linkMail = etLinkMail.getText().toString().trim();
                if (!StringUtils.isNoEmpty(linkMail)) {
                    ToastUtils.getMineToast("请输入联系邮箱");
                    return;
                }

                companyWeb = etWebUrl.getText().toString().trim();
                if (!StringUtils.isNoEmpty(companyWeb)) {
                    ToastUtils.getMineToast("请输入公司官网");
                    return;
                }


//                if (!StringUtils.isNoEmpty(onCard)) {
//                    ToastUtils.getMineToast("请输入身份证正面照片");
//                    return;
//                }
//
//                if (!StringUtils.isNoEmpty(backCard)) {
//                    ToastUtils.getMineToast("请输入身份证反面照片");
//                    return;
//                }

                if (!StringUtils.isNoEmpty(work_1) && !StringUtils.isNoEmpty(work_2)) {
                    ToastUtils.getMineToast("至少选择一张工作证照片");
                    return;
                }


                CopyIosDialogUtils.getInstance().getIosDialog(this, "是否提交审核", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        YwyApproveFunction.getInstance().YwyApproveFun(YwyApproveActivity.this, typeChoose, classType, brandType, areaType,
                                trueName, cardNo, companyName, linkMail,
                                companyWeb, onCard + "," + backCard,
                                work_1 + "," + work_2, new ApproveCallBack() {
                                    @Override
                                    public void onApproveSuccess() {
                                        //认证成功
                                        ToastUtils.getMineToast(getString(R.string.approve_message));
                                        //退出该界面
                                        ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().approveList);
                                        //保存登录信息
                                        LoginFunction.getInstance().saveApproveInfo("审核中", typeChoose);
                                    }

                                    @Override
                                    public void onApproveFail() {
                                        //认证失败

                                    }
                                });
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });

                break;
            case R.id.ll_back:
                //退出该界面
                ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().approveList);
                break;
            case R.id.iv_up_image:
                if (isApproveFinish) {
                    toImageShow(0);
                    return;
                }
                PhotoUtils.getInstance().getPhotoSelectUtils(this, new PhotoCallBack() {
                    @Override
                    public void getPhoto(String path) {
                        onCard = path;
                        GlideUtils.getInstance().displayImage(path, YwyApproveActivity.this, ivUpImage);
                    }
                });
                break;
            case R.id.iv_down_image:
                if (isApproveFinish) {
                    toImageShow(1);
                    return;
                }
                PhotoUtils.getInstance().getPhotoSelectUtils(this, new PhotoCallBack() {
                    @Override
                    public void getPhoto(String path) {
                        backCard = path;
                        GlideUtils.getInstance().displayImage(path, YwyApproveActivity.this, ivDownImage);
                    }
                });
                break;
            case R.id.iv_work_image:
                if (isApproveFinish) {
                    toImageShow(2);
                    return;
                }
                PhotoUtils.getInstance().getPhotoSelectUtils(this, new PhotoCallBack() {
                    @Override
                    public void getPhoto(String path) {
                        work_1 = path;
                        GlideUtils.getInstance().displayImage(path, YwyApproveActivity.this, ivWorkImage);
                    }
                });
                break;
            case R.id.iv_work_second:
                if (isApproveFinish) {
                    toImageShow(3);
                    return;
                }
                PhotoUtils.getInstance().getPhotoSelectUtils(this, new PhotoCallBack() {
                    @Override
                    public void getPhoto(String path) {
                        work_2 = path;
                        GlideUtils.getInstance().displayImage(path, YwyApproveActivity.this, ivWorkImageSecond);
                    }
                });
                break;

            //负责品类
            case R.id.rl_class_type:
                if (isApproveFinish) {
                    return;
                }
                getData(tvClassType, "class", "选择品类");
                break;
            //负责品牌
            case R.id.rl_brand_type:
                if (isApproveFinish) {
                    return;
                }
                getData(tvBrandType, "brand", "选择品牌");
                break;
            //负责地区
            case R.id.rl_area_type:
                if (isApproveFinish) {
                    return;
                }
                getData(tvAreaType, "area", "选择地区");
                break;
        }
    }


    private void getData(final TextView tvType, final String type, String title) {
        ArrayList<String> chooseList = new ArrayList<>();
        chooseList.clear();

        switch (type) {
            case "class":
                int num = classList.size();
                for (int i = 0; i < num; i++) {
                    chooseList.add(classList.get(i).getClassName());
                }
                break;
            case "brand":
                int num2 = brandList.size();
                for (int i = 0; i < num2; i++) {
                    chooseList.add(brandList.get(i).getBrandName());
                }
                break;
            case "area":
                int num3 = areaList.size();
                for (int i = 0; i < num3; i++) {
                    chooseList.add(areaList.get(i).getAreaName());
                }
                break;
        }


        PickDialogUtils.getInstance().getChooseDialog(this, title, chooseList, new ChooseDialogCallBack() {
            @Override
            public void onClickSuccess(String choose) {
                tvType.setText(choose + "  ");
                switch (type) {
                    case "class":
                        classType = choose;
                        break;
                    case "brand":
                        brandType = choose;
                        break;
                    case "area":
                        areaType = choose;
                        break;
                }
            }
        });
    }

    //点击跳转到图片显示界面
    public void toImageShow(int position) {
        imgList.clear();
        imgList.add(onCard);
        imgList.add(backCard);
        if (StringUtils.isNoEmpty(work_1) && work_1.length() > 8) {
            imgList.add(work_1);
        }
        if (StringUtils.isNoEmpty(work_2) && work_2.length() > 8) {
            imgList.add(work_2);
        }

        IntentUtils.getInstance().toImageShowActivity(this, imgList, position);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().approveList);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
