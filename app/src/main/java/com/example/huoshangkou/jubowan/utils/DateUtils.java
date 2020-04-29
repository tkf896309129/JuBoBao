package com.example.huoshangkou.jubowan.utils;

import android.support.annotation.Nullable;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：DateUtils
 * 描述：时间工具类
 * 创建时间：2017-01-03  10:35
 */

public class DateUtils {

    private static class DateHelper {
        private static DateUtils INSTANCE = new DateUtils();
    }

    public static DateUtils getInstance() {
        return DateHelper.INSTANCE;
    }

    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    private final Pattern DATE_PATTERN = Pattern.compile("\\d{4}\\-\\d{2}\\-\\d{2}");

    public String[] format(Date date) {
        return format(DATE_FORMAT.format(date));
    }

    @Nullable
    public String[] format(String date) {
        if (!DATE_PATTERN.matcher(date).matches()) {
            return null;
        }
        return date.split("-");
    }

    public String toDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public String toFormDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(date);
    }

    public String toDate(Date date, int add) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, add);
        return toDate(calendar.getTime());
    }

    /**
     * 计算时间差
     */
    public long getTimeCaculate(String startTime, String endTime) {
        long days = 0;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = df.parse(startTime);
            Date d2 = df.parse(endTime);
            long diff = d2.getTime() - d1.getTime();//这样得到的差值是微秒级别
            days = diff / (1000 * 60 * 60 * 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return days;
    }

    public long getTimeMinuteCaculate(String startTime, String endTime) {
        long days = 0;
        if (!StringUtils.isNoEmpty(startTime) || !StringUtils.isNoEmpty(endTime)) {
            return 1;
        }
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d1 = df.parse(startTime);
            Date d2 = df.parse(endTime);
            long diff = d2.getTime() - d1.getTime();//这样得到的差值是微秒级别

//            days = diff / (1000 * 60 * 60 * 24);
            days = diff / (1000 * 60);

        } catch (Exception e) {
            e.printStackTrace();
        }

        LogUtils.i(days + "");
        return days;
    }

    public long getTimeCaculate(String startTime) {
        long days = 0;
        Date date = new Date();
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = df.parse(startTime);
            Date d2 = date;
            long diff = d2.getTime() - d1.getTime();//这样得到的差值是微秒级别

            days = diff / (1000 * 60 * 60 * 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return days;
    }

    public long getTimeCaculateSecond(String startTime) {
        long days = 0;
        LogUtils.i(startTime + "");
        Date date = new Date();
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d1 = df.parse(startTime);
            Date d2 = date;
            long diff = d2.getTime() - d1.getTime();//这样得到的差值是微秒级别
            LogUtils.i(diff + "");
            days = diff / (1000 * 60 * 60 * 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return days;
    }

    public long getTimeCaculateMinute(String startTime, String endTime) {
        long days = 0;

        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
            Date d1 = df.parse(startTime);
            Date d2 = df.parse(endTime);
            days = d2.getTime() - d1.getTime();//这样得到的差值是微秒级别

        } catch (Exception e) {
            e.printStackTrace();
        }
        return days;
    }

    public long getTimeCaculateMinute(String startTime) {
        long days = 0;
        Date date = new Date();
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
            Date d1 = df.parse(startTime);
            Date d2 = date;
            days = d2.getTime() - d1.getTime();//这样得到的差值是微秒级别
            LogUtils.i(days + "");
//            days = diff / (1000 * 60 * 60 * 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 根据时间段计算时间
     */
    public String getTimeData(String startDays, String endDays, String startTime, String endTime, double timeCaculate) {
        if (!StringUtils.isNoEmpty(startDays) && !StringUtils.isNoEmpty(endDays)) {
            return "0";
        }

        //每次计算时间段之前先计算时间差
        timeCaculate = getTimeCaculate(startTime, endTime);

        switch (startDays) {
            case "上午":
                if (endDays.equals("上午")) {
                    timeCaculate = (timeCaculate + 0.5);
                } else if (endDays.equals("下午")) {
                    timeCaculate = (timeCaculate + 1);
                } else if (!StringUtils.isNoEmpty(endDays)) {
                    timeCaculate = (0);
                }
                break;
            case "下午":
                if (endDays.equals("上午")) {
                    timeCaculate = (timeCaculate + 0);
                } else if (endDays.equals("下午")) {
                    timeCaculate = (timeCaculate + 0.5);
                } else if (!StringUtils.isNoEmpty(endDays)) {
                    timeCaculate = (0);
                }
                break;
        }
        return timeCaculate + "天";
    }

    //获取当前时间戳
    public static String getTime() {
        Date date = new Date();
        return date.getTime() + "";
    }

    public static String getFormData(String strTime) {
        if (!StringUtils.isNoEmpty(strTime)) {
            return "xxxx-xx-xx";
        }

        String str = strTime.substring(strTime.indexOf("(") + 1, strTime.indexOf(")"));

        final String time = str.substring(0, str.length() - 5);
        LogUtils.i(time);//\/Date(1568649600000)\/
//        String dates  = Integer.parseInt(time) + Integer.parseInt(str.substring(str.length() - 5))+"";

        //final String timeZone = str.substring(str.length()-5, str.length());
        //System.out.println(timeZone);
        final Date date = new Date(Long.parseLong(time));
//        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        format.setTimeZone(TimeZone.getTimeZone("UTC+7"));
        String format1 = format.format(date);
        return format1;
    }

    public static String getFormDesData(String strTime) {
        if (!StringUtils.isNoEmpty(strTime)) {
            return "";
        }

        String str = strTime.substring(strTime.indexOf("(") + 1, strTime.indexOf(")"));

        final String time = str.substring(0, str.length());
        LogUtils.i(time);
        final Date date = new Date(Long.parseLong(time));
//        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        format.setTimeZone(TimeZone.getTimeZone("UTC+7"));
        String format1 = format.format(date);
        return format1;
    }

    public static String getFormMinuteData(String strTime) {
        String str = strTime.substring(strTime.indexOf("(") + 1, strTime.indexOf(")"));
        final String time = str.substring(0, str.length() - 5);
        final Date date = new Date(Long.parseLong(time));
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = format.format(date);
        return format1;
    }

    public static String getFormMinuteDesData(String strTime) {
        String str = strTime.substring(strTime.indexOf("(") + 1, strTime.indexOf(")"));
        final String time = str.substring(0, str.length());
        final Date date = new Date(Long.parseLong(time));
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = format.format(date);
        return format1;
    }
    //HH:mm:ss

    public static String getCurrentDate() {
        final Date date = new Date();
        final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String format1 = format.format(date);
        return format1;
    }

    public static String getCurrentWordDate() {
        final Date date = new Date();
        final SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        String format1 = format.format(date);
        return format1;
    }


    public static String getCurrentMonthDate() {
        final Date date = new Date();
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        String format1 = format.format(date);
        return format1;
    }


    public static String getCurrentMinute() {
        final Date date = new Date();
        final SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String format1 = format.format(date);
        return format1;
    }

    public static String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String format1 = format.format(date);
        return format1;
    }


    /**
     * 2.
     * 获取时间差xx小时xx分钟前（类似于新浪微博 的某条微博发表于几小时几分钟前）
     *
     * @param oldTime 老时间 2012-9-10 10:20:08
     * @return 描述
     * @author xl@yang
     */
    public static String getTimeGap(String oldTime) {
        String currentTime = getCurrentTime();

        LogUtils.i(currentTime + "\n" + oldTime);

        String hDes = "";
        String mDes = "";
        String[] newtime = currentTime.split(":");
        int newH = Integer.parseInt(newtime[0]);
        int newM = Integer.parseInt(newtime[1]);

        String[] oldtime = oldTime.split(":");
        int oldH = Integer.parseInt(oldtime[0]);
        int oldM = Integer.parseInt(oldtime[1]);

        int h = newH - oldH;
        int m = newM - oldM;
        int i = 0;
        int k = 0;
        if (0 < h) {
            if (0 < m) {
                hDes = h + "小时";
                mDes = m + "分钟";
            } else if (0 > m) {
                i = 60 - oldM + newM;
                mDes = i + "分钟";
                if (1 < h) {
                    k = h - 1;
                    hDes = k + "小时";
                }
            } else if (0 == m) {
                hDes = h + "小时";
            }
        } else if (0 < m) {
            mDes = m + "分钟";
        }
        return hDes + mDes + "前";
    }

    public static String getDatePoor(Date nowDate, Date endDate) {
        if (endDate == null || nowDate == null) {
            return "—    时间段    —";
        }
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小
        long hour = diff % nd / nh;
        // 计算差多少分钟  快点做吧
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return getTimeNoNull(day, "天") + getTimeNoNull(hour, "小时") + min + "分钟";
    }


    public static String getTimeNoNull(long time, String unit) {
        if (time == 0) {
            return "";
        }
        return time + unit;
    }

    public static String cal(int second) {
        int h = 0;
        int d = 0;
        int s = 0;
        int temp = second % 3600;
        if (second > 3600) {
            h = second / 3600;
            if (temp != 0) {
                if (temp > 60) {
                    d = temp / 60;
                    if (temp % 60 != 0) {
                        s = temp % 60;
                    }
                } else {
                    s = temp;
                }
            }
        } else {
            d = second / 60;
            if (second % 60 != 0) {
                s = second % 60;
            }
        }
        return h + "时" + d + "分" + s + "秒";
    }
}
