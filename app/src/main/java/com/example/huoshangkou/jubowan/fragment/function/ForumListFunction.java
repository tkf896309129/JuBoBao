package com.example.huoshangkou.jubowan.fragment.function;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.ForumBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnForumCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment.function
 * 类名：ForumListFunction
 * 描述：
 * 创建时间：2017-04-08  09:38
 */

public class ForumListFunction {

    private static class ForumListHelper {
        private static ForumListFunction INSTANCE = new ForumListFunction();
    }

    public static ForumListFunction getInstance() {
        return ForumListHelper.INSTANCE;
    }

    /**
     * 维修维保专区【hot】 9
     * 原片交流专区 1
     * 辅料交流专区 2
     * 物流送货哪家强 3
     * 时事新闻 4
     * 搞笑内涵 5
     * 灌水吐槽 6
     * 生活那些事儿 7
     * 获取论坛数据
     */
    public void getForumData(Context context, String postTieId, int pageSize, String checkState, int rank, final OnForumCallBack callBack) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(context, UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_FORUM_TIE
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().POST_TIE_ID + "=" + postTieId + "&"
                + FieldConstant.getInstance().PAGE_SIZE + "=" + pageSize + "&"
                + FieldConstant.getInstance().POST_TIE_STATE + "=" + checkState + "&"
                + FieldConstant.getInstance().RANK + "=" + rank, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                ForumBean forumBean = JSON.parseObject(json, ForumBean.class);
                callBack.onSuccess(forumBean);
            }

            @Override
            public void onFail() {

            }
        });
    }


    //获取待审回帖
    public void getCheckBackTie(Context context, int pageSize, final StringCallBack stringCallBack) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_REPLIES + FieldConstant.getInstance().POST_ID + "=" + "&"
                + FieldConstant.getInstance().REPLIES_STATE + "=0" + "&"
                + FieldConstant.getInstance().PAGE_SIZE + "=" + pageSize, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                stringCallBack.onSuccess(json);
            }

            @Override
            public void onFail() {
                stringCallBack.onFail();
            }
        });
    }


    //收藏帖子
    public void getSaveTieData(Context context) {
//        OkhttpUtil.getInstance().setUnCacheData(context,context.getString(R.string.loading_message),UrlConstant.getInstance().URL
//        +PostConstant.getInstance().SAVE_TIE);
    }

}
