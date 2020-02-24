package com.example.huoshangkou.jubowan.activity;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.BuyApproveFunction;
import com.example.huoshangkou.jubowan.activity.function.LoginFunction;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.BuyInfoObjBean;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.inter.ApproveCallBack;
import com.example.huoshangkou.jubowan.inter.OnBuyInfoCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CardUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.EditTextUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：BuyApproveActivity
 * 描述：加工厂认证界面
 * 创建时间：2017-03-01  14:40
 */

public class BuyApproveActivity extends BaseActivity {
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

    @Bind(R.id.et_true_name)
    EditText etTrueName;
    @Bind(R.id.et_card_no)
    EditText etCardNo;
    @Bind(R.id.et_company_name)
    EditText etCompanyName;
    @Bind(R.id.et_company_address)
    EditText etCompanyAddress;
    @Bind(R.id.et_e_mail)
    EditText etEMail;
    @Bind(R.id.et_company_web)
    EditText etCompanyWeb;
    //机构代码
    @Bind(R.id.et_zzjg_code)
    EditText etJgCode;

    //身份选择
    private String typeChoose = "";
    //真实姓名
    private String trueName = "";
    //身份证号
    private String cardNo = "";
    //公司名称
    private String companyName = "";
    //公司地址
    private String companyAddress = "";
    //联系邮箱
    private String linkMail = "";
    //公司官网
    private String companyWeb = "";
    //身份证正面
    private String onCard = "";
    //身份证反面
    private String downCard = "";
    //营业执照
    private String workPic = "";
    //组织机构代码
    private String jgCode = "";

    private String manChooseType = "";

    //身份选择
    ArrayList<String> manChoose;

    private List<String> imgList = new ArrayList<>();

    //是否审核完成
    private boolean isApproveFinish = false;
    //审核 状态
    private String state = "";

//    String str = "http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20161021/20161021133134_3816.jpg";

    @Override
    public int initLayout() {
        return R.layout.activity_factory;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().approveList);
        tvRight.setText("保存");

        manChoose = new ArrayList<>();
        manChoose.add("建筑商");
        manChoose.add("加工厂");

