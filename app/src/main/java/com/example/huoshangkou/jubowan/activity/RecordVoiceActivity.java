package com.example.huoshangkou.jubowan.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.asrwakeup3.core.mini.AutoCheck;
import com.baidu.aip.asrwakeup3.core.recog.MyRecognizer;
import com.baidu.aip.asrwakeup3.core.recog.listener.IRecogListener;
import com.baidu.aip.asrwakeup3.core.recog.listener.MessageStatusRecogListener;
import com.baidu.aip.nlp.AipNlp;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.RecordAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.AccessTokenBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.MineLoadingDialogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PcmToWavUtil;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：RecordVoiceActivity
 * 描述：沉浸式的课堂
 * 创建时间：2019-08-14  14:59
 */

public class RecordVoiceActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_start)
    TextView tvStart;
    @Bind(R.id.lv_record)
    ListView lvRecord;

    List<String> list = new ArrayList<>();
    RecordAdapter recordAdapter;
    String fileName = "";
    private String accessToken = "";

    public static final String APP_ID = "17061515";
    public static final String API_KEY = "z6ctYlci55lKPUsDAAFgNu2I";
    public static final String SECRET_KEY = "xSflXuTzkGN67NPyiA9VcDsCGtUbItTE";

    //文件上传
    public final int UP_FILE = 1;
    //文件名
    private String fileDirName = "";
    //是否结束
    private int isEnd = 0;

    /**
     * 识别控制器，使用MyRecognizer控制识别的流程
     */
    protected MyRecognizer myRecognizer;
    private String content = "";

    protected Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) { // 处理MessageStatusRecogListener中的状态回调
                case 6:
                    if (msg.arg2 == 1) {
                        String txt = msg.obj.toString();
                        int start = txt.indexOf("，");
                        int end = txt.indexOf("；");
                        if (end != -1) {
                            content = txt.substring(start + 5, end - 1);
                        } else {
                            content = txt.substring(start + 5, txt.length() - 2);
                        }
                        getAnyKeyWord(content);
                        list.add(content);
                        recordAdapter.notifyDataSetChanged();
                    }
                    break;
                case UP_FILE:

                    break;
                case 2:
                    i++;
                    tvTitle.setText(DateUtils.cal(i));
                    if (i % 30 == 0) {
                        ToastUtils.getMineToast("上传文件");
                        stop();
                        File file = new File(fileName);
                        upVoiceFile(file);
                    }
                    if (i % 31 == 0) {
                        start();
                    }
                    break;
            }
        }
    };

    int i = 0;

