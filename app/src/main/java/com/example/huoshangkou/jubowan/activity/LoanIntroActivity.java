package com.example.huoshangkou.jubowan.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ApproveBankListBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.ChooseDialogPositionCallBack;
import com.example.huoshangkou.jubowan.inter.OnImageUpCallBack;
import com.example.huoshangkou.jubowan.inter.OnTimeCallBack;
import com.example.huoshangkou.jubowan.inter.OnUpVideoCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.KeyBoardUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.MoneyFormatUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.PickDialogUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.TimeDialogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.utils.VideoPathUtils;
import com.zero.smallvideorecord.DeviceUtils;
import com.zero.smallvideorecord.JianXiCamera;
import com.zero.smallvideorecord.LocalMediaCompress;
import com.zero.smallvideorecord.model.AutoVBRMode;
import com.zero.smallvideorecord.model.LocalMediaConfig;
import com.zero.smallvideorecord.model.OnlyCompressOverBean;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：LoanIntroActivity
 * 描述：贷款说明
 * 创建时间：2017-08-30  09:34
 */

public class LoanIntroActivity extends BaseActivity {
    private final int PHOTO_CAMERA = 1;
    @Bind(R.id.et_order_num)
    TextView etOrderNum;
    @Bind(R.id.et_loan_name)
    EditText etLoanName;
    @Bind(R.id.et_phone_num)
    EditText etPhoneNum;
    @Bind(R.id.et_loan_use)
    EditText etLoanUse;
    @Bind(R.id.et_loan_money)
    EditText etLoanMoney;
    @Bind(R.id.et_small_write)
    EditText etSmallWrite;
    @Bind(R.id.tv_large_write)
    TextView tvLargeWrite;

    @Bind(R.id.tv_loan_time)
    TextView tvLoanTime;
    @Bind(R.id.tv_arrive_time)
    TextView tvArriveTime;
    @Bind(R.id.tv_month_price)
    TextView tvMonthPrice;

    private String path;//文件路径
    Uri uri;

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.iv_video)
    ImageView imgVideo;

    @Bind(R.id.tv_rzdw)
    TextView tvRzdw;
    @Bind(R.id.tv_khh)
    TextView tvKhh;
    @Bind(R.id.tv_zh)
    TextView tvZh;

    //是否需要重新选择视频
    private boolean isNeedVideo = false;


    //合同号
    private String contactNo = "";
    //订单号
    private String orderId = "";
    //借款人
    private String loanLinkMan = "";
    //手机号
    private String phone = "";
    //借款用途
    private String loanUseFor = "";
    //借款利率
    private String loanLlValue = "";
    //小写借款金额
    private String smallWrite = "";
    //大写
    private String largeWrite = "";
    //借款日期
    private String loanTime = "";
    //入账日期
    private String auditTime = "";
    //到期日期
    private String auditTimeYea = "";
    //占位图
    private String previewImg = "";
    //视频
    private String videoPath = "";
    //月数
    private String months = "";

    private String openBank = "";
    private String accountName = "";
    private String accountNumber = "";

    //到期日期
    private ArrayList<String> arriveTimeList;
    //利率利息
    private double extraMoney;
    //总金额
    private double allMoney;
    private double money;

    private ProgressDialog alertDialog;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    float progress = (float) msg.obj;
                    if (progress != 1.0) {
                        alertDialog.show();
                    } else {
                        alertDialog.dismiss();
                        ToastUtils.getMineToast("上传完成");
                    }
                    alertDialog.setMessage("视频上传中 " + (int) (progress * 100) + "%");

                    break;
            }
        }
    };

    @Override
    public int initLayout() {
        return R.layout.activity_loan_intro;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("借款借据");

        contactNo = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        loanLinkMan = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        phone = getIntent().getStringExtra(IntentUtils.getInstance().STR);

        etLoanName.setText(loanLinkMan);
        etPhoneNum.setText(phone);

        alertDialog = new ProgressDialog(getContext());
        alertDialog.setCanceledOnTouchOutside(false);
        initSmallVideo();
        arriveTimeList = new ArrayList<>();


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

                money = Double.parseDouble(charSequence.toString());
                extraMoney = money * 0.008;
//                allMoney = money + extraMoney;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @OnClick({R.id.ll_zh, R.id.ll_khh, R.id.ll_rzdw, R.id.tv_set_video, R.id.ll_back, R.id.iv_video, R.id.ll_order_num, R.id.tv_confirm_commit, R.id.tv_month_price, R.id.tv_loan_time, R.id.tv_arrive_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_set_video:
                Intent intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent,
                        PHOTO_CAMERA);
                break;
            case R.id.iv_video:
                if (uri != null && !isNeedVideo) {
                    Intent intent2 = new Intent(this, VideoActivity.class);
                    intent2.putExtra("uri", uri);
                    startActivity(intent2);
                    return;
                }
                break;
            case R.id.ll_order_num:
