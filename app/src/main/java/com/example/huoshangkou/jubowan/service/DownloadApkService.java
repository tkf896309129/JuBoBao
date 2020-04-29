package com.example.huoshangkou.jubowan.service;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.content.FileProvider;
import android.webkit.MimeTypeMap;

import android.widget.Toast;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.utils.VersionUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.service
 * 类名：DownloadApkService
 * 描述：下载
 * 创建时间：2017-01-18  11:25
 */

public class DownloadApkService extends Service {

    /**
     * 安卓系统下载类
     **/
    private DownloadManager manager;
    /**
     * 接收下载完的广播
     **/
    private DownloadCompleteReceiver receiver;
    private String url = "";
    private String DOWNLOADPATH = "/apk/";//下载路径，如果不定义自己的路径，6.0的手机不自动安装
    private String apkPath = "";
    //文件名
    private String fileName = "jubobao.apk";

    /**
     * 初始化下载器
     **/
    private void initDownManager() {
        //获取下载地址
        url = VersionUtils.getInstance().getVersionPath();
//        url = "http://server.atjubo.com:11943\\AndroidAPK\\JuBoBao_4.6.5.apk";
        LogUtils.e(url);
        ToastUtils.getMineToast(getString(R.string.DownApkBegain));
        manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        receiver = new DownloadCompleteReceiver();
        //设置下载地址
        DownloadManager.Request down = new DownloadManager.Request(Uri.parse(url));
        // 设置允许使用的网络类型，这里是移动网络和wifi都可以
        down.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE
                | DownloadManager.Request.NETWORK_WIFI);
        down.setAllowedOverRoaming(false);
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(url));
        down.setMimeType(mimeString);
        // 下载时，通知栏显示途中
//        down.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        down.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        // 显示下载界面
        down.setVisibleInDownloadsUi(true);
        // 设置下载后文件存放的位置
        down.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        //设置下载图标
        //设置描述
        down.setDescription(getString(R.string.DownloadDesc));
        //设置下载标题
        down.setTitle(getString(R.string.DownloadTitle));
        // 将下载请求放入队列
        manager.enqueue(down);
        //注册下载广播
        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        url = intent.getStringExtra("url");
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + DOWNLOADPATH + fileName;
        apkPath = path;
        File file = new File(path);
        LogUtils.e(apkPath);
        if (file.exists()) {
            file.delete();
        }
        try {
            // 调用下载
            initDownManager();
//            nm = (NotificationManager) getSystemService(Activity.NOTIFICATION_SERVICE);
//            builder = new NotificationCompat.Builder(getBaseContext());
//            String url = "http://10.0.2.2:8080/HttpTest/weixin.apk";
//            new MyDownloadAnsy().execute(url);//自定义notification

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "下载失败", Toast.LENGTH_SHORT).show();
        }
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onDestroy() {
        if (receiver != null)
            // 注销下载广播
            unregisterReceiver(receiver);
        super.onDestroy();
    }

    class DownloadCompleteReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //判断是否下载完成的广播
            if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                LogUtils.e("下载完成");
                //获取下载的文件id
                long downId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
//                boolean hasInstallPermission = getPackageManager().canRequestPackageInstalls();
//                if (!hasInstallPermission) {
//                    return;
//                }
                if (manager.getUriForDownloadedFile(downId) != null) {
                    install(context,apkPath);
                    //自动安装apk
//                    installAPK(manager.getUriForDownloadedFile(downId), context);
                    //installAPK(context);
                } else {
                    Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
                }
                //停止服务并关闭广播
                DownloadApkService.this.stopSelf();
            }
        }


        /**
         * 用于下载后安装
         *
         * @param context
         * @param apkPath
         */
        public  void install(final Context context, String apkPath) {
            File file = new File(Uri.parse(apkPath).getPath());
            String filePath = file.getAbsolutePath();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri data = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//判断版本大于等于7.0
                // 生成文件的uri// 注意 下面参数com.ausee.fileprovider 为apk的包名加上.fileprovider，
                data = FileProvider.getUriForFile(context, "com.example.huoshangkou.jubowan.fileprovider", new File(filePath));
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);// 给目标应用一个临时授权
            } else {
                data = Uri.fromFile(file);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }

            intent.setDataAndType(data, "application/vnd.android.package-archive");
            context.startActivity(intent);
        }

        private void installAPK(Uri apk, Context context) {
            if (Build.VERSION.SDK_INT < 23) {
                Intent intents = new Intent();
                intents.setAction("android.intent.action.VIEW");
                intents.addCategory("android.intent.category.DEFAULT");
                intents.setType("application/vnd.android.package-archive");
                intents.setData(apk);
                intents.setDataAndType(apk, "application/vnd.android.package-archive");
                intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intents);
            } else {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + DOWNLOADPATH + fileName);
                if (file.exists()) {
                    openFile(file, context);
                }
            }
        }

        private void installAPK(Context context) {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + DOWNLOADPATH + fileName);
            if (file.exists()) {
                openFile(file, context);
            } else {
                Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void openFile(File file, Context context) {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        String type = getMIMEType(file);
        intent.setDataAndType(Uri.fromFile(file), type);
        try {
            context.startActivity(intent);
        } catch (Exception var5) {
            var5.printStackTrace();
            Toast.makeText(context, "没有找到打开此类文件的程序", Toast.LENGTH_SHORT).show();
        }

    }

    public String getMIMEType(File var0) {
        String var1 = "";
        String var2 = var0.getName();
        String var3 = var2.substring(var2.lastIndexOf(".") + 1, var2.length()).toLowerCase();
        var1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(var3);
        return var1;
    }



}
