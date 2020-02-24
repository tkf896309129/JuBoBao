package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：AddGroupActivity
 * 描述：
 * 创建时间：2019-02-20  16:20
 */

public class AddGroupActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_group_name)
    EditText etGroupName;

    private String groupId = "";
    private String panrentId = "";
    private String depName = "";
    //是否是修改
    private boolean isChange = false;

    @Override
    public int initLayout() {
        return R.layout.activity_add_group;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        panrentId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        groupId = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        if (StringUtils.isNoEmpty(groupId)) {
            tvTitle.setText("修改分组");
            isChange = true;
        } else {
            tvTitle.setText("添加分组");
        }
    }

    @OnClick({R.id.tv_add_confirm, R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_add_confirm:
                depName = etGroupName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(depName)) {
                    ToastUtils.getMineToast("请输入分组名称");
                    return;
                }
                String hint = "";
                if (isChange) {
                    hint = "是否修改分组";
                } else {
                    hint = "是否添加分组";
                }
                CopyIosDialogUtils.getInstance().getIosDialog(this, hint, new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        addGroup();
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
                break;
        }
    }


    //添加部门信息
    public void addGroup() {
        if (!StringUtils.isNoEmpty(groupId)) {
            groupId = "";
        }
        OkhttpUtil.getInstance().setUnCacheData(this, "数据加载中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().ADD_DEPARTMENT
                + FieldConstant.getInstance().DEP_ID + "=" + groupId + "&"
                + FieldConstant.getInstance().PARENT_ID_SMALL + "=" + panrentId + "&"
                + FieldConstant.getInstance().DEP_NAME + "=" + depName + "&"
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    if (isChange) {
                        ToastUtils.getMineToast("修改成功");
                    } else {
                        ToastUtils.getMineToast("添加成功");
                    }

                    AddGroupActivity.this.finish();
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
