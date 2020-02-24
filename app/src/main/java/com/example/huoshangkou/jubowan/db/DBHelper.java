package com.example.huoshangkou.jubowan.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.db
 * 类名：DBHelper
 * 描述：
 * 创建时间：2017-08-15  09:50
 */

public class DBHelper {
    //下午5点的时候
    public static final String TAG = "DBHElper";
    public static final String DB_NAME = "writeList.db";
    public static final int VERSION = 1;
    public static final String TABLE_NAME = "writeList";
    public static final String ID = "id";
    public static final String BASEMESSAGE = "baseMessage";
    public static final String PROBLEM = "problem";
    public static final String TOOL_DESC = "toolDesc";
    public static final String TOOL_LIST = "toolList";
    public static final String SERVICE_PRICE = "servicePrice";
    public static final String CONCLUSION = "conclusion";

    //声明数据库操作对象
    private WriteListHelper areaHelper;
    //数据库操作对象
    private static SQLiteDatabase db;

    public DBHelper(Context context) {
        areaHelper = new WriteListHelper(context);
        db = areaHelper.getWritableDatabase();
    }

    //增加
    public static void add(WriteDbListBean areaBean) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BASEMESSAGE, areaBean.getBaseMessage());
        contentValues.put(PROBLEM, areaBean.getProblem());
        contentValues.put(TOOL_DESC, areaBean.getToolDesc());
        contentValues.put(TOOL_LIST, areaBean.getToolList());
        contentValues.put(SERVICE_PRICE, areaBean.getServicePrice());
        contentValues.put(CONCLUSION, areaBean.getConclusion());
        contentValues.put(ID, areaBean.getId());
        db.insert(TABLE_NAME, null, contentValues);
    }

    //删除
    public static void delete(int id) {
        String whereCalues = ID + "=?";
        String[] whereArgs = {id + ""};
        db.delete(TABLE_NAME, whereCalues, whereArgs);
    }

    //修改
    public static void check(WriteDbListBean areaBean) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, areaBean.getId());
        contentValues.put(BASEMESSAGE, areaBean.getBaseMessage());
        contentValues.put(PROBLEM, areaBean.getProblem());
        contentValues.put(TOOL_DESC, areaBean.getToolDesc());
        contentValues.put(TOOL_LIST, areaBean.getToolList());
        contentValues.put(SERVICE_PRICE, areaBean.getServicePrice());
        contentValues.put(CONCLUSION, areaBean.getConclusion());
        String whereCalues = ID + "?=";
        String[] whereArgs = {areaBean.getId() + ""};
        db.update(TABLE_NAME, contentValues, whereCalues, whereArgs);
    }

    //单个查询
    public static WriteDbListBean querySingle(String id) {
        WriteDbListBean areaBean = null;
        Cursor cursor = db.query(TABLE_NAME, null, ID + "=?", new String[]{id}, null, null, null);
        if (cursor.moveToNext()) {
            areaBean = new WriteDbListBean();
            areaBean.setId(cursor.getString(cursor.getColumnIndex(ID)));
            areaBean.setBaseMessage(cursor.getString(cursor.getColumnIndex(BASEMESSAGE)));
            areaBean.setConclusion(cursor.getString(cursor.getColumnIndex(CONCLUSION)));
            areaBean.setProblem(cursor.getString(cursor.getColumnIndex(PROBLEM)));
            areaBean.setServicePrice(cursor.getString(cursor.getColumnIndex(SERVICE_PRICE)));
            areaBean.setToolDesc(cursor.getString(cursor.getColumnIndex(TOOL_DESC)));
            areaBean.setToolList(cursor.getString(cursor.getColumnIndex(TOOL_LIST)));
        }
        return areaBean;
    }

    //全查询
    public static List queryAll() {
        List<WriteDbListBean> areaBeanList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            WriteDbListBean areaBean = new WriteDbListBean();
            areaBean.setId(cursor.getString(cursor.getColumnIndex(ID)));
            areaBean.setBaseMessage(cursor.getString(cursor.getColumnIndex(BASEMESSAGE)));
            areaBean.setConclusion(cursor.getString(cursor.getColumnIndex(CONCLUSION)));
            areaBean.setProblem(cursor.getString(cursor.getColumnIndex(PROBLEM)));
            areaBean.setServicePrice(cursor.getString(cursor.getColumnIndex(SERVICE_PRICE)));
            areaBean.setToolDesc(cursor.getString(cursor.getColumnIndex(TOOL_DESC)));
            areaBean.setToolList(cursor.getString(cursor.getColumnIndex(TOOL_LIST)));
            areaBeanList.add(areaBean);
        }
        return areaBeanList;
    }


}
