package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.baidu.aip.asrwakeup3.core.util.FileUtil;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.FileScanActivity;
import com.example.huoshangkou.jubowan.activity.WebActivity;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.FileListBean;
import com.example.huoshangkou.jubowan.inter.OnPositionCallBack;
import com.example.huoshangkou.jubowan.utils.FileUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.view.CircleBarView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.io.File;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：FileScanListAdapter
 * 描述：
 * 创建时间：2020-03-10  11:51
 */

public class FileScanListAdapter extends BaseAbstractAdapter<FileListBean.DBean.ReListBean> {

    OnPositionCallBack downLoadCallBakc;
    private String mFileType = "";

    public FileScanListAdapter(Context context, List<FileListBean.DBean.ReListBean> listData, String fileType, int resId) {
        super(context, listData, resId);
        mFileType = fileType;
    }

    @Override
    public void convert(ViewHolder holder, final FileListBean.DBean.ReListBean bean, final int position) {
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvSize = holder.getView(R.id.tv_size);
        TextView tvDownload = holder.getView(R.id.tv_download);
        TextView tvScan = holder.getView(R.id.tv_scan);
        TextView tvShare = holder.getView(R.id.tv_share);

        tvName.setText(bean.getFileName());
        tvDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downLoadCallBakc.onPositionClick(position);
            }
        });
        tvSize.setText(bean.getFileSize() == null ? "文件大小：暂无" : "文件大小：" + bean.getFileSize());

        if (FileUtils.isFileExists(bean.getFilePath())) {
            tvDownload.setVisibility(View.INVISIBLE);
            tvScan.setVisibility(View.VISIBLE);
            if (StringUtils.isNoEmpty(mFileType) && mFileType.equals("0")) {
                tvShare.setVisibility(View.VISIBLE);
            }
        } else {
            tvScan.setVisibility(View.INVISIBLE);
            tvShare.setVisibility(View.INVISIBLE);
            tvDownload.setVisibility(View.VISIBLE);
        }

        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM,
                        Uri.fromFile(new File(bean.getFilePath())));
                shareIntent.setType("*/*");//此处可发送多种文件
                context.startActivity(Intent.createChooser(shareIntent, "分享到"));
            }
        });

        tvScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.getInstance().toActivity(context, FileScanActivity.class, bean.getFilePath(), bean.getFileName());
            }
        });
    }

    public void setDownLoadCallBakc(OnPositionCallBack downLoadCallBack) {
        this.downLoadCallBakc = downLoadCallBack;
    }
}
