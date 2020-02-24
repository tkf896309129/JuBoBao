package com.example.huoshangkou.jubowan.widget.pinyin;

import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.ArrayList;

//import com.liucanwen.citylist.widget.pinyin.HanziToPinyin3.Token;

public class PinYin {
    // 汉字返回拼音，字母原样返回，都转换为小写
    public static String getPinYin(String input) {
        ArrayList<HanziToPinyin3.Token> tokens = HanziToPinyin3.getInstance().get(input);
        StringBuilder sb = new StringBuilder();
        if (tokens != null && tokens.size() > 0) {
            for (HanziToPinyin3.Token token : tokens) {
                if (HanziToPinyin3.Token.PINYIN == token.type) {
                    sb.append(token.target);
                } else {
                    sb.append(token.source);
                }
            }
        }
        LogUtils.e(sb.toString() + "  " + sb.toString().toLowerCase());
        if (StringUtils.isNoEmpty(input) && input.substring(0, 1).equals("查")) {
            return "z" + sb.toString().toLowerCase().substring(1, sb.length());
        } else {
            return sb.toString().toLowerCase();
        }
    }
}
