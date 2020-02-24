package com.example.huoshangkou.jubowan.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.TieDetailsActivity;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：ShareUtils
 * 描述：
 * 创建时间：2017-06-26  13:39
 */

public class ShareUtils {

    private static class ShareHelper {
        public static ShareUtils INSTANCE = new ShareUtils();
    }

    public static ShareUtils getInstance() {
        return ShareHelper.INSTANCE;
    }

    final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]{
//                   SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA,
            SHARE_MEDIA.QQ,
            SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QZONE,
            SHARE_MEDIA.WEIXIN_CIRCLE
//                    , SHARE_MEDIA.DOUBAN
    };

    //友盟分享回调
    private UMShareListener umShareListener;

    public void shareContent(Context context, String url, String title) {


        UMImage umImage = new UMImage(context, R.mipmap.share_icon);
        UMWeb umWeb = new UMWeb(url);
        umWeb.setThumb(umImage);

        new ShareAction((Activity) context)
                .setDisplayList(displaylist)
                .withText(title)
                .withFollow("分享中")
                .withMedia(umWeb)
                .setCallback(umShareListener)
                .open();


        //分享帖子
        umShareListener = new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onResult(SHARE_MEDIA share_media) {
            }

            @Override
            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
            }
        };
    }
}
