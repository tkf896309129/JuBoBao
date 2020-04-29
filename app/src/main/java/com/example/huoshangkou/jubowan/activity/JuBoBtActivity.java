package com.example.huoshangkou.jubowan.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnSignImageCallBack;
import com.example.huoshangkou.jubowan.inter.SuccessCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.SignDialogUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：JuBoBtActivity
 * 描述：
 * 创建时间：2018-08-21  08:46
 */

public class JuBoBtActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;

    private boolean isTiE = false;

    @Override
    public int initLayout() {
        return R.layout.activity_jb_bt;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().btList.add(this);
        tvTitle.setText("聚玻白条");
        String stringExtra = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        if (StringUtils.isNoEmpty(stringExtra)) {
            isTiE = true;
        }
    }

    @OnClick({R.id.ll_back, R.id.tv_bt_apply})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_bt_apply:
                String states = (String) SharedPreferencesUtils.getInstance().get(this, SharedKeyConstant.getInstance().USER_STATE, "");
                if (!states.equals(getString(R.string.has_checking))) {
                    ToastUtils.getMineToast("您还没有认证通过，请先认证");
                    return;
                }
                DialogUtils.getInstance().toFaceCheck(this,"您接下来要进行人脸识别验证，验证成功后可进行资料填写！", new SuccessCallBack() {
                    @Override
                    public void onSuccess() {
//                        IntentUtils.getInstance().toActivity(JuBoBtActivity.this, UpWorkPicActivity.class);
                        if (ContextCompat.checkSelfPermission(JuBoBtActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(JuBoBtActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
                        } else {
                            Intent intent = new Intent(JuBoBtActivity.this, FaceLivenessExpActivity.class);
                            if (isTiE) {
                                intent.putExtra(IntentUtils.getInstance().TYPE, "tiE");
                            } else {
                                intent.putExtra(IntentUtils.getInstance().TYPE, "jbBt");
                            }
                            startActivityForResult(intent, 1);
                        }
                    }

                    @Override
                    public void onFail() {

                    }
                });
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case 1:
                String stringExtra = data.getStringExtra(IntentUtils.getInstance().STATE);
                if (stringExtra.equals("success")) {
                    CopyIosDialogUtils.getInstance().getIosDialog(this, "公司名称是否发生变更", "有变更", "无变更", new CopyIosDialogUtils.iosDialogClick() {
                        @Override
                        public void confimBtn() {
                            IntentUtils.getInstance().toActivity(JuBoBtActivity.this, UpWorkPicActivity.class);
                        }

                        @Override
                        public void cancelBtn() {
                            SignDialogUtils.getInstance().updateDialog(JuBoBtActivity.this, new OnSignImageCallBack() {
                                @Override
                                public void onSignSuccess(String path) {
                                    tiUp(path);
                                }
                            });
                        }
                    });
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 2:
                Intent intent = new Intent(JuBoBtActivity.this, FaceLivenessExpActivity.class);
                if (isTiE) {
                    intent.putExtra(IntentUtils.getInstance().TYPE, "tiE");
                } else {
                    intent.putExtra(IntentUtils.getInstance().TYPE, "jbBt");
                }
                startActivityForResult(intent, 1);
                break;
        }
    }

    //提交
    public void tiUp(String pic) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(this, UrlConstant.getInstance().LOCAL_URL + PostConstant.getInstance().SUBMISSION
                + FieldConstant.getInstance().TYPE + "=2" + "&"
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().AUTH_PIC + "=" + pic, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    JuBoBtActivity.this.finish();
                    ToastUtils.getMineToast("提额成功");
                } else {
                    ToastUtils.getMineToast(successBean.getErrMsg());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }
}
