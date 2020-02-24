package com.example.huoshangkou.jubowan.activity.function;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.RepliesListBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnCommonSuccessCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.utils.ZanAnimUtils;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：TieDetailFunction
 * 描述：
 * 创建时间：2017-04-10  10:47
 */

public class TieDetailFunction {

    private static class TieDetailHelper {
        public static TieDetailFunction INSTANCE = new TieDetailFunction();
    }

    public static TieDetailFunction getInstance() {
        return TieDetailHelper.INSTANCE;
    }


    //获取回复帖子
    public void getReplies(Context context, String id, int pageSize, final StringCallBack stringCallBack) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(context, UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_REPLIES_TWO + FieldConstant.getInstance().POST_ID + "=" + id + "&"
                + FieldConstant.getInstance().REPLIES_STATE + "=1" + "&"
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().PAGE_SIZE + "=" + pageSize, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                stringCallBack.onSuccess(json);
            }

            @Override
            public void onFail() {

            }
        });
    }


    //回帖
    public void setReplies(Context context, String postId, String repliesTxt, String createTime, final OnCommonSuccessCallBack successCallBack) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(context, UrlConstant.getInstance().URL
                + PostConstant.getInstance().BACK_REPLIES + FieldConstant.getInstance().POST_ID + "=" + postId + "&"
                + FieldConstant.getInstance().REPLIES_TXT + "=" + EncodeUtils.getInstance().getEncodeString(repliesTxt) + "&"
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    successCallBack.onSuccess();
                } else {
                    successCallBack.onFail();
                }
            }

            @Override
            public void onFail() {
                successCallBack.onFail();
            }
        });
    }

    //对回帖进行点赞或者取消点赞
    public void setZanOrCancel(final Context context, String id, String repliesId, final String zanType,
                               final TextView textView, final String zanNum, final RelativeLayout llClick,
                               final TextView tvZanNum, final RepliesListBean zanBean, final ImageView imgZan) {
        llClick.setClickable(false);
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(context, UrlConstant.getInstance().URL
                + PostConstant.getInstance().SET_TIE_ZAN + FieldConstant.getInstance().ID + "=" + id + "&"
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().REPLIES_ID + "=" + repliesId + "&"
                + FieldConstant.getInstance().TYPE + "=" + zanType, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    textView.setText(zanNum);
                    int[] startLocation = new int[2];
                    textView.getLocationInWindow(startLocation);
                    ZanAnimUtils.setZanAnimation(textView, context, startLocation);

                    //取消点赞
                    if (zanType.equals("0")) {
                        zanBean.setGiveUpCount(zanBean.getGiveUpCount() - 1);
                        tvZanNum.setText(zanBean.getGiveUpCount() + "");
                        zanBean.setGiveUpState(0);
                        imgZan.setImageResource(R.mipmap.dianzan_off_icon);
                        //点赞
                    } else if (zanType.equals("1")) {
                        zanBean.setGiveUpCount(zanBean.getGiveUpCount() + 1);
                        tvZanNum.setText(zanBean.getGiveUpCount() + "");
                        zanBean.setGiveUpState(1);
                        imgZan.setImageResource(R.mipmap.dianzan_on_icon);
                    }
                } else {
                    ToastUtils.getMineToast(successBean.getErrMsg());
                }

                llClick.setClickable(true);
            }

            @Override
            public void onFail() {
                llClick.setClickable(true);
            }
        });
    }


    //论坛管理 1=主贴审核通过,2锁定主贴,3=回帖审核通过,4=解锁帖子
    public void tieManagerAction(Context context, String id, String type, final OnCommonSuccessCallBack successCallBack) {
        LogUtils.i(UrlConstant.getInstance().URL
                + PostConstant.getInstance().TIE_MANAGER + FieldConstant.getInstance().ID + "=" + id + "&"
                + FieldConstant.getInstance().TYPE + "=" + type);
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().TIE_MANAGER + FieldConstant.getInstance().ID + "=" + id + "&"
                + FieldConstant.getInstance().TYPE + "=" + type, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    successCallBack.onSuccess();
                } else {
                    successCallBack.onFail();
                }
            }

            @Override
            public void onFail() {
                successCallBack.onFail();
            }
        });
    }

    //删除回帖
    public void deleteBackTie(Context context, String id, final OnCommonSuccessCallBack successCallBack) {
        LogUtils.i(UrlConstant.getInstance().URL
                + PostConstant.getInstance().DELETE_BACK_TIE + FieldConstant.getInstance().ID + "=" + id);
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().DELETE_BACK_TIE + FieldConstant.getInstance().ID + "=" + id, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    successCallBack.onSuccess();
                } else {
                    successCallBack.onFail();
                }
            }

            @Override
            public void onFail() {
                successCallBack.onFail();
            }
        });
    }


    //对回帖进行回帖
    public void backTieSecond(final Context context, String postId, String parentId, String repliesText, final OnCommonSuccessCallBack commonSuccessCallBack) {

        OkhttpUtil.getInstance().setUnCacheDataNoDialog(context,  UrlConstant.getInstance().URL
                + PostConstant.getInstance().BACK_ALL_TIE + FieldConstant.getInstance().POST_ID + "=" + postId + "&"
                + FieldConstant.getInstance().PARENT_ID + "=" + parentId + "&"
                + FieldConstant.getInstance().REPLIES_TXT + "=" + EncodeUtils.getInstance().getEncodeString(repliesText) + "&"
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    commonSuccessCallBack.onSuccess();
                    if (StringUtils.isNoEmpty(successBean.getErrMsg())) {
                        CopyIosDialogUtils.getInstance().getErrorDialog(context, successBean.getErrMsg(), new CopyIosDialogUtils.ErrDialogCallBack() {
                            @Override
                            public void confirm() {

                            }
                        });
                    }
                } else {
                    commonSuccessCallBack.onFail();
                    ToastUtils.getMineToast(successBean.getErrMsg());
                }
            }

            @Override
            public void onFail() {
                commonSuccessCallBack.onFail();
            }
        });
    }


    //改变浏览量
    public void changeReviewCount(Context context, String id, final OnCommonSuccessCallBack successCallBack) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(context, UrlConstant.getInstance().URL
                + PostConstant.getInstance().CHANGE_VIEW_COUNT + FieldConstant.getInstance().ID + "=" + id, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    successCallBack.onSuccess();
                } else {
                    successCallBack.onFail();
                }
            }

            @Override
            public void onFail() {
                successCallBack.onFail();
            }
        });
    }

    //收藏帖子
    public void saveTie(Context context, String postId) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().ADD_SAVE_TIE + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().POST_ID + "=" + postId, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("收藏成功");
                } else {
                    ToastUtils.getMineToast(successBean.getErrMsg());
                }
            }

            @Override
            public void onFail() {
                ToastUtils.getMineToast("收藏失败");
            }
        });
    }


    //删除原贴
    public void deleteTie(Context context, String id, final OnCommonSuccessCallBack successCallBack) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().POST_TIE
                + PostConstant.getInstance().DELETE_TIE + FieldConstant.getInstance().ID + "=" + id, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    successCallBack.onSuccess();
                } else {
                    successCallBack.onFail();
                }
            }

            @Override
            public void onFail() {
                successCallBack.onFail();
            }
        });
    }


}
