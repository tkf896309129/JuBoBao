package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.huoshangkou.jubowan.R;
import com.umeng.message.UmengNotifyClickActivity;
import com.umeng.socialize.utils.Log;

import org.android.agoo.common.AgooConstants;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：MipushTestActivity
 * 描述：
 * 创建时间：2019-06-21  08:56
 */

public class MipushTestActivity extends UmengNotifyClickActivity {

    private static String TAG = MipushTestActivity.class.getName();

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mipush); //这里设置不同的页面，为了区分是友盟推送进来的，还是通道推送进来的
    }

    @Override
    public void onMessage(Intent intent) {
        super.onMessage(intent);  //此方法必须调用，否则无法统计打开数
        String body = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
        Log.i(TAG, body);
    }
}
