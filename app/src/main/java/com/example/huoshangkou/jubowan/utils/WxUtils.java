package com.example.huoshangkou.jubowan.utils;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zero.smallvideorecord.Log;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：WxUtils
 * 描述：
 * 创建时间：2020-04-07  08:47
 */

public class WxUtils {

    private static WxUtils instance;

    private WxUtils() {

    }

    public static WxUtils getInstance() {
        if (instance == null) {
            return new WxUtils();
        }
        return instance;
    }

    public void toWxSmallPro(Context context,  String proId) {
        if (!isWeixinAvilible(context)) {
            CopyIosDialogUtils.getInstance().getErrorDialog(context, "您还没有安装微信", new CopyIosDialogUtils.ErrDialogCallBack() {
                @Override
                public void confirm() {

                }
            });
            return;
        }
        IWXAPI api = WXAPIFactory.createWXAPI(context, "wx077bb576b1c7629f");
        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
        req.userName = proId; // 小程序原始id
        req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE;
        api.sendReq(req);
    }


    /**
     * 检测是否安装微信
     *
     * @param context
     * @return
     */
    private boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }


    //跳转app
    public void jumpApp(final Context context, String pointName, String pointActivity, final String downUrl, final String appName) {
        try {
            ComponentName componentName = new ComponentName(pointName, pointActivity);
            Intent intent = new Intent();
            intent.setComponent(componentName);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            //判断是否安装B应用，提供下载链接
            CopyIosDialogUtils.getInstance().getIosDialog(context, appName + "未下载，是否前往下载", new CopyIosDialogUtils.iosDialogClick() {
                @Override
                public void confimBtn() {
                    IntentUtils.getInstance().toWebActivity(context, downUrl, appName + "下载");
                }

                @Override
                public void cancelBtn() {

                }
            });
            e.printStackTrace();
        }
    }

}
