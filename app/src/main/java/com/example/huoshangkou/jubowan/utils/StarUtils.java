package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.StarAdapter;
import com.example.huoshangkou.jubowan.view.ScrollGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：StarUtils
 * 描述：
 * 创建时间：2019-03-25  14:46
 */

public class StarUtils {
    public static void setStars(Context context, int stars, RecyclerView recyclerView) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(stars);
        }
        StarAdapter starAdapter = new StarAdapter(context, list);
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
    }
}
