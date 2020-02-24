package com.example.huoshangkou.jubowan.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.baidu.idl.face.platform.FaceStatusEnum;
import com.baidu.idl.face.platform.ui.FaceLivenessActivity;
import com.example.huoshangkou.jubowan.bean.IdCardBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.facebd.widget.DefaultDialog;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.utils.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.Call;

public class FaceLivenessExpActivity extends FaceLivenessActivity {
    private DefaultDialog mDefaultDialog;
    private String str = "https://api-cn.faceplusplus.com/facepp/v3/compare";
    private String idCard = "";
    //是否检测到人脸
    private boolean isFace = false;
    private double confidence = 0;
    //是否是聚玻白条
    private boolean isJuBoBt = false;
    //是否是提额
    private boolean isTiE = false;
    //是否是垫付款资质
    private boolean isDianFuApply = false;
    //是否是平安银行
    private boolean isPinAnBank = false;
    private String btValue = "";
    private String bankUrl = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIdCard();
    }

    @Override
    public void onLivenessCompletion(FaceStatusEnum status, String message, HashMap<String, String> base64ImageMap) {
        super.onLivenessCompletion(status, message, base64ImageMap);
        if (status == FaceStatusEnum.OK && mIsCompletion) {
            PhotoUtils.getInstance().onlyUpImage(bitmapList.get(3), new StringCallBack() {
                @Override
                public void onSuccess(String str) {
                    LogUtils.e(str);
                    getTestFace(str);
                }

                @Override
                public void onFail() {
                    CopyIosDialogUtils.getInstance().getIosDialog(FaceLivenessExpActivity.this, "匹配失败", new CopyIosDialogUtils.iosDialogClick() {
                        @Override
                        public void confimBtn() {
                            startPreview();
                            isFace = false;
                        }

                        @Override
                        public void cancelBtn() {

                        }
                    });
                }
            });
        } else if (status == FaceStatusEnum.Error_DetectTimeout ||
                status == FaceStatusEnum.Error_LivenessTimeout ||
                status == FaceStatusEnum.Error_Timeout) {
            showMessageDialog("活体检测", "采集超时");
        }
        btValue = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        bankUrl = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        if (StringUtils.isNoEmpty(btValue)) {
            isJuBoBt = true;
            if (btValue.equals("pinAnBank")) {
                isPinAnBank = true;
            }
            if (btValue.equals("tiE")) {
                isTiE = true;
            }
            if(btValue.equals("dianFuApply")){
                isDianFuApply =true;
            }
        }
    }

    private void showMessageDialog(String title, String message) {
        if (mDefaultDialog == null) {
            DefaultDialog.Builder builder = new DefaultDialog.Builder(this);
            builder.setTitle(title).
                    setMessage(message).
                    setNegativeButton("确认",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mDefaultDialog.dismiss();
                                    finish();
                                }
                            });
            mDefaultDialog = builder.create();
            mDefaultDialog.setCancelable(true);
        }
        mDefaultDialog.dismiss();
        mDefaultDialog.show();
    }

    @Override
    public void finish() {
        super.finish();
    }

    public void getTestFace(final String url) {
        if (!StringUtils.isNoEmpty(idCard)) {
            CopyIosDialogUtils.getInstance().getIosDialog(FaceLivenessExpActivity.this, " 您的身份证信息不存在,请您去个人中心->进行审核认证。", new CopyIosDialogUtils.iosDialogClick() {
                @Override
                public void confimBtn() {
                    mCamera.startPreview();
                    isFace = false;
                    FaceLivenessExpActivity.this.finish();
                }

                @Override
                public void cancelBtn() {
                    FaceLivenessExpActivity.this.finish();
                }
            });
            return;
        }
        OkHttpUtils.post()
                .addParams("api_key", Util.API_KEY)
                .addParams("api_secret", Util.API_SECRET)
                .addParams("image_url1", idCard)
                .addParams("image_url2", url)
                .url(str)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.i(e.toString());
                ToastUtils.getMineToast(e.toString());
                CopyIosDialogUtils.getInstance().getErrorDialog(FaceLivenessExpActivity.this, "检测失败,请您重新检测", new CopyIosDialogUtils.ErrDialogCallBack() {
                    @Override
                    public void confirm() {
                        startPreview();
                        isFace = false;
                    }
                });
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    LogUtils.i(response);
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray faces1 = jsonObject.getJSONArray("faces1");
                    JSONArray faces2 = jsonObject.getJSONArray("faces2");

//                    String faceToken = faces1.getJSONObject(0).getString("face_token");
//                    LogUtils.i(faceToken);
//                    getFaceScore(faceToken);
                    if (faces2.length() == 0) {
                        CopyIosDialogUtils.getInstance().getIosDialog(FaceLivenessExpActivity.this, "面部识别失败，请重新检测。", new CopyIosDialogUtils.iosDialogClick() {
                            @Override
                            public void confimBtn() {
                                FaceLivenessExpActivity.this.finish();
                                isFace = false;
                            }

                            @Override
                            public void cancelBtn() {
                                FaceLivenessExpActivity.this.finish();
                            }
                        });
                    }
                    if (faces1.length() == 0) {
                        CopyIosDialogUtils.getInstance().getIosDialog(FaceLivenessExpActivity.this, "您的身份证信息可能存在不清晰的现象,请您上传更清晰的身份证正面照。", new CopyIosDialogUtils.iosDialogClick() {
                            @Override
                            public void confimBtn() {
                                isFace = false;
                                FaceLivenessExpActivity.this.finish();
                            }

                            @Override
                            public void cancelBtn() {
                                FaceLivenessExpActivity.this.finish();
                            }
                        });
                    }
                    confidence = jsonObject.getDouble("confidence");
//                    if (confidence == null) {
//                    }
                    LogUtils.i(confidence + "");
                    if (confidence > 80) {
                        String message = "";
                        if (isJuBoBt) {
                            message = "恭喜您匹配成功，您现在可以填写聚玻白条资料了";
                            if (isTiE) {
                                message = "恭喜您匹配成功，是否进行下一步操作";
                                CopyIosDialogUtils.getInstance().getIosDialog(FaceLivenessExpActivity.this, message, new CopyIosDialogUtils.iosDialogClick() {
                                    @Override
                                    public void confimBtn() {
                                        Intent intent = new Intent();
                                        intent.putExtra(IntentUtils.getInstance().STATE, "success");
                                        setResult(101, intent);
                                        FaceLivenessExpActivity.this.finish();
                                    }

                                    @Override
                                    public void cancelBtn() {

                                    }
                                });
                                return;
                            }
                        } else {
                            message = "恭喜您匹配成功，您现在可以填写服务申请资料了";
                        }

                        if (isPinAnBank) {
                            message = "恭喜您匹配成功，您现在可以进行平安银行相关申请了";
                        }
                        if(isDianFuApply){
                            message = "恭喜您匹配成功，您现在可以进行垫付款资质相关申请了";
                        }
                        CopyIosDialogUtils.getInstance().getIosDialog(FaceLivenessExpActivity.this, message, "退出", new CopyIosDialogUtils.iosDialogClick() {
                            @Override
                            public void confimBtn() {
                                saveFaceData(idCard, url, confidence + "");
                                if(isDianFuApply){
                                    IntentUtils.getInstance().toActivity(FaceLivenessExpActivity.this, DianFuNewActivity.class);
                                    return;
                                }
                                if (isPinAnBank) {
                                    IntentUtils.getInstance().toWebActivity(FaceLivenessExpActivity.this, bankUrl, "平安银行数据贷");
                                    return;
                                }

                                //聚玻白条
                                if (isJuBoBt) {
                                    IntentUtils.getInstance().toActivity(FaceLivenessExpActivity.this, UpWorkPicActivity.class);
                                    //聚玻服务
                                } else {
                                    IntentUtils.getInstance().toActivity(FaceLivenessExpActivity.this, LoanApplyActivity.class);
                                }
                            }

                            @Override
                            public void cancelBtn() {
//                                mCamera.startPreview();
//                                isFace = false;
                                FaceLivenessExpActivity.this.finish();
                            }
                        });
                    } else if (confidence < 80 && confidence > 60) {
                        CopyIosDialogUtils.getInstance().getIosDialog(FaceLivenessExpActivity.this, "您的面部识别和身份证比对不太明显，请重试。 ", new CopyIosDialogUtils.iosDialogClick() {
                            @Override
                            public void confimBtn() {
                                FaceLivenessExpActivity.this.finish();
                                isFace = false;
                            }

                            @Override
                            public void cancelBtn() {
                                FaceLivenessExpActivity.this.finish();
                            }
                        });
                    } else if (confidence < 60) {
                        CopyIosDialogUtils.getInstance().getIosDialog(FaceLivenessExpActivity.this, " 您的面部识别和身份证严重不匹配,请您重新再试 ", new CopyIosDialogUtils.iosDialogClick() {
                            @Override
                            public void confimBtn() {
                               FaceLivenessExpActivity.this.finish();
                                isFace = false;
                            }

                            @Override
                            public void cancelBtn() {
                                FaceLivenessExpActivity.this.finish();
                            }
                        });
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //获取身份证照片
    public void getIdCard() {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(this, UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_ID_PIC + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                LogUtils.i(json);
                IdCardBean cardBean = JSON.parseObject(json, IdCardBean.class);
                idCard = cardBean.getReObj();
//                idCard = "http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20170904/20170904150921_1060.png";
//                idCard = "http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20170922/20170922094433_4970.jpg";
            }

            @Override
            public void onFail() {
                //身份证信息获取失败,请联系平台
                CopyIosDialogUtils.getInstance().getIosDialog(FaceLivenessExpActivity.this, "身份证信息获取失败,请联系平台", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        mCamera.startPreview();
                        isFace = false;
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }
        });
    }

    //人脸识别存储
    public void saveFaceData(String cardPic, String facePic, String similar) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(this, UrlConstant.getInstance().URL
                + PostConstant.getInstance().ADD_FACE_INDENTIFY + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().IDENTIFY_PIC + "=" + cardPic + "&"
                + FieldConstant.getInstance().FACE_PIC + "=" + facePic + "&"
                + FieldConstant.getInstance().SIMILARY + "=" + similar, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {

            }

            @Override
            public void onFail() {

            }
        });
    }
}
