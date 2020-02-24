package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.DianFuBean;
import com.example.huoshangkou.jubowan.bean.SuccessDBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：DianFuNewActivity
 * 描述：
 * 创建时间：2019-10-08  08:51
 */

public class DianFuNewActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_up_image)
    ImageView ivUpImage;
    @Bind(R.id.iv_down_image)
    ImageView ivDownImage;
    @Bind(R.id.iv_work_image)
    ImageView ivWorkImage;
    @Bind(R.id.tv_company_name)
    TextView tvCompanyName;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.tv_card_no)
    TextView tvCardNo;
    @Bind(R.id.tv_social_no)
    TextView tvSocialNo;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.et_phone)
    EditText etPhone;

    //身份证正面
    private String onCard = "";
    //身份证反面
    private String downCard = "";
    //营业执照
    private String workPic = "";
    private String companyId = "";
    private String phone = "";
    private List<String> imgList = new ArrayList<>();

    @Override
    public int initLayout() {
        return R.layout.activity_dian_fu_new;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("垫付款申请");
        tvRight.setText("提交");
        getMessage();
    }

    @OnClick({R.id.ll_back, R.id.tv_right, R.id.iv_up_image, R.id.iv_down_image, R.id.iv_work_image})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_right:
                phone = etPhone.getText().toString().trim();
                if (!StringUtils.isNoEmpty(phone)) {
                    ToastUtils.getMineToast("请填写授权手机号");
                    return;
                }
                CopyIosDialogUtils.getInstance().getIosDialog(this, "是否提交申请", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        onCommit();
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
                break;
            case R.id.iv_up_image:
                toImageShow(0);
                break;
            case R.id.iv_down_image:
                toImageShow(1);
                break;
            case R.id.iv_work_image:
                toImageShow(2);
                break;
            case R.id.ll_back:
                this.finish();
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

    //获取垫付款资质信息
    public void getMessage() {
        Map<String, String> map = new HashMap<>();
        map.put("userId", PersonConstant.getInstance().getUserId());
        String json = JSON.toJSONString(map);
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().PADPAY_MANAGE + "GetPadPaymentUnitInfo", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                DianFuBean dianFuBean = JSON.parseObject(str, DianFuBean.class);
                DianFuBean.DBean.ReObjBean reObj = dianFuBean.getD().getReObj();
                tvCompanyName.setText(StringUtils.getNoEmptyStr(reObj.getCompany()));
                tvName.setText(StringUtils.getNoEmptyStr(reObj.getLegalPerson()));
                tvPhone.setText(StringUtils.getNoEmptyStr(reObj.getLinkTel()));
                tvCardNo.setText(StringUtils.getNoEmptyStr(reObj.getIDNumber()));
                tvSocialNo.setText(StringUtils.getNoEmptyStr(reObj.getSocialCreditCode()));
                tvAddress.setText(StringUtils.getNoEmptyStr(reObj.getAddress()));
                if (StringUtils.isNoEmpty(reObj.getIDCardPic())) {
                    String[] split = reObj.getIDCardPic().split(",");
                    if (split != null && split.length == 2) {
                        onCard = split[0];
                        downCard = split[1];
                    }
                }

                GlideUtils.getInstance().displayImage(onCard, getContext(), ivUpImage);
                GlideUtils.getInstance().displayImage(downCard, getContext(), ivDownImage);
                workPic = reObj.getBusinessLicensePic();
                GlideUtils.getInstance().displayImage(workPic, getContext(), ivWorkImage);
                companyId = reObj.getId() + "";
            }

            @Override
            public void onFail() {

            }
        });
    }

    //提交
    public void onCommit() {
        Map<String, String> map = new HashMap<>();
        map.put("companyId", companyId);
        map.put("userId", PersonConstant.getInstance().getUserId());
        map.put("authorizedMobile", phone);
        String json = JSON.toJSONString(map);
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().PADPAY_MANAGE + "AddPadPaymentApply", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                SuccessDBean dBean = JSON.parseObject(str, SuccessDBean.class);
                if (dBean.getD().getSuccess() != -1) {
                    ToastUtils.getMineToast("提交成功");
                } else {
                    ToastUtils.getMineToast(dBean.getD().getErrMsg());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }
}
