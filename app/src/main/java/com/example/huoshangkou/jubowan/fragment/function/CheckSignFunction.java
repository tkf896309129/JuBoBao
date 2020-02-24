package com.example.huoshangkou.jubowan.fragment.function;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment.function
 * 类名：CheckSignFunction
 * 描述：
 * 创建时间：2017-04-19  09:58
 */

public class CheckSignFunction {

    private static class CheckSignHelper {
        private static CheckSignFunction INSTANCE = new CheckSignFunction();
    }

    public static CheckSignFunction getInstance() {
        return CheckSignHelper.INSTANCE;
    }


}
