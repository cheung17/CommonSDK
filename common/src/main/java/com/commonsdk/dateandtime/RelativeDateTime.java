package com.commonsdk.dateandtime;

import java.util.Date;

/**
 * @author ztx
 *         日期相对当前时间转换为 几秒前，几分钟前，几小时前，几天前。
 */
public class RelativeDateTime {
    private static final long ONE_MINUTE = 60 * 1000;
    private static final long ONE_HOUR = 60 * 60 * 1000;
    private static final long ONE_DAY = ONE_HOUR * 24;
    private static final long ONE_WEEK = ONE_DAY * 7;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";

    public static String formatCallDate(String dateTime, String dateFormat) {
        Date date = DateAndTimeUtil.converStringToDate(dateTime, dateFormat);
        return format(date);
    }


    /**
     * 转换日期
     *
     * @param date 日期
     * @return 返回转换后的String
     */
    public static String format(Date date) {
        long delta = new Date().getTime() - date.getTime();
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            return DateAndTimeUtil.convertDate2String(DateAndTimeUtil.TIMEFORM_MINUTE, date).replace("-", "/");
        }
        if (delta < 48L * ONE_HOUR) {
            return "昨天 " + DateAndTimeUtil.convertDate2String(DateAndTimeUtil.TIMEFORM_MINUTE, date).replace("-", "/");
        } else {
            return DateAndTimeUtil.convertDate2String(DateAndTimeUtil.DATEANDTIMEFORM, date).replace("-", "/");
        }
        /*if (delta < 12L * 4L * ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
        } else {
            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
        }*/
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }
}
