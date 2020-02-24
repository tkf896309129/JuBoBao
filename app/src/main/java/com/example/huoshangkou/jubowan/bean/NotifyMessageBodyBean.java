package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：NotifyMessageBodyBean
 * 描述：
 * 创建时间：2017-05-04  10:10
 */

public class NotifyMessageBodyBean {

    private String title;
    private String ticker;
    private String text;
    private int builder_id;
    private String custom;
    private String after_open;
    private String play_vibrate;
    private String play_sound;
    private String play_lights;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAfter_open() {
        return after_open;
    }

    public void setAfter_open(String after_open) {
        this.after_open = after_open;
    }

    public String getPlay_vibrate() {
        return play_vibrate;
    }

    public void setPlay_vibrate(String play_vibrate) {
        this.play_vibrate = play_vibrate;
    }

    public String getPlay_sound() {
        return play_sound;
    }

    public void setPlay_sound(String play_sound) {
        this.play_sound = play_sound;
    }

    public String getPlay_lights() {
        return play_lights;
    }

    public void setPlay_lights(String play_lights) {
        this.play_lights = play_lights;
    }

    public int getBuilder_id() {
        return builder_id;
    }

    public void setBuilder_id(int builder_id) {
        this.builder_id = builder_id;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }
}
