package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.MineApproveFunction;
import com.example.huoshangkou.jubowan.adapter.FragmentChangeAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.constant.ApproveConstant;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.zero.smallvideorecord.Log;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：MineApproveActivity
 * 描述：我的审批
 * 创建时间：2017-03-17  09:32
 */

public class MineApproveActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tab_mine_approve)
    TabLayout tabMineApprove;
    @Bind(R.id.vp_mine_approve)
    ViewPager vpMineApprove;

    private FragmentChangeAdapter fragmentChangeAdapter;
    List<Fragment> fragmentList;

    //判断是审批还是申请类型
    private String approveType = "";
    //是否是审批
    private boolean isCheckApprove = false;

    @Override
    public int initLayout() {
        return R.layout.activity_mine_approve;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().approveCheckList);
        Intent intent = getIntent();
        
        if (intent != null) {//
            approveType = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
            Bundle bundle = intent.getExtras();
            if(bundle!=null){
                approveType = bundle.getString(IntentUtils.getInstance().TYPE);
            }
        }
        //通知判断
        String notiy = (String) SharedPreferencesUtils.getInstance().get(this, SharedKeyConstant.getInstance().NOTIFY_CLICK, "");
        if (StringUtils.isNoEmpty(notiy)) {
            approveType = notiy;
            SharedPreferencesUtils.getInstance().put(this, SharedKeyConstant.getInstance().NOTIFY_CLICK, "");
        }

        tvRight.setText("筛选");
        if(!StringUtils.isNoEmpty(approveType)){
            return;
        }
        //我的审批
        if (approveType.equals(ApproveConstant.getInstance().APPROVE)) {
            isCheckApprove = true;
            MineApproveFunction.getMineApprove(tvTitle, fragmentList, tabMineApprove, fragmentChangeAdapter, getSupportFragmentManager(), vpMineApprove);
            //我的申请
        } else if (approveType.equals(ApproveConstant.getInstance().APPLY)) {
            isCheckApprove = false;
            MineApproveFunction.getMineApply(tvTitle, fragmentList, tabMineApprove, fragmentChangeAdapter, getSupportFragmentManager(), vpMineApprove);
        } else if (approveType.equals(ApproveConstant.getInstance().MINE_ZH)) {
            isCheckApprove = false;
            MineApproveFunction.getMineZhApply(tvTitle, fragmentList, tabMineApprove, fragmentChangeAdapter, getSupportFragmentManager(), vpMineApprove);
        }
    }

    @OnClick({R.id.ll_back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_right:
                //1 审批 0 申请
                if (isCheckApprove) {
                    IntentUtils.getInstance().toActivity(getContext(), ApproveCheckActivity.class, "1");
                } else {
                    if (approveType.equals(ApproveConstant.getInstance().MINE_ZH)) {
                        IntentUtils.getInstance().toActivity(getContext(), ApproveCheckActivity.class, "2");
                        return;
                    }
                    IntentUtils.getInstance().toActivity(getContext(), ApproveCheckActivity.class, "0");
                }
                break;
        }
    }
}
