package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.ToolDetailsActivity;
import com.example.huoshangkou.jubowan.adapter.ToolSearchAdapter;
import com.example.huoshangkou.jubowan.bean.SearchToolBean;
import com.example.huoshangkou.jubowan.bean.SearchToolListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：ToolSearchWindowUtils
 * 描述：
 * 创建时间：2017-06-20  09:39
 */

public class ToolSearchWindowUtils {

    PopupWindow popupSearchWindow;
    List<SearchToolListBean> searchList;
    ToolSearchAdapter searchAdapter;

    private static class ToolSearchHelper {
        private static ToolSearchWindowUtils getInstance = new ToolSearchWindowUtils();
    }

    public static ToolSearchWindowUtils getInstance() {
        return ToolSearchHelper.getInstance;
    }

    /**
     * 添加Popupwindow获取搜索界面弹出框
     */
    public void getSearchPopupWindow(View v, final Context context) {
        searchList = new ArrayList<>();
        searchAdapter = new ToolSearchAdapter(context, searchList, R.layout.item_tool_search);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_tool_window, null);
        final EditText etSearch = (EditText) view.findViewById(R.id.et_search);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        ListView lvSearch = (ListView) view.findViewById(R.id.lv_detail_search);
        LinearLayout llBack = (LinearLayout) view.findViewById(R.id.ll_back);
        lvSearch.setAdapter(searchAdapter);
        lvSearch.setDividerHeight(0);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                popupSearchWindow.dismiss();
                String keyWord = etSearch.getText().toString().trim();
                if (!StringUtils.isNoEmpty(keyWord)) {
                    ToastUtils.getMineToast("请输入关键字");
                    return;
                }

                getSearchData(context, keyWord);
            }
        });


        lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentUtils.getInstance().toActivity(context, ToolDetailsActivity.class, UrlConstant.getInstance().TOOL_UEL + searchList.get(i).getID());
            }
        });

        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupSearchWindow.dismiss();
            }
        });

        popupSearchWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);

        //让Popupwindow上面的控件获取焦点
        popupSearchWindow.setFocusable(true);
        //防止其他的控件没有焦点
        popupSearchWindow.setBackgroundDrawable(new BitmapDrawable());
        popupSearchWindow.setOutsideTouchable(true);
        popupSearchWindow.setAnimationStyle(R.style.PopupAnimations);
        //在底部显示Popupwindow
        popupSearchWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
    }


    public void getSearchData(Context mContext, String keyWord) {
        OkhttpUtil.getInstance().setUnCacheData(mContext, mContext.getString(R.string.loading_message),
                UrlConstant.getInstance().URL + PostConstant.getInstance().GET_MAINTAIN_PRODUCT
                        + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId()
                        + FieldConstant.getInstance().KEY_WORD + "=" + EncodeUtils.getInstance().getEncodeString(keyWord)
                , new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        searchList.clear();
                        SearchToolBean toolBean = JSON.parseObject(json, SearchToolBean.class);
                        searchList.addAll(toolBean.getReList());
                        searchAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFail() {

                    }
                });
    }
}
