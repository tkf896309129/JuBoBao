package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.bigkoo.pickerview.TimePickerView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.inter.OnDateTimeCallBack;
import com.example.huoshangkou.jubowan.inter.OnTimeCallBack;
import com.example.huoshangkou.jubowan.inter.OnTimeDataCallBack;
import com.example.huoshangkou.jubowan.inter.PickPositonCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.inter.StringPositionCallBack;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：TimeDialogUtils
 * 描述：时间弹出框封装类
 * 创建时间：2017-03-01  11:45
 */

public class TimeDialogUtils {

    private static class TimeDialogHelper {
        private static TimeDialogUtils INSTANCE = new TimeDialogUtils();
    }

    public static TimeDialogUtils getInstance() {
        return TimeDialogHelper.INSTANCE;
    }


    //时间选择器
    TimePickerView timePickerView;

    public void getTime(Context context, final OnTimeCallBack timeCallBack) {

        Calendar startDate = Calendar.getInstance();
        startDate.set(2000, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2100, 0, 1);

        timePickerView = new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String format = formatter.format(date);
                timeCallBack.getYearTime(format);

                SimpleDateFormat formMinute = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formatMinute = formMinute.format(date);
                timeCallBack.getMinuteTime(formatMinute);
            }
        }).setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .setCancelText(context.getString(R.string.cancel_btn))
                .setSubmitText(context.getString(R.string.sure_btn))
                .setContentSize(18)
                .setTitleSize(15)
                .setSubCalSize(15)
