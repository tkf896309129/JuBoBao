package com.example.huoshangkou.jubowan.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import butterknife.ButterKnife;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.base
 * 类名：BaseListFragment
 * 描述：
 * 创建时间：2020-03-13  09:46
 */

public abstract class BaseListFragment extends Fragment {
    private boolean isVisible = false;
    private boolean isInit = false;
    private boolean isLoadOver = false;

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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        isVisible = isVisibleToUser;
        setParam();
        LogUtils.e("setUserVisibleHint");
        super.setUserVisibleHint(isVisibleToUser);
    }

    private void setParam() {
        if (isInit && !isLoadOver && isVisible) {
            initData();
            isLoadOver = true;
        }

//        if (!isInit) {
//            return;
//        }
//        if (isVisible) {
//            initData();
//            isLoadOver = true;
//        }
//        if(isLoadOver){
//            stopLoad();
//        }


    }

    protected View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, rootView);
            isInit = true;
            LogUtils.e("onCreateView");
            setParam();
        }
        return rootView;
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

    protected void stopLoad() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
