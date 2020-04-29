package com.example.huoshangkou.jubowan.chat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.chat.helper.ChatHelper;
import com.example.huoshangkou.jubowan.chat.signature.Constants;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.chat
 * 类名：ChatActivity
 * 描述：
 * 创建时间：2020-03-30  09:20
 */

public class ChatActivity extends BaseActivity {
    private ChatFragment mChatFragment;
    private ChatInfo mChatInfo;

    @Override
    public int initLayout() {
        return R.layout.activity_chat;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().activityLoginList);
        chat(getIntent());

        getUserInfo(mChatInfo.getId());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        chat(intent);
    }

         //获取制定用户信息
    public void getUserInfo(String id) {
        //待获取用户资料的用户列表
        List<String> users = new ArrayList<String>();
        users.add(id);
        LogUtils.e(id);
        //获取用户资料
        TIMFriendshipManager.getInstance().getUsersProfile(users, true, new TIMValueCallBack<List<TIMUserProfile>>() {
            @Override
            public void onError(int code, String desc) {
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
                CopyIosDialogUtils.getInstance().getErrorDialogNoCancel(getContext(), "该用户尚未注册!", new CopyIosDialogUtils.ErrDialogCallBack() {
                    @Override
                    public void confirm() {
                        ChatActivity.this.finish();
                    }
                });
                LogUtils.e("失败");
            }

            @Override
            public void onSuccess(List<TIMUserProfile> result) {
                LogUtils.e("成功");
                List<TIMUserProfile> result1 = result;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void chat(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            Uri uri = intent.getData();
            if (uri != null) {
                // 离线推送测试代码，oppo scheme url解析
                Set<String> set = uri.getQueryParameterNames();
                if (set != null) {
                    for (String key : set) {
                        String value = uri.getQueryParameter(key);
                    }
                }
            }
            finish();
        } else {
            // 离线推送测试代码，华为和oppo可以通过在控制台设置打开应用内界面为ChatActivity来测试发送的ext数据
            String ext = bundle.getString("ext");

            Set<String> set = bundle.keySet();
            if (set != null) {
                for (String key : set) {
                    String value = bundle.getString(key);
                }
            }
            // 离线推送测试代码结束
            mChatInfo = (ChatInfo) bundle.getSerializable(Constants.CHAT_INFO);
            if (mChatInfo == null) {
                finish();
                return;
            }
            mChatFragment = new ChatFragment();
            mChatFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.empty_view, mChatFragment).commitAllowingStateLoss();
        }
    }

}