//                IntentUtils.getInstance().toActivity(getContext(),ToPayOrderActivity.class);

                Intent intent1 = new Intent(getContext(), ToPayOrderActivity.class);
                startActivityForResult(intent1, 2);
                break;
            case R.id.tv_loan_time:
                KeyBoardUtils.closeKeybord(etLoanName, getContext());
                TimeDialogUtils.getInstance().getTime(getContext(), new OnTimeCallBack() {
                    @Override
                    public void getYearTime(String year) {
                        loanTime = year;
                        tvLoanTime.setText(loanTime);
                    }

                    @Override
                    public void getMinuteTime(String minute) {

                    }
                });
                break;
            case R.id.tv_arrive_time:
                KeyBoardUtils.closeKeybord(etLoanName, getContext());
                TimeDialogUtils.getInstance().getTime(getContext(), new OnTimeCallBack() {
                    @Override
                    public void getYearTime(String year) {
                        auditTime = year;
                        tvArriveTime.setText(auditTime);
                    }

                    @Override
                    public void getMinuteTime(String minute) {

                    }
                });
                break;
            case R.id.tv_month_price:
                KeyBoardUtils.closeKeybord(etLoanName, getContext());
                arriveTimeList.clear();
                arriveTimeList.add("一个月");
                arriveTimeList.add("两个月");
                arriveTimeList.add("三个月");
                //+ extraMoney
                PickDialogUtils.getInstance().getChoosePositionDialog(getContext(), "到期日期", arriveTimeList, new ChooseDialogPositionCallBack() {
                    @Override
                    public void onGetMessagePosition(String message, int position) {
                        auditTimeYea = message;
                        tvMonthPrice.setText(auditTimeYea);

                        months = (position + 1) + "";
                        allMoney = money + (extraMoney * (position + 1));

                        CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "您需要每月还款利息" + extraMoney + "元，共" + message + ",截止最终到期还款日共需要还款" + (allMoney) + "元", new CopyIosDialogUtils.ErrDialogCallBack() {
                            @Override
                            public void confirm() {

                            }
                        });
                    }
                });
                break;
            case R.id.tv_confirm_commit:
                orderId = etOrderNum.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(orderId)) {
//                    ToastUtils.getMineToast("请选择订单号"); 2132w个路印 2857w个比原 还有五百万疯狂的收eos 我靠牛逼啊 疯狂的
//                }
                loanLinkMan = etLoanName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(loanLinkMan)) {
                    ToastUtils.getMineToast("请输入借款人");
                    return;
                }
                phone = etPhoneNum.getText().toString().trim();
                if (!StringUtils.isNoEmpty(phone)) {
                    ToastUtils.getMineToast("请输入手机号");
                    return;
                }
                loanUseFor = etLoanUse.getText().toString().trim();
                if (!StringUtils.isNoEmpty(loanUseFor)) {
                    ToastUtils.getMineToast("请输入借款用途");
                    return;
                }
                loanLlValue = etLoanMoney.getText().toString().trim();
                if (!StringUtils.isNoEmpty(loanLlValue)) {
                    ToastUtils.getMineToast("请输入借款利率");
                    return;
                }
                smallWrite = etSmallWrite.getText().toString().trim();
                if (!StringUtils.isNoEmpty(smallWrite)) {
                    ToastUtils.getMineToast("请输入借款金额");
                    return;
                }
                if (Integer.parseInt(smallWrite) < 100000 || Integer.parseInt(smallWrite) % 100000 != 0) {
                    ToastUtils.getMineToast("借款金额最低为10万并且只能为10万的整数倍");
                    return;
                }
//                if (!StringUtils.isNoEmpty(largeWrite)) {
//                    ToastUtils.getMineToast("请输入手机号");
//                    return;
//                }
                if (!StringUtils.isNoEmpty(loanTime)) {
                    ToastUtils.getMineToast("请选择借款时间");
                    return;
                }
                if (!StringUtils.isNoEmpty(auditTime)) {
                    ToastUtils.getMineToast("请选择入账时间");
                    return;
                }
                if (!StringUtils.isNoEmpty(auditTimeYea)) {
                    ToastUtils.getMineToast("请选择到期时间");
                    return;
                }
                openBank = tvKhh.getText().toString().trim();
                if (!StringUtils.isNoEmpty(openBank)) {
                    ToastUtils.getMineToast("请选择借款指定入账单位");
                    return;
                }
                accountName = tvRzdw.getText().toString().trim();
                accountNumber = tvZh.getText().toString().trim();

