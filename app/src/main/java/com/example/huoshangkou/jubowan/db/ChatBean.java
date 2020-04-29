package com.example.huoshangkou.jubowan.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.chat
 * 类名：ChatBean
 * 描述： Error:Execution failed for task ':app:greendao'.
 > Constructor (see ChatBean:26) has been changed after generation.
 Please either mark it with @Keep annotation instead of @Generated to keep it untouched,
 or use @Generated (without hash) to allow to replace it.
 * 创建时间：2020-04-07  15:28
 */
@Entity(nameInDb = "chat")
public class ChatBean {
    @Id(autoincrement = true)
    private Long id;
    private int type;
    private String headPic;
    private String content;
    private int height;
    private int width;
    private int imgPosition;
    private String name;

    @Generated(hash = 487334841)
    public ChatBean(Long id, int type, String headPic, String content, int height, int width,
            int imgPosition, String name) {
        this.id = id;
        this.type = type;
        this.headPic = headPic;
        this.content = content;
        this.height = height;
        this.width = width;
        this.imgPosition = imgPosition;
        this.name = name;
    }

    @Generated(hash = 1872716502)
    public ChatBean() {
    }

    public ChatBean addType(int type) {
        setType(type);
        return this;
    }

    public ChatBean addHeadPic(String headPic) {
        setHeadPic(headPic);
        return this;
    }

    public ChatBean addContent(String content) {
        setContent(content);
        return this;
    }

    public ChatBean addHeight(int height) {
        setHeight(height);
        return this;
    }

    public ChatBean addWdth(int width) {
        setWidth(width);
        return this;
    }

    public ChatBean addImgPosition(int bitmapPosition) {
        setImgPosition(bitmapPosition);
        return this;
    }

    public ChatBean addName(String name) {
        setName(name);
        return this;
    }


    public int getImgPosition() {
        return imgPosition;
    }

    public void setImgPosition(int imgPosition) {
        this.imgPosition = imgPosition;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
