package com.example.huoshangkou.jubowan.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.aip.asrwakeup3.core.mini.AutoCheck;
import com.baidu.aip.asrwakeup3.core.recog.MyRecognizer;
import com.baidu.aip.asrwakeup3.core.recog.listener.IRecogListener;
import com.baidu.aip.asrwakeup3.core.recog.listener.MessageStatusRecogListener;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.IndexBean;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：VoiceOrderActivity
 * 描述：
 * 创建时间：2019-10-23  13:25
 */

public class VoiceOrderActivity extends BaseActivity {
    @Bind(R.id.imageView)
    ImageView imgAnim;
    @Bind(R.id.iv_voice)
    ImageView imgVoice;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_brand)
    EditText etBrand;
    @Bind(R.id.et_class)
    EditText etClass;
    @Bind(R.id.et_standard)
    EditText etStandard;
    @Bind(R.id.et_thick)
    EditText etThick;
    @Bind(R.id.et_level)
    EditText etLevel;
    @Bind(R.id.ll_yp)
    LinearLayout llYp;
    @Bind(R.id.tv_class)
    TextView tvClass;
    @Bind(R.id.tv_right)
    TextView tvRight;

    AnimationDrawable animationDrawable;

    /**
     * 识别控制器，使用MyRecognizer控制识别的流程
     */
    protected MyRecognizer myRecognizer;
    private String content = "";
    private String type = "";
    private boolean isYuan;
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
                        ToastUtils.getMineToast(content);
                        if (isYuan) {
                            anylaiseKey(content, typeYp);
                        } else {
                            anylaiseKey(content, typeFl);
                        }
                    }
                    break;
            }
        }
    };

    @Override
    public int initLayout() {
        return R.layout.activity_voice_order;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("语音下单");
        tvRight.setText("温馨提示");
        animationDrawable = (AnimationDrawable) imgAnim.getBackground();
        initPermission();
        IRecogListener listener = new MessageStatusRecogListener(handler);
        myRecognizer = new MyRecognizer(this, listener);
        imgVoice.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });
        type = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);

        //是原片
        if (type.equals("yuan")) {
            isYuan = true;
            //设置标题
            tvTitle.setText(R.string.buy_yuan);
            //是辅材
        } else if (type.equals("fu")) {
            isYuan = false;
            llYp.setVisibility(View.GONE);
            tvClass.setText("类别：");
            //设置标题
            tvTitle.setText(R.string.buy_fu);
        }

        imgVoice.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        animationDrawable.stop();
                        imgVoice.setImageResource(R.mipmap.voice);
                        stop();
                        break;
                    case MotionEvent.ACTION_DOWN:
                        start();
                        animationDrawable.start();
                        imgVoice.setImageResource(R.mipmap.voice_touch);
                        break;
                }
                return false;
            }
        });
    }

    @OnClick({R.id.ll_back, R.id.tv_confirm, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_confirm:
                String brand = etBrand.getText().toString().trim();
                String className = etClass.getText().toString().trim();
                String standard = etStandard.getText().toString().trim();
                standard.replace("x", "*");
                String thick = etThick.getText().toString().trim();
                thick.replace("毫米", "mm");
                String level = etLevel.getText().toString().trim();
                Intent intent = new Intent();
                intent.putExtra(IntentUtils.getInstance().TYPE, brand);
                intent.putExtra(IntentUtils.getInstance().VALUE, className);
                intent.putExtra(IntentUtils.getInstance().STR, standard);
                intent.putExtra(IntentUtils.getInstance().TXT, thick);
                intent.putExtra(IntentUtils.getInstance().LEVEL, level);
                setResult(1, intent);
                this.finish();
                break;
            case R.id.tv_right:
                CopyIosDialogUtils.getInstance().getIosDialog(this, "您可以这么说：\n品牌信义 品类白玻 规格2440x3660 厚度6mm 等级一等品", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {

                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
                break;
        }
    }

    private String[] typeYp = {"品类", "品牌", "级别", "厚度", "规格"};
    private String[] typeFl = {"类别", "品牌", "规格"};
    private List<IndexBean> list = new ArrayList<>();

    //语义分析
    public void anylaiseKey(String str, String[] type) {
        int num = type.length;
        list.clear();
        for (int i = 0; i < num; i++) {
            list.add(new IndexBean(str.indexOf(type[i]), type[i]));
        }
        if (list.size() == 0) {
            return;
        }
        Collections.sort(list, new Comparator<IndexBean>() {
            /*
             * int compare(Person p1, Person p2) 返回一个基本类型的整型，
             * 返回负数表示：p1 小于p2，
             * 返回0 表示：p1和p2相等，
             * 返回正数表示：p1大于p2
             */
            public int compare(IndexBean p1, IndexBean p2) {
                //按照Person的年龄进行升序排列
                if (p1.getIndex() > p2.getIndex()) {
                    return 1;
                }
                if (p1.getIndex() == p2.getIndex()) {
                    return 0;
                }
                return -1;
            }
        });
        int index = list.size();
        for (int i = 0; i < index; i++) {
            String indexStr = "";
            if (list.get(i).getIndex() == -1) {
                continue;
            }
            if (i < index - 1) {
                indexStr = str.substring(list.get(i).getIndex() + 2, list.get(i + 1).getIndex());
            } else {
                indexStr = str.substring(list.get(i).getIndex() + 2, str.length());
            }
            switch (list.get(i).getStr()) {
                case "品牌":
                    etBrand.setText(indexStr);
                    break;
                case "类别":
                case "品类":
                    etClass.setText(indexStr);
                    break;
                case "规格":
                    etStandard.setText(indexStr.replace("×", "*"));
                    break;
                case "厚度":
                    etThick.setText(indexStr
                            .replace("毫米", "mm")
                            .replace("十一", "11")
                            .replace("十二", "12")
                            .replace("十三", "13")
                            .replace("十四", "14")
                            .replace("十五", "15")
                            .replace("十六", "16")
                            .replace("十七", "17")
                            .replace("十八", "18")
                            .replace("十九", "19")
                            .replace("二十", "20")
                            .replace("一", "1")
                            .replace("二", "2")
                            .replace("三", "3")
                            .replace("四", "4")
                            .replace("五", "5")
                            .replace("六", "6")
                            .replace("七", "7")
                            .replace("八", "8")
                            .replace("九", "9")
                            .replace("十", "10")
                    );
                    break;
                case "级别":
                    etLevel.setText(indexStr);
                    break;
            }
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
        params.put("infile", "#com.baidu.aip.asrwakeup3.core.inputstream.MyMicrophoneInputStream.getInstance()");
//        params.put("infile", "/sdcard/test/test.pcm");
        params.put("accept-audio-volume", false);

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
     * 开始录音后，手动点击“停止”按钮。
     * SDK会识别不会再识别停止后的录音。
     * 基于DEMO集成4.1 发送停止事件 停止录音
     */
    protected void stop() {
        myRecognizer.stop();
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
     * 销毁时需要释放识别资源。
     */
    @Override
    protected void onDestroy() {
        // 如果之前调用过myRecognizer.loadOfflineEngine()， release()里会自动调用释放离线资源
        // 基于DEMO5.1 卸载离线资源(离线时使用) release()方法中封装了卸载离线资源的过程
        // 基于DEMO的5.2 退出事件管理器
        myRecognizer.release();
        super.onDestroy();
    }
}
