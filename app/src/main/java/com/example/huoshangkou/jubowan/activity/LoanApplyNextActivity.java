package com.example.huoshangkou.jubowan.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnTimeCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.MoneyFormatUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.TimeDialogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：LoanApplyNextActivity
 * 描述：
 * 创建时间：2017-09-11  10:34
 */

public class LoanApplyNextActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_small_write)
    EditText etSmallWrite;
    @Bind(R.id.tv_large_write)
    TextView tvLargeWrite;
    @Bind(R.id.et_loan_from)
    EditText etLoanFrom;

    @Bind(R.id.iv_work_card)
    ImageView imgWorkCard;
    @Bind(R.id.grid_more_img)
    ScrollGridView gridView;

    @Bind(R.id.tv_time_1)
    TextView tvTime1;
    @Bind(R.id.tv_time_2)
    TextView tvTime2;
    @Bind(R.id.tv_time_3)
    TextView tvTime3;
    //图片
    List<String> imgList;
    //图片适配器
    ImageAddAdapter imageAddAdapter;
    @Bind(R.id.et_money_type)
    TextView etMoneyType;
    @Bind(R.id.et_loan_type)
    TextView etLoanType;
    @Bind(R.id.et_price_1)
    EditText etPrice1;
    @Bind(R.id.et_price_2)
    EditText etPrice2;
    @Bind(R.id.et_price_3)
    EditText etPrice3;
    
    //出借人
    private String borrowName = "";
    //借款人
    private String loanName = "";
    //身份证号
    private String idCard = "";
    //公司名称
    private String companyName = "";
    //公司地址
    private String companyAddress = "";
    //公司厂房
    private String companyHouse = "";
    //现有设备
    private String tools = "";
    //公司规模
    private String companyStandard = "";
    //原欠贷款余额
    private String loanExtra = "";
    //逾期贷款余额
    private String loanOut = "";
    //结欠利息
    private String loanMoney = "";
    //申请用款金额
    private String useMoney = "";
    //借款用途
    private String moneyType = "";
    //还款来源
    private String loanFrom = "";
    //营业执照 
    private String workPic = "";
    //附件
    private String juJian = "";

    private String time_1;
    private String time_2;
    private String time_3;

    private String price_1;
    private String price_2;
    private String price_3;

    //获取到的内容
    private String str = "";
    //附件
    private String fuJian = "";

    //计划还款日期,计划还款金额;计划还款日期,计划还款金额
    private String backMoney = "";

    @Override
    public int initLayout() {
        return R.layout.activity_loan_next;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {

        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().activityLoan);
        tvTitle.setText("贷款申请");
        imgList = new ArrayList<>();
        imageAddAdapter = new ImageAddAdapter(getContext(), imgList);
        gridView.setAdapter(imageAddAdapter);
//        String str = phone + "," + orderId + "," + borrowName + ","
//                + loanName + "," + idCard + "," + companyName + "," + companyHouse + "," + companyStandard
//                + "," + loanExtra + "," + loanOut + "," + loanMoney;
        str = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        String[] split = str.split(",");


        borrowName = split[0];
        loanName = split[1];
        idCard = split[2];
        companyName = split[3];
        companyHouse = split[4];
        companyStandard = split[5];
        loanExtra = split[6];
        loanOut = split[7];
        loanMoney = split[8];
        companyAddress = split[9];
        tools = split[10];

        etSmallWrite.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!StringUtils.isNoEmpty(charSequence.toString())) {
                    tvLargeWrite.setText("");
                    return;
                }
                tvLargeWrite.setText(MoneyFormatUtils.toChineseCharI(charSequence.toString()));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @OnClick({R.id.ll_back, R.id.iv_work_card, R.id.tv_more_photo,
            R.id.tv_time_1, R.id.tv_time_2, R.id.tv_time_3, R.id.tv_commit_apply})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.iv_work_card:
                PhotoUtils.getInstance().getPhotoSelectUtils(LoanApplyNextActivity.this, new PhotoCallBack() {
                    @Override
                    public void getPhoto(String path) {
                        workPic = path;
                        GlideUtils.getInstance().displayImage(path, getContext(), imgWorkCard);
                    }
                });
                break;
            case R.id.tv_more_photo:
                PhotoUtils.getInstance().getMoreLocalPhoto(LoanApplyNextActivity.this, 9, new PhotoCallBack() {
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
                        fuJian = PhotoUtils.getInstance().getImageStr(imgList);
                        imageAddAdapter.notifyDataSetChanged();
                    }
                });
                break;
            case R.id.tv_time_1:
                TimeDialogUtils.getInstance().getTime(getContext(), new OnTimeCallBack() {
                    @Override
                    public void getYearTime(String year) {
                        time_1 = year;
                        tvTime1.setText(year);
                    }

                    @Override
                    public void getMinuteTime(String minute) {

                    }
                });
                break;
            case R.id.tv_time_2:
                TimeDialogUtils.getInstance().getTime(getContext(), new OnTimeCallBack() {
                    @Override
                    public void getYearTime(String year) {
                        time_2 = year;
                        tvTime2.setText(year);
                    }

                    @Override
                    public void getMinuteTime(String minute) {

                    }
                });
                break;
            case R.id.tv_time_3:
                TimeDialogUtils.getInstance().getTime(getContext(), new OnTimeCallBack() {
                    @Override
                    public void getYearTime(String year) {
                        time_3 = year;
                        tvTime3.setText(year);
                    }

                    @Override
                    public void getMinuteTime(String minute) {

                    }
                });
                break;
            case R.id.tv_commit_apply:
                useMoney = etSmallWrite.getText().toString().trim();
                if (!StringUtils.isNoEmpty(useMoney)) {
                    ToastUtils.getMineToast("请输入用款金额");
                    return;
                }

                moneyType = etMoneyType.getText().toString().trim();
                if (!StringUtils.isNoEmpty(moneyType)) {
                    ToastUtils.getMineToast("请输入借款用途");
                    return;
                }

                if (!StringUtils.isNoEmpty(workPic)) {
                    ToastUtils.getMineToast("请上传营业执照");
                    return;
                }



