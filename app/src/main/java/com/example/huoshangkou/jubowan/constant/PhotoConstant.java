package com.example.huoshangkou.jubowan.constant;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.constant
 * 类名：PhotoConstant
 * 描述：相册的一些变量配置
 * 创建时间：2017-02-14  10:11
 */

public class PhotoConstant {

    private static class PhotoHelper {
        private static PhotoConstant INSTANCE = new PhotoConstant();
    }

    public static PhotoConstant getInstance() {
        return PhotoHelper.INSTANCE;
    }


    //论坛界面添加图片是否显示
    public  final String TIE_ADD_PHOTOT = "addPhoto";

    //判断是不是论坛
    public  final String TIE_TYPE = "tiezi";

    //判断图片是不是可以删除的
    public  final String IS_NO_DELETE = "no_delete";


}
