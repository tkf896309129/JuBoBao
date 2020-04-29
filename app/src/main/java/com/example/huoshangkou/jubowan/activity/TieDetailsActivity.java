package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.bumptech.glide.Glide;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.TieDetailFunction;
import com.example.huoshangkou.jubowan.adapter.BackTieAdapter;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ForumListBean;
import com.example.huoshangkou.jubowan.bean.RepliesBean;
import com.example.huoshangkou.jubowan.bean.RepliesListBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PhotoConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.TieDetailConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.BackTieCallBack;
import com.example.huoshangkou.jubowan.inter.OnCommonSuccessCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.inter.TieEditInterface;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DataAgoUtils;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.InputUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.KeyBoardUtils;
import com.example.huoshangkou.jubowan.utils.KeyboardChangeListener;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.LoginUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.TieEditUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：TieDetailsActivity
 * 描述：
 * 创建时间：2017-02-23  09:44
 */
public class TieDetailsActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.lv_refresh)
    ListView ptrCommon;
    @Bind(R.id.et_common)
    EditText etCommon;
    @Bind(R.id.tv_replies)
    TextView tvReplies;
    @Bind(R.id.x_refresh)
    SmartRefreshLayout xRefreshView;

    @Bind(R.id.ll_share)
    LinearLayout llShare;

    private BackTieAdapter backTieAdapter;
    private List<RepliesListBean> tieList;

    private ForumListBean forumListBean;

    //是否是维修维保
    private boolean isRepair = false;

    //论坛详情控件
    TextView tvViewNum;
    TextView tvCommonNum;
    ImageView imgHead;
    TextView tvNickName;
    TextView tvConversionText;
    TextView tvTime;
    TextView tvTopTitle;
    ScrollGridView yuanGridView;
    TextView tvTieScore;
    TextView tvType;
    ImageView imgoOne;

    RelativeLayout rlZan;
    ImageView imgZan;
    TextView tvZanAnim;
    TextView tvZanNum;

    //回帖id
    private String tieId = "";
    //帖子编辑类型
    private String type = "";

    //友盟分享回调
    private UMShareListener umShareListener;

    final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]{
//                   SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA,
            SHARE_MEDIA.QQ,
            SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QZONE,
            SHARE_MEDIA.WEIXIN_CIRCLE
//                    , SHARE_MEDIA.DOUBAN
    };

    //对回帖进行回帖
    private String mPostId = "";//主贴id
    private String mParentId = "";//被回帖id
    private String mUserId = "";//当前回帖人的id

    //是否是对回帖进行回帖
    private boolean isSecondTieBack = false;

    private String text = "";
    private String title = "";

    String id = "";
    String isShare = "";

    //是否是论坛管理
    private boolean isTieManager = false;
    //是否是待审回帖
    private boolean isCheckBackTie = false;
    private int pageSize = 1;

    @Override
    public int initLayout() {
        return R.layout.activity_tie_details;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("论坛详情");
        tvRight.setText("编辑");

        //设置只有上啦加载
        xRefreshView.setEnableLoadMore(true);
        xRefreshView.setEnableRefresh(false);

        forumListBean = (ForumListBean) getIntent().getSerializableExtra(IntentUtils.getInstance().BEAN_TYPE);
        type = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);

        //默认分享
        if (!StringUtils.isNoEmpty(type)) {
            type = TieDetailConstant.getInstance().SHARE;
        }


        if (type.equals(TieDetailConstant.getInstance().IS_CLOCK) || type.equals(TieDetailConstant.getInstance().TIE_APPLY)) {
            isTieManager = true;
            tvRight.setText("编辑");
            llShare.setVisibility(View.GONE);
        } else {
            tvRight.setText("");
            llShare.setVisibility(View.VISIBLE);
        }

        String checkBackTie = getIntent().getStringExtra(IntentUtils.getInstance().SECOND_TYPE);
        if (StringUtils.isNoEmpty(checkBackTie)) {
            isCheckBackTie = true;
            tvRight.setText("编辑");
            llShare.setVisibility(View.GONE);
        } else {
            isCheckBackTie = false;
        }


        //论坛详情界面控件初始化
        View view = LayoutInflater.from(this).inflate(R.layout.item_tie_top, null);
        tvTopTitle = (TextView) view.findViewById(R.id.tv_top_title);
        tvViewNum = (TextView) view.findViewById(R.id.tv_view_num);
        tvCommonNum = (TextView) view.findViewById(R.id.tv_common_num);
        imgHead = (ImageView) view.findViewById(R.id.iv_head_img);
        tvNickName = (TextView) view.findViewById(R.id.tv_name);
        tvConversionText = (TextView) view.findViewById(R.id.tv_conversion_text);
        tvTime = (TextView) view.findViewById(R.id.tv_conversion_time);
        yuanGridView = (ScrollGridView) view.findViewById(R.id.grid_conversion_details);
        tvTieScore = (TextView) view.findViewById(R.id.tv_tie_score);
        tvType = (TextView) view.findViewById(R.id.tv_conversion_type);

        imgoOne = (ImageView) view.findViewById(R.id.img_one);
        rlZan = (RelativeLayout) view.findViewById(R.id.ll_zan_num);
        imgZan = (ImageView) view.findViewById(R.id.iv_zan);
        tvZanAnim = (TextView) view.findViewById(R.id.tv_zan_anim);
        tvZanNum = (TextView) view.findViewById(R.id.tv_zan_num);


        if (forumListBean != null) {

            mPostId = forumListBean.getID() + "";
            tieId = forumListBean.getID() + "";
            id = forumListBean.getPostTypeID() + "";

            tvCommonNum.setText(forumListBean.getReplies() + "");
            GlideUtils.getInstance().displayCricleImage(getContext(), forumListBean.getUserPic(), imgHead);
            tvNickName.setText(forumListBean.getNickname());

            tvZanNum.setText(forumListBean.getGiveUpCount() + "");
            tvViewNum.setText(forumListBean.getPageView() + "");

            if (forumListBean.getGiveUpState() >= 1) {
                imgZan.setImageResource(R.mipmap.dianzan_on_icon);
            } else {
                imgZan.setImageResource(R.mipmap.dianzan_off_icon);
            }

            //锁定中   + list.get(position).getPostTitle()
            String clock = "<font color=\"#FFA07A\">【锁定中】</font>";
            String message = "<font color=\""+getResources().getColor(R.color.address_black_key)+"\">" + forumListBean.getPostTitle() + "</font>";
            //已关闭
            String close = "<font color=\"#cccccc\">【已关闭】</font>";
            if (forumListBean.getPostState() == 1) {
                tvTopTitle.setText(Html.fromHtml(message));
            } else if (forumListBean.getPostState() == 2) {
                tvTopTitle.setText(Html.fromHtml(clock + message));
            } else if (forumListBean.getPostState() == 3) {
                tvTopTitle.setText(Html.fromHtml(close + message));
            } else {
                tvTopTitle.setText(Html.fromHtml(message));
            }

            tvConversionText.setText(forumListBean.getPostText());
//            tvTime.setText(DateUtils.getFormMinuteData(forumListBean.getCreateTime()));

            tvTime.setText(DataAgoUtils.getTime(DateUtils.getFormMinuteData(forumListBean.getCreateTime())));

            List<String> imgList = PhotoUtils.getInstance().getListImage(forumListBean.getPics());

            if (imgList.size() == 1) {
                Glide.with(getContext()).load(imgList.get(0)).placeholder(R.mipmap.default_img).
                        fitCenter().into(imgoOne);
            } else {
                ImageAddAdapter imageAddAdapter = new ImageAddAdapter(getContext(), imgList, PhotoConstant.getInstance().IS_NO_DELETE);
                yuanGridView.setAdapter(imageAddAdapter);
            }


            tvTieScore.setText("此贴悬赏" + forumListBean.getJobobiScore() + "分");

            xRefreshView.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    pageSize++;
                    getSaveData();
                }
            });


            rlZan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (!LoginUtils.getInstance().isLogin(getContext())) {
                        IntentUtils.getInstance().toActivity(getContext(), LoginActivity.class);
                        return;
                    }

                    if (forumListBean.getGiveUpState() <= 0) {
                        //取消点赞
                        zanMain(mPostId, 1);
                    } else {
                        ToastUtils.getMineToast("你已经点过赞了");
                    }