//                loanFrom = etLoanFrom.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(loanFrom)) {
//                    ToastUtils.getMineToast("请输入借款来源");
//                    return;
//                }

//                backMoney = time_1 + "," + price_1 + ";" + time_2 + "," + price_2 + ";" + time_3 + "," + price_3 + ";";

                PhotoUtils.getInstance().mutilLocalImageUp(imgList, this, new StringCallBack() {
                    @Override
                    public void onSuccess(String str) {
                        fuJian = str;
                        addLoanApply();
                    }

                    @Override
                    public void onFail() {

                    }
                });
                break;
        }
    }

    //申请贷款接口
    public void addLoanApply() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getContext().getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().ADD_RZ_ORDER
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().RZLENDER + "=" + EncodeUtils.getInstance().getEncodeString(borrowName) + "&"
                + FieldConstant.getInstance().LINK_MAN + "=" + EncodeUtils.getInstance().getEncodeString(loanName) + "&"
                + FieldConstant.getInstance().LINK_CARD_NO + "=" + EncodeUtils.getInstance().getEncodeString(idCard) + "&"
                + FieldConstant.getInstance().COMPANY_NAME + "=" + EncodeUtils.getInstance().getEncodeString(companyName) + "&"
                + FieldConstant.getInstance().ADDRESS + "=" + EncodeUtils.getInstance().getEncodeString(companyAddress) + "&"
                + FieldConstant.getInstance().AREAS + "=" + EncodeUtils.getInstance().getEncodeString(companyHouse) + "&"
                + FieldConstant.getInstance().SHE_BEIS + "=" + EncodeUtils.getInstance().getEncodeString(tools) + "&"
                + FieldConstant.getInstance().GUI_MO + "=" + EncodeUtils.getInstance().getEncodeString(companyStandard) + "&"
                + FieldConstant.getInstance().OLD_LOAN + "=" + EncodeUtils.getInstance().getEncodeString(loanExtra) + "&"
                + FieldConstant.getInstance().OLD_YUQI_LOAN + "=" + EncodeUtils.getInstance().getEncodeString(loanOut) + "&"
                + FieldConstant.getInstance().OLD_LOAN_LIXI + "=" + EncodeUtils.getInstance().getEncodeString(loanMoney) + "&"
                + FieldConstant.getInstance().RZ_MONEY + "=" + EncodeUtils.getInstance().getEncodeString(useMoney) + "&"
                + FieldConstant.getInstance().RZ_YONG_TU + "=" + EncodeUtils.getInstance().getEncodeString(moneyType) + "&"
                + FieldConstant.getInstance().REFROM_MONEY + "=" + EncodeUtils.getInstance().getEncodeString(loanFrom) + "&"
                + FieldConstant.getInstance().REFROM_TYPE + "=" + EncodeUtils.getInstance().getEncodeString("按月付息，到期还本") + "&"
//                + FieldConstant.getInstance().RZ_DATE_MONEY + "=" + EncodeUtils.getInstance().getEncodeString(backMoney) + "&"
                + FieldConstant.getInstance().RZ_PIC_YYZZ + "=" + EncodeUtils.getInstance().getEncodeString(workPic) + "&"
                + FieldConstant.getInstance().RZ_WENJIAN + "=" + EncodeUtils.getInstance().getEncodeString(fuJian), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().activityLoan);

                    SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().INIT_LOAN, "yes");
                }else{
                    ToastUtils.getMineToast(successBean.getErrMsg());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }
}
