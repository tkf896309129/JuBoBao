package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.ImageAddFunction;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：SbGzSbActivity
 * 描述：设备改造申报
 * 创建时间：2018-06-07  13:15
 */

public class SbGzSbActivity extends BaseActivity {
    @Bind(R.id.grid_view)
    ScrollGridView scrollGridView;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    ImageAddAdapter imageAddAdapter;
    List<String> list;

    @Override
    public int initLayout() {
        return R.layout.activity_sbgz_apply;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        list = new ArrayList<>();
        //图片显示封装
        imageAddAdapter = new ImageAddAdapter(this, list, "");
        ImageAddFunction.addImageFun(imageAddAdapter, list, this, scrollGridView);

        tvTitle.setText("设备改造申报");

    }

    @OnClick({R.id.iv_photo_pic,R.id.tv_commit_put,R.id.rl_brand})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_photo_pic:
                PhotoUtils.getInstance().getMoreLocalPhoto(this, 9, new PhotoCallBack() {
                    @Override
                    public void getPhoto(String path) {
                        String[] split = path.split(",");
                        if (split == null) {
                            return;
                        }

                        int num = split.length;
                        for (int i = 0; i < num; i++) {
                            list.add(split[i]);
                        }

                        imageAddAdapter.notifyDataSetChanged();
                    }
                });
                break;
            case R.id.tv_commit_put:
                IntentUtils.getInstance().toActivity(getContext(),BuyToolActivity.class);
                break;
            case R.id.rl_brand:
                IntentUtils.getInstance().toActivity(getContext(),RepairActivity.class);
                break;
        }
    }


}
