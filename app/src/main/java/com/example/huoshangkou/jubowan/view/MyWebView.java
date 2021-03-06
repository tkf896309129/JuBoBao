package com.example.huoshangkou.jubowan.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.huoshangkou.jubowan.R;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.view
 * 类名：MyWebView
 * 描述：
 * 创建时间：2017-11-20  09:44
 */

public class MyWebView  extends WebView {
    private ProgressBar progressbar;
    private Context mContext;

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
//        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressbar = (ProgressBar) LayoutInflater.from(context).inflate(R.layout.progressbar, null);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 6, 0, 0));
        addView(progressbar);
        //        setWebViewClient(new WebViewClient(){});
        setWebChromeClient(new WebChromeClient());
        setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(mContext, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }
        });
        this.getSettings().setBuiltInZoomControls(true);
        this.getSettings().setUseWideViewPort(true);
    }

    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);
                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
