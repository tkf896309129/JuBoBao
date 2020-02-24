package com.example.huoshangkou.jubowan.inter;

import com.example.huoshangkou.jubowan.bean.HomeBean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：HomeDataCallBack
 * 描述：
 * 创建时间：2017-05-05  14:28
 */

public interface HomeDataCallBack {

    void onSuccess(HomeBean homeBean);

    void onFail();
}
