package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ApproveListBean;
import com.example.huoshangkou.jubowan.constant.PhotoConstant;
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
 * 类名：ApproveAgreeDetailActivity
 * 描述：同意审核详情
 * 创建时间：2017-03-23  13:12
 */

public class ApproveAgreeDetailActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_apply_introduce)
    TextView tvIntro;
    @Bind(R.id.grid_view_apply)
    ScrollGridView scrollGridView;

    ApproveListBean approveListBean;
    List<String> imgList;


    @Override
    public int initLayout() {
        return R.layout.activity_agree_detail;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        approveListBean = (ApproveListBean) getIntent().getSerializableExtra(IntentUtils.getInstance().BEAN_TYPE);
        imgList = new ArrayList<>();
        if (approveListBean == null) {
            tvTitle.setText("审批详情");
            return;
        }
        tvTitle.setText(approveListBean.getApprovalUserName() + "审批详情");
        tvIntro.setText(approveListBean.getApprovalremark());

        imgList.addAll(PhotoUtils.getInstance().getListImage(approveListBean.getApprovalPic()));
        ImageAddAdapter imageAddAdapter = new ImageAddAdapter(getContext(), imgList, PhotoConstant.getInstance().IS_NO_DELETE);
        scrollGridView.setAdapter(imageAddAdapter);
    }

    //点击事件
    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }
}
