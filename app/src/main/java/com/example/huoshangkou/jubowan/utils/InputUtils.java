package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：InputUtils
 * 描述：
 * 创建时间：2017-03-14  15:07
 */

public class InputUtils {

    private static class InputHelper {
        private static InputUtils INSTANCE = new InputUtils();
    }

    public static InputUtils getInstance() {
        return InputHelper.INSTANCE;
    }

    public void inputKeyUtils(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
