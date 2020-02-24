package com.example.huoshangkou.jubowan.inter;

import com.example.huoshangkou.jubowan.bean.LoginMessageObjBean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：OnThreeLoginBack
 * 描述：第三方登录接口回调
 * 创建时间：2017-02-28  09:28
 */

public interface OnThreeLoginBack {

    void threeLoginSuccess(LoginMessageObjBean loginMessageObjBean);

    void threeLoginFail(String errMsg);

}
