package com.example.huoshangkou.jubowan.utils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：PageSizeUtils
 * 描述：分页工具类
 * 创建时间：2017-04-26  10:03
 */

public class PageSizeUtils {

    private static class PageSizeHelper {
        private static PageSizeUtils INSTANCE = new PageSizeUtils();
    }

    public static PageSizeUtils getInstance() {
        return PageSizeHelper.INSTANCE;
    }

    /**
     * 判断集合是否需要刷新
     */
    public <T> void isNeedInit(List<T> detailsList) {
        int size = detailsList.size();
        //有多少个20的数组
        //取莫
        int smallSize = size % 20;
        int num = size - smallSize;
        for (int i = size - 1; i >= num; i--) {
            detailsList.remove(i);
        }
    }

    //改变页码
    public <T> int changePage(List<T> detailsList) {
        int pageSize;
        int size = detailsList.size();
        //有多少个20的数组
        int bigSize = size / 20;
        //取莫
        pageSize = bigSize + 1;
        return pageSize;
    }


}
