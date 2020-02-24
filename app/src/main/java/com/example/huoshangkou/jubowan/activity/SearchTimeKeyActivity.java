package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.KeyBoardUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.TimeDialogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：SearchTimeKeyActivity
 * 描述：
 * 创建时间：2019-09-29  09:29
 */

public class SearchTimeKeyActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_start_time)
    TextView tvStartTime;
    @Bind(R.id.tv_end_time)
    TextView tvEndTime;
    @Bind(R.id.et_key_word)
    EditText etKeyWord;
    @Bind(R.id.ll_start_time)
    LinearLayout llStartTime;
    @Bind(R.id.ll_end_time)
    LinearLayout llEndTime;


    private String startTime = "";
    private String endTime = "";
    private String keyWord = "";

    @Override
    public int initLayout() {
        return R.layout.activity_search_time_key;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("筛选");
        String type = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        if (StringUtils.isNoEmpty(type)) {
            llStartTime.setVisibility(View.GONE);
            llEndTime.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.ll_back, R.id.tv_commit, R.id.tv_start_time, R.id.tv_end_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_commit:
                keyWord = etKeyWord.getText().toString().trim();
                if (!StringUtils.isNoEmpty(keyWord) && !StringUtils.isNoEmpty(startTime) && !StringUtils.isNoEmpty(endTime)) {
                    ToastUtils.getMineToast("请选择筛选条件");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra(IntentUtils.getInstance().TYPE, keyWord);
                intent.putExtra(IntentUtils.getInstance().VALUE, startTime);
                intent.putExtra(IntentUtils.getInstance().STR, endTime);
                setResult(1, intent);
                finish();
                break;
            case R.id.tv_start_time:
                TimeDialogUtils.getInstance().getTime(getContext(), new StringCallBack() {
                    @Override
                    public void onSuccess(String str) {
                        startTime = str;
                        tvStartTime.setText(str);
                    }

                    @Override
                    public void onFail() {

                    }
                });

                KeyBoardUtils.closeKeybord(etKeyWord, getContext());

                break;
            case R.id.tv_end_time:
                TimeDialogUtils.getInstance().getTime(getContext(), new StringCallBack() {
                    @Override
                    public void onSuccess(String str) {
                        endTime = str;
                        tvEndTime.setText(str);
                    }

                    @Override
                    public void onFail() {

                    }
                });
                KeyBoardUtils.closeKeybord(etKeyWord, getContext());
                break;
        }
    }
}
