package com.example.huoshangkou.jubowan.db.chatdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.huoshangkou.jubowan.db.DBHelper;
import com.github.yuweiguocn.library.greendao.MigrationHelper;

import org.greenrobot.greendao.database.Database;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.db.chatdb
 * 类名：ChatDaoHelper
 * 描述：
 * 创建时间：2020-04-09  13:30
 */

public class ChatDaoHelper extends DaoMaster.OpenHelper {
    public ChatDaoHelper(Context context, String name) {
        super(context, name);
    }

    public ChatDaoHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        MigrationHelper.migrate(db,new MigrationHelper.ReCreateAllTableListener(){

            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db,ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db,ifExists);
            }
        },ChatBeanDao.class);

    }
}
