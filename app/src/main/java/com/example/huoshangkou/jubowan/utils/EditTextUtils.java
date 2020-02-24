package com.example.huoshangkou.jubowan.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：EditTextUtils
 * 描述：
 * 创建时间：2017-03-10  14:47
 */

public class EditTextUtils {

    private static class EditTextHepler {
        private static EditTextUtils INSTANCE = new EditTextUtils();
    }

    public static EditTextUtils getInstance() {
        return EditTextHepler.INSTANCE;
    }

    //设置不可编辑
    public void setUnEdit(EditText edit) {
        edit.setFocusable(false);
        edit.setFocusableInTouchMode(false);
    }


}
