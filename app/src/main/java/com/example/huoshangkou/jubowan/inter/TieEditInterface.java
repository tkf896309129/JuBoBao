package com.example.huoshangkou.jubowan.inter;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.businessmarket
 * 类名：TieEditInterface
 * 描述：
 * 创建时间：2016-11-09  10:09
 */
public interface TieEditInterface {

    //审核帖子
    void checkTie(String id, String tieId);

    //锁上原贴
    void clockTie(String id);

    //删除原贴
    void deleteTie(String id);

    //解锁
    void unClockTie(String id);

    //删除回帖
    void deleteBackTie(String id);

    //分享帖子
    void shareTie(String id);

    //收藏帖子
    void saveTie(String id);

}
