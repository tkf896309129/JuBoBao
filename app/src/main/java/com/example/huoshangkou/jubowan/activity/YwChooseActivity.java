package com.example.huoshangkou.jubowan.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.baidu.idl.face.platform.FaceConfig;
import com.baidu.idl.face.platform.FaceEnvironment;
import com.baidu.idl.face.platform.FaceSDKManager;
import com.baidu.idl.face.platform.LivenessTypeEnum;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.YwChooseAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.CheckApplyBean;
import com.example.huoshangkou.jubowan.bean.CompanyQuaBean;
import com.example.huoshangkou.jubowan.bean.JbBtBean;
import com.example.huoshangkou.jubowan.bean.PnBankBean;
import com.example.huoshangkou.jubowan.bean.ReobjDBean;//
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.facebd.Config;
import com.example.huoshangkou.jubowan.facebd.ExampleApplication;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.inter.SuccessCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：YwChooseActivity
 * 描述：
 * 创建时间：2018-07-30  13:27
 */

public class YwChooseActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.grid_choose)
    GridView gridView;
    @Bind(R.id.tv_score_no)
    TextView tvScore;

    List<CheckApplyBean> applyBeanList = new ArrayList<>();
    private JbBtBean btBean;
    private YwChooseAdapter applyAdapter;

    //平安银行
