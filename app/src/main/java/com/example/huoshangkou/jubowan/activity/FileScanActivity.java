package com.example.huoshangkou.jubowan.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.CoordinatorLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.utils.FileUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.tencent.smtt.sdk.TbsReaderView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：FileScanActivity
 * 描述：
 * 创建时间：2020-03-10  09:23
 */

public class FileScanActivity extends BaseActivity {
    @Bind(R.id.fl_file)
    FrameLayout frameLayout;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    private String path = "";
    private String type = "";
    private String name = "";

    @Override
    public int initLayout() {
        return R.layout.activity_file_scan;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        path = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        name = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        tvTitle.setText(name);
        openFile(path);
    }


    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }

    TbsReaderView readerView;

    /**
     * Error:Execution failed for task ':app:transformClassesWithJarMergingForDebug'.
     * > com.android.build.api.transform.TransformException: java.util.zip.ZipException: duplicate entry: MTT/ThirdAppInfoNew.class
     *
     * @param path
     */
    private void openFile(String path) {
        readerView = new TbsReaderView(this, new TbsReaderView.ReaderCallback() {
            @Override
            public void onCallBackAction(Integer integer, Object o, Object o1) {

            }
        });
        //通过bundle把文件传给x5,打开的事情交由x5处理
        Bundle bundle = new Bundle();
        //传递文件路径
        bundle.putString("filePath", path);
        //加载插件保存的路径
        bundle.putString("tempPath", Environment.getExternalStorageDirectory() + File.separator + "temp");
        //加载文件前的初始化工作,加载支持不同格式的插件
        boolean b = readerView.preOpen(getFileType(path), false);
        if (b) {
            readerView.openFile(bundle);
        }
        frameLayout.addView(readerView);
    }

    /***
     * 获取文件类型
     *
     * @param path 文件路径
     * @return 文件的格式
     */
    private String getFileType(String path) {
        String str = "";

        if (TextUtils.isEmpty(path)) {
            return str;
        }
        int i = path.lastIndexOf('.');
        if (i <= -1) {
            return str;
        }
        str = path.substring(i + 1);
        return str;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (readerView != null) {
            readerView.onStop();
            readerView.destroyDrawingCache();
        }
    }
}
