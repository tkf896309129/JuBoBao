package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.TieTypeAdapter;
import com.example.huoshangkou.jubowan.bean.TieTypeBean;
import com.example.huoshangkou.jubowan.constant.EventBusConstant;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.inter.OnTieSelectCallBack;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：TieSelectWindow
 * 描述：发表帖子类型的弹出框
 * 创建时间：2017-02-13  10:57
 */

public class TieSelectWindow {

    private static int clickPosition = -1;

    /**
     * 论坛类型选择弹出框
     */
    public static void tieSelectWindow(Context context, View v, final List<TieTypeBean> tieTtypeList, final OnTieSelectCallBack selectCallBack) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_select_tie, null);
        ListView lvTie = (ListView) view.findViewById(R.id.lv_select_tie);

        final TieTypeAdapter tieTypeAdapter = new TieTypeAdapter(context, tieTtypeList, R.layout.item_tie_type);
        lvTie.setAdapter(tieTypeAdapter);
        lvTie.setDividerHeight(0);

        tieTypeAdapter.setItemClickPositoin(clickPosition);
        tieTypeAdapter.notifyDataSetChanged();


        final PopupWindow popupSearchWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);

        lvTie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                clickPosition = i;
                tieTypeAdapter.setItemClickPositoin(i);
                tieTypeAdapter.notifyDataSetChanged();
                selectCallBack.onClick(tieTtypeList.get(i));
                popupSearchWindow.dismiss();
            }
        });

        popupSearchWindow.setBackgroundDrawable(context.getResources().getDrawable(R.color.alpha_black));

        //让Popupwindow上面的控件获取焦点
        popupSearchWindow.setFocusable(true);
        //防止其他的控件没有焦点
        popupSearchWindow.setBackgroundDrawable(new BitmapDrawable());
        popupSearchWindow.setOutsideTouchable(true);
        //在底部显示Popupwindow
        popupSearchWindow.showAsDropDown(v, 0, 10);
    }


    /**
     * 选择排序方式
     */
    public static void tieSelectSortWindow(Context context, View v, final int position, final int tabPosition, final OnPositionClick positionClick) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_tie_sort, null);
        RadioGroup rgSort = (RadioGroup) view.findViewById(R.id.rg_sort);

        if (position != -1 && position <= 1) {
            RadioButton rbSort = (RadioButton) rgSort.getChildAt(position);
            rbSort.setChecked(true);
        }


        final PopupWindow popupSearchWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);


        //让Popupwindow上面的控件获取焦点
        popupSearchWindow.setFocusable(true);
        //防止其他的控件没有焦点
        popupSearchWindow.setBackgroundDrawable(new BitmapDrawable());
        popupSearchWindow.setOutsideTouchable(true);
        //在底部显示Popupwindow
        popupSearchWindow.showAsDropDown(v, 0, 1);

        rgSort.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_time_sort:
                        positionClick.onPositionClick(0);
                        popupSearchWindow.dismiss();
                        EventBus.getDefault().post(EventBusConstant.getInstance().TIME_SORT+ "," + tabPosition, EventBusConstant.getInstance().FORUM_SORT );
                        break;
                    case R.id.rb_hot_sort:
                        positionClick.onPositionClick(1);
                        popupSearchWindow.dismiss();
                        EventBus.getDefault().post(EventBusConstant.getInstance().HOT_SORT+ "," + tabPosition, EventBusConstant.getInstance().FORUM_SORT );
                        break;
                }
            }
        });

    }


}
