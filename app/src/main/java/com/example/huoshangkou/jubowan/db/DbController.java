package com.example.huoshangkou.jubowan.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.huoshangkou.jubowan.db.chatdb.ChatDaoHelper;
import com.example.huoshangkou.jubowan.db.chatdb.DaoMaster;
import com.example.huoshangkou.jubowan.db.chatdb.ChatBeanDao;
import com.example.huoshangkou.jubowan.db.chatdb.DaoSession;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.db
 * 类名：DbController
 * 描述：
 * 创建时间：2020-04-02  09:36
 */

public class DbController {

    private ChatDaoHelper helper;
    private SQLiteDatabase sqLiteDatabase;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private Context context;
    private String DB_NAME = "chat_message";
    private ChatBeanDao searchBeanDao;

    private static DbController dbController;

    public static DbController getInstance(Context context) {
        if (dbController == null) {
            synchronized (DbController.class) {
                if (dbController == null) {
                    dbController = new DbController(context);
                }
            }
        }
        return dbController;
    }


    public DbController(Context context) {
        this.context = context;
        helper = new ChatDaoHelper(context, DB_NAME);
        daoMaster = new DaoMaster(helper.getWritableDb());
        daoSession = daoMaster.newSession();
        searchBeanDao = daoSession.getChatBeanDao();
    }

    //获取可读数据库
    public SQLiteDatabase getReadDatabase() {
        if (helper == null) {
            helper = new ChatDaoHelper(context, DB_NAME);
        }
        SQLiteDatabase database = helper.getReadableDatabase();
        return database;
    }

    //获取可写数据库
    public SQLiteDatabase getWriteDatabase() {
        if (helper == null) {
            helper = new ChatDaoHelper(context, DB_NAME);
        }
        SQLiteDatabase database = helper.getWritableDatabase();
        return database;
    }

    //判断插入还是替换
    public void insertOrReplace(ChatBean ChatBean) {
        daoSession.insertOrReplace(ChatBean);
    }

    public void insert(ChatBean chatBean){
        daoSession.insert(chatBean);
    }

    //更新数据
    public void updateData(ChatBean ChatBean, String change) {
        ChatBean mOldPersonInfor = searchBeanDao.queryBuilder().where(ChatBeanDao.Properties.Id.eq(ChatBean.getId())).build().unique();//拿到之前的记录
        if (mOldPersonInfor != null) {
            mOldPersonInfor.setContent(change);
            searchBeanDao.update(mOldPersonInfor);
        }
    }

    //删除
    public void delete(String content) {
        searchBeanDao.queryBuilder().where(ChatBeanDao.Properties.Content.eq(content)).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    //查询
    public List<ChatBean> quary(String name) {
        List<ChatBean> list = searchBeanDao.queryBuilder().where(ChatBeanDao.Properties.Name.eq(name)).list();
        return list;
    }


}