//    private String url = "https://f.orangebank.com.cn/nscf/H5/index.html#/A01_infor1?inputChId=hhkhzfh&custMaId=hzxx";
    private String url = "";

    @Override
    public int initLayout() {
        return R.layout.activity_bank_choose;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("业务选择");
        ivRight.setVisibility(View.GONE);

        applyAdapter = new YwChooseAdapter(this, applyBeanList, R.layout.item_yw_choose);
        gridView.setAdapter(applyAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (applyBeanList.get(i).getTypeId()) {
                    case 1:
//                        DialogUtils.getInstance().toFaceCheck(YwChooseActivity.this, "您接下来要进行人脸识别验证，验证成功后可进行资料填写！", new SuccessCallBack() {
//                            @Override
//                            public void onSuccess() {
//                                IntentUtils.getInstance().toActivity(YwChooseActivity.this, FaceLivenessExpActivity.class, "dianFuApply", url);
//                            }
//
//                            @Override
//                            public void onFail() {
//
//                            }
//                        });
                        IntentUtils.getInstance().toActivity(YwChooseActivity.this, DianFuNewActivity.class);
                        break;
                    case 2:
                        DialogUtils.getInstance().toFaceCheck(YwChooseActivity.this, "您接下来要进行人脸识别验证，验证成功后可进行资料填写！", new SuccessCallBack() {
                            @Override
                            public void onSuccess() {
                                if (ContextCompat.checkSelfPermission(YwChooseActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(YwChooseActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
                                } else {
                                    IntentUtils.getInstance().toActivity(YwChooseActivity.this, FaceLivenessExpActivity.class, "pinAnBank", url);
                                }
                            }

                            @Override
                            public void onFail() {

                            }
                        });
//                        IntentUtils.getInstance().toActivity(YwChooseActivity.this, OpenglActivity.class, "pinAnBank", url);
                        break;
                    case 3:
                        IntentUtils.getInstance().toActivity(YwChooseActivity.this, FengKongActivity.class);
                        break;
                    case 4:
                        if (btBean != null) {
                            switch (btBean.getSuccess()) {
                                /**
                                 *
                                 1 用户已有白条额度
                                 2 白条已过期
                                 3 用户正在申请白条且已绑定银行卡
                                 4 用户正在申请白条但未绑定银行卡
                                 5 用户提交的白条申请未通过审核
                                 6 用户有未全额还款的白条订单
                                 7 用户没有白条且没有聚玻贷或聚玻贷已过期
                                 8 用户有聚玻贷额度 但没有聚玻贷欠款
                                 9 用户有聚玻贷欠款
                                 11（待提交公司信息与聚玻白条协议签署）
                                 10（待审核、此时不再直接进入白条页面，提示待审核）
                                 */
                                //已经有白条额度
                                case 1:
                                    IntentUtils.getInstance().toActivity(YwChooseActivity.this, JuBoBtEdActivity.class, btBean);
                                    break;
                                case 2:
                                    DialogUtils.getInstance().btUseCheck(YwChooseActivity.this, "您的聚玻白条已过期，请您重走流程申请额度。");
                                    break;
                                case 3:
                                    DialogUtils.getInstance().priceCheckDialog(YwChooseActivity.this, new StringCallBack() {
                                        //验证金额
                                        @Override
                                        public void onSuccess(String str) {
                                            checkMoney("4", str);
                                        }

                                        @Override
                                        public void onFail() {

                                        }
                                    });
                                    break;
                                case 4:
                                    break;
                                case 5:
                                    DialogUtils.getInstance().btUseCheck(YwChooseActivity.this, "您的聚玻白条审核未通过，请您重新填写资料");
                                    break;
                                case 6:
                                    IntentUtils.getInstance().toActivity(YwChooseActivity.this, JuBoBtEdActivity.class, btBean);
                                    break;
                                case 7:
                                    IntentUtils.getInstance().toActivity(YwChooseActivity.this, JuBoBtActivity.class);
                                    break;
                                case 8:
                                    DialogUtils.getInstance().backJbdDialog(YwChooseActivity.this);
                                    break;
                                case 9://
                                    DialogUtils.getInstance().hadKnow(YwChooseActivity.this, "系统检测到你当前为聚玻贷用户，且有部分贷款 未还清，请还清聚玻贷后使用白条！");
                                    break;
                                case 10:
                                    DialogUtils.getInstance().hadKnow(YwChooseActivity.this, "");
                                    break;
                                case 11:
                                    IntentUtils.getInstance().toActivity(YwChooseActivity.this, CompanyInfoInputActivity.class);
                                    break;
                            }
                        }
                        break;
                }
            }
        });

        // 根据需求添加活体动作
        ExampleApplication.livenessList.clear();
        ExampleApplication.livenessList.add(LivenessTypeEnum.Eye);
        ExampleApplication.livenessList.add(LivenessTypeEnum.Mouth);
//        ExampleApplication.livenessList.add(LivenessTypeEnum.HeadUp);
//        ExampleApplication.livenessList.add(LivenessTypeEnum.HeadDown);
//        ExampleApplication.livenessList.add(LivenessTypeEnum.HeadLeft);
//        ExampleApplication.livenessList.add(LivenessTypeEnum.HeadRight);
        ExampleApplication.livenessList.add(LivenessTypeEnum.HeadLeftOrRight);
        FaceSDKManager.getInstance().initialize(this, Config.licenseID, Config.licenseFileName);
        setFaceConfig();
    }

    private void setFaceConfig() {
        FaceConfig config = FaceSDKManager.getInstance().getFaceConfig();
        // SDK初始化已经设置完默认参数（推荐参数），您也根据实际需求进行数值调整
        config.setLivenessTypeList(ExampleApplication.livenessList);
        config.setLivenessRandom(ExampleApplication.isLivenessRandom);
        config.setBlurnessValue(FaceEnvironment.VALUE_BLURNESS);
        config.setBrightnessValue(FaceEnvironment.VALUE_BRIGHTNESS);
        config.setCropFaceValue(FaceEnvironment.VALUE_CROP_FACE_SIZE);
        config.setHeadPitchValue(FaceEnvironment.VALUE_HEAD_PITCH);
        config.setHeadRollValue(FaceEnvironment.VALUE_HEAD_ROLL);
        config.setHeadYawValue(FaceEnvironment.VALUE_HEAD_YAW);
        config.setMinFaceSize(FaceEnvironment.VALUE_MIN_FACE_SIZE);
        config.setNotFaceValue(FaceEnvironment.VALUE_NOT_FACE_THRESHOLD);
        config.setOcclusionValue(FaceEnvironment.VALUE_OCCLUSION);
        config.setCheckFaceQuality(true);
        config.setFaceDecodeNumberOfThreads(2);

        FaceSDKManager.getInstance().setFaceConfig(config);
    }

    public void getData(CompanyQuaBean quaBean) {
        applyBeanList.clear();
        CompanyQuaBean.DBean.DataBean data = quaBean.getD().getData();
        CheckApplyBean bxBean = new CheckApplyBean();
        bxBean.setImgType(R.mipmap.dian_fu_new)
                .setType("垫付款")
                .setTypeId(1);
        if (data.getCompanyNature() == 2) {
            applyBeanList.add(bxBean);
        }
        CheckApplyBean ykBean = new CheckApplyBean()
                .setImgType(R.mipmap.pn_bank)
                .setType("平安银行数据贷")
                .setTypeId(2);
        applyBeanList.add(ykBean);
        CheckApplyBean ywApply = new CheckApplyBean().setImgType(R.mipmap.jb_feng_kong)
                .setType("聚玻风控")
                .setTypeId(3);
//        if (PersonConstant.getInstance().getRoleType(this).equals("2")) {
        applyBeanList.add(ywApply);
//        }
        CheckApplyBean qjBean = new CheckApplyBean().setImgType(R.mipmap.jb_bt)
                .setType("聚玻白条")
                .setTypeId(4);
        applyBeanList.add(qjBean);
        applyAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }

    //判断验证金额
    public void checkMoney(String type, final String price) {
        OkhttpUtil.getInstance().setUnCacheData(this, "金额验证中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().SUBMISSION
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().MONEY + "=" + price + "&"
                + FieldConstant.getInstance().TYPE + "=" + type, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
//                    ToastUtils.getMineToast("恭喜您验证成功");
                    DialogUtils.getInstance().hadKnow(YwChooseActivity.this, "恭喜您验证成功,请等待审核");
//                    IntentUtils.getInstance().toActivity(YwChooseActivity.this, CompanyInfoInputActivity.class);
                } else {
                    CopyIosDialogUtils.getInstance().getErrorDialog(YwChooseActivity.this, successBean.getErrMsg(), new CopyIosDialogUtils.ErrDialogCallBack() {
                        @Override
                        public void confirm() {

                        }
                    });
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    //判断当前用户的白条资格
    public void btQua() {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(this, UrlConstant.getInstance().URL + PostConstant.getInstance().BT_QUA_GET
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                LogUtils.e(json);
                btBean = JSON.parseObject(json, JbBtBean.class);
            }

            @Override
            public void onFail() {

            }
        });
    }


    //帮助中心权限
    public void getQuaHelp() {
        Map<String, String> map = new HashMap<>();
        map.put("userId", PersonConstant.getInstance().getUserId());
        String json = JSON.toJSONString(map);
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().ABOUT_US_MANAGE + "GetUserJurisdictionInfo", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                //CompanyNature:用户公司性质  （-1 未知，1 聚玻网旗下公司，2客户公司）
                //UserJurisdiction：用户权限（-1 权限未知，0普通成员，1管理员，2超级管理员）

                CompanyQuaBean quaBean = JSON.parseObject(str, CompanyQuaBean.class);
                getData(quaBean);

            }

            @Override
            public void onFail() {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                IntentUtils.getInstance().toActivity(YwChooseActivity.this, FaceLivenessExpActivity.class, "pinAnBank", url);
                break;

        }
    }


    //我的信用评分
    public void getMyScore() {
        OkHttpUtils.get().url(UrlConstant.getInstance().URL + "GetAssessScoreByUserID/?UserID=" + PersonConstant.getInstance().getUserId()).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                ReobjDBean dBean = JSON.parseObject(response, ReobjDBean.class);
                SpannableStringBuilder spannableString = new SpannableStringBuilder();
                String str = dBean.getReObj() + "分";
                spannableString.append(str);
                //字体大小
                AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(28);
                spannableString.setSpan(sizeSpan, str.length() - 1, str.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                tvScore.setText(spannableString);
//                tvScore.setText("信用评分：" + (dBean.getReObj() == 0 ? "未评分" : dBean.getReObj() + "分"));
            }
        });
    }

    //获取平安银行地址
    public void getPinAnBankUrl() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", PersonConstant.getInstance().getUserId());
        String json = JSON.toJSONString(map);
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().ABOUT_US_MANAGE + "GetUserPingAnFinanceJurisdictionInfo", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                PnBankBean pnBankBean = JSON.parseObject(str, PnBankBean.class);
                url = pnBankBean.getD().getData();
            }

            @Override
            public void onFail() {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        btQua();
        getMyScore();
        getQuaHelp();
        getPinAnBankUrl();
    }
}
