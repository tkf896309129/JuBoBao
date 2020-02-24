package com.example.huoshangkou.jubowan.activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.ChangePswFunction;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.inter.OnCommonSuccessCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.MineLoadingDialogUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：ChangePswActivity
 * 描述：忘记密码界面
 * 创建时间：2017-02-06  10:13
 */

public class ChangePswActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_old_psw)
    EditText etOldPsw;
    @Bind(R.id.et_new_psw)
    EditText etNewPsw;
    @Bind(R.id.et_confirm_psw)
    EditText etConfirmPsw;
    @Bind(R.id.tv_phone)
    TextView tvPhone;

    //旧密码
    private String oldPsw;
    //新密码
    private String newPsw;
    //确认密码
    private String confirmPsw;


    @Override
    public int initLayout() {
        return R.layout.activity_forget_psw;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        //修改密码
        tvTitle.setText(R.string.change_psw);
        tvPhone.setText(PersonConstant.getInstance().getPhone(this));
    }

    //点击事件
    @OnClick({R.id.ll_back, R.id.rl_change_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.rl_change_confirm:

                oldPsw = etOldPsw.getText().toString().trim();
                if (!StringUtils.isNoEmpty(oldPsw)) {
                    ToastUtils.getMineToast("请输入原始密码");
                    return;
                }

                newPsw = etNewPsw.getText().toString().trim();
                if (!StringUtils.isNoEmpty(newPsw)) {
                    ToastUtils.getMineToast("请输入新密码");
                    return;
                }


                confirmPsw = etConfirmPsw.getText().toString().trim();
                if (!StringUtils.isNoEmpty(confirmPsw)) {
                    ToastUtils.getMineToast("请输入确认密码");
                    return;
                }

                CopyIosDialogUtils.getInstance().getIosDialog(this, getString(R.string.is_change_psw), new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {

                        if (!confirmPsw.equals(newPsw)) {
                            ToastUtils.getMineToast("两次密码不一致，请确认后重输");
                            return;
                        }

                        ChangePswFunction.getInstance().changePsw(ChangePswActivity.this, oldPsw, newPsw, new OnCommonSuccessCallBack() {
                            @Override
                            public void onSuccess() {
                                ChangePswActivity.this.finish();
                            }

                            @Override
                            public void onFail() {

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
}
