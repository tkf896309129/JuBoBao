package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：LocalWebActivity
 * 描述：
 * 创建时间：2020-04-17  13:10
 */

public class LocalWebActivity extends BaseActivity {
    @Bind(R.id.web_view_local)
    WebView webView;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    private String data = "";

    @Override
    public int initLayout() {
        return R.layout.activity_local_web;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        data = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        String title = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        tvTitle.setText(title);
        //在内置浏览器打开页面
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        //图片宽度改为100%  高度为自适应
        String varjs = "<script type='text/javascript'> \nwindow.onload = function()\n{var $img = document.getElementsByTagName('img');for(var p in  $img){$img[p].style.width = '0'; $img[p].style.height ='0'}}</script>";
        webView.loadData(varjs + data, "text/html", "UTF-8");

    }

    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }


}
