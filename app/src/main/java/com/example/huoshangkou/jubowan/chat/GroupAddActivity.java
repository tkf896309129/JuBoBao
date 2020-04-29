package com.example.huoshangkou.jubowan.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.SelectAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.MemberBean;
import com.example.huoshangkou.jubowan.bean.MemberListBean;
import com.example.huoshangkou.jubowan.bean.SelectManBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.fragment.CheckByManFragment;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.widget.ContactItemInterface;
import com.example.huoshangkou.jubowan.widget.ContactListViewImpl;
import com.example.huoshangkou.jubowan.widget.pinyin.PinYin;
import com.tencent.qcloud.tim.uikit.modules.group.info.GroupInfo;
import com.tencent.qcloud.tim.uikit.modules.group.member.GroupMemberInviteFragment;
import com.tencent.qcloud.tim.uikit.utils.TUIKitConstants;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.chat
 * 类名：GroupAddActivity
 * 描述：
 * 创建时间：2020-04-20  11:27
 */

public class GroupAddActivity extends BaseActivity {

    private String invite = "";
    private String groupId = "";

    @Override
    public int initLayout() {
        return R.layout.activity_add_group_member;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        getData();
    }

    private ArrayList<MemberListBean> memberList = new ArrayList<>();

    //获取数据
    private void getData() {
        invite = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        groupId = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        OkhttpUtil.getInstance().setUnCacheData(this, "数据加载中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_EMPLOYEE_LIST
                + FieldConstant.getInstance().TYPE + "=0&"
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                MemberBean memberBean = JSON.parseObject(json, MemberBean.class);
                memberList.addAll(memberBean.getReList());
                Fragment fragment2 = CheckByManFragment.newInstance();
                Bundle bundle2 = new Bundle();
                bundle2.putParcelableArrayList("manList", memberList);
                bundle2.putString(IntentUtils.getInstance().TYPE, "addGroup");
                bundle2.putString(IntentUtils.getInstance().VALUE, invite);
                bundle2.putString(IntentUtils.getInstance().STR, groupId);
                fragment2.setArguments(bundle2);
                getSupportFragmentManager().beginTransaction().replace(R.id.rl_empty, fragment2).commit();
            }

            @Override
            public void onFail() {

            }
        });
    }

}
