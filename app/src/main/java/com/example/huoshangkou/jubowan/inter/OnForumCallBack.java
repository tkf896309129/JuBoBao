package com.example.huoshangkou.jubowan.inter;

import com.example.huoshangkou.jubowan.bean.ForumBean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：OnForumCallBack
 * 描述：
 * 创建时间：2017-04-10  08:59
 */

public interface OnForumCallBack {

    void onSuccess(ForumBean forumBean);

    void onFail();

}