//                .setRangDate(startDate,endDate)
                .setCancelColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setDividerColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setTextColorCenter(context.getResources().getColor(R.color.main_tab_blue_white))
                .setTitleBgColor(context.getResources().getColor(R.color.white))
                .setTitleColor(context.getResources().getColor(R.color.address_black_key))
                .setBgColor(context.getResources().getColor(R.color.white))
                .setSubmitColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setLineSpacingMultiplier(1.6f)
                .setTitleText("选择时间")
                .setOutSideCancelable(true)// default is true
                .isCyclic(false)// default is false
                .build();
        timePickerView.show();
    }


    public void getTimeHour(Context context, final OnTimeCallBack timeCallBack) {

        timePickerView = new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH");
                String format = formatter.format(date);
                timeCallBack.getYearTime(format);

                SimpleDateFormat formMinute = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formatMinute = formMinute.format(date);
                timeCallBack.getMinuteTime(formatMinute);
            }
        }).setType(TimePickerView.Type.YEAR_MONTH_DAY_HOUR_MIN)
                .setCancelText(context.getString(R.string.cancel_btn))
                .setSubmitText(context.getString(R.string.sure_btn))
                .setContentSize(18)
                .setTitleSize(15)
                .setSubCalSize(15)
                .setCancelColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setSubmitColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setDividerColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setTextColorCenter(context.getResources().getColor(R.color.main_tab_blue_white))
                .setTitleBgColor(context.getResources().getColor(R.color.white))
                .setTitleColor(context.getResources().getColor(R.color.address_black_key))
                .setBgColor(context.getResources().getColor(R.color.white))
                .setLineSpacingMultiplier(1.6f)
                .setTitleText("选择时间")
                .setOutSideCancelable(true)// default is true
                .isCyclic(false)// default is false
                .build();
        timePickerView.show();
    }

    public void getTimeHour(Context context, final OnDateTimeCallBack timeCallBack) {

        timePickerView = new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat formMinute = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formatMinute = formMinute.format(date);
                timeCallBack.onTimeDate(formatMinute, date);
            }
        }).setType(TimePickerView.Type.YEAR_MONTH_DAY_HOUR_MIN)
                .setCancelText(context.getString(R.string.cancel_btn))
                .setSubmitText(context.getString(R.string.sure_btn))
                .setContentSize(18)
                .setTitleSize(15)
                .setSubCalSize(15)
                .setCancelColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setSubmitColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setDividerColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setTextColorCenter(context.getResources().getColor(R.color.main_tab_blue_white))
                .setTitleBgColor(context.getResources().getColor(R.color.white))
                .setTitleColor(context.getResources().getColor(R.color.address_black_key))
                .setBgColor(context.getResources().getColor(R.color.white))
                .setLineSpacingMultiplier(1.6f)
                .setTitleText("选择时间")
                .setOutSideCancelable(true)// default is true
                .isCyclic(false)// default is false
                .build();
        timePickerView.show();
    }

    public void getTime(Context context, final OnTimeDataCallBack timeCallBack) {

        timePickerView = new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                timeCallBack.getData(date);
            }
        }).setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .setCancelText(context.getString(R.string.cancel_btn))
                .setSubmitText(context.getString(R.string.sure_btn))
                .setContentSize(18)
                .setTitleSize(15)
                .setSubCalSize(15)
                .setLineSpacingMultiplier(1.6f)
                .setCancelColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setSubmitColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setDividerColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setTextColorCenter(context.getResources().getColor(R.color.main_tab_blue_white))
                .setTitleBgColor(context.getResources().getColor(R.color.white))
                .setTitleColor(context.getResources().getColor(R.color.address_black_key))
                .setBgColor(context.getResources().getColor(R.color.white))
                .setTitleText("选择时间")
                .setOutSideCancelable(true)// default is true
                .isCyclic(false)// default is false
                .build();

        timePickerView.show();
    }


    public void getTime(Context context, final StringCallBack timeCallBack) {

        timePickerView = new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String format = formatter.format(date);
                timeCallBack.onSuccess(format);
            }
        }).setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .setCancelText(context.getString(R.string.cancel_btn))
                .setSubmitText(context.getString(R.string.sure_btn))
                .setContentSize(18)
                .setTitleSize(15)
                .setCancelColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setSubmitColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setDividerColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setTextColorCenter(context.getResources().getColor(R.color.main_tab_blue_white))
                .setTitleBgColor(context.getResources().getColor(R.color.white))
                .setTitleColor(context.getResources().getColor(R.color.address_black_key))
                .setBgColor(context.getResources().getColor(R.color.white))
                .setSubCalSize(15)
                .setLineSpacingMultiplier(1.6f)
                .setTitleText("请选择时间")
                .setOutSideCancelable(true)// default is true
                .isCyclic(false)// default is false
                .build();

        timePickerView.show();
    }

    public void getMinuteTime(Context context, final StringCallBack timeCallBack) {

        timePickerView = new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = formatter.format(date);
                timeCallBack.onSuccess(format);
            }
        }).setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .setCancelText(context.getString(R.string.cancel_btn))
                .setSubmitText(context.getString(R.string.sure_btn))
                .setContentSize(18)
                .setTitleSize(15)
                .setSubCalSize(15)
                .setCancelColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setSubmitColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setDividerColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setTextColorCenter(context.getResources().getColor(R.color.main_tab_blue_white))
                .setTitleBgColor(context.getResources().getColor(R.color.white))
                .setTitleColor(context.getResources().getColor(R.color.address_black_key))
                .setBgColor(context.getResources().getColor(R.color.white))
                .setLineSpacingMultiplier(1.6f)
                .setTitleText("请选择时间")
                .setOutSideCancelable(true)// default is true
                .isCyclic(false)// default is false
                .build();

        timePickerView.show();
    }

    public void getMonth(Context context, final StringCallBack timeCallBack) {
        ArrayList<String> timeList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        for (int i = 0; i < getDifferMonth() + 1; i++) {
            Calendar selectedDate = Calendar.getInstance();
            selectedDate.set(Calendar.MONTH, selectedDate.get(Calendar.MONTH) - i);
            String format = simpleDateFormat.format(selectedDate.getTime());
            timeList.add(format);
        }

        DialogUtils.getInstance().getBaseDialog(context, timeList, new StringPositionCallBack() {
            @Override
            public void onStringPosition(String str, int position) {
                timeCallBack.onSuccess(str);
            }
        });
    }


    public static long getDifferMonth() {
        Calendar fromDateCal = Calendar.getInstance();
        Calendar toDateCal = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        try {
            fromDateCal.setTime(simpleDateFormat.parse("2016-4"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        toDateCal.setTime(new Date());

        int fromYear = fromDateCal.get(Calendar.YEAR);
        int toYear = toDateCal.get((Calendar.YEAR));
        if (fromYear == toYear) {
            return Math.abs(fromDateCal.get(Calendar.MONTH) - toDateCal.get(Calendar.MONTH));
        } else {
            int fromMonth = 12 - (fromDateCal.get(Calendar.MONTH) + 1);
            int toMonth = toDateCal.get(Calendar.MONTH) + 1;
            return Math.abs(toYear - fromYear - 1) * 12 + fromMonth + toMonth;
        }
    }
}
