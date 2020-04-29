package com.example.huoshangkou.jubowan.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.ButterKnife;


/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.base
 * 类名：BaseFragment
 * 描述：
 * 创建时间：2016-12-28  10:55
 */

public abstract class BaseFragment extends Fragment {



    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            FragmentManager manager = getChildFragmentManager();
            manager.popBackStackImmediate(null, 1);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    protected View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initData();
    }

    /**
     * 布局加载
     *
     * @return
     */
    public abstract int getLayoutId();


    /**
     * 数据逻辑处理
     */
    public abstract void initData();


    @Override
    public void onDestroy() {
        OkHttpUtils.getInstance().cancelTag(this);
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
