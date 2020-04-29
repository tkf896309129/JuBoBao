package com.example.huoshangkou.jubowan.utils;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.RedpacketDetailActivity;
import com.example.huoshangkou.jubowan.bean.RedPacketBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：GetPacketUtils
 * 描述：
 * 创建时间：2017-10-12  08:56
 */

public class GetPacketUtils {

    private static String price = "";

    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    imgGetPacket.setVisibility(View.GONE);
                    tvCheckOthers.setVisibility(View.VISIBLE);
                    rlLogo.setVisibility(View.GONE);
                    rlPrice.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    isRedPacket = true;
                    break;
            }
        }
    };

    static ImageView imgGetPacket;
    static TextView tvCheckOthers;
    static RelativeLayout rlPrice;
    static RelativeLayout rlLogo;

    //是否可以抢红包
    private static boolean isRedPacket = true;

    /**
     * 更新提示弹出框
     */
    public static void getPacketDialogShow(final Context context) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();

        final Window window = dialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        window.setContentView(R.layout.layout_get_packet);
        window.setBackgroundDrawable(context.getResources().getDrawable(R.color.alpha_all));
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        imgGetPacket = (ImageView) window.findViewById(R.id.iv_open_red_packet);
        imgGetPacket.setVisibility(View.VISIBLE);
        tvCheckOthers = (TextView) window.findViewById(R.id.tv_check_others);
        rlPrice = (RelativeLayout) window.findViewById(R.id.rl_price);
        rlLogo = (RelativeLayout) window.findViewById(R.id.rl_logo);
        ImageView imgPic2 = (ImageView) window.findViewById(R.id.iv_pic_2);
        ImageView imgPic = (ImageView) window.findViewById(R.id.iv_pic);
        ImageView imgClose = (ImageView) window.findViewById(R.id.iv_close);
        final TextView tvLogo1 = (TextView) window.findViewById(R.id.tv_11);
        final TextView tvLogo2 = (TextView) window.findViewById(R.id.tv_22);
        final TextView tvLogo3 = (TextView) window.findViewById(R.id.tv_44);
        final TextView tvPrice = (TextView) window.findViewById(R.id.tv_33);
        GlideUtils.getInstance().displayCricleImage(context, R.mipmap.new_icon, imgPic);
        GlideUtils.getInstance().displayCricleImage(context, R.mipmap.new_icon, imgPic2);
        tvCheckOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                IntentUtils.getInstance().toActivity(context, RedpacketDetailActivity.class, price);
            }
        });
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        imgGetPacket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isRedPacket) {
                    isRedPacket = false;
                    getRedPacket(context, tvPrice, tvLogo1, tvLogo2, tvLogo3);

                    handler.sendEmptyMessageDelayed(2, 1000);
                    ObjectAnimator anim = ObjectAnimator//
                            .ofFloat(view, "rotationY", 0.0F, -360.0F)//
                            .setDuration(1000);
                    anim.start();
                    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                                    0f, 0f);
                            ObjectAnimator anim2 = ObjectAnimator.ofPropertyValuesHolder(imgGetPacket, pvhX).setDuration(1000);
                            anim2.start();
                        }
                    });
                }
            }
        });
    }

    //抢红包接口
    public static void getRedPacket(Context context, final TextView tvPrice, final TextView tvLogo1, final TextView tvLogo2, final TextView tvLogo3) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(context, UrlConstant.getInstance().URL
                + PostConstant.getInstance().RED_WARS + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {

                RedPacketBean packetBean = JSON.parseObject(json, RedPacketBean.class);
                price = packetBean.getReObj().getLuckyMoney();
                tvPrice.setText(price);

                if (packetBean.getSuccess() == -1) {
                    tvPrice.setText("非常遗憾的告诉您：\n" + packetBean.getErrMsg());
                    price = packetBean.getErrMsg();
                    tvLogo1.setVisibility(View.GONE);
                    tvLogo2.setVisibility(View.GONE);
                    tvLogo3.setVisibility(View.INVISIBLE);
                }
                handler.sendEmptyMessageDelayed(1, 500);
//                isRedPacket = true;

            }

            @Override
            public void onFail() {

            }
        });
    }

}