        state = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        manChooseType = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);

        LogUtils.i(state+"  "+manChooseType);

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
            case "2":
                typeChoose = "2";
                tvTitle.setText("加工厂认证");
                break;
            //建筑商
            case "1":
                typeChoose = "1";
                tvTitle.setText("建筑商认证");
                break;
            //机械配件厂
            case "8":
                typeChoose = "8";
                tvTitle.setText("机械配件厂认证");
                break;
            //个体工商户
            case "9":
                typeChoose = "9";
                tvTitle.setText("个体工商户认证");
                break;
        }

        //获取买家信息
        BuyApproveFunction.getInstance().getBuyData(this, new OnBuyInfoCallBack() {
            @Override
            public void onBuySuccess(BuyInfoObjBean objBean) {
                trueName = objBean.getLinkman();
                etTrueName.setText(trueName);

                cardNo = objBean.getLinkmancardno();
                etCardNo.setText(cardNo);

                companyName = objBean.getCompany();
                etCompanyName.setText(companyName);

                companyAddress = objBean.getAddress();
                etCompanyAddress.setText(companyAddress);

                linkMail = objBean.getEmail();
                etEMail.setText(linkMail);

                companyWeb = objBean.getWeburl();
                etCompanyWeb.setText(companyWeb);

                jgCode = objBean.getZzjgno();
                etJgCode.setText(jgCode);

                try {
                    String[] split = objBean.getLinkmancardpic().split(",");
//                    String[] split = str.split(",");
                    if (split != null) {
                        if (split[0] != null) {
                            onCard = split[0];
                        }
                        if (split[1] != null) {
                            downCard = split[1];
                        }
                    }
                } catch (Exception e) {

                }

//                onCard = objBean.getLinkmancardpic().substring(0, objBean.getLinkmancardpic().indexOf(","));
//                downCard = objBean.getLinkmancardpic().substring(objBean.getLinkmancardpic().indexOf(",") + 1, objBean.getLinkmancardpic().length());


                GlideUtils.getInstance().displayImage(onCard, BuyApproveActivity.this, ivUpImage);
                GlideUtils.getInstance().displayImage(downCard, BuyApproveActivity.this, ivDownImage);
                workPic = objBean.getPicyyzz();
                GlideUtils.getInstance().displayImage(workPic, BuyApproveActivity.this, ivWorkImage);
            }
        });

        //审核结束
        if (isApproveFinish) {
            EditTextUtils.getInstance().setUnEdit(etCardNo);
            EditTextUtils.getInstance().setUnEdit(etCompanyAddress);
            EditTextUtils.getInstance().setUnEdit(etCompanyName);
            EditTextUtils.getInstance().setUnEdit(etCompanyWeb);
            EditTextUtils.getInstance().setUnEdit(etEMail);
            EditTextUtils.getInstance().setUnEdit(etJgCode);
            EditTextUtils.getInstance().setUnEdit(etTrueName);
        }
    }

    @OnClick({R.id.ll_back, R.id.iv_up_image, R.id.iv_down_image, R.id.iv_work_image, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                //退出该界面
                ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().approveList);
                break;
            case R.id.iv_up_image:
                if (isApproveFinish) {
                    toImageShow(0);
                    return;
                }
                CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "请务必保证身份证清晰并且竖直拍摄", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        PhotoUtils.getInstance().getPhotoSelectUtils(BuyApproveActivity.this, new PhotoCallBack() {
                            @Override
                            public void getPhoto(String path) {
                                onCard = path;
                                GlideUtils.getInstance().displayImage(onCard, BuyApproveActivity.this, ivUpImage);
                            }
                        });
                    }

                    @Override
                    public void cancelBtn() {

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
                        downCard = path;
                        GlideUtils.getInstance().displayImage(downCard, BuyApproveActivity.this, ivDownImage);
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
                        workPic = path;
                        GlideUtils.getInstance().displayImage(workPic, BuyApproveActivity.this, ivWorkImage);
                    }
                });
                break;
            //保存修改
            case R.id.tv_right:
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
                if (!CardUtils.isIdCard(cardNo)) {
                    ToastUtils.getMineToast("请输入正确的身份证号");
                    return;
                }
                companyName = etCompanyName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(companyName)) {
                    ToastUtils.getMineToast("请输入公司名称");
                    return;
                }
                companyAddress = etCompanyAddress.getText().toString().trim();
                if (!StringUtils.isNoEmpty(companyAddress)) {
                    ToastUtils.getMineToast("请输入公司地址");
                    return;
                }
                linkMail = etEMail.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(linkMail)) {
//                    ToastUtils.getMineToast("请输入联系邮箱");
//                    return;
//                }
                companyWeb = etCompanyWeb.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(companyWeb)) {
//                    ToastUtils.getMineToast("请输入公司官网");
//                    return;
//                } t_1514450518233_0241  18258469343  kekai66667777
                jgCode = etJgCode.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(jgCode)) {
//                    ToastUtils.getMineToast("请输入组织机构代码");
//                    return;
//                }

                CopyIosDialogUtils.getInstance().getIosDialog(this, getString(R.string.is_commit_approve), new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        BuyApproveFunction.getInstance().onBuyApprove(BuyApproveActivity.this, typeChoose, trueName, cardNo, companyName, companyAddress,
                                linkMail, companyWeb, onCard, downCard, workPic, jgCode, new ApproveCallBack() {
                                    @Override
                                    public void onApproveSuccess() {
                                        ToastUtils.getMineToast(getString(R.string.approve_message));
                                        ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().approveList);
                                        //保存身份信息
                                        LoginFunction.getInstance().saveApproveInfo("审核中", typeChoose);
                                    }

                                    @Override
                                    public void onApproveFail() {

                                    }
                                });
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
                break;
        }
    }

    //点击跳转到图片显示界面
    public void toImageShow(int position) {
        imgList.clear();
        imgList.add(onCard);
        imgList.add(downCard);
        imgList.add(workPic);

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
