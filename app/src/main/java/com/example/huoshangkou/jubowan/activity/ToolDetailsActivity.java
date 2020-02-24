package com.example.huoshangkou.jubowan.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.LoginUtils;
import com.example.huoshangkou.jubowan.utils.MineLoadingDialogUtils;
import com.example.huoshangkou.jubowan.utils.ShareUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：ToolDetailsActivity
 * 描述：设备详情界面
 * 创建时间：2017-06-15  10:00
 */

public class ToolDetailsActivity extends BaseActivity {
    @Bind(R.id.web_tool_detail)
    WebView webDetail;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    private AlertDialog dialog;
    private String url = "";
    private String productId = "";

    @Override
    public int initLayout() {
        return R.layout.activity_tool_detail;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        url = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        productId = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        tvTitle.setText("设备详情");

        dialog = MineLoadingDialogUtils.updateDialog(getContext(), "页面加载中");
        if (StringUtils.isNoEmpty(url)) {
            webDetail.loadUrl(url);
        }
        LogUtils.i(url);

        WebSettings webSettings = webDetail.getSettings();
        //设置为可调用js方法
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().acceptThirdPartyCookies(webDetail);
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webDetail.addJavascriptInterface(new JSInterface(getContext(), productId), "Android");


        webDetail.setWebViewClient(new WebViewClient() {
            //页面开始加载
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dialog.dismiss();
            }

            //页面加载结束
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                dialog.show();
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });

        webDetail.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                    /*看下等会有没有打印什么信息，看看是不是js执行错了*/
                Log.d("tangkaifeng", "onConsoleMessage: " + consoleMessage.message());
                return super.onConsoleMessage(consoleMessage);
            }
        });
    }

    @OnClick({R.id.ll_back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_right:
                ShareUtils.getInstance().shareContent(getContext(), url, "设备详情");
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        String str = (String) SharedPreferencesUtils.getInstance().get(getContext(), SharedKeyConstant.getInstance().TOOL_REFRESH, "");
        if (StringUtils.isNoEmpty(str)) {
            webDetail.loadUrl(url);
            SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().TOOL_REFRESH, "");
        }
    }
}

class JSInterface {
    private Context mContext;
    private String id;

    JSInterface(Context mContext, String id) {
        this.mContext = mContext;
        this.id = id;
    }

    @JavascriptInterface
    public void GoTo() {
        //oh le a,这个方法名你自己去的
        if (!LoginUtils.getInstance().isLogin(mContext)) {
            IntentUtils.getInstance().toActivity(mContext, LoginActivity.class);
            return;
        }
        IntentUtils.getInstance().toActivity(mContext, ToolSuggestActivity.class, id);
    }
}
