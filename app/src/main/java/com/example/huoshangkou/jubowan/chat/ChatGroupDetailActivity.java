package com.example.huoshangkou.jubowan.chat;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.tencent.imsdk.TIMGroupManager;
import com.tencent.imsdk.TIMGroupMemberInfo;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.qcloud.tim.uikit.modules.group.info.GroupInfo;
import com.tencent.qcloud.tim.uikit.modules.group.info.GroupInfoFragment;
import com.tencent.qcloud.tim.uikit.modules.group.member.GroupMemberDeleteFragment;
import com.tencent.qcloud.tim.uikit.modules.group.member.GroupMemberInviteFragment;
import com.tencent.qcloud.tim.uikit.modules.group.member.GroupMemberManagerFragment;
import com.tencent.qcloud.tim.uikit.modules.group.member.IGroupMemberRouter;
import com.tencent.qcloud.tim.uikit.utils.TUIKitConstants;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.chat
 * 类名：ChatGroupDetailActivity
 * 描述：
 * 创建时间：2020-04-17  11:48
 */

public class ChatGroupDetailActivity extends BaseActivity {

    @Override
    public int initLayout() {
        return R.layout.group_info_activity;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this,ActivityUtils.getInstance().chatList);
        ChatGroupFragment fragment = new ChatGroupFragment();
        fragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().replace(R.id.group_manager_base, fragment).commitAllowingStateLoss();

    }

}
