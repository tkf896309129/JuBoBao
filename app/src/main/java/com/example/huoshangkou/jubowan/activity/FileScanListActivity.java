package com.example.huoshangkou.jubowan.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.FileScanListAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.FileListBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnPositionCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.MineLoadingDialogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：FileScanListActivity
 * 描述：
 * 创建时间：2020-03-10  10:04
 */

public class FileScanListActivity extends BaseActivity {
    @Bind(R.id.lv_file)
    ListView lvFile;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    List<FileListBean.DBean.ReListBean> list = new ArrayList<>();
    FileScanListAdapter listAdapter;

    private String id = "";
    private String postUrl = "";
    private String typeStudy = "";
    private String title = "";
    private String roleFileType = "";
    private int clickPosition = -1;

    @Override
    public int initLayout() {
        return R.layout.activity_file_scan_list;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        id = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        typeStudy = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        title = getIntent().getStringExtra(IntentUtils.getInstance().STR);
        roleFileType = getIntent().getStringExtra(IntentUtils.getInstance().TXT);
        tvTitle.setText(title);
        switch (typeStudy) {
            case "通知制度区":
                postUrl = "GetNoticeFileDownload";
                break;
            case "技能学习区":
                postUrl = "GetSkillFileDownload";
                break;
        }
        listAdapter = new FileScanListAdapter(this, list, roleFileType, R.layout.item_file_list);
        lvFile.setAdapter(listAdapter);
        lvFile.setDividerHeight(0);
        getFileList();

        listAdapter.setDownLoadCallBakc(new OnPositionCallBack() {
            @Override
            public void onPositionClick(int position) {
                clickPosition = position;
                if (ContextCompat.checkSelfPermission(FileScanListActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(FileScanListActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(FileScanListActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE}, 101);
                } else {
                    downFile(list.get(position).getFileUrl(), list.get(position).getFileName(), position);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 101:
                if (clickPosition == -1) {
                    return;
                }
                downFile(list.get(clickPosition).getFileUrl(), list.get(clickPosition).getFileName(), clickPosition);
                break;
        }
    }

    //下载文件
    public void downFile(String url, String name, final int position) {
        final AlertDialog alertDialog = MineLoadingDialogUtils.updateDialog(this, "下载中...");
        final TextView tvShow = alertDialog.getWindow().findViewById(R.id.tv_show_message);
        LogUtils.e(Environment.getExternalStorageDirectory().getAbsolutePath() + "Environment.getExternalStorageDirectory().getAbsolutePath()");
        OkHttpUtils.get().url(url).build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), name) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        alertDialog.dismiss();
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        tvShow.setText("下载中...  " + (int) (progress * 100) + "%");
                    }

                    @Override
                    public void onResponse(File response, int id) {
//                        openFile(response.getAbsolutePath());
                        listAdapter.notifyDataSetChanged();
                        alertDialog.dismiss();
                        IntentUtils.getInstance().toActivity(FileScanListActivity.this, FileScanActivity.class, list.get(position).getFilePath(), list.get(position).getFileName());
                    }
                });
    }


    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }

    //获取文件列表
    public void getFileList() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("userId", PersonConstant.getInstance().getUserId());
        String json = JSON.toJSONString(map);
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().COMPANY_INFORM + postUrl, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                FileListBean listBean = JSON.parseObject(str, FileListBean.class);
                if (listBean.getD() != null) {
                    list.addAll(listBean.getD().getReList());
                }
                int num = list.size();
                for (int i = 0; i < num; i++) {
                    list.get(i).setFilePath(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + list.get(i).getFileName());
                }
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
    }
}
