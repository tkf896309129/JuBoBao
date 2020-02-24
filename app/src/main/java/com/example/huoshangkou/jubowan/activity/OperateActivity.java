package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.EpOperateMemberAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.OperateMemberBean;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：OperateActivity
 * 描述：运营人员
 * 创建时间：2019-03-06  09:09
 */

public class OperateActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ep_operate_member)
    ExpandableListView epOperateMember;
    @Bind(R.id.smart)
    SmartRefreshLayout smart;
    EpOperateMemberAdapter memberAdapter;

    private List<String> listGroup = new ArrayList<>();
    private List<List<OperateMemberBean.DBean.ReObjBean.OperatorsBean>> listChild = new ArrayList<>();

    @Override
    public int initLayout() {
        return R.layout.activity_operate_member;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("运营人员");
        memberAdapter = new EpOperateMemberAdapter(listGroup, listChild, this);
        epOperateMember.setAdapter(memberAdapter);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        epOperateMember.setIndicatorBounds(width, width);
        epOperateMember.setDividerHeight(0);

        memberAdapter.setStringCallBack(new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                Intent intent = new Intent();
                intent.putExtra(IntentUtils.getInstance().TYPE, str);
                setResult(1, intent);
                OperateActivity.this.finish();
            }

            @Override
            public void onFail() {

            }
        });


        getOperateMember();
    }

    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }

    //运营人员
    public void getOperateMember() {
        OkhttpUtil.getInstance().basePostCall(this,  UrlConstant.getInstance().YW_URL + "GetOperators", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                OperateMemberBean memberBean = JSON.parseObject(str, OperateMemberBean.class);
                List<OperateMemberBean.DBean.ReObjBean> reObj = memberBean.getD().getReObj();
                int num = reObj.size();
                for (int i = 0; i < num; i++) {
                    listGroup.add(reObj.get(i).getRoleName());
                    listChild.add(reObj.get(i).getOperators());
                }
                memberAdapter.notifyDataSetChanged();
                for (int i = 0; i < listGroup.size(); i++) {
                    epOperateMember.expandGroup(i);
                }

            }

            @Override
            public void onFail() {

            }
        });

    }

}
