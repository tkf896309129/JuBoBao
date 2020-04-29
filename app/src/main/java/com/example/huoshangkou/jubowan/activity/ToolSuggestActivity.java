package com.example.huoshangkou.jubowan.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.LanImageShowAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.KeyBoardUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：ToolSuggestActivity
 * 描述：设备留言
 * 创建时间：2017-06-15  11:14
 */

public class ToolSuggestActivity extends BaseActivity {
    @Bind(R.id.recycler_tool_suggest)
    RecyclerView recylcerView;
    @Bind(R.id.et_suggest)
    EditText etSuggest;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    //附件
    List<String> imgList;
    private int imgNum = 9;
    LanImageShowAdapter lanImageShowAdapter;

    //留言
    private String content = "";
    private String productId = "";

    private String pics = "";

    @Override
    public int initLayout() {
        return R.layout.activity_suggest_tool;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {

        tvTitle.setText("留言");

        imgList = new ArrayList<>();
        lanImageShowAdapter = new LanImageShowAdapter(imgList, getContext());
        recylcerView.setAdapter(lanImageShowAdapter);
        recylcerView.setLayoutManager(getLayoutManager());
        productId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);

        //删除图片
        lanImageShowAdapter.setDeleteImg(new LanImageShowAdapter.deleteClick() {
            @Override
            public void deleteImgClick(final int position) {
                CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否删除该照片", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        imgList.remove(position);
                        lanImageShowAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }
        });
    }

    @OnClick({R.id.ll_back, R.id.img_check, R.id.tv_commit_suggest})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.img_check:
                KeyBoardUtils.closeKeybord(etSuggest, getContext());

                if (imgList.size() >= 9) {
                    ToastUtils.getMineToast("最多选择9张图片");
                    return;
                }
                int num = imgNum - imgList.size();

                PhotoUtils.getInstance().getMoreLocalPhoto(ToolSuggestActivity.this, num, new PhotoCallBack() {
                    @Override
                    public void getPhoto(String path) {
                        imgList.addAll(PhotoUtils.getInstance().getListImage(path));
                        lanImageShowAdapter.notifyDataSetChanged();
                    }
                });
                break;
            case R.id.tv_commit_suggest:

                content = etSuggest.getText().toString().trim();

                pics = PhotoUtils.getInstance().getImageStr(imgList);

                if (!StringUtils.isNoEmpty(content)) {
                    ToastUtils.getMineToast("请输入留言内容");
                    return;
                }
                PhotoUtils.getInstance().mutilLocalImageUp(imgList, this, new StringCallBack() {
                    @Override
                    public void onSuccess(String str) {
                        pics = str;
                        putSuggest(productId, content, pics);

                    }

                    @Override
                    public void onFail() {

                    }
                });

                break;
        }
    }

    private StaggeredGridLayoutManager getLayoutManager() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL);
        return layoutManager;
    }

    //提交设备建议
    public void putSuggest(String productId, String message, String pics) {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL + PostConstant.getInstance().ADD_MAIN_MESSAGE
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().PRODUCE_ID + "=" + productId + "&"
                + FieldConstant.getInstance().MESSAGE_TXT + "=" + EncodeUtils.getInstance().getEncodeString(message) + "&"
                + FieldConstant.getInstance().PICS + "=" + pics, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("提交成功");
                    ToolSuggestActivity.this.finish();
                    SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().TOOL_REFRESH, "yes");

                } else {
                    ToastUtils.getMineToast("提交失败");
                }
            }

            @Override
            public void onFail() {

            }
        });
    }
}
