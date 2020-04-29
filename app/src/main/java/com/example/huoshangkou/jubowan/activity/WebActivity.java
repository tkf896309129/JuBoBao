package com.example.huoshangkou.jubowan.activity;

import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.just.agentweb.AgentWeb;
import com.tencent.smtt.sdk.WebView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：WebActivity
 * 描述：
 * 创建时间：2017-05-15  10:09
 */

public class WebActivity extends BaseActivity {
    String url = "";
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.web_layout)
    LinearLayout llWebView;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.btn_next)
    TextView tvNext;
    @Bind(R.id.cb_agree)
    CheckBox cbAgree;

    //是否同意
    private boolean isAgree = false;
    private String title = "";
    //是否用红包
    private String isRedPacket = "";
    private String time = "";
    private String orderId = "";

    //btnTitle
    private boolean isWeiTuo = false;
    //是否可以编辑委托书
    private String isWeiEdit = "1";
    private String typeClick = "";
    //是否是 文件预览
    private boolean isFileObserve = false;
    private boolean isCurrentDay = false;
    //制度通知类型
    private String studyType = "";
    //友盟分享回调
    private UMShareListener umShareListener;
    //文件类型 0普通 1重要 2私密
    private String roleFileType = "";
    AgentWeb mAgentWeb;

    @Override
    public int initLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().padPayList);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        url = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        LogUtils.e(url);
        title = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        time = getIntent().getStringExtra(IntentUtils.getInstance().TXT);
        isWeiEdit = getIntent().getStringExtra(IntentUtils.getInstance().STR);
        typeClick = getIntent().getStringExtra(IntentUtils.getInstance().TYPE_CLICK);
        studyType = getIntent().getStringExtra(IntentUtils.getInstance().TYPE_DETAILS);
        roleFileType = getIntent().getStringExtra(IntentUtils.getInstance().PEOPLE_TYPE);
        String btnTitle = getIntent().getStringExtra(IntentUtils.getInstance().BTN_TITLE);
        if (StringUtils.isNoEmpty(btnTitle)) {
            tvNext.setVisibility(View.VISIBLE);
            tvNext.setText(btnTitle);
        }
        if (StringUtils.isNoEmpty(title)) {
            tvTitle.setText(title);
        }
        if (!StringUtils.isNoEmpty(url)) {
            return;
        }
        if (StringUtils.isNoEmpty(typeClick) && typeClick.equals("订单确认书")) {
            cbAgree.setVisibility(View.VISIBLE);
        }

        String notiUrl = (String) SharedPreferencesUtils.getInstance().get(this, SharedKeyConstant.getInstance().NOTIFY_CLICK, "");
        if (StringUtils.isNoEmpty(notiUrl)) {
            url = notiUrl;
            SharedPreferencesUtils.getInstance().put(this, SharedKeyConstant.getInstance().NOTIFY_CLICK, "");
        }

        //设置不用系统浏览器打开,直接显示在当前Webview
        mAgentWeb = AgentWeb.with(this)//传入Activity
                .setAgentWebParent(llWebView, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .createAgentWeb()//
                .ready()
                .go(url);

        /**设置支持缩放*/
        mAgentWeb.getAgentWebSettings().getWebSettings().setSupportZoom(true);
        mAgentWeb.getAgentWebSettings().getWebSettings().setBuiltInZoomControls(true);
        mAgentWeb.getAgentWebSettings().getWebSettings().setDisplayZoomControls(true);
        mAgentWeb.getAgentWebSettings().getWebSettings().setTextZoom(100);

        if (StringUtils.isNoEmpty(time)) {
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String times = df.format(date);
            LogUtils.i(times);
            LogUtils.i(time);
            try {
                String[] split = times.split(" ");
                String[] split1 = split[0].split("-");
                LogUtils.i(split[0]);
                String time_str = split1[0] + split1[1] + split1[2];
                LogUtils.i(time_str);

                String[] split2 = time.split(" ");
                LogUtils.i(split2[0]);
                String[] split_2 = split2[0].split("/");
                String time2_str = split_2[0] + split_2[1] + split_2[2];
                LogUtils.i(time2_str);

                //判断是否是当天
                if (time_str.equals(time2_str)) {
                    isCurrentDay = true;
                }
            } catch (Exception e) {

            }
        }

        tvRight.setText("分享");
        isRedPacket = getIntent().getStringExtra(IntentUtils.getInstance().STR);
        orderId = getIntent().getStringExtra(IntentUtils.getInstance().ORDER_ID);

        if (StringUtils.isNoEmpty(isRedPacket)) {
            tvRight.setVisibility(View.VISIBLE);
        } else {
            tvRight.setVisibility(View.GONE);
        }
        if (StringUtils.isNoEmpty(isWeiEdit) && isWeiEdit.equals("file")) {
            tvRight.setText("文件列表");
            isFileObserve = true;
            orderId = time;
        }

        if (StringUtils.isNoEmpty(orderId) && !StringUtils.isNoEmpty(typeClick)) {
            isWeiTuo = true;
            tvRight.setVisibility(View.VISIBLE);
            if (isWeiEdit.equals("1")) {
                tvRight.setText("编辑");
            } else if (isWeiEdit.equals("0")) {
                tvRight.setText("");
            }
        }


        //分享帖子
        umShareListener = new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onResult(SHARE_MEDIA share_media) {

//                if (!LoginUtils.getInstance().isLogin(getContext())) {
//                    CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "您还没有登录，暂时无法领取红包", new CopyIosDialogUtils.ErrDialogCallBack() {
//                        @Override
//                        public void confirm() {
//
//                        }
//                    });
//                    return;
//                }
//                if (!isCurrentDay) {
//                    CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "分享当天的聚玻早报或者聚玻资讯才能抢红包", new CopyIosDialogUtils.ErrDialogCallBack() {
//                        @Override
//                        public void confirm() {
//
//                        }
//                    });
//                    return;
//                }
//                GetPacketUtils.getPacketDialogShow(getContext());
            }

            @Override
            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "分享失败了", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {

            }
        };

        cbAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isAgree = b;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mAgentWeb==null){
            return;
        }
        mAgentWeb.destroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mAgentWeb == null || !mAgentWeb.back()) {
                WebActivity.this.finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.ll_back, R.id.tv_right, R.id.btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                if (mAgentWeb == null || !mAgentWeb.back()) {
                    WebActivity.this.finish();
                }
                break;
            case R.id.tv_right:
                if (isFileObserve) {
                    IntentUtils.getInstance().toActivity(this, FileScanListActivity.class, orderId, studyType, title, roleFileType);
                    return;
                }
                if (isWeiTuo) {
                    //委托书
                    IntentUtils.getInstance().toActivity(WebActivity.this, WeiTuoShuActivity.class, orderId);
                    return;
                }
                UMImage umImage = new UMImage(WebActivity.this, R.mipmap.share_icon);
                UMWeb umWeb = new UMWeb(url);
                umWeb.setThumb(umImage);
                umWeb.setTitle(title);

                LogUtils.i(title);
                new ShareAction(WebActivity.this)
//                        .setDisplayList(displaylist)
                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                        .withMedia(umWeb)
                        .setCallback(umShareListener)
                        .share();
                break;
            case R.id.btn_next:
                switch (typeClick) {
                    case "垫付款支付":
                        IntentUtils.getInstance().toActivity(this, DianPayConfirmActivity.class, orderId);
                        break;
                    case "订单确认书":
                        if (!isAgree) {
                            ToastUtils.getMineToast("请阅读并同意签订收货确认书");
                            return;
                        }
                        confirmReceive(orderId);
                        break;
                }
                break;
        }
    }


    //确认收货
    public void confirmReceive(String id) {
        OkhttpUtil.getInstance().setUnCacheData(this, getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().CONFIRM_RECEPIT
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().ORDER_ID + "=" + id, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                LogUtils.e(json);
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("收货成功");
                    SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().IS_INIT_ORDER, "yes");
                    ToastUtils.getMineToast("确认成功");
                    WebActivity.this.finish();
                    ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().orderList);
                } else {
                    ToastUtils.getMineToast(successBean.getErrMsg());
                }

            }

            @Override
            public void onFail() {

            }
        });
    }
}
