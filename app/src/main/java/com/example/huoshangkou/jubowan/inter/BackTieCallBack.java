package com.example.huoshangkou.jubowan.inter;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：BackTieCallBack
 * 描述：
 * 创建时间：2017-04-19  13:29
 */

public interface BackTieCallBack {

    //PostID 主帖id
    //ParentID 被回帖的ID
    //UserID 当前回帖人id
    void onBackTie(String name, String postId, String parentId);

}
