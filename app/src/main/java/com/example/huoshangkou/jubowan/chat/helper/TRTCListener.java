package com.example.huoshangkou.jubowan.chat.helper;

import android.os.Bundle;

import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.tencent.trtc.TRTCCloudListener;

import java.util.ArrayList;

public class TRTCListener extends TRTCCloudListener {

    private static final String TAG = TRTCListener.class.getSimpleName();

    private static TRTCListener sTRTCListener;
    private ArrayList<TRTCCloudListener> mList = new ArrayList<>();

    private TRTCListener() {

    }

    public static TRTCListener getInstance() {
        if (sTRTCListener == null) {
            sTRTCListener = new TRTCListener();
        }
        return sTRTCListener;
    }

    public void addTRTCCloudListener(TRTCCloudListener l) {
        if (mList.contains(l)) {
            return;
        }
        mList.add(l);
    }

    public void removeTRTCCloudListener(TRTCCloudListener l) {
        if (l == null) {
            mList.clear();
        } else {
            mList.remove(l);
        }
    }

    @Override
    public void onError(int errCode, String errMsg, Bundle extraInfo) {
        super.onError(errCode, errMsg, extraInfo);
        LogUtils.i(TAG, "onError " + errCode + " " + errMsg);
        for (TRTCCloudListener l : mList) {
            l.onError(errCode, errMsg, extraInfo);
        }
    }

    @Override
    public void onWarning(int warningCode, String warningMsg, Bundle extraInfo) {
        super.onWarning(warningCode, warningMsg, extraInfo);
        LogUtils.i(TAG, "onWarning " + warningCode + " " + warningMsg);
        for (TRTCCloudListener l : mList) {
            l.onWarning(warningCode, warningMsg, extraInfo);
        }
    }

    @Override
    public void onEnterRoom(long elapsed) {
        super.onEnterRoom(elapsed);
        LogUtils.i(TAG, "onEnterRoom " + elapsed);
        for (TRTCCloudListener list : mList) {
            list.onEnterRoom(elapsed);
        }
    }

    @Override
    public void onExitRoom(int reason) {
        super.onExitRoom(reason);
        LogUtils.i(TAG, "onExitRoom " + reason);
        for (TRTCCloudListener l : mList) {
            l.onExitRoom(reason);
        }
    }

    @Override
    public void onRemoteUserEnterRoom(String userId) {
        super.onRemoteUserEnterRoom(userId);
        LogUtils.i(TAG, "onRemoteUserEnterRoom " + userId);
        for (TRTCCloudListener l : mList) {
            l.onRemoteUserEnterRoom(userId);
        }
    }

    @Override
    public void onRemoteUserLeaveRoom(String userId, int reason) {
        super.onRemoteUserLeaveRoom(userId, reason);
        LogUtils.i(TAG, "onRemoteUserLeaveRoom " + userId + " " + reason);
        for (TRTCCloudListener l : mList) {
            l.onRemoteUserLeaveRoom(userId, reason);
        }
    }

    @Override
    public void onUserVideoAvailable(String userId, boolean available) {
        super.onUserVideoAvailable(userId, available);
        LogUtils.i(TAG, "onUserVideoAvailable " + userId + " " + available);
        for (TRTCCloudListener l : mList) {
            l.onUserVideoAvailable(userId, available);
        }
    }

    @Override
    public void onFirstVideoFrame(String userId, int steamType, int width, int height) {
        super.onFirstVideoFrame(userId, steamType, width, height);
        LogUtils.i(TAG, "onFirstVideoFrame " + userId + " " + steamType + " " + width + " " + height);
        for (TRTCCloudListener l : mList) {
            l.onFirstVideoFrame(userId, steamType, width, height);
        }
    }

}
