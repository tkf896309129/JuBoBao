package com.example.huoshangkou.jubowan.activity;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：SuggestBackActivity
 * 描述：意见反馈界面
 * 创建时间：2017-02-06  12:59
 */

public class SuggestBackActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_suggest_back)
    EditText etSuggestBack;
    @Bind(R.id.tv_key_num)
    TextView tvKeyNum;

    String suggestStr;

    @Override
    public int initLayout() {
        return R.layout.activity_suggest_back;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        //设置标题
        tvTitle.setText(R.string.suggest_back);

        etSuggestBack.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tvKeyNum.setText(charSequence.length() + "/300");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    //点击事件
    @OnClick({R.id.ll_back, R.id.tv_confirm_suggest})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_confirm_suggest:

                suggestStr = etSuggestBack.getText().toString().trim();
                if (!StringUtils.isNoEmpty(suggestStr)) {
                    ToastUtils.getMineToast("请输入反馈意见");
                    return;
                }

                CopyIosDialogUtils.getInstance().getIosDialog(this, getString(R.string.is_commit_suggest), new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        setSuggestBack(SuggestBackActivity.this, "", suggestStr);
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
                break;
        }
    }


    //意见
    private void setSuggestBack(Context context, String typeId, String feedTxt) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL + PostConstant.getInstance().COMMIT_FEED +
                FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&" +
                FieldConstant.getInstance().TYPE_ID + "=" + typeId + "&" +
                FieldConstant.getInstance().FEED_TXT + "=" + EncodeUtils.getInstance().getEncodeString(feedTxt), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("提交成功");
                    etSuggestBack.setText("");
                    SuggestBackActivity.this.finish();
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

}
