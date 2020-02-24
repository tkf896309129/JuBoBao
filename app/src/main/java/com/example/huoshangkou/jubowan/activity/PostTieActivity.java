package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.bean.TieTypeBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PhotoConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.ChooseDialogPositionCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.PickDialogUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：PostTieActivity
 * 描述：发表帖子界面
 * 创建时间：2017-02-13  09:53
 */

public class PostTieActivity extends BaseActivity {
    ImageAddAdapter addAdapter;
    List<String> imgList;
    List<TieTypeBean> tieTypeList;
    ArrayList<String> tieShowList;

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.grid_tie_photo)
    ScrollGridView gridTiePhoto;
    @Bind(R.id.tv_type_select)
    TextView tvTieSelect;

    @Bind(R.id.et_tie_title)
    EditText etTitle;
    @Bind(R.id.et_tie_content)
    EditText etContent;

    //悬赏积分
    @Bind(R.id.rl_set_score)
    RelativeLayout rlSetScore;
    @Bind(R.id.et_score)
    EditText etScore;

    //是否是维修维保
    private boolean isRepair = false;

    //发表帖子id
    private String postTieId = "";
    //标题
    private String title = "";
    //内容
    private String content = "";
    //图片
    private String pics = "";
    //设置分数
    private String score = "";

    @Override
    public int initLayout() {
        return R.layout.activity_post_tie;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        String topTitle = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        tvTitle.setText("发表" + topTitle + "帖子");
        tieTypeList = new ArrayList<>();
        imgList = new ArrayList<>();
        tieShowList = new ArrayList<>();

        postTieId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);

        if (postTieId.equals("9")) {
            rlSetScore.setVisibility(View.VISIBLE);
            isRepair = true;
        } else {
            isRepair = false;
            rlSetScore.setVisibility(View.GONE);
        }

        imgList.add(PhotoConstant.getInstance().TIE_ADD_PHOTOT);
        addAdapter = new ImageAddAdapter(this, imgList, PhotoConstant.getInstance().TIE_TYPE);
        gridTiePhoto.setAdapter(addAdapter);


        //初始化发帖的内容
        initPostTieData();

        //点击事件
        addAdapter.setAddPhotoImg(new ImageAddAdapter.addPhotoClick() {
            @Override
            public void onAddPthotoClick() {
                PhotoUtils.getInstance().updateDialog(PostTieActivity.this, new PhotoCallBack() {
                    @Override
                    public void getPhoto(String path) {
                        imgList.remove(imgList.size() - 1);
                        imgList.addAll(PhotoUtils.getInstance().getListImage(path));
                        imgList.add(PhotoConstant.getInstance().TIE_ADD_PHOTOT);
                        addAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

        //删除图片
        addAdapter.setDeleteImg(new ImageAddAdapter.deleteClick() {
            @Override
            public void deleteImgClick(final int position) {
                CopyIosDialogUtils.getInstance().getIosDialog(PostTieActivity.this, "是否删除图片", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        imgList.remove(position);
                        addAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }
        });
    }


    //点击事件
    @OnClick({R.id.ll_back, R.id.ll_select_tie, R.id.ll_common_tie, R.id.tv_post_now})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            //选择专区
            case R.id.ll_select_tie:
                PickDialogUtils.getInstance().getChoosePositionDialog(getContext(), "选择专区", tieShowList, new ChooseDialogPositionCallBack() {
                    @Override
                    public void onGetMessagePosition(String message, int position) {
                        tvTieSelect.setText(message + " ");
                        postTieId = tieTypeList.get(position).getId();
                        if (postTieId.equals("9")) {
                            rlSetScore.setVisibility(View.VISIBLE);
                            isRepair = true;
                        } else {
                            isRepair = false;
                            rlSetScore.setVisibility(View.GONE);
                        }
                    }
                });
                break;
            //普通帖
            case R.id.ll_common_tie:

                break;
            //发表帖子
            case R.id.tv_post_now:
                if (!StringUtils.isNoEmpty(postTieId)) {
                    ToastUtils.getMineToast("请选择专区");
                    return;
                }

                title = StringUtils.getNoSapceStr(etTitle.getText().toString().trim());

                if (!StringUtils.isNoEmpty(title)) {
                    ToastUtils.getMineToast("请输入标题");
                    return;
                }

                if (title.length() <= 5) {
                    ToastUtils.getMineToast("标题必须为5个字以上");
                    return;
                }

                content = etContent.getText().toString().trim();
                if (!StringUtils.isNoEmpty(content)) {
                    ToastUtils.getMineToast("请输入内容");
                    return;
                }

                if (isRepair) {
                    score = etScore.getText().toString().trim();
                    if (!StringUtils.isNoEmpty(score)) {
                        ToastUtils.getMineToast("请输入悬赏积分");
                        return;
                    }

                    if (Integer.parseInt(score) < 100) {
                        ToastUtils.getMineToast("悬赏积分不得小于100");
                        return;
                    }

                } else {
                    score = "";
                }

//                pics = PhotoUtils.getInstance().getImageStr(imgList);

                //设置昵称
                String nickName = (String) SharedPreferencesUtils.getInstance().get(getContext(), SharedKeyConstant.getInstance().NICK_NAME, "");
                if (!StringUtils.isNoEmpty(nickName)) {
                    ToastUtils.getMineToast("您还没有设置昵称");
                    CopyIosDialogUtils.getInstance().setNickName(getContext(), new StringCallBack() {
                        @Override
                        public void onSuccess(final String str) {
                            OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                                    + PostConstant.getInstance().SET_NICK + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                                    + FieldConstant.getInstance().NICK_NAME + "=" + EncodeUtils.getInstance().getEncodeString(str), new OkhttpCallBack() {
                                @Override
                                public void onSuccess(String json) {
                                    SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                                    if (successBean.getSuccess() == 1) {
                                        SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().NICK_NAME, str);
                                        ToastUtils.getMineToast("设置成功");
                                    }
                                }

                                @Override
                                public void onFail() {

                                }
                            });
                        }

                        @Override
                        public void onFail() {

                        }
                    });
                    return;
                }

                CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否发表帖子", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        if (imgList.size() == 0) {
                            return;
                        }
                        imgList.remove(imgList.size() - 1);
                        PhotoUtils.getInstance().mutilLocalImageUp(imgList, PostTieActivity.this, new StringCallBack() {
                            @Override
                            public void onSuccess(String str) {
                                pics = str;
                                postTie(postTieId, title, content, pics, score);
                            }

                            @Override
                            public void onFail() {

                            }
                        });

                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
                break;
        }
    }

    //发表帖子
    public void postTie(String postTypeId, String postTitle, String postTxt, String pics, String juBoScore) {
        OkHttpUtils.post()
                .addParams(FieldConstant.getInstance().USER_ID, PersonConstant.getInstance().getUserId())
                .addParams(FieldConstant.getInstance().POST_TIE_ID, postTypeId)
                .addParams(FieldConstant.getInstance().POST_TIE_TITLE, postTitle)
                .addParams(FieldConstant.getInstance().POST_TEXT, postTxt)
                .addParams(FieldConstant.getInstance().PICS, pics)
                .addParams(FieldConstant.getInstance().PHONE_TYPE, PersonConstant.getInstance().getPhoneType())
                .addParams(FieldConstant.getInstance().JU_BOBI_SCORE, juBoScore)
                .url(UrlConstant.getInstance().POST_TIE)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtils.getMineToast("发表失败" + e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.i(response);
                SuccessBean successBean = JSON.parseObject(response, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    if (StringUtils.isNoEmpty(successBean.getErrMsg())) {
                        CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), successBean.getErrMsg(), new CopyIosDialogUtils.ErrDialogCallBack() {
                            @Override
                            public void confirm() {
                                PostTieActivity.this.finish();
                            }
                        });
                    } else {
                        PostTieActivity.this.finish();
                        ToastUtils.getMineToast("发表成功");
                    }
                } else {
                    ToastUtils.getMineToast(successBean.getErrMsg());
                }
            }
        });
    }

    //初始化发帖标签
    public void initPostTieData() {
        TieTypeBean typeBean_repair = new TieTypeBean();
        typeBean_repair.setType("维修维保");
        typeBean_repair.setId("9");
        tieTypeList.add(typeBean_repair);
        tieShowList.add("维修维保");

        TieTypeBean typeBean_yuan = new TieTypeBean();
        typeBean_yuan.setType("原片交流");
        typeBean_yuan.setId("1");
        tieTypeList.add(typeBean_yuan);
        tieShowList.add("原片交流");

        TieTypeBean typeBean_fu = new TieTypeBean();
        typeBean_fu.setType("辅料交流");
        typeBean_fu.setId("2");
        tieTypeList.add(typeBean_fu);
        tieShowList.add("辅料交流");

        TieTypeBean typeBean_repair_trans = new TieTypeBean();
        typeBean_repair_trans.setType("物流配送");
        typeBean_repair_trans.setId("3");
        tieTypeList.add(typeBean_repair_trans);
        tieShowList.add("物流配送");

        TieTypeBean typeBean_repair_news = new TieTypeBean();
        typeBean_repair_news.setType("实时新闻");
        typeBean_repair_news.setId("4");
        tieTypeList.add(typeBean_repair_news);
        tieShowList.add("实时新闻");

        TieTypeBean typeBean_repair_fun = new TieTypeBean();
        typeBean_repair_fun.setType("搞笑内涵");
        typeBean_repair_fun.setId("5");
        tieTypeList.add(typeBean_repair_fun);
        tieShowList.add("搞笑内涵");

        TieTypeBean typeBean_repair_water = new TieTypeBean();
        typeBean_repair_water.setType("灌水吐槽");
        typeBean_repair_water.setId("6");
        tieTypeList.add(typeBean_repair_water);
        tieShowList.add("灌水吐槽");

        TieTypeBean typeBean_repair_life = new TieTypeBean();
        typeBean_repair_life.setType("生活那些事");
        typeBean_repair_life.setId("7");
        tieTypeList.add(typeBean_repair_life);
        tieShowList.add("生活那些事");
    }
}
