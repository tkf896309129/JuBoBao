package com.example.huoshangkou.jubowan.activity;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.LoginFunction;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.PersonBusBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.EditTextUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：PersonBusinessApprove
 * 描述：
 * 创建时间：2018-02-28  15:23
 */

public class PersonBusinessApprove extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.et_true_name)
    EditText etTrueName;
    @Bind(R.id.et_card_no)
    EditText etCardNo;
    @Bind(R.id.et_company_name)
    EditText etCompanyName;
    @Bind(R.id.et_company_address)
    EditText etCompanyAddress;
    @Bind(R.id.et_zzjg_code)
    EditText etZzjgCode;
    @Bind(R.id.iv_pic_3)
    ImageView ivPic3;
    @Bind(R.id.iv_work_image)
    ImageView ivWorkImage;
    @Bind(R.id.iv_up_image)
    ImageView ivUpImg;
    @Bind(R.id.iv_down_image)
    ImageView ivDownImg;

    private String manChooseType = "";
    private String state = "";
    //是否审核完成
    private boolean isApproveFinish = false;

    //营业执照
    private String workImg = "";
    //经营者姓名
    private String name = "";
    //身份证号
    private String idCard = "";
    //个体工商户名称
    private String personBusName = "";
    //地址
    private String address = "";
    //组织机构代码
    private String zzjgCode = "";

    private String upCard = "";
    private String downCard = "";

    private List<String> imgList = new ArrayList<>();

    @Override
    public int initLayout() {
        return R.layout.activity_person_business;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().approveList);
        tvRight.setText("提交");
        tvTitle.setText("个体工商户认证");
        state = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        manChooseType = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);

        //已认证
        if (state.equals(getString(R.string.has_checking))) {
            isApproveFinish = true;
            tvRight.setText("");
            //审核中
        } else if (state.equals(getString(R.string.checking))) {
            isApproveFinish = false;
        }

        getApproveMessage();

        //审核结束
        if (isApproveFinish) {
            EditTextUtils.getInstance().setUnEdit(etCardNo);
            EditTextUtils.getInstance().setUnEdit(etCompanyAddress);
            EditTextUtils.getInstance().setUnEdit(etCompanyName);
            EditTextUtils.getInstance().setUnEdit(etZzjgCode);
            EditTextUtils.getInstance().setUnEdit(etTrueName);
        }
    }

    @OnClick({R.id.ll_back, R.id.iv_work_image, R.id.tv_right, R.id.iv_up_image, R.id.iv_down_image})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_work_image:
                PhotoUtils.getInstance().getPhotoSelectUtils(this, new PhotoCallBack() {
                    @Override
                    public void getPhoto(String path) {
                        workImg = path;
                        GlideUtils.getInstance().displayImage(path, PersonBusinessApprove.this, ivWorkImage);
                    }
                });
                break;
            case R.id.ll_back:
                ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().approveList);
                break;
            case R.id.tv_right:
                name = etTrueName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(name)) {
                    ToastUtils.getMineToast("请输入经营者姓名");
                    return;
                }

                idCard = etCardNo.getText().toString().trim();
                if (!StringUtils.isNoEmpty(idCard)) {
                    ToastUtils.getMineToast("请输入身份证号");
                    return;
                }
                personBusName = etCompanyName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(personBusName)) {
                    ToastUtils.getMineToast("请输入个体工商户名称");
                    return;
                }
                address = etCompanyAddress.getText().toString().trim();
                if (!StringUtils.isNoEmpty(address)) {
                    ToastUtils.getMineToast("请输入地址");
                    return;
                }

                zzjgCode = etZzjgCode.getText().toString().trim();
                if (!StringUtils.isNoEmpty(zzjgCode)) {
                    ToastUtils.getMineToast("请输入注册号/社会信用代码");
                    return;
                }

                if (!StringUtils.isNoEmpty(workImg) || workImg.length() < 10) {
                    ToastUtils.getMineToast("请上传营业执照照片");
                    return;
                }

//                if (!StringUtils.isNoEmpty(upCard) || !StringUtils.isNoEmpty(downCard)) {
//                    ToastUtils.getMineToast("请上传身份证照片");
//                    return;
//                }

                putApproveMessage();
                break;
            case R.id.iv_up_image:
                if (isApproveFinish) {
                    toImageShow(0);
                    return;
                }
                CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "请务必保证身份证清晰并且竖直拍摄", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        PhotoUtils.getInstance().getPhotoSelectUtils(PersonBusinessApprove.this, new PhotoCallBack() {
                            @Override
                            public void getPhoto(String path) {
                                upCard = path;
                                GlideUtils.getInstance().displayImage(path, PersonBusinessApprove.this, ivUpImg);
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
                        GlideUtils.getInstance().displayImage(path, PersonBusinessApprove.this, ivDownImg);
                    }
                });
                break;
        }
    }


    //获取认证信息
    public void getApproveMessage() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), "数据加载中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_PB
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                PersonBusBean busBean = JSON.parseObject(json, PersonBusBean.class);
                etTrueName.setText(busBean.getReObj().getLinkman());
                etCardNo.setText(busBean.getReObj().getLinkmancardno());
                etCompanyName.setText(busBean.getReObj().getCompany());
                etCompanyAddress.setText(busBean.getReObj().getAddress());
                etZzjgCode.setText(busBean.getReObj().getZzjgno());
                workImg = busBean.getReObj().getPicyyzz();
                LogUtils.i("workImg：" + workImg);
                GlideUtils.getInstance().displayImage(busBean.getReObj().getPicyyzz(), getContext(), ivWorkImage);

                try {
                    String[] split = busBean.getReObj().getLinkmancardpic().split(",");
//                    String[] split = str.split(",");
                    if (split != null) {
                        if (split[0] != null) {
                            upCard = split[0];
                        }
                        if (split[1] != null) {
                            downCard = split[1];
                        }
                    }
                } catch (Exception e) {

                }
                GlideUtils.getInstance().displayImage(upCard, PersonBusinessApprove.this, ivUpImg);
                GlideUtils.getInstance().displayImage(downCard, PersonBusinessApprove.this, ivDownImg);
            }

            @Override
            public void onFail() {

            }
        });
    }

    //提交认证信息
    public void putApproveMessage() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), "数据加载中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().SET_PB
                + FieldConstant.getInstance().LINK_CARD_NO + "=" + idCard + "&"
                + FieldConstant.getInstance().LINK_MAN + "=" + EncodeUtils.getInstance().getEncodeString(name) + "&"
                + FieldConstant.getInstance().COMPANY + "=" + EncodeUtils.getInstance().getEncodeString(personBusName) + "&"
                + FieldConstant.getInstance().JG_CODE + "=" + EncodeUtils.getInstance().getEncodeString(zzjgCode) + "&"
                + FieldConstant.getInstance().ADDRESS + "=" + EncodeUtils.getInstance().getEncodeString(address) + "&"
                + FieldConstant.getInstance().YYZZ_PIC + "=" + EncodeUtils.getInstance().getEncodeString(workImg) + "&"
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().LINK_MAN_CARD + "=" + upCard + "," + downCard, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    //认证成功
                    ToastUtils.getMineToast(getString(R.string.approve_message));
                    //退出该界面
                    ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().approveList);
                    //保存登录信息
                    LoginFunction.getInstance().saveApproveInfo("审核中", manChooseType);
                } else {
                    ToastUtils.getMineToast(successBean.getErrMsg());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    //点击跳转到图片显示界面
    public void toImageShow(int position) {
        imgList.clear();
        imgList.add(upCard);
        imgList.add(downCard);
        imgList.add(workImg);

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
