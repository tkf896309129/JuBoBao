package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.HideBaseActivity;
import com.example.huoshangkou.jubowan.bean.SignGroupBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.ChooseDialogCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PickDialogUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：AddSignManActivity
 * 描述：
 * 创建时间：2017-04-24  11:41
 */

public class AddSignManActivity extends HideBaseActivity {
    @Bind(R.id.tv_group_name)
    TextView tvGroupName;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_name)
    EditText etName;

    //部门名称
    private String groupName = "";
    private String phone = "";
    private String name = "";
    private String groupId = "";

    @Override
    public int initLayout() {
        return R.layout.activity_add_sign_man;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("添加新成员");
        groupName = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        groupId = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        if(StringUtils.isNoEmpty(groupName)){
            tvGroupName.setText(groupName.trim() + " ");
        }else {
            tvGroupName.setText(  "暂无部门 ");
        }


    }

    @OnClick({ R.id.ll_back, R.id.tv_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;

            case R.id.tv_confirm:
                phone = etPhone.getText().toString().trim();
                if (!StringUtils.isNoEmpty(phone)) {
                    ToastUtils.getMineToast("请输入手机号码");
                    return;
                }
                name = etName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(name)) {
                    ToastUtils.getMineToast("请输入姓名");
                    return;
                }
                addNewMember();
                break;
        }
    }

    //添加新成员
    public void addNewMember() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().ADD_EMPLOYEE
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().LINK_MAN + "=" + EncodeUtils.getInstance().getEncodeString(name) + "&"
                + FieldConstant.getInstance().ROLE + "=" + EncodeUtils.getInstance().getEncodeString(groupName) + "&"
                + FieldConstant.getInstance().ROLE_IDS + "=" + EncodeUtils.getInstance().getEncodeString(groupId) + "&"
                + FieldConstant.getInstance().UMENG_ID + "=android" + "&"
                + FieldConstant.getInstance().LOGIN_NAME + "=" + phone, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("添加成功");
                    SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().GET_MEMBER_LIST, "yes");
                    AddSignManActivity.this.finish();
                } else {
                    CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), successBean.getErrMsg(), new CopyIosDialogUtils.ErrDialogCallBack() {
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
}