//                auditTimeYea = etTime.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(previewImg)) {
//                    ToastUtils.getMineToast("请上传签字视频");
//                    return;
//                }

                commitLoanIntro(contactNo, orderId, loanLinkMan, phone, loanUseFor, loanLlValue,
                        smallWrite, loanTime, auditTime, auditTimeYea, previewImg, months, openBank, accountName, accountNumber);
                break;
            case R.id.ll_zh:
            case R.id.ll_khh:
            case R.id.ll_rzdw:
                Intent intentBank = new Intent(this, BorrowBankActivity.class);
                startActivityForResult(intentBank, 3);
                break;
        }
    }


    /**
     * 视频回调
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }

        switch (requestCode) {
            case PHOTO_CAMERA:
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        uri = data.getData();
                        LogUtils.i("Uri1:  " + uri.toString());
                        uri = geturi(this, data);
                        LogUtils.i("Uri2:  " + uri.toString());
                        File file = null;
                        if (uri.toString().indexOf("file") == 0) {

                            file = new File(new URI(uri.toString()));
                            path = file.getPath();

                            LogUtils.i("1:   ");
                            LogUtils.i("1:   " + path);
                        } else {
//                            file = new File(path);
                            LogUtils.i("2:   ");
                            LogUtils.i("2:   " + uri.getPath());
                            path = VideoPathUtils.getPath(getContext(), uri);
                            file = new File(path);

                        }

//                      file = new File(Uri.parse(uri.toString()));

                        isNeedVideo = false;
                        compressVideo();


                        if (!file.exists()) {
                            ToastUtils.getMineToast("文件不存在");
                            break;
                        }
                        if (file.length() > 100 * 1024 * 1024) {
//                            "文件大于100M";
                            break;
                        }
                        alertDialog.show();
                        alertDialog.setMessage("视频上传中 ");
                        GlideUtils.getInstance().displayImage(getContext(), file, imgVideo);

                        //开始上传视频，
//                        submitVedio();
                    } catch (Exception e) {
                        String a = e + "";
                    } catch (OutOfMemoryError e) {
                        String a = e + "";
                    }
                }
                break;
            case 2:
                String orderId = data.getStringExtra("orderId");
                etOrderNum.setText(orderId);
                break;
            case 3:
                ApproveBankListBean bankListBean = (ApproveBankListBean) data.getSerializableExtra("bean");
//                    bankId = bankListBean.getID()+"";
                tvRzdw.setText(bankListBean.getName());
                tvZh.setText(bankListBean.getZH());
                tvKhh.setText(bankListBean.getKHH());
                break;
        }
    }

    public static Uri geturi(Context context, Intent intent) {
        Uri uri = intent.getData();
        String type = intent.getType();
        if (uri.getScheme().equals("file") && (type.contains("image/"))) {
            String path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = context.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=")
                        .append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Images.ImageColumns._ID},
                        buff.toString(), null, null);
                int index = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    // set _id value
                    index = cur.getInt(index);
                }
                if (index == 0) {
                    // do nothing
                } else {
                    Uri uri_temp = Uri
                            .parse("content://media/external/images/media/"
                                    + index);
                    if (uri_temp != null) {
                        uri = uri_temp;
                        Log.i("urishi", uri.toString());
                    }
                }
            }
        }
        return uri;
    }


    //视频压缩
    public void compressVideo() {
        // 选择本地视频压缩
        LocalMediaConfig.Buidler buidler = new LocalMediaConfig.Buidler();
        final LocalMediaConfig config = buidler
                .setVideoPath(path)
                .captureThumbnailsTime(1)
                .doH264Compress(new AutoVBRMode())
//                .setFramerate(15)
                .setFramerate(5)
                .build();


        new Thread(new Runnable() {
            @Override
            public void run() {
                final OnlyCompressOverBean onlyCompressOverBean = new LocalMediaCompress(config).startCompress();
                LogUtils.i(onlyCompressOverBean.getVideoPath());
                LogUtils.i(onlyCompressOverBean.getPicPath());
                File file = new File(onlyCompressOverBean.getVideoPath());
                LogUtils.i(file.length() / 1024 / 1024 + "");

                File imgFile = new File(onlyCompressOverBean.getPicPath());
                PhotoUtils.getInstance().commonImageUp(imgFile, new OnImageUpCallBack() {
                    @Override
                    public void onUpSuccess(String imgPath) {
                        previewImg = imgPath;
                    }
                });
                PhotoUtils.getInstance().commonVideoUp(getContext(), file, new OnUpVideoCallBack() {

                    @Override
                    public void onProgress(float progress) {
                        Message message = handler.obtainMessage();
                        message.what = 1;
                        message.obj = progress;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onStr(String str) {
                        LogUtils.i("str" + str);
                        videoPath = str;
                    }

                    @Override
                    public void onFail() {
                        alertDialog.dismiss();
                    }
                });
            }
        }).start();
    }


    public static void initSmallVideo() {
        // 设置拍摄视频缓存路径
        File dcim = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        if (DeviceUtils.isZte()) {
            if (dcim.exists()) {
                JianXiCamera.setVideoCachePath(dcim + "/zero/");
            } else {
                JianXiCamera.setVideoCachePath(dcim.getPath().replace("/sdcard/",
                        "/sdcard-ext/")
                        + "/zero/");
            }
        } else {
            JianXiCamera.setVideoCachePath(dcim + "/zero/");
        }
        // 初始化拍摄
        JianXiCamera.initialize(false, null);
    }


    //提交借款借据
    public void commitLoanIntro(String contractNo, String orderId, String loanLinkMan, String loanTel, String useOfLoan,
                                String loanRate, String loanAmount, String createTime, String auditTime,
                                String auditTimeYea, String mediaPreview, String months, String openBank, String accountName, String accountNumber) {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                        + PostConstant.getInstance().ADD_DUE_BILL
                        + FieldConstant.getInstance().CONTRACT_NO + "=" + EncodeUtils.getInstance().getEncodeString(contractNo) + "&"
                        + FieldConstant.getInstance().ORDER_ID + "=" + orderId + "&"
                        + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                        + FieldConstant.getInstance().LOAN_LINK_MAN + "=" + EncodeUtils.getInstance().getEncodeString(loanLinkMan) + "&"
                        + FieldConstant.getInstance().LOAN_TEL + "=" + EncodeUtils.getInstance().getEncodeString(loanTel) + "&"
                        + FieldConstant.getInstance().USE_OF_LOAN + "=" + EncodeUtils.getInstance().getEncodeString(useOfLoan) + "&"
                        + FieldConstant.getInstance().LOAN_RATE + "=" + EncodeUtils.getInstance().getEncodeString(loanRate) + "&"
                        + FieldConstant.getInstance().LOAN_AMOUNT + "=" + EncodeUtils.getInstance().getEncodeString(loanAmount) + "&"
                        + FieldConstant.getInstance().CREATE_TIME + "=" + EncodeUtils.getInstance().getEncodeString(createTime) + "&"
                        + FieldConstant.getInstance().MONTHS + "=" + EncodeUtils.getInstance().getEncodeString(months) + "&"
                        + FieldConstant.getInstance().AUDIT_TIME + "=" + EncodeUtils.getInstance().getEncodeString(auditTime) + "&"
                        + FieldConstant.getInstance().AUDIT_TIME_YEA + "=" + EncodeUtils.getInstance().getEncodeString(auditTimeYea) + "&"
                        + FieldConstant.getInstance().ENCLOSURE + "=" + EncodeUtils.getInstance().getEncodeString(videoPath) + "&"
                        + FieldConstant.getInstance().ACCOUNT_NAME + "=" + EncodeUtils.getInstance().getEncodeString(accountName) + "&"
                        + FieldConstant.getInstance().ACCOUNT_NUMBER + "=" + EncodeUtils.getInstance().getEncodeString(accountNumber) + "&"
                        + FieldConstant.getInstance().OPENING_BANK + "=" + EncodeUtils.getInstance().getEncodeString(openBank) + "&"
                        + FieldConstant.getInstance().MEDIA_PREVIEW + "=" + EncodeUtils.getInstance().getEncodeString(mediaPreview), new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                        if (successBean.getSuccess() == 1) {
                            ToastUtils.getMineToast("提交成功");
                            LoanIntroActivity.this.finish();
                            SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().INIT_LOAN, "yes");
                        } else {
                            ToastUtils.getMineToast(successBean.getErrMsg());
                        }
                    }

                    @Override
                    public void onFail() {

                    }
                }
        );
    }
}
