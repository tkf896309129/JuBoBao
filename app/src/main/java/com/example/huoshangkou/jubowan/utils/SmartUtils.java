package com.example.huoshangkou.jubowan.utils;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：SmartUtils
 * 描述：
 * 创建时间：2019-11-22  18:04
 */

public class SmartUtils {

    public static void finishSmart(SmartRefreshLayout smartRefreshLayout){
        if(smartRefreshLayout!=null){
            smartRefreshLayout.finishLoadMore();
            smartRefreshLayout.finishRefresh();
        }
    }

}
