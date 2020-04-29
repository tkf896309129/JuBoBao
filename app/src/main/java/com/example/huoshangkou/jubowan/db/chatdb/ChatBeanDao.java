package com.example.huoshangkou.jubowan.db.chatdb;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.example.huoshangkou.jubowan.db.ChatBean;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "chat".
*/
public class ChatBeanDao extends AbstractDao<ChatBean, Long> {

    public static final String TABLENAME = "chat";

    /**
     * Properties of entity ChatBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Type = new Property(1, int.class, "type", false, "TYPE");
        public final static Property HeadPic = new Property(2, String.class, "headPic", false, "HEAD_PIC");
        public final static Property Content = new Property(3, String.class, "content", false, "CONTENT");
        public final static Property Height = new Property(4, int.class, "height", false, "HEIGHT");
        public final static Property Width = new Property(5, int.class, "width", false, "WIDTH");
        public final static Property ImgPosition = new Property(6, int.class, "imgPosition", false, "IMG_POSITION");
        public final static Property Name = new Property(7, String.class, "name", false, "NAME");
    };


    public ChatBeanDao(DaoConfig config) {
        super(config);
    }
    
    public ChatBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"chat\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"TYPE\" INTEGER NOT NULL ," + // 1: type
                "\"HEAD_PIC\" TEXT," + // 2: headPic
                "\"CONTENT\" TEXT," + // 3: content
                "\"HEIGHT\" INTEGER NOT NULL ," + // 4: height
                "\"WIDTH\" INTEGER NOT NULL ," + // 5: width
                "\"IMG_POSITION\" INTEGER NOT NULL ," + // 6: imgPosition
                "\"NAME\" TEXT);"); // 7: name
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"chat\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ChatBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getType());
 
        String headPic = entity.getHeadPic();
        if (headPic != null) {
            stmt.bindString(3, headPic);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(4, content);
        }
        stmt.bindLong(5, entity.getHeight());
        stmt.bindLong(6, entity.getWidth());
        stmt.bindLong(7, entity.getImgPosition());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(8, name);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ChatBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getType());
 
        String headPic = entity.getHeadPic();
        if (headPic != null) {
            stmt.bindString(3, headPic);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(4, content);
        }
        stmt.bindLong(5, entity.getHeight());
        stmt.bindLong(6, entity.getWidth());
        stmt.bindLong(7, entity.getImgPosition());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(8, name);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ChatBean readEntity(Cursor cursor, int offset) {
        ChatBean entity = new ChatBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // type
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // headPic
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // content
            cursor.getInt(offset + 4), // height
            cursor.getInt(offset + 5), // width
            cursor.getInt(offset + 6), // imgPosition
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7) // name
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ChatBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setType(cursor.getInt(offset + 1));
        entity.setHeadPic(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setContent(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setHeight(cursor.getInt(offset + 4));
        entity.setWidth(cursor.getInt(offset + 5));
        entity.setImgPosition(cursor.getInt(offset + 6));
        entity.setName(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ChatBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ChatBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected boolean hasKey(ChatBean entity) {
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
