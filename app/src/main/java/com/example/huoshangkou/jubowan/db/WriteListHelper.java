package com.example.huoshangkou.jubowan.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.db
 * 类名：WriteListHelper
 * 描述：
 * 创建时间：2017-08-15  09:50
 */

public class WriteListHelper extends SQLiteOpenHelper {


    public WriteListHelper(Context context) {
        super(context, DBHelper.DB_NAME, null, DBHelper.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + DBHelper.TABLE_NAME + "("
                + DBHelper.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DBHelper.BASEMESSAGE + " TEXT NOT NULL,"
                + DBHelper.PROBLEM + " TEXT NOT NULL,"
                + DBHelper.TOOL_DESC + " TEXT NOT NULL,"
                + DBHelper.TOOL_LIST + " TEXT NOT NULL,"
                + DBHelper.SERVICE_PRICE + " TEXT NOT NULL,"
                + DBHelper.CONCLUSION + ")";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
