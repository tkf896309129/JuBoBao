package com.example.huoshangkou.jubowan.base;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.base
 * 类名：ViewHolder
 * 描述：
 * 创建时间：2016-12-28  11:01
 */

import java.util.HashMap;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewHolder {
    //private Map<Integer,View> mViews;
    private SparseArray<View> mViews;

    private View mConvertView;
    private int mPosition;// 记录位置

    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        this.mPosition = position;
        //mViews=new HashMap<Integer, View>();
        mViews = new SparseArray<View>();
        mConvertView.setTag(this);
    }

    /**
     * 让用户去使用该静态方法去构造ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }

    /*
     * 通过viewId获取控件
     */
    public <T extends View> T getView(int resId) {
        View v = mViews.get(resId);
        if (v == null) {
            v = mConvertView.findViewById(resId);
            //加入到map里面
            mViews.put(resId, v);
        }
        return (T) v;
    }

    public View getmConvertView() {
        return mConvertView;
    }
}