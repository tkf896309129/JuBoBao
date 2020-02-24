package com.example.huoshangkou.jubowan.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.view.FllScreenVideoView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：VideoActivity
 * 描述：
 * 创建时间：2017-09-12  13:22
 */

public class VideoActivity extends BaseActivity {
    @Bind(R.id.video_view)
    FllScreenVideoView videoView;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    Uri uri;

    @Override
    public int initLayout() {
        return R.layout.activity_video;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("视频播放");
        uri = getIntent().getParcelableExtra("uri");

        videoView.setMediaController(new MediaController(this));
//        mVideoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "视频播放结束了", Toast.LENGTH_SHORT).show();
            }
        });

        //视频播放
        videoView.setVideoURI(uri);
        videoView.start();

    }

    @OnClick({R.id.ll_back})
    public void onCLick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }
}