//    Timer timer = new Timer();

    @Override
    public int initLayout() {
        return R.layout.activity_record_voice;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("语音识别");
        //获取百度AccessToken
        getAccessToken();
        initPermission();
        IRecogListener listener = new MessageStatusRecogListener(handler);
        // DEMO集成步骤 1.1 1.3 初始化：new一个IRecogListener示例 & new 一个 MyRecognizer 示例,并注册输出事件
        myRecognizer = new MyRecognizer(this, listener);

        recordAdapter = new RecordAdapter(this, list, R.layout.item_record);
        lvRecord.setAdapter(recordAdapter);
        lvRecord.setDividerHeight(0);
    }

    @OnClick({R.id.tv_start, R.id.tv_end, R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_start:
//                timer.scheduleAtFixedRate(new TimerTask() {
//                    @Override
//                    public void run() {
//                        System.out.println("hello world");
//                        Message message = handler.obtainMessage();
//                        message.what = 2;
//                        handler.sendMessage(message);
//                    }
//                }, 100, 1000);
                start();
                fileDirName = "浙江火山口网络科技有限公司" + DateUtils.getInstance().getCurrentDate();
                tvStart.setEnabled(false);
                tvStart.setBackground(getResources().getDrawable(R.drawable.gray_corner_bg));
                break;
            case R.id.tv_end:
                isEnd = 1;
//                timer.cancel();
                stop();
                File file = new File(fileName);
                upVoiceFile(file);
                tvStart.setEnabled(true);
                tvStart.setBackground(getResources().getDrawable(R.drawable.blue_corner_btn));
                break;
            case R.id.ll_back:
                this.finish();
                break;
        }
    }

    /**
     * 开始录音，点击“开始”按钮后调用。
     * 基于DEMO集成2.1, 2.2 设置识别参数并发送开始事件
     * {accept-audio-data=true, vad.endpoint-timeout=0, outfile=/storage/emulated/0/baiduASR/outfile.pcm, infile=#com.baidu.aip.asrwakeup3.core.inputstream.MyMicrophoneInputStream.getInstance(), accept-audio-volume=false}
     */
    protected void start() {
        // DEMO集成步骤2.1 拼接识别参数： 此处params可以打印出来，直接写到你的代码里去，最终的json一致即可。
        final Map<String, Object> params = new HashMap<>();
        params.put("accept-audio-data", true);
        params.put("vad.endpoint-timeout", 0);
//        String fileName = "JuBoBao" + new Date().getTime() + ".pcm";
        fileName = "/storage/emulated/0/baiduASR/JuBoBao" + new Date().getTime() + ".pcm";
//        fileName = "JuBoBao" + new Date().getTime() + ".pcm";
        LogUtils.e(fileName);
        params.put("outfile", fileName);
        params.put("infile", "#com.baidu.aip.asrwakeup3.core.inputstream.MyMicrophoneInputStream.getInstance()");
//        params.put("infile", "/sdcard/test/test.pcm");
        params.put("accept-audio-volume", false);
        //最近还好吗
        // params 也可以根据文档此处手动修改，参数会以json的格式在界面和logcat日志中打印
        Log.i("设置的start输入参数：", params + "");
        // 复制此段可以自动检测常规错误
        (new AutoCheck(getApplicationContext(), new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 100) {
                    AutoCheck autoCheck = (AutoCheck) msg.obj;
                    synchronized (autoCheck) {
                        String message = autoCheck.obtainErrorMessage(); // autoCheck.obtainAllMessage();
                        LogUtils.e(message + "\n");
                        ; // 可以用下面一行替代，在logcat中查看代码
                        // Log.w("AutoCheckMessage", message);
                        Log.e("AutoCheckMessage", message);
                    }
                }
            }
        }, false)).checkAsr(params);

        // 这里打印出params， 填写至您自己的app中，直接调用下面这行代码即可。
        // DEMO集成步骤2.2 开始识别
        myRecognizer.start(params);
    }

    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        String[] permissions = {
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                // 进入到这里代表没有权限.
            }
        }
        String[] tmpList = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }
    }

    /**
     * 获取百度AccessToken
     * https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=TYqQeziRTaHq6KwoaXcHiGdF&client_secret=GSIusfY9QGmf6Fi0qy8tGGXiLwzUQkjb&
     */
    public void getAccessToken() {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(this, "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=z6ctYlci55lKPUsDAAFgNu2I&client_secret=xSflXuTzkGN67NPyiA9VcDsCGtUbItTE", new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                LogUtils.e(json);
                AccessTokenBean tokenBean = JSON.parseObject(json, AccessTokenBean.class);
                accessToken = tokenBean.getAccess_token();
            }

            @Override
            public void onFail() {

            }
        });
    }

    //词法分析
    public void getAnyKeyWord(final String content) {
        final AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 调用接口
        new Thread() {
            @Override
            public void run() {
                super.run();
                JSONObject res = client.lexer(content, null);
                LogUtils.e("-----" + res.toString());
            }
        }.start();
    }

    /**
     * 开始录音后，手动点击“停止”按钮。
     * SDK会识别不会再识别停止后的录音。
     * 基于DEMO集成4.1 发送停止事件 停止录音
     */
    protected void stop() {

        myRecognizer.stop();
    }

    /**
     * 开始录音后，手动点击“取消”按钮。
     * SDK会取消本次识别，回到原始状态。
     * 基于DEMO集成4.2 发送取消事件 取消本次识别
     */
    protected void cancel() {
        myRecognizer.cancel();
    }

    /**
     * 销毁时需要释放识别资源。
     */
    @Override
    protected void onDestroy() {
        // 如果之前调用过myRecognizer.loadOfflineEngine()， release()里会自动调用释放离线资源
        // 基于DEMO5.1 卸载离线资源(离线时使用) release()方法中封装了卸载离线资源的过程
        // 基于DEMO的5.2 退出事件管理器 是的嘛
        myRecognizer.release();
//        timer.cancel();
        super.onDestroy();
    }

    private PcmToWavUtil pcmToWavUtil = new PcmToWavUtil();

    //语音文件上传
    public void upVoiceFile(File file) {
        LogUtils.e(file.getAbsolutePath());
        String outPath = file.getAbsolutePath().replace(".pcm", ".wav");
        pcmToWavUtil.pcmToWav(file.getAbsolutePath(), outPath);
        File fileWav = new File(outPath);
        final AlertDialog alertDialog = MineLoadingDialogUtils.updateDialog(RecordVoiceActivity.this, "语音上传中");
//        String fileName = "JuBoBao" + new Date().getTime() + ".wav";
        String fileName = "JuBoBao" + new Date().getTime() + ".pcm";
        LogUtils.e(PersonConstant.getInstance().getUserId() + "  " + "浙江火山口网络科技有限公司" + "  " + fileDirName + "  " + isEnd);
        OkHttpUtils.post()
                .addFile("imgFile", fileName, file)
                .addParams("UserID", PersonConstant.getInstance().getUserId())
                .addParams("PortraitCompany", "浙江火山口网络科技有限公司")
                .addParams("upfileName", fileDirName)
                .addParams("IsEndTag", isEnd + "")
                .url(UrlConstant.getInstance().UP_VOICE_URL)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.i(call.toString() + "  " + e.toString());
                ToastUtils.getMineToast("上传失败，请重新上传");
                alertDialog.dismiss();
            }

            @Override
            public void onResponse(String response, int id) {
//                ImageUrl imageUrl = JSON.parseObject(response, ImageUrl.class);
                LogUtils.e(response);
                list.add(response);
                recordAdapter.notifyDataSetChanged();
                alertDialog.dismiss();
            }
        });
    }
}