//                    else if (forumListBean.getGiveUpState() <= 0) {
//                        //点赞
//                        zanMain(mPostId, 1);
//                    }
                }
            });


            switch (forumListBean.getPostTypeID()) {
                case 1:
                    tvType.setText("原片专区");
                    break;
                case 2:
                    tvType.setText("辅料专区");
                    break;
                case 3:
                    tvType.setText("物流专区");
                    break;
                case 4:
                    tvType.setText("时事新闻");
                    break;
                case 5:
                    tvType.setText("搞笑内涵");
                    break;
                case 6:
                    tvType.setText("灌水吐槽");
                    break;
                case 7:
                    tvType.setText("秀一秀");
                    break;
                case 9:
                    tvType.setText("维修维保");
                    break;
                case 12:
                    tvType.setText("聚玻早报");
                    break;
            }
        }

        ptrCommon.addHeaderView(view);
        tieList = new ArrayList<>();
        backTieAdapter = new BackTieAdapter(getContext(), tieList, R.layout.item_back_tie, forumListBean.getPostState(), isTieManager);
        ptrCommon.setAdapter(backTieAdapter);
        ptrCommon.setDividerHeight(0);

        //分享帖子
        umShareListener = new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onResult(SHARE_MEDIA share_media) {
                Toast.makeText(getApplicationContext(), "分享成功了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "分享失败了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
                Toast.makeText(getApplicationContext(), "分享取消了", Toast.LENGTH_SHORT).show();
            }
        };

        getSaveData();

        //回复不得少于15字
        etCommon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() >= 15 && isRepair) {
                    tvReplies.setBackground(getResources().getDrawable(R.drawable.blue_all_corner));
                } else if (charSequence.length() >= 1) {
                    tvReplies.setBackground(getResources().getDrawable(R.drawable.blue_all_corner));
                } else {
                    tvReplies.setBackground(getResources().getDrawable(R.drawable.replies_gray_bg));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //对回帖的回帖进行回帖
        backTieAdapter.setBackTieCallBack(new BackTieCallBack() {
            @Override
            public void onBackTie(String name, String postId, String parentId) {
                etCommon.setText("");
                etCommon.setHint("对" + name + "进行回帖");
                InputUtils.getInstance().inputKeyUtils(getContext());
                etCommon.requestFocus();
                mParentId = parentId;
                isSecondTieBack = true;
            }
        });

        new KeyboardChangeListener(this).setKeyBoardListener(new KeyboardChangeListener.KeyBoardListener() {
            @Override
            public void onKeyboardChange(boolean isShow, int keyboardHeight) {
                if (!isShow) {
                    isSecondTieBack = false;
                    etCommon.setText("");
                    etCommon.setHint("回复主贴内容");
                }
            }
        });

        if (forumListBean.getPostState() == 2 || isTieManager || forumListBean.getPostState() == 3) {
            etCommon.setFocusable(false);
        }
    }

    //获取回帖信息
    public void getSaveData() {
        //获取回帖
        TieDetailFunction.getInstance().getReplies(getContext(), forumListBean.getID() + "", pageSize, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                RepliesBean repliesBean = JSON.parseObject(str, RepliesBean.class);
                tieList.addAll(repliesBean.getReList());
                backTieAdapter.notifyDataSetChanged();
                if (xRefreshView != null) {
                    xRefreshView.finishLoadMore();
                }
            }

            @Override
            public void onFail() {
                if (xRefreshView != null) {
                    xRefreshView.finishLoadMore();
                }
            }
        });
    }


    //点击事件
    @OnClick({R.id.ll_back, R.id.tv_replies, R.id.tv_right, R.id.rl_common_back,
            R.id.et_common, R.id.iv_collect, R.id.iv_share})
    public void onClick(View view) {
        switch (view.getId()) {
            //返回
            case R.id.ll_back:
                this.finish();
                break;
            //回复
            case R.id.tv_replies:

                if (!LoginUtils.getInstance().isLogin(getContext())) {
                    IntentUtils.getInstance().toActivity(getContext(), LoginActivity.class);
                    return;
                }

                String content = etCommon.getText().toString().trim();

                if (!StringUtils.isNoEmpty(content)) {
                    ToastUtils.getMineToast("请输入回复内容");
                    return;
                }

                if (content.length() < 15 && isRepair) {
                    ToastUtils.getMineToast("回复内容不能小于15字");
                    return;
                }

                //回复主贴 不需要传parentid
                if (!isSecondTieBack) {
                    mParentId = "";
                }
                TieDetailFunction.getInstance().backTieSecond(getContext(), mPostId, mParentId, content, new OnCommonSuccessCallBack() {
                    @Override
                    public void onSuccess() {
                        isSecondTieBack = false;
                        etCommon.setText("");
                        KeyBoardUtils.closeKeybord(etCommon,TieDetailsActivity.this);
                        etCommon.setHint("回复主贴内容");
                        ToastUtils.getMineToast("回复成功");
                        pageSize = 1;
                        tieList.clear();
                        getSaveData();
                    }

                    @Override
                    public void onFail() {
                        isSecondTieBack = false;
                        etCommon.setText("");
                        etCommon.setHint("回复主贴内容");
                    }
                });
                break;

            case R.id.tv_right:
                TieEditUtils.updateDialog(type, 2, id, tieId, this, isShare, new TieEditInterface() {
                    //审核原贴
                    @Override
                    public void checkTie(String id, String tieId) {
                        ToastUtils.getMineToast(id);
                        String ids = "";
                        //审核类型
                        String type = "";
                        //待审回帖
                        if (isCheckBackTie) {
                            ids = id;
                            type = TieDetailConstant.getInstance().CHECK_BACK_TIE;
                            //审核帖子
                        } else {
                            ids = tieId;
                            type = TieDetailConstant.getInstance().CHECK_MAIN_TIE;
                        }

                        //审核回帖
                        TieDetailFunction.getInstance().tieManagerAction(getContext(), ids, type, new OnCommonSuccessCallBack() {
                            @Override
                            public void onSuccess() {
                                ToastUtils.getMineToast("审核成功");
                                TieDetailsActivity.this.finish();
                                //需要刷新
                                SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().PASS_CHECK, "yes");
                            }

                            @Override
                            public void onFail() {

                            }
                        });
                    }

                    //锁定原贴
                    @Override
                    public void clockTie(String id) {
                        TieDetailFunction.getInstance().tieManagerAction(getContext(), id, TieDetailConstant.getInstance().CLOCK_TIE, new OnCommonSuccessCallBack() {
                            @Override
                            public void onSuccess() {
                                ToastUtils.getMineToast("锁定成功");
                                TieDetailsActivity.this.finish();
                                //需要刷新
                                SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().PASS_CHECK, "yes");
                            }

                            @Override
                            public void onFail() {

                            }
                        });
                    }

                    //删除原贴
                    @Override
                    public void deleteTie(String id) {
                        TieDetailFunction.getInstance().deleteTie(getContext(), id, new OnCommonSuccessCallBack() {
                            @Override
                            public void onSuccess() {
                                ToastUtils.getMineToast("删除成功");
                                TieDetailsActivity.this.finish();
                                //需要刷新
                                SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().PASS_CHECK, "yes");

                            }

                            @Override
                            public void onFail() {

                            }
                        });
                    }

                    //解锁帖子
                    @Override
                    public void unClockTie(String id) {
                        TieDetailFunction.getInstance().tieManagerAction(getContext(), id, TieDetailConstant.getInstance().UN_CLOCK_TIE, new OnCommonSuccessCallBack() {
                            @Override
                            public void onSuccess() {
                                ToastUtils.getMineToast("解锁成功");
                                TieDetailsActivity.this.finish();
                                //需要刷新
                                SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().PASS_CHECK, "yes");
                            }

                            @Override
                            public void onFail() {

                            }
                        });
                    }

                    //删除回帖
                    @Override
                    public void deleteBackTie(String id) {
                        TieDetailFunction.getInstance().deleteBackTie(getContext(), id, new OnCommonSuccessCallBack() {
                            @Override
                            public void onSuccess() {
                                ToastUtils.getMineToast("删除成功");
                                TieDetailsActivity.this.finish();
                            }

                            @Override
                            public void onFail() {

                            }
                        });
                    }

                    //分享帖子
                    @Override
                    public void shareTie(String id) {
                        UMImage umImage = new UMImage(TieDetailsActivity.this, R.mipmap.share_icon);
                        UMWeb umWeb = new UMWeb(UrlConstant.getInstance().SHARE_URL + id);
                        umWeb.setThumb(umImage);

                        new ShareAction(TieDetailsActivity.this)
                                .setDisplayList(displaylist)
                                .withText(forumListBean.getPostTitle())
                                .withFollow("分享中")
                                .withMedia(umWeb)
                                .setCallback(umShareListener)
                                .open();
                    }

                    //收藏帖子
                    @Override
                    public void saveTie(final String id) {
                        CopyIosDialogUtils.getInstance().getIosDialog(TieDetailsActivity.this, "确认收藏帖子吗", new CopyIosDialogUtils.iosDialogClick() {
                            @Override
                            public void confimBtn() {
                                TieDetailFunction.getInstance().saveTie(getContext(), id);

                            }

                            @Override
                            public void cancelBtn() {

                            }
                        });
                    }
                });
                break;
            case R.id.et_common:
            case R.id.rl_common_back:
                if (forumListBean == null) {
                    return;
                }
                //锁定中
                if (forumListBean.getPostState() == 2) {
                    CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "该贴已被锁定", new CopyIosDialogUtils.ErrDialogCallBack() {
                        @Override
                        public void confirm() {

                        }
                    });
                    //已关闭
                } else if (forumListBean.getPostState() == 3) {
                    CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "该贴已被关闭", new CopyIosDialogUtils.ErrDialogCallBack() {
                        @Override
                        public void confirm() {

                        }
                    });
                } else if (isTieManager) {
                    CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "论坛管理不支持回帖", new CopyIosDialogUtils.ErrDialogCallBack() {
                        @Override
                        public void confirm() {

                        }
                    });
                }
                break;
            case R.id.iv_share:
                UMImage umImage = new UMImage(TieDetailsActivity.this, R.mipmap.share_icon);
                UMWeb umWeb = new UMWeb(UrlConstant.getInstance().SHARE_URL + tieId);
                umWeb.setThumb(umImage);
                umWeb.setTitle(forumListBean.getPostTitle());
                umWeb.setDescription(forumListBean.getPostText());
                LogUtils.i(UrlConstant.getInstance().SHARE_URL + tieId + "   " + forumListBean.getPostTitle() + "  " + forumListBean.getPostText());

                new ShareAction(TieDetailsActivity.this)
                        .setDisplayList(displaylist)
                        .withMedia(umWeb)
                        .setCallback(umShareListener)
                        .open();
                break;
            //收藏
            case R.id.iv_collect:
                CopyIosDialogUtils.getInstance().getIosDialog(TieDetailsActivity.this, "确认收藏帖子吗", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        TieDetailFunction.getInstance().saveTie(getContext(), tieId);
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
                break;
            //点赞
//            case R.id.ll_zan_num:
//                ToastUtils.getMineToast("点赞");
//                break;
        }
    }

    //如果使用的是qq或者新浪精简版jar，需要在您使用分享或授权的Activity（fragment不行）中添加如下回调代码
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    //对主贴进行点赞
    public void zanMain(String postId, final int type) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(getContext(), UrlConstant.getInstance().URL
                + PostConstant.getInstance().UPDATE_LT_POST_GIVEUP + FieldConstant.getInstance().POST_ID + "=" + postId + "&"
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().TYPE + "=" + type, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                int countNum = forumListBean.getGiveUpCount();
                if (successBean.getSuccess() == 1) {
                    forumListBean.setGiveUpCount(countNum + 1);
                    forumListBean.setGiveUpState(1);
                    imgZan.setImageResource(R.mipmap.dianzan_on_icon);
                    tvZanNum.setText(forumListBean.getGiveUpCount() + "");
                } else {
                    ToastUtils.getMineToast(successBean.getErrMsg());
                }

            }

            @Override
            public void onFail() {

            }
        });
    }

}
