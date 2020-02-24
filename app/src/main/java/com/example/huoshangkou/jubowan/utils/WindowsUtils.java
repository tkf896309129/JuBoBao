package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by tang on 2015/12/16.
 */
public class WindowsUtils {
    private Context context;
    private WindowManager manager;


    public WindowsUtils(Context context) {
        this.context = context;
        manager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
    }



    public static int getWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }
    public static int getHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }




}
