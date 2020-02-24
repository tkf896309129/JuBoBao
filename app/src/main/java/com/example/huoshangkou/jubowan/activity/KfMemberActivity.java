package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.KfMemberAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.KfMemberBean;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：KfMemberActivity
 * 描述：客服人员
 * 创建时间：2019-02-27  13:23
 */

public class KfMemberActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.ll_back)
    LinearLayout llBack;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.lv_kf_member)
    ListView lvKfMember;

    KfMemberAdapter memberAdapter;
    List<KfMemberBean.DBean.ReObjBean> list = new ArrayList<>();


    @Override
    public int initLayout() {
        return R.layout.activity_kf_member;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("运营人员");
        memberAdapter = new KfMemberAdapter(this, list, R.layout.item_operate_child);
        lvKfMember.setAdapter(memberAdapter);
        lvKfMember.setDividerHeight(0);

        lvKfMember.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra(IntentUtils.getInstance().TYPE, list.get(i).getRealName());
                intent.putExtra(IntentUtils.getInstance().VALUE, list.get(i).getID()+"");
                setResult(1, intent);
                KfMemberActivity.this.finish();
            }
        });

        getMembers();
    }

    //获取运营人员
    public void getMembers() {
        OkhttpUtil.getInstance().basePostCall(this,  UrlConstant.getInstance().YW_URL + "GetApprovalUsers", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                KfMemberBean memberBean = JSON.parseObject(str, KfMemberBean.class);
                list.addAll(memberBean.getD().getReObj());
                memberAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
    }
}
