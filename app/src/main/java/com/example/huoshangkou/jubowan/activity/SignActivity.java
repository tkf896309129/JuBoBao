package com.example.huoshangkou.jubowan.activity;

import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.BaseCheckFunction;
import com.example.huoshangkou.jubowan.activity.function.ImageAddFunction;
import com.example.huoshangkou.jubowan.activity.function.SignFunction;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.inter.SuccessCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.KeyBoardUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.MD5Utils;
import com.example.huoshangkou.jubowan.utils.MineLoadingDialogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhoneUtils;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.zero.smallvideorecord.Log;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：SignActivity
 * 描述：签到界面
 * 创建时间：2017-03-29  17:09
 */

public class SignActivity extends BaseActivity {
    //图片
    List<String> imgList;
    //图片适配器
    ImageAddAdapter imageAddAdapter;
    @Bind(R.id.grid_sign)
    GridView scrollGridView;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.et_sign_remark)
    EditText etRemarkSign;
    @Bind(R.id.tv_commit)
    TextView tvCommit;

    private String address;
    private String remark;
    private String createTime;
    private String x;
    private String y;
    private String pics = "";

    //兴趣点
    private String poinName = "";

    private int imgNum = 9;

    //回访
    private String visitor = "";
    private String visitorId = "";

    @Override
    public int initLayout() {
        return R.layout.activity_sign;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        imgList = new ArrayList<>();
        address = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        poinName = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        visitor = getIntent().getStringExtra(IntentUtils.getInstance().STR);
        visitorId = getIntent().getStringExtra(IntentUtils.getInstance().TXT);
        createTime = getIntent().getStringExtra(IntentUtils.getInstance().TYPE_DETAILS);
//        etRemarkSign.setText(visitor);
        String[] split = address.split("、");
        address = split[0];
        x = split[1];
        y = split[2];
        tvAddress.setText(poinName + "," + address);

        //图片显示封装
        imageAddAdapter = new ImageAddAdapter(this, imgList, "");
        ImageAddFunction.addImageFun(imageAddAdapter, imgList, this, scrollGridView);

        tvTitle.setText("上报");
        tvTime.setText(createTime);
//        try {
//            URL url=new URL("http://www.bjtime.cn");//取得资源对象
//            URLConnection uc=url.openConnection();//生成连接对象
//            uc.connect(); //发出连接
//            long ld=uc.getDate(); //取得网站日期时间
//            Date date=new Date(ld); //转换为标准时间对象
//            //分别取得时间中的小时，分钟和秒，并输出
//            LogUtils.e(date.getHours()+"时"+date.getMinutes()+"分"+date.getSeconds()+"秒");
//        }catch (Exception e){
//
//        }
        getKey();
    }

    //点击事件
    @OnClick({R.id.ll_back, R.id.iv_camera, R.id.tv_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_camera:
                KeyBoardUtils.closeKeybord(etRemarkSign, this);
                if (imgList.size() >= 9) {
                    ToastUtils.getMineToast("最多选择9张图片");
                    return;
                }
                PhotoUtils.getInstance().getCameraPhoto(this, new PhotoCallBack() {
                    @Override
                    public void getPhoto(String path) {
                        if (StringUtils.isNoEmpty(path)) {
                            imgList.addAll(PhotoUtils.getInstance().getListImage(path));
                            imageAddAdapter.notifyDataSetChanged();
                        } else {
                            ToastUtils.getMineToast("获取图片失败");
                        }
                    }
                });
                break;
            case R.id.ll_back:
                this.finish();
                break;
            //提交
            case R.id.tv_commit:
                remark = etRemarkSign.getText().toString().trim();
                if (!StringUtils.isNoEmpty(remark)) {
                    remark = "暂无说明";
                }
                alertDialog = MineLoadingDialogUtils.updateDialog(this, "上报中");
                if (tvCommit == null) {
                    return;
                }
                tvCommit.setClickable(false);
                PhotoUtils.getInstance().mutilLocalImageUp(imgList, this, new StringCallBack() {
                    @Override
                    public void onSuccess(String str) {
                        pics = str;
                        upLocation();
                    }

                    @Override
                    public void onFail() {

                    }
                });

                if (tvCommit == null) {
                    return;
                }
                tvCommit.setClickable(true);
                break;
        }
    }

    AlertDialog alertDialog;

    //提交上报
    public void upLocation() {
        Map<String,Object> map = new HashMap<>();
        map.put(FieldConstant.getInstance().USER_ID, PersonConstant.getInstance().getUserId());
        map.put(FieldConstant.getInstance().VISIT_COMPANT_ID, visitorId);
        map.put(FieldConstant.getInstance().VISIT_COMPANT, visitor);
        map.put(FieldConstant.getInstance().ADDRESS, address);
        map.put(FieldConstant.getInstance().PIC, pics);
        map.put(FieldConstant.getInstance().REMARK, remark);
        map.put(FieldConstant.getInstance().CREATE_TIME, createTime);
        map.put(FieldConstant.getInstance().X, x);
        map.put(FieldConstant.getInstance().Y, y);
        map.put(FieldConstant.getInstance().POIN_NAME, poinName);
        map.put(FieldConstant.getInstance().STR_WHERE, getKey());
        String json = JSON.toJSONString(map);
        LogUtils.e(json);
//        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().POST_SIGN, new StringCallBack() {
//            @Override
//            public void onSuccess(String str) {
//                SuccessBean successBean = JSON.parseObject(str, SuccessBean.class);
//                if (successBean.getSuccess() == 1) {
//                    ToastUtils.getMineToast("提交成功");
//                    SignActivity.this.finish();
//                } else {
//                    ToastUtils.getMineToast(successBean.getErrMsg());
//                }
//                tvCommit.setClickable(true);
//                alertDialog.dismiss();
//            }
//
//            @Override
//            public void onFail() {
//                ToastUtils.getMineToast("发表失败");
//                tvCommit.setClickable(true);
//                alertDialog.dismiss();
//            }
//        });
        //提交签到信息
        OkHttpUtils.post()
                .addParams(FieldConstant.getInstance().USER_ID, PersonConstant.getInstance().getUserId())
                .addParams(FieldConstant.getInstance().VISIT_COMPANT_ID, visitorId)
                .addParams(FieldConstant.getInstance().VISIT_COMPANT, visitor)
                .addParams(FieldConstant.getInstance().ADDRESS, address)
                .addParams(FieldConstant.getInstance().PIC, pics)
                .addParams(FieldConstant.getInstance().REMARK, remark)
                .addParams(FieldConstant.getInstance().CREATE_TIME, createTime)
                .addParams(FieldConstant.getInstance().X, x)
                .addParams(FieldConstant.getInstance().Y, y)
                .addParams(FieldConstant.getInstance().POIN_NAME, poinName)
                .addParams(FieldConstant.getInstance().STR_WHERE, getKey())
                .url(UrlConstant.getInstance().POST_SIGN)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtils.getMineToast("发表失败");
                LogUtils.i(e.toString());
                tvCommit.setClickable(true);
                alertDialog.dismiss();
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.i(response);
                SuccessBean successBean = JSON.parseObject(response, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("提交成功");
                    SignActivity.this.finish();
                } else {
                    ToastUtils.getMineToast(successBean.getErrMsg());
                }
                tvCommit.setClickable(true);
                alertDialog.dismiss();
            }
        });
    }

    public String getKey() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        String format2 = dateFormat.format(date);
        String key = format2 + PersonConstant.getInstance().getPhoneTypeId();
//        LogUtils.e(key+"   "+MD5Utils.getInstance().md5("12", "3"));
        return MD5Utils.getInstance().md5(format2, PersonConstant.getInstance().getPhoneTypeId());
//        return MD5Utils.getInstance().md5("201906141608", PersonConstant.getInstance().getPhoneTypeId());
    }
}
