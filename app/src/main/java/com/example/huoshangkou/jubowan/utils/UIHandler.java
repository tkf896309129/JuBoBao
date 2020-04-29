package com.example.huoshangkou.jubowan.utils;

import android.os.Handler;
import android.os.Message;

import com.example.huoshangkou.jubowan.inter.StringCallBack;

import java.lang.ref.WeakReference;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：UIHandler
 * 描述：
 * 创建时间：2019-08-19  15:19
 */

public class UIHandler extends Handler {
    private final WeakReference<StringCallBack> mTarget;


    public UIHandler(StringCallBack mTarget) {
        this.mTarget = new WeakReference<StringCallBack>(mTarget);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what){
            case 1:
                String json = (String) msg.obj;
                mTarget.get().onSuccess(json);
                break;
        }
    }
}
