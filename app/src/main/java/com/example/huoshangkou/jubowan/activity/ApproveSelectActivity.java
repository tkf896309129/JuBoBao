package com.example.huoshangkou.jubowan.activity;

import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.ApproveSelectFunction;
import com.example.huoshangkou.jubowan.adapter.ApproveChooseAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ApproveTypeBean;
import com.example.huoshangkou.jubowan.bean.ApproveTypeListBean;
import com.example.huoshangkou.jubowan.inter.OnApproveTypeCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：ApproveSelectActivity
 * 描述：业务员认证选择界面
 * 创建时间：2017-03-07  09:02
 */

public class ApproveSelectActivity extends BaseActivity {

    @Bind(R.id.lv_approve_type)
    ScrollListView lvApproveChoose;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    ApproveChooseAdapter chooseAdapter;
    List<ApproveTypeListBean> approveList;

    private String state = "";
    private String type = "";

    @Override
    public int initLayout() {
        return R.layout.activity_approve_select;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().approveList);
        approveList = new ArrayList<>();

        tvTitle.setText("认证选择");

        type = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        state = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);

        if (StringUtils.isNoEmpty(type) && StringUtils.isNoEmpty(state) && state.equals("审核中")) {
            CopyIosDialogUtils.getInstance().getNoCancelIosDialog(getContext(), "您目前认证正在审核，是否前往编辑", new CopyIosDialogUtils.iosDialogClick() {
                @Override
                public void confimBtn() {
                    switch (type) {
                        case "1":
                            IntentUtils.getInstance().toActivity(getContext(), BuyApproveActivity.class, "1", state);
                            break;
                        case "2":
                            IntentUtils.getInstance().toActivity(getContext(), BuyApproveActivity.class, "2", state);
                            break;
                        case "3":
                            IntentUtils.getInstance().toActivity(getContext(), YwyApproveActivity.class, "3", state);
                            break;
                        case "4":
                            IntentUtils.getInstance().toActivity(getContext(), YwyApproveActivity.class, "4", state);
                            break;
                        case "5":
                            IntentUtils.getInstance().toActivity(getContext(), YwyApproveActivity.class, "5", state);
                            break;
                        case "6":
                            IntentUtils.getInstance().toActivity(getContext(), YwyApproveActivity.class, "6", state);
                            break;
                        case "7":
                            IntentUtils.getInstance().toActivity(getContext(), RepairApproveActivity.class, "7", state);
                            break;
                        case "8":
                            IntentUtils.getInstance().toActivity(getContext(), BuyApproveActivity.class, "8", state);
                            break;
                        case "9":
                            IntentUtils.getInstance().toActivity(getContext(), PersonBusinessApprove.class, "9", state);
                            break;
                    }
                }

                @Override
                public void cancelBtn() {
                    ApproveSelectActivity.this.finish();
                }
            });

            return;
        }

        chooseAdapter = new ApproveChooseAdapter(this, approveList, R.layout.item_approve_choose);
        lvApproveChoose.setAdapter(chooseAdapter);
        lvApproveChoose.setDividerHeight(0);

        //加载数据
        ApproveSelectFunction.getInstance().getApproveType(this, new OnApproveTypeCallBack() {
            @Override
            public void onSuccess(ApproveTypeBean typeBean) {
                approveList.addAll(typeBean.getReList());
                chooseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });

        lvApproveChoose.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (approveList.get(i).getRenzhengid()) {
                    //加工厂 建筑商 机械配件厂
                    case 0:
                        IntentUtils.getInstance().toActivity(ApproveSelectActivity.this, BuyApproveActivity.class, approveList.get(i).getId() + "", state);
                        break;
                    //维修工程师
                    case 1:
                        IntentUtils.getInstance().toActivity(ApproveSelectActivity.this, RepairApproveActivity.class, approveList.get(i).getId() + "", state);
                        break;
                    //其他
                    case 2:
                        IntentUtils.getInstance().toActivity(ApproveSelectActivity.this, YwyApproveActivity.class, approveList.get(i).getId() + "", state);
                        break;
                    //个体工商户
                    case 3:
                        IntentUtils.getInstance().toActivity(ApproveSelectActivity.this, PersonBusinessApprove.class, approveList.get(i).getId() + "", state);
                        break;
                }
            }
        });
    }


    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().approveList);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().approveList);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
