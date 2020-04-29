package com.example.huoshangkou.jubowan.fragment.function;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.huoshangkou.jubowan.adapter.CheckByManTopAdapter;
import com.example.huoshangkou.jubowan.bean.SelectManBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment.function
 * 类名：CheckByManHelper
 * 描述：
 * 创建时间：2020-04-27  09:04
 */

public class CheckByManHelper {

    private static CheckByManHelper instace;

    public static CheckByManHelper getInstace() {
        if (instace == null) {
            instace = new CheckByManHelper();
        }
        return instace;
    }

}
